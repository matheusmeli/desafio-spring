package br.com.mercadolivre.desafiospring.dto;

import br.com.mercadolivre.desafiospring.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserFollowedListDTO extends UserDTO{

    private List<UserDTO> followed = new ArrayList<>();

    public UserFollowedListDTO(User user) {
        super(user);
    }

    public List<UserDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(List<UserDTO> followed) {
        this.followed = followed;
    }
}
