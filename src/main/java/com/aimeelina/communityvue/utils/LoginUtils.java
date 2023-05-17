package com.aimeelina.communityvue.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.digester.Digester;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class LoginUtils {
    //生成随机字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    //加密
    public static String md5(String key){
        if(StringUtils.isBlank(key)) {//判断字符串是否为空或是否只有空格
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
