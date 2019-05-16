package com.bmartins.modelagem.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Pendente"),
	CANCELADO(3, "Pendente");
	
	private int id;
	private String descricao;
	
	private EstadoPagamento(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		
		for (EstadoPagamento item : EstadoPagamento.values()) {
			if (id.equals(item.getId())) {
				return item;
			}
		}
		
		throw new IllegalArgumentException("Tipo de cliente invalido:" + id);
	}
}
