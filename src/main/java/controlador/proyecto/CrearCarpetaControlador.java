package controlador.proyecto;
import controlador.dao.ProyectoDao;
import controlador.proyecto.CrearProyectoControlador;
import controlador.dao.CarpetaDao;
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

        String numero = "";
        String nombre="";
        String categoria="";
        String repositorio="";

        boolean resValidCampos= ValidarCamposCarpeta(investigacion, media);
        if (resValidCampos==true){
            registrarProyecto(numero, nombre, categoria, repositorio);
        }

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
            alert.setContentText("Se registró correctamente la carpeta");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
            //regresarProyecto(); HAY QUE QUITARLO DE ACÁ
            //limpiarCampos();
            return respuestaCampos;
        }
    }

    public boolean registrarProyecto(String numero, String nombre,String categoria, String repositorio){
        numero= String.valueOf(Integer.parseInt(CrearProyectoControlador.NumeroProyecto));
        nombre=CrearProyectoControlador.NombreProyecto;
        categoria=CrearProyectoControlador.CategoriaProyecto;
        repositorio=CrearProyectoControlador.RepositorioProyecto;
        int IDUsuario=LoginControlador.UserIdActivo;
        LocalDate fechaCreacion= LocalDate.now();
        LocalDate ultimaModificacion=LocalDate.now();
        boolean respuestaProyecto=true;
        proyecto=new Proyecto(nombre, numero, categoria,fechaCreacion,ultimaModificacion,repositorio,IDUsuario);
        respuestaProyecto=proyectoDao.registrarProyecto(proyecto);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText("Se registró correctamente el proyecto al sistema");
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
        //limpiarCampos();
        return respuestaProyecto;
    }
    public void regresarProyecto() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("CrearProyecto.fxml")));
        Stage window = (Stage) btnCrearCarpeta.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
