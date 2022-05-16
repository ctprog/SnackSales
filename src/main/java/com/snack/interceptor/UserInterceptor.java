package com.snack.interceptor;

import com.snack.constant.SnackConstant;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute(SnackConstant.LOGIN_USER)!=null){
            return true;
        }
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath+"/register");
        return false;
    }
}
