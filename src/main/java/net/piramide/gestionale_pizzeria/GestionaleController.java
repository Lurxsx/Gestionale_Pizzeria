package net.piramide.gestionale_pizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionaleController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Sistema sistema;
    private Stage mainStage;
    private Scene currentScene;

    public void setSistema(Sistema sistema){
        this.sistema = sistema;
    }
    public void setStage(Stage stage){
        this.mainStage = stage;
    }
    public void setScene(Scene scene){
        this.currentScene = scene;
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