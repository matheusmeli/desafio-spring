package br.com.mercadolivre.desafiospring.controllers;

import br.com.mercadolivre.desafiospring.dto.PromoPostCountDTO;
import br.com.mercadolivre.desafiospring.dto.PromoPostDTO;
import br.com.mercadolivre.desafiospring.services.PromoPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class PromoPostController {

    private final PromoPostService promoPostService;

    public PromoPostController(PromoPostService promoPostService) {
        this.promoPostService = promoPostService;
    }

    @RequestMapping(value = "/newpromopost", method = RequestMethod.POST)
    public ResponseEntity<Void> newPromoPost(@RequestBody PromoPostDTO promoPostDTO){
        promoPostService.insert(promoPostDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "{userID}/countPromo", method = RequestMethod.GET)
    public ResponseEntity<PromoPostCountDTO> getPromoPostCount(@PathVariable Integer userID) {
        PromoPostCountDTO promoPostCountDTO = promoPostService.getPromoPostCount(userID);
        return ResponseEntity.ok().body(promoPostCountDTO);
    }
}
