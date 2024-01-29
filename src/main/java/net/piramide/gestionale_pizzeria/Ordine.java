package net.piramide.gestionale_pizzeria;

import java.util.ArrayList;


public class Ordine {
    private ArrayList<Pizza> pizze;
    private int nPizze;
    private String nome;
    private String via;
    private String numero_telefonico;
    private int nOrdine;
    private int stato; //0 = creato, 1 = in preparazione, 2 = pronto, 3 = in consegna, 4 = consegnato

    public Ordine(ArrayList<Pizza> vettorePizze, String nome, String via, String numero_telefonico, int nOrdine){
        this.pizze = vettorePizze;
        this.nPizze = pizze.size();
        this.nome = nome;
        this.via = via;
        this.numero_telefonico = numero_telefonico;
        this.stato = 0;
        this.nOrdine = nOrdine;
    }
    public Ordine(){

    }

    public void setPizze(ArrayList<Pizza> pizze) {
        this.pizze = pizze;
    }

    public void setnOrdine(int nOrdine) {
        this.nOrdine = nOrdine;
    }

    public void setNumero_telefonico(String numero_telefonico) {
        this.numero_telefonico = numero_telefonico;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public void setStato(int stato) {
        this.stato = stato;
    }

    public void setnPizze(int nPizze) {
        this.nPizze = nPizze;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "pizze=" + pizze +
                ", nPizze=" + nPizze +
                ", nome='" + nome + '\'' +
                ", via='" + via + '\'' +
                ", numero_telefonico='" + numero_telefonico + '\'' +
                ", nOrdine=" + nOrdine +
                ", stato=" + stato +
                '}';
    }


}
