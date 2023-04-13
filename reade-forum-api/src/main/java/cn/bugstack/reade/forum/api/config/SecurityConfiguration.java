package cn.bugstack.reade.forum.api.config;

import cn.bugstack.reade.forum.application.converter.RestBean;
import cn.bugstack.reade.forum.application.service.IUserLoginService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */

@Configuration
/** @EnableWebSecurity 用于在我们的项目中启用SpringSecurity。为某些请求路径开启安全认证策略
 * 1.加载了WebSecurityConfiguration配置类, 配置安全认证策略。
 * 2.加载了AuthenticationConfiguration, 配置了认证信息。
 * */
@EnableWebSecurity
public class SecurityConfiguration {

    @Resource
    private IUserLoginService iUserLoginService;

    //   数据源
    @Resource
    DataSource dataSource;

    /**
     * @description: 登录配置器
     * /api/auth/login 登录地址
     * /api/auth/loginOut 登出地址
     * @author: zhd
     * @date: 2023/4/12 11:34
     * @param:
     * @param: httpSecurity
     * @return: SecurityFilterChain
     **/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity
            , PersistentTokenRepository repository) throws Exception {
        return httpSecurity
                .authorizeHttpRequests()
                .antMatchers("/api/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/api/auth/login")
                .successHandler(this::onAuthenticationSuccess)
                .failureHandler(this::onAuthenticationFailure)
                .and()
                .logout()
                .logoutUrl("/api/auth/logout")
                .logoutSuccessHandler(this::onAuthenticationSuccess)
                .and()
//                配置页面上的记住我
                .rememberMe()
                .rememberMeParameter("remember")
                .tokenRepository(repository)
//                有效时间一周（可自行更改）
                .tokenValiditySeconds(3600 * 24 * 7)
                .and()
                .csrf()
                .disable()
                // 以下两行是处理跨域请求的
                .cors()
                .configurationSource(this.configurationSource())
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(this::onAuthenticationFailure)
                .and()
                .build();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        //        数据源位置
        jdbcTokenRepository.setDataSource(dataSource);
        //        自动创建表/创建完成后要改成false
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    /**
     * @description: 处理跨域
     * @author: zhd
     * @date: 2023/4/12 1:19
     * @param:
     * @return: CorsConfigurationSource
     **/
    private CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // 生成环境要配置成自己的 生产地址
        configuration.addAllowedOriginPattern("*");
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addExposedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * @description: 使用自定义的用户校验规则
     * @author: zhd
     * @date: 2023/4/11 21:59
     * @param:
     * @param: httpSecurity
     * @return: AuthenticationManager
     **/
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(iUserLoginService)
                .and()
                .build();
    }

    /**
     * @description: 解决 There is no PasswordEncoder mapped for the id "null" 这个异常
     * @author: zhd
     * @date: 2023/4/11 21:58
     * @param:
     * @return: BCryptPasswordEncoder
     **/
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @description: 请求成功返回信息
     * @author: zhd
     * @date: 2023/4/11 14:23
     * @param:
     * @param: request
     * @param: response
     * @param: authentication
     * @return: void
     **/
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setCharacterEncoding("utf-8");
        if (request.getRequestURI().endsWith("login"))
            response.getWriter().write(JSONObject.toJSONString(RestBean.success("登录成功")));
        else if (request.getRequestURI().endsWith("logout")) {
            response.getWriter().write(JSONObject.toJSONString(RestBean.success("退出成功")));
        }
    }

    /**
     * @description: 请求失败返回信息
     * @author: zhd
     * @date: 2023/4/11 14:23
     * @param:
     * @param: request
     * @param: response
     * @param: exception
     * @return: void
     **/
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONObject.toJSONString(RestBean.failure(401, exception.getMessage())));
    }
}
