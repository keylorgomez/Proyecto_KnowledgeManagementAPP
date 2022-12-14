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
import modelo.Documento;
import modelo.Investigacion;
import vista.Inicio;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ListaInvestigacionesTemporalesController implements Initializable {

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
    private TableView<Investigacion> tbListaInvestigacionesTemporales;
    private InvestigacionDao investigacionDao;
    private DocumentoController documento;

    private ContextMenu cmOpciones;
    private Investigacion investigacionTemporalSeleccionado;

    @FXML
    void AceptarCambios(ActionEvent event) throws SQLException {
        if (investigacionTemporalSeleccionado!=null){
            int estado=investigacionDao.getEstatus(investigacionTemporalSeleccionado.getIdInvestigacion(),investigacionTemporalSeleccionado.getIdIvestigacionModi());
            if (estado==0) {
                if (rbtAprobar.isSelected() == true) {
                    int estatus = 1;
                    int idUsuario=LoginControlador.UserIdActivo;
                    String nombreRuta = investigacionDao.getRuta(investigacionTemporalSeleccionado.getIdInvestigacion());
                    String tituloDocumento = investigacionDao.getTitulo(investigacionTemporalSeleccionado.getIdInvestigacion());
                    System.out.println(nombreRuta+" mas "+tituloDocumento);
                    investigacionTemporalSeleccionado.setEstatus(estatus);
                    investigacionDao.editarInvestigacionTemporal(investigacionTemporalSeleccionado,idUsuario);
                    investigacionDao.editarInvestigacion(investigacionTemporalSeleccionado);
                    int idInvest=investigacionTemporalSeleccionado.getIdInvestigacion();
                    Investigacion modificacion= investigacionDao.Investigacion(idInvest);
                    String ultimaModificacion = modificacion.getFechaModificacion();
                    String fechaInicio = modificacion.getFechaInicio();
                    String categoria = modificacion.getCategoriaInvestigacion();
                    String tema = modificacion.getTema();
                    String autor = modificacion.getAutor();
                    String titulo = modificacion.getTituloInvestigacion();
                    String contenido1 = modificacion.getContenido1();
                    String subTitulo = modificacion.getSubTitulo1();
                    String contenido2 = modificacion.getContenido2();
                    documento.escribirArchivo(nombreRuta, tituloDocumento, ultimaModificacion,fechaInicio,categoria,tema,autor,titulo,contenido1,subTitulo,contenido2);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Exito");
                    alert.setHeaderText(null);
                    alert.setContentText("Usted ha aceptado los cambios y se ha actualizado la investigaci??n seleccionada.");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                    CargarInvestigacionesTemporales();
                    rbtRechazar.setSelected(false);
                    rbtAprobar.setSelected(false);
                }else if (rbtRechazar.isSelected() == true) {
                    int estatus = 2;
                    int idUsuario=LoginControlador.UserIdActivo;
                    investigacionTemporalSeleccionado.setEstatus(estatus);
                    investigacionDao.editarInvestigacionTemporal(investigacionTemporalSeleccionado,idUsuario);
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("IMPORTANTE");
                    alert.setHeaderText(null);
                    alert.setContentText("Usted no ha aceptado los cambios y ser??n eliminados.");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                    CargarInvestigacionesTemporales();
                    rbtRechazar.setSelected(false);
                    rbtAprobar.setSelected(false);
                }
            } else if(estado==3){
                if (rbtAprobar.isSelected()==true){
                    int estatus= 4;
                    int idUsuario=LoginControlador.UserIdActivo;
                    investigacionTemporalSeleccionado.setMostrar(1);
                    investigacionTemporalSeleccionado.setEstatus(estatus);
                    investigacionDao.editarInvestigacionTemporal(investigacionTemporalSeleccionado,idUsuario);
                    investigacionDao.ocultarInvestigacion (investigacionTemporalSeleccionado);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Exito");
                    alert.setHeaderText(null);
                    alert.setContentText("Usted ha aceptado los cambios y se ha actualizado la investigacion seleccionada.");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                    CargarInvestigacionesTemporales();
                    rbtRechazar.setSelected(false);
                    rbtAprobar.setSelected(false);
                }else if (rbtRechazar.isSelected()==true){
                    int estatus= 5;
                    int idUsuario=LoginControlador.UserIdActivo;
                    investigacionTemporalSeleccionado.setMostrar(0);
                    investigacionTemporalSeleccionado.setEstatus(estatus);
                    investigacionDao.editarInvestigacionTemporal(investigacionTemporalSeleccionado,idUsuario);
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("IMPORTANTE");
                    alert.setHeaderText(null);
                    alert.setContentText("Usted no ha aceptado los cambios y ser??n eliminados.");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                    CargarInvestigacionesTemporales();
                    rbtRechazar.setSelected(false);
                    rbtAprobar.setSelected(false);
                }

            }
        }

    }

    @FXML
    void Cancelar(ActionEvent event) {
        investigacionTemporalSeleccionado=null;
        rbtAprobar.setSelected(false);
        rbtRechazar.setSelected(false);
        btnCancelar.setDisable(true);
        btnAceptar.setDisable(true);

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
        this.investigacionDao= new InvestigacionDao();
        CargarInvestigacionesTemporales();
        cmOpciones=new ContextMenu();
        MenuItem gestionarCambios= new MenuItem("Gestionar cambios");
        cmOpciones.getItems().addAll(gestionarCambios);
        gestionarCambios.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index=tbListaInvestigacionesTemporales.getSelectionModel().getSelectedIndex();
                investigacionTemporalSeleccionado=tbListaInvestigacionesTemporales.getItems().get(index);


                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
        });
        tbListaInvestigacionesTemporales.setContextMenu(cmOpciones);
    }

    public void CargarInvestigacionesTemporales(){
        tbListaInvestigacionesTemporales.getItems().clear();
        tbListaInvestigacionesTemporales.getColumns().clear();
        List<Investigacion> investigaciones=this.investigacionDao.listarInvestigacionesTemporales();
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

        TableColumn estatusCol=new TableColumn<>("Estatus");
        estatusCol.setCellValueFactory(new PropertyValueFactory("estatus"));

        TableColumn idModiCol=new TableColumn<>("Id Modificado");
        idModiCol.setCellValueFactory(new PropertyValueFactory("idIvestigacionModi"));

        tbListaInvestigacionesTemporales.setItems(data);
        tbListaInvestigacionesTemporales.getColumns().addAll(idCol,tituloCol,temaCol,categoriaCol,nombreCol,subtituloCol,fechaCreacioCol,fechaModifiCol,estatusCol,idModiCol);

    }
}