package Banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public Connection Conexao() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:8889/agenda?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false",
                "usuario", "senha");
    }
}
