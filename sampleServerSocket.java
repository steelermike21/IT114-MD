package Session6SocketsClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class sampleServerSocket {

	public void start(int port) {
		//How to create socket, accept connection, and setup input/output channels
		System.out.println("Waiting for a client connection . . .");	
		try(
			ServerSocket serverSocket = new ServerSocket(port);	
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				)
		{
			System.out.println("Client Connected, waiting for message");
			String fromClient = "";
			int toClient = 0;
			while((fromClient = in.readLine()) != null) {
				System.out.println("Message from client: " + fromClient);
				//List<String> reversedInput = Arrays.asList(fromClient.split(""));
				int count = fromClient.length();
				toClient = count;
				System.out.println("Sending to Client the amount of characters in message: " + toClient);
				if("kill server".equalsIgnoreCase(fromClient)) {
					System.out.println("Client killed server process");
					break;
				}
				
				else{
					out.println(toClient);
				}
			}
			
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Staring server");
		sampleServerSocket server = new sampleServerSocket();
		int port = -1;
		
		if(args.length > 0) {
			try {
				port = Integer.parseInt(args[0]);
			}
			catch(Exception e) {
				System.out.println("Invalid port: " + args[0]);
				
			}
		
		}
		
		if(port > -1) {
			System.out.println("Server listening on port " + port);
			server.start(port);
		}
		
			
		
		System.out.println("Server stopped");
	}

}
