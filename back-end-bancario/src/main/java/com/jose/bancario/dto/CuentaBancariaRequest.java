package com.jose.bancario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CuentaBancariaRequest {

    @NotBlank(message = "El titular es obligatorio.")
    @Size(min = 1, max = 100, message = "El titular debe tener entre 1 y 100 caracteres.")
    private String titular;

}