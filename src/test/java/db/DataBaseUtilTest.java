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
        assertNotNull("Conexão deve ser estabelecida", connection);
    }

    @After
    public void tearDown() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testConexao() throws Exception {
        assertFalse("Conexão deve estar aberta", connection.isClosed());
    }

    @Test
    public void testCreateTable() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TEMPORARY TABLE connection_test (id SERIAL PRIMARY KEY, nome VARCHAR(100));");
        }

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery("SELECT * FROM connection_test LIMIT 1;")) {
            assertTrue("A tabela temporária 'connection_test' deveria permitir SELECT", true);
        } catch (Exception e) {
            fail("Não foi possível acessar a tabela temporária 'connection_test'.");
        }
    }

    @Test
    public void testInserirEDepoisConsultar() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TEMPORARY TABLE teste_dados (id SERIAL PRIMARY KEY, nome VARCHAR(100));");
        }

        try (Statement stmt = connection.createStatement()) {
            stmt.execute("INSERT INTO teste_dados (nome) VALUES ('TesteNome');");
        }

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery("SELECT nome FROM teste_dados WHERE nome = 'TesteNome';")) {
            assertTrue("Deve encontrar o nome inserido", resultSet.next());
            assertEquals("Nome inserido deve ser 'TesteNome'", "TesteNome", resultSet.getString("nome"));
        }
    }
}
