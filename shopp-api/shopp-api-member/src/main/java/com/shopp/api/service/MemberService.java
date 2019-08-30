package com.shopp.api.service;

import com.shopp.base.ResponseBase;
import com.shopp.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/member")
public interface MemberService {

    /**
     * 使用userId查找用户信息
     */
    @RequestMapping("/findUserById")
    ResponseBase findUserById(Long userId);

    /**
     *  注册
     */
    @RequestMapping("/regUser")
    ResponseBase regUser(@RequestBody UserEntity user);

    @RequestMapping("/login")
    ResponseBase login(@RequestBody UserEntity user);

    @RequestMapping("/findByTokenUser")
    ResponseBase findByTokenUser(@RequestParam("token") String token);

    @RequestMapping("/findByOpenIdUser")
    ResponseBase findByOpenIdUser(@RequestParam("openid") String openid);

    @RequestMapping("/qqLogin")
    ResponseBase qqLogin(@RequestBody UserEntity user);


    @RequestMapping("/setRedisTest")
    ResponseBase setRedisTest(@RequestParam("key") String key,@RequestParam("value") String value);

    @RequestMapping("/getRedis")
    ResponseBase getRedis(@RequestParam("key") String key);

}
