package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudanteService;

@RestController
@RequestMapping("/api")
public class EstudanteRestController {

    private final EstudanteService service;

    @Autowired
    public EstudanteRestController(final EstudanteService service) {
        this.service = service;

    }

    @RequestMapping(value = "/estudantes", method = RequestMethod.POST)
    public @ResponseBody void cadastrarEstudante(@Valid @RequestBody Estudante estudante) {
        try {
            service.cadastrarEstudante(estudante);
        } catch (Exception e) {

        }
    }

    @RequestMapping(value = "/estudantes/{id}", method = RequestMethod.PUT)
    public @ResponseBody void atualizarEstudante(@NotNull @Size(min = 0) @PathVariable Long id,
            @Valid @RequestBody Estudante estudante) {
        try {
            estudante.setId(id);
            service.atualizarEstudante(estudante);
        } catch (Exception e) {

        }
    }

    @RequestMapping(value = "/estudantes/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void excluir(@NotNull @Size(min = 0) @PathVariable Long id) {
        try {
            service.excluirEstudante(id);
        } catch(Exception e) {

        }
    }

    @RequestMapping(value = "/estudantes", method = RequestMethod.GET)
    public @ResponseBody List<Estudante> buscarEstudantes() {
        try {
            return service.buscarEstudantes();
        } catch (Exception e) {
        	return Collections.emptyList();
        }
    }

}