package br.com.mercadolivre.desafiospring.dto;

import br.com.mercadolivre.desafiospring.domain.PromoPost;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FeedPromoPostDTO {

    private Integer userId;
    private String userName;
    private List<PromoPost> posts = new ArrayList<>();
}
