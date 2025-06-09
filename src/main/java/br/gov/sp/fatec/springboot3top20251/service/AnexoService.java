package br.gov.sp.fatec.springboot3top20251.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springboot3top20251.entity.Anexo;
import br.gov.sp.fatec.springboot3top20251.repository.AnexoRepository;

@Service
public class AnexoService {

    @Autowired
    private AnexoRepository repo;

    @Autowired
    private TrabalhoService trabalhoService;

    public List<Anexo> todos() {
        return repo.findAll();
    }

    public List<Anexo> buscarPorTipoETituloDoTrabalho(String tipo, String titulo) {
        if(tipo == null || tipo.isBlank() || titulo == null || titulo.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo e título do trabalho são obrigatórios");
        }
        return repo.findByTipoContainingIgnoreCaseAndTrabalhoTituloContainingIgnoreCase(tipo, titulo);
    }

    public Anexo novo(Anexo anexo) {
        if(anexo == null ||
               anexo.getTexto() == null || 
               anexo.getTexto().isBlank() ||
               anexo.getTrabalho() == null || 
               anexo.getTrabalho().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Texto e id do trabalho são obrigatórios");
        }
        if(anexo.getDataHora() == null) {
            anexo.setDataHora(LocalDateTime.now());
        }
        anexo.setTrabalho(trabalhoService.buscarPorId(anexo.getTrabalho().getId()));
        anexo.setId(null);
        return repo.save(anexo);
    }
    
}
