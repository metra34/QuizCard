import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class QuizCardPlayer extends QuizCardGui{
    
    private JMenuItem loadMenuItem;
    private JTextArea qTextArea;
    private JTextArea aTextArea;
    private ArrayList<QuizCard> allCards;
    // flag to determine state of cardPlayer
    private boolean isAnswerShown;
    
    public static void main(String[] args) {
	QuizCardPlayer ac = new QuizCardPlayer();
    }
    
    public QuizCardPlayer(){
	// setup superClass
	super();
	// Initialize variables
	qTextArea = getQuestion();
	aTextArea = getAnswer();
	// true until first flag call in initialize
	isAnswerShown = false;
	allCards = new ArrayList<QuizCard>();
	// build GUI
	initialize();
    }
    
    /**
     * @wbp.parser.entryPoint
     **/
    public void initialize(){
	// build and display GUI
	//setup frame
	getFrame().setTitle("QuizCard Player");
	
	//  Create and add menu Item
	loadMenuItem = new JMenuItem("Load");
	loadMenuItem.addActionListener(new OpenMenuListener());
	getMenu().add(loadMenuItem);
	
	//add listener to next button to show / hide answer and then move on to next question
	getNextButton().addActionListener(new NextCardListener());
	getNextButton().setText("Show Answer");
	
	// disable user input to JTextAreas
	qTextArea.setEditable(false);
	aTextArea.setEditable(false);
	aTextArea.setVisible(false);
	
	JFrame frame = getFrame();
	frame.pack();
	frame.setVisible(true);
	
    }
    
    private void checkNexButtontFlag(){
	if (isAnswerShown){
	    // answer is shown, so hide answer, change button text and fetch next question
	    aTextArea.setVisible(false);
	    isAnswerShown = false;
	    getNextButton().setText("Show Answer");
	}else{
	    // answer not shown, show answer, change button text
	    aTextArea.setVisible(true);
	    isAnswerShown = true;
	    getNextButton().setText("Next Question");
	}
    }
    
    private class NextCardListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO
	    // if this is a question, show the answer, otherwise show next question
	    // set a flag for whether its a question or answer
	    
	    checkNexButtontFlag();
	    
	}
    }
    
    private class OpenMenuListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
	    // TODO
	    // bring up a file dialog box
	    // let the user navigate to and choose a card set to open
	    
	}
    }
    
    private void loadFile(File file){
	// TODO
	// must build an arraylist of cards, by reading them from a text file
	// called from the OpenMenuListener event handler, reads the file one line at a time
	// and tells the makeCard() method to make a new card out of the line
	// one line in the file holds both the question and answer, separated by a "|/|"
    }
    
    private void makeCard(String lineToParse){
	//TODO
	// called by the loadFile method takes a line from the text file
	// and parses into two pieces - question and answer - and creates a new QuizCard
	// and adds it to the ArrayList called CardList
    }
}