package bibliotech;

import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Livro> livros;
    private ArrayList<Leitor> leitores;
    private ArrayList<Emprestimo> emprestimos;

    public Biblioteca() {
        livros = new ArrayList<>();
        leitores = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public boolean cadastrarLeitor(Leitor leitor) {
        if (buscarLeitor(leitor.getMatricula()) != null) {
            return false;
        }
        leitores.add(leitor);
        return true;
    }

    public void listarAcervo() {
        System.out.println("=== ACERVO DA BIBLIOTECA ===");
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    public Livro buscarLivro(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public Leitor buscarLeitor(String matricula) {
        for (Leitor leitor : leitores) {
            if (leitor.getMatricula().equals(matricula)) {
                return leitor;
            }
        }
        return null;
    }

    public boolean emprestar(String titulo, String matricula) {
        Livro livro = buscarLivro(titulo);
        Leitor leitor = buscarLeitor(matricula);

        if (livro == null || leitor == null) {
            return false;
        }

        Emprestimo emprestimo = new Emprestimo(livro, leitor);
        emprestimos.add(emprestimo);
        return true;
    }

    public boolean devolver(String titulo, String matricula) {
        for (Emprestimo e : emprestimos) {
            if (e.getLivro().getTitulo().equalsIgnoreCase(titulo) && 
                e.getLeitor().getMatricula().equals(matricula)) {
                
                return true;
            }
        }
        return false;
    }

    public void listarEmprestimos() {
        System.out.println("=== HISTÓRICO DE EMPRÉSTIMOS ===");
        for (Emprestimo e : emprestimos) {
            System.out.println(e);
        }
    }

    public void listarLivrosDoLeitor(String matricula) {
        System.out.println("--- Livros com a matricula " + matricula + " ---");
        for (Emprestimo e : emprestimos) {
            if (e.getLeitor().getMatricula().equals(matricula)) {
                System.out.println(e.getLivro());
            }
        }
    }
}
