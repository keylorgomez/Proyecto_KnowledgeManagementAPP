package controlador.proyecto;
import controlador.dao.ProyectoDao;
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
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private AnchorPane anchorAñadir;
    @FXML
    private Button btnAgregarLider;
    @FXML
    private Label lblEmailLider;
    @FXML
    private Label lblLider;
    @FXML
    private TextField txtEmailLider;

    private ProyectoDao proyectoDao;
    private UsuarioDao usuarioDao;

    private ContextMenu cmOpciones;
    private Proyecto proyectoSelecionado;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.proyectoDao = new ProyectoDao();
        this.usuarioDao=new UsuarioDao();
        String tipoUsuario = LoginControlador.tipoUsuario;
        CargarProyectos(tipoUsuario);

        if(tipoUsuario.equals("Líder")){
            anchorAñadir.setVisible(true);
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
                    btnEditar.setDisable(true);
                    btnCancelar.setDisable(true);
                    txtNombre.setDisable(true);
                    txtVersion.setDisable(true);
                    txtCategoria.setDisable(true);
                    txtRepositorio.setDisable(true);
                    txtEmailParticipante.setDisable(false);
                    btnAgregarParticipante.setDisable(false);
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
                    txtNombre.setDisable(false);
                    txtVersion.setDisable(false);
                    txtCategoria.setDisable(false);
                    txtRepositorio.setDisable(false);
                    txtEmailParticipante.setDisable(true);
                    btnAgregarParticipante.setDisable(true);
                }
            });
            eliminarProyecto.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int index = tbProyectos.getSelectionModel().getSelectedIndex();
                    Proyecto proyectoEliminar = tbProyectos.getItems().get(index);
                    btnCancelar.setDisable(true);
                    btnEditar.setDisable(true);
                    txtNombre.setDisable(true);
                    txtVersion.setDisable(true);
                    txtCategoria.setDisable(true);
                    txtRepositorio.setDisable(true);
                    txtEmailParticipante.setDisable(true);
                    btnAgregarParticipante.setDisable(true);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("¿Seguro que desea eliminar el proyeto: " + proyectoEliminar.getNombre() + "?");
                    alert.initStyle(StageStyle.UTILITY);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        proyectoEliminar.getIdProyecto();
                        proyectoEliminar.getNombre();
                        proyectoEliminar.getCategoria();
                        proyectoEliminar.getFechaCreacion();
                        proyectoEliminar.getNumeroProyecto();
                        proyectoEliminar.getRepositorio();
                        proyectoEliminar.getUltimaModificacion();
                        int estatus= 3;
                        proyectoEliminar.setEstatus(estatus);
                        boolean rsp = proyectoDao.crearProyectoTemporal(proyectoEliminar);
                        if (rsp == true) {
                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Éxito");
                            alert2.setHeaderText(null);
                            alert2.setContentText("Se debe esperar aprobación del usuario Gestor o Líder para borrar el proyecto.");
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
        } else if (tipoUsuario.equals("Gestor")) {
            anchorAñadir.setVisible(true);
            btnAgregarParticipante.setVisible(true);
            lblAgregarParticipante.setVisible(true);
            lblEmailParticpante.setVisible(true);
            txtEmailParticipante.setVisible(true);
            btnAgregarLider.setVisible(true);
            lblEmailLider.setVisible(true);
            lblLider.setVisible(true);
            txtEmailLider.setVisible(true);
            cmOpciones = new ContextMenu();

            MenuItem editarProyecto = new MenuItem("Editar Proyecto");
            MenuItem eliminarProyecto = new MenuItem("Eliminar Proyecto");
            MenuItem agregarParticipante=new MenuItem("Añadir Participante");
            MenuItem agregarLider=new MenuItem("Añadir Líder");
            cmOpciones.getItems().addAll(editarProyecto, eliminarProyecto,agregarParticipante,agregarLider);
            agregarLider.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int index=tbProyectos.getSelectionModel().getSelectedIndex();
                    Proyecto agregarLiderProyecto=tbProyectos.getItems().get(index);
                    int idProyecto=agregarLiderProyecto.getIdProyecto();
                    btnAgregarLider.setDisable(false);
                    txtEmailLider.setDisable(false);
                    txtNombre.setDisable(true);
                    txtCategoria.setDisable(true);
                    txtVersion.setDisable(true);
                    txtRepositorio.setDisable(true);
                    btnEditar.setDisable(true);
                    btnCancelar.setDisable(true);
                    txtEmailParticipante.setDisable(true);
                    btnAgregarParticipante.setDisable(true);
                }
            });
            agregarParticipante.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int index=tbProyectos.getSelectionModel().getSelectedIndex();
                    Proyecto agregarParticipanteProyecto=tbProyectos.getItems().get(index);
                    int idProyecto=agregarParticipanteProyecto.getIdProyecto();
                    btnAgregarLider.setDisable(true);
                    txtEmailLider.setDisable(true);
                    txtNombre.setDisable(true);
                    txtCategoria.setDisable(true);
                    txtVersion.setDisable(true);
                    txtRepositorio.setDisable(true);
                    btnEditar.setDisable(true);
                    btnCancelar.setDisable(true);
                    txtEmailParticipante.setDisable(false);
                    btnAgregarParticipante.setDisable(false);
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

                    btnAgregarLider.setDisable(true);
                    txtEmailLider.setDisable(true);
                    txtNombre.setDisable(false);
                    txtCategoria.setDisable(false);
                    txtVersion.setDisable(false);
                    txtRepositorio.setDisable(false);
                    btnEditar.setDisable(false);
                    btnCancelar.setDisable(false);
                    txtEmailParticipante.setDisable(true);
                    btnAgregarParticipante.setDisable(true);
                }
            });
            eliminarProyecto.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int index = tbProyectos.getSelectionModel().getSelectedIndex();
                    Proyecto proyectoEliminar = tbProyectos.getItems().get(index);
                    btnAgregarLider.setDisable(true);
                    txtEmailLider.setDisable(true);
                    txtNombre.setDisable(true);
                    txtCategoria.setDisable(true);
                    txtVersion.setDisable(true);
                    txtRepositorio.setDisable(true);
                    btnEditar.setDisable(true);
                    btnCancelar.setDisable(true);
                    txtEmailParticipante.setDisable(true);
                    btnAgregarParticipante.setDisable(true);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("¿Seguro que desea eliminar el proyeto: " + proyectoEliminar.getNombre() + "?");
                    alert.initStyle(StageStyle.UTILITY);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        proyectoEliminar.getIdProyecto();
                        proyectoEliminar.getNombre();
                        proyectoEliminar.getCategoria();
                        proyectoEliminar.getFechaCreacion();
                        proyectoEliminar.getNumeroProyecto();
                        proyectoEliminar.getRepositorio();
                        proyectoEliminar.getUltimaModificacion();
                        int estatus= 3;
                        proyectoEliminar.setEstatus(estatus);
                        boolean rsp = proyectoDao.crearProyectoTemporal(proyectoEliminar);
                        if (rsp == true) {
                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Éxito");
                            alert2.setHeaderText(null);
                            alert2.setContentText("Se debe esperar aprobación del usuario Gestor o Líder para borrar el proyecto.");
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
        } else {
            anchorAñadir.setVisible(false);
            btnAgregarParticipante.setVisible(false);
            lblAgregarParticipante.setVisible(false);
            lblEmailParticpante.setVisible(false);
            txtEmailParticipante.setVisible(false);
            btnAgregarLider.setVisible(false);
            lblEmailLider.setVisible(false);
            lblLider.setVisible(false);
            txtEmailLider.setVisible(false);
            cmOpciones = new ContextMenu();

            MenuItem editarProyecto = new MenuItem("Editar Proyecto");
            MenuItem eliminarProyecto = new MenuItem("Eliminar Proyecto");
            cmOpciones.getItems().addAll(editarProyecto, eliminarProyecto);
            editarProyecto.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int index = tbProyectos.getSelectionModel().getSelectedIndex();
                    proyectoSelecionado = tbProyectos.getItems().get(index);
                    txtNombre.setDisable(false);
                    txtCategoria.setDisable(false);
                    txtVersion.setDisable(false);
                    txtRepositorio.setDisable(false);
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
                    txtNombre.setDisable(true);
                    txtCategoria.setDisable(true);
                    txtVersion.setDisable(true);
                    txtRepositorio.setDisable(true);
                    btnCancelar.setDisable(true);
                    btnEditar.setDisable(true);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("¿Seguro que desea eliminar el proyeto: " + proyectoEliminar.getNombre() + "?");
                    alert.initStyle(StageStyle.UTILITY);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        proyectoEliminar.getIdProyecto();
                        proyectoEliminar.getNombre();
                        proyectoEliminar.getCategoria();
                        proyectoEliminar.getFechaCreacion();
                        proyectoEliminar.getNumeroProyecto();
                        proyectoEliminar.getRepositorio();
                        proyectoEliminar.getUltimaModificacion();
                        int estatus= 3;
                        proyectoEliminar.setEstatus(estatus);
                        System.out.println(proyectoEliminar);

                        boolean rsp = proyectoDao.crearProyectoTemporal(proyectoEliminar);
                        if (rsp == true) {
                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Éxito");
                            alert2.setHeaderText(null);
                            alert2.setContentText("Se debe esperar aprobación del usuario Gestor o Líder  para eliminar proyecto");
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
            proyectoSelecionado.getFechaCreacion();
            proyectoSelecionado.getIdProyecto();
            int estatus= 0;
            proyectoSelecionado.setEstatus(estatus);
            System.out.println(proyectoSelecionado);

            boolean rsp = this.proyectoDao.crearProyectoTemporal(proyectoSelecionado);
            if (rsp == true) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Espera");
                alert.setHeaderText(null);
                alert.setContentText("En espera que usuario Gestor o lider acepten modificaciones");
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
        }
    }


    @FXML
    void AgregarParticipante(ActionEvent event) throws SQLException {
        int index=tbProyectos.getSelectionModel().getSelectedIndex();
        Proyecto agregarParticipanteProyecto=tbProyectos.getItems().get(index);
        int idProyecto=agregarParticipanteProyecto.getIdProyecto();
        String emailParticipante= txtEmailParticipante.getText();
        int idUsuario= proyectoDao.getUsuarioId(emailParticipante);
        String tipo="Participante";
        if(proyectoDao.getUsuarioId(emailParticipante)==0||emailParticipante.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Se presentó un error con el correo ingresado.");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        } else{
            proyectoDao.registrarProyectoxusuario(idUsuario, idProyecto,tipo);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Se agrego correctamente el usuario al proyecto.");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
            txtEmailParticipante.setText("");
        }
    }
    @FXML
    void AgregarLider(ActionEvent event) throws SQLException {
        int index=tbProyectos.getSelectionModel().getSelectedIndex();
        Proyecto agregarLiderProyecto=tbProyectos.getItems().get(index);
        int idProyecto=agregarLiderProyecto.getIdProyecto();
        String emailLider= txtEmailLider.getText();
        int idUsuario= proyectoDao.getUsuarioId(emailLider);
        String tipo="Líder";
        if(proyectoDao.getUsuarioId(emailLider)==0||emailLider.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Se presentó un error con el correo ingresado.");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        } else{
            String rol= usuarioDao.getTipoUsuario(emailLider);
            System.out.println(rol);
            if (rol.equals("Líder")){
                proyectoDao.registrarProyectoxusuario(idUsuario, idProyecto,tipo);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Se agrego correctamente el usuario al proyecto.");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
                txtEmailLider.setText("");
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("El usuario ingresado no posee rol Líder en el sistema.");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }
        }
    }
}