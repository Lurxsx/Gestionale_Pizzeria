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
        Sistema sistema = new Sistema();

        FXMLLoader fxmlLoader = new FXMLLoader(GestionaleApplication.class.getResource("mainMenu.fxml"));
        Parent root = fxmlLoader.load();
        Scene mainMenuScene = new Scene(root);

        // Ottieni il controller
        GestionaleController gestionaleController = fxmlLoader.getController();

        // Crea un'istanza di Sistema
        // Chiama il metodo setSistema del controller
        gestionaleController.setSistema(sistema);

        stage.setTitle("GESTIONALE");
        stage.setScene(mainMenuScene);
        stage.show();




    }



    public static void main(String[] args) {
        launch();
    }
}