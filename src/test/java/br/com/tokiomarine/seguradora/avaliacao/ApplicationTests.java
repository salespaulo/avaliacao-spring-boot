package br.com.tokiomarine.seguradora.avaliacao;

import static br.com.tokiomarine.seguradora.avaliacao.Utils.CURSO;
import static br.com.tokiomarine.seguradora.avaliacao.Utils.EMAIL;
import static br.com.tokiomarine.seguradora.avaliacao.Utils.MATRICULA;
import static br.com.tokiomarine.seguradora.avaliacao.Utils.MSG_EMAIL_INVALID;
import static br.com.tokiomarine.seguradora.avaliacao.Utils.MSG_EMAIL_NULL;
import static br.com.tokiomarine.seguradora.avaliacao.Utils.MSG_MATRICULA_NULL;
import static br.com.tokiomarine.seguradora.avaliacao.Utils.MSG_NOME_NULL;
import static br.com.tokiomarine.seguradora.avaliacao.Utils.NOME;
import static br.com.tokiomarine.seguradora.avaliacao.Utils.TELEFONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudanteService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ApplicationTests {

	@Autowired
	private EstudanteService service;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private Validator validator;
	private Estudante estudanteTest;
	private Estudante estudanteTest2;

	@Before
	public void beforeAll() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

		estudanteTest = new Estudante();
		estudanteTest.setNome(NOME);
		estudanteTest.setEmail(EMAIL);
		estudanteTest.setTelefone(TELEFONE);
		estudanteTest.setMatricula(MATRICULA);
		estudanteTest.setCurso(CURSO);

		service.cadastrarEstudante(estudanteTest);

		estudanteTest2 = new Estudante();
		estudanteTest2.setNome(NOME);
		estudanteTest2.setEmail(EMAIL);
		estudanteTest2.setTelefone(TELEFONE);
		estudanteTest2.setMatricula(MATRICULA);
		estudanteTest2.setCurso(CURSO);

		service.cadastrarEstudante(estudanteTest2);
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
		estudante.setMatricula(MATRICULA);
		estudante.setCurso(CURSO);

		Set<ConstraintViolation<Estudante>> violations = validator.validate(estudante);
		assertFalse(violations.isEmpty());

		final String actual = violations.iterator().next().getMessage();
		assertEquals(MSG_NOME_NULL, actual);
	}

	@Test
	public void estudanteSemMatriculaTest() {
		final Estudante estudante = new Estudante();

		estudante.setNome(NOME);
		estudante.setEmail(EMAIL);
		estudante.setTelefone(TELEFONE);
		estudante.setCurso(CURSO);

		Set<ConstraintViolation<Estudante>> violations = validator.validate(estudante);
		assertFalse(violations.isEmpty());

		final String actual = violations.iterator().next().getMessage();
		assertEquals(MSG_MATRICULA_NULL, actual);
	}

	@Test
	public void estudanteSemEmailTest() {
		final Estudante estudante = new Estudante();

		estudante.setNome(NOME);
		estudante.setTelefone(TELEFONE);
		estudante.setMatricula(MATRICULA);
		estudante.setCurso(CURSO);

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
		estudante.setMatricula(MATRICULA);
		estudante.setCurso(CURSO);

		Set<ConstraintViolation<Estudante>> violations = validator.validate(estudante);
		assertFalse(violations.isEmpty());

		final String actual = violations.iterator().next().getMessage();
		assertEquals(MSG_EMAIL_INVALID, actual);
	}

	@Test
	public void estudanteOKTest() {
		final Estudante estudante = new Estudante();

		estudante.setNome(NOME);
		estudante.setEmail(EMAIL);
		estudante.setTelefone(TELEFONE);
		estudante.setMatricula(MATRICULA);
		estudante.setCurso(CURSO);

		Set<ConstraintViolation<Estudante>> violations = validator.validate(estudante);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void serviceCadastrarEstudanteTest() {
		Estudante estudante = new Estudante();
		estudante.setNome(NOME);
		estudante.setEmail(EMAIL);
		estudante.setTelefone(TELEFONE);
		estudante.setMatricula(MATRICULA);
		estudante.setCurso(CURSO);

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

	@Test
	public void restCadastrarEstudanteTest() throws Exception {
		Estudante estudante = new Estudante();
		estudante.setNome(NOME);
		estudante.setEmail(EMAIL);
		estudante.setTelefone(TELEFONE);
		estudante.setMatricula(MATRICULA);
		estudante.setCurso(CURSO);

		final byte[] content = objectMapper.writeValueAsBytes(estudante);

		this.mockMvc.perform(post("/api/estudantes").contentType(MediaType.APPLICATION_JSON).content(content)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void restAtualizarEstudanteTest() throws Exception {
		estudanteTest2.setCurso("Culinária");

		final byte[] content = objectMapper.writeValueAsBytes(estudanteTest2);

		this.mockMvc.perform(put("/api/estudantes/" + estudanteTest2.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(content).accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void restBuscarEstudantePorId() throws Exception {
		final MvcResult result = this.mockMvc
				.perform(get("/api/estudantes/" + estudanteTest2.getId()).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = result.getResponse();
		Estudante estudantes = objectMapper.readValue(response.getContentAsByteArray(), Estudante.class);

		assertEquals(estudanteTest2.getId(), estudantes.getId());
	}

	@Test
	public void restBuscarEstudantesTest() throws Exception {
		final MvcResult result = this.mockMvc.perform(get("/api/estudantes").accept(MediaType.APPLICATION_JSON))
				.andReturn();

		MockHttpServletResponse response = result.getResponse();
		@SuppressWarnings("unchecked")
		List<Estudante> estudantes = objectMapper.readValue(response.getContentAsByteArray(), List.class);

		assertFalse(estudantes.isEmpty());
	}

	@Test
	public void restExcluirEstudantePorId() throws Exception {
		this.mockMvc.perform(delete("/api/estudantes/" + estudanteTest2.getId()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
