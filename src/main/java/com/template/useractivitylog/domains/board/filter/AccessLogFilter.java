package com.template.useractivitylog.domains.board.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class AccessLogFilter extends OncePerRequestFilter {

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    return !request.getServletPath().startsWith("/api/v1");
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {
    String sessionId = request.getSession(true).getId();


    // 클라이언트 IP 주소
    String ip = request.getRemoteAddr();

    // 요청 URI
    String requestURI = request.getRequestURI();
    String requestMethod = request.getMethod();


    // 현재 시간
    LocalDateTime now = LocalDateTime.now();

    // 로깅
    log.info("[ACCESS_LOG] sessionId={}, ip={}, requestURI={}, requestMethod={}, now={}",
        sessionId, ip, requestURI, requestMethod, now);

    // 다음 필터로 진행
    filterChain.doFilter(request, response);
  }
}