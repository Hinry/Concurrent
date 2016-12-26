package org.hillel.net;




import org.hillel.Producer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by HillelNew8 on 26.12.2016.
 */
public class ServerBootstrap {

    private ServerSocket serverSocket;
    private Thread acceptorThread;
    private MessageListener listener;

    public ServerBootstrap(MessageListener listener, int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.listener = listener;
        acceptorThread = new Thread(new Acceptor());
        acceptorThread.start();

    }

    public void stop(){
        if(acceptorThread.isAlive()){
            acceptorThread.interrupt();
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void registerSocket(Socket socket){
        Producer producer = new Producer(socket, listener);
        producer.start();
    }
    private class Acceptor implements Runnable{

        public void run() {
            while (!Thread.interrupted()){
                try {
                    Socket socket = serverSocket.accept();
                    registerSocket(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
