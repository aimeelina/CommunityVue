package com.aimeelina.communityvue.controller.interceptor;

import com.aimeelina.communityvue.annotation.LoginRequired;
import com.aimeelina.communityvue.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {
    @Autowired
    private HostHolder hostHolder;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            //在调用controller中的某个方法前执行下列代码
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            LoginRequired loginRequired = handlerMethod.getMethodAnnotation(LoginRequired.class);
            // 拦截器的执行顺序是先LoginInterceptor再LoginRequiredInterceptor
            // 如果用户已登录，LoginInterceptor会将用户信息保存到hostHolder中
            if(loginRequired!=null&&hostHolder.getUser()==null){
                //当前访问的方法需要登录才能访问，且当前用户未登录
                System.out.println("用户未登录，被LoginRequiredInterceptor拦截下");
                return false;
            }
        }
        return true;
    }
}
