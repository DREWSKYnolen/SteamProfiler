import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI extends JPanel
{
	
	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame("Steam Profile Lookup");
	JFrame frame2 = new JFrame();
	private JButton submit = new JButton("Submit");
	//private JButton clear = new JButton("Clear Entries");
	private JTextField username = new JTextField("");
	private JTextArea results = new JTextArea("");
	private JLabel resultLabel = new JLabel("Account Information:");
	private JLabel usernameLabel = new JLabel("Enter a Steam Username:");
	JLabel avatar = new JLabel();
	private static final int FRAMEHEIGHT = 800;
	private static final int FRAMEWIDTH =600;
	ImageIcon icon = new ImageIcon("steam_logo.png");
	
	public GUI() 
	{
		//frame.setIconImage(img.getImage());
		setLayout(null);
		setSize(FRAMEWIDTH, FRAMEHEIGHT);
		usernameLabel.setSize(450,50);
		usernameLabel.setLocation(25,0);
		usernameLabel.setFont(new Font("Ariel", Font.BOLD, 20));
		add(usernameLabel);
		username.setSize(440,50);
		username.setLocation(25,50);
		username.setEditable(true);
		username.setFont(new Font("Ariel", Font.PLAIN, 18));
		add(username);
		submit.setSize(80,50);
		submit.setLocation(490,50);
		submit.addActionListener(new ButtonClickHandler());
		add(submit);
		resultLabel.setSize(440,50);
		resultLabel.setLocation(25,100);
		resultLabel.setFont(new Font("Ariel", Font.BOLD, 20));
		add(resultLabel);
		results.setSize(540,275);
		results.setLocation(25,450);
		results.setFont(new Font("Ariel", Font.PLAIN, 18));
		results.setEditable(false);
		add(results);
		add(avatar);
		
	}
	
	public void display()
	{
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setContentPane(this);
		frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
		frame.setVisible(true);	
	
	}
	
	private void writeResults(Profile prof) throws MalformedURLException, IOException
	{
		results.setText("Steam Status: " + prof.status[prof.getOnlineStatus()] + "\nSteam ID: " + prof.getSteamID()
		+ "\nSteam Username: " + prof.getUserName()	+"\nLast login time: " + prof.getLogOffString() 
		+ "\nSteam Profile URL: " + prof.getProfileURL());
		
		if (prof.getTimeCreated() != 0)
			results.append("\nAccount Created: " + prof.getAccountCreatedString());
		if (prof.getRealName() != null)
			results.append("\nReal Name: " + prof.getRealName()); 
		if (prof.getCountryCode() !=null)
			results.append("\nCountry Code: " + prof.getCountryCode());
		if (prof.getStateCode() != null)
			results.append("\nState Code: " + prof.getStateCode());
		if (prof.getGameExtraInfo() != null)
			results.append("\nIn Game: " + prof.getGameExtraInfo());
		if (prof.getGameID() != null)
			results.append("\nID of Game: " + prof.getGameID());
		
		//JFrame frame = new JFrame();
		URL url = new URL(prof.getAvatarURL());
//		Image image = ImageIO.read(url);
//        BufferedImage image = ImageIO.read(url);
//        ImageIcon imgThisImg = new ImageIcon(prof.getAvatarURL());
//        JLabel avatar = new JLabel(new ImageIcon(image));
//        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(avatar);
//        frame.pack();
//        frame.setLocation(200, 200);
//        frame.setVisible(true);
//        frame.setVisible(false);
        
        avatar.setIcon(new ImageIcon(url));
        ImageIcon image2 = new ImageIcon(url);
        avatar.setIcon(image2);
        avatar.setLocation(200,150);
        avatar.setSize(300,300);
        add(avatar);
		repaint();
	}
	

	
	private class ButtonClickHandler implements ActionListener
	{	
		Usernames uns = new Usernames();
		
		public void actionPerformed(ActionEvent e){
			if (e.getSource() == submit)
			{
				String enteredName = username.getText();
				try {
					uns.name2ID(enteredName);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					results.setText("Invalid or non-existant Username");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					results.setText("Invalid or non-existant Username");
				}
				try {
					Profile prof = new Profile(uns.name2IDRetString(enteredName));
					writeResults(prof);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					results.setText("Invalid or non-existant Username");
				}
			}
		}
	}
}
