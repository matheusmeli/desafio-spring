package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.domain.Salesman;
import br.com.mercadolivre.desafiospring.domain.User;
import br.com.mercadolivre.desafiospring.dto.SalesmanFollowersCountDTO;
import br.com.mercadolivre.desafiospring.dto.SalesmanFollowersListDTO;
import br.com.mercadolivre.desafiospring.dto.UserDTO;
import br.com.mercadolivre.desafiospring.dto.UserFollowedListDTO;
import br.com.mercadolivre.desafiospring.repositories.SalesmanRepository;
import br.com.mercadolivre.desafiospring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SalesmanRepository salesmanRepository;

    public UserService(UserRepository userRepository, SalesmanRepository salesmanRepository) {
        this.userRepository = userRepository;
        this.salesmanRepository = salesmanRepository;
    }

    public User insert(User user){
        return userRepository.save(user);
    }

    public User follow(Integer userId, Integer salesmanIDToFollow) {
        User user = userRepository.findById(userId).get();
//        User user = userRepository.getById(userId);
        Salesman salesmanToFollow = salesmanRepository.findById(salesmanIDToFollow).get();

        user.getFollowed().add(salesmanToFollow);

        salesmanRepository.save(salesmanToFollow);
        userRepository.save(user);

        return user;
    }

    public User unfollow(Integer userID, Integer salesmanIDToUnfollow) {
        User user = userRepository.findById(userID).get();
        Salesman salesmanToUnfollow = salesmanRepository.findById(salesmanIDToUnfollow).get();

        user.getFollowed().remove(salesmanToUnfollow);

        salesmanRepository.save(salesmanToUnfollow);
        userRepository.save(user);

        return user;
    }

    public SalesmanFollowersListDTO getFollowersList(Integer userID, String orderBy) {
        Salesman salesman = salesmanRepository.findById(userID).get();
        List<UserDTO> followers = null;

        SalesmanFollowersListDTO salesmanFollowersListDTO = new SalesmanFollowersListDTO(salesman);
        if(orderBy.equals("name_desc")){
            followers = salesman.getFollowers().stream().sorted(comparing(User::getName, String.CASE_INSENSITIVE_ORDER).reversed()).map(UserDTO::new).collect(Collectors.toList());
        }else if(orderBy.equals("name_asc")){
            followers = salesman.getFollowers().stream().sorted(comparing(User::getName, String.CASE_INSENSITIVE_ORDER)).map(UserDTO::new).collect(Collectors.toList());
        }

        salesmanFollowersListDTO.setFollowers(followers);

        return salesmanFollowersListDTO;
    }

    public UserFollowedListDTO getFollowedList(Integer userID, String orderBy) {
        User user = userRepository.findById(userID).get();
        List<UserDTO> followed = null;

        UserFollowedListDTO userFollowedListDTO = new UserFollowedListDTO(user);

        if(orderBy.equals("name_desc")){
            followed = user.getFollowed().stream().sorted(comparing(Salesman::getName).reversed()).map(UserDTO::new).collect(Collectors.toList());
        }else if(orderBy.equals("name_asc")){
            followed = user.getFollowed().stream().sorted(comparing(Salesman::getName)).map(UserDTO::new).collect(Collectors.toList());
        }

        userFollowedListDTO.setFollowed(followed);

        return userFollowedListDTO;
    }

    public SalesmanFollowersCountDTO getFollowersCount(Integer userID) {
        Salesman salesman = salesmanRepository.findById(userID).get();
        SalesmanFollowersCountDTO salesmanFollowersCountDTO = new SalesmanFollowersCountDTO(salesman);
        salesmanFollowersCountDTO.setFollowersCount(salesman.getFollowers().size());

        return salesmanFollowersCountDTO;
    }
}
