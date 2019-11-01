package Session6SocketsClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class sampleServerSocket {
	int port = -1;
	List<ServerThread> users = new ArrayList<ServerThread>();
	public static boolean isRunning = true;
	public sampleServerSocket() {
		isRunning = true;
	}
	
	public synchronized void broadcast(String message) {
		//iterate through all clients and attempt to send the message to each
		System.out.println("Sending message to " + users.size() + " users");
		//TODO ensure closed clients are removed from the list
		for(int i = 0; i < users.size(); i++) {
			users.get(i).send(message);
		}
	}
	private void start(int port) {
		this.port = port;
		System.out.println("Waiting for users");
		try(ServerSocket serverSocket = new ServerSocket(port);){
			while(sampleServerSocket.isRunning) {
				try {
					//use Consumer class to pass a callback to the thread for broadcasting messages
					//to all clients
					Consumer<String> callback = s -> broadcast(s);
					Socket client = serverSocket.accept();
					System.out.println("User connected");
					ServerThread thread = new ServerThread(client, 
							"User number " + users.size() ,
							callback);
					thread.start();//start client thread
					users.add(thread);//add to client pool
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				isRunning = false;
				Thread.sleep(50);
				System.out.println("closing server socket");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] arg) {
		System.out.println("Starting Server");
		sampleServerSocket server = new sampleServerSocket();
		int port = -1;
		if(arg.length > 0){
			try{
				port = Integer.parseInt(arg[0]);
			}
			catch(Exception e){
				System.out.println("Invalid port: " + arg[0]);
			}		
		}
		if(port > -1){
			System.out.println("Server listening on port " + port);
			server.start(port);
		}
		System.out.println("Server Stopped");
	}
}
//Class to hold client connection and prevent it from blocking the main thread of the server
class ServerThread extends Thread{
	private Socket user;
	private String userName;
	
	private BufferedReader in;
	private PrintWriter out;
	private boolean isRunning = false;
	private Consumer<String> callback;
	public ServerThread(Socket myUser, String userName, Consumer<String> callback) throws IOException {
		this.user = myUser;
		this.userName = userName;
		this.callback = callback;
		isRunning = true;
		out = new PrintWriter(user.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(user.getInputStream()));
		System.out.println("Spawned thread for user " + userName);
	}
	@Override
	public void run() {
		try{
			String fromUser = "", toUser = "";
			while(isRunning && !"disconnect".equalsIgnoreCase(fromUser) && (fromUser = in.readLine()) != null) {
				
				System.out.println("Received: " + fromUser);
				if(callback != null) {
					toUser = userName + ": " + fromUser;
					System.out.println("Sending: " + toUser);
					callback.accept(toUser);
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("Server cleaning up IO for " + userName);
			cleanup();
		}
	}
	public void stopThread() {
		isRunning = false;
	}
	public void send(String msg) {
		out.println(msg);
	}
	void cleanup() {
		if(in != null) {
			try{in.close();}
			catch(Exception e) { System.out.println("Input already closed");}
		}
		if(out != null) {
			try {out.close();}
			catch(Exception e) {System.out.println("Output already closed");}
		}
	}
}


