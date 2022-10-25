package controlador.proyecto;


import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import modelo.Proyecto;

public class CrearProyectoControlador {
    private Proyecto proyecto;


    @FXML private DatePicker dpFechaCreacion;
    @FXML private DatePicker dpFechaModificacion;

    @FXML private Label labelCategoria;

    @FXML private Label labelColaboradores;

    @FXML private Label labelCrearProyecto;

    @FXML private Label labelFechaCreacion;

    @FXML private Label labelFechaModificacion;

    @FXML private Label labelMedia;

    @FXML
    private Label labelNombre;

    @FXML private Label labelRepositorio;

    @FXML private TextField txtCategoría;

    @FXML private TextField txtColaboradores;

    @FXML private TextField txtMedia;

    @FXML private TextArea txtRepositorio;

    @FXML public void crearProtecto(){
        //proyecto = new Proyecto();

    }

}

