package messenger.Model;

import messenger.Controller.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class SocketHandler extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(SocketHandler.class);
    Socket sc;
    DataInputStream dis;
    DataOutputStream dos;
    String usr;

    public SocketHandler(Socket sock) {
        try {
            sc = sock;
            dis = new DataInputStream(sc.getInputStream());
            dos = new DataOutputStream(sc.getOutputStream());
        } catch (Exception e) {
            LOG.warn("", e);
        }
    }

    public void check() {
        try {
            usr = dis.readUTF();
            dos.writeUTF("Connected\n\"/commands\" for available list of commands\n\"/p nickname\" for private messages");
            MainController.appendLog(usr + " connected" + '\n');
            LOG.info(usr + " connected");
        } catch (Exception e) {
            LOG.warn("", e);
        }
    }

    public void run() {
        check();
        ClientHandler clientHandler = new ClientHandler(usr, sc);
        ChatServer.online.add(clientHandler);
        clientHandler.start();
    }


}
