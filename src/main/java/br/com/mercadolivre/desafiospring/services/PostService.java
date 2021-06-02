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
import br.com.mercadolivre.desafiospring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    final PostRepository postRepository;
    final UserRepository userRepository;
    final ProductRepository productRepository;
    final SalesmanRepository salesmanRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, ProductRepository productRepository, SalesmanRepository salesmanRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.salesmanRepository = salesmanRepository;
    }

    public void insert(PostDTO postDTO) {
        Integer userID = postDTO.getUserID();
        Salesman salesman = salesmanRepository.findById(userID).get();

        Product product = new Product(postDTO.getDetail());
        Post post = new Post(postDTO, salesman, new Product(postDTO.getDetail()), postDTO.getCategory(), postDTO.getPrice());

        post.setDetail(product);

        productRepository.save(product);
        postRepository.save(post);
    }

    public FeedPostsDTO getFollowedUsersPost(Integer userID, String orderBy) {
        User user = userRepository.findById(userID).get();
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
