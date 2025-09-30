package src.Entidades;

public class Peca {
    //Atributos
    String tipo;
    int quantidade;
    int precoUnitario;
    Peca proximo;

    public Peca(String tipo, int quantidade, int precoUnitario) {
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.proximo = null;
    }
}
