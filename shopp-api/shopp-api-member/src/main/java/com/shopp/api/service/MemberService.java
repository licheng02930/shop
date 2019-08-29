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

    ResponseBase login(@RequestBody UserEntity user);

    ResponseBase findByTokenUser(@RequestParam("token") String token);

    ResponseBase findByOpenIdUser(@RequestParam("openid") String openid);

    ResponseBase qqLogin(@RequestBody UserEntity user);


    @RequestMapping("/setRedisTest")
    public ResponseBase setRedisTest(String key, String value);

    @RequestMapping("/getRedis")
    public ResponseBase getRedis(String key);

}
