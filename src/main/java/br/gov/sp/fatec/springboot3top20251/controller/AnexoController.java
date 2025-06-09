package br.gov.sp.fatec.springboot3top20251.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springboot3top20251.entity.Anexo;
import br.gov.sp.fatec.springboot3top20251.service.AnexoService;

@RestController
@CrossOrigin
@RequestMapping(value = "/anexo")
public class AnexoController {

    @Autowired
    private AnexoService service;

    @GetMapping
    public List<Anexo> buscarTodos() {
        return service.todos();
    }

    @GetMapping(value = "/{tipo}/{titulo}")
    public List<Anexo> buscarPorTipoETituloDoTrabalho(@PathVariable("tipo") String tipo, @PathVariable("titulo") String titulo) {
        return service.buscarPorTipoETituloDoTrabalho(tipo, titulo);
    }

    @PostMapping
    public Anexo novo(@RequestBody Anexo anexo) {
        return service.novo(anexo);
    }

}
