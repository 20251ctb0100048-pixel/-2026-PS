import bibliotech.Livro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.List;

public class TelaLivros extends JFrame {

    private final LivroDAO livroDAO = new LivroDAO();

    private JTextField campoId;
    private JTable tabela;
    private DefaultTableModel modelo;
    private JLabel labelStatus;

    public TelaLivros() {
        setTitle("BiblioTech - Consulta de Livros");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        montarInterface();
        atualizarListagem();
    }

    private void montarInterface() {
        setLayout(new BorderLayout(10, 10));

        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 12));
        painelBusca.setBackground(new Color(230, 238, 245));

        painelBusca.add(new JLabel("ID do livro:"));

        campoId = new JTextField(8);
        painelBusca.add(campoId);

        JButton botaoBuscar = new JButton("Buscar por ID");
        JButton botaoAtualizar = new JButton("Atualizar listagem");
        JButton botaoMostrarTodos = new JButton("Mostrar todos");
        JButton botaoCapa = new JButton("Selecionar capa");

        painelBusca.add(botaoBuscar);
        painelBusca.add(botaoAtualizar);
        painelBusca.add(botaoMostrarTodos);
        painelBusca.add(botaoCapa);

        add(painelBusca, BorderLayout.NORTH);

        modelo = new DefaultTableModel(
            new Object[]{"ID", "Capa", "Título", "Autor", "Ano"},
            0
        ) {
            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };

        tabela = new JTable(modelo);
        tabela.setRowHeight(85);
        tabela.setFont(new Font("Arial", Font.PLAIN, 13));
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tabela.getTableHeader().setBackground(new Color(45, 75, 105));
        tabela.getTableHeader().setForeground(Color.WHITE);

        tabela.getColumnModel().getColumn(1).setCellRenderer(
            new RenderizadorCapa()
        );

        tabela.getColumnModel().getColumn(0).setPreferredWidth(45);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(75);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(220);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(180);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(60);

        add(new JScrollPane(tabela), BorderLayout.CENTER);

        labelStatus = new JLabel("Pronto.");
        labelStatus.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        add(labelStatus, BorderLayout.SOUTH);

        botaoBuscar.addActionListener(e -> buscarPorId());
        botaoAtualizar.addActionListener(e -> atualizarListagem());
        botaoMostrarTodos.addActionListener(e -> atualizarListagem());
        botaoCapa.addActionListener(e -> selecionarCapa());
    }

    private void atualizarListagem() {
        modelo.setRowCount(0);

        List<Livro> livros = livroDAO.listarLivros();

        for (Livro livro : livros) {
            modelo.addRow(new Object[]{
                livro.getId(),
                livro.getCapa(),
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
            livro.getCapa(),
            livro.getTitulo(),
            livro.getAutor(),
            livro.getAno()
        });

        labelStatus.setText("Livro encontrado.");
    }

    private void selecionarCapa() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Selecione um livro na tabela primeiro.",
                "Nenhum livro selecionado",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int linhaModelo = tabela.convertRowIndexToModel(linha);
        int id = (int) modelo.getValueAt(linhaModelo, 0);

        JFileChooser seletor = new JFileChooser();
        seletor.setDialogTitle("Escolha a capa do livro");

        seletor.setFileFilter(
            new FileNameExtensionFilter(
                "Imagens (JPG, JPEG, PNG, GIF)",
                "jpg", "jpeg", "png", "gif"
            )
        );

        int resultado = seletor.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivo = seletor.getSelectedFile();

            try {
                String caminho = ImagemLivro.salvar(arquivo);

                if (livroDAO.atualizarCapa(id, caminho)) {
                    atualizarListagem();

                    JOptionPane.showMessageDialog(
                        this,
                        "Capa salva com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                    );

                    labelStatus.setText("Capa atualizada para o livro ID " + id);
                } else {
                    JOptionPane.showMessageDialog(
                        this,
                        "Não foi possível atualizar a capa no banco.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar imagem: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private class RenderizadorCapa extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(
            JTable tabela,
            Object valor,
            boolean selecionado,
            boolean foco,
            int linha,
            int coluna
        ) {
            super.getTableCellRendererComponent(
                tabela, valor, selecionado, foco, linha, coluna
            );

            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
            setIcon(null);
            setText("Sem capa");

            if (valor != null && !valor.toString().isEmpty()) {
                File arquivo = new File(valor.toString());

                if (arquivo.exists()) {
                    ImageIcon imagem = new ImageIcon(valor.toString());

                    Image miniatura = imagem.getImage().getScaledInstance(
                        55, 75, Image.SCALE_SMOOTH
                    );

                    setIcon(new ImageIcon(miniatura));
                    setText("");
                }
            }

            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaLivros tela = new TelaLivros();
            tela.setVisible(true);
        });
    }
}
