package br.com.tokiomarine.seguradora.avaliacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;

public class EstudanteTests {
	private static final String NOME = "Test";
	private static final String EMAIL = "test@test.com";
	private static final String TELEFONE = "11911223344";

	private static final String MSG_NOME_NULL = "Nome é obrigatório!";
	private static final String MSG_EMAIL_NULL = "E-mail é obrigatório!";
	private static final String MSG_EMAIL_INVALID = "E-mail é invalido!";

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
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

}
