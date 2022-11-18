package controlador.proyecto;
import controlador.dao.UsuarioDao;
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
import modelo.Usuario;
import vista.Inicio;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ListaUsuariosController implements Initializable {

    @FXML
    private Button btnAsignar;

    @FXML
    private Button btnIrMenuPrincipal;

    @FXML
    private Button btnRegresar;

    @FXML
    private RadioButton rbtLider;

    @FXML
    private RadioButton rbtMiembro;

    @FXML
    private TableView<Usuario> tbUsuarios;

    @FXML
    void AsignarRol(ActionEvent event) {

    }

    @FXML
    void IrMenuPrincipal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("MenuInicial.fxml")));
        Stage window = (Stage) btnIrMenuPrincipal.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @FXML
    void Regresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("Login.fxml")));
        Stage window = (Stage) btnRegresar.getScene().getWindow();
        window.setScene(new Scene(root));

    }
    private UsuarioDao usuarioDao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group=new ToggleGroup();
        rbtMiembro.setToggleGroup(group);
        rbtLider.setToggleGroup(group);
        this.usuarioDao=new UsuarioDao();

        CargarUsuarios();
    }

    public void CargarUsuarios(){
        tbUsuarios.getItems().clear();
        tbUsuarios.getColumns().clear();

        List<Usuario> usuarios=this.usuarioDao.listarUsuarios();

        ObservableList<Usuario> data= FXCollections.observableArrayList(usuarios);
        TableColumn idCol=new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory("usuarioId"));

        TableColumn nombreCol=new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory("nombre"));

        TableColumn apellidoCol=new TableColumn<>("Apellidos");
        apellidoCol.setCellValueFactory(new PropertyValueFactory("apellido"));

        TableColumn edadCol=new TableColumn<>("Edad");
        edadCol.setCellValueFactory(new PropertyValueFactory("edad"));

        TableColumn fechaNacimientoCol=new TableColumn<>("Fecha Nacimiento");
        fechaNacimientoCol.setCellValueFactory(new PropertyValueFactory("fechaNacimiento"));

        TableColumn emailCol=new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));

        TableColumn tipoUsuarioCol=new TableColumn<>("Rol");
        tipoUsuarioCol.setCellValueFactory(new PropertyValueFactory("tipoUsuario"));



        tbUsuarios.setItems(data);
        tbUsuarios.getColumns().addAll(idCol,nombreCol,apellidoCol,edadCol,fechaNacimientoCol,emailCol,tipoUsuarioCol);
    }
}