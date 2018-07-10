package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBanco {

    public static Connection getConexao() throws ClassNotFoundException {
        Connection conexao = null;
        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bdvitalclin", "postgres", "postgres");
        
        } catch (SQLException erro2) {
            throw new RuntimeException(erro2);
        }
        return conexao;
    }

}
