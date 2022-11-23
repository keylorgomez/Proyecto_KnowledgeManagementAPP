package controlador.proyecto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import vista.Inicio;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuInicialController implements Initializable {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnCrearProyecto;

    @FXML
    private Button btnEditarProyecto;

    @FXML
    private Button btnProyectosTemporales;

    @FXML
    private Button btnEditarInvestigacion;


    @FXML
    private Button btnInvestigacionesTemporales;

    @FXML
    void IrCambiosPresentados(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("ListaProyectosTemporales.fxml")));
        Stage window = (Stage) btnProyectosTemporales.getScene().getWindow();
        window.setScene(new Scene(root));


    }

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
    @FXML
    void IrEditarInvestigacion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("ListaInvestigaciones.fxml")));
        Stage window = (Stage) btnEditarInvestigacion.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    @FXML
    void IrCambiosInvestigaciones(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnProyectosTemporales.setVisible(false);
        btnInvestigacionesTemporales.setVisible(false);
        String TipoUsuario=LoginControlador.tipoUsuario;
        if (TipoUsuario.equals("Gestor") || TipoUsuario.equals("Líder")){
            btnProyectosTemporales.setVisible(true);
            btnInvestigacionesTemporales.setVisible(true);
        }
    }
}
