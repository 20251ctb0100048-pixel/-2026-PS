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
        setTitle("BiblioTech | Gerenciamento de Livros");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        montarInterface();
        atualizarListagem();
    }

    private void montarInterface() {
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 244, 248));

        // 1. Painel Superior de Pesquisa (Fundo claro suave)
        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        painelBusca.setBackground(new Color(240, 244, 248));

        JLabel labelPesquisar = new JLabel("Pesquisar:");
        labelPesquisar.setFont(new Font("SansSerif", Font.PLAIN, 13));
        labelPesquisar.setForeground(new Color(80, 90, 100));
        painelBusca.add(labelPesquisar);

        campoId = new JTextField(12);
        campoId.setFont(new Font("SansSerif", Font.PLAIN, 13));
        campoId.setPreferredSize(new Dimension(150, 32));
        painelBusca.add(campoId);

        JButton botaoBuscar = new JButton("Buscar");
        JButton botaoAtualizar = new JButton("Atualizar");
        JButton botaoMostrarTodos = new JButton("Mostrar todos");
        JButton botaoCapa = new JButton("Selecionar capa");

        Color corAzulBotao = new Color(53, 89, 153);
        JButton[] botoes = {botaoBuscar, botaoAtualizar, botaoMostrarTodos, botaoCapa};
        for (JButton b : botoes) {
            b.setBackground(corAzulBotao);
            b.setForeground(Color.WHITE);
            b.setFont(new Font("SansSerif", Font.BOLD, 12));
            b.setFocusPainted(false);
            b.setPreferredSize(new Dimension(b.getPreferredSize().width, 32));
            painelBusca.add(b);
        }

        add(painelBusca, BorderLayout.NORTH);

        // 2. Modelo da Tabela
        modelo = new DefaultTableModel(
            new Object[]{"Capa", "Título", "Autor", "Ano", "ID"},
            0
        ) {
            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };

        // Paleta exata do Protótipo
        Color corFundoCabecalho = new Color(42, 48, 60);   // #2A303C
        Color corLinha1 = new Color(47, 54, 66);           // #2F3642
        Color corLinha2 = new Color(53, 61, 74);           // #353D4A

        tabela = new JTable(modelo);
        tabela.setRowHeight(90);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabela.setBackground(corLinha1);
        tabela.setForeground(Color.WHITE);
        tabela.setSelectionBackground(new Color(75, 88, 110));
        tabela.setSelectionForeground(Color.WHITE);
        
        // Remove as linhas de grade para ficar idêntico ao protótipo
        tabela.setShowGrid(false);
        tabela.setIntercellSpacing(new Dimension(0, 0));

        // Cabeçalho da Tabela
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        tabela.getTableHeader().setBackground(corFundoCabecalho);
        tabela.getTableHeader().setForeground(Color.WHITE);
        tabela.getTableHeader().setPreferredSize(new Dimension(100, 38));
        tabela.getTableHeader().setOpaque(true);

        // Renderizador para texto (linhas alternadas sem grade)
        DefaultTableCellRenderer renderizadorTexto = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? corLinha1 : corLinha2);
                }
                c.setForeground(Color.WHITE);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

                if (column == 3 || column == 4) {
                    setHorizontalAlignment(RIGHT);
                } else {
                    setHorizontalAlignment(LEFT);
                }
                return c;
            }
        };

        tabela.getColumnModel().getColumn(1).setCellRenderer(renderizadorTexto);
        tabela.getColumnModel().getColumn(2).setCellRenderer(renderizadorTexto);
        tabela.getColumnModel().getColumn(3).setCellRenderer(renderizadorTexto);
        tabela.getColumnModel().getColumn(4).setCellRenderer(renderizadorTexto);

        // Renderizador da Capa (Coluna 0)
        tabela.getColumnModel().getColumn(0).setCellRenderer(new RenderizadorCapa(corLinha1, corLinha2));

        // Dimensionamento das colunas conforme o protótipo (Capa, Título, Autor, Ano)
        tabela.getColumnModel().getColumn(0).setPreferredWidth(90);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(220);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(40);

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.getViewport().setBackground(corLinha1);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        painelCentral.setBackground(new Color(240, 244, 248));
        painelCentral.add(scrollPane, BorderLayout.CENTER);

        add(painelCentral, BorderLayout.CENTER);

        // 3. Status rodapé
        labelStatus = new JLabel("Pronto.");
        labelStatus.setFont(new Font("SansSerif", Font.PLAIN, 12));
        labelStatus.setForeground(new Color(100, 110, 120));
        labelStatus.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        add(labelStatus, BorderLayout.SOUTH);

        // Ações dos Botões
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
                livro.getCapa(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getAno(),
                livro.getId()
            });
        }
        labelStatus.setText(livros.size() + " livros apresentados");
    }

    private void buscarPorId() {
        String textoId = campoId.getText().trim();
        if (textoId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o ID do livro.", "Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(textoId);
            Livro livro = livroDAO.buscarLivroPorId(id);
            modelo.setRowCount(0);

            if (livro != null) {
                modelo.addRow(new Object[]{
                    livro.getCapa(),
                    livro.getTitulo(),
                    livro.getAutor(),
                    livro.getAno(),
                    livro.getId()
                });
                labelStatus.setText("Livro encontrado.");
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum livro encontrado com o ID " + id + ".", "Não Encontrado", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O ID deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void selecionarCapa() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um livro na tabela primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modelo.getValueAt(linha, 4);
        JFileChooser seletor = new JFileChooser();
        seletor.setDialogTitle("Escolha a capa do livro");
        seletor.setFileFilter(new FileNameExtensionFilter("Imagens (JPG, PNG)", "jpg", "jpeg", "png", "gif"));

        if (seletor.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                String caminho = ImagemLivro.salvar(seletor.getSelectedFile());
                if (livroDAO.atualizarCapa(id, caminho)) {
                    atualizarListagem();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar imagem: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class RenderizadorCapa extends DefaultTableCellRenderer {
        private final Color cor1, cor2;

        public RenderizadorCapa(Color cor1, Color cor2) {
            this.cor1 = cor1;
            this.cor2 = cor2;
        }

        @Override
        public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean foco, int linha, int coluna) {
            super.getTableCellRendererComponent(tabela, valor, selecionado, foco, linha, coluna);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
            setIcon(null);
            setText("");

            if (!selecionado) {
                setBackground(linha % 2 == 0 ? cor1 : cor2);
            }

            if (valor != null && !valor.toString().isEmpty()) {
                File arquivo = new File(valor.toString());
                if (arquivo.exists()) {
                    ImageIcon imagem = new ImageIcon(valor.toString());
                    Image miniatura = imagem.getImage().getScaledInstance(50, 70, Image.SCALE_SMOOTH);
                    setIcon(new ImageIcon(miniatura));
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