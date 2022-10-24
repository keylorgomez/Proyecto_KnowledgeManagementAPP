module com.horaaventura.horaaventura {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens Vista to javafx.fxml;
    exports Vista;
    exports Modelo;
    exports Controlador.Proyecto;
    opens Controlador.Proyecto to javafx.fxml;
}

