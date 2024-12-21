package com.template.useractivitylog.domains.accessLog.filter;

import com.template.useractivitylog.domains.accessLog.event.AccessLogEvent;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class AccessLogFilter extends OncePerRequestFilter {
  private final ApplicationEventPublisher eventPublisher;

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

    eventPublisher.publishEvent(
        new AccessLogEvent(
            request.getSession(true).getId(),
            request.getRemoteAddr(),
            request.getRequestURI(),
            request.getMethod(),
            LocalDateTime.now()
        )
    );

    // 다음 필터로 진행
    filterChain.doFilter(request, response);
  }
}