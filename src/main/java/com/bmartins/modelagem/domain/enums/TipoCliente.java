package com.bmartins.modelagem.domain.enums;

import java.util.Iterator;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int id;
	private String descricao;
	
	private TipoCliente(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		
		for (TipoCliente item : TipoCliente.values()) {
			if (id.equals(item.getId())) {
				return item;
			}
		}
		
		throw new IllegalArgumentException("Tipo de cliente invalido:" + id);
	}
}
