package org.hillel.net;

import org.hillel.Consumer;
import org.hillel.net.MessageListener;
import org.hillel.net.ServerBootstrap;

import java.io.File;
import java.io.IOException;

/**
 * Created by HillelNew8 on 26.12.2016.
 */
public class Main {



    public static void main(String[] args) throws IOException {
        MessageListener listener = new Consumer(new File("C:\\Users\\HillelNew8\\Documents\\string.txt"));

        int port = 4444;
        ServerBootstrap serverBootstrap = new ServerBootstrap(listener, port);

    }
}
