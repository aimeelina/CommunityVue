package com.aimeelina.communityvue.controller.interceptor;

import com.aimeelina.communityvue.entity.LoginTicket;
import com.aimeelina.communityvue.entity.User;
import com.aimeelina.communityvue.service.UserService;
import com.aimeelina.communityvue.utils.CookieUtil;
import com.aimeelina.communityvue.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("运行LoginInterceptor.preHandle");
        //从request中获得cookie,再从cookie中查找ticket的值
        String ticket = CookieUtil.getValueFromRequest(request, "ticket");
        if (ticket != null) {
            //查数据库得到loginTicket
            LoginTicket loginTicket = userService.getLoginTicket(ticket);
            //检查loginTicket是否有效
            if (loginTicket != null && loginTicket.getStatus() == 0 && loginTicket.getExpired().after(new Date())) {
                //loginTicket合法,根据loginTicket获取user对象
                User user = userService.findUserById(loginTicket.getUserId());
                //将user存到hostHolder对象中
                hostHolder.setUser(user);
                System.out.println("preHandle在hostHolder里存了user"+user);
            }
        }
        return true;
//        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
////        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//        System.out.println("运行LoginInterceptor.postHandle");
//        User user = hostHolder.getUser();
//        System.out.println("postHandle从hostHolder里取user"+user);
//        if(user!=null){
//            modelAndView.addObject("usermsg",user);
//            System.out.println("准备运行LoginInterceptor.returnJson");
//            returnJson(response,user.toString());
//            System.out.println("运行完成LoginInterceptor.returnJson");
//        }
//    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        System.out.println("运行LoginInterceptor.afterCompletion");
        hostHolder.clear();
    }

//    private void returnJson(HttpServletResponse response, String json) throws Exception {
//        PrintWriter writer = null;
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=utf-8");
//        try {
////            writer = response.getWriter();
//            response.getOutputStream().write(json.getBytes());
////            writer.print(json);
//
//        } catch (IOException e) {
//        } finally {
//            if (writer != null)
//                writer.close();
//        }
//    }
}
