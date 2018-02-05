/*
 * Copyright (C) 2018 mgarcia.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package GERAL;

/**
 *
 * @author mgarcia
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Arquivo {

    //lê a primeira linha de um arquivo informado pelo parâmetro
    public static String getFirstLine(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = buffRead.readLine();
        buffRead.close();
        return linha;
    }

    //escreve o conteúdo da String no arquivo, informados via parâmetro
    public static void setFileContent(String path, String content)
            throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        buffWrite.append(content + "\n");
        buffWrite.close();
    }

    //este método lê as linhas de um arquivo e retorna uma lista de Strings
    //recebe o caminho para o arquivo como parâmetro
    public static ArrayList<String> getAllLines(String patch)
            throws FileNotFoundException, IOException {
        ArrayList<String> dados = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(patch));
        String linha;
        Scanner scan = new Scanner(br);
        while (scan.hasNextLine()) {
            if (null != scan.nextLine()) {
                linha = scan.nextLine();
                dados.add(linha);
            }
        }
        br.close();
        return dados;
    }

    //retorna uma linha no arquivo que comece com a String informada.
    //a string retornada começa depois do final + 1 espaço do codigo
    public static String getLineByCode(String patch, String codigo)
            throws FileNotFoundException, IOException, Exception {
        BufferedReader br = new BufferedReader(new FileReader(patch));
        String linha; //linha atual de cada iteração
        int contador = 0; //total de ocorrências do codigo
        String saida = ""; //string que será retornada
        Scanner scan = new Scanner(br);

        while (scan.hasNextLine()) {
            linha = scan.nextLine();
            if (linha.indexOf(codigo) != -1) {
                if (contador > 0) { // se houver mais de uma ocorrência
                    br.close();
                    throw new Exception("Util.Arquivo: O Programa tentou "
                            + "buscar no arquivo por uma referência que se "
                            + "repete mais de uma vez e por isso ele não sabe "
                            + "qual delas usar. Contate o Desenvolvedor");
                } else {
                    saida = linha;
                    contador++;
                }
            }
        }
        br.close();
        if (contador == 0) {
            return null;
        } else {
            saida = saida.substring(codigo.length() + 1, saida.length());
            //corta a substring desde o final do codigo mais 1 ate o final.
            // o +1 serve para pular o espaço vazio após o codigo
            return saida;
        }
    }

    //verifica se o arquivo especificado existe
    public static boolean AreYouHere(String patch) {
        File file = new File(patch);
        return file.exists();
    }

    public static void main(String[] args) {
        //testes aqui
    }

}
