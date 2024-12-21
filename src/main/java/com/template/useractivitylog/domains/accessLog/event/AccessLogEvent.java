package com.template.useractivitylog.domains.accessLog.event;

import java.time.LocalDateTime;

public record AccessLogEvent(
    String sessionId,
    String ip,
    String requestURI,
    String requestMethod,
    LocalDateTime timestamp
) {}