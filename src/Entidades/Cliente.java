package src.Entidades;

public class Cliente {
    //Atributos
    public String nome;
    public String sexo;
    public String horaEntrada;
    public int idade;
    public int calcas;
    public int camisetas;
    public int camisolas;
    public int vestidos;
    public int totItems;
    public double valorApagar;

    public Cliente(String nome, String sexo, int idade, String horaEntrada, int calcas, int camisetas, int camisolas, int vestidos, int totItems, double valorApagar){
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.horaEntrada = horaEntrada;
        this.calcas = calcas;
        this.camisetas = camisetas;
        this.camisolas = camisolas;
        this.vestidos = vestidos;
        this.totItems = totItems;
        this.valorApagar = valorApagar;
    }

    @Override
    public String toString() {
        return nome + ";" + sexo + ";" + idade + ";" + horaEntrada + ";" + calcas + ";" + camisetas + ";" + camisolas + ";" + vestidos + ";" + totItems + ";" + valorApagar;
    }
}
