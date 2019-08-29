package com.shopp.api.service;

import com.shopp.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/order")
public interface OrderService {

    /**
     * 使用订单id修改订单状态
     *
     * @param
     * @return
     */
    @RequestMapping("/updateOrderId")
    ResponseBase updateOrderId(@RequestParam("isPay") Long isPay, @RequestParam("payId") String aliPayId,
                               @RequestParam("orderNumber") String orderNumber);

}
