package com.projeto.validador.service;

import com.projeto.validador.factory.Validador;
import com.projeto.validador.factory.ValidadorFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class ValidadorService {

    private ValidadorFactory validadorFactory;

    public String validar(String entrada) {
        Validador validador = validadorFactory.getValidator(entrada);
        return validador.isValido(entrada)
                ? String.format(" %s valido", validador.getTipoDocumento())
                : String.format(" %s invalido", validador.getTipoDocumento());
    }
}
