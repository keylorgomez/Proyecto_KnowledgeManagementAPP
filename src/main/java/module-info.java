module com.horaaventura.horaaventura {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;

    opens vista to javafx.fxml;
    exports vista;
    exports modelo;
    exports controlador.proyecto;
    opens controlador.proyecto to javafx.fxml;
}

