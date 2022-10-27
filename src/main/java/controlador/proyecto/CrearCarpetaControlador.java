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

    @FXML
    private TextField txtinvestigacion;

    @FXML
    private TextField txtmedia;

    private Proyecto proyecto;
    private ProyectoDao proyectoDao;
    private Carpeta carpeta;
    private CarpetaDao carpetaDao;


    public CrearCarpetaControlador() {
        proyecto = new Proyecto();
        proyectoDao=new ProyectoDao();
        carpeta = new Carpeta();
        carpetaDao = new CarpetaDao();

    }

    @FXML public void crearProyecto() throws IOException {

        carpeta.setInvestigacion(txtinvestigacion.getText());
        carpeta.setMedia(txtmedia.getText());

        String investigacion=carpeta.getInvestigacion();
        String media=carpeta.getMedia();

        ValidarCamposCarpeta(investigacion, media);


    }

    public boolean ValidarCamposCarpeta(String linkInvestigacion, String linkMedia) throws IOException {
        int IDProyecto= CrearProyectoControlador.proyectoIdActivo;
        boolean respuestaCampos=true;
        if(linkInvestigacion.isEmpty() || linkMedia.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error debido a espacios en blanco");
            alert.showAndWait();

            return respuestaCampos=false;
        }else {
            carpeta=new Carpeta(linkInvestigacion, linkMedia, IDProyecto);
            respuestaCampos= carpetaDao.registrarCarpeta(carpeta);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("¡Se registró el proyecto y carpeta de manera exitosa!");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();

            return respuestaCampos;
        }
    }

    @FXML
    void regresarCrearProyecto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("CrearProyecto.fxml")));
        Stage window = (Stage) btnCrearCarpeta.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void regresarProyecto() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("Login.fxml")));
        Stage window = (Stage) btnCrearCarpeta.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
