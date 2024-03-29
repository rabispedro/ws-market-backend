package com.wsmarket.wsmarketbackend.domains;

import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "tb_item_pedido")
public class ItemPedido extends BaseDomain {
	@JsonIgnore
	@EmbeddedId
	@Column(name = "id")
	private ItemPedidoPK id = new ItemPedidoPK();
	
	@Column(name = "desconto")
	private Double desconto;

	@Column(name = "quantidade")
	private Integer quantidade;

	@Column(name = "preco")
	private Double preco;

	public ItemPedido() {}

	public ItemPedido(
		Pedido pedido,
		Produto produto,
		Double desconto,
		Integer quantidade,
		Double preco
	) {
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public Double getSubTotal() {
		return ((this.preco - this.desconto) * this.quantidade);
	}

	@JsonIgnore
	public Pedido getPedido() {
		return this.id.getPedido();
	}

	public void setPedido(Pedido pedido) {
		this.id.setPedido(pedido);
	}
	
	public Produto getProduto() {
		return this.id.getProduto();
	}

	public void setProduto(Produto produto) {
		this.id.setProduto(produto);
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
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
		ItemPedido other = (ItemPedido) obj;
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

		StringBuilder builder = new StringBuilder()
			.append(this.getProduto().getNome())
			.append(", Quantidade: ")
			.append(this.getQuantidade())
			.append(", Preço unitário: ")
			.append(currencyFormatter.format(this.getPreco()))
			.append(", Subtotal: ")
			.append(currencyFormatter.format(this.getSubTotal()))
			.append("\n");

		return builder.toString();
	}
}
