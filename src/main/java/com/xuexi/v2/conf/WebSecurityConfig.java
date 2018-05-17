package com.xuexi.v2.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.xuexi.v2.security.CustomAccessDeniedHandler;
import com.xuexi.v2.security.CustomSecurityFilter;
import com.xuexi.v2.security.CustomUserDetailsService;
import com.xuexi.v2.security.LoginSuccessHandler;
import com.xuexi.v2.utils.MD5Util;

 

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomSecurityFilter customSecurityFilter;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;

	@Autowired
	private WebProperties webProperties;
	
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();

	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 
		  http.csrf().disable() 
	        .authorizeRequests()  
	        .antMatchers(webProperties.getProjectName()+"/static/**").permitAll()
	        .antMatchers(webProperties.getProjectName()+"/register").permitAll()
	        .anyRequest().authenticated()  
	        .and()  
	        .formLogin()  
	        .loginPage(webProperties.getProjectName()+"/login")      
	  //      .failureUrl("/login?error")
	        .successHandler(loginSuccessHandler())//code3
//	        .defaultSuccessUrl("/home")  defaultSuccessUrl 和 successHandler只能用一个，否则 defaultSuccessUrl 会覆盖 successHandler
	        .permitAll()
	        .and()  
	        .logout()  
	        .logoutSuccessUrl(webProperties.getProjectName()+"/login")  
	        .permitAll()  
	        .invalidateHttpSession(true)  
	        .and()  
	        .rememberMe()  
	        .tokenValiditySeconds(1209600);  
		 http.headers().frameOptions().disable();//解决页面iframe的问题 X-Frame-Options
		 http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
		 http.addFilterAfter(customSecurityFilter, FilterSecurityInterceptor.class);
		 
		 //在正确的位置添加我们自定义的过滤器  
		
	 
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// 指定密码加密所使用的加密器为passwordEncoder()
		// 需要将密码加密后写入数据库
		//auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
		auth.userDetailsService(customUserDetailsService).passwordEncoder(new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return MD5Util.encode((String) rawPassword);
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return encodedPassword.equals(MD5Util.encode((String) rawPassword));
			}
		});
		auth.eraseCredentials(false);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}

	@Bean
	public LoginSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler();
	}
}
