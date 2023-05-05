package com.crudlogger.crudloggerstarter.aspect;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ogbozoyan
 * @date 10.04.2023
 */
@Component
public class CachingResponseBodyFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse currentResponse = (HttpServletResponse) servletResponse;
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(currentResponse);
        try {
            chain.doFilter(servletRequest, wrappedResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            wrappedResponse.copyBodyToResponse();
        }
    }
}
