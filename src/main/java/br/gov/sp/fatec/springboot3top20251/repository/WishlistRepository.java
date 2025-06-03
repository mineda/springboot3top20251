package br.gov.sp.fatec.springboot3top20251.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springboot3top20251.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    public List<Wishlist> findByHardwareDescricaoContainingIgnoreCaseAndNivelInteresseGreaterThan(String descricao, Integer nivelInteresse);
    
}
