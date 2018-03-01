/*
 * Copyright (C) 2018 mgarcia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package GERAL;

import java.util.InputMismatchException;

/**
 *
 * @author mgarcia
 */
public class Cpf {

    /**
     * Este método faz algumas verificações para saber se um CPF é válido
     * analisando se a cadeia de caracteres é uma sequência de numeros iguais e
     * calculando os dois ultimos digitos.<br>
     * Utilize este método para validar campos de CPF que contenham apenas os 11
     * dígitos sem os pontos e traços.<br>
     * Este método é uma adaptação do original da DevMedia disponível em
     * https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097
     * acessado em 01/03/2018
     *
     * @author DevMedia, adaptado por mgarcia.
     * @param CPF String com o CPF contendo somente os 11 dígitos
     * @return boolean true se o CPF for válido, false se não.
     */
    public static boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0         
                // (48 eh a posicao de '0' na tabela ASCII)         
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    /**
     * Este método formata um CPF de 11 dígitos numéricos para o padrão formal
     * ddd.ddd.ddd-vv<br>
     * Utilize este método para tratar CPFs em estado bruto e exibi-los no
     * padrão formal.
     *
     *
     * @author mgarcia.
     * @param CPF String de 11 dígitos numéricos
     * @return String de CPF formatada no padrão formal ddd.ddd.ddd-vv
     */
    public static String printCPF(String CPF) {
        if (CPF.length() == 11) {
            return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "."
                    + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
        } else {
            return "CPF INVÁLIDO";
        }
    }

    /**
     * Este método retira de um CPF formatado uma string puramente numérica de
     * 11 dígitos. <br>
     * Utilize este método para armazenar CPFs no banco de dados de maneira mais
     * eficiente.
     *
     * @author mgarcia
     * @param CPF String de CPF no formato formal ddd.ddd.ddd-vv
     * @return String contendo o CPF puro com 11 dígitos sem pontuação.
     */
    public static String getIntCPF(String CPF) {
        if (CPF.length() == 14) {
            return (CPF.substring(0, 3) + CPF.substring(4, 7)
                    + CPF.substring(8, 11) + CPF.substring(12, 14));
        } else {
            return "CPF inválido";
        }
    }
}
