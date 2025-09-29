package Telas;

import javax.swing.*;
import java.awt.*;

public class Cadastro {
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

    public Cadastro(){
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
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(iconeTela.getImage());

    }

    void proLabel(){
        titulo.setText("AroEd Lavandaria");
        //titulo.setIcon(new ImageIcon(iconeTitulo.getImage()));
        titulo.setForeground(new Color(255, 255, 255, 255));

        nome.setText("Nome: ");
        sexo.setText("Sexo: ");
        idade.setText("Idade: ");
        tipoRegistro.setText("Tipo de Registro");
    }

    void proBotoes(){
        btnCancelar.setText("Cancelar");
        btnConcluir.setText("Concluir");
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
