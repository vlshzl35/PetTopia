package com.sh.pettopia.Hojji.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collection;

@Slf4j
// 관리자일 경우, 관리자 페이지로 이동 처리를 하기 위한 핸들러입니다. - 홍지민
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final RequestCache requestCache = new HttpSessionRequestCache();


    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        log.debug("login 후, 권한 확인을 합니다.");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        log.debug("authorities = {}", authorities);

        // 기본 리다이렉트 URL은 사용자가 머물렀던 페이지로 설정합니다.
        String redirectUrl = determineTargetUrl(request, response, authentication);

        // Admin이 있는 경우만 확인하면 되기 대문에 ADMIN만 확인합니다.
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                redirectUrl = "/admin/dashboard"; // 관리자 페이지 URL 설정합니다.
                break; // 추가 권한 확인 필요 없으므로 반복 중단합니다.
            }
        }
        log.debug("redirectUrl = {}", redirectUrl);
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            if (StringUtils.hasText(targetUrl)) {
                return targetUrl;  // 원래 요청했던 URL로 리다이렉트
            }
        }

        return super.determineTargetUrl(request, response);
    }
}
