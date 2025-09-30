package src.UI_Telas;

import src.Entidades.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

class Tela_Recibo implements ActionListener {
    private JFrame frame;
    private JTextArea areaRecibo;
    private JButton btnContinuar;
    private DefaultTableModel tabelaPecas;
    private String nome, sexo, horaRegistro;
    private int idade;
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

        criarTela();
        montarRecibo();
        frame.setVisible(true);
    }

    private void criarTela(){
        frame = new JFrame("Recibo");
        frame.setSize(400,500);
        frame.setLayout(new BorderLayout());

        areaRecibo = new JTextArea();
        areaRecibo.setEditable(false);
        areaRecibo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        frame.add(new JScrollPane(areaRecibo), BorderLayout.CENTER);

        btnContinuar = new JButton("Continuar");
        frame.add(btnContinuar, BorderLayout.SOUTH);
        btnContinuar.addActionListener(this);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void montarRecibo(){
        int totalPecas=0;
        int totalPagar=0;

        StringBuilder sb = new StringBuilder();
        sb.append("------ Lavandaria AroEd ------\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Sexo: ").append(sexo).append("\n");
        sb.append("Idade: ").append(idade).append("\n");
        sb.append("Hora Entrada: ").append(horaRegistro).append("\n");
        sb.append("------------------------------\n");
        sb.append("Carrinho:\n");

        int calcas=0, camisetas=0, camisolas=0, vestidos=0;

        for(int i=0;i<tabelaPecas.getRowCount();i++){
            String item = tabelaPecas.getValueAt(i,0).toString();
            int qtd = (int)tabelaPecas.getValueAt(i,1);
            int precoUnit = switch(item){
                case "Calca" -> precoCalca;
                case "Camiseta" -> precoCamiseta;
                case "Camisola" -> precoCamisola;
                case "Vestido" -> precoVestido;
                default -> 0;
            };
            int precoItem = precoUnit*qtd;
            totalPecas += qtd;
            totalPagar += precoItem;

            sb.append(String.format("%-10s x %2d = %3d\n", item, qtd, precoItem));

            switch(item){
                case "Calca" -> calcas += qtd;
                case "Camiseta" -> camisetas += qtd;
                case "Camisola" -> camisolas += qtd;
                case "Vestido" -> vestidos += qtd;
            }
        }

        sb.append("------------------------------\n");
        sb.append("Total de peças: ").append(totalPecas).append("\n");
        sb.append("Total a pagar: ").append(totalPagar).append(" MZN\n");
        sb.append("------------------------------\n");
        sb.append("Obrigado pela preferencia!");

        areaRecibo.setText(sb.toString());

        // Criar objeto Cliente
        Cliente cliente = new Cliente(nome, sexo, idade, horaRegistro,
                calcas, camisetas, camisolas, vestidos, totalPecas, totalPagar);

        // Gravar no arquivo
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("src/BaseDados/Clientes.txt",true))){
            writer.write(cliente.toString());
            writer.newLine();
        }catch(Exception e){ e.printStackTrace(); }

        // Adicionar na tabela da tela principal
        telaPrincipal.adicionarCliente(cliente);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnContinuar){
            frame.dispose();
            telaPrincipal.getFrame().setVisible(true);
        }
    }
}
