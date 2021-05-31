package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.domain.User;
import br.com.mercadolivre.desafiospring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User insert(User user){
        return userRepository.save(user);
    }

}
