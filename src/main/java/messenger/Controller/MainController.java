package messenger.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import messenger.Model.ChatServer;
import messenger.Model.ServerSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

import static messenger.View.OnlineUsersView.showOnlineUsers;

public class MainController implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    public static TextArea logs;

    Thread serverThread;
    ChatServer mainServer;

    @FXML
    private TextArea logsTextArea;
    @FXML
    private Button clearButton;
    @FXML
    private Button startButton;
    @FXML
    private Button endButton;
    @FXML
    private Button onlineUsersButton;



    @FXML
    private void onClearButtonClick() {
        logsTextArea.clear();

    }

    @FXML
    private void onStartButtonClick() {
        mainServer = new ChatServer();
        serverThread = new Thread(mainServer);
        serverThread.start();
        startButton.setVisible(false);
    }

    @FXML
    private void onEndButtonClick() {
        serverThread.interrupt();
        serverThread = null;
        mainServer.stopServer();
        startButton.setVisible(true);

    }

    @FXML
    private void onOnlineUsersButtonClick() {

        showOnlineUsers();
    }

    public static void  appendLog(String s){
        ServerSettings.textArea.appendText(s);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServerSettings.textArea = logsTextArea;
    }
}
