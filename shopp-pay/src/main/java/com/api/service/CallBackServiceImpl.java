package com.api.service;

import java.util.Map;

import com.alipay.config.AlipayConfig;
import com.dao.PaymentInfoDao;
import com.fegin.OrderServiceFegin;
import com.shopp.api.service.CallBackService;
import com.shopp.base.BaseApiService;
import com.shopp.base.ResponseBase;
import com.shopp.constants.Constants;
import com.shopp.entity.PaymentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CallBackServiceImpl extends BaseApiService implements CallBackService {
    @Autowired
    private PaymentInfoDao paymentInfoDao;
    @Autowired
    private OrderServiceFegin orderFegin;

    @Override
    public ResponseBase synCallBack(@RequestParam Map<String, String> params) {
        // 1.日志记录
        log.info("#####支付宝同步通知synCallBack#####开始,params:{}", params);
        // 2.验签操作
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key,
                    AlipayConfig.charset, AlipayConfig.sign_type); // 调用SDK验证签名
            log.info("#####支付宝同步通知signVerified:{}######", signVerified);
            // ——请在这里编写您的程序（以下代码仅作参考）——
            if (!signVerified) {
                return setResultError("验签失败!");
            }
            // 商户订单号
            String outTradeNo = params.get("out_trade_no");
            // 支付宝交易号
            String tradeNo = params.get("trade_no");
            // 付款金额
            String totalAmount = params.get("total_amount");
            JSONObject data = new JSONObject();
            data.put("outTradeNo", outTradeNo);
            data.put("tradeNo", tradeNo);
            data.put("totalAmount", totalAmount);
            return setResultSuccess(data);
        } catch (Exception e) {
            log.error("####支付宝同步通知出现异常,ERROR:", e);
            return setResultError("系统错误!");
        } finally {
            log.info("#####支付宝同步通知synCallBack#####结束,params:{}", params);
        }

    }

    @Override
    public String asynCallBack(@RequestParam Map<String, String> params) {
        // 1.日志记录
        log.info("#####支付宝异步通知synCallBack#####开始,params:{}", params);
        // 2.验签操作
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key,
                    AlipayConfig.charset, AlipayConfig.sign_type); // 调用SDK验证签名
            log.info("#####支付宝异步通知signVerified:{}######", signVerified);
            // ——请在这里编写您的程序（以下代码仅作参考）——
            if (!signVerified) {
                return Constants.PAY_FAIL;
            }
            // 商户订单号
            String outTradeNo = params.get("out_trade_no");
            // 调用订单数据库修改订单表数据库
            // 3 处理幂等
            PaymentInfo orderIdPayInfo = paymentInfoDao.getByOrderIdPayInfo(outTradeNo);
            if (orderIdPayInfo == null) {
                return Constants.PAY_FAIL;
            }
            // 判断是否已经支付过，如果已经支付过,返回success,防止重试产生重复
            Integer state = orderIdPayInfo.getState();
            if (state == 1) {
                return Constants.PAY_SUCCESS;
            }

            // 支付宝交易号
            String tradeNo = params.get("trade_no");
            orderIdPayInfo.setState(1);
            orderIdPayInfo.setPlatformorderId(tradeNo);
            orderIdPayInfo.setPayMessage(params.toString());
            Integer updatePayInfo = paymentInfoDao.updatePayInfo(orderIdPayInfo);
            if (updatePayInfo <= 0) {
                return Constants.PAY_FAIL;
            }
            // 调用订单接口通知修改订单状态
            ResponseBase ordeResponseBase = orderFegin.updateOrderId(1l, tradeNo, outTradeNo);
            if (!ordeResponseBase.getRtnCode().equals(Constants.HTTP_RES_CODE_200)) {
                return Constants.PAY_FAIL;
            }
            return Constants.PAY_SUCCESS;
        } catch (Exception e) {
            log.error("####支付宝异步通知出现异常,ERROR:", e);
            return Constants.PAY_FAIL;
        } finally {
            log.info("#####支付宝异步通知synCallBack#####结束,params:{}", params);
        }
    }

}
