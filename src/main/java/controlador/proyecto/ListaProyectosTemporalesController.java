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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Proyecto;
import vista.Inicio;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ListaProyectosTemporalesController implements Initializable {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnRegresar;

    @FXML
    private RadioButton rbtAprobar;

    @FXML
    private RadioButton rbtRechazar;

    @FXML
    private TableView<Proyecto> tbProyectosTemporales;
    private ProyectoDao proyectoDao;

    @FXML
    void AceptarCambios(ActionEvent event) {

    }

    @FXML
    void Cancelar(ActionEvent event) {

    }

    @FXML
    void Regresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("MenuInicial.fxml")));
        Stage window = (Stage) btnRegresar.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group=new ToggleGroup();
        rbtAprobar.setToggleGroup(group);
        rbtRechazar.setToggleGroup(group);
        this.proyectoDao= new ProyectoDao();
        CargarProyectosTemporales();
    }

    public void CargarProyectosTemporales(){
        tbProyectosTemporales.getItems();
        tbProyectosTemporales.getColumns();
        List<Proyecto> proyectos=this.proyectoDao.listarProyectosTemporales();
        ObservableList<Proyecto> data= FXCollections.observableArrayList(proyectos);
        TableColumn idCol=new TableColumn("Id Proyecto");
        idCol.setCellValueFactory(new PropertyValueFactory("idProyecto"));

        TableColumn nombreCol = new TableColumn("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory("nombre"));

        TableColumn categoriaCol = new TableColumn("Categoría");
        categoriaCol.setCellValueFactory(new PropertyValueFactory("categoria"));

        TableColumn numeroCol = new TableColumn("Versión");
        numeroCol.setCellValueFactory(new PropertyValueFactory("numeroProyecto"));

        TableColumn repositorioCol = new TableColumn("Repositorio");
        repositorioCol.setCellValueFactory(new PropertyValueFactory("repositorio"));

        TableColumn creacionCol = new TableColumn("Fecha creación");
        creacionCol.setCellValueFactory(new PropertyValueFactory("fechaCreacion"));

        TableColumn modificacionCol = new TableColumn("Última modificación");
        modificacionCol.setCellValueFactory(new PropertyValueFactory("ultimaModificacion"));

        TableColumn estatusCol= new TableColumn("Estatus");
        estatusCol.setCellValueFactory(new PropertyValueFactory("estatus"));

        tbProyectosTemporales.setItems(data);
        tbProyectosTemporales.getColumns().addAll(idCol, nombreCol, categoriaCol, numeroCol, repositorioCol, creacionCol, modificacionCol,estatusCol);
    }
}
