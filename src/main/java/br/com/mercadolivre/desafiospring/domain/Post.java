package br.com.mercadolivre.desafiospring.domain;

import br.com.mercadolivre.desafiospring.dto.PostDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "post_type")
public class Post {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Salesman user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product detail;

    private Integer category;
    private double price;

    public Post(PostDTO postDTO, Salesman user, Product detail, Integer category, double price){
        this.date = postDTO.getDate();
        this.user = user;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }
}
