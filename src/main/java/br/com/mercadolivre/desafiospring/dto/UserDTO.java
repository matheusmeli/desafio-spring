package br.com.mercadolivre.desafiospring.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String name;
    private String email;
    private Integer age;
}