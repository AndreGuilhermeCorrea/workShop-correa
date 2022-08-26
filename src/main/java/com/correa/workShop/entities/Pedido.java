package com.correa.workShop.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.correa.workShop.entities.enums.PedidoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity //anotacao da jpa que essa classe é uma tabela do db
@Table(name = "tb_pedido")
public class Pedido implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	//anotacao para formatar o json
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant momento;
	private Integer pedidoStatus;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id") //anotacao para intruir o jpa a transformar o id a uma chave estrangeira (cliente_id)(muitos para 01)
	private Usuario cliente; //associação pedido ao cliente

	@OneToMany(mappedBy = "id.pedido")
	private Set<PedidoItens> itens = new HashSet<>();
	
	//associação um para um, mapeamento relação da duas entidades pelo mesmo id 
	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Pagamento pagamento;
	
	//construtor padrão vazio
	public Pedido() {
	}

	
	//construtor com argumentos
	public Pedido(Long id, Instant momento, PedidoStatus pedidoStatus, Usuario cliente) {
		super();
		this.id = id;
		this.momento = momento;
		setPedidoStatus(pedidoStatus);
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMomento() {
		return momento;
	}

	public void setMomento(Instant momento) {
		this.momento = momento;
	}


	public PedidoStatus getPedidoStatus() {
		return PedidoStatus.valueOf(pedidoStatus);
	}

	public void setPedidoStatus(PedidoStatus pedidoStatus) {
			if (pedidoStatus != null) {
			this.pedidoStatus = pedidoStatus.getCodigo();
			}
	}
	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public Set<PedidoItens> getItens(){
		return itens;
	}
	
	public Double getTotal() {
		double soma = 0.0;
		for (PedidoItens x : itens) {
			soma += x.getSubTotal();
		}
		return soma;
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
	
	

}
