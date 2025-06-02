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

import br.gov.sp.fatec.springboot3top20251.entity.Revisao;
import br.gov.sp.fatec.springboot3top20251.service.RevisaoService;

@RestController
@CrossOrigin
@RequestMapping(value = "/revisao")
public class RevisaoController {

    @Autowired
    private RevisaoService service;

    @PostMapping
    public Revisao nova(@RequestBody Revisao revisao) {
        return service.nova(revisao);
    }

    @GetMapping
    public List<Revisao> todas() {
        return service.todas();
    }

    @GetMapping(value = "/{titulo}/{conteudo}")
    public List<Revisao> buscarPorTrabalhoTituloEConteudo(@PathVariable("titulo") String titulo, @PathVariable("conteudo") String conteudo) {
        return service.buscarPorTrabalhoTituloEConteudo(titulo, conteudo);
    }
    
}
