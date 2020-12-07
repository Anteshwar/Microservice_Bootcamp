package com.bootcamp.auditor.listener;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bootcamp.auditor.dao.AuditEventRepository;
import com.bootcamp.auditor.model.AuditEvent;

@Component
public class AuditEventListener {
	@Autowired
	AuditEventRepository auditEventRepository;
	private static Logger log = LoggerFactory.getLogger(AuditEventListener.class);

	@StreamListener(AuditStream.INPUT)
	public void handleAudit(@Payload String auditPayload) {

		log.info("Received =" + auditPayload);
		AuditEvent event = new AuditEvent(UUID.randomUUID().toString(), auditPayload, new Date());
		auditEventRepository.save(event);
	}
}
