package com.wsmarket.wsmarketbackend.domains;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "tb_pedido")
public class Pedido extends BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Column(name = "instante")
	private Date instante;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "id_endereco_entrega")
	private Endereco enderecoEntrega;

	@OneToMany(mappedBy = "id.pedido")
	@Column(name = "itens")
	private Set<ItemPedido> itens = new HashSet<>();

	public Pedido() {}

	public Pedido(
		Long id,
		Date instante,
		Cliente cliente,
		Endereco enderecoEntrega
	) {
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoEntrega = enderecoEntrega;
	}

	public Double getTotal() {
		Double total = 0.0;
		for(ItemPedido item : itens) {
			total += item.getSubTotal();
		}

		return total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat currencyFormatter = NumberFormat
			.getCurrencyInstance(new Locale("pt", "BR"));

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

		StringBuilder builder = new StringBuilder()
			.append("Pedido número: ")
			.append(this.getId())
			.append(", Instante: ")
			.append(dateFormatter.format(this.getInstante()))
			.append(", Cliente: ")
			.append(this.getCliente().getNome())
			.append(", Status do pagamento: ")
			.append(this.getPagamento().getEstado().getDescricao())
			.append("\nDetalhes:\n");
		
		for(ItemPedido item : this.getItens()){
			builder.append(item.toString());
		}
		
		builder
			.append("Valor total: ")
			.append(currencyFormatter.format(this.getTotal()))
			.append("\n");

		return builder.toString();
	}

	
}
