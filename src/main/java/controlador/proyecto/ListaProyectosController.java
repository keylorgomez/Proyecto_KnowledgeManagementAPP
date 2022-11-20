package controlador.proyecto;
import controlador.dao.ProyectoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Proyecto;
import vista.Inicio;

import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.proyectoDao=new ProyectoDao();
        CargarProyectos();

    }

    @FXML
    void Cancelar(ActionEvent event) {

    }

    @FXML
    void EditarProyecto(ActionEvent event) {

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
