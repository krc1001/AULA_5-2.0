import java.sql.*;
import java.util.List;

public class Clientes {
    private static final String URL ="jdbc:sqlite:banco.db" ;
    private Connection connection;

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);
            connection.setAutoCommit(false);
            System.out.println("Conexão realizada! Banco de dados: " + URL);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão fechada.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Clientes (ID INTEGER PRIMARY KEY, Nome VARCHAR, Idade INTEGER)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            connection.commit();
            System.out.println("Tabela criada ou já existe.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void adicionarVarios(List<Cliente> clientesList) {
        String sql = "INSERT INTO Clientes (Nome, Idade) VALUES (?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            for (Cliente cliente : clientesList) {
                insertStatement.setString(1, cliente.getNome());
                insertStatement.setInt(2, cliente.getIdade());
                insertStatement.addBatch();
            }
            insertStatement.executeBatch();
            connection.commit();
            System.out.println("Clientes inseridos.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir clientes: " + e.getMessage());
        }
    }

    public void retornarMaioresDeIdade() {
        String sql = "SELECT * FROM Clientes WHERE Idade >= 18";
        try (PreparedStatement selectStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = selectStatement.executeQuery();
            System.out.println("Clientes maiores de idade:");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nome = resultSet.getString("Nome");
                int idade = resultSet.getInt("Idade");
                System.out.println("Cliente ID: " + id + ", Nome: " + nome + ", Idade: " + idade);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar clientes: " + e.getMessage());
        }
    }

    public void sinalizarMenoresDeIdade() {
        String sql = "UPDATE Clientes SET Nome = Nome || ' (menor)' WHERE Idade < 18";
        try (PreparedStatement updateStatement = connection.prepareStatement(sql)) {
            updateStatement.executeUpdate();
            connection.commit();
            System.out.println("Clientes menores de idade sinalizados.");
        } catch (SQLException e) {
            System.out.println("Erro ao sinalizar menores de idade: " + e.getMessage());
        }
    }

    public void limparBancoDeDados() {
        String sql = "DELETE FROM Clientes WHERE Idade < 18";
        try (PreparedStatement deleteStatement = connection.prepareStatement(sql)) {
            deleteStatement.executeUpdate();
            connection.commit();
            System.out.println("Clientes menores de idade removidos.");
        } catch (SQLException e) {
            System.out.println("Erro ao limpar banco de dados: " + e.getMessage());
        }
    }

    public void deleteClient(int id) {
        try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Clientes WHERE ID = ?")) {
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
            connection.commit();
            System.out.println("Cliente deletado.");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar cliente: " + e.getMessage());
        }
    }
}

class Cliente {
    private String nome;
    private int idade;

    public Cliente(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }
}