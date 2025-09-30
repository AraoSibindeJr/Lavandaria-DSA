package src.Entidades;

public class Cliente {
    //Atributos
    int id;
    String nome;
    String sexo;
    int idade;
    String horaEntrada;
    ListaPecas carrinho; // lista ligada de peças
    Cliente proximo;

    public Cliente(int id, String nome, String sexo, int idade, String horaEntrada) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.horaEntrada = horaEntrada;
        this.carrinho = new ListaPecas();
        this.proximo = null;
    }

}
