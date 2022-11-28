package com.wsmarket.wsmarketbackend.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.wsmarket.wsmarketbackend.domains.Pedido;
import com.wsmarket.wsmarketbackend.services.interfaces.EmailService;

public abstract class AbstractEmailService implements EmailService {
	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage message = hydrateSimpleMailMessageFromPedido(pedido);
		sendEmail(message);
	}

	protected SimpleMailMessage hydrateSimpleMailMessageFromPedido(Pedido pedido) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(pedido.getCliente().getEmail());
		message.setFrom(sender);
		message.setSubject("Pedido confirmado! Código: " + pedido.getId());
		message.setSentDate(new Date(System.currentTimeMillis()));
		message.setText(pedido.toString());

		return message;
	}
}
