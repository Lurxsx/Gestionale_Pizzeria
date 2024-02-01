package net.piramide.gestionale_pizzeria;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.SearchableComboBox;

public class NewPizzaController {
    private ObservableList<String> ingredientiObservableList;

    private ObservableList<String> ingredientiSelezionati = FXCollections.observableArrayList();
    @FXML
    private SearchableComboBox<String> searchableComboBox;
    @FXML
    private ListView<String> ingredientiListView;
    private Map<String, List<String>> pizzaIngredienti;
    @FXML
    private Button confirmPizzaButton;
    public static String stringaprova;
    public static Pizza newPizza;
    DatabasePizze dbp = new DatabasePizze();
    private Sistema Sistema;
    public static boolean confirmed = false;
    public void initialize() throws IOException {
        // Inizializza l'ObservableList
        ingredientiObservableList = FXCollections.observableArrayList();
        ingredientiListView.setItems(ingredientiObservableList);

        //HASHMAP per pizza = ingredienti
        pizzaIngredienti = dbp.getPizzaIngredientsHashMap();


        ObservableList<String> pizzaList = FXCollections.observableArrayList(pizzaIngredienti.keySet());
        searchableComboBox.setItems(pizzaList);

        searchableComboBox.setOnAction(event -> {
            try {
                handlePizzaSelection();
            } catch (IOException e) {
            }
        });



        //ObservableList<String> items = FXCollections.observableArrayList("Margherita", "Diavola", "4 formaggi");
        //searchableComboBox.getItems().addAll(items);
    }

    private void handlePizzaSelection() throws IOException {
        // Gestisci la selezione della pizza qui
        String pizzaSelezionata = searchableComboBox.getValue();
        List<String> ingredienti = pizzaIngredienti.get(pizzaSelezionata);

        if (ingredienti != null) {
            System.out.println("Ingredienti per la pizza " + pizzaSelezionata + ": " + ingredienti);
            ingredientiObservableList.setAll(ingredienti);
            newPizza = new Pizza("Pizza " + pizzaSelezionata, "normale", ingredienti);
        } else {
            System.out.println("Pizza non trovata: " + pizzaSelezionata);
        }
    }


    public void onConfirmButtonClick(ActionEvent actionEvent) {
        stringaprova = newPizza.getNome();
        confirmed = true;
        closeStage();

    }

    private void closeStage() {
        ((Stage) confirmPizzaButton.getScene().getWindow()).close();
    }

    public void onAggiungiButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IngredientiAggiungereController.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        // Disabilita la finestra sottostante
        Node root2 = ((Node) actionEvent.getSource()).getScene().getRoot();
        root2.setDisable(true);

        Stage stage = new Stage();
        stage.setScene(new Scene(root1));

        // Ottieni il controller degli ingredienti
        IngredientiAggiungereController ingredientiAggiungereController = fxmlLoader.getController();

        // Aggiungi un listener per gestire la chiusura della nuova finestra
        stage.setOnHiding(event -> {
            // Riabilita la finestra principale quando la finestra degli ingredienti è chiusa
            root2.setDisable(false);

            // Ottieni l'ingrediente selezionato dal controller degli ingredienti
            String selectedIngredient = ingredientiAggiungereController.getSelectedIngredient();
            System.out.println("Ingrediente selezionato: " + selectedIngredient);

            // Esegui le azioni necessarie con l'ingrediente selezionato
            // Ad esempio, puoi aggiungerlo alla pizza o fare altre operazioni
            if (selectedIngredient != null && !selectedIngredient.isEmpty()) {
                newPizza.aggiungiIngrediente(selectedIngredient);
                ingredientiSelezionati.add(selectedIngredient);

                // Aggiorna l'interfaccia utente o esegui altre azioni necessarie
                // ...

                // Stampa di debug
                System.out.println("Ingrediente aggiunto alla pizza: " + selectedIngredient);
                // Notifica all'adapter che i dati sono cambiati
//                this.adapter.notifyDataSetChanged();
                ingredientiListView.getItems().clear();
                ingredientiListView.getItems().addAll(newPizza.getIngredienti());
            }
        });

        stage.showAndWait();
    }


    public void onTogliButtonClick(ActionEvent event) {
        // Rimuovi l'ingrediente selezionato dalla lista
        String ingredienteSelezionato = ingredientiListView.getSelectionModel().getSelectedItem();
        if (ingredienteSelezionato != null) {
            newPizza.rimuoviIngrediente(ingredienteSelezionato);
            System.out.println("Ingredienti rimossi: " + newPizza.getIngredienti());
            ingredientiSelezionati.remove(ingredienteSelezionato); // Aggiorna l'interfaccia utente
            ingredientiListView.getItems().clear();
            ingredientiListView.getItems().addAll(newPizza.getIngredienti());
        }
    }

}