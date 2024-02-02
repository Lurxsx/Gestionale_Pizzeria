package net.piramide.gestionale_pizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class GestionaleController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Sistema sistema;
    private Stage mainStage;
    private Scene currentScene;
    @FXML
private VBox vboxOpzioni;
    private ImageView imgv;

    public GestionaleController(){
    }

    public void setSistema(Sistema sistema){
        this.sistema = sistema;
    }
    public void setStage(Stage stage){
        this.mainStage = stage;
    }
    public void setScene(Scene scene){
        this.currentScene = scene;
    }

    @FXML
    private void initialize() {

        try {
            // Carica l'immagine
            Image img = new Image(getClass().getResourceAsStream("/net/piramide/gestionale_pizzeria/images/pizzasfondo.jpg"));

            // Imposta le dimensioni desiderate per l'ImageView
            ImageView imgv = new ImageView(img);
            imgv.setFitWidth(800);  // Imposta la larghezza desiderata
            imgv.setFitHeight(600); // Imposta l'altezza desiderata

            // Imposta l'ImageView come sfondo della VBox
            BackgroundImage backgroundImage = new BackgroundImage(
                    imgv.getImage(),  // Usa l'immagine dal ImageView
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            Background background = new Background(backgroundImage);
            vboxOpzioni.setBackground(background);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void switchToTakeOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("takeOrder.fxml"));
        Parent root = loader.load();

        // Ottieni il controller del TakeOrderController
        TakeOrderController takeOrderController = loader.getController();

        // Passa il sistema al TakeOrderController
        takeOrderController.setSistema(this.sistema);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToCurrentOrders(ActionEvent actionEvent) throws IOException {
        FXMLLoader currentOrdersLoader = new FXMLLoader(getClass().getResource("currentOrders.fxml"));
        Parent root = currentOrdersLoader.load();

        // Ottieni il controller del CurrentOrderController
        CurrentOrdersController currentOrdersController = currentOrdersLoader.getController();

        // Passa il sistema al CurrentOrderController
        currentOrdersController.setSistema(this.sistema);

        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public Sistema getSistema() {
        return sistema;
    }

}