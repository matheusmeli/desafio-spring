package br.com.mercadolivre.desafiospring.dto;

import br.com.mercadolivre.desafiospring.domain.User;

public class SalesmanFollowersCountDTO extends UserDTO{

    private Integer followersCount;

    public SalesmanFollowersCountDTO(User user) {
        super(user);
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }
}
