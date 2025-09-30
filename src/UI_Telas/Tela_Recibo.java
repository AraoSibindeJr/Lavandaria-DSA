package src.UI_Telas;

import javax.swing.*;
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


    public Tela_Recibo(String nome, String sexo, int idade, String horaRegistro) {
        instanciar();
        proTela();
        proPanel();
        addElementos();
        accao();
        proLabel();
        proBotao();
        frame.setVisible(true);


        areaRecibo.setEditable(false);
        areaRecibo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaRecibo.setText(
                "------ Lavandaria AroEd ------\n" +
                        "Nome: " + nome + "\n" +
                        "Sexo: " + sexo + "\n" +
                        "Idade: " + idade + "\n" +
                        "Data de Registro: " + horaRegistro + "\n" +
                        "------------------------------\n" +
                        "Obrigado pela preferencia!"
        );
    }
    void instanciar(){
        frame = new JFrame("Recibo - Lavandaria");
        panelCima = new JPanel();
        panelBaixo = new JPanel();
        areaRecibo = new JTextArea();
        panelCentro = new JPanel();
        iconeTela = new ImageIcon("Imagens/Logo02.jpg");
        btnContinuar = new JButton();
        labelTitulo = new JLabel("Recibo do Cliente");
    }
    void proTela(){
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(iconeTela.getImage());
    }
    void proLabel(){
        labelTitulo.setFont(new Font("Sans-serif", Font.BOLD, 28));
        labelTitulo.setForeground(Color.WHITE);
    }
    void proPanel(){
        panelCima.setBackground(new Color(37, 78, 199));
        panelBaixo.setBackground(new Color(37, 78, 199));
    }
    void proBotao(){
        btnContinuar.setText("Continuar");
        btnContinuar.setBackground(new Color(255, 255, 255));
        btnContinuar.setForeground(Color.BLUE);
        btnContinuar.setFocusPainted(false);
        btnContinuar.setPreferredSize(new Dimension(120, 35));
    }
    void addElementos(){
        frame.add(panelCima,BorderLayout.NORTH);
        frame.add(panelCentro,BorderLayout.CENTER);
        frame.add(panelBaixo, BorderLayout.SOUTH);
        panelCima.add(labelTitulo);
        panelBaixo.add(btnContinuar);
        panelCentro.add(new JScrollPane(areaRecibo));
    }

    void accao(){
        btnContinuar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnContinuar){
            //Abrir tela principal do Projecto
            System.out.println("Funciona"); //Teste da accao
        }
    }
}
