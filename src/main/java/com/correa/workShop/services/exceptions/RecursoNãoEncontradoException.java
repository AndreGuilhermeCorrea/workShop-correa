package com.correa.workShop.services.exceptions;


//sublasse do runtime onde o compilador não obriga tratar
public class RecursoNãoEncontradoException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	//tratamento do erro 500 para 404 nao encontrado no http

	//exceção personalizada na camada de serviço
	//construtor recebe um object id e não foi encontrado
	public RecursoNãoEncontradoException(Object id) {
		//método chamando construtor da superclasse concatenado com o id
		super("Recurso não encontrado. Id " + id);
	}
}
