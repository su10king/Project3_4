package ThreadPool;

import Handler.HandleMap;

import java.net.ServerSocket;

public interface Dispatcher {
    public void dispatch(ServerSocket serverSocket, HandleMap handlers);
}
