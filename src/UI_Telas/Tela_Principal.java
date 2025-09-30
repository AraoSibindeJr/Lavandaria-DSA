package src.UI_Telas;

import src.Entidades.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Tela_Principal implements ActionListener {
    private JFrame frame;
    private JPanel panelTopo;
    private JPanel panelMenu;
    private JPanel panelConteudo;
    private JLabel titulo;
    private ImageIcon iconeTela;
    private ImageIcon iconeTitulo;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JScrollPane scrollPane;
    private int nextID = 1;

    private JButton btnClientes;
    private JButton btnEncomendas;
    private JButton btnOrcamento;
    private JButton btnRelatorios;
    private JButton btnSair;

    public Tela_Principal() {
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
        panelConteudo = new JPanel();
        titulo = new JLabel();
        iconeTela = new ImageIcon("Imagens/Logo02.jpg");
        iconeTitulo = new ImageIcon("Imagens/Logo02.jpg");


        btnClientes = new JButton("👥 Clientes");
        btnEncomendas = new JButton("📦 Encomendas");
        btnRelatorios = new JButton("📊 Relatórios");
        btnOrcamento = new JButton("💳 Orcamento");
        btnSair = new JButton("🚪 Sair");
    }

    private void proTela(){
        // Tela Principal
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

        scrollPane = new JScrollPane(tabela);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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

        panelConteudo.add(scrollPane, BorderLayout.CENTER);
    }

    void addElementos(){
        frame.add(panelTopo, BorderLayout.NORTH);
        frame.add(panelMenu, BorderLayout.WEST);
        frame.add(panelConteudo, BorderLayout.CENTER);

        // Topo
        panelTopo.add(titulo);

        // Menu
        panelMenu.add(btnClientes);
        panelMenu.add(btnEncomendas);
        panelMenu.add(btnOrcamento);
        panelMenu.add(btnRelatorios);
        panelMenu.add(new JLabel());
        panelMenu.add(btnSair);
    }

    void proBotoes(){
        JButton[] botoes = {btnClientes, btnEncomendas, btnOrcamento, btnRelatorios};
        for (JButton btn : botoes) {
            btn.setFont(new Font("Sans-serif", Font.BOLD, 16));
            btn.setBackground(new Color(52, 152, 219));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
            btn.setPreferredSize(new Dimension(200, 60));
            btn.addActionListener(this);

            // Efeito hover
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

    public void adicionarCliente(Cliente cliente){
        modeloTabela.addRow(new Object[]{
                nextID++, cliente.nome, cliente.sexo, cliente.idade,
                cliente.horaEntrada, cliente.calcas, cliente.camisetas,
                cliente.camisolas, cliente.vestidos, cliente.totItems,
                cliente.valorApagar
        });


        ajustarTabela();
    }

    public void carregarClientesDoArquivo(){
        File file = new File("src/BaseDados/Clientes.txt");
        if(!file.exists()) return;

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] campos = line.split(";");
                if(campos.length == 10){
                    Cliente c = new Cliente(
                            campos[0], campos[1], Integer.parseInt(campos[2]),
                            campos[3], Integer.parseInt(campos[4]), Integer.parseInt(campos[5]),
                            Integer.parseInt(campos[6]), Integer.parseInt(campos[7]),
                            Integer.parseInt(campos[8]), Double.parseDouble(campos[9])
                    );
                    adicionarCliente(c);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        // Ajustar colunas após carregar todos os clientes
        ajustarTabela();
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClientes) {
        } else if (e.getSource() == btnEncomendas) {
        }
    }
}