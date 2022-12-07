package com.load.food.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    private final String xClientKey;

    public AuthInterceptor(String xClientKey) {
        this.xClientKey = xClientKey;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("==> [AuthInterceptor] preHandle request ip: {}", request.getRemoteAddr());

        if (request.getHeader("X-CLIENT-KEY") != null) {
            if (request.getHeader("X-CLIENT-KEY").equals(xClientKey))
                return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", HttpStatus.OK);
        resultMap.put("error", "X-CLIENT-KEY isEmpty or different.");

        new MappingJackson2HttpMessageConverter().write(resultMap, MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response));

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
