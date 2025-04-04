package main.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBaseUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/projeto_ans?allowPublicKeyRetrieval=true&useSSL=false&allowLoadLocalInfile=true&serverTimezone=UTC"; // Ajuste
    private static final String USUARIO = "root"; // Ajuste
    private static final String SENHA = "root"; // Ajuste

    public static Connection conectar() throws Exception {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
    public static void runScript(String sql) throws Exception {
        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }
}