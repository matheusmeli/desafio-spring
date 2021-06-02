package br.com.mercadolivre.desafiospring.repositories;

import br.com.mercadolivre.desafiospring.domain.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesmanRepository extends JpaRepository<Salesman, Integer> {
}
