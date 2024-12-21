package com.template.useractivitylog.domains.accessLog.service;

import com.template.useractivitylog.domains.accessLog.event.AccessLogEvent;
import jakarta.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccessLogEventListener {
  private final AccessLogService service;
  private final List<AccessLogEvent> buffer = new ArrayList<>();
  private LocalDateTime firstLogTime = null;

  @EventListener
  public void handleAccessLogEvent(AccessLogEvent event) {
    addLog(event);
  }

  private synchronized void addLog(AccessLogEvent log) {
    if (buffer.isEmpty()) {
      firstLogTime = LocalDateTime.now();
    }

    buffer.add(log);

    if (shouldFlush()) {
      flush();
    }
  }

  private boolean shouldFlush() {
    if (buffer.size() >= 10) {
      return true;
    }

    if (firstLogTime != null) {
      return firstLogTime.plusMinutes(10).isBefore(LocalDateTime.now());
    }
    return false;
  }

  private void flush() {
    if(buffer.isEmpty()) return;

    service.saveAll(buffer.stream().map(AccessLogEvent::toEntity).toList());
    log.info("[AccessLogBatch] Flushed {} logs to DB", buffer.size());
    buffer.clear();
    firstLogTime = null;
  }

  @PreDestroy
  private synchronized void flushOnShutDown() {
    if (!buffer.isEmpty()) {
      log.info("Flushing remaining {} logs before shutdown...", buffer.size());
      flush();
    } else {
      log.info("No logs to flush before shutdown.");
    }
  }
}