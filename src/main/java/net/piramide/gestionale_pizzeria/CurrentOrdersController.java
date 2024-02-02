package net.piramide.gestionale_pizzeria;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DecimalFormat;
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
    @FXML
    private Label pizzaVboxLabel;
    private int cycleOrdini=0;

    private int ordineAttuale;

    public void setGestionale(GestionaleController gestionale) {
        this.gestionale = gestionale;
    }
    public void setSistema(Sistema sistema1) throws IOException {
        this.sistema = sistema1;
        //updateOrderPostit();
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



    private void updateOrderPostit() throws IOException {

        for (ordineAttuale = 0; ordineAttuale < sistema.getCountOrdini(); ordineAttuale++) {
            cycleOrdini=0;
            newOrdinePostit(sistema.getOrdineAt(ordineAttuale));
        }
        System.out.println(sistema.getCountOrdini());
    }

    private void newOrdinePostit(Ordine ordine) throws IOException {

        if (hboxOrdini.getChildren().size() >= 5) {
            HBox hbox = new HBox();
            copyHbox(hboxOrdini, hbox, ordine);
            vboxOrdini.getChildren().add(hbox);
            hboxOrdini = hbox;
            hboxOrdini.getChildren().clear();

            AnchorPane anchorOrdine1 = new AnchorPane();
            copyAnchorPane(anchorOrdine, anchorOrdine1, ordine);
            hbox.getChildren().add(0, anchorOrdine1);
        } else {
            AnchorPane anchorOrdine1 = new AnchorPane();
            copyAnchorPane(anchorOrdine, anchorOrdine1, ordine);
            hboxOrdini.getChildren().add(0, anchorOrdine1);
        }
    }

    private void copyAnchorPane(AnchorPane sourceAnchorPane, AnchorPane targetAnchorPane, Ordine ordine) throws IOException {
        // Rimuovi tutti gli elementi esistenti dal targetAnchorPane
        targetAnchorPane.getChildren().clear();

        // Copia gli elementi dall'AnchorPane sorgente al nuovo AnchorPane
        for (Node node : sourceAnchorPane.getChildren()) {
            cycleOrdini++;
            Node clonedNode = cloneNode(node, ordine);
            targetAnchorPane.getChildren().add(clonedNode);
        }

        // Copia le proprietà specifiche dell'AnchorPane
        targetAnchorPane.setPrefSize(sourceAnchorPane.getPrefWidth(), sourceAnchorPane.getPrefHeight());
        targetAnchorPane.setLayoutX(sourceAnchorPane.getLayoutX());
        targetAnchorPane.setLayoutY(sourceAnchorPane.getLayoutY());
        // ... Altre proprietà specifiche dell'AnchorPane ...

        targetAnchorPane.setStyle(sourceAnchorPane.getStyle());
    }

    private void copyHbox(HBox sourceAnchorPane, HBox targetAnchorPane, Ordine ordine) throws IOException {
        // Rimuovi tutti gli elementi esistenti dal targetAnchorPane
        targetAnchorPane.getChildren().clear();

        // Clona gli elementi dall'AnchorPane sorgente al nuovo AnchorPane
        for (Node node : sourceAnchorPane.getChildren()) {

            Node clonedNode = cloneNode(node, ordine);
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

    private Node cloneNode(Node sourceNode, Ordine ordine) throws IOException {
        if (sourceNode instanceof Label) {
            Label sourceLabel = (Label) sourceNode;
            Label clonedLabel = new Label(sourceLabel.getText());
            System.out.println(clonedLabel.getText() + "   --> " + cycleOrdini);
            if (cycleOrdini==2){
                DecimalFormat df = new DecimalFormat("000");
                clonedLabel.setText("Ordine #" + df.format(sistema.getCountOrdini()));
            }


            // Copia le proprietà specifiche del Label
            clonedLabel.setLayoutX(sourceLabel.getLayoutX());
            clonedLabel.setLayoutY(sourceLabel.getLayoutY());
            clonedLabel.setPrefWidth(sourceLabel.getPrefWidth());
            clonedLabel.setPrefHeight(sourceLabel.getPrefHeight());

            // Copia le proprietà del Font
            Font sourceFont = sourceLabel.getFont();
            Font clonedFont = Font.font(
                    sourceFont.getFamily(),
                    sourceFont.getSize()
            );

            // Check if the original font is bold and set the cloned font accordingly
            if (sourceFont.getStyle().equals("Bold")) {
                clonedFont = Font.font(
                        sourceFont.getFamily(),
                        FontWeight.BOLD,
                        sourceFont.getSize()
                );
            }
            clonedLabel.setFont(clonedFont);
            clonedLabel.setAlignment(sourceLabel.getAlignment());

            return clonedLabel;
        } else if (sourceNode instanceof Button) {
            Button sourceButton = (Button) sourceNode;
            Button clonedButton = new Button(sourceButton.getText());
            // Copia altre proprietà specifiche del Button
            clonedButton.setLayoutX(sourceButton.getLayoutX());
            clonedButton.setLayoutY(sourceButton.getLayoutY());
            clonedButton.setPrefWidth(sourceButton.getPrefWidth());
            clonedButton.setPrefHeight(sourceButton.getPrefHeight());
            return clonedButton;
        } else if (sourceNode instanceof ScrollPane) {
            ScrollPane sourceScrollPane = (ScrollPane) sourceNode;
            ScrollPane clonedScrollPane = new ScrollPane();
            // Copia altre proprietà specifiche dello ScrollPane
            clonedScrollPane.setLayoutX(sourceScrollPane.getLayoutX());
            clonedScrollPane.setLayoutY(sourceScrollPane.getLayoutY());
            clonedScrollPane.setPrefWidth(sourceScrollPane.getPrefWidth());
            clonedScrollPane.setPrefHeight(sourceScrollPane.getPrefHeight());

            Node content = cloneNode(sourceScrollPane.getContent(), ordine);
            clonedScrollPane.setContent(content);
            return clonedScrollPane;
        } else if (sourceNode instanceof VBox) {

            VBox sourceVBox = (VBox) sourceNode;
            VBox clonedVBox = new VBox();
            // Copia altre proprietà specifiche del VBox
            clonedVBox.setLayoutX(sourceVBox.getLayoutX());
            clonedVBox.setLayoutY(sourceVBox.getLayoutY());
            clonedVBox.setPrefWidth(sourceVBox.getPrefWidth());
            clonedVBox.setPrefHeight(sourceVBox.getPrefHeight());

            for (Node child : sourceVBox.getChildren()) {
                Node clonedChild = cloneNode(child, ordine);
                clonedVBox.getChildren().add(clonedChild);
            }

            clonedVBox.getChildren().clear();

            for (int i = 0; i < sistema.getOrdineAt(ordineAttuale).getnPizze(); i++) {
                Label newPizzaLabel = (Label) pizzaVboxLabel;
                newPizzaLabel.setLayoutX(pizzaVboxLabel.getLayoutX());
                newPizzaLabel.setLayoutY(pizzaVboxLabel.getLayoutY());
                newPizzaLabel.setPrefWidth(pizzaVboxLabel.getPrefWidth());
                newPizzaLabel.setPrefHeight(pizzaVboxLabel.getPrefHeight());

                // Copia le proprietà del Font
                Font source1Font = pizzaVboxLabel.getFont();
                Font cloned1Font = Font.font(
                        source1Font.getFamily(),
                        source1Font.getSize()
                );
                System.out.println("font passsati");
                // Check if the original font is bold and set the cloned font accordingly
                if (source1Font.getStyle().equals("Bold")) {
                    cloned1Font = Font.font(
                            source1Font.getFamily(),
                            FontWeight.BOLD,
                            source1Font.getSize()
                    );
                }
                System.out.println("CHECK2");
                newPizzaLabel.setFont(cloned1Font);
                newPizzaLabel.setAlignment(pizzaVboxLabel.getAlignment());
                //newPizzaLabel.setText(sistema.getOrdineAt(ordineAttuale).getPizzaAt(i).getNome());
                //sistema.getOrdineAt(ordineAttuale).getListaIngredientiPiuMeno(i);
                clonedVBox.getChildren().add(newPizzaLabel);
            }

            return clonedVBox;
        } else if (sourceNode instanceof AnchorPane) {
            AnchorPane sourceAnchorPane = (AnchorPane) sourceNode;
            AnchorPane clonedAnchorPane = new AnchorPane();
            // Copia altre proprietà specifiche dell'AnchorPane
            clonedAnchorPane.setLayoutX(sourceAnchorPane.getLayoutX());
            clonedAnchorPane.setLayoutY(sourceAnchorPane.getLayoutY());
            clonedAnchorPane.setPrefWidth(sourceAnchorPane.getPrefWidth());
            clonedAnchorPane.setPrefHeight(sourceAnchorPane.getPrefHeight());

            for (Node child : sourceAnchorPane.getChildren()) {
                Node clonedChild = cloneNode(child, ordine);
                clonedAnchorPane.getChildren().add(clonedChild);
            }
            return clonedAnchorPane;
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


    public void onUpdateButton(ActionEvent actionEvent) throws IOException {
        updateOrderPostit();
    }
}
