package ThreadPool;

import Handler.HandleMap;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadPoolDispatcher implements Dispatcher {


        static final String NUMTHERADS = "8";
        static final String THREADPROP = "Threads";

        private int numThreads;

        public ThreadPoolDispatcher() {
            numThreads = Integer.parseInt(System.getProperty(THREADPROP, NUMTHERADS));
        }

        public void dispatch(final ServerSocket serverSocket, final HandleMap handleMap){
            for (int i = 0; i < (numThreads - 1); i++) {
                Thread thread = new Thread() {
                    public void run () {
                        dispatch(serverSocket, handleMap);
                    }
                };
                thread.start();
                System.out.println("Created 새로운 생성 스레드" + thread.getName());


            }
            System.out.println("Iterative server starting in main thread" +
                    Thread.currentThread().getName());

            dispatchLoop(serverSocket, handleMap);
        }

        private void dispatchLoop(ServerSocket serverSocket, HandleMap handleMap) {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    Runnable demultiplexer = new Demultiplexer(socket, handleMap);
                    demultiplexer.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}
