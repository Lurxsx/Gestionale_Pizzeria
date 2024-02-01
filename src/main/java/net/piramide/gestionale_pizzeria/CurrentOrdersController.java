package net.piramide.gestionale_pizzeria;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.util.Duration;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentOrdersController {

    private Sistema sistema;
    @FXML
    private VBox vboxOrdini;
    @FXML
    private HBox hboxOrdini;
    @FXML
    private AnchorPane anchorOrdine;

    public void setGestionale(GestionaleController gestionale) {
        this.gestionale = gestionale;
    }
    public void setSistema(Sistema sistema){
        this.sistema = sistema;
    }

    private GestionaleController gestionale;

    @FXML
    private Label clockLabel;

    @FXML
    private void initialize() {
        // Creazione della timeline per aggiornare l'orario ogni secondo
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), this::updateClock)
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }
    public void onTestClick(ActionEvent actionEvent) {

        if (hboxOrdini.getChildren().size() >= 5) {
            HBox hbox = new HBox();
            copyHbox(hboxOrdini, hbox);
            vboxOrdini.getChildren().add(hbox);
            hboxOrdini = hbox;
            hboxOrdini.getChildren().clear();

            AnchorPane anchorOrdine1 = new AnchorPane();
            copyAnchorPane(anchorOrdine, anchorOrdine1);
            hbox.getChildren().add(0, anchorOrdine1);
        } else {
            AnchorPane anchorOrdine1 = new AnchorPane();
            copyAnchorPane(anchorOrdine, anchorOrdine1);
            hboxOrdini.getChildren().add(0, anchorOrdine1);
        }
/*
        AnchorPane anchorOrdine1 = new AnchorPane();
        copyAnchorPane(anchorOrdine, anchorOrdine1);
        hboxOrdini.getChildren().add(0, anchorOrdine1);
*/
    }

    private void copyAnchorPane(AnchorPane sourceAnchorPane, AnchorPane targetAnchorPane) {
        // Rimuovi tutti gli elementi esistenti dal targetAnchorPane
        targetAnchorPane.getChildren().clear();

        // Clona gli elementi dall'AnchorPane sorgente al nuovo AnchorPane
        for (Node node : sourceAnchorPane.getChildren()) {
            Node clonedNode = cloneNode(node);
            targetAnchorPane.getChildren().add(clonedNode);
        }

        // Copia le proprietà specifiche dell'AnchorPane
        targetAnchorPane.setPrefSize(sourceAnchorPane.getPrefWidth(), sourceAnchorPane.getPrefHeight());
        targetAnchorPane.setLayoutX(sourceAnchorPane.getLayoutX());
        targetAnchorPane.setLayoutY(sourceAnchorPane.getLayoutY());
        // ... Altre proprietà specifiche dell'AnchorPane ...

        // Puoi anche copiare altri attributi o stili se necessario
        targetAnchorPane.setStyle(sourceAnchorPane.getStyle());
    }
    private void copyHbox(HBox sourceAnchorPane, HBox targetAnchorPane) {
        // Rimuovi tutti gli elementi esistenti dal targetAnchorPane
        targetAnchorPane.getChildren().clear();

        // Clona gli elementi dall'AnchorPane sorgente al nuovo AnchorPane
        for (Node node : sourceAnchorPane.getChildren()) {
            Node clonedNode = cloneNode(node);
            targetAnchorPane.getChildren().add(clonedNode);
        }

        // Copia le proprietà specifiche dell'AnchorPane
        targetAnchorPane.setPrefSize(sourceAnchorPane.getPrefWidth(), sourceAnchorPane.getPrefHeight());
        targetAnchorPane.setLayoutX(sourceAnchorPane.getLayoutX());
        targetAnchorPane.setLayoutY(sourceAnchorPane.getLayoutY());
        // ... Altre proprietà specifiche dell'AnchorPane ...

        // Puoi anche copiare altri attributi o stili se necessario
        targetAnchorPane.setStyle(sourceAnchorPane.getStyle());
    }

    private Node cloneNode(Node sourceNode) {
        if (sourceNode instanceof Label) {
            Label sourceLabel = (Label) sourceNode;
            Label clonedLabel = new Label(sourceLabel.getText());
            // Copia altre proprietà specifiche del Label se necessario
            clonedLabel.setLayoutX(sourceLabel.getLayoutX());
            clonedLabel.setLayoutY(sourceLabel.getLayoutY());
            return clonedLabel;
        } else if (sourceNode instanceof Button) {
            // Aggiungi gestione per il pulsante se necessario
        } else if (sourceNode instanceof ScrollPane) {
            // Aggiungi gestione per la ScrollPane se necessario
        } else if (sourceNode instanceof VBox) {
            // Aggiungi gestione per la VBox se necessario
        }
        // Puoi aggiungere casi per altri tipi di nodi se necessario

        // Ritorna una copia di base se il tipo di nodo non è gestito
        return new Label("Cloned Node");
    }

    private void updateClock(ActionEvent event) {
        // Ottenere l'orario corrente
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = dateFormat.format(now);

        // Aggiornare l'etichetta con l'orario corrente
        clockLabel.setText(formattedTime);
    }





}
