package com.template.useractivitylog.domains.accessLog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_access_log")
@NoArgsConstructor
@ToString
public class AccessLog {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;
  private String sessionId;
  private String ip;
  private String uri;
  private String method;
  private LocalDateTime createdAt;

  @Builder
  public AccessLog(String sessionId, String ip, String uri, String method, LocalDateTime createdAt) {
    this.sessionId = sessionId;
    this.ip = ip;
    this.uri = uri;
    this.method = method;
    this.createdAt = createdAt;
  }
}