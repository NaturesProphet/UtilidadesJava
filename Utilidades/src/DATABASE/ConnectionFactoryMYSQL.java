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
package DATABASE;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * Esta classe se encarrega de abrir e entregar as conexões com o banco de dados
 * MySQL para a aplicação<br>
 * Autor: Mateus Garcia<br>
 * github.com/NaturesProphet
 *
 * @author mgarcia
 */
public class ConnectionFactoryMYSQL {

    private final String host = "seu_ip"; //INSIRA O IP DO BANCO
    private final int port = 0000; //INSIRA A PORTA (MySQL padrão = 3306)
    private final String user = "usuario"; //INSIRA O USUARIO DO BANCO
    private final String password = "senha"; // INSIRA A SENHA DO BANCO
    private final String db = "Schema"; //Nome do Schema.

    /* não encoste, não olhe, nem pense sobre esta linha abaixo...
    *  De preferência, leia-a com os olhos fechados.
     */
    private final String url = "jdbc:mysql://" + host + ":" + port + "/" + db;

    /**
     * Este método cria uma conexão com o Servidor MySQL, mas não a fecha.
     * Lembre-se de sempre fechar esta conexão manualmente ao terminar de
     * utiliza-la. O funcionamento correto deste método DEPENDE DO CONNECTOR
     * .jar para Java do MySQL adicionado as bibliotecas da aplicação<br>
     * Utilize este método para abrir uma conexão com o banco de dados.
     *
     * @author mgarcia
     * @return Objeto java.sql.Connection capaz de se comunicar com o servidor.
     * Pode retornar NULL se a configuração da URL estiver errada ou o servidor
     * estiver offline
     */
    public Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return (DriverManager.getConnection(url, user, password));
        } catch (Exception e) {
            System.out.println("ConnectinFactory: "
                    + "Erro ao tentar abrir a conexão" + e);
            return null;
        }
    }

}
