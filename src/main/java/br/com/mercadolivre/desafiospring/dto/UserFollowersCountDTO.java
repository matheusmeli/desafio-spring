package br.com.mercadolivre.desafiospring.dto;

import br.com.mercadolivre.desafiospring.domain.User;

public class UserFollowersCountDTO extends UserDTO{

    private Integer followersCount;

    public UserFollowersCountDTO(User user) {
        super(user);
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }
}
