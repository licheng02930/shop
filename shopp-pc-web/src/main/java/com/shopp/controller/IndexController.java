package com.shopp.controller;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import com.shopp.base.ResponseBase;
import com.shopp.constants.Constants;
import com.shopp.fegin.MemberServiceFegin;
import com.shopp.utils.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    private static final String INDEX = "index";
    @Autowired
    private MemberServiceFegin memberServiceFegin;

    // 主页--查询用户信息
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest reqest) {
        // 1.从cookie中获取 token信息
        String token = CookieUtil.getUid(reqest, Constants.COOKIE_MEMBER_TOKEN);
        // 2.如果cookie 存在 token，调用会员服务接口，使用token查询用户信息
        if (!StringUtils.isEmpty(token)) {
            ResponseBase responseBase = memberServiceFegin.findByTokenUser(token);
            if (responseBase.getRtnCode().equals(Constants.HTTP_RES_CODE_200)) {
                LinkedHashMap userData = (LinkedHashMap) responseBase.getData();
                String username = (String) userData.get("username");
                reqest.setAttribute("username", username);
            }
        }
        return INDEX;
    }

}
