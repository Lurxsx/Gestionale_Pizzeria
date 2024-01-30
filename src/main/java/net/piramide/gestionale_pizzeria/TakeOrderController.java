package net.piramide.gestionale_pizzeria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class TakeOrderController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public VBox vBoxListaPrezzi;
    public AnchorPane APContenitorePizzaPrezzo;
    public Label lblNomePizza;
    public Label lblPrezzoPizza;
    public Label lblPrezzoTotale;
    public TextField txtTel;
    public TextField txtNom;
    public TextField txtCity;
    public TextField txtIndirizzo;
    private ArrayList<Pizza> listaPizze;
    @FXML
    private Button pizzaListButton;
    @FXML
    private Button newPizzaButton;
    @FXML
    private VBox VboxLista;
    Ordine ordine;
    Map<String, Double> listaPrezziPizza;
    private Sistema Sistema;
    DatabasePizze dbp = new DatabasePizze();
    public void initialize() { //inizializazione
        ordine = new Ordine();  //creazione istanza Ordine
        listaPizze = new ArrayList<>(); //creazione lista pizze (vuota)
        // Creazione della mappa gusto pizza - prezzo
        listaPrezziPizza= new HashMap<>();

        // Aggiunta degli elementi alla mappa


    }

    public void setSistema(Sistema Sistema){
        this.Sistema = Sistema;
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

    public void updateList() throws IOException {   //AGGIORNA LA LISTA DELLE PIZZE ORDINATE
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


        //parte del vBoxListaPrezzi
        //vBoxListaPrezzi.getChildren().clear(); //cancella tutto il contenuto della Vbox
        System.out.println(lblNomePizza.getText());
        System.out.println(lblPrezzoPizza.getText());

        vBoxListaPrezzi.getChildren().clear();
        for (int i = 0; i < listaPizze.size(); i++) {
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setStyle(APContenitorePizzaPrezzo.getStyle());
            anchorPane.setPrefHeight(APContenitorePizzaPrezzo.getPrefHeight());
            anchorPane.setPrefWidth(APContenitorePizzaPrezzo.getPrefHeight());

            Label nome = new Label();
            nome.setStyle(lblNomePizza.getStyle());
            nome.setFont(lblNomePizza.getFont());
            nome.setText(listaPizze.get(i).getNome());
            nome.setPrefHeight(lblNomePizza.getPrefHeight());
            nome.setPrefWidth(lblNomePizza.getPrefWidth());
            System.out.println(nome.getText());

            Label prezzo = new Label();
            prezzo.setStyle(lblPrezzoPizza.getStyle());
            prezzo.setFont(lblPrezzoPizza.getFont());

            // Formatta il prezzo con due decimali e il simbolo dell'euro
            prezzo.setText(String.format(Locale.US, "€ %.2f", dbp.getPrezzo(nome.getText())));

            prezzo.setPrefHeight(lblPrezzoPizza.getPrefHeight());
            prezzo.setPrefWidth(lblPrezzoPizza.getPrefWidth());

            HBox hbox = new HBox();
            hbox.getChildren().addAll(nome, prezzo);

            anchorPane.getChildren().add(hbox);
            vBoxListaPrezzi.getChildren().add(i, anchorPane);
        }



        //parte dell'aggiornamenteo del prezzp
        double SommaTotale = 0;
        for (int i = 0; i < listaPizze.size(); i++) {
            SommaTotale = SommaTotale + listaPrezziPizza.get(listaPizze.get(i).getNome());
        }
        System.out.println(SommaTotale);
        lblPrezzoTotale.setText(String.format(Locale.US, "€ %.2f", SommaTotale));
    }

    public void onConfirmButtonClick(ActionEvent event) throws IOException {
        if(txtCity.getText() != null && txtIndirizzo.getText() != null && txtNom.getText() != null && txtTel.getText() != null /*&& listaPizze.size() > 1*/) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
            Parent root = loader.load();

            // Ottieni il controller del GestionaleController
            GestionaleController gestionaleController = loader.getController();

            // Passa il sistema al GestionaleController
            gestionaleController.setSistema(this.Sistema);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
