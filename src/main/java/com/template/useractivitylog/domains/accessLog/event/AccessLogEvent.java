package com.template.useractivitylog.domains.accessLog.event;

import com.template.useractivitylog.domains.accessLog.domain.AccessLog;
import java.time.LocalDateTime;

public record AccessLogEvent(
    String sessionId,
    String ip,
    String uri,
    String method,
    LocalDateTime createdAt
) {

  public AccessLog toEntity() {
    return AccessLog.builder()
        .sessionId(sessionId())
        .ip(ip())
        .uri(uri())
        .method(method())
        .createdAt(createdAt())
        .build();
  }
}