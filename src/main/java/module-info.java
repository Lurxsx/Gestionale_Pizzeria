module net.piramide.gestionale_pizzeria {
    requires javafx.controls;
    requires javafx.fxml;


    opens net.piramide.gestionale_pizzeria to javafx.fxml;
    exports net.piramide.gestionale_pizzeria;
}