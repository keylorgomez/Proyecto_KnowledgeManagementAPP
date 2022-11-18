package controlador.proyecto;
import controlador.dao.ProyectoDao;
import controlador.proyecto.CrearProyectoControlador;
import controlador.dao.CarpetaDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Carpeta;
import modelo.Proyecto;
import vista.Inicio;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class CrearCarpetaControlador {
    @FXML
    private Button btnCrearCarpeta;
    @FXML private Button btnRegresar;
    @FXML Button btnMedia;


    private Proyecto proyecto;
    private ProyectoDao proyectoDao;
    private Carpeta carpeta;
    private CarpetaDao carpetaDao;


    @FXML
    void crearProyecto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("CrearProyecto.fxml")));
        Stage window = (Stage) btnCrearCarpeta.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void irCrearMedia() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("CrearMedia.fxml")));
        Stage window = (Stage) btnMedia.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
