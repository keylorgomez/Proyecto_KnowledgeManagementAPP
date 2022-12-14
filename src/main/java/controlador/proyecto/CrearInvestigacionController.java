package controlador.proyecto;

import controlador.dao.CarpetaDao;
import controlador.dao.InvestigacionDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Investigacion;
import modelo.Proyecto;
import vista.Inicio;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class CrearInvestigacionController {
    private Investigacion investigacion;
    private InvestigacionDao investigacionDao;
    private CrearCarpetaController carpeta;
    private DocumentoController documento;
    @FXML
    private Button btnGuardarInvestigacion;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtContenido1;

    @FXML
    private TextField txtContenido2;

    @FXML
    private TextField txtSubTitulo;

    @FXML
    private TextField txtTema;

    @FXML
    private TextField txtTitulo;

    private Proyecto proyecto;
    public CrearInvestigacionController() {
        investigacion = new Investigacion();
        investigacionDao= new InvestigacionDao();
        carpeta=new CrearCarpetaController();
        documento=new DocumentoController();
        proyecto= new Proyecto();

    }

    @FXML
    void crearInvestigacion(ActionEvent event) throws SQLException, IOException {

        DirectoryChooser selectorCarpeta = new DirectoryChooser();
        selectorCarpeta.setInitialDirectory(new File("."));
        selectorCarpeta.setTitle("Seleccione la carpeta para guardar la investigación.");


        File file = selectorCarpeta.showDialog(null);

        String RutaDirectorio=file.getPath().toString();

        String ultimaModificacion = LocalDate.now().toString();
        String fechaCreacion = LocalDate.now().toString();
        String categoria = txtCategoria.getText();
        String tema = txtTema.getText();
        String autor = txtAutor.getText();
        String titulo = txtTitulo.getText();
        String contenido1 = txtContenido1.getText();
        String subTitulo = txtSubTitulo.getText();
        String contenido2 = txtContenido2.getText();

        if (validarCampos(categoria, tema, autor, titulo, subTitulo)==true){
            int idProyecto=CrearProyectoControlador.proyectoIdActivo;
            int idUsuario= LoginControlador.UserIdActivo;
            int mostrar= 0;
            int estatus=0;
            int palabras=0;
            Investigacion investigacion = new Investigacion(ultimaModificacion, fechaCreacion, categoria,tema, autor,titulo, subTitulo, mostrar, estatus, palabras, RutaDirectorio, contenido1, contenido2);
            System.out.println(investigacion);
            boolean rsp = investigacionDao.registrarInvestigacion(investigacion,idProyecto, idUsuario);
            if (rsp==true){

                carpeta.crearCarpeta(RutaDirectorio, titulo);
                String nuevaRuta =RutaDirectorio+"\\"+titulo;
                File docInvestigacion = documento.crearDocumento(ultimaModificacion, fechaCreacion, nuevaRuta, categoria,tema,autor,titulo,contenido1,subTitulo,contenido2);
                int totalPalabras = documento.contarPalabras(docInvestigacion);
                System.out.println("La cantidad de palabras totales es: "+totalPalabras);
                int id= investigacionDao.getIdUltimo();
                investigacionDao.editarTotalPalabras(totalPalabras, id);
                carpeta.crearCarpeta(nuevaRuta, "Media");
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Se registró correctamente la investigación.");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
                limpiarCampos();
                Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("Carpeta.fxml")));
                Stage window = (Stage) btnGuardarInvestigacion.getScene().getWindow();
                window.setScene(new Scene(root));
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Error al crear investigacion");
                alert.showAndWait();
            }
        }
    }
    private void limpiarCampos() {
        txtCategoria.setText("");
        txtTema.setText("");
        txtAutor.setText("");
        txtTitulo.setText("");
        txtContenido1.setText("");
        txtSubTitulo.setText("");
        txtContenido2.setText("");
    }
    public boolean validarCampos(String categoria, String tema, String autor, String titulo, String subtitulo){
        if (categoria.isEmpty()|| tema.isEmpty()||autor.isEmpty()||titulo.isEmpty()||subtitulo.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error debido a espacios en blanco");
            alert.showAndWait();
            return false;
        }else{
            return true;
        }
    }
}// fin de clase crrear investigacion

