package com.wsmarket.wsmarketbackend.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

public class SmtpEmailService extends AbstractEmailService {
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	private final MailSender _mailSender;
	private final JavaMailSender _javaMailSender;

	public SmtpEmailService(
		@Autowired MailSender mailSender,
		@Autowired JavaMailSender javaMailSender) {
		super(new TemplateEngine(), javaMailSender);
		
		_mailSender = mailSender;
		_javaMailSender = javaMailSender;
	}

	@Override
	public void sendEmail(SimpleMailMessage message) {
		LOG.info("Inicio envio de email em texto plano");
		_mailSender.send(message);
		LOG.info("Fim envio de email em texto plano");
	}

	@Override
	public void sendHtmlEmail(MimeMessage message) {
		LOG.info("Inicio envio de email em html");
		_javaMailSender.send(message);
		LOG.info("Fim envio de email em html");
	}
}
