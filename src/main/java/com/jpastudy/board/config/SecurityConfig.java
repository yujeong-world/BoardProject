package com.jpastudy.board.config;

import com.jpastudy.board.Service.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserAccountService userAccountService;

    //비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //인증 무시 ->static 디렉토리 파일들은 항상 인증 무시 (통과)
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    //Request가 들어오는 경우 권한 설정 & 로그인 & 로그아웃 처리
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers(
                        "/useraccount/myinfo",
                        "/form"
                ).hasRole("USER")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin() /*//login 경로로 접근하면 시큐리티에서 제공하는 로그인 폼사용 가능*/
                .defaultSuccessUrl("/board")
                .permitAll()
                .and()
                .logout() /* /logout에 접근하면 HTTP 세션 제거*/
                .and()
                .exceptionHandling().accessDeniedPage("/board");
                //예외가 발생했을 때 goekd vpdlwlfh dlehdgkehfhr audtl
    }

    //모든 인증을 처리하기 위한 AuthenticationManagerBuilder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAccountService).passwordEncoder(passwordEncoder());

    }




}
