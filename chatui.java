package Session6SocketsClients;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

//Class created to create gui
public class chatui extends Frame {
	//panels created
	LoginPanel login;
	ChatPanel chat;
	
	//sets size and adds title, listeners, and the panels
	public chatui() {
		setLayout(new CardLayout());
		setSize(700,500);
		setTitle("Chat Client");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
		login=new LoginPanel(this);
		add(login, "login");
		chat = new ChatPanel(this);
		add(chat, "chat");
		setVisible(true);	
	}

	public LoginPanel getLoginPanel(){
		return login;
	}

	
	public static void main(String[] args){
		chatui cu = new chatui();
	}
}
//creates text fields and labels with buttons 
class LoginPanel extends Panel implements ActionListener{
	TextField text;
	Label lab;
	chatui cu;
	Button loginbut;
	String user;

	public LoginPanel(chatui cf){
		setLayout(new BorderLayout());
		this.cu=cf;
		text=new TextField(25);
		text.addActionListener(this);
		lab=new Label("                                       Enter your chat name below, then click \"Login\"");
		add(lab, BorderLayout.NORTH);
		Panel p=new Panel();
		p.add(text);
		loginbut=new Button("Login");
		loginbut.addActionListener(this);
		p.add(loginbut);
		add(p, BorderLayout.CENTER);
	}

	public String getUsername(){
		return user;
	}

	public void actionPerformed(ActionEvent ae){
		user=text.getText();
		CardLayout cl=(CardLayout)(cu.getLayout());
		cl.show(cu, "chat");
	}
}

class ChatPanel extends Panel implements ActionListener, ItemListener, Runnable{
	TextField textfield;
	TextArea textingarea;
	Socket sock;
	PrintWriter writerman;
	BufferedReader buff;
	Thread thread;
	Button connect, disconnect;
	chatui cu;
	String username, privateUser="";
	List users;
	
	public ChatPanel(chatui cu){
		setLayout(new BorderLayout());
		this.cu=cu;
		
		textfield = new TextField();
		textfield.addActionListener(this);
		add(textfield, BorderLayout.NORTH);
		//tf.setEditable(false);
		textingarea = new TextArea();
		textingarea.setText("Press \"Connect\" to connect to this chat room");
		textingarea.setEditable(false);
		add(textingarea, BorderLayout.CENTER);
		users=new List();
		users.addItemListener(this);
		add(users, BorderLayout.EAST);
		
		connect = new Button("Connect");
		connect.addActionListener(this);
		disconnect = new Button("Disconnect");
		disconnect.addActionListener(this);
		disconnect.setEnabled(false);
		Panel q = new Panel();
		q.add(connect);
		q.add(disconnect);
		add(q, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==textfield){
			String temp = textfield.getText();
			if(privateUser!=""){
				writerman.println(temp + "&user="+privateUser);
				users.deselect(users.getSelectedIndex());
			}
			else{
				writerman.println(temp);
			}
			privateUser="";
			textfield.setText("");
		}
		else if(ae.getSource() == connect){
			username=(cu.getLoginPanel()).getUsername();
			try{
				sock = new Socket("localhost", 3001); 
				writerman = new PrintWriter(sock.getOutputStream(), true);
				buff = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				writerman.println(username);
			}
			catch(UnknownHostException uhe){
				System.out.println(uhe.getMessage());
			}
			catch(IOException ioe){
				System.out.println(ioe.getMessage());
			}
			thread = new Thread(this, username);
			thread.start();
			textingarea.setText("");
			connect.setEnabled(false);
			disconnect.setEnabled(true);
		}
		else if(ae.getSource() == disconnect){
			//how to kill connection?
			writerman.println("418");
			writerman = null;
			buff = null;
			sock = null;
			thread = null;
			users.removeAll();			
			disconnect.setEnabled(false);
			connect.setEnabled(true);
		}
	}
	
	public void itemStateChanged(ItemEvent ie){
		privateUser=users.getSelectedItem();
	}
	
	public void run(){
		String temp = "";
		try{
			while(((temp = buff.readLine()) != null) && (thread != null)){			
				textingarea.append(temp+"\n");
				if(temp.contains("the chat room----")){
					updateUserList();
				}
			}
		}
		catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}	
	}

	public void updateUserList(){
		users.removeAll();
		try{
			int listSize=Integer.parseInt(buff.readLine());
			for(int i=0; i<listSize; i++){
				users.add(buff.readLine());
			}
		}
		catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}
}