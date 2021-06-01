package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.domain.Post;
import br.com.mercadolivre.desafiospring.domain.Product;
import br.com.mercadolivre.desafiospring.domain.User;
import br.com.mercadolivre.desafiospring.repositories.PostRepository;
import br.com.mercadolivre.desafiospring.repositories.ProductRepository;
import br.com.mercadolivre.desafiospring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class DBService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ProductRepository productRepository;

    public DBService(UserRepository userRepository, PostRepository postRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.productRepository = productRepository;
    }

    public void instantiateTestDatabase(){
        User user1 = new User(null, "Matheus", "vsgmatheus@gmail.com", 21);
        User user2 = new User(null, "Patricia", "patricia@gmail.com", 21);
        User user3 = new User(null, "Gabriel", "gabriel@gmail.com", 21);
        User user4 = new User(null, "João", "joão@gmail.com", 21);
        User user5 = new User(null, "Henrique", "Henrique@gmail.com", 21);

        user1.setFollowed(new HashSet<User>(Arrays.asList(user2)));
        user2.setFollowed(new HashSet<User>(Arrays.asList(user3, user1)));

        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));

        Product product1 = new Product(null, "Table", "Furniture", "IKEA", "brown", "Regular Table");
        Post post1 = new Post(user1, null, LocalDate.now(), product1, 1, 100.0);

        productRepository.saveAll(Arrays.asList(product1));
        postRepository.saveAll(Arrays.asList(post1));

    }
}
