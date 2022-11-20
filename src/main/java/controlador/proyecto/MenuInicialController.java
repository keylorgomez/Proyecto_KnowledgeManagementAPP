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

public class MenuInicialController {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnCrearProyecto;

    @FXML
    private Button btnEditarProyecto;

    @FXML
    void CerrarSesion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("Login.fxml")));
        Stage window = (Stage) btnCerrarSesion.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @FXML
    void IrCrearProyecto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("CrearProyecto.fxml")));
        Stage window = (Stage) btnCrearProyecto.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @FXML
    void IrEditarProyecto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("ListaProyectos.fxml")));
        Stage window = (Stage) btnEditarProyecto.getScene().getWindow();
        window.setScene(new Scene(root));

    }

}
