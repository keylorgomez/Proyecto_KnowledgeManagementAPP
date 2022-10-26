package controlador.proyecto;


import controlador.dao.ProyectoDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Proyecto;
import vista.Inicio;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class CrearProyectoControlador {
    private Proyecto proyecto;

    @FXML
    private Label labelCategoria;

    @FXML
    private Label labelCrearProyecto;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelRepositorio;

    @FXML
    private TextField txtCategoría;

    @FXML
    private TextField txtNombrePoryecto;

    @FXML
    private TextArea txtRepositorio;

    @FXML
    private Button bntCrearCarpeta;


    private ProyectoDao proyectoDao;

    public static String NombreProyecto;
    public static String CategoriaProyecto;
    public static String RepositorioProyecto;

    public CrearProyectoControlador(){
        proyecto = new Proyecto();
        proyectoDao=new ProyectoDao();
    }

    @FXML public void crearCarpeta() throws IOException {

        proyecto.setNombre(txtNombrePoryecto.getText());
        proyecto.setCategoria(txtCategoría.getText());
        proyecto.setRepositorio(txtRepositorio.getText());

        NombreProyecto=proyecto.getNombre();
        CategoriaProyecto=proyecto.getCategoria();
        RepositorioProyecto=proyecto.getRepositorio();

        boolean respuestaValidacion= ValidarCampos(NombreProyecto,NombreProyecto,RepositorioProyecto);
        if (respuestaValidacion==true){
            Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("Carpeta.fxml")));
            Stage window = (Stage) bntCrearCarpeta.getScene().getWindow();
            window.setScene(new Scene(root));
        }


    }
    public boolean ValidarCampos(String nombre, String categoria, String repositorio){
        //int IDUsuario=LoginControlador.UserIdActivo;
        boolean rsp=true;
        //LocalDate fechaCreacion= LocalDate.now();
        //LocalDate ultimaModificacion=LocalDate.now();

        if(nombre.isEmpty() || categoria.isEmpty() || repositorio.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error debido a espacios en blanco");
            alert.showAndWait();

            return rsp=false;
        }
        return rsp=true;

        /*else {
            proyecto=new Proyecto(nombre,categoria,fechaCreacion,ultimaModificacion,repositorio,IDUsuario);
            rsp=proyectoDao.registrarProyecto(proyecto);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Se registró correctamente el proyecto");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
            //limpiarCampos();
            return rsp;
        }*/

    }
    public void limpiarCampos(){
        txtNombrePoryecto.setText("");
        txtCategoría.setText("");
        txtRepositorio.setText("");
    }



    /*public void irCrearCarpeta(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("Carpeta.fxml")));
        Stage window = (Stage) crearCarpeta.getScene().getWindow();
        window.setScene(new Scene(root));
    }*/
}

