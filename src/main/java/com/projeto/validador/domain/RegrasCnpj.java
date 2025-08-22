package com.projeto.validador.domain;

public class RegrasCnpj {
    /**
     * Valida um CNPJ alfanumérico completo (14 caracteres: 12 base + 2 dígitos verificadores).
     * Regras:
     * - Remove pontuação / espaços.
     * - Deve ter 14 caracteres (alfanuméricos).
     * - Últimos 2 caracteres devem ser dígitos (os DVs).
     * - Calcula DVs com o algoritmo módulo 11, usando valor = (int)char - 48
     *   (ou seja, código ASCII decimal subtraído de 48), conforme documentação oficial.
     */
    public static boolean cnpjAlfanumericoValido(String entrada) {
        if (entrada == null) return false;

        // remove qualquer caractere que não seja letra ou número (pontos, barras, traços, espaços)
        String limpo = entrada.replaceAll("[^A-Za-z0-9]", "");
        limpo = limpo.toUpperCase();

        // Deve ter exatamente 14 caracteres (12 base + 2 DVs)
        if (limpo.length() != 14) return false;

        // Os 12 primeiros podem ser alfanuméricos; os 2 últimos devem ser numéricos (DVs)
        String base12 = limpo.substring(0, 12);
        String dvsInformados = limpo.substring(12); // últimos 2 chars

        if (!dvsInformados.matches("\\d{2}")) return false;

        int dvCalculado1 = calcularDv(base12, true);  // calcula primeiro DV
        if (dvCalculado1 < 0) return false; // erro inesperado

        // recalcula o segundo DV incluindo o primeiro DV
        int dvCalculado2 = calcularDv(base12 + dvCalculado1, false);

        int dvInformado1 = Character.getNumericValue(dvsInformados.charAt(0));
        int dvInformado2 = Character.getNumericValue(dvsInformados.charAt(1));

        return dvInformado1 == dvCalculado1 && dvInformado2 == dvCalculado2;
    }

    /**
     * Calcula o dígito verificador (DV) de acordo com o peso do CNPJ.
     * Se primeiraRodada == true, usa os pesos do cálculo do primeiro dígito:
     *   pesos = [5,4,3,2,9,8,7,6,5,4,3,2]  (para 12 posições)
     * Se false, usa pesos para a segunda rodada:
     *   pesos = [6,5,4,3,2,9,8,7,6,5,4,3,2] (para 13 posições)
     *
     * Cada caractere é convertido para valor numérico: (int)ch - 48 (ASCII - 48).
     * Essa fórmula preserva 0..9 para dígitos e dá valores >=17 para letras (A=65 => 17).
     *
     * Retorna o dígito calculado (0..9) ou -1 em caso de erro.
     */
    private static int calcularDv(String caracteres, boolean primeiraRodada) {
        int[] pesos = primeiraRodada
                ? new int[]{5,4,3,2,9,8,7,6,5,4,3,2}   // 12 pesos
                : new int[]{6,5,4,3,2,9,8,7,6,5,4,3,2}; // 13 pesos

        if (caracteres == null) return -1;
        if (caracteres.length() != pesos.length) return -1;

        int soma = 0;
        for (int i = 0; i < caracteres.length(); i++) {
            char caractere = caracteres.charAt(i);
            // deve ser alfanumérico
            if (!Character.isLetterOrDigit(caractere)) return -1;

            int valor = ((int) caractere) - 48; // ASCII decimal menos 48 (conforme especificação)
            soma += valor * pesos[i];
        }

        int resto = soma % 11;
        int dv = 11 - resto;
        if (dv == 10 || dv == 11) dv = 0; // regra módulo 11 padrão para CNPJ

        return dv;
    }

    // método auxiliar (útil em logs/debug)
    public static String formatar(String entrada) {
        if (entrada == null) return null;
        String limpo = entrada.replaceAll("[^A-Za-z0-9]", "")
        limpo = limpo.toUpperCase();
        if (limpo.length() != 14) return limpo;
        // ex.: 12ABC34501DE35 -> 12.ABC.345/01DE-35 (apenas exemplo de formatação)
        return String.format("%s.%s.%s/%s-%s",
                limpo.substring(0,2),
                limpo.substring(2,5),
                limpo.substring(5,8),
                limpo.substring(8,12),
                limpo.substring(12));
    }
}
