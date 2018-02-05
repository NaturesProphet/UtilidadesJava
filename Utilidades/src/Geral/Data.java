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
package Geral;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mgarcia
 */
public class Data {
    /*
    *   Esta classe fornece utilidades para tratamento de Datas dos tipos
    *   java.sql.Date e java.util.Date, fornecendo métodos fáceis para
    *   transformação de datas em strings em formatos pré determinados
    *   ou o inverso, transformar strings formatadas em data.
    */
    
    //devolve uma string formatada no padrão brasileiro
    public static String getDataAsStringBR(Date d) {
        SimpleDateFormat dmy = new SimpleDateFormat("dd/MM/yyyy");
        return dmy.format(d);
    }
    
    //devolve uma string formatada no padrão americano
    public static String getDataAsStringUS(Date d) {
        SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
        return ymd.format(d);
    }
    
    //devolve um java.sql.Date (para uso em PreparedStatements)
    public static java.sql.Date getDataAsSQL (Date d) {
        return new java.sql.Date(d.getTime());
    }
    
    //devolve um java.util.Date
    public static Date getDataAsUtil(java.sql.Date d) {
        return new Date(d.getTime());
    }
    //devolve um java.util.Date pela string parão BR fornecida
    public static Date getDataFromStringDMY(String d) throws ParseException {
        SimpleDateFormat dmy = new SimpleDateFormat("dd/MM/yyyy");
        return dmy.parse(d);
    }
    
}
