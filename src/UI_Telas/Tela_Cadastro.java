package src.UI_Telas;

import src.Entidades.Cliente;
import src.Entidades.ListaClientes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela_Cadastro implements ActionListener {
    private JFrame frame;
    private JPanel panelCima;
    private JPanel panelBaixo;
    private JPanel formPecas;
    private JPanel panelCentro;
    private JPanel panelMeio;
    private JLabel titulo;
    private JLabel tituloPecas;
    private JLabel nome;
    private JLabel sexo;
    private JLabel idade;
    private SpinnerModel esconherIdade;
    private ImageIcon iconeTela;
    private ImageIcon iconeTitulo;
    private JButton btnCancelar;
    private JButton btnConcluir;
    private JTextField txtNome;
    private JComboBox<String> escolherSexo;
    private JPanel panelPecas;
    private JComboBox<String> comboItem;
    private JSpinner spinnerQtd;
    private JButton btnAdicionar;
    private JTable tabelaPecas;
    private DefaultTableModel modeloTabela;
    private Tela_Principal telaPrincipal;

    public Tela_Cadastro(Tela_Principal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
        instanciar();
        proTela();
        proFormulario();
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
        panelMeio = new JPanel(new BorderLayout());
        titulo = new JLabel();
        formPecas = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        nome = new JLabel();
        tituloPecas = new JLabel("Adicionar Pecas");
        sexo = new JLabel();
        idade = new JLabel();
        esconherIdade = new SpinnerNumberModel(18, 0, 120, 1);
        btnCancelar = new JButton();
        txtNome = new JTextField();
        btnConcluir = new JButton();
        iconeTela = new ImageIcon("Imagens/Logo02.jpg");
        iconeTitulo = new ImageIcon("Imagens/Logo02.jpg");
        panelPecas = new JPanel();
        String[] itens = {"Calca", "Camiseta", "Camisola", "Vestido"};
        comboItem = new JComboBox<>(itens);
        spinnerQtd = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
        btnAdicionar = new JButton("Adicionar");
        modeloTabela = new DefaultTableModel(new String[]{"Item", "Quantidade"}, 0);
        tabelaPecas = new JTable(modeloTabela);
    }

    void proTela() {
        frame.setTitle("AroEd-Lavandaria");
        frame.setSize(450, 600);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Alterado para DISPOSE_ON_CLOSE
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
        nome.setForeground(Color.BLUE);

        sexo.setText("Sexo: ");
        sexo.setForeground(Color.BLUE);

        idade.setText("Idade: ");
        idade.setForeground(Color.BLUE);

        tituloPecas.setFont(new Font("Sans-serif", Font.BOLD, 20));
        tituloPecas.setForeground(new Color(37, 78, 199));
        tituloPecas.setHorizontalAlignment(SwingConstants.CENTER);
    }

    void proFormulario() {
        formPecas.setBackground(panelPecas.getBackground());
        formPecas.add(new JLabel("Item:"));
        formPecas.add(comboItem);
        formPecas.add(new JLabel("Qtd:"));
        formPecas.add(spinnerQtd);
        formPecas.add(btnAdicionar);
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

    void proPanel() {
        panelCima.setBackground(new Color(37, 78, 199));
        panelBaixo.setBackground(new Color(245, 245, 245));

        Color corPadrao = new Color(245, 245, 245);
        panelCentro.setBackground(corPadrao);
        panelPecas.setBackground(corPadrao);

        panelCima.setPreferredSize(new Dimension(400, 80));
        panelBaixo.setPreferredSize(new Dimension(400, 80));

        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelCentro.setLayout(new GridBagLayout());

        panelPecas.setLayout(new BorderLayout(10, 10));
        panelPecas.setPreferredSize(new Dimension(400, 200));
    }

    void proCombo() {
        String[] sexoOp = {"Masculino", "Feminino"};
        escolherSexo = new JComboBox<>(sexoOp);
        escolherSexo.setPreferredSize(new Dimension(200, 25));
        txtNome.setPreferredSize(new Dimension(200, 25));
    }

    void addElementos() {
        // Painéis principais
        frame.add(panelCima, BorderLayout.NORTH);
        frame.add(panelBaixo, BorderLayout.SOUTH);
        frame.add(panelMeio, BorderLayout.CENTER); // Meio vai conter cadastro + peças

        // Topo
        panelCima.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panelCima.add(titulo);

        // --- Parte do cadastro ---
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
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

        // --- Parte das peças ---
        panelPecas.setLayout(new BorderLayout(10, 10));

        // Título
        JPanel panelTituloPecas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTituloPecas.setBackground(panelPecas.getBackground());
        panelTituloPecas.add(tituloPecas);
        panelPecas.add(panelTituloPecas, BorderLayout.NORTH);

        // Conteúdo central (form + tabela)
        JPanel panelConteudo = new JPanel(new BorderLayout(10, 10));
        panelConteudo.setBackground(panelPecas.getBackground());
        panelConteudo.add(formPecas, BorderLayout.NORTH);
        panelConteudo.add(new JScrollPane(tabelaPecas), BorderLayout.CENTER);

        panelPecas.add(panelConteudo, BorderLayout.CENTER);

        // --- Unindo cadastro + peças ---
        panelMeio.setLayout(new BorderLayout(10, 10));
        panelMeio.add(panelCentro, BorderLayout.NORTH);
        panelMeio.add(panelPecas, BorderLayout.CENTER);

        // Baixo
        panelBaixo.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        panelBaixo.add(btnCancelar);
        panelBaixo.add(btnConcluir);
    }

    void accoes() {
        btnConcluir.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnAdicionar.addActionListener(e -> {
            String item = comboItem.getSelectedItem().toString();
            int qtd = (int) spinnerQtd.getValue();
            modeloTabela.addRow(new Object[]{item, qtd});
        });
    }


    private boolean nomeJaExiste(String nome) {
        // Buscar na lista de clientes da tela principal
        ListaClientes listaClientes = telaPrincipal.getListaClientes();

        if (listaClientes != null && listaClientes.inicio != null) {
            Cliente atual = listaClientes.inicio;
            while (atual != null) {
                if (atual.nome.equalsIgnoreCase(nome)) {
                    return true; // Nome já existe
                }
                atual = atual.proximo;
            }
        }
        return false; // Nome não existe
    }

    // NOVO MÉTODO: Mostrar detalhes do cliente existente
    private void mostrarClienteExistente(String nome) {
        ListaClientes listaClientes = telaPrincipal.getListaClientes();
        Cliente clienteExistente = listaClientes.buscarClientePorNome(nome);

        if (clienteExistente != null) {
            int id = listaClientes.obterIDDoCliente(clienteExistente);

            String mensagem = String.format(
                    "⚠️ CLIENTE JA CADASTRADO!\n\n" +
                            "Já existe um cliente com o nome '%s':\n\n" +
                            "📋 Informações do Cliente Existente:\n" +
                            "➤ ID: %d\n" +
                            "➤ Nome: %s\n" +
                            "➤ Sexo: %s\n" +
                            "➤ Idade: %d anos\n" +
                            "➤ Hora de Entrada: %s\n\n" +
                            "💰 Valor a Pagar: %,.2f MZN\n" +
                            "❌ Não é permitido cadastrar clientes com o mesmo nome.",
                    nome, id, clienteExistente.nome, clienteExistente.sexo,
                    clienteExistente.idade, clienteExistente.horaEntrada,
                    clienteExistente.valorApagar
            );

            JOptionPane.showMessageDialog(frame,
                    mensagem,
                    "Cliente Já Existente",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnConcluir){
            // Validação do nome
            if (txtNome.getText() == null || txtNome.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(null,
                        "Por favor, insira o seu nome para continuar.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String nomeCliente = txtNome.getText().trim();

            // NOVA VALIDAÇÃO: Verificar se nome já existe
            if (nomeJaExiste(nomeCliente)) {
                mostrarClienteExistente(nomeCliente);
                txtNome.requestFocus(); // Dar foco no campo de nome
                txtNome.selectAll(); // Selecionar todo o texto
                return; // Impedir o cadastro
            }

            // Validação do carrinho
            if (modeloTabela.getRowCount() == 0){
                JOptionPane.showMessageDialog(null,
                        "O carrinho está vazio! Por favor, adicione peças antes de continuar.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Se passou todas as validações, prosseguir com o cadastro
            String sexoCliente = escolherSexo.getSelectedItem().toString();
            int idadeCliente = (int) esconherIdade.getValue();
            String horaRegistro = java.time.LocalDateTime.now()
                    .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

            frame.dispose();
            new Tela_Recibo(nomeCliente, sexoCliente, idadeCliente, horaRegistro, modeloTabela, telaPrincipal);

        }
        if (e.getSource() == btnCancelar){
            // Alterado para apenas fechar a janela, não encerrar o programa
            frame.dispose();
            telaPrincipal.getFrame().setVisible(true); // Mostrar a tela principal novamente
        }
    }
}