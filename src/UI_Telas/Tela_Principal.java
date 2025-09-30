package src.UI_Telas;

import src.Entidades.Cliente;
import src.Entidades.ListaClientes;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.List;

public class Tela_Principal implements ActionListener {
    private JFrame frame;
    private JPanel panelTopo;
    private JPanel panelMenu;
    private JPanel panelConteudo;
    private JPanel panelBotoes;
    private JLabel titulo;
    private ImageIcon iconeTela;
    private ImageIcon iconeTitulo;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JScrollPane scrollPane;

    private JButton btnClientes;
    private JButton btnRelatorios;
    private JButton btnSair;

    private JButton btnAddCliente;
    private JButton btnBuscarCliente;
    private JButton btnRemoverCliente;


    private ListaClientes listaClientes;

    public Tela_Principal() {
        listaClientes = new ListaClientes();
        instanciar();
        proLabel();
        proTela();
        proPanel();
        addElementos();
        proBotoes();
        carregarClientesDoArquivo();
        ajustarTabela();
    }

    void instanciar(){
        frame = new JFrame();
        panelTopo = new JPanel();
        panelMenu = new JPanel();
        panelBotoes = new JPanel();
        panelConteudo = new JPanel();
        titulo = new JLabel();
        iconeTela = new ImageIcon("Imagens/Logo02.jpg");
        iconeTitulo = new ImageIcon("Imagens/Logo02.jpg");

        btnClientes = new JButton("👥 Clientes");
        btnRelatorios = new JButton("📊 Relatórios");
        btnSair = new JButton("🚪 Sair");

        btnAddCliente = new JButton("Adicionar Cliente");
        btnBuscarCliente = new JButton("Buscar Cliente");
        btnRemoverCliente = new JButton("Remover Cliente");
    }

    private void proTela(){
        frame.setTitle("AroEd-Lavandaria");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(iconeTela.getImage());
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        btnSair.addActionListener(e -> System.exit(0));

        modeloTabela = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(modeloTabela);
        modeloTabela.setColumnIdentifiers(new String[]{
                "ID","Nome","Sexo","Idade","HoraEntrada",
                "Calcas","Camisetas","Camisolas","Vestidos","TotItems","ValorApagar"
        });

        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setSelectionBackground(new Color(220, 240, 255));
        tabela.setSelectionForeground(Color.BLACK);
        tabela.setGridColor(new Color(200, 200, 200));
        tabela.setShowGrid(true);
        tabela.setRowHeight(30);
        tabela.setIntercellSpacing(new Dimension(1, 1));

        tabela.setFont(new Font("Sans-serif", Font.PLAIN, 14));
        tabela.getTableHeader().setFont(new Font("Sans-serif", Font.BOLD, 14));
        tabela.getTableHeader().setBackground(new Color(70, 130, 180));
        tabela.getTableHeader().setForeground(Color.WHITE);
        tabela.getTableHeader().setPreferredSize(new Dimension(0, 35));

        // Configurar renderizador para a coluna Status - CORREÇÃO: sem cast
        tabela.getColumnModel().getColumn(10).setCellRenderer(new StatusCellRenderer());

        scrollPane = new JScrollPane(tabela);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // CORREÇÃO: Classe interna deve ser estática ou usar referência correta
    private class StatusCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value != null) {
                String status = value.toString();
                if ("Pendente".equals(status)) {
                    c.setBackground(new Color(255, 245, 157));
                    c.setForeground(new Color(179, 98, 0));
                } else if ("Entregue".equals(status)) {
                    c.setBackground(new Color(200, 230, 201));
                    c.setForeground(new Color(46, 125, 50));
                }

                if (isSelected) {
                    c.setBackground(new Color(220, 240, 255));
                    c.setForeground(Color.BLACK);
                }

                setHorizontalAlignment(SwingConstants.CENTER);
                setFont(new Font("Sans-serif", Font.BOLD, 12));
            }

