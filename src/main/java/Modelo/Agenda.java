package Modelo;

public class Agenda {
    private int indice;
    private String nome, telefone;

    public Agenda(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public Agenda(int indice, String nome, String telefone) {
        this.indice = indice;
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
