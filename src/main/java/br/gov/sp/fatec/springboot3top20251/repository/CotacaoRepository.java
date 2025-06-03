package br.gov.sp.fatec.springboot3top20251.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springboot3top20251.entity.Cotacao;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {
    
    public List<Cotacao> findByHardwareDescricaoContainingIgnoreCaseAndValorLessThan(String descricao, Float valor);

}
