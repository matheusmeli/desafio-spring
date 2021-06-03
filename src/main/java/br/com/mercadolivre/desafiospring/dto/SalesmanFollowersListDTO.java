package br.com.mercadolivre.desafiospring.dto;

import br.com.mercadolivre.desafiospring.domain.User;

import java.util.ArrayList;
import java.util.List;

public class SalesmanFollowersListDTO extends UserDTO{

    private List<UserDTO> followers = new ArrayList<>();

    public SalesmanFollowersListDTO(User user) {
        super(user);
    }

    public List<UserDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserDTO> followers) {
        this.followers = followers;
    }
}