package org.hillel;

import org.hillel.net.MessageListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by HillelNew8 on 26.12.2016.
 */
public class Producer extends Thread{


    private Socket socket;
    private final MessageListener listener;
    public Producer(Socket socket, MessageListener listener){
        this.socket = socket;
        this.listener = listener;
    }

    @Override
    public void run(){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()) )){

            while(!Thread.interrupted()){
                String msg = reader.readLine();

                if(msg.equals("exit") || msg == null){
                    socket.close();
                    break;
                }else{
                    try {
                        listener.newMessage(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() throws IOException {
        if(socket.isConnected()){
            socket.close();
        }
    }

}
