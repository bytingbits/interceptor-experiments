package com.apitrial.apitrial.interceptor;

import org.hibernate.engine.internal.Collections;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class LoggingInterceptor implements HandlerInterceptor{ //this line
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Log the request details
        System.out.println("Intercepted Request Details:");
       
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Request Method: " + request.getMethod());
         
      
        
        return true; // continue with the request processing
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        // Log the response details
        System.out.println("Response Status: " + response.getStatus());
        System.out.println("----");
    }


}
