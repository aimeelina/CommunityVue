package com.aimeelina.communityvue.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static String getValueFromRequest(HttpServletRequest request, String name){
        if(request==null||name==null){
            throw new IllegalArgumentException("参数为空");
        }
        Cookie[] cookies=request.getCookies();
//        System.out.println("读取cookie");
        if(cookies!=null){
            for(Cookie cookie:cookies){
//                System.out.println(cookie.getName()+":"+cookie.getValue());
                if(cookie.getName().equals(name)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
