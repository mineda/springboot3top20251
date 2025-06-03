package br.gov.sp.fatec.springboot3top20251.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springboot3top20251.entity.Hardware;

public interface HardwareRepository extends JpaRepository<Hardware, Long> {

    public List<Hardware> findByDescricaoContainingIgnoreCase(String descricao);
    
}
