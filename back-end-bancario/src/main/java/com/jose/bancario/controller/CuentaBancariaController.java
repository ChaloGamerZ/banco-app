package com.jose.bancario.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jose.bancario.dto.CuentaBancariaRequest;
import com.jose.bancario.dto.TransaccionRequest;
import com.jose.bancario.entity.CuentaBancaria;
import com.jose.bancario.service.CuentaBancariaService;

import io.micrometer.core.instrument.config.validate.ValidationException;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/accounts")
public class CuentaBancariaController {

    @Autowired
    private CuentaBancariaService cuentaBancariaService;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody @Valid CuentaBancariaRequest request) {
        try {
            CuentaBancaria cuenta = cuentaBancariaService.createAccount(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(cuenta);
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<?> deposit(@PathVariable Long id, @RequestBody @Valid TransaccionRequest request) {
        try {
            cuentaBancariaService.deposit(id, request.getMonto());
            return ResponseEntity.ok("Depósito realizado con éxito.");
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable Long id, @RequestBody @Valid TransaccionRequest request) {
        try {
            cuentaBancariaService.withdraw(id, request.getMonto());
            return ResponseEntity.ok("Retiro realizado con éxito.");
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<?> getBalance(@PathVariable Long id) {
        BigDecimal balance = cuentaBancariaService.getBalance(id);
        return ResponseEntity.ok(balance);
    }
}
