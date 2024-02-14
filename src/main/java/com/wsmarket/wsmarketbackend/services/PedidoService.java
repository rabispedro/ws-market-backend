package com.wsmarket.wsmarketbackend.services;

import java.util.Date;
import java.util.Optional;

import com.wsmarket.wsmarketbackend.domains.ItemPedido;
import com.wsmarket.wsmarketbackend.domains.PagamentoComBoleto;
import com.wsmarket.wsmarketbackend.domains.Pedido;
import com.wsmarket.wsmarketbackend.domains.enums.EstadoPagamento;
import com.wsmarket.wsmarketbackend.repositories.ItemPedidoRepository;
import com.wsmarket.wsmarketbackend.repositories.PagamentoRepository;
import com.wsmarket.wsmarketbackend.repositories.PedidoRepository;
import com.wsmarket.wsmarketbackend.services.exceptions.ObjectNotFoundException;
import com.wsmarket.wsmarketbackend.services.interfaces.IBoletoService;
import com.wsmarket.wsmarketbackend.services.interfaces.IClienteService;
import com.wsmarket.wsmarketbackend.services.interfaces.IEmailService;
import com.wsmarket.wsmarketbackend.services.interfaces.IPedidoService;
import com.wsmarket.wsmarketbackend.services.interfaces.IProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class PedidoService extends BaseService implements IPedidoService {
	private final PedidoRepository _pedidoRepository;
	private final PagamentoRepository _pagamentoRepository;
	private final ItemPedidoRepository _itemPedidoRepository;
	private final IProdutoService _produtoService;
	private final IClienteService _clienteService;
	private final IEmailService _emailService;
	private final IBoletoService _boletoService;

	public PedidoService(
		@Autowired PedidoRepository pedidoRepository,
		@Autowired PagamentoRepository pagamentoRepository,
		@Autowired ItemPedidoRepository itemPedidoRepository,
		@Autowired IProdutoService produtoService,
		@Autowired IClienteService clienteService,
		@Autowired IEmailService emailService,
		@Autowired IBoletoService boletoService
	) {
		_pedidoRepository = pedidoRepository;
		_pagamentoRepository = pagamentoRepository;
		_itemPedidoRepository = itemPedidoRepository;
		_produtoService = produtoService;
		_clienteService = clienteService;
		_emailService = emailService;
		_boletoService = boletoService;
	}

	public Pedido findById(Long id) {
		Optional<Pedido> pedido = _pedidoRepository.findById(id);

		return pedido.orElseThrow(() -> (
			new ObjectNotFoundException(
				"Objeto não encontrado! " +
				"Id: " + id + ", " +
				"Tipo: " + Pedido.class.getName() + "."
			)
		));
	}

	@Transactional
	public Pedido create(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.setCliente(_clienteService.findById(pedido.getCliente().getId()));
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);

		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamento = (PagamentoComBoleto) pedido.getPagamento();
			_boletoService.hydratePagamento(pagamento, pedido.getInstante());
		}

		pedido = _pedidoRepository.save(pedido);
		_pagamentoRepository.save(pedido.getPagamento());

		for(ItemPedido item : pedido.getItens()) {
			item.setDesconto(0.0);
			item.setProduto(_produtoService.findById(item.getProduto().getId()));
			item.setPreco(item.getProduto().getPreco());
			item.setPedido(pedido);
		}
		_itemPedidoRepository.saveAll(pedido.getItens());

		_emailService.sendOrderConfirmationEmail(pedido);
		_emailService.sendOrderConfirmationHtmlEmail(pedido);
		return pedido;
	}
}
