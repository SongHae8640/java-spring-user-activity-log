package com.template.useractivitylog.domains.accessLog.service;

import com.template.useractivitylog.domains.accessLog.event.AccessLogEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccessLogService {

  @EventListener
  public void handleAccessLogEvent(AccessLogEvent event) {
    log.info("[ACCESS_LOG] sessionId={}, ip={}, requestURI={}, requestMethod={}, now={}",
        event.sessionId(),
        event.ip(),
        event.requestURI(),
        event.requestMethod(),
        event.timestamp()
    );
  }

}
