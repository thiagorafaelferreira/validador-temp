package com.projeto.validador.controller;

import com.projeto.validador.service.ValidadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/validar")
@RequiredArgsConstructor
public class ValidadorController {

    private final ValidadorService validadorService;

    @GetMapping("/cnpj")
    public ResponseEntity<String> cnpj(@RequestParam String documento) {
        return ResponseEntity.ok().body(validadorService.validar(documento));
    }

    @GetMapping("/cpf")
    public ResponseEntity<String> cpf(@RequestParam String documento) {
        return ResponseEntity.ok().body(validadorService.validar(documento));
    }
}
