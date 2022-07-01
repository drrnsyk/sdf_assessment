package vttp2022.sdfassessment;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private String hostname = "localhost";
    private Integer port = 8080;
    private String request;
    private Float result;
    private String name = "Darren Sim Yi Kang";
    private String email = "darren.syk@gmail.com";

    public Client (String host, int port) {
        this.hostname = host;
        this.port = port;
    }

    public boolean readBoolean (boolean response) {
        return response;
    }
    
    public void start () throws UnknownHostException, IOException {

        Socket clientSock = new Socket(hostname, port);

        OutputStream os = clientSock.getOutputStream();
        InputStream is = clientSock.getInputStream();
    
        ObjectOutputStream oos = new ObjectOutputStream(os);
        ObjectInputStream ois = new ObjectInputStream(is);
    
        request = ois.readUTF();
        System.out.println(request);

        Calculate calculate = new Calculate(request);
        result = calculate.average();
        System.out.println("The avg is: " + result);

        System.out.println("This is the request id: " + calculate.getRequestID());

        oos.writeUTF(calculate.getRequestID());
        oos.writeUTF(name);
        oos.writeUTF(email);
        oos.writeFloat(result);
        oos.flush();

        if (readBoolean(ois.readBoolean())) {
            System.out.println("SUCCESS");
            os.close();
            is.close();
        } 
        else 
        {
            System.out.println("FAILED");
            System.out.println(ois.readUTF());
            ois.close();
        }
    }
}
