package com.bmartins.modelagem.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemPedidoPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido ped;
	
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto prod;
	
	
	public Pedido getPed() {
		return ped;
	}
	public void setPed(Pedido ped) {
		this.ped = ped;
	}
	public Produto getProd() {
		return prod;
	}
	public void setProd(Produto prod) {
		this.prod = prod;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ped == null) ? 0 : ped.hashCode());
		result = prime * result + ((prod == null) ? 0 : prod.hashCode());
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
		ItemPedidoPK other = (ItemPedidoPK) obj;
		if (ped == null) {
			if (other.ped != null)
				return false;
		} else if (!ped.equals(other.ped))
			return false;
		if (prod == null) {
			if (other.prod != null)
				return false;
		} else if (!prod.equals(other.prod))
			return false;
		return true;
	}
	
	
}
