package controlador.proyecto;
import controlador.dao.InvestigacionDao;
import controlador.dao.MediaDao;
import controlador.dao.UsuarioDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Usuario;
import vista.Inicio;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class EstadisticasController implements Initializable {

    @FXML
    private Button btnRegresar;

    @FXML
    private TextField txtInvestigacionMasGrande;

    @FXML
    private TextField txtLiderAceptados;

    @FXML
    private TextField txtMasInvestigaciones;

    @FXML
    private TextField txtMasMedia;

    @FXML
    void Regresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("MenuInicial.fxml")));
        Stage window = (Stage) btnRegresar.getScene().getWindow();
        window.setScene(new Scene(root));

    }
    private InvestigacionDao investigacionDao;
    private UsuarioDao usuarioDao;
    private MediaDao mediaDao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.investigacionDao=new InvestigacionDao();
        this.usuarioDao=new UsuarioDao();
        this.mediaDao=new MediaDao();
        txtInvestigacionMasGrande.setDisable(true);
        txtLiderAceptados.setDisable(true);
        txtMasInvestigaciones.setDisable(true);
        txtMasMedia.setDisable(true);

        try {
            CargarEstadisticas();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void CargarEstadisticas() throws SQLException {

        int idMasInvest=investigacionDao.UsuarioMasInvest();
        if (idMasInvest==0){
            txtMasInvestigaciones.setText("Sin resultados");
        }else{
            Usuario usuarioMasInvest=usuarioDao.Usuario(idMasInvest);
            String usuarioMasInvestigaciones="";
            usuarioMasInvestigaciones=usuarioMasInvest.getNombre()+" "+usuarioMasInvest.getApellido();
            txtMasInvestigaciones.setText(usuarioMasInvestigaciones);
        }


        int idLiderAceptada=investigacionDao.LiderMasAceptadas();
        if (idLiderAceptada==0){
            txtLiderAceptados.setText("Sin resultados");
        }else{
            Usuario usuarioLider=usuarioDao.Usuario(idLiderAceptada);
            String usuarioLiderAceptadas="";
            usuarioLiderAceptadas=usuarioLider.getNombre()+" "+usuarioLider.getApellido();
            txtLiderAceptados.setText(usuarioLiderAceptadas);
        }


        int idInvestMasGrande=investigacionDao.InvestExtensa();
        if (idInvestMasGrande==0){
            txtInvestigacionMasGrande.setText("Sin resultados");
        }else{
            Usuario usuarioInvestMasGrande=usuarioDao.Usuario(idInvestMasGrande);
            String usuarioInvestigacionMasGrande="";
            usuarioInvestigacionMasGrande=usuarioInvestMasGrande.getNombre()+" "+usuarioInvestMasGrande.getApellido();
            txtInvestigacionMasGrande.setText(usuarioInvestigacionMasGrande);
        }

        int idMasMedia= mediaDao.UsuarioMasMedia();
        if (idMasMedia==0){
            txtMasMedia.setText("Sin resultados");
        }else{
            Usuario usuarioMasMedia=usuarioDao.Usuario(idMasMedia);
            String usuarioMasMediaa="";
            usuarioMasMediaa=usuarioMasMedia.getNombre()+" "+usuarioMasMedia.getApellido();
            txtMasMedia.setText(usuarioMasMediaa);
        }

    }
}