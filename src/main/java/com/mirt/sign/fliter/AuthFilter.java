package com.mirt.sign.fliter;

import com.mirt.sign.util.JwtUtil;
import io.jsonwebtoken.Claims;
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

    private static final String[] PATH_NO_NEED_FILTER = {"/", "/login", "/logout", "/register", "/fail", "/auth"};

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("权限过滤器：'AuthFilter' to urls:[/*]");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI().substring(request.getContextPath().length());
        boolean noNeedFilter = Arrays.stream(PATH_NO_NEED_FILTER).anyMatch(s -> s.contains(url));
        // 获取sessionId

        String sessionId = request.getSession().getId();
        String jwt = request.getHeader("token");
        if (noNeedFilter || isAllowed(sessionId, jwt)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 设置重定向地址
            response.sendRedirect("/");
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
    private boolean isAllowed(String sessionId, String jwt) {
        if (Objects.isNull(sessionId) || Objects.isNull(jwt))
            return false;
        // todo 根据sessionId获取当前人email 与 jwt的email比对

        // todo 判断Jwt是否符合要求
        Claims claims = JwtUtil.parseJwt(jwt);
        if (Objects.isNull(claims))
            return false;
        if (!"mirt".equalsIgnoreCase(claims.getIssuer()))
            return false;
        String userEmail = claims.get("user-email", String.class);

        return true;
    }
}
