package messenger.Model;

/**
 * Created by LeC1K on 21.02.2016.
 */
public class DisconnectHandler implements Runnable {


    @Override
    public void run() {
        for (int i=0;i<ChatServer.online.size();i++) {
            ChatServer.online.get(i).stopClientHandler();
        }
    }
}
