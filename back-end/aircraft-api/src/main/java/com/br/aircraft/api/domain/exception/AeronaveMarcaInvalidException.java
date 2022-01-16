package com.br.aircraft.api.domain.exception;

public class AeronaveMarcaInvalidException extends NegocioException{
	private static final long serialVersionUID = 1L;
	
	public AeronaveMarcaInvalidException(String mensagem) {
		super(mensagem);
	}		

	
}
