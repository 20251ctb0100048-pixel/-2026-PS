package bibliotech;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class TelaBiblioteca extends JFrame {
    private Biblioteca biblioteca;

    private JTextField campoTitulo;
    private JTextField campoMatricula;
    private JTextArea areaAcervo;

    public TelaBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;

        setTitle("BiblioTech");
        setSize(760, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        montarInterface();
    }

    private void montarInterface() {
        setLayout(new BorderLayout(10, 10));

        JPanel painelEntrada = new JPanel(new GridLayout(2, 2, 5, 5));

        painelEntrada.add(new JLabel("Título do livro:"));
        campoTitulo = new JTextField();
        painelEntrada.add(campoTitulo);

        painelEntrada.add(new JLabel("Matrícula do leitor:"));
        campoMatricula = new JTextField();
        painelEntrada.add(campoMatricula);

        add(painelEntrada, BorderLayout.NORTH);

        areaAcervo = new JTextArea();
        areaAcervo.setEditable(false);
        add(areaAcervo, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout());

        JButton botaoEmprestar = new JButton("Emprestar");
        JButton botaoDevolver = new JButton("Devolver");
        JButton botaoAtualizar = new JButton("Atualizar acervo");

        painelBotoes.add(botaoEmprestar);
        painelBotoes.add(botaoDevolver);
        painelBotoes.add(botaoAtualizar);

        add(painelBotoes, BorderLayout.SOUTH);

        botaoEmprestar.addActionListener(e -> emprestar());
        botaoDevolver.addActionListener(e -> devolver());
        botaoAtualizar.addActionListener(e -> atualizarAcervo());

        atualizarAcervo();
    }

    private void atualizarAcervo() {
        areaAcervo.setText(biblioteca.obterAcervoComoTexto());
    }

    private void emprestar() {
        String titulo = campoTitulo.getText().trim();
        String matricula = campoMatricula.getText().trim();

        if (titulo.isEmpty() || matricula.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Preencha o titulo e a matricula."
            );
            return;
        }

        boolean resultado = biblioteca.emprestar(titulo, matricula);
        atualizarAcervo();

        if (resultado) {
            JOptionPane.showMessageDialog(
                this,
                "Emprestimo realizado."
            );
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Emprestimo recusado. Confira o titulo, a matricula, "
                + "a disponibilidade e o limite do leitor."
            );
        }
    }

    private void devolver() {
        String titulo = campoTitulo.getText().trim();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Preencha o titulo do livro."
            );
            return;
        }

        boolean resultado = biblioteca.devolver(titulo);
        atualizarAcervo();

        if (resultado) {
            JOptionPane.showMessageDialog(
                this,
                "Devolucao registrada."
            );
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Nenhum emprestimo ativo encontrado para esse titulo."
            );
        }
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.cadastrarLivro(
            new Livro("Dom Casmurro", "Machado de Assis", 1899)
        );

        biblioteca.cadastrarLivro(
            new Livro("Capitaes da Areia", "Jorge Amado", 1937)
        );

        biblioteca.cadastrarLeitor(
            new Leitor("Pedro Alves", "2026010", 1)
        );

        biblioteca.cadastrarLeitor(
            new Leitor("Ana Lima", "2026011", 2)
        );

        SwingUtilities.invokeLater(() -> {
            TelaBiblioteca tela = new TelaBiblioteca(biblioteca);
            tela.setVisible(true);
        });
    }
}
