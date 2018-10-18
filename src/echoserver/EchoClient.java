package echoserver;

import java.net.*;
import java.io.*;

public class EchoClient
{
  public static final int portNumber = 6013;

  public static void main(String[] args) throws IOException
  {
    //Create a string of the ip to connect to
    String server;

    // Use "127.0.0.1", i.e., localhost, if no server is specified.
    if (args.length == 0)
    {
      //Localhost
      server = "127.0.0.1";
    }
    else
    {
      //Specified ip
      server = args[0];
    }

    //System.out.write

    try {
      // Connect to the server
      Socket socket = new Socket(server, portNumber);

      // Get the input stream so we can read from that socket
      OutputStream output = socket.getOutputStream();
      InputStream input = socket.getInputStream();

      // Read byte from keyboard
      int b;
      while ((b = System.in.read()) != -1)
      {
        //Possible issue with System.in.read() also capturing things like hitting enter (which results in a 10 on the ASCII table)
        // ^^ Is not an issue, Nic said that that is the end line character which is needed :)
        // Send byte to the server
        output.write(b);

        //output.flush();

        // Server reads byte
        b = input.read();

        // Server writes byte to screen
        System.out.write(b);

      }

      // Close the socket when we're done reading from it
      socket.close();

    // Provide some minimal error handling.
    } catch (ConnectException ce) {
      System.out.println("We were unable to connect to " + server);
      System.out.println("You should make sure the server is running.");
    } catch (IOException ioe) {
      System.out.println("We caught an unexpected exception");
      System.err.println(ioe);
    }
  }
}
