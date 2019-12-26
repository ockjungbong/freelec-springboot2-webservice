package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import com.jojoldu.book.springboot.config.auth.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()  // h2-console 화면을 사용하기 위해
                .and().authorizeRequests()      // URL별 권한 관리 설정 옵션 시작점
                .antMatchers("/", "/css/***", "/images/***", "/js/***", "/h2-console/***")
                .permitAll().antMatchers("/api/v1/***").hasRole(Role.USER.name()).anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/")   // 로그아웃 기능에 대한 여러 설정의 진입점
                .and().oauth2Login()    // OAuth Login 설정 진입점
                .userInfoEndpoint().userService(customOAuth2UserService);
    }
}
