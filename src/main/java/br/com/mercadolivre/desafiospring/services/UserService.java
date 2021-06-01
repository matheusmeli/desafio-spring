package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.domain.User;
import br.com.mercadolivre.desafiospring.dto.UserDTO;
import br.com.mercadolivre.desafiospring.dto.UserFollowedListDTO;
import br.com.mercadolivre.desafiospring.dto.UserFollowersCountDTO;
import br.com.mercadolivre.desafiospring.dto.UserFollowersListDTO;
import br.com.mercadolivre.desafiospring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User insert(User user){
        return userRepository.save(user);
    }

    public User follow(Integer userId, Integer userIdToFollow) {
        User user = userRepository.findById(userId).get();
        User userToFollow = userRepository.findById(userIdToFollow).get();

        user.getFollowed().add(userToFollow);

        userRepository.saveAll(Arrays.asList(user, userToFollow));

        return user;
    }

    public UserFollowersListDTO getFollowersList(Integer userID) {
        User user = userRepository.findById(userID).get();

        UserFollowersListDTO userFollowersListDTO = new UserFollowersListDTO(user);
        Set<UserDTO> followers = user.getFollowers().stream().map(UserDTO::new).collect(Collectors.toSet());
        userFollowersListDTO.setFollowers(followers);

        return userFollowersListDTO;
    }

    public UserFollowedListDTO getFollowedList(Integer userID) {
        User user = userRepository.findById(userID).get();

        UserFollowedListDTO userFollowedListDTO = new UserFollowedListDTO(user);
        Set<UserDTO> followed = user.getFollowed().stream().map(UserDTO::new).collect(Collectors.toSet());
        userFollowedListDTO.setFollowed(followed);

        return userFollowedListDTO;
    }

    public UserFollowersCountDTO getFollowersCount(Integer userID) {
        User user = userRepository.findById(userID).get();
        UserFollowersCountDTO userFollowersCountDTO = new UserFollowersCountDTO(user);
        userFollowersCountDTO.setFollowersCount(user.getFollowers().size());

        return userFollowersCountDTO;
    }
}
