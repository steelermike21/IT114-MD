package Session6SocketsClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
//SOme extra code in here as im trying to get private messaging to work .... 12/12/ now complete
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
		for(ClientandHandler update: handlers) {//loops through when someone joins the chat
			update.writerguy.println("The following user " +getUser()+" Has joined the chat");
			update.sendList();
		}
		temp="";//while loop that allows private messaging to happen
		while((temp = buff.readLine()) != null){
			if(temp.contains("&user=")){
				int beginUsername=temp.indexOf("&user=")+6;
				String message=temp.substring(0, temp.indexOf("&user="));
				String privUser=temp.substring(beginUsername).trim();
				for(ClientandHandler update:handlers){
					if(update.getUser().equals(privUser)){
						update.writerguy.println(getUser()+": (PRIVATE) "+message);
					}
				}
				this.writerguy.println(getUser()+": (PRIVATE) "+message);
			}
			else{
				for (ClientandHandler update : handlers){

					if(temp.trim().equals("418")){
						status=true;
					}
					else{	
						update.writerguy.println(getUser()+": "+temp);
					}

				}

			}
			if(status){
				handlers.remove(this);
				break;
			}
		}
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
		//gets users
	public String getUser() {
		return user;
	}
	//sends the list to the UI
	public void sendList() {
		writerguy.println(handlers.size());
		for(ClientandHandler update:handlers) {
			writerguy.println(update.getUser());
		}
	}
	

}
