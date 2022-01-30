package com.wsmarket.wsmarketbackend.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
}
