package com.shopp.utils;

import com.shopp.constants.Constants;

import java.util.UUID;


public class TokenUtils {

    public static String getMemberToken() {
        return Constants.TOKEN_MEMBER + "-" + UUID.randomUUID();
    }

}
