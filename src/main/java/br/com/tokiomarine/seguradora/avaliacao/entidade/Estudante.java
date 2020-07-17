package br.com.tokiomarine.seguradora.avaliacao.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "estudante")
public class Estudante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@NotNull(message = "Nome é obrigatório!")
	@Column(nullable = false, length = 100)
	private String nome;

	@NotNull(message = "E-mail é obrigatório!")
	@Email(message = "E-mail é invalido!")
	@Column(nullable = false, length = 200)
	private String email;

	//@Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$", message = "Telefone é inválido!")
	@Column(nullable = false, length = 20)
	private String telefone;

	@NotNull(message = "Matricula é obrigatória!")
	@Column(nullable = false, length = 100)
	private String matricula;

	@Column(nullable = false, length = 200)
	private String curso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Estudante)) {
			return false;
		}

		final Estudante other = (Estudante) obj;
		return this.getId().equals(other.getId());
	}

}
