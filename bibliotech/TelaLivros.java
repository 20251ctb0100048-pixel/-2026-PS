import bibliotech.Livro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaLivros extends JFrame {

    private final LivroDAO livroDAO = new LivroDAO();

    private JTextField campoId;
    private JTable tabela;
    private DefaultTableModel modelo;
    private JLabel labelStatus;

    public TelaLivros() {
        setTitle("BiblioTech - Consulta de Livros");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        montarInterface();
        atualizarListagem();
    }

    private void montarInterface() {
        setLayout(new BorderLayout(10, 10));

        JPanel painelBusca = new JPanel(new FlowLayout());

        painelBusca.add(new JLabel("ID do livro:"));

        campoId = new JTextField(10);
        painelBusca.add(campoId);

        JButton botaoBuscar = new JButton("Buscar por ID");
        JButton botaoAtualizar = new JButton("Atualizar listagem");
        JButton botaoMostrarTodos = new JButton("Mostrar todos");

        painelBusca.add(botaoBuscar);
        painelBusca.add(botaoAtualizar);
        painelBusca.add(botaoMostrarTodos);

        add(painelBusca, BorderLayout.NORTH);

        modelo = new DefaultTableModel(
            new Object[]{"ID", "Título", "Autor", "Ano"},
            0
        ) {
            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };

        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        labelStatus = new JLabel("Pronto.");
        add(labelStatus, BorderLayout.SOUTH);

        botaoBuscar.addActionListener(e -> buscarPorId());
        botaoAtualizar.addActionListener(e -> atualizarListagem());
        botaoMostrarTodos.addActionListener(e -> atualizarListagem());
    }

    private void atualizarListagem() {
        modelo.setRowCount(0);

        List<Livro> livros = livroDAO.listarLivros();

        for (Livro livro : livros) {
            modelo.addRow(new Object[]{
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getAno()
            });
        }

        labelStatus.setText("Total de livros: " + livros.size());
    }

    private void buscarPorId() {
        String textoId = campoId.getText().trim();

        if (textoId.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Digite o ID do livro.",
                "Validação",
                JOptionPane.WARNING_MESSAGE
            );
            labelStatus.setText("Informe um ID.");
            return;
        }

        int id;

        try {
            id = Integer.parseInt(textoId);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "O ID deve ser um número inteiro.",
                "ID inválido",
                JOptionPane.WARNING_MESSAGE
            );
            labelStatus.setText("ID inválido.");
            return;
        }

        if (id <= 0) {
            JOptionPane.showMessageDialog(
                this,
                "O ID deve ser maior que zero.",
                "ID inválido",
                JOptionPane.WARNING_MESSAGE
            );
            labelStatus.setText("ID inválido.");
            return;
        }

        Livro livro = livroDAO.buscarLivroPorId(id);
        modelo.setRowCount(0);

        if (livro == null) {
            JOptionPane.showMessageDialog(
                this,
                "Nenhum livro encontrado com o ID " + id + ".",
                "Livro não encontrado",
                JOptionPane.INFORMATION_MESSAGE
            );
            labelStatus.setText("Livro não encontrado.");
            return;
        }

        modelo.addRow(new Object[]{
            livro.getId(),
            livro.getTitulo(),
            livro.getAutor(),
            livro.getAno()
        });

        labelStatus.setText("Livro encontrado.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaLivros tela = new TelaLivros();
            tela.setVisible(true);
        });
    }
}
