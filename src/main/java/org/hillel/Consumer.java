package org.hillel;

import org.hillel.net.MessageListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by HillelNew8 on 26.12.2016.
 */
public class Consumer implements MessageListener , Runnable{

    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    private File file;

    public Consumer(File file){
        this.file = file;
    }

    public void newMessage(String text) throws InterruptedException {
        queue.put(text);

    }
    private void saveToFile(String text){
        try {
            FileWriter writer = new FileWriter(file,true);
            writer.write(text+"\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            saveToFile(queue.poll());
        }
    }
}
