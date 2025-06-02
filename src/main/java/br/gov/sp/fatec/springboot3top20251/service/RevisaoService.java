package br.gov.sp.fatec.springboot3top20251.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springboot3top20251.entity.Revisao;
import br.gov.sp.fatec.springboot3top20251.entity.Trabalho;
import br.gov.sp.fatec.springboot3top20251.repository.RevisaoRepository;
import br.gov.sp.fatec.springboot3top20251.repository.TrabalhoRepository;

@Service
public class RevisaoService {

    @Autowired
    private RevisaoRepository repo;

    @Autowired
    private TrabalhoRepository trabalhoRepo;

    public Revisao nova(Revisao revisao) {
        if (revisao == null ||
                revisao.getConteudo() == null || revisao.getConteudo().isBlank() ||
                revisao.getTrabalho() == null ||
                revisao.getTrabalho().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos para a revisão");
        }
        Optional<Trabalho> trabalhoOpt = trabalhoRepo.findById(revisao.getTrabalho().getId());
        if (trabalhoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trabalho não encontrado");
        }
        revisao.setTrabalho(trabalhoOpt.get());
        if(revisao.getDataHora() == null) {
            revisao.setDataHora(LocalDateTime.now());
        }
        return repo.save(revisao);
    }

    public List<Revisao> todas() {
        return repo.findAll();
    }

    public List<Revisao> buscarPorTrabalhoTituloEConteudo(String titulo, String conteudo) {
        if (titulo == null || titulo.isBlank() || conteudo == null || conteudo.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Título e conteúdo não podem ser vazios");
        }
        return repo.findByTrabalhoTituloContainingIgnoreCaseAndConteudoContainingIgnoreCase(titulo, conteudo);
    }

}
