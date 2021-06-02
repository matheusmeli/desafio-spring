package br.com.mercadolivre.desafiospring.dto;

import br.com.mercadolivre.desafiospring.domain.User;

public class PromoPostCountDTO extends UserDTO {

    private Integer promoProductsCount;

    public PromoPostCountDTO(User user) {
        super(user);
    }

    public Integer getPromoProductsCount() {
        return promoProductsCount;
    }

    public void setPromoProductsCount(Integer promoProductsCount) {
        this.promoProductsCount = promoProductsCount;
    }
}
