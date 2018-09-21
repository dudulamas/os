package work_02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;

public class DataClient {
	
	public static void main(String[] args) throws IOException {
		InputStream in_stream = null;
		BufferedReader bin = null;
		Socket sock = null;
		
		//Mensagem que será enviada:
		HashMap<String, String> message = new HashMap<String, String>();
		
		ObjectInputStream in = null;
	    ObjectOutputStream out = null;

		try {
			//Create Connection:
			sock = new Socket("127.0.0.1", 5000);
			
			//Build message:
			//message.put("text", "Teste 2");
			//message.put("to", "joao@example.com");
			//message.put("from", "adriano@example.com");
			//message.put("at", new Date().toString());
			//message.put("action", "send");
			
			message.put("action", "receive");
			message.put("user", "joao@example.com");
			
			// we have a connection
			//PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);
			
			out = new ObjectOutputStream(new BufferedOutputStream(sock.getOutputStream()));
			//in = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));

			System.out.println("in out");
			
			out.writeObject(message);
		    out.flush();
		    System.out.println("Sent obj");
		    
			// write the String to the socket
			//String input = new String("+:1:2");
			//{"text" : "Teste", "to" : "example@example.com"}
			//pout.println(message);
			//pout.flush();

			/*
			// Receive data from Server:
			in = sock.getInputStream();
			bin = new BufferedReader(new InputStreamReader(in));

			String line;
			while( (line = bin.readLine()) != null)
				System.out.println(line);
			*/
			
			sock.close();
		}
		catch (IOException ioe) {
				System.err.println(ioe);
		}
                finally {
                    sock.close();
                }
	}

}
