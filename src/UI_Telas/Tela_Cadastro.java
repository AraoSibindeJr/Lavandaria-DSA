package src.UI_Telas;

import javax.swing.*;
import java.awt.*;

public class Tela_Cadastro {
    private JFrame frame;
    private JPanel panelCima;
    private JPanel panelBaixo;
    private JPanel panelCentro;
    private JLabel titulo;
    private JLabel nome;
    private JLabel sexo;
    private JLabel idade;
    private SpinnerModel esconherIdade;
    private JLabel tipoRegistro;
    private ImageIcon iconeTela;
    private ImageIcon iconeTitulo;
    private JButton btnCancelar;
    private JButton btnConcluir;
    private JTextField txtNome;
    private JComboBox escolherSexo;
    private JComboBox tipoReg;

    public Tela_Cadastro(){
        instanciar();
        proTela();
        proPanel();
        proCombo();
        proBotoes();
        proLayout();
        proLabel();
        addElementos();
        frame.setVisible(true);
    }

    public void instanciar(){
        frame = new JFrame();
        panelCima = new JPanel();
        panelCentro = new JPanel();
        panelBaixo = new JPanel();
        titulo = new JLabel();
        nome = new JLabel();
        sexo = new JLabel();
        idade = new JLabel();
        esconherIdade = new SpinnerNumberModel(0,0,120,1);
        btnCancelar = new JButton();
        txtNome = new JTextField();
        btnConcluir = new JButton();
        tipoRegistro = new JLabel();
        tipoReg = new JComboBox<String>();
        iconeTela = new ImageIcon("Imagens/Logo02.jpg");
        iconeTitulo = new ImageIcon("Imagens/Logo02.jpg");
    }

     void proTela(){
        frame.setTitle("AroEd-Lavandaria");
        frame.setSize(400, 550);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(iconeTela.getImage());

    }

    void proLabel(){
        titulo.setText("AroEd Lavandaria");
        titulo.setFont(new Font("Sans-serif",Font.BOLD,28));
        titulo.setForeground(new Color(255, 255, 255, 255));

        nome.setText("Nome: ");
        nome.setForeground(Color.WHITE);
        sexo.setText("Sexo: ");
        sexo.setForeground(Color.WHITE);

        idade.setText("Idade: ");
        idade.setForeground(Color.WHITE);

        tipoRegistro.setText("Tipo de Registro");
        tipoRegistro.setForeground(Color.WHITE);

    }

    void proBotoes(){
        btnCancelar.setText("Cancelar");
        btnCancelar.setForeground(new Color(37, 78, 199));
        btnConcluir.setText("Concluir");
        btnConcluir.setForeground(new Color(37, 78, 199));
    }


    void proPanel(){
        panelCima.setBackground(new Color(37, 78, 199));
        panelBaixo.setBackground(new Color(37, 78, 199));
        panelCentro.setBackground(new Color(52, 119, 180, 107));
    }

    void proCombo(){
        String[] sexo = {"Masculino","Feminino"};
        escolherSexo = new JComboBox<>(sexo);

        String[] tipoR = {"Normal" , "VIP"};
        tipoReg = new JComboBox<>(tipoR);
    }

    void proLayout(){
        GridLayout grid = new GridLayout(4,2,5,15);
        panelCentro.setLayout(grid);
    }

    void addElementos(){
        frame.add(panelCima, BorderLayout.NORTH);
        frame.add(panelCentro, BorderLayout.CENTER);
        frame.add(panelBaixo, BorderLayout.SOUTH);


        panelCima.add(titulo);

        panelCentro.add(nome);
        panelCentro.add(txtNome);
        panelCentro.add(sexo);
        panelCentro.add(escolherSexo);
        panelCentro.add(idade);
        panelCentro.add(new JSpinner(esconherIdade));
        panelCentro.add(tipoRegistro);
        panelCentro.add(tipoReg);

        panelBaixo.add(btnCancelar);
        panelBaixo.add(btnConcluir);

    }
}
