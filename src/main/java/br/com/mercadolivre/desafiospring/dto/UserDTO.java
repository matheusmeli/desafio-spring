package br.com.mercadolivre.desafiospring.dto;

import br.com.mercadolivre.desafiospring.domain.User;
import lombok.Data;

@Data
public class UserDTO {

    private Integer userId;
    private String name;

    public UserDTO(User user){
        this.userId = user.getId();
        this.name = user.getName();
    }
}
