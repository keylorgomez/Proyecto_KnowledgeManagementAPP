package controlador.proyecto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import vista.Inicio;

import java.io.IOException;
import java.util.Objects;

public class PantallaEsperaController {

    @FXML
    private Button btnRegresar;

    @FXML
    void Regresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("Login.fxml")));
        Stage window = (Stage) btnRegresar.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
