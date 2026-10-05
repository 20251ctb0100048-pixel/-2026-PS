package bibliotech;

public class TesteBiblioteca {
    public static void main(String[] args) {
        Biblioteca b = new Biblioteca();

        Livro l1 = new Livro("Dom Casmurro", "Machado de Assis", 1899);
        Livro l2 = new Livro("Capitaes da Areia", "Jorge Amado", 1937);
        b.cadastrarLivro(l1);
        b.cadastrarLivro(l2);

        // Criando leitores com os 3 parâmetros: Nome, Matrícula, Limite
        Leitor leitor1 = new Leitor("Ana", "2026011", 3);
        
        System.out.println("Cadastra Ana: " + b.cadastrarLeitor(leitor1));
        
        // Tentativa de cadastrar a mesma matrícula (Nível B)
        Leitor leitorDuplicado = new Leitor("Ana Duplicada", "2026011", 3);
        System.out.println("Mesma matricula de novo: " + b.cadastrarLeitor(leitorDuplicado));

        // Empréstimos
        b.emprestar("Dom Casmurro", "2026011");
        b.emprestar("Capitaes da Areia", "2026011");

        // Devolução
        b.devolver("Dom Casmurro", "2026011");

        // Listar livros da Ana (Nível A)
        b.listarLivrosDoLeitor("2026011");
    }
}
