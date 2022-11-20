package controlador.proyecto;
import controlador.dao.ProyectoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Proyecto;
import vista.Inicio;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ListaProyectosController implements Initializable {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnRegresar;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtRepositorio;

    @FXML
    private TextField txtVersion;
    @FXML
    private TableView<Proyecto> tbProyectos;

    private ProyectoDao proyectoDao;

    private ContextMenu cmOpciones;
    private Proyecto proyectoSelecionado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.proyectoDao=new ProyectoDao();
        btnCancelar.setDisable(true);
        CargarProyectos();

        cmOpciones=new ContextMenu();

        MenuItem editarProyecto=new MenuItem("EditarProyecto");
        cmOpciones.getItems().addAll(editarProyecto);
        editarProyecto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index=tbProyectos.getSelectionModel().getSelectedIndex();
                proyectoSelecionado=tbProyectos.getItems().get(index);
                txtNombre.setText(proyectoSelecionado.getNombre());
                txtCategoria.setText(proyectoSelecionado.getCategoria());
                txtVersion.setText(proyectoSelecionado.getNumeroProyecto());
                txtRepositorio.setText(proyectoSelecionado.getRepositorio());

                btnCancelar.setDisable(false);
                btnEditar.setDisable(false);
            }
        });
        tbProyectos.setContextMenu(cmOpciones);

    }

    @FXML
    void Cancelar(ActionEvent event) {
        proyectoSelecionado=null;
        limpiarCampos();
        btnCancelar.setDisable(true);
        btnEditar.setDisable(true);
    }

    @FXML
    void EditarProyecto(ActionEvent event) {
        if(proyectoSelecionado!=null){
            proyectoSelecionado.setNombre(txtNombre.getText());
            proyectoSelecionado.setCategoria(txtCategoria.getText());
            proyectoSelecionado.setNumeroProyecto(txtVersion.getText());
            proyectoSelecionado.setRepositorio(txtRepositorio.getText());
            LocalDate FechaModi;
            FechaModi=LocalDate.now();
            String FechaModiString=FechaModi.toString();
            proyectoSelecionado.setUltimaModificacion(FechaModiString);

            boolean rsp=this.proyectoDao.editarProyecto(proyectoSelecionado);
            if (rsp==true){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Se editó correctamente la tarea.");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
                limpiarCampos();
                CargarProyectos();
                proyectoSelecionado=null;
                btnCancelar.setDisable(true);
                btnEditar.setDisable(true);
            }else {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Se presentó un error al editar el proyecto.");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }
        }

    }
    public void limpiarCampos(){
        txtNombre.setText("");
        txtCategoria.setText("");
        txtVersion.setText("");
        txtRepositorio.setText("");
    }

    @FXML
    void Regresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("MenuInicial.fxml")));
        Stage window = (Stage) btnRegresar.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    public void CargarProyectos(){
        tbProyectos.getItems().clear();
        tbProyectos.getColumns().clear();

        List<Proyecto> proyectos=this.proyectoDao.listarProyectos();
        ObservableList<Proyecto> data= FXCollections.observableArrayList(proyectos);
        TableColumn idCol=new TableColumn("Id Proyecto");
        idCol.setCellValueFactory(new PropertyValueFactory("idProyecto"));

        TableColumn nombreCol=new TableColumn("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory("nombre"));

        TableColumn categoriaCol=new TableColumn("Categpría");
        categoriaCol.setCellValueFactory(new PropertyValueFactory("categoria"));

        TableColumn numeroCol=new TableColumn("Versión");
        numeroCol.setCellValueFactory(new PropertyValueFactory("numeroProyecto"));

        TableColumn repositorioCol=new TableColumn("Repositorio");
        repositorioCol.setCellValueFactory(new PropertyValueFactory("repositorio"));

        TableColumn creacionCol=new TableColumn("Fecha creación");
        creacionCol.setCellValueFactory(new PropertyValueFactory("fechaCreacion"));

        TableColumn modificacionCol=new TableColumn("Última modificación");
        modificacionCol.setCellValueFactory(new PropertyValueFactory("ultimaModificacion"));

        tbProyectos.setItems(data);
        tbProyectos.getColumns().addAll(idCol,nombreCol,categoriaCol,numeroCol,repositorioCol,creacionCol,modificacionCol);

    }

}
