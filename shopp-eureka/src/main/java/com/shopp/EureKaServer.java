package com.shopp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 类描述:
 *
 * @author: licheng
 * @date: 2019-06-12 15:03
 */
@SpringBootApplication
@EnableEurekaServer
public class EureKaServer {

    public static void main(String[] args) {
        SpringApplication.run(EureKaServer.class,args);
    }
}
