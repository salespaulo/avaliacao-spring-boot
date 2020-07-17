package br.com.tokiomarine.seguradora.avaliacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Long> {

	Optional<Estudante> findById(Long id);

	void deleteById(Long id);

}
