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
    private Sistema Sistema;
    private Stage mainStage;
    private Scene currentScene;

    public void setSistema(Sistema Sistema){
        this.Sistema = Sistema;
    }
    public void setStage(Stage stage){
        this.mainStage = stage;
    }
    public void setScene(Scene scene){
        this.currentScene = scene;
    }



    public void switchToTakeOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("take_order.fxml"));
        Parent root = loader.load();

        // Ottieni il controller del TakeOrderController
        TakeOrderController takeOrderController = loader.getController();

        // Passa il sistema al TakeOrderController
        takeOrderController.setSistema(this.Sistema);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToCurrentOrders(ActionEvent actionEvent) {

        mainStage.setScene(currentScene);
        mainStage.show();

    }
}