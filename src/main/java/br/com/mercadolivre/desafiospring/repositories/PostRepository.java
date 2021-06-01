package br.com.mercadolivre.desafiospring.repositories;

import br.com.mercadolivre.desafiospring.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
