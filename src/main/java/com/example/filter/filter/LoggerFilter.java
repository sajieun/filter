package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
@Component
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 진입전
        log.info(">>>>> 진입 ");

        var req = new HttpServletRequestWrapper((HttpServletRequest) request);
        var res = new HttpServletResponseWrapper((HttpServletResponse) response);

        var br = req.getReader();
        var list = br.lines().collect(Collectors.toList());
        list.forEach(it->{
            log.info("{}",it);
        });

        chain.doFilter(req, res);
        log.info("<<<<< 리턴");
        // 진입후

    }
}