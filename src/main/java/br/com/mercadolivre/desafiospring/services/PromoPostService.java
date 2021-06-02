package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.domain.Post;
import br.com.mercadolivre.desafiospring.domain.Product;
import br.com.mercadolivre.desafiospring.domain.PromoPost;
import br.com.mercadolivre.desafiospring.domain.Salesman;
import br.com.mercadolivre.desafiospring.dto.PromoPostCountDTO;
import br.com.mercadolivre.desafiospring.dto.PromoPostDTO;
import br.com.mercadolivre.desafiospring.repositories.ProductRepository;
import br.com.mercadolivre.desafiospring.repositories.PromoPostRespository;
import br.com.mercadolivre.desafiospring.repositories.SalesmanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromoPostService {

    final PromoPostRespository promoPostRespository;
    final SalesmanRepository salesmanRepository;
    final ProductRepository productRepository;

    public PromoPostService(PromoPostRespository promoPostRespository, SalesmanRepository salesmanRepository, ProductRepository productRepository) {
        this.promoPostRespository = promoPostRespository;
        this.salesmanRepository = salesmanRepository;
        this.productRepository = productRepository;
    }

    public void insert(PromoPostDTO promoPostDTO){
        Integer userID = promoPostDTO.getUserID();
        Salesman salesman = salesmanRepository.findById(userID).get();

        Product product = new Product(promoPostDTO.getDetail());
        PromoPost promoPost = new PromoPost(promoPostDTO, salesman, new Product(promoPostDTO.getDetail()), promoPostDTO.getCategory(), promoPostDTO.getPrice(), promoPostDTO.isHasPromo(), promoPostDTO.getDiscount());

        promoPost.setDetail(product);

        productRepository.save(product);
        promoPostRespository.save(promoPost);
    }

    public PromoPostCountDTO getPromoPostCount(Integer userID) {
        Salesman salesman = salesmanRepository.findById(userID).get();
        PromoPostCountDTO promoPostCountDTO = new PromoPostCountDTO(salesman);

        List<PromoPost> promoPosts = salesman.getPosts().stream().filter(PromoPost.class::isInstance).map(PromoPost.class::cast).collect(Collectors.toList());

        promoPostCountDTO.setPromoProductsCount(promoPosts.size());

        return promoPostCountDTO;
    }
}
