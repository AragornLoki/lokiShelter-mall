package tk.lokiShelter.manage.config.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tk.lokiShelter.manage.util.JwtTokenUtil;
import tk.lokiShelter.manage.api.ResponseBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import static tk.lokiShelter.manage.util.AdminResponseCode.ADMIN_INVALID_ACCOUNT;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    public LoginFilter(String filterProcessesUrl, AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl(filterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)
                || request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
            Map<String, String> loginData = new HashMap<>();
            try {
                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            }catch (IOException e) {
                e.printStackTrace();
            }
            String username = loginData.get(getUsernameParameter());
            String password = loginData.get(getPasswordParameter());
            if (username == null) {
                username = "";
            }
            if (password == null) {
                password = "";
            }
            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);
            setDetails(request, authRequest);
            SecurityContextHolder.getContext().setAuthentication(authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }else{
            return super.attemptAuthentication(request,response);
        }

    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse resp, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Object principal=authResult.getPrincipal();
        String name=new String();
        if (principal instanceof UserDetails) {
            name =((UserDetails) principal).getUsername();
        }
        if (principal instanceof Principal) {
            name =((Principal) principal).getName();
        }
        String token = JwtTokenUtil.generateToken(name);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", "Bearer ");
        String s = new ObjectMapper().writeValueAsString(ResponseBean.ok(tokenMap));
        out.write(s);
        out.flush();
        out.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (failed instanceof LockedException) {
            out.write(new ObjectMapper().writeValueAsString(ResponseBean.fail(ADMIN_INVALID_ACCOUNT, "账户被锁定，请联系管理员!")));
        } else if (failed instanceof CredentialsExpiredException) {
            out.write(new ObjectMapper().writeValueAsString(ResponseBean.fail(ADMIN_INVALID_ACCOUNT, "密码过期，请联系管理员!")));
        } else if (failed instanceof AccountExpiredException) {
            out.write(new ObjectMapper().writeValueAsString(ResponseBean.fail(ADMIN_INVALID_ACCOUNT, "账户过期，请联系管理员!")));
        } else if (failed instanceof DisabledException) {
            out.write(new ObjectMapper().writeValueAsString(ResponseBean.fail(ADMIN_INVALID_ACCOUNT, "账户被禁用，请联系管理员!")));
        } else if (failed instanceof BadCredentialsException) {
            out.write(new ObjectMapper().writeValueAsString(ResponseBean.fail(ADMIN_INVALID_ACCOUNT, "用户帐号或密码不正确")));
        }

        out.flush();
        out.close();


    }

}
