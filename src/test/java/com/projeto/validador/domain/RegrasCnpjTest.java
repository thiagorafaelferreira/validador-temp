package com.projeto.validador.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegrasCnpjTest {

    @Test
    @DisplayName("Deve retornar true para CNPJ válido sem formatação")
    void deveRetornarVerdadeiroParaCnpjValidoSemFormatacao() {
        String cnpj = "11222333000181";
        assertTrue(RegrasCnpj.cnpjAlfanumericoValido(cnpj));
    }

    @Test
    @DisplayName("Deve retornar true para CNPJ válido com formatação")
    void deveRetornarVerdadeiroParaCnpjValidoComFormatacao() {
        String cnpj = "11.222.333/0001-81";
        assertTrue(RegrasCnpj.cnpjAlfanumericoValido(cnpj));
    }

    @Test
    @DisplayName("Deve retornar false para CNPJ alfanumérico válido")
    void deveRetornarVerdadeiroParaCnpjAlfanumericoValido() {
        String cnpj = "11ABC3330001XY";
        assertFalse(RegrasCnpj.cnpjAlfanumericoValido(cnpj));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
        " ",
        "123",
        "11.222.333/0001-8",
        "11.222.333/0001-82",
        "11.222.333/0001-8X",
        "11222333000182",
        "11.222.333/0001-8X",
        "11.222.333/0001-8#",
        "11.222.333/0001-8 "
    })
    @DisplayName("Deve retornar false para CNPJs inválidos")
    void deveRetornarFalsoParaCnpjsInvalidos(String cnpjInvalido) {
        assertFalse(RegrasCnpj.cnpjAlfanumericoValido(cnpjInvalido));
    }

    @Test
    @DisplayName("Deve retornar CNPJ formatado corretamente")
    void deveFormatarCnpjCorretamente() {
        String cnpj = "11ABC3330001XY";
        String esperado = "11.ABC.333/0001-XY";
        assertEquals(esperado, RegrasCnpj.formatar(cnpj));
    }

    @Test
    @DisplayName("Deve retornar null ao formatar CNPJ nulo")
    void deveRetornarNullAoTentarFormatarCnpjNulo() {
        assertNull(RegrasCnpj.formatar(null));
    }

    @Test
    @DisplayName("Deve retornar o próprio valor ao formatar CNPJ com tamanho inválido")
    void deveRetornarProprioValorAoTentarFormatarCnpjComTamanhoInvalido() {
        String cnpjInvalido = "123";
        assertEquals(cnpjInvalido, RegrasCnpj.formatar(cnpjInvalido));
    }
}
