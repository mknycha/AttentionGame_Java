import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BrokenBarrierException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.text.Position;


public class FocusGame implements ActionListener {

	MyButton mbtn1, mbtn2, mbtn3, mbtn4, mbtn5, mbtn6, mbtn7, mbtn8, mbtn9, mbtn10, mbtn11, mbtn12, mbtn13, mbtn14, mbtn15, mbtn16;
	MyButton[] arrayMbtns = {mbtn1, mbtn2, mbtn3, mbtn4, mbtn5, mbtn6, mbtn7, mbtn8, mbtn9, mbtn10, mbtn11, mbtn12, mbtn13, mbtn14, mbtn15, mbtn16};
	List listMbtns = Arrays.asList(arrayMbtns);
	ArrayList<String> listAllNames = new ArrayList<>();
	LinkedList<String> listTrueNames = new LinkedList<String>();
	ButtonNamesContainer namesContainer = new ButtonNamesContainer();
	private int numberAllButtons = 16;
	private int numberTrueButtons = 0;
	private int buttonsClicked = 0;
	private int pointsReceived = 0;
	private int trueButtonsClicked = 0;
	private int level = 1;
	JFrame jfrm;
	//TODO: Change the names of objects to be more consistent
	
	FocusGame(int numberOfTrueButtons) {
		
		this.numberTrueButtons = numberOfTrueButtons;
		
		jfrm = new JFrame("Attention game");
		jfrm.setLocation(300, 300);
		jfrm.setSize(500, 280);
		jfrm.setResizable(false);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelStart = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 10));
		JLabel lblDesc = new JLabel("<html>Celem gry jest zapamiêtanie pojawiaj¹cych siê nazw owoców. "
				+ "<br><br>Po rozpoczêciu zostanie wyœwietlone kilka owoców. "
				+ "<br>Nastêpnie pojawi siê ekran, wybierz na nim owoce aby zdobyæ punkty."
				+ "<br>Czekaj¹ na Ciebie cztery poziomy. "
				+ "<br>Powodzenia!</html>");
		JButton btnStart = new JButton("START!");
		
		panelStart.add(lblDesc);
		panelStart.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jfrm.remove(btnStart);
				generateGameScreen();
			}
		});
		jfrm.setContentPane(panelStart);
		jfrm.setVisible(true);

	}
	
	public static void main(String[] args) {
		// Tworzy okno w w¹tku rozdzia³u zdarzeñ.
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	        new FocusGame(5);
	      }
	    });
	}
	
	private void assignButtons(int howManyTrues) {
		//Getting buttons names from the namesContainer object
		listAllNames = namesContainer.getButtonsObjects(numberAllButtons);
		//List is shuffled, so that the names assigned to buttons are in random order.
		Collections.shuffle(listAllNames);
		//For every button in the array, we are assigning name from the button names list, and assigning true or false value to a myButton object
		for (int i=0; i<numberAllButtons; i++) {
			if (i <= (howManyTrues-1) ) {
				arrayMbtns[i] = new MyButton(listAllNames.get(i), true);
				listTrueNames.add(listAllNames.get(i));
			} else {
				arrayMbtns[i] = new MyButton(listAllNames.get(i), false);
			}
		}
		//List must be shuffled one more time, so that we get buttons in random order
		Collections.shuffle(listMbtns);
	}
	
	private void generateGameScreen() {
		assignButtons(numberTrueButtons);
		JPanel gridpanel = new JPanel(new GridBagLayout());
		JLabel lblInfo = new JLabel("");
		jfrm.setContentPane(gridpanel);
		gridpanel.add(lblInfo);	
    	lblInfo.setText("Skup siê...");
		jfrm.revalidate();
		jfrm.repaint();
		
		Timer timer = new Timer(1000, null);
		ActionListener listener = new ActionListener(){
	        public void actionPerformed(ActionEvent event){
	        	if (!listTrueNames.isEmpty()) {
	        		lblInfo.setText(listTrueNames.get(0));
	        		listTrueNames.remove(0);
	        		jfrm.revalidate();
					jfrm.repaint();
	        	} else {
	        		timer.stop();
	        		gridpanel.remove(lblInfo);
	        		GridBagConstraints c = new GridBagConstraints();
					//Change the place (constraint) where the label should appear
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = 0;
					c.gridy = 0;
					c.ipady = 10;
	        		JLabel lblLevel = new JLabel("Poziom: "+level);
	        		gridpanel.add(lblLevel, c);
	        		addButtonsPanel();
	        		jfrm.revalidate();
					jfrm.repaint();
	        	}

	        }
	    };
	    timer.addActionListener(listener);
	    timer.setRepeats(true); 
	    timer.start();
	    
	}
	private void addButtonsPanel() {
		JPanel jpane = new JPanel(new GridLayout(5, 5, 10, 10));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		for (int j=0; j<16; j++) {
			MyButton tempMyButton = arrayMbtns[j];
			jpane.add(tempMyButton);
			tempMyButton.addActionListener(this);
			tempMyButton.setActionCommand("fruitButton");
		}

		jfrm.getContentPane().add(jpane, c);
	}
	
	private void generateEndScreen() {
		JPanel flowpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 15));
		Font f = new Font("serif", Font.BOLD, 24);
		JLabel lblMessage = new JLabel("Koniec gry!");
		lblMessage.setFont(f);
		JLabel lblScore = new JLabel("Twój wynik: " + pointsReceived);
		JButton btnEnd = new JButton("Wyjœcie");
		flowpanel.add(lblMessage);
		flowpanel.add(lblScore);
		try {
			BufferedImage img = ImageIO.read(getClass().getResource("/strawberry.jpg"));
	        ImageIcon icon = new ImageIcon(img);
	        JLabel lblImage = new JLabel(icon);
	        flowpanel.add(lblImage);
		} catch (IOException e) {
			e.getMessage();
		}
		flowpanel.add(btnEnd);
		btnEnd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		jfrm.setContentPane(flowpanel);
		jfrm.revalidate();
		jfrm.repaint();
	}
	
	public void addSummarySection() {
		GridBagConstraints c = new GridBagConstraints();
		//Change the place (constraint) where the label should appear
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.ipadx = 20;
		jfrm.getContentPane().add(new JLabel(trueButtonsClicked + "/" + numberTrueButtons), c);
		//Again, change the place (constraint) where the button should appear
		c.ipadx = 0;
		c.gridx = 1;
		c.gridwidth = 2;
		//Create the button and add it
		JButton buttonNext = new JButton("PrzejdŸ dalej");
		buttonNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Count the score
				pointsReceived += (trueButtonsClicked * level * 100);
				if (level == 4) {
					//Show ending screen with the score
					generateEndScreen();
				} else {
					//Reset clickedButton counters
					buttonsClicked = 0;
					trueButtonsClicked = 0;
					//Change the level
					numberTrueButtons ++;
					level ++;
					generateGameScreen();
				}
				
			}
		});
		jfrm.getContentPane().add(buttonNext, c);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "fruitButton") {
			if (((MyButton) ae.getSource()).getValue() == true) {
				((MyButton) ae.getSource()).setBackground(Color.GREEN);
				trueButtonsClicked ++;
			} else {
				((MyButton) ae.getSource()).setBackground(Color.RED);
			}
			((MyButton) ae.getSource()).setEnabled(false);
			buttonsClicked ++;
			if (buttonsClicked >= numberTrueButtons) {
				for (MyButton mbtn : arrayMbtns ) {
					mbtn.setEnabled(false);
				}
				addSummarySection();
				jfrm.revalidate();
				jfrm.repaint();
			}
		}
		
	}


}
