package com.mmt.enterprise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;

import com.mmt.enterprise.support.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private MyUserDetailsService myUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        // http.authorizeRequests()每个匹配器按照它们被声明的顺序被考虑。
        http.authorizeRequests()
            // 所有用户均可访问的资源
            .antMatchers("/static/font-awesome-4.7.0/**",
            			"/static/fonts/**",
            			"/static/icons-reference/**",
            			"/static/css/**",
            			"/static/js/**",
            			"/static/img/**",
            			"/static/bootstrap-4.3.1-dist/**",
            			"/static/mmt/**",
            			"/enterprise",
            			"/"
            		     )
            .permitAll()
            // ROLE_USER的权限才能访问的资源
            //.antMatchers("/manage/**").hasRole("MANAGE")
            // 任何尚未匹配的URL只需要验证用户即可访问
            .anyRequest().authenticated()
            .and().formLogin().loginPage("/enterprise/login")// 指定登录页面,授予所有用户访问登录页面
            .successHandler(new ForwardAuthenticationSuccessHandler("/enterprise/main"))
            .failureUrl("/enterprise/login-error").permitAll()
            .and().logout().logoutSuccessUrl("/enterprise/login")
            .and().csrf().disable();
        }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
