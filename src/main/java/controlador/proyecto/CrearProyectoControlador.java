package controlador.proyecto;


import controlador.dao.ProyectoDao;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.StageStyle;
import modelo.Proyecto;

import java.time.LocalDate;
import java.util.Date;

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

    private ProyectoDao proyectoDao;

    public CrearProyectoControlador(){
        proyecto = new Proyecto();
        proyectoDao=new ProyectoDao();
    }

    @FXML public void crearProtecto(){

        proyecto.setNombre(txtNombrePoryecto.getText());
        proyecto.setCategoria(txtCategoría.getText());
        proyecto.setRepositorio(txtRepositorio.getText());

        String nombreProyecto=proyecto.getNombre();
        String categoriaProyecto=proyecto.getCategoria();
        String repositorioProyecto=proyecto.getRepositorio();

        ValidarCampos(nombreProyecto,categoriaProyecto,repositorioProyecto);
    }
    public boolean ValidarCampos(String nombre, String categoria, String repositorio){
        boolean rsp=true;
        LocalDate fechaCreacion= LocalDate.now();
        LocalDate ultimaModificacion=LocalDate.now();

        if(nombre.isEmpty() || categoria.isEmpty() || repositorio.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error debido a espacios en blanco");
            alert.showAndWait();
            rsp=false;
            return rsp;
        }else {
            proyecto=new Proyecto(nombre,categoria,fechaCreacion,ultimaModificacion,repositorio);
            rsp=proyectoDao.registrarProyecto(proyecto);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Se registró correctamente el proyecto");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
            limpiarCampos();
            return rsp;
        }

    }
    public void limpiarCampos(){
        txtNombrePoryecto.setText("");
        txtCategoría.setText("");
        txtRepositorio.setText("");
    }

}

