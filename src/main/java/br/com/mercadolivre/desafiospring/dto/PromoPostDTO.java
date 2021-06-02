package br.com.mercadolivre.desafiospring.dto;

import lombok.Data;

@Data
public class PromoPostDTO extends PostDTO{

    private boolean hasPromo;
    private double discount;
}
