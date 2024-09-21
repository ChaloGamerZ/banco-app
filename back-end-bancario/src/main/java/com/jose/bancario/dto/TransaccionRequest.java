package com.jose.bancario.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.DecimalMin;

@Data
public class TransaccionRequest {

    @NotNull(message = "El monto es obligatorio.")
    @DecimalMin(value = "0.01", message = "El monto debe ser positivo.")
    private BigDecimal monto;

    
}