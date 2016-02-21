package messenger.Model;

import messenger.Controller.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(ChatServer.class);
    ServerSocket ss;
    Socket skt;
    public static Vector<ClientHandler> online;

    public ChatServer() {
        try {
            ss = new ServerSocket(8881);
            MainController.appendLog("Listening to port 8881...\n");
            LOG.info("Listening to port 8881");
            online = new Vector<ClientHandler>();
        } catch (Exception e) {
            LOG.warn("", e);
        }
    }

    public void monitoring() {
        try {
            while (true) {
                skt = ss.accept();
                SocketHandler sh = new SocketHandler(skt);
                MainController.appendLog("Client accepted \n");
                LOG.info("Client accepted");
                sh.start();
            }
        } catch (Exception e) {
            LOG.warn("Socket closed");
        }
    }

    public void stopServer() {
        try {
//            for (int i=0;i<online.size();i++) {
//                online.get(i).stopClientHandler();
//            }
            ss.close();
            MainController.appendLog("Server closed...\n");

        } catch (Exception e) {
            LOG.warn("", e);
        }
        skt = null;
        ss = null;
    }

    @Override
    public void run() {
        try {
            monitoring();
        } catch (Exception e) {
            LOG.warn("", e);
        }
    }
}
