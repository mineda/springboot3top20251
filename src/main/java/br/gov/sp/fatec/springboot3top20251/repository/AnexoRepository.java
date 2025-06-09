package br.gov.sp.fatec.springboot3top20251.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springboot3top20251.entity.Anexo;

public interface AnexoRepository extends JpaRepository<Anexo, Long> {

    public List<Anexo> findByTipoContainingIgnoreCaseAndTrabalhoTituloContainingIgnoreCase(String tipo, String titulo);
    
}
