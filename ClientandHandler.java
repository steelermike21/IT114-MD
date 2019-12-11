package Session6SocketsClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
//SOme extra code in here as im trying to get private messaging to work
public class ClientandHandler extends Thread {
	
	Socket socket;
	BufferedReader buff;
	String temp;
	PrintWriter writerguy;
	ArrayList<ClientandHandler> handlers;
	String user;
	boolean status;

	
	public ClientandHandler(Socket socket, ArrayList<ClientandHandler>handlers) {
		this.socket=socket;
		this.handlers=handlers;
		status=false;
	}
	
	public void runChat() {
		try {
			
		//The array list handles new users getting added
		handlers.add(this);
		
		buff=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writerguy= new PrintWriter(socket.getOutputStream(), true);
		user=buff.readLine();
		for(ClientandHandler update: handlers) {
			update.writerguy.println("The following user " +getUser()+" Has joined the chat, say hi!");
			update.sendList();
		}
		temp="";
	
	}
	
	catch(IOException e) {
		System.out.println(e.getMessage());
	}
	finally {
		if(handlers.contains(this)) {
			handlers.remove(this);
		}
		for(ClientandHandler update:handlers){
			update.writerguy.println(getUser()+" has left the chat");
			update.sendList();}
		}
	}
		
	public String getUser() {
		return user;
	}
	
	public void sendList() {
		writerguy.println(handlers.size());
		for(ClientandHandler update:handlers) {
			writerguy.println(update.getUser());
		}
	}
	

}
