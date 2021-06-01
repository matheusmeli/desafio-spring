package br.com.mercadolivre.desafiospring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PostDTO {

    private Integer userID;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private ProductDTO detail;
    private Integer category;
    private double price;
}
