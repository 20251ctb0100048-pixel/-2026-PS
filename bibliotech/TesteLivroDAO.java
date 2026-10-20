import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteLivroDAO {

    public static void main(String[] args) {
        LivroDAO dao = new LivroDAO();

        System.out.println("=== ADICIONANDO LIVROS ===");

        System.out.println("Livro 1: " +
            dao.adicionarLivro("Dom Casmurro", "Machado de Assis", 1899));

        System.out.println("Livro 2: " +
            dao.adicionarLivro("O Pequeno Principe", "Antoine de Saint-Exupery", 1943));

        System.out.println("Livro 3: " +
            dao.adicionarLivro("A Hora da Estrela", "Clarice Lispector", 1977));

        System.out.println("\n=== LIVROS CADASTRADOS ===");
        listarLivros();

        System.out.println("\n=== ATUALIZANDO LIVRO ===");

        int idParaAtualizar = obterPrimeiroId();

        if (idParaAtualizar != -1) {
            System.out.println("Atualizacao: " +
                dao.atualizarLivro(idParaAtualizar,
                    "Dom Casmurro - Edicao Atualizada",
                    "Machado de Assis", 1899));
        }

        System.out.println("\n=== EXCLUINDO LIVRO ===");

        int idParaExcluir = obterUltimoId();

        if (idParaExcluir != -1) {
            System.out.println("Exclusao: " + dao.excluirLivro(idParaExcluir));
        }

        System.out.println("\n=== TESTANDO ID INEXISTENTE ===");
        System.out.println("Atualizacao ID 999999: " +
            dao.atualizarLivro(999999, "Livro Inexistente", "Autor", 2000));
        System.out.println("Exclusao ID 999999: " + dao.excluirLivro(999999));

        System.out.println("\n=== LIVROS FINAIS NO BANCO ===");
        listarLivros();
    }

    private static void listarLivros() {
        String sql = "SELECT * FROM livros";

        try (Connection conexao = Conexao.conectar();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                    "ID: " + rs.getInt("id") +
                    " | Titulo: " + rs.getString("titulo") +
                    " | Autor: " + rs.getString("autor") +
                    " | Ano: " + rs.getInt("ano")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }
    }

    private static int obterPrimeiroId() {
        return obterId("SELECT MIN(id) FROM livros");
    }

    private static int obterUltimoId() {
        return obterId("SELECT MAX(id) FROM livros");
    }

    private static int obterId(String sql) {
        try (Connection conexao = Conexao.conectar();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                int id = rs.getInt(1);
                return rs.wasNull() ? -1 : id;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter ID: " + e.getMessage());
        }

        return -1;
    }
}
