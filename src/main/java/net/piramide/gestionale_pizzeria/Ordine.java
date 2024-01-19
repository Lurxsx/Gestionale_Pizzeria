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
