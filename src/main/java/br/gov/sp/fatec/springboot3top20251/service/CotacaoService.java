package br.gov.sp.fatec.springboot3top20251.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springboot3top20251.entity.Cotacao;
import br.gov.sp.fatec.springboot3top20251.entity.Hardware;
import br.gov.sp.fatec.springboot3top20251.repository.CotacaoRepository;
import br.gov.sp.fatec.springboot3top20251.repository.HardwareRepository;

@Service
public class CotacaoService {

    @Autowired
    private CotacaoRepository repo;

    @Autowired
    private HardwareRepository hardwareRepo;

    public List<Cotacao> buscarPorDescricaoEValor(String descricao, Float valor) {
        if (descricao == null || descricao.isBlank() || valor == null || valor < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Descrição não pode ser nula ou vazia e valor deve ser maior ou igual a zero");
        }
        return repo.findByHardwareDescricaoContainingIgnoreCaseAndValorLessThan(descricao, valor);
    }

    public Cotacao novo(Cotacao cotacao) {
        if (cotacao == null || 
            cotacao.getHardware() == null || 
            cotacao.getHardware().getId() == null || 
            cotacao.getValor() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cotação, hardware e valor não podem ser nulos ou inválidos");
        }
        if(cotacao.getDataHora() == null) {
            cotacao.setDataHora(LocalDateTime.now());
        }
        Optional<Hardware> hardwareOpt = hardwareRepo.findById(cotacao.getHardware().getId());
        if (!hardwareOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hardware não encontrado");
        }
        cotacao.setHardware(hardwareOpt.get());
        return repo.save(cotacao);
    }

    public List<Cotacao> todos() {
        return repo.findAll();
    }
    
}
