package com.sh.pettopia.Hojji.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * 정적 파일에 대해서는 인증/인가 검사를 수행하지 않는다.
     * @return
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("css/**", "js/**",  "scss/**", "images/**", "fonts/**", "json/**", "ocr/**", "assets/**"); // 이 경로로 시작하는 것들은 보안 검사를 하지 말라는 의미
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /**
         * - permitAll() : 모두 허용
         * - authenticated() : 인증된 사용자만 허용
         * - anonymous() : 인증하지 않은 사용자만 허용
         * - hasRole(), hasAnyRole : 특정 권한이 있는 사용자만 허용
         */
        http
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests((registry) -> {
            // 특수한 경우부터 보편적인 경우 순으로 작성했습니다.
            registry
                    // 누구나 허용
                    .requestMatchers("/", "/index.html","/ocrUpload",
                            "/petsitter/petsittingmain", "/petsitter/list", "/petsitterfinder/careregistrationlist",
                            "/enterprise/location",
                            "/community/postList").permitAll()

                    // 로그인 안 한 사용자에게 허용되는 페이지
                    .requestMatchers("/member/**", "/auth/login").anonymous()

                    // 인증된 사용자만 허용 - 로그인 한 사용자를 의미함
                    .requestMatchers("/petsitter/detail/","/petsitter/successpay/", "/enterprise/**", "/mypage/**", "/petsitter/**", "/petsitterfinder/**", "/community/postDetail", "/community/registPost").authenticated()

                    // ROLE_SITTER 권한이 있는 사용자만 허용
                    .requestMatchers("/petsitter/registerpost", "/petsitter/registerprofile", "/petsitter/startjob").hasRole("SITTER")

                    // ROLE_ADMIN 권한이 있는 사용자만 허용
                    .requestMatchers("/admin/**").hasRole("ADMIN")

                    // 나머지들은 이렇게 해주세요~
                    .anyRequest().authenticated();
        }); // 람다로 작성하게 되어있음

        /**
         * 폼 로그인 설정
         * -
         */
        http.formLogin(configurer -> {
            configurer
                    .loginPage("/auth/login") // GET 방식의 로그인 폼 요청
                    .loginProcessingUrl("/auth/login") // POST방식으로 로그인 처리
                    .successHandler(customAuthenticationSuccessHandler()) // 사용자 정의 인증 성공 핸들러 설정
//                    .defaultSuccessUrl("/", true) // 로그인 성공 후 이동 페이지
                    .usernameParameter("username") // login.html의 input태그의 name 속성을 입력합니다.
                    .passwordParameter("password") // login.html의 input태그의 name 속성을 입력합니다.
                    .permitAll(); //로그인 페이지는 인증 없이 접근 가능
        });

        /**
         * 로그아웃 설정 - POST 요청만 가능하다.
         */
        http.logout(configurer -> {
            configurer.logoutUrl("/auth/logout") // POST 요청으로 보내야 함
                    .logoutSuccessUrl("/");// 로그아웃 후 리다리엑트 url(기본값은 로그인 페이지)
        });
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 암호화 처리
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
}
