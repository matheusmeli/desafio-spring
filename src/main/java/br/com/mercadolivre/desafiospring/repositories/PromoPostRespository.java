package br.com.mercadolivre.desafiospring.repositories;

import br.com.mercadolivre.desafiospring.domain.PromoPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoPostRespository extends JpaRepository<PromoPost, Integer> {
}
