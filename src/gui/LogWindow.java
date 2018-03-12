package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import models.*;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/*
 * Class to choice players and start game
 */
public class LogWindow extends JFrame implements ActionListener, ListSelectionListener {

	private JPanel contentPane;
	private JPanel panelAction,panelPlayer,panelListPlayers,panelAllPlayers;
	private JButton btnPlay,btnShowRankPlayers,btnExit,btnRemove;
	private JTextField txtPlayerName;
	private JList listOfPlayers,listAllPlayers;
	private DefaultListModel modelChoicePlayer, modelAllPlayers;
	private JButton btnAdd;
	private Players players;
	private boolean createUser = true;
	private ArrayList<User> usersList = new ArrayList<User>();
	private NeuroshimaApp mainFrame;
	private JScrollPane scrollAllPlayers;
	/**
	 * 
	 * Inner class for players 
	 *
	 */
	@XmlRootElement(name = "Players")
	static class Players
	{
		@XmlElement (name = "Player")
		List<User> playersList=null;

		public List<User> getPlayersList() {
			return playersList;
		}

	}
	/**
	 * Create the frame.
	 */
	public LogWindow(NeuroshimaApp app) {
		mainFrame = app;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		players = new Players();

		panelAction = new JPanel();
		panelAction.setBounds(12, 13, 171, 219);
		contentPane.add(panelAction);
		panelAction.setLayout(null);
		panelAction.setBorder(BorderFactory.createTitledBorder("Menu"));

		btnPlay = new JButton("Play");
		btnPlay.setBounds(12, 23, 147, 53);
		btnPlay.addActionListener(this);
		btnPlay.setEnabled(false);
		panelAction.add(btnPlay);

		btnShowRankPlayers = new JButton("Show rank players");
		btnShowRankPlayers.setBounds(12, 89, 147, 53);
		btnShowRankPlayers.addActionListener(this);
		panelAction.add(btnShowRankPlayers);

		btnExit = new JButton("Exit");
		btnExit.setBounds(12, 155, 147, 51);
		btnExit.addActionListener(this);
		panelAction.add(btnExit);

		panelPlayer = new JPanel();
		panelPlayer.setBounds(208, 13, 226, 75);
		contentPane.add(panelPlayer);
		panelPlayer.setBorder(BorderFactory.createTitledBorder("Player"));
		panelPlayer.setLayout(null);

		txtPlayerName = new JTextField();
		txtPlayerName.setBounds(92, 27, 116, 22);
		txtPlayerName.addActionListener(this);
		panelPlayer.add(txtPlayerName);
		txtPlayerName.setColumns(10);

		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setBounds(12, 30, 68, 16);
		panelPlayer.add(lblNickname);

		btnAdd = new JButton("");
		btnAdd.setBounds(440, 38, 35, 25);
		btnAdd.setBorder(BorderFactory.createEmptyBorder());
		btnAdd.setContentAreaFilled(false);
		try {
			Image img = ImageIO.read(getClass().getResource("/gui/images/add.png"));
			btnAdd.setIcon(scaleImage(img, btnAdd.getWidth(), btnAdd.getHeight()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		btnAdd.addActionListener(this);
		contentPane.add(btnAdd);

		panelListPlayers = new JPanel();
		panelListPlayers.setBounds(208, 101, 267, 131);
		contentPane.add(panelListPlayers);
		panelListPlayers.setBorder(BorderFactory.createTitledBorder("List of players"));
		panelListPlayers.setLayout(null);

		modelChoicePlayer = new DefaultListModel();
		listOfPlayers = new JList(modelChoicePlayer);
		listOfPlayers.setBackground(SystemColor.menu);
		listOfPlayers.setBounds(12, 23, 197, 95);
		listOfPlayers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listOfPlayers.setLayoutOrientation(JList.VERTICAL);
		listOfPlayers.setVisibleRowCount(-1);
		panelListPlayers.add(listOfPlayers);

		btnRemove = new JButton("");
		btnRemove.setContentAreaFilled(false);
		btnRemove.setBorder(BorderFactory.createEmptyBorder());
		btnRemove.setBounds(221, 23, 35, 25);
		try {
			Image img_1 = ImageIO.read(getClass().getResource("/gui/images/remove.png"));
			btnRemove.setIcon(scaleImage(img_1, btnAdd.getWidth(), btnAdd.getHeight()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		btnRemove.addActionListener(this);
		panelListPlayers.add(btnRemove);
		
		panelAllPlayers = new JPanel();
		panelAllPlayers.setBounds(487, 13, 160, 219);
		panelAllPlayers.setBorder(BorderFactory.createTitledBorder("Available players"));
		contentPane.add(panelAllPlayers);
		
		modelAllPlayers = new DefaultListModel();
		listAllPlayers = new JList(modelAllPlayers);
		listAllPlayers.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Object z = e.getSource();
				if(z==listAllPlayers)
				{
					txtPlayerName.setText("");
				}
			}
		});
		panelAllPlayers.setLayout(null);
		listAllPlayers.setBackground(SystemColor.menu);
		listAllPlayers.setBounds(12, 24, 136, 182);
		listAllPlayers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listAllPlayers.setLayoutOrientation(JList.VERTICAL);
		listAllPlayers.setVisibleRowCount(-1);
		//scrollAllPlayers = new JScrollPane(listAllPlayers);
		//scrollAllPlayers.setBounds(12, 207, 136, -184);
		panelAllPlayers.add(listAllPlayers);
		this.setLocationRelativeTo(null);
		this.setTitle("Neuroshima");
		
		unmarshal();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object z = e.getSource();

		if(z == btnAdd)
		{
			if(modelChoicePlayer.getSize()==4)
			{
				JOptionPane.showMessageDialog(this, "Too many players! (max. 4 players)", "Waring", JOptionPane.WARNING_MESSAGE);
			}
			else {
				addUserToGame();
				if(modelChoicePlayer.getSize()>1) btnPlay.setEnabled(true);	
				else btnPlay.setEnabled(false);
			}
		}
		else if(z == btnExit)
		{
			System.exit(0);
		}
		else if(z == btnPlay)
		{
			for(int i=0; i<modelChoicePlayer.getSize();i++)
			{
				for(User user: players.playersList)
				{
					if(user.getName().equals(modelChoicePlayer.getElementAt(i)))
						usersList.add(user);
				}
			}
			this.setVisible(false);
			mainFrame.ShowMainFrame();
		}
		else if( z== btnShowRankPlayers)
		{

		}
		else if(z==btnRemove)
		{
			if(modelChoicePlayer.size()!=0 && listOfPlayers.getSelectedIndex()!=-1)
			{
				modelChoicePlayer.removeElementAt(listOfPlayers.getSelectedIndex());
			}
			else JOptionPane.showMessageDialog(this,"Empty list or do not choose player!" );
			if(modelChoicePlayer.size()<2) btnPlay.setEnabled(false);
		}
		else if(z==txtPlayerName)
		{
			listAllPlayers.clearSelection();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object z = e.getSource();
		
		if(z== listAllPlayers)
		{
			System.out.print("LuL1");
		}
		
	}
	
	/**
	 * Function to add user
	 */
	public void addUserToGame()
	{
		boolean isOnList=false;
		if(txtPlayerName.getText().length()!=0)
		{
			for(User user: players.playersList)
			{
				if(txtPlayerName.getText().equals(user.getName()))
				{
					if(JOptionPane.showConfirmDialog(this, "This nick is used, would you like to play as this player?","Question",
							JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE)==0)
					{
						for(int i=0;i<modelChoicePlayer.size();i++)
						{
							if(user.getName().equals(modelChoicePlayer.getElementAt(i)))
							{
								isOnList=true;
								break;
							}
						}
						if(isOnList==false) {
							modelChoicePlayer.addElement(user.getName());
							txtPlayerName.setText("");
							createUser= false;
						}
						else JOptionPane.showMessageDialog(this, "This user is on list!");
					}
					createUser= false;
					txtPlayerName.setText("");
					break;
				}
			}
			if(createUser)
			{
				User user = new User(players.playersList.size()+1,txtPlayerName.getText(),0);
				players.playersList.add(user);
				modelChoicePlayer.addElement(user.getName());
				marshall();
				txtPlayerName.setText("");
			}
			createUser = true;
		}
		else if(listAllPlayers.getSelectedIndex()!=-1)
		{
			for(int i=0; i<modelChoicePlayer.size(); i++)
			{
				if(listAllPlayers.getSelectedValue().toString().substring(2).equals(modelChoicePlayer.getElementAt(i).toString()))
				{
					isOnList=true;
					break;
				}
			}
			if(isOnList==false) 
			{
				modelChoicePlayer.addElement(listAllPlayers.getSelectedValue().toString().substring(2));
				txtPlayerName.setText("");
			}
			else JOptionPane.showMessageDialog(this, "This user is on list!");
		}
		else {
			JOptionPane.showMessageDialog(this, "Field with nickname is empty!");
		}
	}

	/**
	 * Read from xml file to playersList
	 */
	public void unmarshal()
	{
		try {			
			JAXBContext jabx = JAXBContext.newInstance(Players.class);

			Unmarshaller unmarsh = jabx.createUnmarshaller();
			File save = new File("DataPlayers.xml");
			if(save.canExecute())
				players = (Players) unmarsh.unmarshal(new File("DataPlayers.xml"));
			for(int i=0; i<players.playersList.size(); i++)
			{
				modelAllPlayers.addElement(i + "." + players.playersList.get(i).getName());
			}
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Save playersList to xml file
	 * 
	 */
	public void marshall()
	{
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Players.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(players, new File("DataPlayers.xml"));
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param img - image to scale
	 * @param width - new img width
	 * @param height - new img height
	 * @return imgIcon
	 */
	public ImageIcon scaleImage(Image img, int width, int height)
	{
		Image scaleImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon imgIcon= new ImageIcon(scaleImg);
		return imgIcon;
	}

	/**
	 * 
	 * @return List of users who will play game
	 */
	public ArrayList<User> getUsersList() {
		return usersList;
	}
}
