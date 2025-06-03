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

import br.gov.sp.fatec.springboot3top20251.entity.Wishlist;
import br.gov.sp.fatec.springboot3top20251.service.WishlistService;

@RestController
@CrossOrigin
@RequestMapping(value = "/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService service;

    @GetMapping
    public List<Wishlist> todos() {
        return service.todos();
    }

    @GetMapping("/{descricao}/{nivelInteresse}")
    public List<Wishlist> buscarPorDescricao(@PathVariable("descricao") String descricao, @PathVariable("nivelInteresse") Integer nivelInteresse) {
        return service.buscarPorDescricaoEInteresse(descricao, nivelInteresse);
    }

    @PostMapping
    public Wishlist novo(@RequestBody Wishlist wishlist) {
        return service.novo(wishlist);
    }

}
