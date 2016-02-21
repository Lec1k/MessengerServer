package messenger.Model;

import messenger.Controller.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;


public class ClientHandler extends Thread {


    private static final Logger LOG = LoggerFactory.getLogger(ClientHandler.class);
    Socket sock;
    DataInputStream dis;
    DataOutputStream dos;
    String username;

    public String getUsername() {
        return username;
    }

    public ClientHandler(String usr, Socket sc) {

        sock = sc;
        try {
            dis = new DataInputStream(sock.getInputStream());
            dos = new DataOutputStream(sock.getOutputStream());
            username = usr;
        } catch (Exception e) {
            LOG.warn(username + " lost connection");
        }

    }

    public void sendMsg(String msg) {
        try {
            dos.writeUTF(msg);
            dos.flush();
            onlineUsers();
            if(!ServerSettings.textArea.getText().contains("Client message::::" + msg + '\n')){
            MainController.appendLog("Client message::::" + msg + '\n');
            LOG.info("Client message::::" + msg);
            }
        } catch (Exception e) {
            //LOG.warn(username + " lost connection");
        }
    }

    public void sendAll(String msg) {
        try {
            for (int i = 0; i < ChatServer.online.size(); i++) {
                ClientHandler ch = ChatServer.online.get(i);
                ch.sendMsg(username + ": " + msg);
            }
        } catch (Exception e) {
            LOG.warn("");
        }
    }
    public void sendPrivate(String usr,String msg){
        try{
            for(int i =0;i<ChatServer.online.size();i++){
                ClientHandler ch = ChatServer.online.get(i);
                String name = ch.getUsername();
                if(name.equalsIgnoreCase(usr)){
                    ch.sendMsg(username + ": " + msg);
                    MainController.appendLog("Client private message::::" + msg + '\n');
                    dos.writeUTF(username + ": " + msg);
                    break;
                }
            }
        }
        catch (Exception e){
            LOG.warn("",e);
        }
    }



    public void stopClientHandler() {
        try {

            sock.close();
            dis.close();
            dos.close();
//            ChatServer.online.remove(this);
        } catch (Exception e) {
            LOG.warn("", e);
        }
    }

    public void run() {
        sendAll(" Joined chat");
        try {
            while (true) {
                String listen = dis.readUTF();
                listen = listen.trim();
                if (listen.startsWith("/commands")) {
                    dos.writeUTF("\"/p nickname\" for private messages\n\"/commands\" view commands list");
                } else {
                    if (listen.startsWith("/p")) {
                        String sent = listen.substring(2);
                        StringTokenizer st = new StringTokenizer(sent, " ");
                        String name = st.nextToken();
                        String msg = "";
                        while (st.hasMoreTokens()) {
                            msg += " " + st.nextToken();
                        }
                        sendPrivate(name, msg);
                    } else {
                        sendAll(listen);
                    }
                }
            }
        } catch (Exception e) {
            MainController.appendLog("Client lost connection: " + username + '\n');
            LOG.warn(username + " lost connection");
            sendAll(" has left the chat");
            for (ClientHandler ch : ChatServer.online) {
                if (ch.getUsername().equalsIgnoreCase(username)) {
                    ChatServer.online.remove(ch);
                    break;
                }
            }


        }
    }

    public void onlineUsers() {
        try {
            String name = "";
            for (int i = 0; i < ChatServer.online.size(); i++) {
                ClientHandler handler = ChatServer.online.get(i);
                name += handler.username + ",";
            }
            dos.writeUTF("oNL1n3" + name);
            dos.flush();

        } catch (Exception e) {
            LOG.warn(username + " lost connection");
    }
    }
}
