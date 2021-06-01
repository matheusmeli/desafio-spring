package br.com.mercadolivre.desafiospring.controllers;

import br.com.mercadolivre.desafiospring.domain.User;
import br.com.mercadolivre.desafiospring.dto.UserFollowedListDTO;
import br.com.mercadolivre.desafiospring.dto.UserFollowersCountDTO;
import br.com.mercadolivre.desafiospring.dto.UserFollowersListDTO;
import br.com.mercadolivre.desafiospring.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{userID}/follow/{userIDToFollow}", method = RequestMethod.POST)
    public ResponseEntity<Void> follow(@PathVariable Integer userID, @PathVariable Integer userIDToFollow){
        User user = service.follow(userID, userIDToFollow);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "{userID}/followers/list", method = RequestMethod.GET)
    public ResponseEntity<UserFollowersListDTO> getFollowersList(@PathVariable Integer userID){
        UserFollowersListDTO userFollowersListDTO = service.getFollowersList(userID);
        return ResponseEntity.ok().body(userFollowersListDTO);
    }

    @RequestMapping(value = "{userID}/followed/list", method = RequestMethod.GET)
    public ResponseEntity<UserFollowedListDTO> getFollowedList(@PathVariable Integer userID){
        UserFollowedListDTO userFollowedListDTO = service.getFollowedList(userID);
        return ResponseEntity.ok().body(userFollowedListDTO);
    }

    @RequestMapping(value = "{userID}/followers/count", method = RequestMethod.GET)
    public ResponseEntity<UserFollowersCountDTO> getFollowersCount(@PathVariable Integer userID){
        UserFollowersCountDTO userFollowersCountDTO = service.getFollowersCount(userID);
        return ResponseEntity.ok().body(userFollowersCountDTO);
    }
}
