package net.piramide.gestionale_pizzeria;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentOrdersController {

    public void setGestionale(GestionaleController gestionale) {
        this.gestionale = gestionale;
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

    private void updateClock(ActionEvent event) {
        // Ottenere l'orario corrente
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = dateFormat.format(now);

        // Aggiornare l'etichetta con l'orario corrente
        clockLabel.setText(formattedTime);
    }

    public void test(){
        System.out.println(gestionale.test);
    }




}
