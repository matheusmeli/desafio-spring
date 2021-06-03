package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.domain.Product;
import br.com.mercadolivre.desafiospring.domain.PromoPost;
import br.com.mercadolivre.desafiospring.domain.Salesman;
import br.com.mercadolivre.desafiospring.dto.FeedPromoPostDTO;
import br.com.mercadolivre.desafiospring.dto.PromoPostCountDTO;
import br.com.mercadolivre.desafiospring.dto.PromoPostDTO;
import br.com.mercadolivre.desafiospring.repositories.ProductRepository;
import br.com.mercadolivre.desafiospring.repositories.PromoPostRespository;
import br.com.mercadolivre.desafiospring.resources.exceptions.UserNotASalesmanException;
import br.com.mercadolivre.desafiospring.resources.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromoPostService {

    private final PromoPostRespository promoPostRespository;
    private final ProductRepository productRepository;

    private final UserService userService;

    public PromoPostService(PromoPostRespository promoPostRespository, ProductRepository productRepository, UserService userService) {
        this.promoPostRespository = promoPostRespository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    public void insert(PromoPostDTO promoPostDTO) throws UserNotASalesmanException, UserNotFoundException {
        Integer userID = promoPostDTO.getUserID();
        Salesman salesman = userService.getSalesman(userID);

        Product product = new Product(promoPostDTO.getDetail());
        PromoPost promoPost = new PromoPost(promoPostDTO, salesman, new Product(promoPostDTO.getDetail()), promoPostDTO.getCategory(), promoPostDTO.getPrice(), promoPostDTO.isHasPromo(), promoPostDTO.getDiscount());

        promoPost.setDetail(product);

        productRepository.save(product);
        promoPostRespository.save(promoPost);
    }

    public PromoPostCountDTO getPromoPostCount(Integer userID) throws UserNotASalesmanException, UserNotFoundException {
        Salesman salesman = userService.getSalesman(userID);
        PromoPostCountDTO promoPostCountDTO = new PromoPostCountDTO(salesman);

        List<PromoPost> promoPosts = salesman.getPosts().stream().filter(PromoPost.class::isInstance).map(PromoPost.class::cast).collect(Collectors.toList());

        promoPostCountDTO.setPromoProductsCount(promoPosts.size());

        return promoPostCountDTO;
    }

    public FeedPromoPostDTO getPromoPostList(Integer userID) throws UserNotASalesmanException, UserNotFoundException {
        Salesman salesman = userService.getSalesman(userID);
        FeedPromoPostDTO feedPromoPostDTO = new FeedPromoPostDTO();

        List<PromoPost> promoPosts = salesman.getPosts().stream().filter(PromoPost.class::isInstance).map(PromoPost.class::cast).collect(Collectors.toList());

        feedPromoPostDTO.setUserId(salesman.getId());
        feedPromoPostDTO.setUserName(salesman.getName());
        feedPromoPostDTO.setPosts(promoPosts);

        return feedPromoPostDTO;
    }
}
