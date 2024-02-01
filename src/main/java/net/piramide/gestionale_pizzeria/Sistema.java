package net.piramide.gestionale_pizzeria;

import java.util.ArrayList;

public class Sistema {
    private ArrayList<Ordine> Ordini;
    private int countOrdini = 0;

    public Sistema(){
        Ordini = new ArrayList<>();
    }

    public void make_Order(ArrayList<Pizza> vettorePizze, String nome, String via, String numero_telefonico){
        Ordine tempOrdine = new Ordine(vettorePizze, nome, via, numero_telefonico, countOrdini);

        //aggiunta all'array
        Ordini.add(tempOrdine);
        countOrdini++;

    }

    public void make_Order(Ordine Ordine){
        Ordini.add(Ordine);
        countOrdini++;
    }

    public int getCountOrdini() {
        return countOrdini;
    }

    public ArrayList<Ordine> getOrdini() {
        return Ordini;
    }
    public Ordine getOrdineAt(int i) {
        return Ordini.get(i);
    }

    public void stampaOrdini(){
        for (Ordine ordine : Ordini) {
            System.out.println(ordine.toString());
        }
    }
}
