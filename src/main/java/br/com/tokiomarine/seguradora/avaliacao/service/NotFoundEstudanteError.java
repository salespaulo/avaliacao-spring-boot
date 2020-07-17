package br.com.tokiomarine.seguradora.avaliacao.service;

public class NotFoundEstudanteError extends RuntimeException {
	
	private static final long serialVersionUID = -5005998597752046885L;

	public NotFoundEstudanteError(Long id) {
		super("Não encontrado estudante por id=" + id);
	}

}
