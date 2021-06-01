package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.domain.Post;
import br.com.mercadolivre.desafiospring.domain.Product;
import br.com.mercadolivre.desafiospring.domain.User;
import br.com.mercadolivre.desafiospring.dto.PostDTO;
import br.com.mercadolivre.desafiospring.repositories.PostRepository;
import br.com.mercadolivre.desafiospring.repositories.ProductRepository;
import br.com.mercadolivre.desafiospring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    final PostRepository postRepository;

    final UserRepository userRepository;

    final ProductRepository productRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public void insert(PostDTO postDTO) {
        Integer userID = postDTO.getUserID();
        User user = userRepository.findById(userID).get();

        Product product = new Product(postDTO.getDetail());
        Post post = new Post(postDTO, user, new Product(postDTO.getDetail()), postDTO.getCategory(), postDTO.getPrice());

        post.setDetail(product);

        productRepository.save(product);
        postRepository.save(post);
    }

    public List<Post> getFollowedUsersPost(Integer userID) {
        User user = userRepository.findById(userID).get();

        return null;
    }
}
