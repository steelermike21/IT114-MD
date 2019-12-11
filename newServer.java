package Session6SocketsClients;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class newServer {

	//New Socket
	Socket socket;
	//For Usernames, handlers used to update list when user joins
	//seperate class for handling users and messages
	ArrayList<ClientandHandler>handlers;
	//references client class
	//Function to start server
	public newServer(){
		
		try {
		//for now, port will be default to 3001
			ServerSocket newsock = new ServerSocket(3001);
			
			handlers= new ArrayList<ClientandHandler>();
			for(;;){
				
				socket= newsock.accept();
				ClientandHandler update = new ClientandHandler(socket, handlers);
				update.start();
						
			}
			
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		newServer test = new newServer();
	}

}
