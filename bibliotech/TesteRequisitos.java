package bibliotech;

public class TesteRequisitos {

    static int passaram = 0;
    static int falharam = 0;

    static void verificar(String nome, boolean condicao) {
        if (condicao) {
            System.out.println("OK      " + nome);
            passaram++;
        } else {
            System.out.println("FALHOU  " + nome);
            falharam++;
        }
    }

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        Livro dom = new Livro("Dom Casmurro", "Machado de Assis", 1899);
        Livro capitaes = new Livro("Capitaes da Areia", "Jorge Amado", 1937);

        Leitor ana = new Leitor("Ana Lima", "2026011", 1);
        Leitor pedro = new Leitor("Pedro Alves", "2026012", 1);

        biblioteca.cadastrarLivro(dom);
        biblioteca.cadastrarLivro(capitaes);
        biblioteca.cadastrarLeitor(ana);
        biblioteca.cadastrarLeitor(pedro);

        // RF01
        verificar(
            "RF01 livro cadastrado aparece na busca",
            biblioteca.buscarLivro("Dom Casmurro") != null
        );

        // RF02
        verificar(
            "RF02 leitor cadastrado aparece na busca",
            biblioteca.buscarLeitor("2026011") != null
        );

        // RF03
        verificar(
            "RF03 livro novo esta disponivel",
            dom.estaDisponivel()
        );

        verificar(
            "RF03 titulo fora do acervo nao e encontrado",
            biblioteca.buscarLivro("O Cortico") == null
        );

        // RF05
        verificar(
            "RF05 emprestimo de livro disponivel e aceito",
            biblioteca.emprestar("Dom Casmurro", "2026012")
        );

        verificar(
            "RF05 livro emprestado fica indisponivel",
            !dom.estaDisponivel()
        );

        verificar(
            "RF05 leitor passa a ter 1 livro em maos",
            pedro.getLivrosEmMaos() == 1
        );

        verificar(
            "RF05 livro emprestado e recusado para outro leitor",
            !biblioteca.emprestar("Dom Casmurro", "2026011")
        );

        verificar(
            "RF05 leitor no limite e recusado",
            !biblioteca.emprestar("Capitaes da Areia", "2026012")
        );

        // RF04
        verificar(
            "RF04 devolucao de emprestimo ativo e aceita",
            biblioteca.devolver("Dom Casmurro")
        );

        verificar(
            "RF04 livro devolvido volta a ficar disponivel",
            dom.estaDisponivel()
        );

        verificar(
            "RF04 segunda devolucao e recusada",
            !biblioteca.devolver("Dom Casmurro")
        );

        System.out.println();
        System.out.println(passaram + " passaram, " + falharam + " falharam.");
    }
}