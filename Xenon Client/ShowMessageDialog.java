/** 
 * ShowMessageDialogue
 * Purpose: To launch a basic java applet before the client opens.
 * Made by Trisidia
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.GroupLayout.*;
import java.io.*;
import javax.swing.JTextField;
import java.applet.*;
import java.net.*;

public class ShowMessageDialog {
	
	JButton button;
	
	public static void main(String[] args){
		ShowMessageDialog md = new ShowMessageDialog();
	}


	public ShowMessageDialog(){
		JFrame frame = new JFrame("Please select an option:");
		JPanel panel = new JPanel();
		button = new JButton("Popout Client");
		button.addActionListener(new MyAction());
		panel.add(button);
		button = new JButton("Webclient");
		button.addActionListener(new MyAction());
		panel.add(button);
		frame.add(panel);
		frame.setSize(300, 100);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public class MyAction implements ActionListener {
	
		public void actionPerformed(ActionEvent ae){
			String str = ae.getActionCommand();
			if(str.equals("Popout Client")){
				JOptionPane.showMessageDialog(null, "You have selected the popout client, and it will now launch.", "Popout client selected", 1);
				try {
				Runtime.getRuntime().exec("initiate.bat");
				terminateApplet();
			}
			catch (Exception e){
			System.out.println(e.getMessage());
			}
		}
			if(str.equals("Webclient")){
				JOptionPane.showMessageDialog(null, "You have selected the webclient, and it will now launch.", "Webclient selected", 1);
				String link = "http://mrzeerak8520.wix.com/xenon";
					try
				{ 
					Process p=Runtime.getRuntime().exec("cmd /c start http://mrzeerak8520.wix.com/xenon"); 
					terminateApplet();
				} 
					catch (Exception e){
				}	
		}	
	}
}
	 
	public void terminateApplet() {
		System.exit(0);
		}
		
}