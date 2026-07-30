package ADV.loja;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== EXECUÇÃO DOS TESTES — SYSCONTROL v2.0 ===\n");

        // TESTE 1: Instanciação válida (Substitua "MA" e "15" pelos seus dados se quiser)
        Produto p1 = new Produto("MA - Mouse Gamer", 150.00, 15);
        Produto p2 = new Produto("MA - Teclado Mecânico", 250.00, 10);
        Produto p3 = new Produto("MA - Monitor", 900.00, 5);

        System.out.println("--- Teste 1: Estado Inicial dos Objetos ---");
        System.out.println(p1.obterResumo());
        System.out.println(p2.obterResumo());
        System.out.println(p3.obterResumo());

        // TESTE 2: Tentativa inválida — Nome vazio
        System.out.println("\n--- Teste 2: Atribuir Nome Vazio ---");
        p1.setNome(""); 
        System.out.println("Nome após tentar mudar para vazio: " + p1.getNome());

        // TESTE 3: Tentativa inválida — Preço negativo
        System.out.println("\n--- Teste 3: Atribuir Preço Negativo ---");
        p1.setPreco(-50.00);
        System.out.println("Preço após tentar mudar para -50: R$ " + p1.getPreco());

        // TESTE 4: Comportamento permitido
        System.out.println("\n--- Teste 4: Adicionar e Remover Estoque Válido ---");
        p1.adicionarEstoque(5);
        boolean removeu = p1.removerEstoque(3);
        System.out.println("Remoção realizada com sucesso? " + removeu);

        // TESTE 5: Comportamento impossível
        System.out.println("\n--- Teste 5: Remover Estoque Insuficiente ---");
        boolean removeuExcesso = p1.removerEstoque(100);
        System.out.println("Remoção realizada? " + removeuExcesso);

        // Estado Final
        System.out.println("\n--- Estado Final do Objeto 1 ---");
        System.out.println(p1.obterResumo());
    }
}