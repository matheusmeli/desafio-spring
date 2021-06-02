package br.com.mercadolivre.desafiospring.dto;

import br.com.mercadolivre.desafiospring.domain.Post;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class FeedPostsDTO {

    private Integer userID;
    private List<Post> posts = new ArrayList<>();
}
