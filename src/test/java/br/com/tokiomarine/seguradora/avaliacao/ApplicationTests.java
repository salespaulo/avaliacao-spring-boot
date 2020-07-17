package br.com.tokiomarine.seguradora.avaliacao;

import static br.com.tokiomarine.seguradora.avaliacao.Utils.EMAIL;
import static br.com.tokiomarine.seguradora.avaliacao.Utils.MSG_EMAIL_INVALID;
import static br.com.tokiomarine.seguradora.avaliacao.Utils.MSG_EMAIL_NULL;
import static br.com.tokiomarine.seguradora.avaliacao.Utils.MSG_NOME_NULL;
import static br.com.tokiomarine.seguradora.avaliacao.Utils.NOME;
import static br.com.tokiomarine.seguradora.avaliacao.Utils.TELEFONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudanteService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private EstudanteService service;
	private Validator validator;
	private Estudante estudanteTest;

	@Before
	public void beforeAll() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

		estudanteTest = new Estudante();
		estudanteTest.setNome(NOME);
		estudanteTest.setEmail(EMAIL);
		estudanteTest.setTelefone(TELEFONE);

		service.cadastrarEstudante(estudanteTest);
	}

	@Test
	public void contextLoads() {
		// Context loaded - OK!
	}
	
	@Test
	public void estudanteSemNomeTest() {
		final Estudante estudante = new Estudante();
		
		estudante.setEmail(EMAIL);
		estudante.setTelefone(TELEFONE);

        Set<ConstraintViolation<Estudante>> violations = validator.validate(estudante);
        assertFalse(violations.isEmpty());

        final String actual = violations.iterator().next().getMessage();
        assertEquals(MSG_NOME_NULL, actual);
	}

	@Test
	public void estudanteSemEmailTest() {
		final Estudante estudante = new Estudante();
		
		estudante.setNome(NOME);
		estudante.setTelefone(TELEFONE);

        Set<ConstraintViolation<Estudante>> violations = validator.validate(estudante);
        assertFalse(violations.isEmpty());

        final String actual = violations.iterator().next().getMessage();
        assertEquals(MSG_EMAIL_NULL, actual);
	}

	@Test
	public void estudanteEmailInvalidoTest() {
		final Estudante estudante = new Estudante();
		
		estudante.setNome(NOME);
		estudante.setEmail(NOME);
		estudante.setTelefone(TELEFONE);

        Set<ConstraintViolation<Estudante>> violations = validator.validate(estudante);
        assertFalse(violations.isEmpty());

        final String actual = violations.iterator().next().getMessage();
        assertEquals(MSG_EMAIL_INVALID, actual);
	}

	@Test
	public void estudanteSemTelefoneTest() {
		final Estudante estudante = new Estudante();
		
		estudante.setNome(NOME);
		estudante.setEmail(EMAIL);
		estudante.setTelefone(TELEFONE);

        Set<ConstraintViolation<Estudante>> violations = validator.validate(estudante);
        assertTrue(violations.isEmpty());
	}

	@Test
	public void serviceCadastrarEstudanteTest() {
		Estudante estudante = new Estudante();
		estudante.setNome(NOME);
		estudante.setEmail(EMAIL);
		estudante.setTelefone(TELEFONE);

		service.cadastrarEstudante(estudante);
		assertTrue(estudante != null);
	}

	@Test
	public void serviceAtualizarEstudanteTest() {
		estudanteTest.setNome("Nome Alterado!");

		service.atualizarEstudante(estudanteTest);
	}

	@Test
	public void serviceBuscarEstudantePorIdTest() {
		Estudante estudante = service.buscarEstudante(estudanteTest.getId());

		assertTrue(estudante != null);
	}

	@Test
	public void serviceBuscarEstudantesTest() {
		List<Estudante> lista = service.buscarEstudantes();

		assertFalse(lista.isEmpty());
	}
	
}
