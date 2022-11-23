package controlador.proyecto;


import controlador.dao.CarpetaDao;
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
import java.sql.SQLException;
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
    private TextField txtNumProyecto;
    @FXML
    private TextField txtCategoría;

    @FXML
    private TextField txtNombrePoryecto;

    @FXML
    private TextArea txtRepositorio;

    @FXML
    private Button bntCrearCarpeta;

    @FXML private Button btnCancelar;

    private CarpetaDao carpetaDao;
    private ProyectoDao proyectoDao;
    public static int proyectoIdActivo=0;


    public CrearProyectoControlador(){
        proyecto = new Proyecto();
        proyectoDao=new ProyectoDao();
        carpetaDao=new CarpetaDao();
    }

    @FXML public void crearCarpeta() throws IOException, SQLException {
        proyecto.setNumeroProyecto(txtNumProyecto.getText());
        proyecto.setNombre(txtNombrePoryecto.getText());
        proyecto.setCategoria(txtCategoría.getText());
        proyecto.setRepositorio(txtRepositorio.getText());

        String NumeroProyecto=proyecto.getNumeroProyecto();
        String NombreProyecto=proyecto.getNombre();
        String CategoriaProyecto=proyecto.getCategoria();
        String RepositorioProyecto=proyecto.getRepositorio();

        boolean respuestaValidacion= ValidarCampos(NombreProyecto,CategoriaProyecto,RepositorioProyecto,NumeroProyecto);
        if (respuestaValidacion==true){
            proyectoIdActivo= carpetaDao.getProyectoId(NumeroProyecto);
            int IDUsuario=LoginControlador.UserIdActivo;
            proyectoDao.registrarProyectoxusuario(IDUsuario, proyectoIdActivo);
            Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("Carpeta.fxml")));
            Stage window = (Stage) bntCrearCarpeta.getScene().getWindow();
            window.setScene(new Scene(root));
        }


    }

    public boolean ValidarCampos( String nombre, String categoria, String repositorio,String numero){
        int IDUsuario=LoginControlador.UserIdActivo;
        LocalDate fechaCreacion= LocalDate.now();
        String fechaCreacionString=fechaCreacion.toString();
        LocalDate ultimaModificacion=LocalDate.now();
        String fechaUltimaModificacionString=ultimaModificacion.toString();
        boolean rsp=true;
        if(numero.isEmpty()||nombre.isEmpty() || categoria.isEmpty() || repositorio.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error debido a espacios en blanco");
            alert.showAndWait();
            return rsp=false;
        }else{
            int mostrar= 0;
            int estatus=0;
            proyecto=new Proyecto(nombre,categoria,fechaCreacionString,fechaUltimaModificacionString,repositorio,IDUsuario,numero,mostrar);
            rsp=proyectoDao.registrarProyecto(proyecto);
            return rsp=true;
        }

    }
    public void limpiarCampos(){
        txtNombrePoryecto.setText("");
        txtCategoría.setText("");
        txtRepositorio.setText("");
    }


    @FXML
    void cancelarRegistroProyecto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("MenuInicial.fxml")));
        Stage window = (Stage) btnCancelar.getScene().getWindow();
        window.setScene(new Scene(root));

    }

}

