package vttp2022.sdfassessment;

import java.io.IOException;
import java.net.UnknownHostException;

public class Main
{
    public static void main( String[] args ) throws UnknownHostException, IOException 
    {
        String host = args[0];
        Integer port = Integer.parseInt(args[1]);
        Client client = new Client(host, port);
        client.start();
    }
}
