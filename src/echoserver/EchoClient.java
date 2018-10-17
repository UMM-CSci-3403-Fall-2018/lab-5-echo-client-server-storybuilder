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


      //While there is more characters in the keyboard input(?)
      int b;
      while ((b = System.in.read()) != -1)
      {
        output.write(b);
      }

      output.flush();

      System.out.println("Finished writing...");

      //Read the binary from the server ((CURRENTLY THE PROBLEM))
      int c;
      while ((c = input.read()) != -1)
      {
        System.out.println(c);
      }

      System.out.println("Finished reading...");

      input.close();

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
