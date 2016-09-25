import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;

public class QuizCardBuilder extends QuizCardGui{
    private JMenuItem saveItem;
    private JMenuItem newItem;
    private JTextArea qTextArea;
    private JTextArea aTextArea;
    private ArrayList<QuizCard> allCards;

    public QuizCardBuilder() {
	super();
	qTextArea = getQuestion();
	aTextArea = getAnswer();
	initialize();
	allCards = new ArrayList<QuizCard>();
    }

    /**
     * @wbp.parser.entryPoint
     */
    public void initialize() {
	// build and display GUI
	getFrame().setTitle("QuizCard Builder");
	// Create and add menu Items
	newItem = new JMenuItem("New");
	newItem.addActionListener(new NewMenuListener());
	saveItem = new JMenuItem("Save");
	saveItem.addActionListener(new SaveMenuListener());

	JMenu fileMenu = getMenu();
	fileMenu.add(newItem);
	fileMenu.add(saveItem);

	// Set Default Question and Answer Text
	qTextArea.setText("Enter a Question");
	aTextArea.setText("Write the correct answer to the question above");

	getNextButton().addActionListener(new NextCardListener());

	JFrame frame = getFrame();
	frame.pack();
	frame.setVisible(true);
    }

    private void clearCard(boolean defaultSetting) {
	if (defaultSetting) {
	    qTextArea.setText("Enter a Question");
	    aTextArea.setText("Write the correct answer to the question above");
	} else {
	    qTextArea.setText("");
	    aTextArea.setText("");
	}
	qTextArea.requestFocus();
    }

    private class NextCardListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent event) {
	    // add the current card to the list and clear the text areas
	    allCards.add(new QuizCard(qTextArea.getText(), aTextArea.getText()));
	    clearCard(false);
	}
    }

    private class SaveMenuListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    // bring up a file dialog box
	    // let the user name and save the set
	    QuizCard card = new QuizCard(qTextArea.getText(), aTextArea.getText());
	    allCards.add(card);

	    // bring up a file dialog box and wait on the fileSave line until
	    // the user chooses save
	    // JFileChooser handles all dialog boxes
	    JFileChooser fileSave = new JFileChooser();
	    fileSave.showSaveDialog(getFrame());
	    // save the cards to the desired file
	    saveFile(fileSave.getSelectedFile());
	}
    }

    private class NewMenuListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    // clear out the card list, and clear out the text areas
	    allCards.clear();
	    clearCard(true);
	}
    }

    private void saveFile(File file) {
	// iterate through the list of cards, and write each one out to a text
	// file
	// in a parseable way (with clear separation between parts)
	try {
	    BufferedWriter writer = new BufferedWriter(new FileWriter(file));

	    for (QuizCard card : allCards) {
		writer.write(card.getQuestion() + "\t\t\t/\t\t\t");
		writer.write(card.getAnswer() + "\n");
	    }
	    writer.close();
	} catch (IOException e) {
	    System.out.println("Could not write the card list out");
	    e.printStackTrace();
	}
    }
}
