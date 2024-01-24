package net.piramide.gestionale_pizzeria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;



public class TakeOrderController {
    private ArrayList<Pizza> listaPizze;
    @FXML
    private Button pizzaListButton;
    @FXML
    private Button newPizzaButton;
    @FXML
    private VBox VboxLista;
    Ordine ordine;
    public void initialize() {
        ordine = new Ordine();
        listaPizze = new ArrayList<>();
    }

    public void onNewPizzaButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newPizza.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        Pizza nuovaPizza = NewPizzaController.newPizza;
        listaPizze.add(listaPizze.size(), nuovaPizza);
        ordine.setPizze(listaPizze);
        VboxLista.getChildren().clear();
        for (int i = 0; i < listaPizze.size(); i++) {
            Button button = new Button();
            button.setStyle(pizzaListButton.getStyle());
            button.setText(pizzaListButton.getText());
            button.setPrefWidth(pizzaListButton.getPrefWidth());
            button.setPrefHeight(pizzaListButton.getPrefHeight());
            button.setText(listaPizze.get(i).getNome() + ": " + listaPizze.get(i).getIngredienti());

            VboxLista.getChildren().add(i, button);

        }

        VboxLista.getChildren().add(listaPizze.size(), newPizzaButton);
    }

    public void onTestButtonClick(ActionEvent actionEvent) {



    }
}
