package src.UI_Telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela_Recibo implements ActionListener {
    private JFrame frame;
    private JPanel panelCima;
    private JPanel panelBaixo;
    private JPanel panelCentro;
    private JButton btnContinuar;
    private JLabel labelTitulo;
    private ImageIcon iconeTela;
    private JTextArea areaRecibo;


    private final int precoCalca = 150;
    private final int precoCamiseta = 100;
    private final int precoCamisola = 200;
    private final int precoVestido = 150;
    private DefaultTableModel tabelaPecas;

    public Tela_Recibo(String nome, String sexo, int idade, String horaRegistro, DefaultTableModel tabelaPecas) {
        this.tabelaPecas = tabelaPecas;

        instanciar();
        proTela();
        proPanel();
        addElementos();
        accao();
        proLabel();
        proBotao();
        frame.setVisible(true);
        montarRecibo(nome, sexo, idade, horaRegistro);
    }

    void instanciar() {
        frame = new JFrame("Recibo - Lavandaria");
        panelCima = new JPanel();
        panelBaixo = new JPanel();
        panelCentro = new JPanel(new BorderLayout());
        areaRecibo = new JTextArea();
        iconeTela = new ImageIcon("Imagens/Logo02.jpg");
        btnContinuar = new JButton();
        labelTitulo = new JLabel("Recibo do Cliente", SwingConstants.CENTER);
    }

    void proTela() {
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(iconeTela.getImage());
    }

    void proLabel() {
        labelTitulo.setFont(new Font("Sans-serif", Font.BOLD, 24));
        labelTitulo.setForeground(Color.WHITE);
    }

    void proPanel() {
        panelCima.setBackground(new Color(37, 78, 199));
        panelBaixo.setBackground(new Color(37, 78, 199));
    }

    void proBotao() {
        btnContinuar.setText("Continuar");
        btnContinuar.setBackground(new Color(255, 255, 255));
        btnContinuar.setForeground(Color.BLUE);
        btnContinuar.setFocusPainted(false);
        btnContinuar.setPreferredSize(new Dimension(120, 35));
    }

    void addElementos() {
        frame.add(panelCima, BorderLayout.NORTH);
        frame.add(panelCentro, BorderLayout.CENTER);
        frame.add(panelBaixo, BorderLayout.SOUTH);

        panelCima.add(labelTitulo);
        panelBaixo.add(btnContinuar);

        areaRecibo.setEditable(false);
        areaRecibo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        panelCentro.add(new JScrollPane(areaRecibo), BorderLayout.CENTER);
    }

    void accao() {
        btnContinuar.addActionListener(this);
    }

    private void montarRecibo(String nome, String sexo, int idade, String horaRegistro) {
        if (tabelaPecas.getRowCount() == 0) {
            JOptionPane.showMessageDialog(frame,
                    "O carrinho está vazio! Por favor, adicione peças antes de continuar.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int totalPecas = 0;
        int totalPagar = 0;
        StringBuilder sb = new StringBuilder();

        sb.append("------ Lavandaria AroEd ------\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Sexo: ").append(sexo).append("\n");
        sb.append("Idade: ").append(idade).append("\n");
        sb.append("Data de Registro: ").append(horaRegistro).append("\n");
        sb.append("------------------------------\n");
        sb.append("Carrinho do Cliente:\n");

        for (int i = 0; i < tabelaPecas.getRowCount(); i++) {
            String item = tabelaPecas.getValueAt(i, 0).toString();
            int qtd = (int) tabelaPecas.getValueAt(i, 1);

            int precoUnit = switch (item) {
                case "Calca" -> precoCalca;
                case "Camiseta" -> precoCamiseta;
                case "Camisola" -> precoCamisola;
                case "Vestido" -> precoVestido;
                default -> 0;
            };

            int precoItem = precoUnit * qtd;
            totalPecas += qtd;
            totalPagar += precoItem;

            sb.append(String.format("%-10s x %2d = %3d\n", item, qtd, precoItem));
        }

        sb.append("------------------------------\n");
        sb.append("Total de pecas: ").append(totalPecas).append("\n");
        sb.append("Total a pagar: ").append(totalPagar).append(" MZN\n");
        sb.append("------------------------------\n");
        sb.append("Obrigado pela preferencia!");

        areaRecibo.setText(sb.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnContinuar) {
            frame.dispose();
            System.out.println("Continuar pra tela Principal");
        }
    }
}
