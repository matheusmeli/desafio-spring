package br.com.mercadolivre.desafiospring.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Salesman extends User{

    @ManyToMany(mappedBy = "followed")
    private Set<User> followers = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Post> posts = new HashSet<>();

    public Salesman(Integer id, String name, String email, Integer age) {
        super(id, name, email, age);
    }
}
