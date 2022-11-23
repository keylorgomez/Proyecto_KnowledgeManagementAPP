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

    private ContextMenu cmOpciones;
    private Proyecto proyectoTemporalSeleccionado;


    @FXML
    void AceptarCambios(ActionEvent event) {
        if(proyectoTemporalSeleccionado!=null){
            System.out.println(proyectoTemporalSeleccionado);
            if(proyectoTemporalSeleccionado.getEstatus()==0) {
                if (rbtAprobar.isSelected() == true) {
                    int estatus = 1;
                    proyectoTemporalSeleccionado.setEstatus(estatus);
                    proyectoDao.editarProyectoTemporal(proyectoTemporalSeleccionado);
                    proyectoDao.editarProyecto(proyectoTemporalSeleccionado);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Exito");
                    alert.setHeaderText(null);
                    alert.setContentText("Usted ha aceptado los cambios y se ha actualizado el proyecto seleccionado.");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                } else if (rbtRechazar.isSelected() == true) {
                    int estatus = 2;
                    proyectoTemporalSeleccionado.setEstatus(estatus);
                    proyectoDao.editarProyectoTemporal(proyectoTemporalSeleccionado);
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("IMPORTANTE");
                    alert.setHeaderText(null);
                    alert.setContentText("Usted no ha aceptado los cambios y serán eliminaodos.");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                }
            }
            if(proyectoTemporalSeleccionado.getEstatus()==3){
                if (rbtAprobar.isSelected()==true){
                    System.out.println("sin editar: "+proyectoTemporalSeleccionado);
                    int estatus= 4;
                    proyectoTemporalSeleccionado.setMostrar(1);
                    proyectoTemporalSeleccionado.setEstatus(estatus);
                    proyectoDao.editarProyectoTemporal(proyectoTemporalSeleccionado);
                    proyectoDao.ocultarProyecto(proyectoTemporalSeleccionado);
                    System.out.println("editado: "+proyectoTemporalSeleccionado);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Exito");
                    alert.setHeaderText(null);
                    alert.setContentText("Usted ha aceptado los cambios y se ha actualizado el proyecto seleccionado.");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                }else if (rbtRechazar.isSelected()==true){
                    int estatus= 5;
                    proyectoTemporalSeleccionado.setMostrar(0);
                    proyectoTemporalSeleccionado.setEstatus(estatus);
                    proyectoDao.editarProyectoTemporal(proyectoTemporalSeleccionado);
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("IMPORTANTE");
                    alert.setHeaderText(null);
                    alert.setContentText("Usted no ha aceptado los cambios y serán eliminaodos.");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();

                }
            }


        }

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
        cmOpciones=new ContextMenu();

        MenuItem gestionarCambios= new MenuItem("Gestionar cambios");
        cmOpciones.getItems().addAll(gestionarCambios);
        gestionarCambios.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index=tbProyectosTemporales.getSelectionModel().getSelectedIndex();
                proyectoTemporalSeleccionado=tbProyectosTemporales.getItems().get(index);


                btnAceptar.setDisable(false);
                btnCancelar.setDisable(false);
            }
        });
        tbProyectosTemporales.setContextMenu(cmOpciones);

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
