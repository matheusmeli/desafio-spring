package br.com.mercadolivre.desafiospring.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

}
