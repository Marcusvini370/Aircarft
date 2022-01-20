package com.br.aircraft.api.domain.exception;

public class AeronaveNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	public AeronaveNotFoundException(String mensagem) {
		super(mensagem);
	}
	
}
