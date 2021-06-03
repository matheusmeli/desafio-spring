package br.com.mercadolivre.desafiospring.resources.exceptions;

public class UserNotASalesmanException extends Exception {

    public UserNotASalesmanException(Integer userId) {
        super("Id " + userId + " is not a Salesman");
    }
}
