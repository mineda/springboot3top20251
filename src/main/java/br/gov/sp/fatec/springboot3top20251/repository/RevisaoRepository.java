package br.gov.sp.fatec.springboot3top20251.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springboot3top20251.entity.Revisao;

public interface RevisaoRepository extends JpaRepository<Revisao, Long> {
    
    public List<Revisao> findByTrabalhoTituloContainingIgnoreCaseAndConteudoContainingIgnoreCase(String titulo, String conteudo);
    
}
