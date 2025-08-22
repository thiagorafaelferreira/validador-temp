package com.projeto.validador.domain;

public class RegrasCpf {

    public static boolean cpfValido(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            return false;
        }

        // Elimina CPFs com todos dígitos iguais (ex: 11111111111)
        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        try {
            int soma1 = 0, soma2 = 0;

            // Calcula primeiro dígito verificador
            for (int i = 0; i < 9; i++) {
                int num = Character.getNumericValue(cpf.charAt(i));
                soma1 += num * (10 - i);
                soma2 += num * (11 - i);
            }

            int digito1 = (soma1 * 10) % 11;
            if (digito1 == 10) digito1 = 0;

            soma2 += digito1 * 2;
            int digito2 = (soma2 * 10) % 11;
            if (digito2 == 10) digito2 = 0;

            return digito1 == Character.getNumericValue(cpf.charAt(9)) &&
                    digito2 == Character.getNumericValue(cpf.charAt(10));

        } catch (NumberFormatException e) {
            return false;
        }
    }
}

