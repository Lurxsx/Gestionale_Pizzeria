package net.piramide.gestionale_pizzeria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    @FXML
    private VBox vBoxListaPrezzi;
    @FXML
    private AnchorPane APContenitorePizzaPrezzo;
    @FXML
    private Label lblNomePizza;
    @FXML
    private Label lblPrezzoPizza;
    @FXML
    private Label lblPrezzoTotale;
    @FXML
    private TextField txtTel;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtIndirizzo;

    public ArrayList<Pizza> listaPizze;
    @FXML
    private Button pizzaListButton;
    @FXML
    private Button newPizzaButton;
    @FXML
    private VBox VboxLista;
    Ordine ordine;
    Map<String, Double> listaPrezziPizza;
    private Sistema Sistema;
    Pizza nuovaPizza;

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

    public void onNewPizzaButtonClick(ActionEvent actionEvent) throws IOException {
        NewPizzaController.setNewPizza(null);
        try {
            // Azione bottone "NUOVA PIZZA"
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newPizza.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            NewPizzaController.setConfirmed(false); //imposta bottone conferma su false
            // Disabilita la finestra sottostante
            Node root2 = ((Node) actionEvent.getSource()).getScene().getRoot();
            root2.setDisable(true);

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));

            // Aggiungi un listener per gestire la chiusura della nuova finestra
            stage.setOnHiding(event -> {
                // Riabilita la finestra sottostante quando la nuova finestra è chiusa
                root2.setDisable(false);
            });

            stage.showAndWait();

            // Azzeramento della pizza corrente

            // Se la nuova pizza è valida, la aggiungi alla lista
            if (NewPizzaController.getNewPizza() != null && NewPizzaController.isConfirmed()) {
                listaPizze.add(listaPizze.size(), NewPizzaController.getNewPizza());
                ordine.setPizze(listaPizze);
                updateList();
            }
        } catch (Exception e) {
            // Gestisci eventuali eccezioni qui
            e.printStackTrace();
        }
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
        //System.out.println(lblNomePizza.getText());
        //System.out.println(lblPrezzoPizza.getText());

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
            prezzo.setText(String.format(Locale.US, "€ %.2f",listaPizze.get(i).getPrezzo()));

            prezzo.setPrefHeight(lblPrezzoPizza.getPrefHeight());
            prezzo.setPrefWidth(lblPrezzoPizza.getPrefWidth());

            HBox hbox = new HBox();
            hbox.getChildren().addAll(nome, prezzo);

            anchorPane.getChildren().add(hbox);
            vBoxListaPrezzi.getChildren().add(i, anchorPane);
        }



        //parte dell'aggiornamenteo del prezzp
        // parte dell'aggiornamento del prezzo
        double SommaTotale = 0;
        for (int i = 0; i < listaPizze.size(); i++) {
            SommaTotale = SommaTotale + listaPizze.get(i).getPrezzo();
        }
        lblPrezzoTotale.setText(String.format(Locale.US, "€ %.2f", SommaTotale));

        //System.out.println(SommaTotale);
        lblPrezzoTotale.setText(String.format(Locale.US, "€ %.2f", SommaTotale));
    }

    public void onConfirmButtonClick(ActionEvent event) throws IOException {
        if(!txtCity.getText().equals("") && !txtIndirizzo.getText().equals("") && !txtNom.getText().equals("") && !txtTel.getText().equals("")) {
            Ordine Ordine = new Ordine(listaPizze, txtNom.getText(),txtIndirizzo.getText(), txtNom.getText(), 1);
            Sistema.make_Order(Ordine);
            System.out.println("Il sistema ha ora " + Sistema.getCountOrdini() + " ordini");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
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

    public void onIndietroButtonClick(ActionEvent event) throws IOException {
        // Creazione di un alert di conferma
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma Indietro");
        alert.setHeaderText("Sei sicuro di voler tornare al Gestionale?");

        // Configurazione dei pulsanti dell'alert
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        // Visualizza l'alert e attendi la risposta
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                try {
                    // Carica il GestionaleController
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
                    Parent root = loader.load();

                    // Ottieni il controller del GestionaleController
                    GestionaleController gestionaleController = loader.getController();

                    // Passa il sistema al GestionaleController
                    gestionaleController.setSistema(this.Sistema);

                    // Cambia la scena
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
