package br.com.mercadolivre.desafiospring.dto;

import br.com.mercadolivre.desafiospring.domain.Post;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FeedPostsDTO {

    private Integer userID;
    private List<Post> posts = new ArrayList<>();
}
