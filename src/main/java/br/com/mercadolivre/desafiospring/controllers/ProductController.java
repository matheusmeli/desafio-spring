package br.com.mercadolivre.desafiospring.controllers;

import br.com.mercadolivre.desafiospring.domain.Post;
import br.com.mercadolivre.desafiospring.dto.PostDTO;
import br.com.mercadolivre.desafiospring.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    final PostService postService;

    public ProductController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public ResponseEntity<Void> newpost(@RequestBody PostDTO postDTO){
        postService.insert(postDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/followed/{userID}/list", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> followedUsersPost(@PathVariable Integer userID){
        List<Post> followedUsersPost = postService.getFollowedUsersPost(userID);
        return ResponseEntity.ok().body(followedUsersPost);
    }
}
