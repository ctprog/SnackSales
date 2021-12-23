package com.snack.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user")!=null||request.getSession().getAttribute("aId")!=null){
            return true;
        }
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath+"/register");
        return true;
    }
}
