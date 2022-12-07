package controlador.proyecto;

import controlador.dao.InvestigacionDao;
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
import modelo.Investigacion;
import modelo.Proyecto;
import vista.Inicio;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class BusquedaContenidoController implements Initializable {
    private InvestigacionDao investigacionDao;
    @FXML
    private Button btnBuscarPalabra;

    @FXML
    private TextField txtPalabraBuscar;
    @FXML private Label labelEncontrado;
    @FXML
    private TableView<Investigacion> tbResultados;
    @FXML
    private Button btnRegresar;

    @FXML
    private TableView<Investigacion> tbBusquedaContenido;


    @FXML
    void Regresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("MenuInicial.fxml")));
        Stage window = (Stage) btnRegresar.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    public BusquedaContenidoController() {
        investigacionDao= new InvestigacionDao();
    }

    @FXML public void buscarPalabra() throws IOException, SQLException {
        String palabra= txtPalabraBuscar.getText();

        if (palabra.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error, no hay palabra ingresada para hacer búsqueda");
            alert.showAndWait();
        }else{
            RealizarBusqueda(palabra);
            RealizarBusquedaxContenido(palabra);
        }
        txtPalabraBuscar.setText("");
    }


    public void RealizarBusqueda(String palabra){
        tbResultados.getItems().clear();
        tbResultados.getColumns().clear();

        List<Investigacion> investigaciones=this.investigacionDao.BusquedaInvest(palabra);
        ObservableList<Investigacion> data= FXCollections.observableArrayList(investigaciones);
        TableColumn idCol=new TableColumn("Id Investigación");
        idCol.setCellValueFactory(new PropertyValueFactory("idInvestigacion"));

        TableColumn tituloCol=new TableColumn("Título");
        tituloCol.setCellValueFactory(new PropertyValueFactory("tituloInvestigacion"));

        TableColumn categoriaCol=new TableColumn("Categoría");
        categoriaCol.setCellValueFactory(new PropertyValueFactory("CategoriaInvestigacion"));

        TableColumn temaCol=new TableColumn("Tema");
        temaCol.setCellValueFactory(new PropertyValueFactory("tema"));

        tbResultados.setItems(data);
        tbResultados.getColumns().addAll(idCol,tituloCol,categoriaCol,temaCol);

    }
    public void RealizarBusquedaxContenido(String palabra) throws SQLException {
        tbBusquedaContenido.getItems().clear();
        tbBusquedaContenido.getColumns().clear();

        List<Investigacion> investigacionesConte=this.investigacionDao.BusquedaxContenido(palabra);
        ObservableList<Investigacion> data= FXCollections.observableArrayList(investigacionesConte);
        TableColumn idCol=new TableColumn("Id Investigación");
        idCol.setCellValueFactory(new PropertyValueFactory("idInvestigacion"));

        TableColumn tituloCol=new TableColumn("Título");
        tituloCol.setCellValueFactory(new PropertyValueFactory("tituloInvestigacion"));

        TableColumn subtituloCol=new TableColumn("Subtitulo");
        subtituloCol.setCellValueFactory(new PropertyValueFactory("subTitulo1"));

        TableColumn contenido1Col=new TableColumn("Contenido 1");
        contenido1Col.setCellValueFactory(new PropertyValueFactory("contenido1"));

        TableColumn contenido2Col=new TableColumn("Contenido 2");
        contenido2Col.setCellValueFactory(new PropertyValueFactory("contenido2"));

        tbBusquedaContenido.setItems(data);
        tbBusquedaContenido.getColumns().addAll(idCol,tituloCol,subtituloCol,contenido1Col,contenido2Col);

    }


    @FXML
    void BuscarPalabra(ActionEvent event) throws SQLException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.investigacionDao=new InvestigacionDao();
    }
}

