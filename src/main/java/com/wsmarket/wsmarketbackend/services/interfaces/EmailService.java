package com.wsmarket.wsmarketbackend.services.interfaces;

import org.springframework.mail.SimpleMailMessage;

import com.wsmarket.wsmarketbackend.domains.Pedido;

public interface EmailService {
	void sendOrderConfirmationEmail(Pedido pedido);
	void sendEmail(SimpleMailMessage message);
}