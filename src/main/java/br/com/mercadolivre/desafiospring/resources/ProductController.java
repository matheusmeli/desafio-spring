package br.com.mercadolivre.desafiospring.resources;

import br.com.mercadolivre.desafiospring.dto.FeedPostsDTO;
import br.com.mercadolivre.desafiospring.dto.PostDTO;
import br.com.mercadolivre.desafiospring.resources.exceptions.UserNotASalesmanException;
import br.com.mercadolivre.desafiospring.resources.exceptions.UserNotFoundException;
import br.com.mercadolivre.desafiospring.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    final PostService postService;

    public ProductController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public ResponseEntity<Void> newpost(@RequestBody PostDTO postDTO) throws UserNotASalesmanException, UserNotFoundException {
        postService.insert(postDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/followed/{userID}/list", method = RequestMethod.GET)
    public ResponseEntity<FeedPostsDTO> followedUsersPost(
            @PathVariable Integer userID,
            @RequestParam(value = "order", defaultValue = "date_asc") String orderBy) throws UserNotFoundException {
        FeedPostsDTO followedUsersPost = postService.getFollowedUsersPost(userID, orderBy);
        return ResponseEntity.ok().body(followedUsersPost);
    }
}
