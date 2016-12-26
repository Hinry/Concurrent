package org.hillel;

import org.hillel.net.MessageListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by HillelNew8 on 26.12.2016.
 */
public class Consumer implements MessageListener{

    private File file;

    public Consumer(File file){
        this.file = file;
    }

    public void newMessage(String text){
        try {
            FileWriter writer = new FileWriter(file,true);
            writer.write(text+"\r\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
