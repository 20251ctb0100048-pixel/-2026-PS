package ADV.loja;

public class Produto {
    private String nome;
    private double preco;
    private int quantidade;

    // Construtor principal
    public Produto(String nome, double preco, int quantidade) {
        setNome(nome);
        setPreco(preco);
        setQuantidade(quantidade);
    }

    // Construtor alternativo (Desafio)
    public Produto(String nome, double preco) {
        this(nome, preco, 0);
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    // Setters com Validações
    public void setNome(String nome) {
        if (nome != null && !nome.isBlank()) {
            this.nome = nome;
        }
    }

    public void setPreco(double preco) {
        if (preco >= 0) {
            this.preco = preco;
        }
    }

    public void setQuantidade(int quantidade) {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        }
    }

    // Métodos de Comportamento
    public void adicionarEstoque(int quantidadeAdicionar) {
        if (quantidadeAdicionar > 0) {
            this.quantidade += quantidadeAdicionar;
        }
    }

    public boolean removerEstoque(int quantidadeRemover) {
        if (quantidadeRemover > 0 && quantidadeRemover <= this.quantidade) {
            this.quantidade -= quantidadeRemover;
            return true;
        }
        return false;
    }

    public double calcularValorEmEstoque() {
        return this.preco * this.quantidade;
    }

    // Resumo textual (Desafio)
    public String obterResumo() {
        return String.format("Produto: %s | Preço: R$ %.2f | Estoque: %d un | Valor Total: R$ %.2f", 
                nome, preco, quantidade, calcularValorEmEstoque());
    }
}