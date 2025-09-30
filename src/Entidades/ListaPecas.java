package src.Entidades;

public class ListaPecas {
     Peca inicio;

    public ListaPecas(){
        this.inicio = null;
    }

    // Adicionar peça no final da lista
    public void adicionarPeca(Peca p) {
        if (inicio == null) {
            inicio = p;
        } else {
            Peca atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = p;
        }
    }

    // Calcular total de items
    public int totalItems() {
        int total = 0;
        Peca atual = inicio;
        while (atual != null) {
            total += atual.quantidade;
            atual = atual.proximo;
        }
        return total;
    }

    // Calcular total a pagar
    public int totalPagar() {
        int total = 0;
        Peca atual = inicio;
        while (atual != null) {
            total += atual.quantidade * atual.precoUnitario;
            atual = atual.proximo;
        }
        return total;
    }
}

