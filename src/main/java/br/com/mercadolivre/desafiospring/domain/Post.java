package br.com.mercadolivre.desafiospring.domain;

import br.com.mercadolivre.desafiospring.dto.PostDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product detail;

    private Integer category;
    private double price;

    public Post(PostDTO postDTO, User user, Product detail, Integer category, double price){
        this.date = postDTO.getDate();
        this.user = user;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }
}
