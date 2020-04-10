package com.example.bbcb.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *只有经过DispatcherServlet 的请求，才会走拦截器链
 * HandlerInterceptor:拦截器
 * HandlerInterceptorAdapter
 *
 * 1,日志记录：记录请求信息的日志，以便进行信息监控、信息统计、计算PV（Page View）等
 * 2,权限检查：如登录检测，进入处理器检测检测是否登录
 * 3,性能监控：通过拦截器在进入处理器之前记录开始时间，在处理完后记录结束时间，从而得到该请求的处理时间。（反向代理，如apache也可以自动记录）；
 * 4,通用行为：读取cookie得到用户信息并将用户对象放入请求，从而方便后续流程使用，还有如提取Locale、Theme信息等，只要是多个处理器都需要的即可使用拦截器实现
 *
 */
@Slf4j
public class ProcessPerformanceInterceptor extends HandlerInterceptorAdapter {
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ProcessPerformance-StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long beginTime = System.currentTimeMillis();// 1、开始时间
        startTimeThreadLocal.set(beginTime);// 线程绑定变量（该数据只有当前请求的线程可见）
        return true;// 继续流程
    }
    /*@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long endTime = System.currentTimeMillis();// 2、结束时间
        long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
        long consumeTime = endTime - beginTime;// 3、消耗的时间
        if (consumeTime > 500) {// 此处认为处理时间超过500毫秒的请求为慢请求
            log.info(String.format("%s consume %d millis", request.getRequestURI(), consumeTime));
        }
    }
}
