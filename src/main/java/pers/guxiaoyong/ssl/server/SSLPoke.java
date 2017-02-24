package pers.guxiaoyong.ssl.server;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

/**
 * Establish a SSL connection to a host and port, writes a byte and prints the response - Ashok Goli. See
 * http://confluence.atlassian.com/display/JIRA/Connecting+to+SSL+services
 */
public class SSLPoke {

  /**
   * The main method.
   * Usage: $java -jar SSLPoker.jar <host> <port>
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
//    if (args.length != 2) {
//      System.out.println("Usage: " + SSLPoke.class.getName() + " <host> <port>");
//      System.exit(1);
//    }
    try {
      SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
      SSLSocket sslsocket =
          (SSLSocket) sslsocketfactory.createSocket(args[0], Integer.parseInt(args[1]));

      InputStream in = sslsocket.getInputStream();
      OutputStream out = sslsocket.getOutputStream();

      // Write a test byte to get a reaction :)
      out.write(1);

      while (in.available() > 0) {
        System.out.print(in.read());
      }
      System.out.println("Successfully connected");

    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}