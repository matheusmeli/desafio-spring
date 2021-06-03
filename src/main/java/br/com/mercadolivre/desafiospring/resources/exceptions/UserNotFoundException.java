package br.com.mercadolivre.desafiospring.resources.exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(Integer userId) {
        super("User ID " + userId + " not found.");
    }
}
