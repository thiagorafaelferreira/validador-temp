package com.projeto.validador.factory;

import com.projeto.validador.dto.DocumentoInvalidoException;
import com.projeto.validador.interfaces.Validador;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Objects.isNull;

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
        if (isNull(documento)) {
            throw new DocumentoInvalidoException(documento, "Documento não pode ser nulo");
        }

        Validador validator = validadores.get(documento.length());
        if (validator == null) {
            throw new DocumentoInvalidoException(documento, "Tamanho inválido para documento: " + documento.length());
        }

        return validator;
    }
}
