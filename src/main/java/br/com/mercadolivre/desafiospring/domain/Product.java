package br.com.mercadolivre.desafiospring.domain;

import br.com.mercadolivre.desafiospring.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    @JsonIgnore
    @OneToOne(mappedBy = "detail")
    private Post post;

    public Product(Integer id, String productName, String type, String brand, String color, String notes) {
        this.id = id;
        this.productName = productName;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.notes = notes;
    }

    public Product(ProductDTO detail) {
        this.productName = detail.getProductName();
        this.type = detail.getType();
        this.brand = detail.getBrand();
        this.color = detail.getColor();
        this.notes = detail.getNotes();
    }
}
