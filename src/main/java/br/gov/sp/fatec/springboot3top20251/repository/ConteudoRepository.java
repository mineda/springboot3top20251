package br.gov.sp.fatec.springboot3top20251.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springboot3top20251.entity.Conteudo;

public interface ConteudoRepository extends JpaRepository<Conteudo, Long> {
    
    public List<Conteudo> findByTrabalhoDataHoraEntregaLessThanAndTextoContainingIgnoreCase(LocalDateTime dataHoraEntrega, String texto);
}
