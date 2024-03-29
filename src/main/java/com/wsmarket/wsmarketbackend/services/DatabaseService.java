package com.wsmarket.wsmarketbackend.services;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wsmarket.wsmarketbackend.domains.Categoria;
import com.wsmarket.wsmarketbackend.domains.Cidade;
import com.wsmarket.wsmarketbackend.domains.Cliente;
import com.wsmarket.wsmarketbackend.domains.Endereco;
import com.wsmarket.wsmarketbackend.domains.Estado;
import com.wsmarket.wsmarketbackend.domains.ItemPedido;
import com.wsmarket.wsmarketbackend.domains.Pagamento;
import com.wsmarket.wsmarketbackend.domains.PagamentoComBoleto;
import com.wsmarket.wsmarketbackend.domains.PagamentoComCartao;
import com.wsmarket.wsmarketbackend.domains.Pedido;
import com.wsmarket.wsmarketbackend.domains.Produto;
import com.wsmarket.wsmarketbackend.domains.enums.EstadoPagamento;
import com.wsmarket.wsmarketbackend.domains.enums.PerfilCliente;
import com.wsmarket.wsmarketbackend.domains.enums.TipoCliente;
import com.wsmarket.wsmarketbackend.repositories.CategoriaRepository;
import com.wsmarket.wsmarketbackend.repositories.CidadeRepository;
import com.wsmarket.wsmarketbackend.repositories.ClienteRepository;
import com.wsmarket.wsmarketbackend.repositories.EnderecoRepository;
import com.wsmarket.wsmarketbackend.repositories.EstadoRepository;
import com.wsmarket.wsmarketbackend.repositories.ItemPedidoRepository;
import com.wsmarket.wsmarketbackend.repositories.PagamentoRepository;
import com.wsmarket.wsmarketbackend.repositories.PedidoRepository;
import com.wsmarket.wsmarketbackend.repositories.ProdutoRepository;
import com.wsmarket.wsmarketbackend.services.interfaces.IDatabaseService;

public class DatabaseService extends BaseService implements IDatabaseService {
	private final CategoriaRepository _categoriaRepository;
	private final ProdutoRepository _produtoRepository;
	private final EstadoRepository _estadoRepository;
	private final CidadeRepository _cidadeRepository;
	private final ClienteRepository _clienteRepository;
	private final EnderecoRepository _enderecoRepository;
	private final PedidoRepository _pedidoRepository;
	private final PagamentoRepository _pagamentoRepository;
	private final ItemPedidoRepository _itemPedidoRepository;
	private final BCryptPasswordEncoder _bCryptPasswordEncoder;
	
	public DatabaseService(
		@Autowired CategoriaRepository categoriaRepository,
		@Autowired ProdutoRepository produtoRepository,
		@Autowired EstadoRepository estadoRepository,
		@Autowired CidadeRepository cidadeRepository,
		@Autowired ClienteRepository clienteRepository,
		@Autowired EnderecoRepository enderecoRepository,
		@Autowired PedidoRepository pedidoRepository,
		@Autowired PagamentoRepository pagamentoRepository,
		@Autowired ItemPedidoRepository itemPedidoRepository,
		@Autowired BCryptPasswordEncoder bCryptPasswordEncoder
	) {
		_categoriaRepository = categoriaRepository;
		_produtoRepository = produtoRepository;
		_estadoRepository = estadoRepository;
		_cidadeRepository = cidadeRepository;
		_clienteRepository = clienteRepository;
		_enderecoRepository = enderecoRepository;
		_pedidoRepository = pedidoRepository;
		_pagamentoRepository = pagamentoRepository;
		_itemPedidoRepository = itemPedidoRepository;
		_bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public void instantiateTestDatabase() throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Almoxarifado");
		Categoria cat4 = new Categoria(null, "Monitores");
		Categoria cat5 = new Categoria(null, "Backoffice");
		Categoria cat6 = new Categoria(null, "Eletrônicos");
		Categoria cat7 = new Categoria(null, "Escola");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de Escritorio", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV True Color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		_categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));

		_produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		_estadoRepository.saveAll(Arrays.asList(est1, est2));
		_cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(
			null,
			"Pedro Diniz",
			"pedrodiniz@arpiatecnologia.com",
			"61989698000",
			TipoCliente.PESSOA_FISICA,
			_bCryptPasswordEncoder.encode("Arroba123")
		);
		cli1.addPerfil(PerfilCliente.ADMIN);

		Cliente cli2 = new Cliente(
			null,
			"Maria Silva",
			"maria@gmail.com",
			"36378912377",
			TipoCliente.PESSOA_FISICA,
			_bCryptPasswordEncoder.encode("Arroba123")
		);

		cli1.getTelefones().addAll(Arrays.asList("38933922827"));
		cli2.getTelefones().addAll(Arrays.asList("31927363323", "85993838393"));

		Endereco e1 = new Endereco(
			null,
			"Rua Flores",
			"300",
			"Apto 303",
			"Jardim",
			"38220834",
			cli1,
			c1
		);

		Endereco e2 = new Endereco(
			null,
			"Avenida Matos",
			"105",
			"Sala 800",
			"Centro",
			"38777012",
			cli1,
			c2
		);

		Endereco e3 = new Endereco(
			null,
			"Rua Continental",
			"22",
			"Casa Fundo",
			"Areias",
			"64027686",
			cli2,
			c3
		);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));

		_clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		_enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));

		SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm"
		);
		
		Pedido ped1 = new Pedido(
			null,
			dateFormat.parse("30/09/2017 10:32"),
			cli1,
			e1
		);

		Pedido ped2 = new Pedido(
			null,
			dateFormat.parse("10/10/2020 19:35"),
			cli1,
			e2
		);

		Pagamento pag1 = new PagamentoComCartao(
			null,
			EstadoPagamento.QUITADO,
			ped1,
			6
		);

		ped1.setPagamento(pag1);

		Pagamento pag2 = new PagamentoComBoleto(
			null,
			EstadoPagamento.PENDENTE,
			ped2,
			dateFormat.parse("20/10/2020 00:00"),
			null
		);

		ped2.setPagamento(pag2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		_pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		_pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

		ItemPedido ip1 = new ItemPedido(
			ped1,
			p1,
			0.00,
			1,
			2000.00
		);

		ItemPedido ip2 = new ItemPedido(
			ped1,
			p3,
			0.00,
			2,
			80.00
		);

		ItemPedido ip3 = new ItemPedido(
			ped2,
			p2,
			100.00,
			1,
			800.00
		);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		_itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
