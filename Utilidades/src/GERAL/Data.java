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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Esta classe fornece utilidades para tratamento de Datas dos tipos
 * java.sql.Date e java.util.Date, fornecendo métodos fáceis para transformação
 * de datas em strings em formatos pré determinados ou o inverso, transformar
 * strings formatadas em data. Autor: Mateus Garcia github.com/NaturesProphet
 *
 * @author mgarcia
 */
public class Data {

    /**
     * Método que trata um objeto java.util.Date extraindo a informação de Data
     * no formato Brasileiro dd/mm/aaaa<br>
     * Utilize este método se você quer uma Data como string já formatada no
     * padrão brasileiro.
     *
     * @param d Objeto java.util.Date
     * @return Retorna uma String de Data formatada em dd/mm/aaaa
     * @author mgarcia
     * @throws Exception Caso o objeto não possa ser convertido para o formato
     * dd/MM/yyyy ou caso o objeto Data seja NULO.
     */
    public static String getDataAsStringBR(Date d) throws Exception {
        if (null != d) {
            SimpleDateFormat dmy = new SimpleDateFormat("dd/MM/yyyy");
            try {
                return dmy.format(d);
            } catch (Exception e) {
                throw new Exception("O objeto Data informado não pode ser "
                        + "convertido em dd/MM/yyyy\n" + e);
            }
        } else {
            throw new Exception("O objeto Data informado é NULO");
        }
    }

    /**
     * Este método trata um Objeto java.util.Date extraindo uma String de Data
     * formatada no padrão americano yyyy-MM-dd (separados por traços).<br>
     * Utilize este método se você precisa de uma String de Data formatada com o
     * padrão americano de Datas yyyy-MM-dd
     *
     * @param d Objeto java.util.Date
     * @return Retorna uma String de Data formatada em yyyy-MM-dd separado por
     * traços
     * @author mgarcia
     * @throws Exception Caso o objeto não possa ser convertido para o formato
     * dd/MM/yyyy ou caso o objeto Data seja NULO.
     */
    public static String getDataAsStringUS(Date d) throws Exception {
        if (null != d) {
            SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return ymd.format(d);
            } catch (Exception e) {
                throw new Exception("O objeto Data informado não pode ser "
                        + "convertido em yyyy-MM-dd\n" + e);
            }
        } else {
            throw new Exception("O objeto Data informado é NULO");
        }
    }

    /**
     * Este método trata um Objeto java.util.Date e retorna um Objeto
     * java.sql.Date com os mesmos dados do objeto informado.<br>
     * Utilize este método em Prepared Statements para simplificar o processo de
     * inserções de tipos DATETIME no banco de dados
     *
     * @author mgarcia
     * @param d Objeto java.util.Date
     * @return Objeto java.sql.Date com os dados da data d
     * @throws Exception Caso o objeto não possa ser convertido ou caso o objeto
     * Data seja NULO.
     */
    public static java.sql.Date getDataAsSQL(Date d) throws Exception {
        if (null != d) {
            try {
                return new java.sql.Date(d.getTime());
            } catch (Exception e) {
                throw new Exception("O objeto informado não pode ser convertido"
                        + " em java.sql.Date\n" + e);
            }
        } else {
            throw new Exception("O objeto Data informado é NULO");
        }
    }

    /**
     * Este método trata um Objeto java.sql.Date e retorna um Objeto
     * java.util.Date com os mesmos dados do objeto informado.<br>
     * Utilize este método por exemplo para extrair Datas de ResultSet's e
     * converte-las para o formato java.util.Date utilizado na aplicação
     *
     * @author mgarcia
     * @param d Objeto java.sql.Date
     * @return Objeto java.util.Date com os dados da data d
     * @throws Exception Caso o objeto não possa ser convertido ou caso o objeto
     * Data seja NULO.
     */
    public static Date getDataAsUtil(java.sql.Date d) throws Exception {
        if (null != d) {
            try {
                return new Date(d.getTime());
            } catch (Exception e) {
                throw new Exception("O objeto informado não pode ser convertido"
                        + " em java.util.Date\n" + e);
            }
        } else {
            throw new Exception("O objeto Data informado é NULO");
        }
    }

    /**
     * Este método trata uma String de Data formatada no padrão BR dd/mm/aaaa e
     * retorna um Objeto java.util.Date com os dados da String.<br>
     * Utilize este método para gerar Objetos java.util.Date a partir de Strings
     * formatadas no padrão brasileiro.
     * 
     * @author mgarcia
     * @param d String de Data formatada no padrão BR dd/mm/aaaa
     * @return Objeto java.util.Date com os dados da String informada.
     * @throws ParseException caso a String esteja em um formato inválido
     * (diferente de dd/MM/yyyy) ou for nula
     */
    public static Date getDataFromStringDMY(String d) throws ParseException {
        SimpleDateFormat dmy = new SimpleDateFormat("dd/MM/yyyy");
        return dmy.parse(d);
    }

    //devolve um java.util.Date pela string parão US fornecida
    /**
     * Este método trata uma String de Data formatada no padrão americano
     * yyyy-MM-dd e retorna um Objeto java.util.Date com os dados da String.<br>
     * Utilize este método para gerar Objetos java.util.Date a partir de Strings
     * formatadas no padrão americano.
     * 
     * @author mgarcia
     * @param d String de Data formatada no padrão americano yyyy-MM-dd
     * @return Objeto java.util.Date com os dados da String informada.
     * @throws ParseException caso a String esteja em um formato inválido
     * (diferente de yyyy-MM-dd) ou for nula
     */
    public static Date getDataFromStringYMD(String d) throws ParseException {
        SimpleDateFormat dmy = new SimpleDateFormat("yyyy-MM-dd");
        return dmy.parse(d);
    }

    /**
     * Este método cria um Objeto java.util.Date instantaneamente e retorna a
     * hora contida neste Objeto no momento que foi instanciado.<br>
     * Utilize este método para saber que horas são no momento que for invocado.
     *
     * @author mgarcia
     * @return String com a hora atual formatada em H:mm:ss
     */
    public static String getHora() {
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("H:mm:ss");
        return df.format(d);

    }

    //retorna data e hora no formato BR
    /**
     * Este método cria um Objeto java.util.Date instantaneamente e retorna a
     * data e a hora contida neste Objeto no momento que foi instanciado ja
     * formatados em português.<br>
     * Utilize este método para saber que dia e horas são no momento que for
     * invocado.
     *
     * @author mgarcia
     * @return String com Dia e data formatados em português no formato "dd' de
     * 'MMMMM' de 'yyyy' - 'HH':'mm'h'"
     */
    public static String getDataHoraBR() {
        Locale locale = new Locale("pt", "BR");
        Date agora = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'", locale);
        return formatador.format(agora.getTime());
    }

}