package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;

@Service
@Transactional
public class EstudanteServiceImpl implements EstudanteService {

	private final EstudanteRepository repository;
	
	@Autowired
	public EstudanteServiceImpl(final EstudanteRepository repository) {
		this.repository = repository;
	}

	@Override
	public void cadastrarEstudante(@Valid Estudante estudante) {
		repository.saveAndFlush(estudante);
	}

	@Override
	public void atualizarEstudante(@Valid Estudante estudante) {
		repository	
				.findById(estudante.getId())
				.map(byId -> {
					byId.setNome(estudante.getNome());
					byId.setEmail(estudante.getEmail());
					byId.setTelefone(estudante.getTelefone());
					byId.setMatricula(estudante.getMatricula());
					byId.setCurso(estudante.getCurso());
					return byId;
				})
				.map(repository::saveAndFlush);
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

	@Override
	public void excluirEstudante(long id) {
		repository.deleteById(id);
	}

}
