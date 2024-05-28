package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 진입전
        log.info(">>>>> 진입 ");
        chain.doFilter(request, response);
        log.info("<<<<< 리턴");
        // 진입후

    }
}