package com.dong.buddy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor
{

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception
    {

    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception
    {

        HttpSession session = req.getSession();
        String loginName = (String) session.getAttribute("loginName");
        if (loginName != null)
        {
            return true;
        }

        // XMLHttpRequest,ajax请求
        String xhr = req.getHeader("X-Requested-With");
        if (!StringUtils.isEmpty(xhr))
        {
            System.out.println("非ajax请求");
        }
        else
        {
            resp.sendRedirect("/html/login.html");
        }
        return false;
    }

}