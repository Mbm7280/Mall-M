package com.ex.mall.base.config;

import com.ex.mall.base.CommonResult;
import com.ex.mall.dto.other.AdminUserDetails;
import com.ex.mall.model.UmsAdmin;
import com.ex.mall.model.UmsPermission;
import com.ex.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.ex.mall.base.component.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
* @Package: com.ex.mall.base.config
* @ClassName: SecurityConfig
* @Description: config
 *              -- security
* @Author: mbm
* @date: 2020/6/27 14:44
* @Version: 1.0
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UmsAdminService adminService;

    @Autowired
   private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon,ico",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**"
                )
                .permitAll()
                .antMatchers("/admin/login","/admin/register")
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .anyRequest()
                .authenticated();

        httpSecurity.headers().cacheControl();
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            CommonResult commonResult = adminService.getAdminByUsername(username);
            UmsAdmin admin = (UmsAdmin) commonResult.getData();
            if(null != admin){
                CommonResult commonResultPer = adminService.getPerMissionList(admin.getId());
                if(null != commonResultPer){
                    List<UmsPermission> permissionList = (List<UmsPermission>) commonResultPer.getData();
                    return new AdminUserDetails(admin,permissionList);
                }
            }
            throw new UsernameNotFoundException("用户名或则密码错误");
        };
    }

    /**
     * @author mbm X
     * @methodname : jwtAuthenticationTokenFilter
     * @description : Jwt - Security
     * @return : com.ex.mall.base.config.JwtAuthenticationTokenFilter
     * @date : 2020/6/27 15:18
     */
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    /**
     * @author mbm X
     * @methodname : passwordEncoder
     * @description : password - security
     * @return : org.springframework.security.crypto.password.PasswordEncoder
     * @date : 2020/6/27 15:21
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * @author mbm X
     * @methodname : configure
     * @description : security  - password
     * @param auth :
     * @return : void
     * @date : 2020/6/27 15:21
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }



}
