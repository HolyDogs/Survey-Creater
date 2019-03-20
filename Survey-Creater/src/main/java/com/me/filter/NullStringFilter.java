package com.me.filter;

import com.me.config.ManageHttpRequestWrapper;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @ClassName NullStringFilter
 * @Description TODO
 * @Author xufeng
 * @Data 2019/3/10 15:59
 * @Version 1.0
 **/
@WebFilter(filterName = "select",urlPatterns = "/manager/selectPage")
public class NullStringFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ManageHttpRequestWrapper requestWrapper = new ManageHttpRequestWrapper((HttpServletRequest) servletRequest);
        String email = servletRequest.getParameter("email");
        if ("".equals(email)){
            requestWrapper.setParameter("email",null);
        }
        String name = servletRequest.getParameter("name");
        if ("".equals(name)){
           requestWrapper.setParameter("name",null);
        }
        filterChain.doFilter(requestWrapper,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
