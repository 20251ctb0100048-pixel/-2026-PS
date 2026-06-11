import java.util.Random;
import java.util.Scanner;

public class CardapioRestaurante {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        double total = 0;
        boolean continuar = true;

        int qtdXBurguer = 0;
        int qtdPizza = 0;
        int qtdBatata = 0;
        int qtdRefrigerante = 0;
        int qtdSorvete = 0;

        while (continuar) {

            System.out.println("\n=========================================");
            System.out.println("       SABOR CENTRAL - CARDÁPIO");
            System.out.println("=========================================");
            System.out.println("1 - X-Burguer ............. R$ 18,00");
            System.out.println("2 - Pizza ................. R$ 35,00");
            System.out.println("3 - Batata Frita .......... R$ 12,00");
            System.out.println("4 - Refrigerante .......... R$ 8,00");
            System.out.println("5 - Sorvete ............... R$ 10,00");
            System.out.println("6 - Finalizar Pedido");
            System.out.println("=========================================");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            if (opcao == 6) {

                System.out.println("\nO que deseja fazer?");
                System.out.println("1 - Modificar carrinho");
                System.out.println("2 - Finalizar compra");
                System.out.print("Escolha: ");

                int escolhaFinal = scanner.nextInt();

                if (escolhaFinal == 1) {
                    continue; // volta ao cardápio
                } else if (escolhaFinal == 2) {
                    break; // segue para resumo e pagamento
                } else {
                    System.out.println("Opção inválida!");
                    continue;
                }
            }

            System.out.print("Digite a quantidade desejada: ");
            int quantidade = scanner.nextInt();

            switch (opcao) {

                case 1:
                    qtdXBurguer += quantidade;
                    total += quantidade * 18;
                    System.out.println("X-Burguer adicionado ao pedido!");
                    break;

                case 2:
                    qtdPizza += quantidade;
                    total += quantidade * 35;
                    System.out.println("Pizza adicionada ao pedido!");
                    break;

                case 3:
                    qtdBatata += quantidade;
                    total += quantidade * 12;
                    System.out.println("Batata Frita adicionada ao pedido!");
                    break;

                case 4:
                    qtdRefrigerante += quantidade;
                    total += quantidade * 8;
                    System.out.println("Refrigerante adicionado ao pedido!");
                    break;

                case 5:
                    qtdSorvete += quantidade;
                    total += quantidade * 10;
                    System.out.println("Sorvete adicionado ao pedido!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        System.out.println("\n=========================================");
        System.out.println("           RESUMO DO PEDIDO");
        System.out.println("=========================================");

        if (qtdXBurguer > 0) {
            System.out.printf("%dx X-Burguer ........ R$ %.2f%n",
                    qtdXBurguer, qtdXBurguer * 18.0);
        }

        if (qtdPizza > 0) {
            System.out.printf("%dx Pizza ............ R$ %.2f%n",
                    qtdPizza, qtdPizza * 35.0);
        }

        if (qtdBatata > 0) {
            System.out.printf("%dx Batata Frita ..... R$ %.2f%n",
                    qtdBatata, qtdBatata * 12.0);
        }

        if (qtdRefrigerante > 0) {
            System.out.printf("%dx Refrigerante ..... R$ %.2f%n",
                    qtdRefrigerante, qtdRefrigerante * 8.0);
        }

        if (qtdSorvete > 0) {
            System.out.printf("%dx Sorvete .......... R$ %.2f%n",
                    qtdSorvete, qtdSorvete * 10.0);
        }

        System.out.println("-----------------------------------------");
        System.out.printf("TOTAL: R$ %.2f%n", total);

        System.out.println("\nForma de pagamento:");
        System.out.println("1 - Dinheiro");
        System.out.println("2 - Cartão");
        System.out.println("3 - PIX");
        System.out.print("Escolha: ");

        int pagamento = scanner.nextInt();

        if (pagamento == 1) {
            System.out.println("Pagamento em Dinheiro realizado com sucesso!");
        } else if (pagamento == 2) {
            System.out.println("Pagamento em Cartão realizado com sucesso!");
        } else if (pagamento == 3) {
            System.out.println("Pagamento via PIX realizado com sucesso!");
        } else {
            System.out.println("Forma de pagamento inválida!");
        }

        int numeroPedido = random.nextInt(900) + 100;

        System.out.println("\nPedido Nº " + numeroPedido);
        System.out.println("Aguarde a chamada do seu pedido.");

        scanner.close();
    }
}