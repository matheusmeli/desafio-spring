package br.com.mercadolivre.desafiospring.repositories;

import br.com.mercadolivre.desafiospring.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
