package com.template.useractivitylog.domains.accessLog.service;

import com.template.useractivitylog.domains.accessLog.domain.AccessLog;
import com.template.useractivitylog.domains.accessLog.repository.AccessLogRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccessLogService {
  private final AccessLogRepository repository;

  @Transactional
  public void saveAll(List<AccessLog> list) {
    repository.saveAll(list);
  }
}