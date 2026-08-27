/*
 * Disciplina: Programacao Orientada a Objetos / Estrutura de Dados
 * Nome: ALCIDES DINIZ VEIGA
 * Data: 2026.08.27
 * Projeto: Projeto Secretaria (Aula 34)
 * Arquivo: Main.java
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Aluno> lista = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n==========================================");
            System.out.println(" SECRETARIA DO ALCIDES");
            System.out.println("==========================================");
            System.out.println("[1] Cadastrar aluno");
            System.out.println("[2] Listar alunos");
            System.out.println("[3] Buscar aluno");
            System.out.println("[4] Atualizar aluno");
            System.out.println("[5] Remover aluno");
            System.out.println("[6] Relatorio");
            System.out.println("[0] Sair");
            System.out.print("Sua escolha: ");

            String opcao = scanner.nextLine();

            if (opcao.equals("1")) {
                cadastrar();

            } else if (opcao.equals("2")) {
                listar();

            } else if (opcao.equals("3")) {
                buscar();

            } else if (opcao.equals("4")) {
                atualizar();

            } else if (opcao.equals("5")) {
                remover();

            } else if (opcao.equals("6")) {
                relatorio();

            } else if (opcao.equals("0")) {
                System.out.println("Secretaria fechada. Ate a proxima!");
                break;

            } else {
                System.out.println("Opcao invalida! Vale 0, 1, 2, 3, 4, 5 ou 6.");
            }
        }
    }

    static void cadastrar() {

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Matricula: ");
        String matricula = scanner.nextLine();

        // Verifica se a matricula ja existe
        Aluno existente = buscarPorMatricula(lista, matricula);

        if (existente != null) {
            System.out.println("Ja existe ficha com a matricula " + matricula + "!");
            return;
        }

        System.out.print("Curso: ");
        String curso = scanner.nextLine();

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        Aluno novo = new Aluno(nome, matricula, curso, cidade);

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
            System.out.println(aluno);
        }
    }

    static Aluno buscarPorMatricula(ArrayList<Aluno> lista, String matricula) {

        for (Aluno aluno : lista) {

            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }

        return null;
    }

    static void buscar() {

        System.out.print("Matricula procurada: ");
        String matricula = scanner.nextLine();

        Aluno a = buscarPorMatricula(lista, matricula);

        if (a == null) {
            System.out.println("Nenhuma ficha com a matricula " + matricula + ".");
            return;
        }

        System.out.println("Achei: " + a);
    }

    static void atualizar() {

        System.out.print("Matricula do aluno: ");
        String matricula = scanner.nextLine();

        Aluno a = buscarPorMatricula(lista, matricula);

        if (a == null) {
            System.out.println("Nenhuma ficha com a matricula " + matricula + ".");
            return;
        }

        System.out.println("Aluno encontrado: " + a.getNome());

        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();

        System.out.print("Novo curso: ");
        String novoCurso = scanner.nextLine();

        System.out.print("Nova cidade: ");
        String novaCidade = scanner.nextLine();

        a.setNome(novoNome);
        a.setCurso(novoCurso);
        a.setCidade(novaCidade);

        System.out.println("Ficha atualizada: " + a);
    }

    static void remover() {

        System.out.print("Matricula da ficha a remover: ");
        String matricula = scanner.nextLine();

        Aluno a = buscarPorMatricula(lista, matricula);

        if (a == null) {
            System.out.println("Nenhuma ficha com a matricula " + matricula + ".");
            return;
        }

        System.out.print("Tem certeza que remove " + a.getNome() + "? (s/n): ");
        String resposta = scanner.nextLine();

        if (resposta.equals("s")) {
            lista.remove(a);
            System.out.println("Ficha removida.");
        } else {
            System.out.println("Remocao cancelada.");
        }
    }

    static void relatorio() {

        System.out.println("\n--- RELATORIO DA SECRETARIA ---");
        System.out.println("Total de fichas: " + lista.size());

        System.out.print("Contar alunos de qual curso? ");
        String curso = scanner.nextLine();

        int contador = 0;

        for (Aluno aluno : lista) {
            if (aluno.getCurso().equals(curso)) {
                contador++;
            }
        }

        System.out.println("Alunos de " + curso + ": " + contador);
    }
}