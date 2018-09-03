package com.mirt.sign.fliter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * 用于权限验证的过滤器
 *
 * @author Mirt Zhang
 * @date 2018/8/30
 */

@Slf4j
@WebFilter(filterName = "AuthFilter", description = "权限验证", urlPatterns = "/*")
@Order(1)
public class AuthFilter implements Filter {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String[] PATH_NO_NEED_FILTER = {"/login", "/logout", "/register"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("权限过滤器：'AuthFilter' to urls:[/*]");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI().substring(request.getContextPath().length());
        boolean noNeedFilter = Arrays.stream(PATH_NO_NEED_FILTER).anyMatch(url::contains);
        // todo 获取sessionId
        String sessionId = "sessionId";
        if (noNeedFilter || isAllowed(sessionId)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // todo 设置重定向地址
            response.sendRedirect("login");
        }
    }

    @Override
    public void destroy() {
        log.info("权限过滤器：'AuthFilter' to urls:[/*] 被销毁");
    }

    /**
     * 判断token是否合法
     *
     * @param sessionId
     * @return
     */
    private boolean isAllowed(String sessionId) {
        if (Objects.isNull(sessionId))
            return false;
        // todo 判断sessionId是否存在

        return true;
    }
}
