package controlador.proyecto;
import controlador.dao.InvestigacionDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Investigacion;
import modelo.Proyecto;
import modelo.Usuario;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListaInvestigacionesController implements Initializable {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEditar;

    @FXML
    private TableView<Investigacion> tbInvestigaciones;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtSubtitulo;

    @FXML
    private TextField txtTema;

    @FXML
    private TextField txtTitulo;
    private InvestigacionDao investigacionDao;
    private ContextMenu cmOpciones;
    private Investigacion investigacionSeleccionada;

    @FXML
    void Cancelar(ActionEvent event) {

    }

    @FXML
    void EditarProyecto(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.investigacionDao=new InvestigacionDao();
        CargarInvestigaciones();

        cmOpciones=new ContextMenu();
        MenuItem editarInvestigacion= new MenuItem("Editar investigación");
        MenuItem eliminarInvestigacion = new MenuItem("Eliminar investigación");
        cmOpciones.getItems().addAll(editarInvestigacion, eliminarInvestigacion);
        editarInvestigacion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tbInvestigaciones.getSelectionModel().getSelectedIndex();
                investigacionSeleccionada = tbInvestigaciones.getItems().get(index);
                btnEditar.setDisable(false);
            }
        });
        eliminarInvestigacion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tbInvestigaciones.getSelectionModel().getSelectedIndex();
                Investigacion investigacionEliminar = tbInvestigaciones.getItems().get(index);

            }
        });

    }

    public void CargarInvestigaciones(){
        tbInvestigaciones.getItems().clear();
        tbInvestigaciones.getColumns().clear();

        List<Investigacion> investigaciones=this.investigacionDao.listarInvestigacionGestor();
        ObservableList<Investigacion> data= FXCollections.observableArrayList(investigaciones);
        TableColumn idCol=new TableColumn<>("Id investigación");
        idCol.setCellValueFactory(new PropertyValueFactory("idInvestigacion"));

        TableColumn tituloCol=new TableColumn<>("Título");
        tituloCol.setCellValueFactory(new PropertyValueFactory("tituloInvestigacion"));

        TableColumn temaCol=new TableColumn<>("Tema");
        temaCol.setCellValueFactory(new PropertyValueFactory("tema"));

        TableColumn categoriaCol=new TableColumn<>("Categoría");
        categoriaCol.setCellValueFactory(new PropertyValueFactory("CategoriaInvestigacion"));

        TableColumn subtituloCol=new TableColumn<>("Subtitulo");
        subtituloCol.setCellValueFactory(new PropertyValueFactory("subTitulo1"));

        TableColumn fechaCreacioCol=new TableColumn<>("Fecha creación");
        fechaCreacioCol.setCellValueFactory(new PropertyValueFactory("fechaInicio"));

        TableColumn fechaModifiCol=new TableColumn<>("Última modificación");
        fechaModifiCol.setCellValueFactory(new PropertyValueFactory("fechaModificacion"));

        tbInvestigaciones.setItems(data);
        tbInvestigaciones.getColumns().addAll(idCol,tituloCol,temaCol,categoriaCol,subtituloCol,fechaCreacioCol,fechaModifiCol);
    }
}