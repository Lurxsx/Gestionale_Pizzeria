package net.piramide.gestionale_pizzeria;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IngredientiAggiungereController {
    public String selectedIngredient;
    public SearchableComboBox Ingredienti;

    public void initialize() {
        loadIngredientiFromFile("src/main/resources/IngredientiAggiungere");

        // Aggiungi un listener per gestire la selezione dell'ingrediente
        Ingredienti.setOnHiding(event -> {
            selectedIngredient = (String) Ingredienti.getValue();
            onIngredientiAggiungereClick(null); // Simula il click quando viene selezionato un ingrediente
        });
    }
    public void loadIngredientiFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Ingredienti.getItems().add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Gestisci l'errore se si verifica un problema durante la lettura del file
        }
    }



    public String getSelectedIngredient() {
        return selectedIngredient;
    }
    public void onIngredientiAggiungereClick(ActionEvent event) {
        selectedIngredient = (String) Ingredienti.getValue();  // Imposta la selezione nella variabile

        // Chiudi la finestra degli ingredienti dopo la selezione
        closeStage();
    }

    private void closeStage() {
        ((Stage) Ingredienti.getScene().getWindow()).close();
    }
}
