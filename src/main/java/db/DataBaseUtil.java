package main.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBaseUtil {

    private static final String URL = "jdbc:postgresql://localhost:5432/database"; // Ajuste
    private static final String USUARIO = "user"; // Ajuste
    private static final String SENHA = "password"; // Ajuste

    public static Connection conectar() throws Exception {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
    public static void runScript(String sql) throws Exception {
        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }
}