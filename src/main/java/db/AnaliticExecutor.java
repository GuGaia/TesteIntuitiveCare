package main.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AnaliticExecutor {

    private static final String URL = "jdbc:mysql://localhost:3306/projeto_ans?allowPublicKeyRetrieval=true&useSSL=false&allowLoadLocalInfile=true&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
             Statement stmt = connection.createStatement()) {

            System.out.println("=== Top 10 Operadoras - Último Trimestre ===");
            executarConsultaTrimestre(stmt);

            System.out.println("\n=== Top 10 Operadoras - Último Ano ===");
            executarConsultaAno(stmt);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executarConsultaTrimestre(Statement stmt) throws Exception {
        String sql = """
            WITH ultimo_trimestre AS (
                SELECT MAX(data) AS max_data FROM demonstracoes
            )
            SELECT 
                o.razao_social,
                d.reg_ans,
                SUM(d.vl_saldo_final) AS total_despesas
            FROM demonstracoes d
            JOIN operadoras o ON o.cod_ans = d.reg_ans
            JOIN ultimo_trimestre u ON QUARTER(d.data) = QUARTER(u.max_data) AND YEAR(d.data) = YEAR(u.max_data)
            WHERE d.descricao LIKE '%EVENTOS%'
              AND d.descricao LIKE '%SINISTROS%'
              AND d.descricao LIKE '%MEDICO%'
            GROUP BY o.razao_social, d.reg_ans
            ORDER BY total_despesas DESC
            LIMIT 10;
        """;

        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nome = rs.getString("razao_social");
                String regAns = rs.getString("reg_ans");
                double despesas = rs.getDouble("total_despesas");

                System.out.printf("Operadora: %-50s | Registro ANS: %-10s | Despesa: R$ %.2f%n", nome, regAns, despesas);
            }
        }
    }

    private static void executarConsultaAno(Statement stmt) throws Exception {
        String sql = """
            WITH ultimo_ano AS (
                SELECT MAX(YEAR(data)) AS ano FROM demonstracoes
            )
            SELECT 
                o.razao_social,
                d.reg_ans,
                SUM(d.vl_saldo_final) AS total_despesas
            FROM demonstracoes d
            JOIN operadoras o ON o.cod_ans = d.reg_ans
            JOIN ultimo_ano u ON YEAR(d.data) = u.ano
            WHERE d.descricao LIKE '%EVENTOS%'
              AND d.descricao LIKE '%SINISTROS%'
              AND d.descricao LIKE '%MEDICO%'
            GROUP BY o.razao_social, d.reg_ans
            ORDER BY total_despesas DESC
            LIMIT 10;
        """;

        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nome = rs.getString("razao_social");
                String regAns = rs.getString("reg_ans");
                double despesas = rs.getDouble("total_despesas");

                System.out.printf("Operadora: %-50s | Registro ANS: %-10s | Despesa: R$ %.2f%n", nome, regAns, despesas);
            }
        }
    }
}
