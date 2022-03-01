package com.sparta.juteukki02.juteukki_week02.security;
import com.sparta.juteukki02.juteukki_week02.jwt.JwtAuthenticationFilter;
import com.sparta.juteukki02.juteukki_week02.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // rest api 만을 고려하여 기본 설정은 해제하겠습니다.
                .csrf().disable() // csrf 보안 토큰 disable처리.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 역시 사용하지 않습니다.
                .and()
                .authorizeRequests() // 요청에 대한 사용권한 체크
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/showpost").permitAll()
                .antMatchers("/api/test").permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);
        // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//// 회원 관리 처리 API (POST /user/**) 에 대해 CSRF 무시
//        http.csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests()
//// image 폴더를 login 없이 허용
//                .antMatchers("/images/**").permitAll()
//// css 폴더를 login 없이 허용
//                .antMatchers("/css/**").permitAll()
//// 회원 관리 처리 API 전부를 login 없이 허용
//                .antMatchers("/user/**").permitAll()
//// 그 외 어떤 요청이든 '인증'
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
//                        UsernamePasswordAuthenticationFilter.class)
//        // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
//// [로그인 기능]
//                .formLogin()
//// 로그인 View 제공 (GET /user/login)
//                .loginPage("/user/login")
//// 로그인 처리 (POST /user/login)
//                .loginProcessingUrl("/user/login")
//// 로그인 처리 후 성공 시 URL
//                .defaultSuccessUrl("/")
//// 로그인 처리 후 실패 시 URL
//                .failureUrl("/user/login?error")
//                .permitAll()
//                .and()
//// [로그아웃 기능]
//                .logout()
//// 로그아웃 처리 URL
//                .logoutUrl("/user/logout")
//                .permitAll();
//    }
}