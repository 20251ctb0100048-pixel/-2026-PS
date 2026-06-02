import java.util.Scanner;

public class CardapioRestaurante {

    public static void main(String[] args) {
        
        Scanner entrada = new Scanner(System.in);
        
        // Exibe o nome do restaurante
        System.out.println("=========================================");
        System.out.println("       SABOR CENTRAL - CARDÁPIO          ");
        System.out.println("=========================================");
        // 5 itens obrigatórios
        System.out.println("1 - X-Burguer ............. R$ 18,00");
        System.out.println("2 - Pizza ................. R$ 35,00");
        System.out.println("3 - Suco Natural .......... R$ 8,00");
        System.out.println("4 - Café .................. R$ 5,00");
        System.out.println("5 - Batata Frita .......... R$ 12,00");
        System.out.println("=========================================");
        
        System.out.print("Escolha uma opção: ");
        int opcao = entrada.nextInt();
        
        // Variáveis para armazenar os dados do item escolhido
        String itemEscolhido = "";
        double precoUnitario = 0.0;
        boolean opcaoValida = true;
        
        // Estrutura de decisão para identificar o item e o preço
        if (opcao == 1) {
            itemEscolhido = "X-Burguer";
            precoUnitario = 18.00;
        } else if (opcao == 2) {
            itemEscolhido = "Pizza";
            precoUnitario = 35.00;
        } else if (opcao == 3) {
            itemEscolhido = "Suco Natural";
            precoUnitario = 8.00;
        } else if (opcao == 4) {
            itemEscolhido = "Café";
            precoUnitario = 5.00;
        } else if (opcao == 5) {
            itemEscolhido = "Batata Frita";
            precoUnitario = 12.00;
        } else {
            System.out.println("\n[ERRO] Opção inválida. Tente novamente.");
            opcaoValida = false;
        }
        
        // Processa o pedido apenas se a opção for válida
        if (opcaoValida) {
            System.out.print("Digite a quantidade desejada: ");
            int quantidade = entrada.nextInt();
            
            // Cálculos do desafio opcional
            double valorTotal = precoUnitario * quantidade;
            
            // Resumo final do pedido
            System.out.println("\n=========================================");
            System.out.println("           RESUMO DO PEDIDO              ");
            System.out.println("=========================================");
            System.out.println("Item: " + itemEscolhido);
            System.out.printf("Preço unitário: R$ %.2f\n", precoUnitario);
            System.out.println("Quantidade: " + quantidade);
            System.out.println("-----------------------------------------");
            System.out.printf("VALOR TOTAL: R$ %.2f\n", valorTotal);
            System.out.println("=========================================");
        }
        
        entrada.close();
    }
}
