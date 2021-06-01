package br.com.mercadolivre.desafiospring.dto;

import br.com.mercadolivre.desafiospring.domain.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

public class UserFollowedListDTO extends UserDTO{

    private Set<UserDTO> followed = new HashSet<UserDTO>();

    public UserFollowedListDTO(User user) {
        super(user);
    }

    public Set<UserDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(Set<UserDTO> followed) {
        this.followed = followed;
    }
}
