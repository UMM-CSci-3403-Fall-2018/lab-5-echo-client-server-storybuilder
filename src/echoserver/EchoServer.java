package echoserver;

import java.net.*;
import java.io.*;

public class EchoServer
{
  public static final int portNumber = 6013;

    public static void main(String[] args) {

      try {

        //Visual feedback to show the server started properly
        System.out.println("Server started successfully!");

        // Start listening on the specified port
        ServerSocket sock = new ServerSocket(portNumber);

        // Run forever, which is common for server style services
        while (true) {

          // Wait until someone connects, then establish streams for connected client
          Socket client = sock.accept();
          InputStream input = client.getInputStream();
          OutputStream output = client.getOutputStream();

          //Visual feedback to show the client connected
          System.out.println("Client Connected");

          //Read all incoming data from a connected client
          int b;
          while ((b = input.read()) != -1)
          {
            //Print it back to the client
            System.out.println("Wrote " + Integer.toString(b) + " (" + (char)b + ") to the client.");
            output.write(b);
          }

          // Close the client socket since we're done.
          client.close();
        }
      // *Very* minimal error handling.
      } catch (IOException ioe) {
        System.out.println("We caught an unexpected exception");
        System.err.println(ioe);
      }
    }
}
