package com.bootcamp.services.discount.auditor;

import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import com.bootcamp.services.discount.model.DiscountResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO LABB-1.1 add spring for RabbitMQ and Spring Cloud Stream starters
 * add binder configuration application.yaml
 * 
 * @author amit
 *
 */
@Component
public class AuditService {

	private final AuditStream auditStream;
	private static Logger logger = LoggerFactory.getLogger(AuditService.class);

	public AuditService(AuditStream auditStream) {
		this.auditStream = auditStream;
	}

	public void pubAuditEvent(final DiscountResponse response) {
		logger.info(">>pubAuditEvent Aync");
		Executors.newFixedThreadPool(1).execute(() -> {
			String responseJSON = "";
			try {
				// Thread.sleep(2000);
				ObjectMapper obj = new ObjectMapper();
				responseJSON = obj.writeValueAsString(response);
				MessageChannel messageChannel = auditStream.outboundAudit();
				messageChannel.send(MessageBuilder.withPayload(responseJSON)
						.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.TEXT_PLAIN_VALUE).build());

			} catch (Throwable e) {
				logger.error("\nCouldnt Emit Discount Stream. Error :" + e.getMessage());
				logger.info("\nLogging Discount Respnse as Fallback : " + responseJSON);
			}

		});

	}

}
