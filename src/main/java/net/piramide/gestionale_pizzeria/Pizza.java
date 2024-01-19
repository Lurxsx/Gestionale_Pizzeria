package net.piramide.gestionale_pizzeria;

import java.util.List;

public class Pizza {
    private String nome;
    private String dimensione;
    private List<String> ingredienti;

    public Pizza(String nome, String dimensione, List<String> ingredienti) {
        this.nome = nome;
        this.dimensione = dimensione;
        this.ingredienti = ingredienti;
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

    // Esempio di utilizzo
    public static void main(String[] args) {
        // Lista di ingredienti per una pizza margherita
        List<String> ingredientiMargherita = List.of("salsa di pomodoro", "mozzarella", "basilico");

        // Creazione di un'istanza della classe Pizza
        Pizza pizzaMargherita = new Pizza("Margherita", "Media", ingredientiMargherita);

        // Stampa della descrizione della pizza
        System.out.println(pizzaMargherita.descrizione());
    }
}

