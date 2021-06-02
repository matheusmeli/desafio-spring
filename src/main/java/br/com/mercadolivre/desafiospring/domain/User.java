package br.com.mercadolivre.desafiospring.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "user_type")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private String name;
    private String email;
    private Integer age;

    @ManyToMany
    @JoinTable(name="user_follows",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="followed_id")})
    private Set<Salesman> followed = new HashSet<>();

    public User(Integer id, String name, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
