package edu.nmsu.cs.webserver;

/**
 * Web worker: an object of this class executes in its own new thread to receive and respond to a
 * single HTTP request. After the constructor the object executes on its "run" method, and leaves
 * when it is done.
 *
 * One WebWorker object is only responsible for one client connection. This code uses Java threads
 * to parallelize the handling of clients: each WebWorker runs in its own thread. This means that
 * you can essentially just think about what is happening on one client at a time, ignoring the fact
 * that the entirety of the webserver execution might be handling other clients, too.
 *
 * This WebWorker class (i.e., an object of this class) is where all the client interaction is done.
 * The "run()" method is the beginning -- think of it as the "main()" for a client interaction. It
 * does three things in a row, invoking three methods in this class: it reads the incoming HTTP
 * request; it writes out an HTTP header to begin its response, and then it writes out some HTML
 * content for the response content. HTTP requests and responses are just lines of text (in a very
 * particular format).
 * 
 * @author Jon Cook, Ph.D.
 * 
 * @author of minor modifications Nasley Chumacero-Martin
 *
 **/

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WebWorker implements Runnable    {
   
   private Socket socket;
   private String pageName;
   
   /**
    * Constructor: must have a valid open socket
    **/
   public WebWorker(Socket s)
   {
      socket = s;
   }
   
   /**
    * Worker thread starting point. Each worker handles just one HTTP request and then returns, which
    * destroys the thread. This method assumes that whoever created the worker created it with a
    * valid open socket object.
    **/
   public void run()   {
      System.err.println("Handling connection...");
      try   {
         InputStream is = socket.getInputStream();
         OutputStream os = socket.getOutputStream();
         String content = readHTTPRequest(is);  //Gets what is in "GET"
         formatString(content);        //Formats what "GET" returns.
         writeHTTPHeader(os, "text/html");  //Don't delete! It breaks the server!
         writeContent(os, pageName);   
         os.flush();
         socket.close();
      }
      catch (Exception e)   {
         System.err.println("Output error: " + e);
      }
      System.err.println("Done handling connection.");
      return;
   }
   
   /*
    * Formats the page request by stripping "GET" and "HTTP".
    * @param content      The unformatted string from "GET".
    */
   private void formatString(String content)   {
      if (content.contains("html"))  {
         pageName = content.substring(content.indexOf('/') + 1, content.indexOf("HTTP")).trim();
      }
      else  {
         pageName = "localhost";
      }
   }
   
   /**
    * Read the HTTP request header. 
    * This method has been modified to read the "GET" request, read what is there 
    * and return the string to later verify if it contains an html page.
    * @param is     The input stream.
    * @return      The string that contains a port number with/without html page request.
    **/
   private String readHTTPRequest(InputStream is)  {
      String line, content = "";
      BufferedReader r = new BufferedReader(new InputStreamReader(is));
      while (true)  {
         try   {
            while (!r.ready())    {
               Thread.sleep(1);
            }
            line = r.readLine();
            System.err.println("Request line: (" + line + ")");
            if ( line.contains("GET") && !line.contains("favicon.ico"))  {   //Reads input string
               content = line;               //and if it contains a GET request, it will now store and return it.
            }
            if (line.length() == 0)    {
               break;
            }
         }
         catch (Exception e)    {
            System.err.println("Request error: " + e);
            break;
         }
      }
      return content;
   }
   
   /**
    * Write the HTTP header lines to the client network connection.
    * 
    * @param os
    *          is the OutputStream object to write to
    * @param contentType
    *          is the string MIME content type (e.g. "text/html")
    **/
   private void writeHTTPHeader(OutputStream os, String contentType) throws Exception   {
      Date d = new Date();
      DateFormat df = DateFormat.getDateTimeInstance();
      df.setTimeZone(TimeZone.getTimeZone("GMT"));
      
      File htmlPage = new File(pageName);
      if (htmlPage.exists() || pageName.equals("localhost"))  {
         os.write("HTTP/1.1 200 OK\n".getBytes());
      }
      else  {
         os.write("HTTP/1.1 404 Not Found\n".getBytes());
      }
      
      os.write("Date: ".getBytes());
      os.write((df.format(d)).getBytes());
      os.write("\n".getBytes());
      os.write("Server: nohemi108.cs371\n".getBytes());  //Changed to my server name
      os.write("Connection: close\n".getBytes());
      os.write("Content-Type: ".getBytes());
      os.write(contentType.getBytes());
      os.write("\n\n".getBytes()); // HTTP header ends with 2 newlines
      return;
   }
   
   /**
    * Write the data content to the client network connection. This MUST be done after the HTTP
    * header has been written out.
    * The method has been modified to serve a page if it exists, or provide a 404 message
    * otherwise.
    * @param os
    *          is the OutputStream object to write to
    **/
   private void writeContent(OutputStream os, String pageName) throws IOException   {
      File htmlPage = new File (pageName);
      //If the GET requests the  basic host with no html page, the original message prints.
      try   {
         if (pageName == "localhost")  {
            os.write("<html><head></head><body>\n".getBytes());
            os.write("<h2>The web server is working.</h2>\n".getBytes());
            os.write("</body></html>\n".getBytes());
         }
         //If GET request contains a page name, then a page is opened
         else if (htmlPage.exists())  {
            Scanner input = new Scanner(htmlPage);
            Date today = new Date();
            String todayFormat = DateFormat.getDateInstance(DateFormat.MEDIUM).format(today);
            while (input.hasNext()) {
               String line = input.nextLine();
               //Replaces the date and server tags in the file
               if (line.contains("<cs371date>") || line.contains("<cs371server>") )   {
                  line = line.replaceAll("<cs371date>", todayFormat);
                  line = line.replaceAll("<cs371server>","nohemi108 Server");
               }
               os.write(line.getBytes());
            }
            input.close();
         }
         //Gives the 404 Page Not Found Error
         else if (!htmlPage.exists() )  {
            os.write("<html> <body> <h1><center>404 Page Was Not Found</center></h1>".getBytes());
            os.write("<p><center><br>You probably typed the wrong page... </center></p>".getBytes());
            os.write("<br> <br>".getBytes());
            os.write("<p><center>Or the server is having issues. <br>".getBytes());
            os.write("Either way, this page doesn't exist, so try again!</center></p>".getBytes());
            os.write("</body> </html>".getBytes());
         }
      }
      //Catches exception so that browser that doesn't give stock error.
      catch (FileNotFoundException error)  {
      }
   }
   
}
