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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    @FXML
    private Button btnAgregarParticipante;
    @FXML
    private Label lblAgregarParticipante;
    @FXML
    private Label lblEmailParticpante;
    @FXML
    private TextField txtEmailParticipante;

    private ProyectoDao proyectoDao;

    private ContextMenu cmOpciones;
    private Proyecto proyectoSelecionado;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.proyectoDao = new ProyectoDao();
        btnCancelar.setDisable(true);
        String tipoUsuario = LoginControlador.tipoUsuario;
        CargarProyectos(tipoUsuario);

        if(tipoUsuario.equals("Gestor") || tipoUsuario.equals("Líder")){
            btnAgregarParticipante.setVisible(true);
            lblAgregarParticipante.setVisible(true);
            lblEmailParticpante.setVisible(true);
            txtEmailParticipante.setVisible(true);
            cmOpciones = new ContextMenu();

            MenuItem editarProyecto = new MenuItem("Editar Proyecto");
            MenuItem eliminarProyecto = new MenuItem("Eliminar Proyecto");
            MenuItem agregarParticipante=new MenuItem("Añadir Participante");
            cmOpciones.getItems().addAll(editarProyecto, eliminarProyecto,agregarParticipante);
            agregarParticipante.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int index=tbProyectos.getSelectionModel().getSelectedIndex();
                    Proyecto agregarParticipanteProyecto=tbProyectos.getItems().get(index);
                    int idProyecto=agregarParticipanteProyecto.getIdProyecto();
                    System.out.println(idProyecto);
                    btnAgregarParticipante.setDisable(false);
                    btnEditar.setDisable(true);
                    btnCancelar.setDisable(true);
                }
            });
            editarProyecto.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int index = tbProyectos.getSelectionModel().getSelectedIndex();
                    proyectoSelecionado = tbProyectos.getItems().get(index);
                    txtNombre.setText(proyectoSelecionado.getNombre());
                    txtCategoria.setText(proyectoSelecionado.getCategoria());
                    txtVersion.setText(proyectoSelecionado.getNumeroProyecto());
                    txtRepositorio.setText(proyectoSelecionado.getRepositorio());

                    btnCancelar.setDisable(false);
                    btnEditar.setDisable(false);
                    btnAgregarParticipante.setDisable(true);
                }
            });
            eliminarProyecto.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int index = tbProyectos.getSelectionModel().getSelectedIndex();
                    Proyecto proyectoEliminar = tbProyectos.getItems().get(index);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("¿Seguro que desea eliminar el proyeto: " + proyectoEliminar.getNombre() + "?");
                    alert.initStyle(StageStyle.UTILITY);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        boolean rsp = proyectoDao.eliminarProyecto(proyectoEliminar.getIdProyecto());
                        if (rsp == true) {
                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Éxito");
                            alert2.setHeaderText(null);
                            alert2.setContentText("Se eliminó correctamente el proyecto.");
                            alert2.initStyle(StageStyle.UTILITY);
                            alert2.showAndWait();
                            CargarProyectos(tipoUsuario);
                        } else {
                            Alert alert2 = new Alert(Alert.AlertType.ERROR);
                            alert2.setTitle("Error");
                            alert2.setHeaderText(null);
                            alert2.setContentText("Se presentó un error y no se logró eliminar el proyecto.");
                            alert2.initStyle(StageStyle.UTILITY);
                            alert2.showAndWait();
                        }
                    }
                }
            });
            tbProyectos.setContextMenu(cmOpciones);
        }else {
            cmOpciones = new ContextMenu();

            MenuItem editarProyecto = new MenuItem("Editar Proyecto");
            MenuItem eliminarProyecto = new MenuItem("Eliminar Proyecto");
            cmOpciones.getItems().addAll(editarProyecto, eliminarProyecto);
            editarProyecto.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int index = tbProyectos.getSelectionModel().getSelectedIndex();
                    proyectoSelecionado = tbProyectos.getItems().get(index);
                    txtNombre.setText(proyectoSelecionado.getNombre());
                    txtCategoria.setText(proyectoSelecionado.getCategoria());
                    txtVersion.setText(proyectoSelecionado.getNumeroProyecto());
                    txtRepositorio.setText(proyectoSelecionado.getRepositorio());

                    btnCancelar.setDisable(false);
                    btnEditar.setDisable(false);
                }
            });
            eliminarProyecto.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int index = tbProyectos.getSelectionModel().getSelectedIndex();
                    Proyecto proyectoEliminar = tbProyectos.getItems().get(index);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("¿Seguro que desea eliminar el proyeto: " + proyectoEliminar.getNombre() + "?");
                    alert.initStyle(StageStyle.UTILITY);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        boolean rsp = proyectoDao.eliminarProyecto(proyectoEliminar.getIdProyecto());
                        if (rsp == true) {
                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Éxito");
                            alert2.setHeaderText(null);
                            alert2.setContentText("Se eliminó correctamente el proyecto.");
                            alert2.initStyle(StageStyle.UTILITY);
                            alert2.showAndWait();
                            CargarProyectos(tipoUsuario);
                        } else {
                            Alert alert2 = new Alert(Alert.AlertType.ERROR);
                            alert2.setTitle("Error");
                            alert2.setHeaderText(null);
                            alert2.setContentText("Se presentó un error y no se logró eliminar el proyecto.");
                            alert2.initStyle(StageStyle.UTILITY);
                            alert2.showAndWait();
                        }
                    }
                }
            });
            tbProyectos.setContextMenu(cmOpciones);
        }



    }

    @FXML
    void Cancelar(ActionEvent event) {
        proyectoSelecionado = null;
        limpiarCampos();
        btnCancelar.setDisable(true);
        btnEditar.setDisable(true);
    }

    @FXML
    void EditarProyecto(ActionEvent event) {
        String tipoUsuario = LoginControlador.tipoUsuario;
        if (proyectoSelecionado != null) {
            proyectoSelecionado.setNombre(txtNombre.getText());
            proyectoSelecionado.setCategoria(txtCategoria.getText());
            proyectoSelecionado.setNumeroProyecto(txtVersion.getText());
            proyectoSelecionado.setRepositorio(txtRepositorio.getText());
            LocalDate FechaModi;
            FechaModi = LocalDate.now();
            String FechaModiString = FechaModi.toString();
            proyectoSelecionado.setUltimaModificacion(FechaModiString);

            boolean rsp = this.proyectoDao.editarProyecto(proyectoSelecionado);
            if (rsp == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Se editó correctamente el proyecto.");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
                limpiarCampos();
                CargarProyectos(tipoUsuario);
                proyectoSelecionado = null;
                btnCancelar.setDisable(true);
                btnEditar.setDisable(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Se presentó un error al editar el proyecto.");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }
        }

    }

    public void limpiarCampos() {
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

    public void CargarProyectos(String tipoUsuario) {
        tbProyectos.getItems().clear();
        tbProyectos.getColumns().clear();
        int UsuarioId = LoginControlador.UserIdActivo;
        if (tipoUsuario.equals("Gestor")) {
            List<Proyecto> proyectos = this.proyectoDao.listarProyectosGestor();
            ObservableList<Proyecto> data = FXCollections.observableArrayList(proyectos);
            TableColumn idCol = new TableColumn("Id Proyecto");
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

            tbProyectos.setItems(data);
            tbProyectos.getColumns().addAll(idCol, nombreCol, categoriaCol, numeroCol, repositorioCol, creacionCol, modificacionCol);
        } else if (tipoUsuario.equals("Miembro") || tipoUsuario.equals("Líder")) {
            List<Proyecto> proyectos = this.proyectoDao.listarProyectosUsuarios(UsuarioId);
            ObservableList<Proyecto> data = FXCollections.observableArrayList(proyectos);
            TableColumn idCol = new TableColumn("Id Proyecto");
            idCol.setCellValueFactory(new PropertyValueFactory("idProyecto"));

            TableColumn nombreCol = new TableColumn("Nombre");
            nombreCol.setCellValueFactory(new PropertyValueFactory("nombre"));

            TableColumn categoriaCol = new TableColumn("Categpría");
            categoriaCol.setCellValueFactory(new PropertyValueFactory("categoria"));

            TableColumn numeroCol = new TableColumn("Versión");
            numeroCol.setCellValueFactory(new PropertyValueFactory("numeroProyecto"));

            TableColumn repositorioCol = new TableColumn("Repositorio");
            repositorioCol.setCellValueFactory(new PropertyValueFactory("repositorio"));

            TableColumn creacionCol = new TableColumn("Fecha creación");
            creacionCol.setCellValueFactory(new PropertyValueFactory("fechaCreacion"));

            TableColumn modificacionCol = new TableColumn("Última modificación");
            modificacionCol.setCellValueFactory(new PropertyValueFactory("ultimaModificacion"));

            tbProyectos.setItems(data);
            tbProyectos.getColumns().addAll(idCol, nombreCol, categoriaCol, numeroCol, repositorioCol, creacionCol, modificacionCol);
        }
    }


    @FXML
    void AgregarParticipante(ActionEvent event) throws SQLException {
        int index=tbProyectos.getSelectionModel().getSelectedIndex();
        Proyecto agregarParticipanteProyecto=tbProyectos.getItems().get(index);
        int idProyecto=agregarParticipanteProyecto.getIdProyecto();
        String emailParticipante= txtEmailParticipante.getText();
        int idUsuario= proyectoDao.getUsuarioId(emailParticipante);
        if(proyectoDao.getUsuarioId(emailParticipante)==0||emailParticipante.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Se presentó un error con el correo ingresado.");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        } else{
            proyectoDao.registrarProyectoxusuario(idUsuario, idProyecto);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Se agrego correctamente el usuario al proyecto.");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        }
    }
}