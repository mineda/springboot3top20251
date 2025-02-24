package br.gov.sp.fatec.springboot3top20251.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springboot3top20251.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
