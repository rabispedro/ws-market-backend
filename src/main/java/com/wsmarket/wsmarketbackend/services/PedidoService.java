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
import com.wsmarket.wsmarketbackend.services.interfaces.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EmailService emailService;

	public Pedido findById(Long id) {
		Optional<Pedido> pedido = this.pedidoRepository.findById(id);

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
		pedido.setCliente(this.clienteService.findById(pedido.getCliente().getId()));
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);

		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamento = (PagamentoComBoleto) pedido.getPagamento();
			BoletoService.hydratePagamento(pagamento, pedido.getInstante());
		}

		pedido = this.pedidoRepository.save(pedido);
		this.pagamentoRepository.save(pedido.getPagamento());

		for(ItemPedido item : pedido.getItens()) {
			item.setDesconto(0.0);
			item.setProduto(this.produtoService.findById(item.getProduto().getId()));
			item.setPreco(item.getProduto().getPreco());
			item.setPedido(pedido);
		}
		this.itemPedidoRepository.saveAll(pedido.getItens());

		this.emailService.sendOrderConfirmationEmail(pedido);
		this.emailService.sendOrderConfirmationHtmlEmail(pedido);
		return pedido;
	}
}
