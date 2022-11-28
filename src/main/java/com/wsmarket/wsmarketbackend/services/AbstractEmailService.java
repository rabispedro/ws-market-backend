package com.wsmarket.wsmarketbackend.services;

import java.util.Date;

import javax.el.ELException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.wsmarket.wsmarketbackend.domains.Pedido;
import com.wsmarket.wsmarketbackend.services.interfaces.EmailService;

public abstract class AbstractEmailService implements EmailService {
	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage message = this.hydrateSimpleMailMessageFromPedido(pedido);
		sendEmail(message);
	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
		try {
			MimeMessage message = this.hydrateMimeMessageFromPedido(pedido);
			sendHtmlEmail(message);
		} catch(MessagingException exception) {
			this.sendOrderConfirmationEmail(pedido);
		}
	}

	protected SimpleMailMessage hydrateSimpleMailMessageFromPedido(Pedido pedido) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(pedido.getCliente().getEmail());
		message.setFrom(this.sender);
		message.setSubject("Pedido confirmado! Código: " + pedido.getId());
		message.setSentDate(new Date(System.currentTimeMillis()));
		message.setText(pedido.toString());

		return message;
	}

	protected MimeMessage hydrateMimeMessageFromPedido(Pedido pedido) throws MessagingException {
		MimeMessage message = this.javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

		messageHelper.setTo(pedido.getCliente().getEmail());
		messageHelper.setFrom(this.sender);
		messageHelper.setSubject("Pedido Confirmado! Código: " + pedido.getId());
		messageHelper.setSentDate(new Date(System.currentTimeMillis()));
		messageHelper.setText(this.htmlFromTemplatePedido(pedido), true);

		return message;
	}

	protected String htmlFromTemplatePedido(Pedido pedido) {
		Context context = new Context();
		context.setVariable("pedido", pedido);

		return this.templateEngine.process("email/confirmacaoPedido", context);
	}
}
