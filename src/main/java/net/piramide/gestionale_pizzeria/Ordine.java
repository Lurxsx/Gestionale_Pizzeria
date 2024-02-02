package net.piramide.gestionale_pizzeria;

import java.io.IOException;
import java.util.ArrayList;


public class Ordine {
    private ArrayList<Pizza> pizze;
    private int nPizze;
    private String nome;
    private String via;
    private String numero_telefonico;
    private int nOrdine;
    private int stato; //0 = creato, 1 = in preparazione, 2 = pronto, 3 = in consegna, 4 = consegnato
    private DatabasePizze dbp= new DatabasePizze();
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

    public int getnPizze() {
        return pizze.size();
    }

    public ArrayList<Pizza> getPizze() {
        return pizze;
    }
    public Pizza getPizzaAt(int i) {
        return pizze.get(i);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void getListaIngredientiPiuMeno(int i) throws IOException {
        ArrayList<String> ingredientipiumeno = null;
        System.out.println("AA-->" + pizze.get(i).getIngredienti().size());
        Pizza pizzaTemp = pizze.get(i);

        ArrayList<String> ingredientiTemp = dbp.getIngredientiFromName(pizzaTemp.getNome());
        /*
        for (int j = 0; j < ingredientiTemp.size(); j++) {
            System.out.println(ingredientiTemp.get(i));
        }

        ArrayList<String> intersection = new ArrayList<>(pizze.get(i).getIngredienti());
        intersection.retainAll(ingredientiTemp);

        // Confronta le dimensioni
        if (pizze.get(i).getIngredienti().size() > ingredientiTemp.size()) {
            // Elementi in più nel primo elenco
            pizze.get(i).getIngredienti().removeAll(ingredientiTemp);
            System.out.println("Elementi in più nel primo elenco: " + pizze.get(i).getIngredienti());

        } else if (ingredientiTemp.size() > pizze.get(i).getIngredienti().size()) {
            // Elementi in più nel secondo elenco
            ingredientiTemp.removeAll(pizze.get(i).getIngredienti());
            System.out.println("Elementi in più nel secondo elenco: " + ingredientiTemp);
        } else {
            System.out.println("I due elenchi sono identici.");
        }

 */

    }


    public int getnOrdine() {
        return nOrdine;
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
