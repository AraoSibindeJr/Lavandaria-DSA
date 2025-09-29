package src.UI_Telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela_Cadastro implements ActionListener {
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
    private JComboBox<String> escolherSexo;
    private JComboBox<String> tipoReg;

    public Tela_Cadastro() {
        instanciar();
        proTela();
        proPanel();
        accoes();
        proCombo();
        proBotoes();
        proLabel();
        addElementos();
        frame.setVisible(true);
    }

    public void instanciar() {
        frame = new JFrame();
        panelCima = new JPanel();
        panelCentro = new JPanel();
        panelBaixo = new JPanel();
        titulo = new JLabel();
        nome = new JLabel();
        sexo = new JLabel();
        idade = new JLabel();
        esconherIdade = new SpinnerNumberModel(18, 0, 120, 1);
        btnCancelar = new JButton();
        txtNome = new JTextField();
        btnConcluir = new JButton();
        tipoRegistro = new JLabel();
        tipoReg = new JComboBox<>();
        iconeTela = new ImageIcon("Imagens/Logo02.jpg");
        iconeTitulo = new ImageIcon("Imagens/Logo02.jpg");
    }

    void proTela() {
        frame.setTitle("AroEd-Lavandaria");
        frame.setSize(450, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(iconeTela.getImage());
        frame.setLayout(new BorderLayout(15, 15));
        frame.getContentPane().setBackground(new Color(230, 240, 250));
    }

    void proLabel() {
        Image img = iconeTitulo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon iconeRedimensionado = new ImageIcon(img);
        titulo.setHorizontalTextPosition(SwingConstants.RIGHT);
        titulo.setIconTextGap(15);
        titulo.setIcon(iconeRedimensionado);
        titulo.setText("Cadastro");
        titulo.setFont(new Font("Sans-serif", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);

        nome.setText("Nome: ");
        nome.setForeground(Color.WHITE);

        sexo.setText("Sexo: ");
        sexo.setForeground(Color.WHITE);

        idade.setText("Idade: ");
        idade.setForeground(Color.WHITE);

        tipoRegistro.setText("Tipo de Registro:");
        tipoRegistro.setForeground(Color.WHITE);
    }

    void proBotoes() {
        btnCancelar.setText("Cancelar");
        btnCancelar.setBackground(new Color(255, 90, 90));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setPreferredSize(new Dimension(120, 35));

        btnConcluir.setText("Concluir");
        btnConcluir.setBackground(new Color(37, 150, 190));
        btnConcluir.setForeground(Color.WHITE);
        btnConcluir.setFocusPainted(false);
        btnConcluir.setPreferredSize(new Dimension(120, 35));
    }

    void proPanel(){
        panelCima.setBackground(new Color(37, 78, 199));
        panelBaixo.setBackground(new Color(245, 245, 245));
        panelCentro.setBackground(new Color(52, 119, 180, 107));

        panelCima.setPreferredSize(new Dimension(400,80));
        panelBaixo.setPreferredSize(new Dimension(400,80));

        panelCentro.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panelCentro.setLayout(new GridBagLayout());
    }

    void proCombo(){
        String[] sexoOp = {"Masculino","Feminino"};
        escolherSexo = new JComboBox<>(sexoOp);
        escolherSexo.setPreferredSize(new Dimension(200,25));

        String[] tipoR = {"Normal" , "VIP"};
        tipoReg = new JComboBox<>(tipoR);
        tipoReg.setPreferredSize(new Dimension(200,25));

        txtNome.setPreferredSize(new Dimension(200,25));
    }

    void addElementos(){
        frame.add(panelCima, BorderLayout.NORTH);
        frame.add(panelCentro, BorderLayout.CENTER);
        frame.add(panelBaixo, BorderLayout.SOUTH);

        // Topo
        panelCima.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
        panelCima.add(titulo);

        // Centro com GridBag
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        panelCentro.add(nome, gbc);
        gbc.gridx = 1;
        panelCentro.add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelCentro.add(sexo, gbc);
        gbc.gridx = 1;
        panelCentro.add(escolherSexo, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelCentro.add(idade, gbc);
        gbc.gridx = 1;
        panelCentro.add(new JSpinner(esconherIdade), gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelCentro.add(tipoRegistro, gbc);
        gbc.gridx = 1;
        panelCentro.add(tipoReg, gbc);

        // Baixo
        panelBaixo.setLayout(new FlowLayout(FlowLayout.CENTER,30,20));
        panelBaixo.add(btnCancelar);
        panelBaixo.add(btnConcluir);
    }

    void accoes(){
        btnConcluir.addActionListener(this);
        btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (txtNome.getText() != null && !txtNome.getText().isEmpty()) {
            if (e.getSource() == btnConcluir) {
                // Dados necessarios na tela de Recibo
                String nome = txtNome.getText();
                String sexo = escolherSexo.getSelectedItem().toString();
                int idade = (int) esconherIdade.getValue();
                String horaRegistro = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                JOptionPane.showMessageDialog(null,
                        "Usuario registrado com sucesso!",
                        "Confirmacao",
                        JOptionPane.INFORMATION_MESSAGE);
                        //Abrir a tela de recibo
                new Tela_Recibo(nome,sexo,idade,horaRegistro);
                frame.dispose();
            }
        } else {
            if (e.getSource() == btnConcluir) {
                JOptionPane.showMessageDialog(null,
                        "Por favor, insira o seu nome para continuar.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == btnCancelar){
            System.exit(0);
        }
    }
}