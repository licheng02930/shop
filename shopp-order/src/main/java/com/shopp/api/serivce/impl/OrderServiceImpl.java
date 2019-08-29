package com.shopp.api.serivce.impl;

import com.shopp.api.service.OrderService;
import com.shopp.base.BaseApiService;
import com.shopp.base.ResponseBase;
import com.shopp.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderServiceImpl extends BaseApiService implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public ResponseBase updateOrderId(@RequestParam("isPay") Long isPay, @RequestParam("payId") String aliPayId,
                                      @RequestParam("orderNumber") String orderNumber) {
        int updateOrder = orderDao.updateOrder(isPay, aliPayId, orderNumber);
        if (updateOrder <= 0) {
            return setResultError("系统错误!");
        }
        return setResultSuccess();
    }

}
