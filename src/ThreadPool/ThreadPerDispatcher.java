package ThreadPool;


import Handler.HandleMap;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadPerDispatcher implements Dispatcher {

    public void dispatch(final ServerSocket serverSocket, final HandleMap handleMap) {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                //4장 nonblocking
                System.out.println("이 부분에서 블로킹되서 구문 실행이 안되는 중");

                Runnable demultiplexer = new Demultiplexer(socket, handleMap);
                Thread thread = new Thread(demultiplexer);
                thread.start();

            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
