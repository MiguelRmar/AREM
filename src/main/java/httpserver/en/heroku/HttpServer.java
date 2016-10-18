/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver.en.heroku;

import java.net.*;
import java.io.*;

/**
 *
 * @author miguel
 */
public class HttpServer {
  public static void main(String[] args) throws IOException {
   ServerSocket serverSocket = null;
   Socket clientSocket = null;
   
   while(true){
       
    try {
       serverSocket = new ServerSocket(new Integer(System.getenv("PORT")));
    } catch (IOException e) {
       System.err.println("Could not listen on port: "+ new Integer("36000"));
       System.exit(1);
    }


    try {
        System.out.println("Listo para recibir ...");
        clientSocket = serverSocket.accept();
    } catch (IOException e) {
        System.err.println("Accept failed.");
        System.exit(1);
    }
    PrintWriter out = new PrintWriter(
                          clientSocket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(
                          new InputStreamReader(clientSocket.getInputStream()));
    String inputLine, outputLine;
    while ((inputLine = in.readLine()) != null) {
       System.out.println("Recibí: " + inputLine);
       if (!in.ready()) {break; }
    }
    outputLine = 
           "<!DOCTYPE html>" + 
           "<html>" + 
           "<head>" + 
           "<meta charset=\"UTF-8\">" + 
           "<title>Title of the document</title>\n" + 
           "</head>" + 
           "<body>" + 
           "<h1>Mi propio mensaje</h1>" + 
           "</body>" + 
           "</html>" + inputLine; 
     out.println(outputLine);
     out.close(); 
     in.close(); 
     clientSocket.close(); 
     serverSocket.close(); 
   }
  }
}
