package controlador.proyecto;

import controlador.dao.CarpetaDao;
import controlador.dao.MediaDao;
import controlador.dao.ProyectoDao;
import controlador.dao.UsuarioDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.Media;
import modelo.Proyecto;
import vista.Inicio;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class CrearMediaControlador {
    private Media media;

    @FXML
    Button btnRegresar;
    @FXML Button btnCrearMedia;
    @FXML Button btnbuscarMedia;
    @FXML ImageView mediaImagen;
    @FXML
    TextField txtAutor;
    private MediaDao mediaDao;
    private String foto;
    private String imagen;

    public CrearMediaControlador(){
        media = new Media();
        mediaDao = new MediaDao();
    }

    @FXML public void crearMedia() throws IOException, SQLException {
        media.setAutor(txtAutor.getText());
        String autor = media.getAutor();

        ValidarCampos(autor);


    }
    public boolean ValidarCampos( String autor){
        LocalDate fechaCreacion= LocalDate.now();
        boolean rsp=true;
        if(autor.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error debido a espacios en blanco");
            alert.showAndWait();
            return rsp=false;
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Exito");
            alert.setContentText("Se registro exitosamente la media");
            alert.showAndWait();
            media=new Media(autor,foto,fechaCreacion);
            rsp= mediaDao.registrarMedia(media);
            return rsp=true;

        }
    }
    public String buscarFoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Media");
            // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

            // Obtener la imagen seleccionada
        File file = fileChooser.showOpenDialog(null);

            // Mostar la imagen
        if (file != null) {
            imagen = file.toString();
            mediaImagen.setImage(new Image(file.toURI().toString()));
            foto = imagen;
            return foto;
        }else {
            String error ="Ocurre error con foto";
            return error;
        }

    }

    @FXML
    void regresarCrearCarpeta(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("Carpeta.fxml")));
        Stage window = (Stage) btnRegresar.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    

}
