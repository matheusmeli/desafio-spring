package br.com.mercadolivre.desafiospring.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class StandardError implements Serializable {

    private Integer status;
    private String msg;
}
