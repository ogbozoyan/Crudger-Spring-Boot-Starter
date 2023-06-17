package ru.crudger.crudger.aspect;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>
 * Chain which caching Request for multiple getBody usage
 *
 * @author ogbozoyan
 * @date 09.04.2023
 */
@Component
public class CachingRequestBodyFilter extends GenericFilterBean {

    /**
     * Filters the servlet request and caches the request body for multiple uses.
     *
     * @param servletRequest  the servlet request
     * @param servletResponse the servlet response
     * @param chain           the filter chain
     * @throws IOException      if an I/O error occurs during the filtering process
     * @throws ServletException if a servlet-specific error occurs during the filtering process
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest currentRequest = (HttpServletRequest) servletRequest;
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(currentRequest);

        chain.doFilter(wrappedRequest, servletResponse);
    }
}