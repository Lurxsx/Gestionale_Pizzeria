package net.piramide.gestionale_pizzeria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionaleApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(GestionaleApplication.class.getResource("main-menu.fxml"));
        Parent root = fxmlLoader.load();
        Scene mainMenuScene = new Scene(root);

        // Ottieni il controller
        GestionaleController gestionaleController = fxmlLoader.getController();
        // Crea un'istanza di Sistema
        Sistema sistema = new Sistema();
        // Chiama il metodo setSistema del controller
        gestionaleController.setSistema(sistema);

        FXMLLoader currentOrderLoader = new FXMLLoader(GestionaleApplication.class.getResource("main-menu.fxml"));
        root = currentOrderLoader.load();
        Scene currentOrderScene = new Scene(root);

        gestionaleController.setStage(stage);
        gestionaleController.setScene(currentOrderScene);

        stage.setTitle("GESTIONALE");
        stage.setScene(mainMenuScene);
        stage.show();




    }



    public static void main(String[] args) {
        launch();
    }
}