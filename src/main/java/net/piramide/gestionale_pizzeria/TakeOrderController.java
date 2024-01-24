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
    public void initialize() { //inizializazione
        ordine = new Ordine();  //creazione istanza Ordine
        listaPizze = new ArrayList<>(); //creazione lista pizze (vuota)
    }

    public void onNewPizzaButtonClick(ActionEvent actionEvent) throws IOException { //Azione bottone "NUOVA PIZZA"
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newPizza.fxml")); //crea una nuova pagina con il file "newPizza.fxml"
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        Pizza nuovaPizza = NewPizzaController.newPizza; //l'oggetto creato nell'altro controller viene copiato in modo da avere le info sulla pizza
        listaPizze.add(listaPizze.size(), nuovaPizza); //aggiunta nuova pizza nella lista delle pizze ordinate
        ordine.setPizze(listaPizze); //aggiunta lista di pizze all'ordine, instanza della classe Ordine
        updateList();

    }

    public void updateList(){   //AGGIORNA LA LISTA DELLE PIZZE ORDINATE
        VboxLista.getChildren().clear(); //cancella tutto il contenuto della Vbox
        for (int i = 0; i < listaPizze.size(); i++) { //per ogni ciclo, crea un nuovo bottone con le informazioni della pizza
            Button button = new Button();
            button.setStyle(pizzaListButton.getStyle());
            button.setText(pizzaListButton.getText());
            button.setPrefWidth(pizzaListButton.getPrefWidth());
            button.setPrefHeight(pizzaListButton.getPrefHeight());
            button.setText(listaPizze.get(i).getNome() + ": " + listaPizze.get(i).getIngredienti());
            VboxLista.getChildren().add(i, button); //aggiunge il bottone creato alla Vbox con il metodo getChildren
        }
        VboxLista.getChildren().add(listaPizze.size(), newPizzaButton); //aggiunge il bottone "nuova pizza" in fondo
    }

    public void onTestButtonClick(ActionEvent actionEvent) {



    }
}
