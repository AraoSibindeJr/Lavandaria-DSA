package src.UI_Telas;

import src.Entidades.Cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

class Tela_Recibo implements ActionListener {
    private JFrame frame;
    private JPanel panelCima;
    private JPanel panelCentro;
    private JPanel panelBaixo;
    private JLabel titulo;
    private JButton btnContinuar;
    private DefaultTableModel tabelaPecas;
    private String nome, sexo, horaRegistro;
    private int idade;
    private ImageIcon iconeTitulo;
    private Tela_Principal telaPrincipal;
    private final int precoCalca=150, precoCamiseta=100, precoCamisola=200, precoVestido=150;

    public Tela_Recibo(String nome, String sexo, int idade, String horaRegistro,
                       DefaultTableModel tabelaPecas, Tela_Principal telaPrincipal){
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.horaRegistro = horaRegistro;
        this.tabelaPecas = tabelaPecas;
        this.telaPrincipal = telaPrincipal;

        instanciar();
        proTela();
        proLabel();
        proPanel();
        addElementos();
        proBotoes();
        montarRecibo();
        frame.setVisible(true);
    }

    private void instanciar(){
        iconeTitulo = new ImageIcon("Imagens/Logo02.jpg");
        frame = new JFrame("Recibo - Lavandaria AroEd");
        panelCima = new JPanel();
        panelCentro = new JPanel();
        panelBaixo = new JPanel();
        titulo = new JLabel("Recibo do/a Cliente: " + nome);
        btnContinuar = new JButton("Continuar");
    }

    private void proTela(){
        frame.setIconImage(iconeTitulo.getImage());
        frame.setSize(500, 650);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setResizable(false);
    }

    private void proLabel(){
        titulo.setFont(new Font("Sans-serif", Font.BOLD, 22));
        titulo.setForeground(new Color(37, 78, 199));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(new EmptyBorder(15, 0, 15, 0));
    }

