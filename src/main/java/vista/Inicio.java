package vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class Inicio extends Application {

    public static <String> void main(String[] args) {
        launch(args);
    }

    private static <String> void launch(String[] args) {
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 671, 463);
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
