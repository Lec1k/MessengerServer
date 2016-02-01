package messenger.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Created by LeC1K on 01.02.2016.
 */
public class ChatServer implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(ChatServer.class);
    ServerSocket ss ;
    Socket skt;
    public static Vector<ClientHandler> online;

    public ChatServer(){
        try {
            ss = new ServerSocket(8881);
            LOG.info("Listening to port 5000");
            online = new Vector<ClientHandler>();
        }
        catch (Exception e){
            LOG.warn("",e);
        }
    }

    public void monitoring(){
        try{
            while (true){
                skt = ss.accept();
                SocketHandler sh = new SocketHandler(skt);
                LOG.info("Client accepted");
                sh.start();
            }
        }
        catch (Exception e ){
            LOG.warn("",e);
        }
    }

    @Override
    public void run() {
        try{
            monitoring();
        }
        catch (Exception e){
            LOG.warn("",e);
        }
    }
}
