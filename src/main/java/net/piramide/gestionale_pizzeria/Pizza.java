package net.piramide.gestionale_pizzeria;

import java.io.IOException;
import java.util.List;

public class Pizza {
    private double prezzo;
    private DatabasePizze dbp = new DatabasePizze();
    private String nome;
    private String dimensione;
    private List<String> ingredienti;

    public Pizza(String nome, String dimensione, List<String> ingredienti) throws IOException {
        this.nome = nome;
        this.dimensione = dimensione;
        this.ingredienti = ingredienti;
        stabilisciprezzo();
    }


    public void stabilisciprezzo() throws IOException {
        String DaRimuovere = "Pizza";
        if (this.nome.startsWith(DaRimuovere)) {
            this.nome = this.nome.substring(DaRimuovere.length()).trim();
            this.nome = this.nome.substring(0, 1).toUpperCase() + this.nome.substring(1).toLowerCase();
        }
        this.prezzo = dbp.getPrezzo(this.nome);
    }


    public double getPrezzo(){
        return this.prezzo;
    }

    public String getNome() {
        return nome;
    }

    public String getDimensione() {
        return dimensione;
    }

    public List<String> getIngredienti() {
        return ingredienti;
    }

    public String descrizione() {
        return nome + " - Dimensione: " + dimensione + " - Ingredienti: " + String.join(", ", ingredienti);
    }


}

