import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) {
        try (Connection conexao = Conexao.conectar()) {
            System.out.println("Conexao com o banco bibliotech realizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados:");
            e.printStackTrace();
        }
    }
}
