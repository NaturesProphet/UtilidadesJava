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
    //Classe que fornece utilidades para manipulação de arquivos de texto puro

    /**
     * Faz a leitura de um arquivo específico. Use este método para recuperar
     * SOMENTE a primeira linha de um arquivo
     *
     * @return Retorna a primeira Linha do arquivo designado
     * @author Mateus Garcia
     * @param path caminho para o arquivo especificado
     * @throws IOException
     *
     */
    public static String getFirstLine(String path) throws IOException {
        if (AreYouHere(path)) {
            BufferedReader buffRead = new BufferedReader(new FileReader(path));
            String linha = buffRead.readLine();
            buffRead.close();
            return linha;
        } else {
            throw new FileNotFoundException("O programa buscou por um arquivo "
                    + "que não foi encontrado no local especificado");
        }
    }

    /**
     * Este método SOBREESCREVE todo o conteúdo do arquivo designado e escreve
     * SOMENTE o conteúdo da String informada. Utilize este método com muito
     * cuidado, uma vez que ele apaga o conteúdo anterior do arquivo.
     *
     * @author Mateus Garcia
     * @param path caminho para o arquivo especificado
     * @param content Uma String com o conteúdo que se deseja gravar no arquivo
     * @throws IOException
     *
     */
    public static void setFileContentAsSingleLine(String path, String content)
            throws IOException {
        if (AreYouHere(path)) {
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
            buffWrite.append(content + "\n");
            buffWrite.close();
        } else {
            throw new FileNotFoundException("O programa buscou por um arquivo "
                    + "que não foi encontrado no local especificado");
        }
    }

    /**
     * Este método faz a leitura completa do arquivo especificado,
     * linha-a-linha, gera um ArrayList de String, contendo todas as linhas do
     * arquivo respeitando suas posições, mantendo o index do ArrayList igual ao
     * numero da linha, começando por 0. Use este método se você quer uma lista
     * com as linhas do arquivo
     *
     * @return Retorna um ArrayList contendo todas as linhas do arquivo
     * @author Mateus Garcia
     * @param path caminho para o arquivo especificado
     * @throws IOException
     * @throws FileNotFoundException
     *
     */
    public static ArrayList<String> getAllLines(String patch)
            throws FileNotFoundException, IOException {
        if (AreYouHere(patch)) {
            ArrayList<String> dados = new ArrayList();
            BufferedReader br = new BufferedReader(new FileReader(patch));
            String linha;
            Scanner scan = new Scanner(br);
            while (scan.hasNextLine()) {
                linha = scan.nextLine();
                dados.add(linha);
            }
            br.close();
            scan.close();
            return dados;
        } else {
            throw new FileNotFoundException("O programa buscou por um arquivo "
                    + "que não foi encontrado no local especificado");
        }
    }

    /**
     * Faz a leitura de um arquivo específico buscando por uma linha que começe
     * com a String informada como Codigo. Se o arquivo contiver mais de uma
     * linha que começe com o Código informado, ele lancará uma Exception
     * informando esta ocorrência. Este método foi desenvolvido para ser
     * utilizado para leitura de Arquivos de configuração genéricos. Utilize
     * este método se você deseja ler o conteúdo de apenas uma linha específica
     * dentro de um arquivo e sabe como esta linha se inicia.
     *
     * @return Retorna apenas a linha que começa com o código informado
     * @author Mateus Garcia
     * @param path caminho para o arquivo especificado
     * @param codigo String identica ao inicio da linha desejada.
     * @throws IOException
     * @throws Exception Caso mais de uma linha for encontrada com o código
     * @throws FileNotFoundException
     *
     */
    public static String getLineByCode(String path, String codigo)
            throws FileNotFoundException, IOException, Exception {
        if (AreYouHere(path)) {
            BufferedReader br = new BufferedReader(new FileReader(path));
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
            scan.close();
            if (contador == 0) {
                return null;
            } else {
                saida = saida.substring(codigo.length() + 1, saida.length());
                //corta a substring desde o final do codigo mais 1 ate o final.
                // o +1 serve para pular o espaço vazio após o codigo
                return saida;
            }
        } else {
            throw new FileNotFoundException("O programa buscou por um arquivo "
                    + "que não foi encontrado no local especificado");
        }
    }

    /**
     * Configura o texto de uma linha que comece com o codigo informado. Este
     * método é similar e oposto ao método getLineByCode. O método varre o
     * arquivo designado, localizando a linha que começa com o código informado.
     * Ao localizar esta linha, o conteudo da mesma é SOBREESCRITO mantendo-se
     * apenas o código inicial, somado do conteúdo informado no parametro. Assim
     * como o getLineByCode, este método também foi desenvolvido para trabalhar
     * com arquivos de configuração genéricos, salvando as configurações do
     * usuário para serem recuperadas mais tarde pelo getLineByCode.
     * #####################################<br>
     * <strong>CUIDADO</strong><br>
     * #
     * ####################################<br>
     * Este método SOBREESCREVE o conteúdo da linha designada pelo código.
     * Utilize este método com cautela. Use este método se você deseja
     * configurar o conteúdo de uma linha especifica do arquivo
     *
     * @author Mateus Garcia
     * @param path caminho para o arquivo especificado
     * @param Code String identica ao inicio da linha desejada
     * @param insert String com o conteúdo a ser posicionado após o Code
     * @throws IOException Se o arquivo contiver mais de uma linha com o Codigo
     * informado, uma IOException será lançada informando esta ocorrência.
     *
     *
     */
    public static void setLineByCode(String path, String Code, String insert) throws IOException {
        if (AreYouHere(path)) {

            ArrayList<String> AllLines = getAllLines(path);
            boolean IsRedundant = false;

            for (int i = 0; i < AllLines.size(); i++) {
                if (IsAComent(AllLines.get(i), '#')) {
                    continue;
                } else {
                    if (Code.equals(getFirstStringFromLine(AllLines.get(i)))) {
                        if (!IsRedundant) {
                            AllLines.set(i, Code + " " + insert);
                            IsRedundant = true;
                        } else {
                            throw new IOException("O programa tentou alterar"
                                    + " uma linha do arquivo " + path
                                    + " mas o codigo " + Code + " foi encontrado"
                                    + "mais de uma vez, causando um conflito"
                                    + "Por segurança, nenhuma "
                                    + "alteração foi feita.");
                        }
                    }
                }
            }
            OverWrite(path, AllLines);
        } else {
            throw new FileNotFoundException("O programa buscou por um arquivo "
                    + "que não foi encontrado no local especificado");
        }
    }

    /**
     * Use este método para verificar se um arquivo existe ou não.
     *
     * @return Retorna um boolean. Se o arquivo existe, retorna TRUE, caso
     * contrário, retorna FALSE.
     * @author Mateus Garcia
     * @param path caminho para o arquivo especificado
     *
     */
    public static boolean AreYouHere(String patch) {
        File file = new File(patch);
        return file.exists();
    }

    //verifica o primeiro caractere encontrado na linha
    //util para mecanismos de comentario em arquivo, para que o sistema
    //ignore as linhas que começam com um determinado caractere, 
    //como o '#', no caso do linux
    /**
     * Este método faz uma simples leitura de uma String (uma linha que foi
     * retirada de um arquivo qualquer por outros métodos) e verifica qual é o
     * primeiro caractere válido desta linha.<br>
     * Este método foi desenvolvido para auxiliar a leitura de arquivos de
     * configuração genéricos, com o objetivo de saber se a linha analisada é um
     * comentário ou uma linha de código válida.<br>
     * O método que invocar este, saberá se a linha é um comentário.<br>
     * Utilize este método se você deseja saber se a linha informada é um
     * comentário.
     *
     * @param linha String representando uma linha de um arquivo qualquer
     * @param coment Char indicador de comentários
     * @return Retorna um boolean informando se a linha se inicia com o
     * caractere informado. TRUE se sim, FALSE se não.
     * @author Mateus Garcia
     *
     */
    public static boolean IsAComent(String linha, char coment) {
        for (int i = 0; i < linha.length(); i++) {
            if (linha.charAt(i) != ' ') {
                return linha.charAt(i) == coment;
            }
        }
        return false;
    }

    /**
     * Método que ADICIONA uma linha no FINAL do arquivo informado. Utilize este
     * método para escrever um conteúdo no final de um arquivo sem alterar o
     * conteúdo anterior.
     *
     * @author Mateus Garcia
     * @param path caminho para o arquivo especificado
     * @param texto String com o conteúdo a ser escrito no final do arquivo.
     * @throws IOException
     * @throws FileNotFoundException caso o arquivo informado não exista
     *
     */
    public static void AddThisLineAtEOF(String path, String texto) throws IOException {
        if (AreYouHere(path)) {
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(texto);
            bw.newLine();
            bw.close();
        } else {
            throw new FileNotFoundException("O programa buscou por um arquivo "
                    + "que não foi encontrado no local especificado");
        }

    }

    /**
     * Método que ADICIONA uma linha no INICIO do arquivo informado. Utilize
     * este método para escrever um conteúdo no começo de um arquivo sem alterar
     * o conteúdo adiante.
     *
     * @author Mateus Garcia
     * @param path caminho para o arquivo especificado
     * @param texto String com o conteúdo a ser escrito no começo do arquivo.
     * @throws IOException
     * @throws FileNotFoundException caso o arquivo informado não exista
     *
     */
    public static void AddThisLineAtBOF(String path, String texto) throws IOException {
        if (AreYouHere(path)) {
            ArrayList<String> conteudoanterior = getAllLines(path);
            setFileContentAsSingleLine(path, texto);
            for (int i = 0; i < conteudoanterior.size(); i++) {
                AddThisLineAtEOF(path, conteudoanterior.get(i));
            }

        } else {
            throw new FileNotFoundException("O programa buscou por um arquivo "
                    + "que não foi encontrado no local especificado");
        }
    }

    /**
     * Método que retorna o primeiro conteúdo válido de uma String.<br>
     * Este método elimina possíveis espaços antes e depois da primeira
     * sequência de caractéres válida encontrada na linha, e termina no primeiro
     * espaço encontrado após o primeiro conteúdo.<br>
     * Utilize este método se você deseja saber qual é a primeira palavra de uma
     * frase.
     *
     * @author Mateus Garcia
     * @param linha Frase ou linha a ser analisada
     * @return retorna a primeira palavra ou sequência de caracteres válida
     * encontrada na linha.
     *
     */
    public static String getFirstStringFromLine(String linha) {
        StringBuilder sb = new StringBuilder(10);
        if (!linha.isEmpty()) {
            boolean PrimeiroEspaco = true;
            for (int i = 0; i < linha.length(); i++) {
                if (linha.charAt(i) == ' ' && PrimeiroEspaco) {
                    continue;
                } else {
                    PrimeiroEspaco = false;
                }
                if (!PrimeiroEspaco) {
                    if (linha.charAt(i) != ' ') {
                        sb.append(linha.charAt(i));
                    } else {
                        return sb.toString();
                    }
                }
            }
        }
        return sb.toString(); //se a linha for vazia retorna nada
    }

    //metodo para sobreescrever um arquivo com a lista de strings informada
    /**
     * ##########################<br>
     * CUIDADO! METODO PERIGOSO<br>
     * #
     * #########################<br>
     * Este Método SOBREESCREVE TODO O CONTEÚDO ANTERIOR do arquivo indicado,
     * substituindo o conteúdo anterior pelo conteúdo da lista de Strings
     * informada.<br>
     * Utilize este método para resetar um arquivo com as linhas default
     * informadas na lista.
     *
     * @author Mateus Garcia
     * @param path caminho para o arquivo especificado
     * @param Content ArrayList de Strings contendo a lista de linhas a serem
     * escritas no arquivo
     * @throws IOException caso o arquivo não seja encontrado ou não tenha
     * permissões de escrita
     *
     */
    public static void OverWrite(String path, ArrayList<String> Content) throws IOException {
        if (AreYouHere(path)) {
            setFileContentAsSingleLine(path, Content.get(0));
            for (int i = 1; i < Content.size(); i++) {
                AddThisLineAtEOF(path, Content.get(i));
            }
        } else {
            throw new FileNotFoundException("O programa buscou por um arquivo "
                    + "que não foi encontrado no local especificado");
        }
    }

    /**
     * Este método le um arquivo de texto contendo apenas numeros inteiros
     * separados por espaços e devolve um Array de Inteiros contendo estes
     * numeros na ordem em que se encontram no arquivo
     *
     * @author Mateus Garcia
     * @param patch String contendo o caminho completo para o arquivo
     * @throws Exception caso ocorra um erro ao ler o arquivo ou ao converter
     * Strings em numeros durante o processo
     * @return Array de inteiros int[] contendo os numeros inteiros lidos do
     * arquivo
     *
     */
    public static int[] getIntArray(String patch) throws Exception {

        ArrayList<String> content = getAllLines(patch); //pega o conteudo do arquivo

        ArrayList<Integer> vetor = new ArrayList();
        /* declara a lista que conterá os numeros que serão processados 
        a seguir e posteriormente será armazenada em um array de inteiros 
        e entregue como resultado */

        for (int i = 0; i < content.size(); i++) {
            String linha = content.get(i);
            String num = "";
            boolean lendo = false;
            for (int z = 0; z < linha.length(); z++) {
                if (linha.charAt(z) == ' ') {
                    if (lendo == false) {
                        continue;
                    } else {
                        int n = Integer.parseInt(num);
                        vetor.add(n);
                        num = "";
                        lendo = false;
                    }
                } else {
                    lendo = true;
                    num += linha.charAt(z);
                }
                /* ao fim da varredura, adiciona o ultimo valor válido lido na 
                lista. O fim da varredura implicaria na não inclusão deste numero,
                por isso este if foi adicionado, garantindo a integridade */
                if (z == linha.length() - 1) {
                    int n = Integer.parseInt(num);
                    vetor.add(n);
                }
            }
        }
        int[] Array = new int[vetor.size()];
        for (int i = 0; i < vetor.size(); i++) {
            Array[i] = vetor.get(i);
        }
        return Array;
    }


}
