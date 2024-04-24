package com.example.zepto.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.example.zepto.service.customUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;
	
	@Autowired
	customUserDetailService customUserDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/shop/**", "/register").permitAll()  // Correct usage: requestMatchers
                //.requestMatchers("/store/**").hasRole("STORE")
                .requestMatchers("/store/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin
            		.loginPage("/login")
            		.permitAll()
            		.failureUrl("/login?error=true")
            		.defaultSuccessUrl("/")
            		.usernameParameter("email")
            		.passwordParameter("password")
            		)
            .userDetailsService(customUserDetailService)
            .oauth2Login(oauth2Login -> oauth2Login
            		.loginPage("/login")
            		.successHandler(googleOAuth2SuccessHandler)
            		)
            .logout(logout -> logout
            		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            		.logoutSuccessUrl("/login")
            		.invalidateHttpSession(true)
            		.deleteCookies("JSESSIONID")
            		)
            .csrf(csrf ->csrf.disable())
            .securityMatcher(request -> !request.getRequestURI().startsWith("/resources/")
                    && !request.getRequestURI().startsWith("/static/")
                    );
        return http.build();
                    // ... other patterns to exclude

//            .requestMatchers("/resources/**", "/static/**", "/images/**", "/productimages/**", "/css/**", "/js/**") ; // Apply ignoring here

//            http.headers().frameOptions().disable()
           
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    }
    
     
