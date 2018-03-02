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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe para validação de E-mails
 * 
 * @author "natureza"
 */
public class Email {

    /**
     * Utilize este método para validar campos de e-mail
     * <br>
     * Autor: Usuário "natureza" do Forum Guj. Código original disponível em
     * http://www.guj.com.br/t/validar-email-do-usuario/138604 Acessado em
     * 02/03/2018
     *
     * @author natureza
     * @param email String com um e-mail a ser analisado
     * @return TRUE se for válido, false se não.
     */
    public static boolean validar(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }
}
