package br.gov.sp.fatec.springboot3top20251.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springboot3top20251.entity.Hardware;
import br.gov.sp.fatec.springboot3top20251.entity.Wishlist;
import br.gov.sp.fatec.springboot3top20251.repository.HardwareRepository;
import br.gov.sp.fatec.springboot3top20251.repository.WishlistRepository;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository repo;

    @Autowired
    private HardwareRepository hardwareRepo;

    public List<Wishlist> buscarPorDescricaoEInteresse(String descricao, Integer nivelInteresse) {
        if (descricao == null || descricao.isBlank() || nivelInteresse == null || nivelInteresse < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Descrição e nível de interesse não podem ser nulos ou inválidos");
        }
        return repo.findByHardwareDescricaoContainingIgnoreCaseAndNivelInteresseGreaterThan(descricao, nivelInteresse);
    }

    public Wishlist novo(Wishlist wishlist) {
        if (wishlist == null || 
            wishlist.getHardware() == null || 
            wishlist.getNivelInteresse() == null || 
            wishlist.getHardware().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wishlist, hardware e nível de interesse não podem ser nulos");
        }
        if(wishlist.getDataHoraInclusao() == null) {
            wishlist.setDataHoraInclusao(LocalDateTime.now());
        }
        Optional<Hardware> hardwareOpt = hardwareRepo.findById(wishlist.getHardware().getId());
        if (!hardwareOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hardware não encontrado");
        }
        wishlist.setHardware(hardwareOpt.get());
        return repo.save(wishlist);
    }

    public List<Wishlist> todos() {
        return repo.findAll();
    }
    
}
