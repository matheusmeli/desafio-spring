package br.com.mercadolivre.desafiospring.domain;

import br.com.mercadolivre.desafiospring.dto.SalesmanDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Salesman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private Integer age;

    @ManyToMany(mappedBy = "followedSalesman")
    private List<User> followers = new ArrayList<>();

    @OneToMany(mappedBy = "salesman", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    public Salesman(SalesmanDTO salesmanDTO) {
        this.id = null;
        this.name = salesmanDTO.getName();
        this.email = salesmanDTO.getEmail();
        this.age = salesmanDTO.getAge();
    }
}
