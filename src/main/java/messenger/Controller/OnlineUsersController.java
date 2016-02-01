package messenger.Controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnlineUsersController {
    private static final Logger log = LoggerFactory.getLogger(OnlineUsersController.class);

    @FXML
    private TextArea onlineUsersTextAera;
    @FXML
    private Button okButton;

    @FXML
    private void onOkButtonClick() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
        log.info("Online Users window was closed");
    }

}
