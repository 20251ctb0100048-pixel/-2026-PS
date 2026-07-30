import java.util.ArrayList;

public class aula29 {

    // Exercício 1
    static double calcularMedia(double[] notas) {
        double soma = 0;

        for (double nota : notas) {
            soma += nota;
        }

        return soma / notas.length;
    }

    // Exercício 2
    static int contarAprovados(double[] notas) {
        int quantidade = 0;

        for (double nota : notas) {
            if (nota >= 6.0) {
                quantidade++;
            }
        }

        return quantidade;
    }

    // Exercício 3
    static void adicionarProduto(ArrayList<String> lista, String nome) {
        lista.add(nome);
    }

    static void listarProdutos(ArrayList<String> lista) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i));
        }
    }

    // Exercício 4 (sobrecarga)
    static int maiorValor(int[] valores) {
        int maior = valores[0];

        for (int valor : valores) {
            if (valor > maior) {
                maior = valor;
            }
        }

        return maior;
    }

    static int maiorValor(int a, int b) {
        return (a > b) ? a : b;
    }

    // Exercício 5
    static void exibirBoletim(double[] notas) {
        double media = calcularMedia(notas);
        int aprovados = contarAprovados(notas);

        String situacao;

        if (media >= 6.0) {
            situacao = "APROVADA";
        } else {
            situacao = "EM RECUPERACAO";
        }

        System.out.println("Media: " + media);
        System.out.println("Aprovados: " + aprovados);
        System.out.println("Situacao: " + situacao);
    }

    // Desafio (conceito A)
    static int contarAcimaDaMedia(double[] notas) {
        double media = calcularMedia(notas);
        int quantidade = 0;

        for (double nota : notas) {
            if (nota > media) {
                quantidade++;
            }
        }

        return quantidade;
    }

    public static void main(String[] args) {

        System.out.println("=== Exercício 1 ===");
        double[] notas1 = {7.0, 8.0, 9.0};
        System.out.println("Média: " + calcularMedia(notas1));

        System.out.println();

        System.out.println("=== Exercício 2 ===");
        double[] notas2 = {7.0, 4.0, 9.0, 6.0};
        System.out.println("Aprovados: " + contarAprovados(notas2));

        System.out.println();

        System.out.println("=== Exercício 3 ===");
        ArrayList<String> produtos = new ArrayList<>();
        adicionarProduto(produtos, "Pizza");
        adicionarProduto(produtos, "Suco");
        listarProdutos(produtos);

        System.out.println();

        System.out.println("=== Exercício 4 ===");
        System.out.println(maiorValor(new int[]{3, 9, 5}));
        System.out.println(maiorValor(12, 7));

        System.out.println();

        System.out.println("=== Exercício 5 ===");
        double[] notas3 = {7.0, 5.0, 9.0, 6.0};
        exibirBoletim(notas3);

        System.out.println();

        System.out.println("=== Desafio ===");
        System.out.println("Acima da média: " + contarAcimaDaMedia(notas3));
    }
}