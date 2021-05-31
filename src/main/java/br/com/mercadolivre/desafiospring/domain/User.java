package br.com.mercadolivre.desafiospring.domain;

import br.com.mercadolivre.desafiospring.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private Integer age;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "USER_SALESMAN",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "salesman_id"))
    private List<Salesman> followedSalesman = new ArrayList<>();

    public User(UserDTO userDTO){
        this.id = null;
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.age = userDTO.getAge();
    }
}
