package com.jose.bancario;

import com.jose.bancario.controller.CuentaBancariaController;
import com.jose.bancario.dto.CuentaBancariaRequest;
import com.jose.bancario.dto.TransaccionRequest;
import com.jose.bancario.entity.CuentaBancaria;
import com.jose.bancario.service.CuentaBancariaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.http.HttpStatus.CREATED;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class CuentaBancariaControllerTest {

    @InjectMocks
    private CuentaBancariaController cuentaBancariaController;

    @Mock
    private CuentaBancariaService cuentaBancariaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount() {
        CuentaBancariaRequest request = new CuentaBancariaRequest();
        request.setTitular("Juan Perez");

        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        cuentaBancaria.setId(1L);
        cuentaBancaria.setTitular("Juan Perez");
        cuentaBancaria.setSaldo(BigDecimal.ZERO);

        when(cuentaBancariaService.createAccount(any(CuentaBancariaRequest.class))).thenReturn(cuentaBancaria);

        var response = cuentaBancariaController.createAccount(request);

        assertThat(response.getStatusCode()).isEqualTo(CREATED);
        assertThat(response.getBody()).isEqualTo(cuentaBancaria);
    }


    @Test
    void testWithdraw() {
        TransaccionRequest request = new TransaccionRequest();
        request.setMonto(BigDecimal.valueOf(100));

        doNothing().when(cuentaBancariaService).withdraw(anyLong(), any(BigDecimal.class));

        var response = cuentaBancariaController.withdraw(1L, request);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isEqualTo("Retiro realizado con éxito.");
    }
    
}
