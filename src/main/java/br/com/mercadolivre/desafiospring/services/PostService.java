package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.domain.Post;
import br.com.mercadolivre.desafiospring.domain.Product;
import br.com.mercadolivre.desafiospring.domain.Salesman;
import br.com.mercadolivre.desafiospring.domain.User;
import br.com.mercadolivre.desafiospring.dto.FeedPostsDTO;
import br.com.mercadolivre.desafiospring.dto.PostDTO;
import br.com.mercadolivre.desafiospring.repositories.PostRepository;
import br.com.mercadolivre.desafiospring.repositories.ProductRepository;
import br.com.mercadolivre.desafiospring.repositories.SalesmanRepository;
import br.com.mercadolivre.desafiospring.resources.exceptions.UserNotASalesmanException;
import br.com.mercadolivre.desafiospring.resources.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final ProductRepository productRepository;

    private final UserService userService;

    public PostService(PostRepository postRepository, ProductRepository productRepository, SalesmanRepository salesmanRepository, UserService userService) {
        this.postRepository = postRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    public void insert(PostDTO postDTO) throws UserNotASalesmanException, UserNotFoundException {
        Integer userID = postDTO.getUserID();
        Salesman salesman = userService.getSalesman(userID);

        Product product = new Product(postDTO.getDetail());
        Post post = new Post(postDTO, salesman, new Product(postDTO.getDetail()), postDTO.getCategory(), postDTO.getPrice());

        post.setDetail(product);

        productRepository.save(product);
        postRepository.save(post);
    }

    public FeedPostsDTO getFollowedUsersPost(Integer userID, String orderBy) throws UserNotFoundException {
        User user = userService.getUser(userID);
        FeedPostsDTO feedPostsDTO = new FeedPostsDTO();

        Set<Post> allPosts = new HashSet<>();
        for (Salesman followed : user.getFollowed()) {
            for (Post post : followed.getPosts()) {
                allPosts.add(post);
            }
        }

        LocalDate twoWeeksAgo = LocalDate.now().minusWeeks(2);
        Set<Post> posts = allPosts.stream()
                .filter(post -> !post.getDate().isBefore(twoWeeksAgo))
                .collect(Collectors.toSet());

        List<Post> sortedList = null;
        if(orderBy.equals("date_asc")){
            sortedList = posts.stream().sorted(Comparator.comparing(Post::getDate)).collect(Collectors.toList());
        }else if(orderBy.equals("date_desc")){
            sortedList = posts.stream().sorted(Comparator.comparing(Post::getDate, Comparator.reverseOrder())).collect(Collectors.toList());
        }

        feedPostsDTO.setPosts(sortedList);
        feedPostsDTO.setUserID(userID);

        return feedPostsDTO;
    }
}
