package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 로그인 실패시 처리 / 빈으로 구현
    @Bean
    UserAuthenticationFailureHandler getFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

        // 로그인 없이 접근 가능하도록 설정
        http.authorizeRequests()
                .antMatchers(
                        "/"
                        , "/member/register"
                        , "/member/email-auth"
                        , "/member/find-password"
                )
                .permitAll();
    
        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasAuthority("ROLE_ADMIN");

        // 로그인 처리
        http.formLogin()
                .loginPage("/member/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(getFailureHandler())
                .permitAll();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
        
        http.exceptionHandling()
                .accessDeniedPage("/error/denied");

        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                .passwordEncoder(getPasswordEncoder());

        super.configure(auth);
    }

}
