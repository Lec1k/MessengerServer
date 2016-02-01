package messenger.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import messenger.Model.ChatServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.net.Socket;

import static messenger.View.OnlineUsersView.showOnlineUsers;

public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    Thread serverThread;
    ChatServer mainServer;

    @FXML
    private TextArea logsTextAera;
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
        logsTextAera.clear();

    }

    @FXML
    private void onStartButtonClick() {
        mainServer = new ChatServer();
        serverThread = new Thread(mainServer);
        serverThread.start();
    }

    @FXML
    private void onEndButtonClick() {

    }

    @FXML
    private void onOnlineUsersButtonClick() {

        showOnlineUsers();
    }

    public void appendLog(String s){
        logsTextAera.appendText(s);
    }
}
