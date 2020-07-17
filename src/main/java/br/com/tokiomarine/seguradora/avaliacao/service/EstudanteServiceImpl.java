package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;

@Component
public class EstudanteServiceImpl implements EstudandeService {

	private final EstudanteRepository repository;
	
	@Autowired
	public EstudanteServiceImpl(final EstudanteRepository repository) {
		this.repository = repository;
	}

	@Override
	public void cadastrarEstudante(@Valid Estudante estudante) {
		repository.save(estudante);
	}

	@Override
	public void atualizarEstudante(@Valid Estudante estudante) {
		repository	
				.findById(estudante.getId())
				.map(byId -> {
					byId.setNome(estudante.getNome());
					byId.setEmail(estudante.getEmail());
					byId.setTelefone(estudante.getTelefone());
					return byId;
				})
				.map(repository::save);
	}

	@Override
	public List<Estudante> buscarEstudantes() {
		return repository.findAll();
	}

	@Override
	public Estudante buscarEstudante(long id) {
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundEstudanteError(id));
	}

}
