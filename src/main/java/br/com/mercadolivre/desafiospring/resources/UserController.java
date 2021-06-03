package br.com.mercadolivre.desafiospring.resources;

import br.com.mercadolivre.desafiospring.domain.User;
import br.com.mercadolivre.desafiospring.dto.SalesmanFollowersCountDTO;
import br.com.mercadolivre.desafiospring.dto.SalesmanFollowersListDTO;
import br.com.mercadolivre.desafiospring.dto.UserFollowedListDTO;
import br.com.mercadolivre.desafiospring.resources.exceptions.UserNotASalesmanException;
import br.com.mercadolivre.desafiospring.resources.exceptions.UserNotFoundException;
import br.com.mercadolivre.desafiospring.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @ApiOperation(value = "User follow salesman")
    @RequestMapping(value = "/{userID}/follow/{salesmanIDToFollow}", method = RequestMethod.POST)
    public ResponseEntity<Void> follow(@PathVariable Integer userID, @PathVariable Integer salesmanIDToFollow) throws UserNotFoundException, UserNotASalesmanException {
        User user = service.follow(userID, salesmanIDToFollow);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{userID}/unfollow/{salesmanIDToFollow}", method = RequestMethod.POST)
    public ResponseEntity<Void> unfollow(@PathVariable Integer userID, @PathVariable Integer salesmanIDToFollow) throws UserNotASalesmanException, UserNotFoundException {
        User user = service.unfollow(userID, salesmanIDToFollow);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "{userID}/followers/list", method = RequestMethod.GET)
    public ResponseEntity<SalesmanFollowersListDTO> getFollowersList(
            @PathVariable Integer userID,
            @RequestParam(value = "order", defaultValue = "name_asc") String orderBy) throws UserNotASalesmanException, UserNotFoundException {
        SalesmanFollowersListDTO salesmanFollowersListDTO = service.getFollowersList(userID, orderBy);
        return ResponseEntity.ok().body(salesmanFollowersListDTO);
    }

    @RequestMapping(value = "{userID}/followed/list", method = RequestMethod.GET)
    public ResponseEntity<UserFollowedListDTO> getFollowedList(
            @PathVariable Integer userID,
            @RequestParam(value = "order", defaultValue = "name_asc") String orderBy) throws UserNotFoundException {
        UserFollowedListDTO userFollowedListDTO = service.getFollowedList(userID, orderBy);
        return ResponseEntity.ok().body(userFollowedListDTO);
    }

    @RequestMapping(value = "{userID}/followers/count", method = RequestMethod.GET)
    public ResponseEntity<SalesmanFollowersCountDTO> getFollowersCount(@PathVariable Integer userID) throws UserNotASalesmanException, UserNotFoundException {
        SalesmanFollowersCountDTO salesmanFollowersCountDTO = service.getFollowersCount(userID);
        return ResponseEntity.ok().body(salesmanFollowersCountDTO);
    }
}
