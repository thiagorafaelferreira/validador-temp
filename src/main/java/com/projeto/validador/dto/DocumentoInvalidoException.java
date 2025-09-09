package com.projeto.validador.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DocumentoInvalidoException extends RuntimeException {
    private final String documento;
    private final String motivo;

    public DocumentoInvalidoException(String documento, String motivo) {
        super("Documento inválido: " + documento + " - Motivo: " + motivo);
        this.documento = documento;
        this.motivo = motivo;
    }
}
