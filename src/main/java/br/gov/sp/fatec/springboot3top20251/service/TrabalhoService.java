package br.gov.sp.fatec.springboot3top20251.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springboot3top20251.entity.Trabalho;
import br.gov.sp.fatec.springboot3top20251.repository.TrabalhoRepository;

@Service
public class TrabalhoService {

    @Autowired
    private TrabalhoRepository repo;
    
    public List<Trabalho> buscarTodos() {
        return repo.findAll();
    }

    public Trabalho novo(Trabalho trabalho) {
        if(trabalho.getTitulo() == null ||
            trabalho.getTitulo().isBlank() ||
            trabalho.getGrupo() == null ||
            trabalho.getGrupo().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta título ou grupo!");
        }
        return repo.save(trabalho);
    }

    public List<Trabalho> buscarPorTituloENota(String titulo, Integer nota) {
        return repo.buscarPorTituloENota(titulo, nota);
    }

    public Trabalho buscarPorId(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trabalho não encontrado"));
    }
}
