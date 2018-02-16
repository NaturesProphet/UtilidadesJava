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
package DATABASE;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Esta classe se encarrega de abrir e entregar as conexões com o banco de dados
 * SQLITE para a aplicação.<br>
 * Autor: Mateus Garcia<br>
 * github.com/NaturesProphet
 *
 * @author mgarcia
 */
public class ConnectionFactorySQLITE {

    private String patch;
    private String url = "jdbc:sqlite:" + patch;

    public ConnectionFactorySQLITE() throws IOException {
        patch = "caminho_para_o_arquivo.db"; //insira o caminho do arquivo .db

        /* não encoste, não olhe, nem pense sobre esta linha abaixo...
        *  De preferência, leia-a com os olhos fechados.
         */
        url = "jdbc:sqlite:" + patch;
    }

    /**
     * Este método cria uma conexão com o arquivo de Banco de Dados local
     * SQLITE3, mas não a fecha. Lembre-se de sempre fechar esta conexão
     * manualmente ao terminar de utiliza-la. O funcionamento correto deste
     * método DEPENDE DO CONNECTOR .jar para Java do SQLITE3 adicionado as
     * bibliotecas da aplicação<br>
     * Utilize este método para abrir uma conexão com o arquivo de banco de
     * dados local .db do SQLITE3.
     *
     * @author mgarcia
     * @return Objeto java.sql.Connection capaz de se comunicar com o arquivo
     * .db do Banco de Dados local SQLITE3. Pode retornar NULL se a configuração
     * da URL estiver errada ou o arquivo for inválido
     */
    public Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(url);
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactorySQLITE.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Nao conectou ao sqlite\n" + ex);
        }
        return null;
    }
}
