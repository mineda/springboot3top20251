package br.gov.sp.fatec.springboot3top20251.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springboot3top20251.entity.Hardware;
import br.gov.sp.fatec.springboot3top20251.repository.HardwareRepository;

@Service
public class HardwareService {

    @Autowired
    private HardwareRepository repo;

    public List<Hardware> buscarPorDescricao(String descricao) {
        return repo.findByDescricaoContainingIgnoreCase(descricao);
    }

    public Hardware novo(Hardware hardware) {
        if(hardware == null || 
                hardware.getDescricao() == null ||
                hardware.getDescricao().isBlank() ||
                hardware.getDataHoraAnuncio() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hardware, descrição e data/hora de anúncio não podem ser nulos ou vazios");
        }
        return repo.save(hardware);
    }

    public List<Hardware> todos() {
        return repo.findAll();
    }
    
}
