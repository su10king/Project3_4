package ThreadPool;

import java.io.IOException;
import java.io.InputStream;

public class Demultiplexer implements Runnable {

    @Override
    public void run() {

        try {
            InputStream inputStream = socket.getInputSteram();

            byte[] buffer = new byte[HEADER_SIZE];
            inputStream.read(buffer);
            String header = new String(buffer);

            handleMap.get(header).handleEvent(inputStream);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
