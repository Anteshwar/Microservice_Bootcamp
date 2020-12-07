package com.bootcamp.services.discount.auditor;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AuditStream {

	final String OUTPUT = "webaudit-out";

	@Output(OUTPUT)
	MessageChannel outboundAudit();

}
