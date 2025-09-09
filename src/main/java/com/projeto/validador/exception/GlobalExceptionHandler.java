package com.projeto.validador.exception;

import com.projeto.validador.dto.DocumentoInvalidoException;
import com.projeto.validador.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DocumentoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleDocumentoInvaliddo(DocumentoInvalidoException die, WebRequest request) {

        log.warn("Documento inválido: {} - Path: {} - IP: {}",
                die.getMessage(),
                request.getDescription(false),
                request.getHeader("X-Forwarded-For"));

        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Documento Inválido")
                .message(die.getMessage())
                .build();

        return ResponseEntity.badRequest().body(error);
    }
}
