# Projeto Java - Processamento de Dados da ANS

Este projeto Java realiza o processamento de arquivos de dados da ANS, como:

- Download automático de arquivos da ANS (PDFs, Excel).
- Extração de dados de planilhas (.xlsx) para CSV.
- Compactação dos arquivos em zip.
- Criação e execução de scripts SQL para MySQL.
- Geração de relatórios analíticos de operadoras de plano de saúde.

## Tecnologias Utilizadas

- Java 11+
- Apache POI (manipulação de Excel)
- JDBC (conexão com banco de dados MySQL)
- JUnit (testes automatizados)

## Pré-requisitos

- Java 11 ou superior instalado
- MySQL Server (8.0 ou superior)
- Maven (opcional)

## Como Executar

1. Clone o projeto:
git clone https://github.com/seu-usuario/projeto-java-ans.git
2. Execute os testes
- Execute os testes da pasta testes realizar o download e compactação de todos os arquivos no repositório resources
- Configure o banco de dados no arquivo DataBaseUtil.java:
  
  private static final String URL = "jdbc:mysql://localhost:3306/projeto_ans";
  private static final String USUARIO = "root";
  private static final String SENHA = "sua_senha";
  
- Execute a classe principal ScriptExecutor.java para criar tabelas e importar os dados.
- Execute a classe principal AnaliticExecutor.java para realizar as analise dos dados.
