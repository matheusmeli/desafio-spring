package br.com.mercadolivre.desafiospring.domain;

import br.com.mercadolivre.desafiospring.dto.PostDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PromoPost extends Post{

    private boolean hasPromo;
    private double discount;

    public PromoPost(PostDTO postDTO, Salesman user, Product detail, Integer category, double price) {
        super(postDTO, user, detail, category, price);
    }

    public PromoPost(PostDTO postDTO, Salesman user, Product detail, Integer category, double price, boolean hasPromo, double discount) {
        super(postDTO, user, detail, category, price);
        this.hasPromo = hasPromo;
        this.discount = discount;
    }
}
