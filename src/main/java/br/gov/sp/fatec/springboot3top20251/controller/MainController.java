package br.gov.sp.fatec.springboot3top20251.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MainController {

    @GetMapping
    public String index() {
        return "Essa é a rota principal e não tem nada aqui!";
    }
    
}
