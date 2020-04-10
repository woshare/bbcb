package com.example.bbcb.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 实现过滤功能，即对每个请求及响应增加的额外的预处理和后处理
 * 执行该方法之前，即对用户请求进行预处理
 * 执行该方法之后，即对服务器响应进行后处理
 * 值得注意的是，chain.doFilter()方法执行之前为预处理阶段
 * 该方法执行结束即代表用户的请求已经得到控制器处理
 * 因此，如果再doFilter中忘记调用chain.doFilter()方法，则用户的请求将得不到处理。
 *
 */
@Slf4j
@Component
public class UGCContentsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Execute cost="+(System.currentTimeMillis()-start));
    }

    @Override
    public void destroy() {

    }
}
