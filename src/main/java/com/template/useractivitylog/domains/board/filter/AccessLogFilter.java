package com.template.useractivitylog.domains.board.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class AccessLogFilter extends OncePerRequestFilter {

  private static final Logger log = LoggerFactory.getLogger(AccessLogFilter.class);

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