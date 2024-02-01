package net.piramide.gestionale_pizzeria;

import java.util.ArrayList;

public class Sistema {
    private ArrayList<Ordine> ordini;
    private int countOrdini = 0;

    public Sistema(){
        ordini = new ArrayList<>();
        this.countOrdini = 0;
        System.out.println("avviato il sis");
    }

    public void make_Order(ArrayList<Pizza> vettorePizze, String nome, String via, String numero_telefonico){
        Ordine tempOrdine = new Ordine(vettorePizze, nome, via, numero_telefonico, countOrdini);

        //aggiunta all'array
        ordini.add(tempOrdine);
        countOrdini++;

    }

    public void make_Order(Ordine Ordine){
        ordini.add(Ordine);
        countOrdini++;
    }

    public int getCountOrdini() {
        return ordini.size();
    }

    public ArrayList<Ordine> getOrdini() {
        return ordini;
    }
    public Ordine getOrdineAt(int i) {
        return ordini.get(i);
    }

    public void stampaOrdini(){
        for (Ordine ordine : ordini) {
            System.out.println(ordine.toString());
        }
    }
}
