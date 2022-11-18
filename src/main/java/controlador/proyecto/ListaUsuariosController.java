package controlador.proyecto;
import controlador.dao.UsuarioDao;
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

    private UsuarioDao usuarioDao;
    private ContextMenu cmOpciones;
    private Usuario usuarioSeleccionado;

    @FXML
    void AsignarRol(ActionEvent event) {
        if (usuarioSeleccionado!=null){
            if (rbtMiembro.isSelected()==true){
                usuarioSeleccionado.setTipoUsuario("Miembro");
            } else if (rbtLider.isSelected()==true) {
                usuarioSeleccionado.setTipoUsuario("Líder");
            }
        }
        boolean rsp=this.usuarioDao.asignarRol(usuarioSeleccionado);
        if(rsp==true){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Se asignó correctamente un rol al usuario.");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
            limpiarCampos();
            CargarUsuarios();
            btnAsignar.setDisable(true);
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Se presentó un error, no se pudo asignar el rol.");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        }

    }
    private void limpiarCampos(){
        rbtMiembro.setSelected(true);
        rbtLider.setSelected(false);
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group=new ToggleGroup();
        rbtMiembro.setToggleGroup(group);
        rbtLider.setToggleGroup(group);
        this.usuarioDao=new UsuarioDao();
        CargarUsuarios();

        cmOpciones=new ContextMenu();
        MenuItem asignarRol= new MenuItem("Seleccionar rol");
        cmOpciones.getItems().addAll(asignarRol);
        asignarRol.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index=tbUsuarios.getSelectionModel().getSelectedIndex();
                usuarioSeleccionado=tbUsuarios.getItems().get(index);
                System.out.println(usuarioSeleccionado);

                switch (usuarioSeleccionado.getTipoUsuario()){
                    case "Miembro": rbtMiembro.setSelected(true);
                        break;
                    case "Líder": rbtLider.setSelected(true);
                        break;
                    case "Sin rol":
                        rbtMiembro.setSelected(false);
                        rbtLider.setSelected(false);
                        break;
                }
                btnAsignar.setDisable(false);
            }
        });
        tbUsuarios.setContextMenu(cmOpciones);
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