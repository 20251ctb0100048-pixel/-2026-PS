import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Aluno> lista = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n==========================================");
            System.out.println("      SECRETARIA DO ALCIDES");
            System.out.println("==========================================");
            System.out.println("[1] Cadastrar aluno");
            System.out.println("[2] Listar alunos");
            System.out.println("[0] Sair");
            System.out.print("Sua escolha: ");

            String opcao = scanner.nextLine();

            if (opcao.equals("1")) {
                cadastrar();
            } else if (opcao.equals("2")) {
                listar();
            } else if (opcao.equals("0")) {
                System.out.println("Secretaria fechada. Ate a proxima!");
                break;
            } else {
                System.out.println("Opcao invalida! Vale 0, 1 ou 2.");
            }
        }
    }

    static void cadastrar() {

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Matricula: ");
        String matricula = scanner.nextLine();

        System.out.print("Curso: ");
        String curso = scanner.nextLine();

        Aluno novo = new Aluno(nome, matricula, curso);

        lista.add(novo);

        System.out.println("Ficha de " + nome + " arquivada!");
    }

    static void listar() {

        if (lista.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("\n--- FICHAS NO GAVETEIRO: " + lista.size() + " ---");

        for (Aluno aluno : lista) {
            System.out.println(
                    aluno.getMatricula() + " | "
                    + aluno.getNome() + " | "
                    + aluno.getCurso());
        }
    }
}