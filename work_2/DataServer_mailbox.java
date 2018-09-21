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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataServer {
	
	//Atributos:
	private static HashMap<String, List<HashMap<String, String>>> mail_box = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		Socket client = null;
		ServerSocket sock = null;
		//InputStream in = null;
		BufferedReader bin = null;
		Float return_calculate;
		String action = null;
		
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		
		
		
		//mail_box = { "adriano@example" : [<message_1>,], "eduardo@example.com" : [<message_2>], "ricardo" };
		
		try {
			sock = new ServerSocket(5000);
			
			// now listen for connections
			while (true) {
				client = sock.accept();
				System.out.println("\nNew Connection");
				System.out.println("server = " + sock);
				System.out.println("client = " + client);
				
				in = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				//out = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));

				//Recebendo a mensagem:
				HashMap<String, String> message = (HashMap<String, String>) in.readObject();
				
				//Verificando qual a ação que ela quer realizar:
				action = message.get("action");
				
				System.out.println(action);
				
				//Verificando se a ação do Usuário é para enviar um e-mail para
				// alguém:
				if (action.equals("send")) {
					save_message(message);
				    System.out.println("Mail_box: " + mail_box.toString());
					
				}
				
				//Verificando se a ação do Usuário é receber os seus e-mails:
				else if (action.equals("receive")) {
					
					//Se sim, vamos retornar todas as mensagens que ele
					List messages = get_messages(message.get("user"));
					
				    System.out.println("Messages: " + messages);

				}
				
				//Se nenhuma ação for identificada, retornar ao Usuário que esse
				// campo é obrigatório:
				else {
				    System.out.println("Action is required");
				}
				
				
	            //System.out.println(message.get("from"));
	            
				in.close();
				client.close();
			}
		}
		catch (IOException | ClassNotFoundException ioe) {
				System.err.println(ioe);
		}
		finally {
			if (sock != null)
				sock.close();
			if (client != null)
				client.close();
		}
	
	}
	
	private static void save_message(HashMap<String, String> message_to_save) {
		//Capturando o destinatário da mensagem:
		String to = message_to_save.get("to");
		
		
		//Verificando se na mail_box já existe esse destinatário. Se não, ou seja
		// se o valor da condição abaixo for null, criar essa chave:
		if (mail_box.get(to) == null) {
			mail_box.put(to, new ArrayList<HashMap<String , String>>()); 
		}
		
		//Agora que temos a certeza da chave e valor do destinatário, podemos
		// adicionar a mensagem enviada como parâmetro dessa função.
		mail_box.get(to).add(message_to_save);
	}
	
	private static List get_messages(String user) {
		
		if (mail_box.get(user) == null) {
			return null;
		}
		
		//Capturando as mensagens do usuário: 
		return mail_box.get(user);  
			
	}
	
	private static Float calculate(String expression) {
		String[] array = expression.split(":");
		Float result_calculate = null;
		
		switch (array[0]) {
			case "+":				
				result_calculate = Float.parseFloat(array[1]) + Float.parseFloat(array[2]);
				break;
			
			case "-":
				result_calculate = Float.parseFloat(array[1]) - Float.parseFloat(array[2]);
				break;
			
			case "*":
				result_calculate = Float.parseFloat(array[1]) * Float.parseFloat(array[2]);
				break;
			
			case "/":
				result_calculate = Float.parseFloat(array[1]) / Float.parseFloat(array[2]);
				break;
	
		}
		
		return result_calculate;
	}

}