    private void proPanel(){
        // Panel Cima - Título
        panelCima.setBackground(Color.WHITE);
        panelCima.setLayout(new BorderLayout());
        panelCima.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(37, 78, 199)),
                new EmptyBorder(5, 20, 5, 20)
        ));

        // Panel Centro - Conteúdo do recibo
        panelCentro.setBackground(Color.WHITE);
        panelCentro.setLayout(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(15, 20, 15, 20),
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1)
        ));

        // Panel Baixo - Botão
        panelBaixo.setBackground(Color.WHITE);
        panelBaixo.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBaixo.setBorder(new EmptyBorder(15, 20, 20, 20));
    }

    private void addElementos(){
        frame.add(panelCima, BorderLayout.NORTH);
        frame.add(panelCentro, BorderLayout.CENTER);
        frame.add(panelBaixo, BorderLayout.SOUTH);

        panelCima.add(titulo, BorderLayout.CENTER);
        panelBaixo.add(btnContinuar);
    }

    private void proBotoes(){
        btnContinuar.setFont(new Font("Sans-serif", Font.BOLD, 16));
        btnContinuar.setBackground(new Color(37, 78, 199));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFocusPainted(false);
        btnContinuar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30, 60, 160), 2),
                new EmptyBorder(10, 35, 10, 35)
        ));
        btnContinuar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efeito hover
        btnContinuar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnContinuar.setBackground(new Color(41, 128, 185));
                btnContinuar.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(35, 100, 180), 2),
                        new EmptyBorder(10, 35, 10, 35)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnContinuar.setBackground(new Color(37, 78, 199));
                btnContinuar.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(30, 60, 160), 2),
                        new EmptyBorder(10, 35, 10, 35)
                ));
            }
        });

        btnContinuar.addActionListener(this);
    }

    private void montarRecibo(){
        int totalPecas = 0;
        int totalPagar = 0;
        int calcas = 0, camisetas = 0, camisolas = 0, vestidos = 0;


        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


        JLabel lblHeader = new JLabel(" Lavandaria AroEd");
        lblHeader.setFont(new Font("Sans-serif", Font.BOLD, 20));
        lblHeader.setForeground(new Color(37, 78, 199));
        lblHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblHeader.setBorder(new EmptyBorder(0, 0, 15, 0));
        contentPanel.add(lblHeader);


        contentPanel.add(criarLinhaRecibo(" Nome:", nome));
        contentPanel.add(criarLinhaRecibo(" Sexo:", sexo));
        contentPanel.add(criarLinhaRecibo(" Idade:", idade + " anos"));
        contentPanel.add(criarLinhaRecibo(" Hora Entrada:", horaRegistro));


        contentPanel.add(criarSeparador());


        JLabel lblCarrinho = new JLabel("🛒 Itens do Cliente");
        lblCarrinho.setFont(new Font("Sans-serif", Font.BOLD, 16));
        lblCarrinho.setForeground(new Color(80, 80, 80));
        lblCarrinho.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblCarrinho.setBorder(new EmptyBorder(5, 0, 10, 0));
        contentPanel.add(lblCarrinho);


        for(int i = 0; i < tabelaPecas.getRowCount(); i++){
            String item = tabelaPecas.getValueAt(i, 0).toString();
            int qtd = (int) tabelaPecas.getValueAt(i, 1);
            int precoUnit = switch(item){
                case "Calca" -> precoCalca;
                case "Camiseta" -> precoCamiseta;
                case "Camisola" -> precoCamisola;
                case "Vestido" -> precoVestido;
                default -> 0;
            };
            int precoItem = precoUnit * qtd;
            totalPecas += qtd;
            totalPagar += precoItem;


            contentPanel.add(criarItemRecibo(item, qtd, precoItem));

            switch(item){
                case "Calca" -> calcas += qtd;
                case "Camiseta" -> camisetas += qtd;
                case "Camisola" -> camisolas += qtd;
                case "Vestido" -> vestidos += qtd;
            }
        }


        contentPanel.add(criarSeparador());


        contentPanel.add(criarLinhaTotal("Total de Pecas:", String.valueOf(totalPecas)));
        contentPanel.add(criarLinhaTotal("Total a Pagar:", String.format("%,d MZN", totalPagar)));


        contentPanel.add(criarSeparador());


        JLabel lblObrigado = new JLabel("Obrigado pela preferencia!");
        lblObrigado.setFont(new Font("Sans-serif", Font.BOLD, 14));
        lblObrigado.setForeground(new Color(37, 78, 199));
        lblObrigado.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblObrigado.setBorder(new EmptyBorder(10, 0, 5, 0));
        contentPanel.add(lblObrigado);


        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.WHITE);
        panelCentro.add(scrollPane, BorderLayout.CENTER);


        Cliente cliente = new Cliente(nome, sexo, idade, horaRegistro,
                calcas, camisetas, camisolas, vestidos, totalPecas, totalPagar);


        try(BufferedWriter writer = new BufferedWriter(new FileWriter("src/BaseDados/Clientes.txt", true))){
            writer.write(cliente.toString());
            writer.newLine();
        } catch(Exception e){
            e.printStackTrace();
        }


        telaPrincipal.adicionarCliente(cliente);
    }

    private JPanel criarLinhaRecibo(String label, String valor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(400, 30));
        panel.setBorder(new EmptyBorder(2, 10, 2, 10));

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Sans-serif", Font.BOLD, 13));
        lbl.setForeground(new Color(80, 80, 80));

        JLabel val = new JLabel(valor);
        val.setFont(new Font("Sans-serif", Font.PLAIN, 13));
        val.setForeground(Color.BLACK);
        val.setHorizontalAlignment(SwingConstants.RIGHT);

        panel.add(lbl, BorderLayout.WEST);
        panel.add(val, BorderLayout.EAST);

        return panel;
    }

    private JPanel criarItemRecibo(String item, int quantidade, int preco) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(400, 35));
        panel.setBorder(new EmptyBorder(5, 15, 5, 15));

        JLabel lblItem = new JLabel(item);
        lblItem.setFont(new Font("Sans-serif", Font.PLAIN, 13));
        lblItem.setForeground(new Color(60, 60, 60));

        JLabel lblDetalhes = new JLabel(String.format("x%d = %,d MZN", quantidade, preco));
        lblDetalhes.setFont(new Font("Sans-serif", Font.BOLD, 13));
        lblDetalhes.setForeground(new Color(37, 78, 199));
        lblDetalhes.setHorizontalAlignment(SwingConstants.RIGHT);

        panel.add(lblItem, BorderLayout.WEST);
        panel.add(lblDetalhes, BorderLayout.EAST);

        return panel;
    }

    private JPanel criarLinhaTotal(String label, String valor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(400, 40));
        panel.setBorder(new EmptyBorder(8, 15, 8, 15));

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Sans-serif", Font.BOLD, 14));
        lbl.setForeground(new Color(37, 78, 199));

        JLabel val = new JLabel(valor);
        val.setFont(new Font("Sans-serif", Font.BOLD, 15));
        val.setForeground(new Color(0, 128, 0));
        val.setHorizontalAlignment(SwingConstants.RIGHT);

        panel.add(lbl, BorderLayout.WEST);
        panel.add(val, BorderLayout.EAST);

        return panel;
    }

    private JSeparator criarSeparador() {
        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
        separator.setForeground(new Color(220, 220, 220));
        separator.setMaximumSize(new Dimension(400, 5));
        separator.setBorder(new EmptyBorder(10, 20, 10, 20));
        return separator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnContinuar){
            frame.dispose();
            telaPrincipal.getFrame().setVisible(true);
        }
    }
}