package com.br.aircraft.api.domain.exception;

public class AeronaveDadoInvalidoException extends NegocioException{
	private static final long serialVersionUID = 1L;
	
	public AeronaveDadoInvalidoException(String mensagem) {
		super(mensagem);
	}		

	
}
