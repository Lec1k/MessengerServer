package messenger.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;


public class OnlineUsersView {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(OnlineUsersView.class);

    public static boolean showOnlineUsers() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(OnlineUsersView.class.getResource("/fxml/onlineUsers.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("NC Messenger - Server - Online Users");
            stage.setScene(new Scene(root1));
            stage.show();

            return true;
        } catch (Exception e) {
            log.error("In showOnlineUsers()-", e);
            return false;
        }
    }
}
