package com.projeto.validador.factory;

import com.projeto.validador.domain.RegrasCnpj;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCnpj implements Validador {

    public boolean isValido(String entrada) {
        return RegrasCnpj.cnpjAlfanumericoValido(entrada);
    }

    public String getTipoDocumento() {
        return "CNPJ";
    }

}
