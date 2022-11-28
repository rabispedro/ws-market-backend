package com.wsmarket.wsmarketbackend.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {
	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender javaMailSender;

	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage message) {
		LOG.info("Inicio envio de email em texto plano");
		this.mailSender.send(message);
		LOG.info("Fim envio de email em texto plano");

		return;
	}

	@Override
	public void sendHtmlEmail(MimeMessage message) {
		LOG.info("Inicio envio de email em html");
		this.javaMailSender.send(message);
		LOG.info("Fim envio de email em html");

		return;
	}
}
