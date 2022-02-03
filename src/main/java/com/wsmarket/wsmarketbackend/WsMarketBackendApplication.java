package com.wsmarket.wsmarketbackend;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.wsmarket.wsmarketbackend.domain.Categoria;
import com.wsmarket.wsmarketbackend.domain.Cidade;
import com.wsmarket.wsmarketbackend.domain.Cliente;
import com.wsmarket.wsmarketbackend.domain.Endereco;
import com.wsmarket.wsmarketbackend.domain.Pedido;
import com.wsmarket.wsmarketbackend.domain.Estado;
import com.wsmarket.wsmarketbackend.domain.Pagamento;
import com.wsmarket.wsmarketbackend.domain.PagamentoComBoleto;
import com.wsmarket.wsmarketbackend.domain.PagamentoComCartao;
import com.wsmarket.wsmarketbackend.domain.Produto;
import com.wsmarket.wsmarketbackend.domain.enums.EstadoPagamento;
import com.wsmarket.wsmarketbackend.domain.enums.TipoCliente;
import com.wsmarket.wsmarketbackend.repositories.CategoriaRepository;
import com.wsmarket.wsmarketbackend.repositories.CidadeRepository;
import com.wsmarket.wsmarketbackend.repositories.ClienteRepository;
import com.wsmarket.wsmarketbackend.repositories.EnderecoRepository;
import com.wsmarket.wsmarketbackend.repositories.EstadoRepository;
import com.wsmarket.wsmarketbackend.repositories.PagamentoRepository;
import com.wsmarket.wsmarketbackend.repositories.PedidoRepository;
import com.wsmarket.wsmarketbackend.repositories.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WsMarketBackendApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(WsMarketBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		this.produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		this.estadoRepository.saveAll(Arrays.asList(est1, est2));
		this.cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(
			null,
			"Maria Silva",
			"maria@gmail.com",
			"36378912377",
			TipoCliente.PESSOA_FISICA
		);

		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

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

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		this.clienteRepository.saveAll(Arrays.asList(cli1));
		this.enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
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

		this.pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		this.pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

		return;
	}
}
