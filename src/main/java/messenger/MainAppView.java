package messenger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainAppView extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainAppView.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(MainAppView.class.getResource("/fxml/main.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage1 = new Stage();
        stage1.setTitle("NC Messenger - Server");
        stage1.setScene(new Scene(root1));
        stage1.show();

        stage1.setOnCloseRequest(we -> {
            Platform.exit();
            System.exit(0);
        });


    }
}

