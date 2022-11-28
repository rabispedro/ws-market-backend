package com.wsmarket.wsmarketbackend.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage message) {
		LOG.info("Inicio simulação de envio de email em texto plano.");
		LOG.info(message.toString());
		LOG.info("Fim simulação de envio de email em texto plano.");

		return;
	}

	@Override
	public void sendHtmlEmail(MimeMessage message) {
		LOG.info("Inicio simulação de envio de email em html");
		LOG.info(message.toString());
		LOG.info("Fim simulação de envio de email em html");

		return;
	}
}
