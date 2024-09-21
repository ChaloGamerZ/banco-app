package com.jose.bancario.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jose.bancario.dto.CuentaBancariaRequest;
import com.jose.bancario.entity.CuentaBancaria;
import com.jose.bancario.entity.Transaccion;
import com.jose.bancario.exception.ValidationException;
import com.jose.bancario.repository.CuentaBancariaRepository;
import com.jose.bancario.repository.TransaccionRepository;

@Service
public class CuentaBancariaService {

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    private static final Logger logger = LoggerFactory.getLogger(CuentaBancariaService.class);

    public CuentaBancaria createAccount(CuentaBancariaRequest request) {
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.setTitular(request.getTitular());
        cuenta.setSaldo(BigDecimal.ZERO);
        logger.info("Cuenta creada con titular: {}", request.getTitular());
        return cuentaBancariaRepository.save(cuenta);
    }

    public void deposit(Long id, BigDecimal monto) {
        CuentaBancaria cuenta = findAccount(id);
        validatePositiveAmount(monto);
        cuenta.setSaldo(cuenta.getSaldo().add(monto));
        logTransaction(cuenta, monto, Transaccion.TipoTransaccion.DEPOSITO);
    }

    public void withdraw(Long id, BigDecimal monto) {
        CuentaBancaria cuenta = findAccount(id);
        validatePositiveAmount(monto);
        validateSufficientBalance(cuenta, monto);
        cuenta.setSaldo(cuenta.getSaldo().subtract(monto));
        logTransaction(cuenta, monto, Transaccion.TipoTransaccion.RETIRO);
    }

    public BigDecimal getBalance(Long id) {
        return findAccount(id).getSaldo();
    }

    private CuentaBancaria findAccount(Long id) {
        return cuentaBancariaRepository.findById(id)
            .orElseThrow(() -> new ValidationException("Cuenta no encontrada."));
    }

    private void validatePositiveAmount(BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            logger.error("El monto de la transacción debe ser positivo.");
            throw new ValidationException("El monto de la transacción debe ser positivo.");
        }
    }

    private void validateSufficientBalance(CuentaBancaria cuenta, BigDecimal monto) {
        if (cuenta.getSaldo().compareTo(monto) < 0) {
            logger.error("Saldo insuficiente para el retiro.");
            throw new ValidationException("Saldo insuficiente para el retiro.");
        }
    }

    private void logTransaction(CuentaBancaria cuenta, BigDecimal monto, Transaccion.TipoTransaccion tipo) {
        Transaccion transaccion = new Transaccion();
        transaccion.setCuentaBancaria(cuenta);
        transaccion.setMonto(monto);
        transaccion.setTipo(tipo);
        transaccionRepository.save(transaccion);
        logger.info("Transacción de tipo {} realizada por un monto de {}.", tipo, monto);
    }
}
