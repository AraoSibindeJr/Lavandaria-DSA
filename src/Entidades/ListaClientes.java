package src.Entidades;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;


public class ListaClientes {
   public Cliente inicio;
    private int nextID;

    public ListaClientes(){
        this.inicio = null;
        this.nextID =1;
    }

    //AddCliente no final da
    public void adicionarCliente(Cliente c) {
        if (inicio == null) {
            inicio = c;
        } else {
            Cliente atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = c;
        }
    }

    //Remover Cliente por ID
    public boolean removerCliente(int id) {
        if (inicio == null) return false;

        // Caso especial: remover o primeiro elemento
        if (getID(inicio) == id) {
            inicio = inicio.proximo;
            return true;
        }

        // Buscar o cliente anterior ao que sera removido
        Cliente anterior = inicio;
        Cliente atual = inicio.proximo;

        while (atual != null) {
            if (getID(atual) == id) {
                anterior.proximo = atual.proximo;
                return true;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        return false;
    }

    //Remover cliente por Nome
    public boolean removerClientePorNome(String nome) {
        if (inicio == null) return false;

        // Caso especial: remover o primeiro elemento se for o nome procurado
        if (inicio.nome.equalsIgnoreCase(nome)) {
            inicio = inicio.proximo;
            return true;
        }

        // Buscar o cliente anterior ao que será removido
        Cliente anterior = inicio;
        Cliente atual = inicio.proximo;

        while (atual != null) {
            if (atual.nome.equalsIgnoreCase(nome)) {
                anterior.proximo = atual.proximo;
                return true;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        return false;
    }

    //Buscar multiplos clientes com o mesmo nome
    public java.util.List<Cliente> buscarTodosClientesPorNome(String nome) {
        java.util.List<Cliente> resultados = new java.util.ArrayList<>();
        Cliente atual = inicio;
        while (atual != null) {
            if (atual.nome.equalsIgnoreCase(nome)) {
                resultados.add(atual);
            }
            atual = atual.proximo;
        }
        return resultados;
    }

    // Buscar cliente por nome
    public Cliente buscarClientePorNome(String nome) {
        Cliente atual = inicio;
        while (atual != null) {
            if (atual.nome.equalsIgnoreCase(nome)) {
                return atual;
            }
            atual = atual.proximo;
        }
        return null;
    }

    //Obter ID real de um cliente específico
    public int obterIDDoCliente(Cliente cliente) {
        Cliente atual = inicio;
        int id = 1;
        while (atual != null) {
            if (atual == cliente) {
                return id;
            }
            atual = atual.proximo;
            id++;
        }
        return -1;
    }

    //Buscar clientes que contenham o texto no nome (busca parcial)
    public List<Cliente> buscarClientesPorTexto(String texto) {
        List<Cliente> resultados = new ArrayList<>();
        Cliente atual = inicio;
        while (atual != null) {
            if (atual.nome.toLowerCase().contains(texto.toLowerCase())) {
                resultados.add(atual);
            }
            atual = atual.proximo;
        }
        return resultados;
    }


    //Buscar Cliente por ID
    public Cliente buscarCliente(int id) {
        Cliente atual = inicio;
        while (atual != null) {
            if (getID(atual) == id) {
                return atual;
            }
            atual = atual.proximo;
        }
        return null;
    }

    // Atualizar tabela com dados da lista ligada
    public void atualizarLista(DefaultTableModel modelo) {
        modelo.setRowCount(0); // Limpar tabela
        Cliente atual = inicio;
        int id = 1;

        while (atual != null) {
            modelo.addRow(new Object[]{
                    id++, atual.nome, atual.sexo, atual.idade,
                    atual.horaEntrada, atual.calcas, atual.camisetas,
                    atual.camisolas, atual.vestidos, atual.totItems,
                    atual.valorApagar
            });
            atual = atual.proximo;
        }
    }

    //Metodo pra simular ID com base na posicao(Auxiliar)
    private int getID(Cliente c) {
        Cliente atual = inicio;
        int id = 1;
        while (atual != null) {
            if (atual == c) return id;
            atual = atual.proximo;
            id++;
        }
        return -1;
    }

    // Obter ID real de um cliente na lista
    public int obterID(Cliente cliente) {
        return getID(cliente);
    }

    // Contar total de clientes
    public int totalClientes() {
        int count = 0;
        Cliente atual = inicio;
        while (atual != null) {
            count++;
            atual = atual.proximo;
        }
        return count;
    }



}
