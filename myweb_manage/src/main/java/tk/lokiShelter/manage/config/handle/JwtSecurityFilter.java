package tk.lokiShelter.manage.config.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import tk.lokiShelter.manage.util.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtSecurityFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        //OPTIONS请求直接放行
        if(request.getMethod().equals(HttpMethod.OPTIONS.toString())){
            filterChain.doFilter(request, response);
            return;
        }
        if (authHeader == null && !authHeader.startsWith("Bearer ")) {
            throw new AccessDeniedException("抱歉，您没有访问权限");
        }
        String authToken = authHeader.substring("Bearer ".length());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(JwtTokenUtil.getUserNameFromToken(authToken));
        if(JwtTokenUtil.validateToken(authToken,userDetails))
            filterChain.doFilter(request, response);
        else
            throw new AccessDeniedException("抱歉，您没有访问权限");
    }
}
