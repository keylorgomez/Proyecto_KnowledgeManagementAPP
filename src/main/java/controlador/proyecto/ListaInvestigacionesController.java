package controlador.proyecto;
import controlador.dao.InvestigacionDao;
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
import modelo.Investigacion;
import vista.Inicio;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    private Button btnRegresar;

    @FXML
    private TextField txtTitulo;
    private InvestigacionDao investigacionDao;
    private ContextMenu cmOpciones;
    private Investigacion investigacionSeleccionada;

    @FXML
    void Regresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("MenuInicial.fxml")));
        Stage window = (Stage) btnRegresar.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @FXML
    void Cancelar(ActionEvent event) {
        investigacionSeleccionada = null;
        limpiarCampos();
        btnCancelar.setDisable(true);
        btnEditar.setDisable(true);

    }
    public void limpiarCampos() {
        txtTema.setText("");
        txtCategoria.setText("");
        txtAutor.setText("");
        txtTitulo.setText("");
        txtTitulo.setText("");
        txtSubtitulo.setText("");
    }
    @FXML
    void EditarProyecto(ActionEvent event) {
        String tipoUsuario = LoginControlador.tipoUsuario;
        if (investigacionSeleccionada != null) {
            investigacionSeleccionada.setTema(txtTema.getText());
            investigacionSeleccionada.setCategoriaInvestigacion(txtCategoria.getText());
            investigacionSeleccionada.setAutor(txtAutor.getText());
            investigacionSeleccionada.setTituloInvestigacion(txtTitulo.getText());
            investigacionSeleccionada.setSubTitulo1(txtSubtitulo.getText());
            LocalDate FechaModi;
            FechaModi = LocalDate.now();
            String FechaModiString = FechaModi.toString();
            investigacionSeleccionada.setFechaModificacion(FechaModiString);
            investigacionSeleccionada.getFechaInicio();
            investigacionSeleccionada.getIdInvestigacion();
            int estatus=0;
            investigacionSeleccionada.setEstatus(estatus);

            boolean rsp = this.investigacionDao.crearInvestigacionTemporal(investigacionSeleccionada);
            if (rsp == true) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Espera");
                alert.setHeaderText(null);
                alert.setContentText("En espera a que el usuario gestor o l??der acepten las modificaciones.");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
                limpiarCampos();
                CargarInvestigaciones(tipoUsuario);
                investigacionSeleccionada = null;
                btnCancelar.setDisable(true);
                btnEditar.setDisable(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Se present?? un error al editar la investigacion.");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.investigacionDao=new InvestigacionDao();
        btnCancelar.setDisable(true);
        String tipoUsuario = LoginControlador.tipoUsuario;
        CargarInvestigaciones(tipoUsuario);

        //Keylor
        cmOpciones=new ContextMenu();
        MenuItem editarInvestigacion= new MenuItem("Editar investigaci??n");
        MenuItem eliminarInvestigacion = new MenuItem("Eliminar investigaci??n");
        cmOpciones.getItems().addAll(editarInvestigacion, eliminarInvestigacion);
        editarInvestigacion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tbInvestigaciones.getSelectionModel().getSelectedIndex();
                investigacionSeleccionada = tbInvestigaciones.getItems().get(index);
                txtTema.setText(investigacionSeleccionada.getTema());
                txtCategoria.setText(investigacionSeleccionada.getCategoriaInvestigacion());
                txtAutor.setText(investigacionSeleccionada.getAutor());
                txtTitulo.setText(investigacionSeleccionada.getTituloInvestigacion());
                txtSubtitulo.setText(investigacionSeleccionada.getSubTitulo1());

                btnCancelar.setDisable(false);
                btnEditar.setDisable(false);
            }
        });
        eliminarInvestigacion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tbInvestigaciones.getSelectionModel().getSelectedIndex();
                Investigacion investigacionEliminar = tbInvestigaciones.getItems().get(index);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmaci??n");
                alert.setHeaderText(null);
                alert.setContentText("??Seguro que desea eliminar la investigaci??n: " + investigacionEliminar.getTema() + "?");
                alert.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    investigacionEliminar.getIdInvestigacion();
                    investigacionEliminar.getCategoriaInvestigacion();
                    investigacionEliminar.getTema();
                    investigacionEliminar.getAutor();
                    investigacionEliminar.getTituloInvestigacion();
                    investigacionEliminar.getSubTitulo1();
                    investigacionEliminar.getFechaInicio();
                    investigacionEliminar.getFechaModificacion();
                    int estatus= 3;
                    investigacionEliminar.setEstatus(estatus);
                    boolean rsp =investigacionDao.crearInvestigacionTemporal(investigacionEliminar);
                    if (rsp == true) {
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("??xito");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Se debe esperar aprobaci??n del usuario gestor o l??der para borrar la investigaci??n.");
                        alert2.initStyle(StageStyle.UTILITY);
                        alert2.showAndWait();
                        CargarInvestigaciones(tipoUsuario);
                    } else {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Error");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Se present?? un error y no se logr?? eliminar la investigaci??n.");
                        alert2.initStyle(StageStyle.UTILITY);
                        alert2.showAndWait();
                    }
                }
            }
        });
        tbInvestigaciones.setContextMenu(cmOpciones);
    }

    public void CargarInvestigaciones(String tipoUsuario){
        tbInvestigaciones.getItems().clear();
        tbInvestigaciones.getColumns().clear();
        int UsuarioId = LoginControlador.UserIdActivo;

        //Andrea
        if (tipoUsuario.equals("Gestor")) {
            List<Investigacion> investigaciones=this.investigacionDao.listarInvestigacionGestor();
            ObservableList<Investigacion> data= FXCollections.observableArrayList(investigaciones);
            TableColumn idCol=new TableColumn<>("Id investigaci??n");
            idCol.setCellValueFactory(new PropertyValueFactory("idInvestigacion"));

            TableColumn tituloCol=new TableColumn<>("T??tulo");
            tituloCol.setCellValueFactory(new PropertyValueFactory("tituloInvestigacion"));

            TableColumn temaCol=new TableColumn<>("Tema");
            temaCol.setCellValueFactory(new PropertyValueFactory("tema"));

            TableColumn categoriaCol=new TableColumn<>("Categor??a");
            categoriaCol.setCellValueFactory(new PropertyValueFactory("CategoriaInvestigacion"));

            TableColumn nombreCol=new TableColumn<>("Autor");
            nombreCol.setCellValueFactory(new PropertyValueFactory("autor"));

            TableColumn subtituloCol=new TableColumn<>("Subtitulo");
            subtituloCol.setCellValueFactory(new PropertyValueFactory("subTitulo1"));

            TableColumn fechaCreacioCol=new TableColumn<>("Fecha creaci??n");
            fechaCreacioCol.setCellValueFactory(new PropertyValueFactory("fechaInicio"));

            TableColumn fechaModifiCol=new TableColumn<>("??ltima modificaci??n");
            fechaModifiCol.setCellValueFactory(new PropertyValueFactory("fechaModificacion"));

            tbInvestigaciones.setItems(data);
            tbInvestigaciones.getColumns().addAll(idCol,tituloCol,temaCol,categoriaCol,nombreCol,subtituloCol,fechaCreacioCol,fechaModifiCol);

        } else if (tipoUsuario.equals("Miembro") || tipoUsuario.equals("L??der")) {
            List<Investigacion> investigaciones = this.investigacionDao.listarInvestigacionesUsuarios(UsuarioId);
            ObservableList<Investigacion> data = FXCollections.observableArrayList(investigaciones);
            TableColumn idCol=new TableColumn<>("Id investigaci??n");
            idCol.setCellValueFactory(new PropertyValueFactory("idInvestigacion"));

            TableColumn tituloCol=new TableColumn<>("T??tulo");
            tituloCol.setCellValueFactory(new PropertyValueFactory("tituloInvestigacion"));

            TableColumn temaCol=new TableColumn<>("Tema");
            temaCol.setCellValueFactory(new PropertyValueFactory("tema"));

            TableColumn categoriaCol=new TableColumn<>("Categor??a");
            categoriaCol.setCellValueFactory(new PropertyValueFactory("CategoriaInvestigacion"));

            TableColumn nombreCol=new TableColumn<>("Autor");
            nombreCol.setCellValueFactory(new PropertyValueFactory("autor"));

            TableColumn subtituloCol=new TableColumn<>("Subtitulo");
            subtituloCol.setCellValueFactory(new PropertyValueFactory("subTitulo1"));

            TableColumn fechaCreacioCol=new TableColumn<>("Fecha creaci??n");
            fechaCreacioCol.setCellValueFactory(new PropertyValueFactory("fechaInicio"));

            TableColumn fechaModifiCol=new TableColumn<>("??ltima modificaci??n");
            fechaModifiCol.setCellValueFactory(new PropertyValueFactory("fechaModificacion"));

            tbInvestigaciones.setItems(data);
            tbInvestigaciones.getColumns().addAll(idCol,tituloCol,temaCol,categoriaCol,nombreCol,subtituloCol,fechaCreacioCol,fechaModifiCol);
        }
    }
}