package br.com.mercadolivre.desafiospring.resources;

import br.com.mercadolivre.desafiospring.dto.FeedPromoPostDTO;
import br.com.mercadolivre.desafiospring.dto.PromoPostCountDTO;
import br.com.mercadolivre.desafiospring.dto.PromoPostDTO;
import br.com.mercadolivre.desafiospring.resources.exceptions.UserNotASalesmanException;
import br.com.mercadolivre.desafiospring.resources.exceptions.UserNotFoundException;
import br.com.mercadolivre.desafiospring.services.PromoPostService;
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
    public ResponseEntity<Void> newPromoPost(@RequestBody PromoPostDTO promoPostDTO) throws UserNotASalesmanException, UserNotFoundException {
        promoPostService.insert(promoPostDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "{userID}/countPromo", method = RequestMethod.GET)
    public ResponseEntity<PromoPostCountDTO> getPromoPostCount(@PathVariable Integer userID) throws UserNotASalesmanException, UserNotFoundException {
        PromoPostCountDTO promoPostCountDTO = promoPostService.getPromoPostCount(userID);
        return ResponseEntity.ok().body(promoPostCountDTO);
    }

    @RequestMapping(value = "{userID}/list", method = RequestMethod.GET)
    public ResponseEntity<FeedPromoPostDTO> getPromoPostList(@PathVariable Integer userID) throws UserNotASalesmanException, UserNotFoundException {
        FeedPromoPostDTO promoPostCountDTO = promoPostService.getPromoPostList(userID);
        return ResponseEntity.ok().body(promoPostCountDTO);
    }
}
