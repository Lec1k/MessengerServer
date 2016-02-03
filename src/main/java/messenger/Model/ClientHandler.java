package messenger.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by LeC1K on 01.02.2016.
 */
public class ClientHandler extends  Thread {


    private static final Logger LOG = LoggerFactory.getLogger(ClientHandler.class);
    Socket sock;
    DataInputStream dis;
    DataOutputStream dos;
    String username;

    public String getUsername() {
        return username;
    }

    public ClientHandler(String usr, Socket sc){

        sock= sc;
        try{
            dis = new DataInputStream(sock.getInputStream());
            dos = new DataOutputStream(sock.getOutputStream());
            username = usr;
        }
        catch (Exception e){
            LOG.warn("",e);
        }

    }

    public void sendMsg(String msg){
        try{
            dos.writeUTF(msg);
            dos.flush();
            LOG.info(this.getUsername() + " sent message " + msg);
        }
        catch (Exception e){
            LOG.warn("",e);
        }
    }

    public void sendAll(String msg){
        try{
            for(int i = 0;i<ChatServer.online.size();i++){
                ClientHandler ch = ChatServer.online.get(i);
                ch.sendMsg(username + ": " + msg);
            }
        }
        catch (Exception e){
            LOG.warn("",e);
        }
    }

    public void run(){
        sendAll(" Joined chat");
        try{
            while (true){
                String listen = dis.readUTF();
                listen = listen.trim();
                sendAll(listen);
            }
        }
        catch (Exception e){
            LOG.warn("",e);

        }
    }


}
