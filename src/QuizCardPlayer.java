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
    private QuizCard currCard;
    // flag to determine state of cardPlayer
    private boolean isAnswerShown;
    
    public static void main(String[] args) {
	QuizCardBuilder bc = new QuizCardBuilder();
	QuizCardPlayer ac = new QuizCardPlayer();
    }
    
    public QuizCardPlayer(){
	// setup superClass
	super();
	// Initialize variables
	qTextArea = getQuestion();
	aTextArea = getAnswer();
	allCards = new ArrayList<QuizCard>();
	// true until first flag call in initialize
	isAnswerShown = false;
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
	getFrame().setBounds(550, 50, 800, 1000);
	
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
    
    private void setAndShowCard(){
	if (allCards.size() > 0){
	    currCard = allCards.get(0);
	    allCards.remove(0);
	    qTextArea.setText(currCard.getQuestion());
	    aTextArea.setText(currCard.getAnswer());
	}else{
	    qTextArea.setText("All Questions finished");
	    aTextArea.setText("");
	}
    }
    
    private void checkNexButtontFlag(){
	if (isAnswerShown){
	    // answer is shown, so hide answer, change button text and fetch next question
	    aTextArea.setVisible(false);
	    isAnswerShown = false;
	    setAndShowCard();
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
	    // if this is a question, show the answer, otherwise show next question
	    // set a flag for whether its a question or answer
	    
	    checkNexButtontFlag();
	}
    }
    
    private class OpenMenuListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
	    // bring up a file dialog box
	    // let the user navigate to and choose a card set to open
	    
	    JFileChooser fileOpen = new JFileChooser();
	    fileOpen.showOpenDialog(getFrame());
	    loadFile (fileOpen.getSelectedFile());
	    // set the question after loading a file
	    isAnswerShown = true;
	    checkNexButtontFlag();
	}
    }
    
    private void loadFile(File file){
	// must build an arraylist of cards, by reading them from a text file
	// called from the OpenMenuListener event handler, reads the file one line at a time
	// and tells the makeCard() method to make a new card out of the line
	// one line in the file holds both the question and answer, separated by a "|/|"
	allCards = new ArrayList<QuizCard>();
	
	// setUp BufferReader with fileinputStream
	try {
	    BufferedReader reader = new BufferedReader(new FileReader(file));
	    
	    String line = null;
	    while ((line = reader.readLine()) != null){
		makeCard(line);
	    }
	    reader.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    private void makeCard(String lineToParse){
	// called by the loadFile method takes a line from the text file
	// and parses into two pieces - question and answer - and creates a new QuizCard
	// and adds it to the ArrayList called CardList
	String[] args = lineToParse.split("\t\t\t/\t\t\t");
	if (args.length > 1){
	    // check that args are not null and there is a value for a question and answer
	    if (args[0].length() > 0 && args[1].length() > 0){
		allCards.add(new QuizCard(args[0], args[1]));
	    }
	}
    }
}