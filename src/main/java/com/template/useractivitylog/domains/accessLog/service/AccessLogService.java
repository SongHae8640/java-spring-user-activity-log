package com.template.useractivitylog.domains.accessLog.service;

import com.template.useractivitylog.domains.accessLog.domain.AccessLog;
import com.template.useractivitylog.domains.accessLog.event.AccessLogEvent;
import com.template.useractivitylog.domains.accessLog.repository.AccessLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccessLogService {
  private final AccessLogRepository repository;

  @EventListener
  @Transactional
  public void handleAccessLogEvent(AccessLogEvent event) {
    AccessLog accessLog = repository.save(event.toEntity());
    log.info("AccessLog saved: {}", accessLog);
  }
}