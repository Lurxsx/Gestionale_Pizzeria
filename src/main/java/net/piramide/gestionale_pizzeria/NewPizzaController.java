package net.piramide.gestionale_pizzeria;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    @FXML
    private SearchableComboBox<String> searchableComboBox;
    @FXML
    private ListView<String> ingredientiListView;
    private Map<String, List<String>> pizzaIngredienti;
    @FXML
    private Button confirmPizzaButton;
    public static String stringaprova;
    public static Pizza newPizza;

    private Sistema Sistema;
    public void initialize() {
        //HASHMAP per pizza = ingredienti
        pizzaIngredienti = new HashMap<>();
        pizzaIngredienti.put("Margherita", List.of("salsa di pomodoro", "mozzarella"));
        pizzaIngredienti.put("Marinara", List.of("salsa di pomodoro", "aglio", "origano"));
        pizzaIngredienti.put("Diavola", List.of("salsa di pomodoro", "mozzarella", "salame piccante"));

        ObservableList<String> pizzaList = FXCollections.observableArrayList(pizzaIngredienti.keySet());
        searchableComboBox.setItems(pizzaList);

        searchableComboBox.setOnAction(event -> handlePizzaSelection());



        //ObservableList<String> items = FXCollections.observableArrayList("Margherita", "Diavola", "4 formaggi");
        //searchableComboBox.getItems().addAll(items);
    }

    private void handlePizzaSelection() {
        // Gestisci la selezione della pizza qui
        String pizzaSelezionata = searchableComboBox.getValue();
        List<String> ingredienti = pizzaIngredienti.get(pizzaSelezionata);

        if (ingredienti != null) {
            System.out.println("Ingredienti per la pizza " + pizzaSelezionata + ": " + ingredienti);
            ObservableList<String> ingredientiObservableList = FXCollections.observableArrayList(ingredienti);
            ingredientiListView.setItems(ingredientiObservableList);
            newPizza = new Pizza("Pizza " + pizzaSelezionata, "normale", ingredienti);
        } else {
            System.out.println("Pizza non trovata: " + pizzaSelezionata);
        }
    }


    public void onConfirmButtonClick(ActionEvent actionEvent) {
        stringaprova = newPizza.getNome();
        closeStage();

    }

    private void closeStage() {
        ((Stage) confirmPizzaButton.getScene().getWindow()).close();
    }
}
