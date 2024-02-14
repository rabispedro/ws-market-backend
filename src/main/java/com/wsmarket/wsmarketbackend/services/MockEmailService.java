package com.wsmarket.wsmarketbackend.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

public class MockEmailService extends AbstractEmailService {
	public MockEmailService(
		TemplateEngine templateEngine,
		JavaMailSender javaMailSender
	) {
		super(templateEngine, javaMailSender);
	}

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage message) {
		LOG.info("Inicio simulação de envio de email em texto plano.");
		LOG.info(message.toString());
		LOG.info("Fim simulação de envio de email em texto plano.");
	}

	@Override
	public void sendHtmlEmail(MimeMessage message) {
		LOG.info("Inicio simulação de envio de email em html");
		LOG.info(message.toString());
		LOG.info("Fim simulação de envio de email em html");
	}
}
