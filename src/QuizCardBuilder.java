import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;

public class QuizCardBuilder {
    private JFrame frame;
    private JTextArea qTextArea;
    private JTextArea aTextArea;
    private JButton nextBtn;
    private JMenuItem saveItem;
    private JMenuItem newItem;
    private ArrayList<QuizCard> allCards;
    
    public static void main(String[] args){
	QuizCardBuilder ac = new QuizCardBuilder();
    }
        
	public QuizCardBuilder(){
		initialize ();
		allCards = new ArrayList<QuizCard>();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void initialize (){
	    // build and display GUI
	    //setup frame
	    frame = new JFrame("QuizCard Builder");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setBounds(50, 50, 800, 1000);
	    
	    //setup menubar and menu
	    JMenuBar menuBar = new JMenuBar();
	    JMenu fileMenu = new JMenu("File");
	    
	    //  Create and add menu Items
	    newItem = new JMenuItem("New");
	    newItem.addActionListener(new NewMenuListener());
	    saveItem = new JMenuItem("Save");
	    saveItem.addActionListener(new SaveMenuListener());
	    
	    fileMenu.add(newItem);
	    fileMenu.add(saveItem);
	    menuBar.add(fileMenu);
	    frame.getContentPane().add(menuBar, BorderLayout.NORTH);
	    
	    //setup background JPanel that uses BorderLayout for future customization
	    BorderLayout layout = new BorderLayout();
	    JPanel background = new JPanel(layout);
	    // Set preferred size of background
	    background.setPreferredSize(new Dimension(500, 600));
	    // empty border to create margin between the edges of the panel and where the components are placed
	    background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    //Box with BoxLayout to hold content
	    Box contentBox = new Box(BoxLayout.Y_AXIS);
	    background.add(contentBox, BorderLayout.CENTER);
	    // add margins to the bottom, left and right side of contentBox
	    background.add(Box.createRigidArea(new Dimension(20,0)), BorderLayout.WEST);
	    background.add(Box.createRigidArea(new Dimension(20,0)), BorderLayout.EAST);
	    background.add(Box.createRigidArea(new Dimension(0,50)), BorderLayout.SOUTH);
	    
	    // add Questions label
	    JLabel qLabel = new JLabel("Question:");
	    qLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    contentBox.add(qLabel);
	    //add some blank space
	    contentBox.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    //add questions JTextArea
	    qTextArea = new JTextArea("Enter a Question", 6, 20);
	    qTextArea.setLineWrap(true);
	    qTextArea.setWrapStyleWord(true);
	    qTextArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
	    contentBox.add(qTextArea);
	    contentBox.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    //add answers label
	    JLabel aLabel = new JLabel("Answer:");
	    aLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    contentBox.add(aLabel);
	    contentBox.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    //add Answer JTextArea
	    aTextArea = new JTextArea("Write the correct answer to the question above", 6, 20);
	    aTextArea.setLineWrap(true);
	    aTextArea.setWrapStyleWord(true);
	    aTextArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
	    contentBox.add(aTextArea);
	    contentBox.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    nextBtn = new JButton("Next Card");
	    nextBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
	    nextBtn.addActionListener(new NextCardListener());
	    
	    contentBox.add(nextBtn);
	    
	    frame.getContentPane().add(background, BorderLayout.CENTER);
	    frame.pack();
	    frame.setVisible(true);
		
	}
	
	private void clearCard(boolean defaultSetting){
	    if (defaultSetting){
		qTextArea.setText("Enter a Question");
		aTextArea.setText("Write the correct answer to the question above");
	    }else{
		qTextArea.setText("");
		aTextArea.setText("");
	    }
	    qTextArea.requestFocus();
	}
	
	private class NextCardListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent event) {
		// add the current card to the list and clear the text areas
		allCards.add(new QuizCard(qTextArea.getText(), aTextArea.getText()));
		clearCard(false);
	    }
	}
	
	private class SaveMenuListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e) {
		// bring up a file dialog box
		// let the user name and save the set
		QuizCard card = new QuizCard(qTextArea.getText(), aTextArea.getText());
		allCards.add(card);
		
		// bring up a file dialog box and wait on the fileSave line until the user chooses save
		// JFileChooser handles all dialog boxes
		JFileChooser fileSave = new JFileChooser();
		fileSave.showSaveDialog(frame);
		// save the cards to the desired file
		saveFile(fileSave.getSelectedFile());	
	    }
	}
	
	private class NewMenuListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e) {
		// clear out the card list, and clear out the text areas
		allCards.clear();
		clearCard(true);
	    }
	}
	
	private void saveFile(File file){
	    // iterate through the list of cards, and write each one out to a text file
	    // in a parseable way (with clear separation between parts)
	    try{
		BufferedWriter writer = new BufferedWriter (new FileWriter(file));
		
		for (QuizCard card : allCards){
		    writer.write(card.getQuestion() + "/");
		    writer.write(card.getAnswer() + "\n");
		}
		writer.close();
	    }catch(IOException e){
		System.out.println("Could not write the card list out");
		e.printStackTrace();
	    }
	}

}
