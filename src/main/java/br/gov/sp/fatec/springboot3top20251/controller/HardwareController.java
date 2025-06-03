package br.gov.sp.fatec.springboot3top20251.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springboot3top20251.entity.Hardware;
import br.gov.sp.fatec.springboot3top20251.service.HardwareService;

@RestController
@CrossOrigin
@RequestMapping(value = "/hardware")
public class HardwareController {

    @Autowired
    private HardwareService service;

    @GetMapping
    public List<Hardware> todos() {
        return service.todos();
    }
    @GetMapping("/{descricao}")
    public List<Hardware> buscarPorDescricao(@PathVariable String descricao) {
        return service.buscarPorDescricao(descricao);
    }
    @PostMapping
    public Hardware novo(@RequestBody Hardware hardware) {
        return service.novo(hardware);
    }
    
}
