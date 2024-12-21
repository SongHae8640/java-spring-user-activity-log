package com.template.useractivitylog.domains.accessLog.repository;

import com.template.useractivitylog.domains.accessLog.domain.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
}