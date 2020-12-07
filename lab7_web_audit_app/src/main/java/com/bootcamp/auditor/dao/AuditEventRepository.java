package com.bootcamp.auditor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.auditor.model.AuditEvent;

public interface AuditEventRepository extends JpaRepository<AuditEvent, String>{

}
