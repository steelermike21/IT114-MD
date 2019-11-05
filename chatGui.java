package Session6SocketsClients;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class chatGui {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			JFrame frame = new JFrame("Chat Room!");
			frame.setLayout(new BorderLayout());
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(500,500));
			panel.setLayout((new BorderLayout()));
			JTextArea textbox = new JTextArea();
			textbox.setEditable(false);
			textbox.setText("");
			JPanel chatbox = new JPanel();
			chatbox.setLayout(new BorderLayout());
			chatbox.add(textbox, BorderLayout.CENTER);
			chatbox.setBorder(BorderFactory.createLineBorder(Color.red));
			panel.add(chatbox, BorderLayout.CENTER);
			JPanel usertext = new JPanel();
			JTextField userField = new JTextField();
			userField.setPreferredSize(new Dimension(100,30));
			JButton button = new JButton();
			button.setPreferredSize(new Dimension(100,30));
			button.setText("Send");
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String message = userField.getText();
					if(message.length() > 0) {
						textbox.append("\n" + userField.getText());
						userField.setText("");
						
					}
				}
			});
			
			usertext.add(userField);
			usertext.add(button);
			panel.add(usertext, BorderLayout.SOUTH);
			frame.add(panel, BorderLayout.CENTER);
			frame.pack();
			
			
			
			
			frame.setVisible(true);
			
			
	}

}
