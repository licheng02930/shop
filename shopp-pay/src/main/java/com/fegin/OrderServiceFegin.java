package com.fegin;

import com.shopp.api.service.OrderService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;


@FeignClient("order")
@Component
public interface OrderServiceFegin extends OrderService {

}

