package main.java.db;

public class ScriptExecutor {

    public static void main(String[] args) throws Exception {
        criarTabelas();
        importarOperadoras();
        importarDemonstracoes();
    }

    private static void criarTabelas() throws Exception {
        String dropOperadoras = "DROP TABLE IF EXISTS operadoras;";
        String createOperadoras = """
        CREATE TABLE operadoras (
            cod_ans             VARCHAR(20) PRIMARY KEY,
            razao_social        VARCHAR(255),
            cnpj                VARCHAR(20),
            modalidade          VARCHAR(100),
            logradouro          VARCHAR(255),
            numero_endereco     VARCHAR(20),
            complemento         VARCHAR(100),
            bairro              VARCHAR(100),
            cidade              VARCHAR(100),
            uf                  VARCHAR(2),
            cep                 VARCHAR(15),
            ddd                 VARCHAR(5),
            telefone            VARCHAR(20),
            fax                 VARCHAR(20),
            email               VARCHAR(255),
            representante       VARCHAR(255),
            cargo_representante VARCHAR(100),
            data_registro_ans   DATE
        );
    """;

        String dropDemonstracoes = "DROP TABLE IF EXISTS demonstracoes;";
        String createDemonstracoes = """
        CREATE TABLE demonstracoes (
            id SERIAL PRIMARY KEY,
            data DATE NOT NULL,
            reg_ans VARCHAR(20) NOT NULL,
            cd_conta_contabil VARCHAR(10) NOT NULL,
            descricao VARCHAR(255),
            vl_saldo_inicial DECIMAL(18,2),
            vl_saldo_final DECIMAL(18,2),
            dt_inclusao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
    """;

        System.out.println("Criando tabelas...");
        DataBaseUtil.runScript(dropOperadoras);
        DataBaseUtil.runScript(createOperadoras);
        DataBaseUtil.runScript(dropDemonstracoes);
        DataBaseUtil.runScript(createDemonstracoes);
        System.out.println("Tabelas criadas com sucesso!");
    }

    private static void importarOperadoras() throws Exception {
        String caminhoOperadoras = "C:\\Users\\gugai\\Downloads\\projetos_ans\\operadoras.csv";

        String sqlImportOperadoras =
                "LOAD DATA LOCAL INFILE '" + caminhoOperadoras.replace("\\", "\\\\") + "' " +
                        "INTO TABLE operadoras " +
                        "FIELDS TERMINATED BY ';' " +
                        "ENCLOSED BY '\"' " +
                        "LINES TERMINATED BY '\\n' " +
                        "IGNORE 1 LINES " +
                        "(cod_ans, razao_social, cnpj, modalidade, logradouro, numero_endereco, complemento, bairro, cidade, uf, cep, ddd, telefone, fax, email, representante, cargo_representante, data_registro_ans);";

        System.out.println("Importando operadoras...");
        DataBaseUtil.runScript(sqlImportOperadoras);
        System.out.println("Importação de operadoras concluída!");
    }

    private static void importarDemonstracoes() throws Exception {
        String caminhoDemonstracoes = "C:\\Users\\gugai\\Downloads\\projetos_ans\\desmonstrações.csv";

        String sqlImportDemonstracoes =
                "LOAD DATA LOCAL INFILE '" + caminhoDemonstracoes.replace("\\", "\\\\") + "' " +
                        "INTO TABLE demonstracoes " +
                        "FIELDS TERMINATED BY ';' " +
                        "ENCLOSED BY '\"' " +
                        "LINES TERMINATED BY '\\n' " +
                        "IGNORE 1 LINES " +
                        "(data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final);";

        System.out.println("Importando demonstracoes contábeis...");
        DataBaseUtil.runScript(sqlImportDemonstracoes);
        System.out.println("Importação de demonstracoes concluída!");
    }
}
