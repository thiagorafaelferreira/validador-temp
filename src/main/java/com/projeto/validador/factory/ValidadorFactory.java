package com.projeto.validador.factory;

import com.projeto.validador.interfaces.Validador;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ValidadorFactory {

    private final Map<Integer, Validador> validadores;

    public ValidadorFactory(ValidadorCpf validadorCpf,
                            ValidadorCnpj validadorCnpj) {
        this.validadores = Map.of(
                11, validadorCpf,
                14, validadorCnpj
        );
    }

    public Validador getValidator(String documento) {
        if (documento == null) {
            throw new IllegalArgumentException("Documento não pode ser nulo");
        }

        Validador validator = validadores.get(documento.length());
        if (validator == null) {
            throw new IllegalArgumentException("Tamanho inválido para documento: " + documento.length());
        }

        return validator;
    }
}
