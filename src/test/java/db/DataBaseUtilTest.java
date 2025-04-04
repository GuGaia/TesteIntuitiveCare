package test.java.db;

import main.java.db.DataBaseUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

public class DataBaseUtilTest {

    private Connection connection;

    @Before
    public void setUp() throws Exception {
        connection = DataBaseUtil.conectar();
        assertNotNull(connection);
    }

    @After
    public void tearDown() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testConexao() throws Exception {
        assertFalse(connection.isClosed());
    }

    @Test
    public void testCreateTable() throws Exception {
        String sql = "CREATE TEMP TABLE connection_test (id SERIAL PRIMARY KEY, nome VARCHAR(100));";
        DataBaseUtil.runScript(sql);

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(
                    "SELECT table_name FROM information_schema.tables WHERE table_name = 'teste_conexao';"
            );
            assertTrue(resultSet.next());
        }
    }

    @Test
    public void testInserirEDepoisConsultar() throws Exception {

        DataBaseUtil.runScript("CREATE TEMP TABLE teste_dados (id SERIAL PRIMARY KEY, nome VARCHAR(100));");

        DataBaseUtil.runScript("INSERT INTO teste_dados (nome) VALUES ('TesteNome');");

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery("SELECT nome FROM teste_dados WHERE nome = 'TesteNome';")) {
            assertTrue("Deve encontrar o nome inserido", resultSet.next());
            assertEquals("TesteNome", resultSet.getString("nome"));
        }
    }
}