            return c;
        }
    }

    void proLabel(){
        Image img = iconeTitulo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon iconeRedimensionado = new ImageIcon(img);

        titulo.setHorizontalTextPosition(SwingConstants.RIGHT);
        titulo.setIconTextGap(20);
        titulo.setIcon(iconeRedimensionado);
        titulo.setText("Gestao Lavandaria AroEd");
        titulo.setFont(new Font("Sans-serif", Font.BOLD, 32));
        titulo.setForeground(Color.WHITE);
    }

    void proPanel(){
        panelTopo.setBackground(new Color(37, 78, 199));
        panelTopo.setPreferredSize(new Dimension(800, 100));
        panelTopo.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        panelMenu.setBackground(new Color(245, 245, 245));
        panelMenu.setPreferredSize(new Dimension(250, 600));
        panelMenu.setLayout(new GridLayout(8, 1, 10, 10));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelConteudo.setBackground(Color.WHITE);
        panelConteudo.setLayout(new BorderLayout());
        panelConteudo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelBotoes.setBackground(Color.WHITE);
        panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotoes.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelBotoes.setPreferredSize(new Dimension(0,70));

        panelConteudo.add(scrollPane, BorderLayout.CENTER);
        panelConteudo.add(panelBotoes, BorderLayout.SOUTH);
    }

    void addElementos(){
        frame.add(panelTopo, BorderLayout.NORTH);
        frame.add(panelMenu, BorderLayout.WEST);
        frame.add(panelConteudo, BorderLayout.CENTER);

        panelTopo.add(titulo);
        panelMenu.add(btnClientes);
        panelMenu.add(btnRelatorios);
        panelMenu.add(new JLabel());
        panelMenu.add(btnSair);
        panelBotoes.add(btnAddCliente);
        panelBotoes.add(btnBuscarCliente);
        panelBotoes.add(btnRemoverCliente);
    }

    void proBotoes(){
        JButton[] botoes = {btnClientes,  btnRelatorios};
        for (JButton btn : botoes) {
            btn.setFont(new Font("Sans-serif", Font.BOLD, 16));
            btn.setBackground(new Color(52, 152, 219));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
            btn.setPreferredSize(new Dimension(200, 60));
            btn.addActionListener(this);

            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(new Color(41, 128, 185));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(new Color(52, 152, 219));
                }
            });
        }

        JButton[] botoesClientes = {btnAddCliente, btnBuscarCliente, btnRemoverCliente};
        for (JButton btn : botoesClientes) {
            btn.setFont(new Font("Sans-serif", Font.BOLD, 14));
            btn.setBackground(new Color(52, 152, 219));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
            btn.setPreferredSize(new Dimension(150, 45));
            btn.addActionListener(this);

            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(new Color(41, 128, 185));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(new Color(52, 152, 219));
                }
            });
        }

        btnSair.setFont(new Font("Sans-serif", Font.BOLD, 16));
        btnSair.setBackground(new Color(215, 59, 59));
        btnSair.setForeground(Color.WHITE);
        btnSair.setFocusPainted(false);
        btnSair.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        btnSair.setPreferredSize(new Dimension(200, 60));
        btnSair.addActionListener(this);
    }

    private void ajustarTabela() {
        TableColumnModel columnModel = tabela.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(50);   // ID
        columnModel.getColumn(1).setPreferredWidth(150);  // Nome
        columnModel.getColumn(2).setPreferredWidth(80);   // Sexo
        columnModel.getColumn(3).setPreferredWidth(60);   // Idade
        columnModel.getColumn(4).setPreferredWidth(120);  // HoraEntrada
        columnModel.getColumn(5).setPreferredWidth(80);   // Calcas
        columnModel.getColumn(6).setPreferredWidth(100);  // Camisetas
        columnModel.getColumn(7).setPreferredWidth(100);  // Camisolas
        columnModel.getColumn(8).setPreferredWidth(80);   // Vestidos
        columnModel.getColumn(9).setPreferredWidth(80);   // TotItems
        columnModel.getColumn(10).setPreferredWidth(100); // ValorApagar

        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < tabela.getColumnCount(); i++) {
                columnModel.getColumn(i).setMinWidth(50);
            }
        });
    }

    public ListaClientes getListaClientes() {
        return listaClientes;
    }

    public void adicionarCliente(Cliente cliente){
        listaClientes.adicionarCliente(cliente);
        atualizarTabela();
    }

    public void carregarClientesDoArquivo(){
        File file = new File("src/BaseDados/Clientes.txt");
        if(!file.exists()) return;

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] campos = line.split(";");
                if(campos.length >= 10){
                    Cliente c = new Cliente(
                            campos[0], campos[1], Integer.parseInt(campos[2]),
                            campos[3], Integer.parseInt(campos[4]), Integer.parseInt(campos[5]),
                            Integer.parseInt(campos[6]), Integer.parseInt(campos[7]),
                            Integer.parseInt(campos[8]), Double.parseDouble(campos[9])
                    );

                    // Se tiver status no arquivo, usar ele


                    listaClientes.adicionarCliente(c);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        atualizarTabela();
    }

    // Método para atualizar a tabela a partir da lista ligada
    private void atualizarTabela() {
        listaClientes.atualizarLista(modeloTabela);
        ajustarTabela();
    }

    // Método para salvar clientes no arquivo
    private void salvarClientesNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/BaseDados/Clientes.txt"))) {
            Cliente atual = listaClientes.inicio;
            while (atual != null) {
                writer.write(atual.toString());
                writer.newLine();
                atual = atual.proximo;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erro ao salvar dados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarDialogRelatorios() {
        String[] opcoes = {"Relatório por Sexo", "Relatório por Idade", "Relatório por Valor", "Relatório Completo", "Cancelar"};

        int escolha = JOptionPane.showOptionDialog(frame,
                "Qual relatório deseja gerar?",
                "Escolher Tipo de Relatório",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        switch (escolha) {
            case 0: // Relatório por Sexo
                gerarRelatorioPorSexo();
                break;
            case 1: // Relatório por Idade
                gerarRelatorioPorIdade();
                break;
            case 2: // Relatório por Valor
                gerarRelatorioPorValor();
                break;
            case 3: // Relatório Completo
                gerarRelatorioCompleto();
                break;
            // case 4: Cancelar - não faz nada
        }
    }

    private void gerarRelatorioPorSexo() {
        if (listaClientes.totalClientes() == 0) {
            JOptionPane.showMessageDialog(frame,
                    "Não há clientes cadastrados para gerar relatório!",
                    "Sem Dados",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Agrupar clientes por sexo
        java.util.Map<String, java.util.List<Cliente>> clientesPorSexo = new java.util.HashMap<>();
        Cliente atual = listaClientes.inicio;

        while (atual != null) {
            String sexo = atual.sexo;
            clientesPorSexo.putIfAbsent(sexo, new java.util.ArrayList<>());
            clientesPorSexo.get(sexo).add(atual);
            atual = atual.proximo;
        }

        // Gerar relatório
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("📊 RELATÓRIO DE CLIENTES POR SEXO\n");
        relatorio.append("=====================================\n\n");

        int totalClientes = 0;
        double valorTotal = 0.0;

        for (java.util.Map.Entry<String, java.util.List<Cliente>> entry : clientesPorSexo.entrySet()) {
            String sexo = entry.getKey();
            java.util.List<Cliente> clientes = entry.getValue();

            relatorio.append("🔹 ").append(sexo.toUpperCase()).append(" - ").append(clientes.size()).append(" cliente(s)\n");
            relatorio.append("-------------------------------------\n");

            double subtotalSexo = 0.0;
            for (Cliente cliente : clientes) {
                int id = listaClientes.obterIDDoCliente(cliente);
                relatorio.append(String.format(
                        "ID: %d | Nome: %-15s | Idade: %2d | Itens: %2d | Valor: %,.2f MZN | Status: %s\n",
                        id, cliente.nome, cliente.idade, cliente.totItems, cliente.valorApagar
                ));
                subtotalSexo += cliente.valorApagar;
                totalClientes++;
            }

            relatorio.append(String.format("SUBTOTAL %s: %,.2f MZN\n\n", sexo.toUpperCase(), subtotalSexo));
            valorTotal += subtotalSexo;
        }

        relatorio.append("=====================================\n");
        relatorio.append(String.format("TOTAL GERAL: %d cliente(s) | Valor Total: %,.2f MZN\n", totalClientes, valorTotal));
        relatorio.append("\nData: ").append(java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        relatorio.append("\nHora: ").append(java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")));

        exibirRelatorio("Relatório por Sexo", relatorio.toString());
    }
    // MÉTODO: Exibir relatório em uma nova janela
    private void exibirRelatorio(String titulo, String conteudo) {
        JFrame frameRelatorio = new JFrame(titulo);
        frameRelatorio.setSize(800, 600);
        frameRelatorio.setLocationRelativeTo(frame);
        frameRelatorio.setIconImage(iconeTela.getImage());

        JTextArea textArea = new JTextArea(conteudo);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        textArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de botões
        JPanel panelBotoes = new JPanel(new FlowLayout());
        JButton btnImprimir = new JButton("🖨️ Imprimir");
        JButton btnSalvar = new JButton("💾 Salvar");
        JButton btnFechar = new JButton("❌ Fechar");

        // Configurar botões
        btnImprimir.setBackground(new Color(52, 152, 219));
        btnImprimir.setForeground(Color.WHITE);
        btnSalvar.setBackground(new Color(46, 125, 50));
        btnSalvar.setForeground(Color.WHITE);
        btnFechar.setBackground(new Color(215, 59, 59));
        btnFechar.setForeground(Color.WHITE);

        for (JButton btn : new JButton[]{btnImprimir, btnSalvar, btnFechar}) {
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            btn.setFont(new Font("Sans-serif", Font.BOLD, 12));
        }

        // Ações dos botões
        btnImprimir.addActionListener(e -> imprimirTexto(conteudo, titulo));
        btnSalvar.addActionListener(e -> salvarRelatorio(conteudo, titulo));
        btnFechar.addActionListener(e -> frameRelatorio.dispose());

        panelBotoes.add(btnImprimir);
        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnFechar);

        frameRelatorio.setLayout(new BorderLayout());
        frameRelatorio.add(scrollPane, BorderLayout.CENTER);
        frameRelatorio.add(panelBotoes, BorderLayout.SOUTH);

        frameRelatorio.setVisible(true);
    }

    // MÉTODO: Imprimir texto
    private void imprimirTexto(String texto, String titulo) {
        try {
            JTextArea textArea = new JTextArea(texto);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 10));

            boolean impresso = textArea.print();
            if (impresso) {
                JOptionPane.showMessageDialog(frame,
                        "Relatório enviado para impressão com sucesso!",
                        "Impressão Concluída",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Impressão cancelada pelo usuário.",
                        "Impressão Cancelada",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame,
                    "Erro ao imprimir: " + e.getMessage(),
                    "Erro de Impressão",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // MÉTODO: Salvar relatório em arquivo
    private void salvarRelatorio(String conteudo, String titulo) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Relatório");
        fileChooser.setSelectedFile(new java.io.File(titulo + "_" +
                java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("ddMMyyyy")) + ".txt"));

        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            java.io.File arquivo = fileChooser.getSelectedFile();
            try (java.io.PrintWriter writer = new java.io.PrintWriter(arquivo)) {
                writer.write(conteudo);
                JOptionPane.showMessageDialog(frame,
                        "Relatório salvo com sucesso!\n" + arquivo.getAbsolutePath(),
                        "Salvo com Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame,
                        "Erro ao salvar arquivo: " + e.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void gerarRelatorioPorIdade() {
        if (listaClientes.totalClientes() == 0) {
            JOptionPane.showMessageDialog(frame,
                    "Não há clientes cadastrados para gerar relatório!",
                    "Sem Dados",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Agrupar clientes por idade
        java.util.Map<Integer, java.util.List<Cliente>> clientesPorIdade = new java.util.HashMap<>();
        Cliente atual = listaClientes.inicio;

        while (atual != null) {
            int idade = atual.idade;
            clientesPorIdade.putIfAbsent(idade, new java.util.ArrayList<>());
            clientesPorIdade.get(idade).add(atual);
            atual = atual.proximo;
        }

        // Gerar relatório
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("📊 RELATÓRIO DE CLIENTES POR IDADE\n");
        relatorio.append("=====================================\n\n");

        int totalClientes = 0;
        double valorTotal = 0.0;

        // Ordenar por idade
        java.util.List<Integer> idadesOrdenadas = new java.util.ArrayList<>(clientesPorIdade.keySet());
        java.util.Collections.sort(idadesOrdenadas);

        for (int idade : idadesOrdenadas) {
            java.util.List<Cliente> clientes = clientesPorIdade.get(idade);

            relatorio.append("🔹 IDADE ").append(idade).append(" anos - ").append(clientes.size()).append(" cliente(s)\n");
            relatorio.append("-------------------------------------\n");

            double subtotalIdade = 0.0;
            for (Cliente cliente : clientes) {
                int id = listaClientes.obterIDDoCliente(cliente);
                relatorio.append(String.format(
                        "ID: %d | Nome: %-15s | Sexo: %-10s | Itens: %2d | Valor: %,.2f MZN | Status: %s\n",
                        id, cliente.nome, cliente.sexo, cliente.totItems, cliente.valorApagar
                ));
                subtotalIdade += cliente.valorApagar;
                totalClientes++;
            }

            relatorio.append(String.format("SUBTOTAL %d anos: %,.2f MZN\n\n", idade, subtotalIdade));
            valorTotal += subtotalIdade;
        }

        relatorio.append("=====================================\n");
        relatorio.append(String.format("TOTAL GERAL: %d cliente(s) | Valor Total: %,.2f MZN\n", totalClientes, valorTotal));
        relatorio.append("\nData: ").append(java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        relatorio.append("\nHora: ").append(java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")));

        exibirRelatorio("Relatório por Idade", relatorio.toString());
    }

    private void gerarRelatorioPorValor() {
        if (listaClientes.totalClientes() == 0) {
            JOptionPane.showMessageDialog(frame,
                    "Não há clientes cadastrados para gerar relatório!",
                    "Sem Dados",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Agrupar clientes por faixa de valor
        java.util.Map<String, java.util.List<Cliente>> clientesPorValor = new java.util.HashMap<>();
        Cliente atual = listaClientes.inicio;

        while (atual != null) {
            double valor = atual.valorApagar;
            String faixaValor;

            if (valor < 500) {
                faixaValor = "0-500 MZN";
            } else if (valor < 1000) {
                faixaValor = "500-1000 MZN";
            } else if (valor < 2000) {
                faixaValor = "1000-2000 MZN";
            } else {
                faixaValor = "Acima de 2000 MZN";
            }

            clientesPorValor.putIfAbsent(faixaValor, new java.util.ArrayList<>());
            clientesPorValor.get(faixaValor).add(atual);
            atual = atual.proximo;
        }

        // Gerar relatório
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("📊 RELATÓRIO DE CLIENTES POR VALOR\n");
        relatorio.append("=====================================\n\n");

        int totalClientes = 0;
        double valorTotal = 0.0;

        // Ordenar faixas de valor
        String[] faixasOrdenadas = {"0-500 MZN", "500-1000 MZN", "1000-2000 MZN", "Acima de 2000 MZN"};

        for (String faixa : faixasOrdenadas) {
            if (clientesPorValor.containsKey(faixa)) {
                java.util.List<Cliente> clientes = clientesPorValor.get(faixa);

                relatorio.append("🔹 FAIXA ").append(faixa).append(" - ").append(clientes.size()).append(" cliente(s)\n");
                relatorio.append("-------------------------------------\n");

                double subtotalFaixa = 0.0;
                for (Cliente cliente : clientes) {
                    int id = listaClientes.obterIDDoCliente(cliente);
                    relatorio.append(String.format(
                            "ID: %d | Nome: %-15s | Sexo: %-10s | Idade: %2d | Itens: %2d | Valor: %,.2f MZN\n",
                            id, cliente.nome, cliente.sexo, cliente.idade, cliente.totItems, cliente.valorApagar
                    ));
                    subtotalFaixa += cliente.valorApagar;
                    totalClientes++;
                }

                relatorio.append(String.format("SUBTOTAL %s: %,.2f MZN\n\n", faixa, subtotalFaixa));
                valorTotal += subtotalFaixa;
            }
        }

        relatorio.append("=====================================\n");
        relatorio.append(String.format("TOTAL GERAL: %d cliente(s) | Valor Total: %,.2f MZN\n", totalClientes, valorTotal));
        relatorio.append("\nData: ").append(java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        relatorio.append("\nHora: ").append(java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")));

        exibirRelatorio("Relatório por Valor", relatorio.toString());
    }

    private void gerarRelatorioCompleto() {
        if (listaClientes.totalClientes() == 0) {
            JOptionPane.showMessageDialog(frame,
                    "Não há clientes cadastrados para gerar relatório!",
                    "Sem Dados",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("📊 RELATÓRIO COMPLETO DE CLIENTES\n");
        relatorio.append("=====================================\n\n");

        Cliente atual = listaClientes.inicio;
        int totalClientes = 0;
        double valorTotal = 0.0;
        int totalItens = 0;

        // Estatísticas por sexo
        int masculino = 0, feminino = 0;
        double valorMasculino = 0.0, valorFeminino = 0.0;

        while (atual != null) {
            int id = listaClientes.obterIDDoCliente(atual);
            relatorio.append(String.format(
                    "ID: %d | Nome: %-15s | Sexo: %-10s | Idade: %2d | Itens: %2d | Valor: %,.2f MZN | Status: %s\n",
                    id, atual.nome, atual.sexo, atual.idade, atual.totItems, atual.valorApagar
            ));

            // Estatísticas
            if ("Masculino".equalsIgnoreCase(atual.sexo)) {
                masculino++;
                valorMasculino += atual.valorApagar;
            } else {
                feminino++;
                valorFeminino += atual.valorApagar;
            }

            totalClientes++;
            valorTotal += atual.valorApagar;
            totalItens += atual.totItems;
            atual = atual.proximo;
        }

        relatorio.append("\n=====================================\n");
        relatorio.append("📈 ESTATÍSTICAS GERAIS\n");
        relatorio.append("-------------------------------------\n");
        relatorio.append(String.format("Total de Clientes: %d\n", totalClientes));
        relatorio.append(String.format("Total de Itens: %d\n", totalItens));
        relatorio.append(String.format("Valor Total: %,.2f MZN\n\n", valorTotal));

        relatorio.append("👥 DISTRIBUIÇÃO POR SEXO\n");
        relatorio.append(String.format("Masculino: %d cliente(s) - %,.2f MZN\n", masculino, valorMasculino));
        relatorio.append(String.format("Feminino: %d cliente(s) - %,.2f MZN\n\n", feminino, valorFeminino));

        relatorio.append("📅 DATA E HORA\n");
        relatorio.append("Data: ").append(java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("\n");
        relatorio.append("Hora: ").append(java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"))).append("\n");

        exibirRelatorio("Relatório Completo", relatorio.toString());
    }



    private void mostrarDialogRemocao() {
        String[] opcoes = {"Remover por ID", "Remover por Nome", "Cancelar"};

        int escolha = JOptionPane.showOptionDialog(frame,
                "Como deseja remover o cliente?",
                "Escolher Método de Remoção",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        switch (escolha) {
            case 0: // Remover por ID
                removerClientePorID();
                break;
            case 1: // Remover por Nome
                removerClientePorNome();
                break;
            // case 2: Cancelar - não faz nada
        }
    }

    private void removerClientePorNome() {
        String nome = JOptionPane.showInputDialog(frame,
                "Digite o nome do cliente a ser removido:",
                "Remover Cliente por Nome",
                JOptionPane.QUESTION_MESSAGE);

        if (nome == null || nome.trim().isEmpty()) {
            return; // Usuário cancelou
        }

        nome = nome.trim();

        // Buscar todos os clientes com este nome
        List<Cliente> clientesEncontrados = listaClientes.buscarTodosClientesPorNome(nome);

        if (clientesEncontrados.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Nenhum cliente encontrado com o nome: " + nome,
                    "Cliente Não Encontrado",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Se encontrar apenas um cliente
        if (clientesEncontrados.size() == 1) {
            Cliente cliente = clientesEncontrados.get(0);
            int id = listaClientes.obterIDDoCliente(cliente);

            // Confirmar remoção
            int confirm = JOptionPane.showConfirmDialog(frame,
                    "Encontrado 1 cliente:\n\n" +
                            "ID: " + id + "\n" +
                            "Nome: " + cliente.nome + "\n" +
                            "Sexo: " + cliente.sexo + "\n" +
                            "Idade: " + cliente.idade + "\n" +
                            "Valor a Pagar: " + cliente.valorApagar + " MZN\n\n" +
                            "Deseja remover este cliente?",
                    "Confirmar Remoção por Nome",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean removido = listaClientes.removerClientePorNome(nome);

                if (removido) {
                    atualizarTabela();
                    salvarClientesNoArquivo();

                    JOptionPane.showMessageDialog(frame,
                            "Cliente removido com sucesso!",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        // Se encontrar múltiplos clientes com o mesmo nome
        else {
            StringBuilder mensagem = new StringBuilder();
            mensagem.append("Foram encontrados ").append(clientesEncontrados.size())
                    .append(" clientes com o nome '").append(nome).append("':\n\n");

            for (Cliente cliente : clientesEncontrados) {
                int id = listaClientes.obterIDDoCliente(cliente);
                mensagem.append("ID: ").append(id)
                        .append(" | Sexo: ").append(cliente.sexo)
                        .append(" | Idade: ").append(cliente.idade)
                        .append(" | Valor: ").append(cliente.valorApagar).append(" MZN\n");
            }

            mensagem.append("\nPara remover um cliente específico, use a opção 'Remover por ID'.");

            JOptionPane.showMessageDialog(frame,
                    mensagem.toString(),
                    "Múltiplos Clientes Encontrados",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void removerClientePorID() {
        String input = JOptionPane.showInputDialog(frame,
                "Digite o ID do cliente a ser removido:",
                "Remover Cliente por ID",
                JOptionPane.QUESTION_MESSAGE);

        if (input == null || input.trim().isEmpty()) {
            return; // Usuário cancelou
        }

        try {
            int id = Integer.parseInt(input.trim());

            // Verificar se o ID é válido
            if (id < 1 || id > listaClientes.totalClientes()) {
                JOptionPane.showMessageDialog(frame,
                        "ID inválido! Digite um ID entre 1 e " + listaClientes.totalClientes(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Buscar cliente para mostrar informações
            Cliente cliente = listaClientes.buscarCliente(id);
            if (cliente == null) {
                JOptionPane.showMessageDialog(frame,
                        "Cliente não encontrado!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Confirmar remoção com informações do cliente
            int confirm = JOptionPane.showConfirmDialog(frame,
                    "Tem certeza que deseja remover o cliente?\n\n" +
                            "ID: " + id + "\n" +
                            "Nome: " + cliente.nome + "\n" +
                            "Sexo: " + cliente.sexo + "\n" +
                            "Valor a Pagar: " + cliente.valorApagar + " MZN\n\n" +
                            "Esta ação não pode ser desfeita.",
                    "Confirmar Remoção por ID",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                // Remover da lista ligada
                boolean removido = listaClientes.removerCliente(id);

                if (removido) {
                    // Atualizar tabela
                    atualizarTabela();

                    // Salvar no arquivo
                    salvarClientesNoArquivo();

                    JOptionPane.showMessageDialog(frame,
                            "Cliente removido com sucesso!",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Erro ao remover cliente!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,
                    "Por favor, digite um número válido para o ID!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarDialogBusca() {
        String[] opcoes = {"Buscar por ID", "Buscar por Nome", "Buscar por Texto", "Cancelar"};

        int escolha = JOptionPane.showOptionDialog(frame,
                "Como deseja buscar o cliente?",
                "Escolher Método de Busca",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        switch (escolha) {
            case 0: // Buscar por ID
                buscarClientePorID();
                break;
            case 1: // Buscar por Nome (exato)
                buscarClientePorNomeExato();
                break;
            case 2: // Buscar por Texto (parcial)
                buscarClientePorTexto();
                break;
            // case 3: Cancelar - não faz nada
        }
    }

    private void buscarClientePorID() {
        String input = JOptionPane.showInputDialog(frame,
                "Digite o ID do cliente:",
                "Buscar Cliente por ID",
                JOptionPane.QUESTION_MESSAGE);

        if (input == null || input.trim().isEmpty()) {
            return; // Usuário cancelou
        }

        try {
            int id = Integer.parseInt(input.trim());

            // Verificar se o ID é válido
            if (id < 1 || id > listaClientes.totalClientes()) {
                JOptionPane.showMessageDialog(frame,
                        "ID inválido! Digite um ID entre 1 e " + listaClientes.totalClientes(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Buscar cliente
            Cliente cliente = listaClientes.buscarCliente(id);
            if (cliente != null) {
                mostrarResultadoBusca(cliente, id);
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Cliente não encontrado!",
                        "Resultado da Busca",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,
                    "Por favor, digite um número válido para o ID!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarClientePorNomeExato() {
        String nome = JOptionPane.showInputDialog(frame,
                "Digite o nome exato do cliente:",
                "Buscar Cliente por Nome",
                JOptionPane.QUESTION_MESSAGE);

        if (nome == null || nome.trim().isEmpty()) {
            return; // Usuário cancelou
        }

        nome = nome.trim();

        // Buscar todos os clientes com este nome
        List<Cliente> clientesEncontrados = listaClientes.buscarTodosClientesPorNome(nome);

        if (clientesEncontrados.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Nenhum cliente encontrado com o nome: " + nome,
                    "Resultado da Busca",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (clientesEncontrados.size() == 1) {
            // Mostrar resultado único
            Cliente cliente = clientesEncontrados.get(0);
            int id = listaClientes.obterIDDoCliente(cliente);
            mostrarResultadoBusca(cliente, id);
        } else {
            // Mostrar múltiplos resultados
            mostrarMultiplosResultados(clientesEncontrados, "Resultados para: " + nome);
        }
    }

    private void buscarClientePorTexto() {
        String texto = JOptionPane.showInputDialog(frame,
                "Digite parte do nome do cliente:\n(Exemplo: 'Maria' encontrará 'Maria', 'Ana Maria', etc.)",
                "Buscar Cliente por Texto",
                JOptionPane.QUESTION_MESSAGE);

        if (texto == null || texto.trim().isEmpty()) {
            return; // Usuário cancelou
        }

        texto = texto.trim();

        // Buscar clientes que contenham o texto
        List<Cliente> clientesEncontrados = listaClientes.buscarClientesPorTexto(texto);

        if (clientesEncontrados.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Nenhum cliente encontrado contendo: '" + texto + "'",
                    "Resultado da Busca",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (clientesEncontrados.size() == 1) {
            // Mostrar resultado único
            Cliente cliente = clientesEncontrados.get(0);
            int id = listaClientes.obterIDDoCliente(cliente);
            mostrarResultadoBusca(cliente, id);
        } else {
            // Mostrar múltiplos resultados
            mostrarMultiplosResultados(clientesEncontrados, "Resultados contendo: '" + texto + "'");
        }
    }

    private void mostrarResultadoBusca(Cliente cliente, int id) {
        String mensagem = String.format(
                "✅ CLIENTE ENCONTRADO\n\n" +
                        "📋 Informações do Cliente:\n" +
                        "➤ ID: %d\n" +
                        "➤ Nome: %s\n" +
                        "➤ Sexo: %s\n" +
                        "➤ Idade: %d anos\n" +
                        "➤ Hora de Entrada: %s\n\n" +
                        "👕 Itens para Lavar:\n" +
                        "➤ Calças: %d\n" +
                        "➤ Camisetas: %d\n" +
                        "➤ Camisolas: %d\n" +
                        "➤ Vestidos: %d\n\n" +
                        "💰 Total:\n" +
                        "➤ Total de Itens: %d\n" +
                        "➤ Valor a Pagar: %,.2f MZN\n\n" +
                        "📊 Status: %s",
                id, cliente.nome, cliente.sexo, cliente.idade, cliente.horaEntrada,
                cliente.calcas, cliente.camisetas, cliente.camisolas, cliente.vestidos,
                cliente.totItems, cliente.valorApagar
        );

        // Destacar na tabela
        destacarClienteNaTabela(id);

        JOptionPane.showMessageDialog(frame,
                mensagem,
                "Cliente Encontrado - ID: " + id,
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void destacarClienteNaTabela(int id) {
        // Limpar seleção anterior
        tabela.clearSelection();

        // Procurar a linha com o ID
        for (int i = 0; i < modeloTabela.getRowCount(); i++) {
            int idNaTabela = (int) modeloTabela.getValueAt(i, 0);
            if (idNaTabela == id) {
                // Selecionar a linha
                tabela.setRowSelectionInterval(i, i);

                // Rolar até a linha
                tabela.scrollRectToVisible(tabela.getCellRect(i, 0, true));

                // Dar foco à tabela
                tabela.requestFocus();
                break;
            }
        }
    }

    private void mostrarMultiplosResultados(List<Cliente> clientes, String titulo) {
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("🔍 ").append(titulo).append("\n\n");
        mensagem.append("Foram encontrados ").append(clientes.size()).append(" cliente(s):\n\n");

        for (Cliente cliente : clientes) {
            int id = listaClientes.obterIDDoCliente(cliente);
            mensagem.append(String.format(
                    "➤ ID: %d | Nome: %s | Sexo: %s | Idade: %d | Valor: %,.2f MZN | Status: %s\n",
                    id, cliente.nome, cliente.sexo, cliente.idade, cliente.valorApagar
            ));
        }

        mensagem.append("\n💡 Dica: Para ver detalhes completos, use a busca por ID.");

        JOptionPane.showMessageDialog(frame,
                mensagem.toString(),
                "Múltiplos Clientes Encontrados",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClientes) {
            JOptionPane.showMessageDialog(frame,
                    "Lista de Clientes\n" +
                            "Total de clientes: " + listaClientes.totalClientes(),
                    "Informação de Clientes",
                    JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == btnRelatorios) {
            mostrarDialogRelatorios();
        } else if (e.getSource() == btnAddCliente) {
            new Tela_Cadastro(this);
            frame.setVisible(false);
        } else if (e.getSource() == btnBuscarCliente) {
            mostrarDialogBusca();
        } else if (e.getSource() == btnRemoverCliente) {
            mostrarDialogRemocao();
        }
    }
}