package br.com.mercadolivre.desafiospring.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String body;

    @ManyToOne
    @JoinColumn(name = "salesman_id")
    private Salesman salesman;
}
