package com.example.filter.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class OpenApiInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("pre handle");
        // controller 전달, false 전달하지 않는다.

        var handlerMethod = (HandlerMethod) handler;

//        OpenApi가 메소드에 있는지 검사
        var methodLevel = handlerMethod.getMethodAnnotation(OpenApi.class);
        if (methodLevel != null){
            log.info("method level");
            return true;
        }
//        OpenApi가 클래스에 있는지 검사
        var classLevel = handlerMethod.getBeanType().getAnnotation(OpenApi.class);
        if (classLevel != null){
            log.info("class level");
            return true;
        }

        log.info("open api 아닙니다. : {}",request.getRequestURI());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("post handler");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("after completion");
        //        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
