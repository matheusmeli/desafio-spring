package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.domain.Post;
import br.com.mercadolivre.desafiospring.domain.Product;
import br.com.mercadolivre.desafiospring.domain.Salesman;
import br.com.mercadolivre.desafiospring.domain.User;
import br.com.mercadolivre.desafiospring.repositories.PostRepository;
import br.com.mercadolivre.desafiospring.repositories.ProductRepository;
import br.com.mercadolivre.desafiospring.repositories.SalesmanRepository;
import br.com.mercadolivre.desafiospring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class DBService {

    private final UserRepository userRepository;
    private final SalesmanRepository salesmanRepository;
    private final PostRepository postRepository;
    private final ProductRepository productRepository;

    public DBService(UserRepository userRepository, SalesmanRepository salesmanRepository, PostRepository postRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.salesmanRepository = salesmanRepository;
        this.postRepository = postRepository;
        this.productRepository = productRepository;
    }

    public void instantiateTestDatabase(){
        User user1 = new User(null, "Matheus", "vsgmatheus@gmail.com", 21);
        User user2 = new User(null, "Patricia", "patricia@gmail.com", 21);
        User user3 = new User(null, "Gabriel", "gabriel@gmail.com", 21);
        User user4 = new User(null, "João", "joão@gmail.com", 21);

        Salesman salesman1 = new Salesman(null, "aHenrique", "henrique@gmail.com", 21);
        Salesman salesman2 = new Salesman(null, "eMaria", "maria@gmail.com", 21);
        Salesman salesman3 = new Salesman(null, "zJoão", "joao@gmail.com", 21);

        user1.setFollowed(new HashSet<Salesman>(Arrays.asList(salesman1)));

        salesmanRepository.saveAll(Arrays.asList(salesman1, salesman2, salesman3));
        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4));

        Product product1 = new Product(null, "Table", "Furniture", "IKEA", "brown", "Regular Table");
        Post post1 = new Post(salesman1, null, LocalDate.now().minusDays(5), product1, 1, 100.0);

        Product product2 = new Product(null, "Table", "Furniture", "IKEA", "brown", "Regular Table");
        Post post2 = new Post(salesman1, null, LocalDate.now().minusDays(3), product2, 1, 100.0);

        Product product3 = new Product(null, "Table", "Furniture", "IKEA", "brown", "Regular Table");
        Post post3 = new Post(salesman1, null, LocalDate.now().minusWeeks(1).minusDays(1), product3, 1, 100.0);

        productRepository.saveAll(Arrays.asList(product1, product2, product3));
        postRepository.saveAll(Arrays.asList(post1, post2, post3));
    }
}
