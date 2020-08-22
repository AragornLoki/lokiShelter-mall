package tk.lokiShelter.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import tk.lokiShelter.manage.config.handle.LoginFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tk.lokiShelter.manage.config.handle.JwtFilter;
import tk.lokiShelter.manage.config.handle.LoginFilter;
import tk.lokiShelter.manage.config.service.UserDetailsServiceImpl;
import tk.lokiShelter.manage.config.temple.IgnoreUrlsBean;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }
    @Bean
    public IgnoreUrlsBean ignoreUrlsBean() {
        return new IgnoreUrlsBean();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        //获取用户账号密码及权限信息
        return new UserDetailsServiceImpl();
    }
    //注册自定义的UsernamePasswordAuthenticationFilter
    @Bean
    LoginFilter loginFilter() throws Exception {
        return new LoginFilter("/admin/login",authenticationManagerBean());
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    //<security : authentication-manager>
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
    //<security:http>
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();
        //不需要保护的资源路径允许访问
        for (String url : ignoreUrlsBean().getUrls()) {
            registry.antMatchers(url).permitAll();
        }
        http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAt(loginFilter(),UsernamePasswordAuthenticationFilter.class)
                // 自定义权限拦截器JWT过滤器
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/");
    }
}
