package com.projeto.validador.factory;

import com.projeto.validador.domain.RegrasCpf;
import com.projeto.validador.interfaces.Validador;
import org.springframework.stereotype.Service;

@Service
public class ValidadorCpf implements Validador {

    public boolean isValido(String entrada) {
        return RegrasCpf.cpfValido(entrada);
    }

    public String getTipoDocumento() {
        return "CPF";
    }

}

