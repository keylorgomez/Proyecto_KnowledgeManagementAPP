module com.horaaventura.horaaventura {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.horaaventura.horaaventura to javafx.fxml;
    exports com.horaaventura.horaaventura;
}