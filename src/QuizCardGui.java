import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public abstract class QuizCardGui {
    private JFrame frame;
    private JTextArea qTextArea;
    private JTextArea aTextArea;
    private JMenu fileMenu;
    private JButton nextBtn;
    
    public QuizCardGui() {
	initialize ();
    }
    
    /**
     * * @wbp.parser.entryPoint
    */
    private void initialize() {
	// Creates the Base GUI containing two text areas and an empty menu item and a
	// nextBtn with no listener
	// subclasses need to implement their own button listeners and add features that make the
	// windows unique
	// each subclass must call frame.pack() and frame.setVisible(true) at the end of initialize
	//setup frame
	frame = new JFrame("");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setBounds(50, 50, 800, 1000);
	    
	//setup menubar and menu
	JMenuBar menuBar = new JMenuBar();
	fileMenu = new JMenu("File");
	menuBar.add(fileMenu);
	frame.getContentPane().add(menuBar, BorderLayout.NORTH);

	// setup background JPanel that uses BorderLayout for future
	// customization
	BorderLayout layout = new BorderLayout();
	JPanel background = new JPanel(layout);
	// Set preferred size of background
	background.setPreferredSize(new Dimension(500, 600));
	// empty border to create margin between the edges of the panel and
	// where the components are placed
	background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	// Box with BoxLayout to hold content
	Box contentBox = new Box(BoxLayout.Y_AXIS);
	background.add(contentBox, BorderLayout.CENTER);
	// add margins to the bottom, left and right side of contentBox
	background.add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.WEST);
	background.add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.EAST);
	background.add(Box.createRigidArea(new Dimension(0, 50)), BorderLayout.SOUTH);

	// add Questions label
	JLabel qLabel = new JLabel("Question:");
	qLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	contentBox.add(qLabel);
	// add some blank space
	contentBox.add(Box.createRigidArea(new Dimension(0, 5)));

	// add questions JTextArea
	qTextArea = new JTextArea(6, 20);
	qTextArea.setLineWrap(true);
	qTextArea.setWrapStyleWord(true);
	qTextArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
	contentBox.add(qTextArea);
	contentBox.add(Box.createRigidArea(new Dimension(0, 5)));

	// add answers label
	JLabel aLabel = new JLabel("Answer:");
	aLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	contentBox.add(aLabel);
	contentBox.add(Box.createRigidArea(new Dimension(0, 5)));

	// add Answer JTextArea
	aTextArea = new JTextArea(6, 20);
	aTextArea.setLineWrap(true);
	aTextArea.setWrapStyleWord(true);
	aTextArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
	contentBox.add(aTextArea);
	contentBox.add(Box.createRigidArea(new Dimension(0, 5)));

	nextBtn = new JButton("Next Card");
	nextBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

	contentBox.add(nextBtn);

	frame.getContentPane().add(background, BorderLayout.CENTER);
    }
    
    private abstract class NextCardListener implements ActionListener{
	// this class is used to implement an action on the next button
    }
    
    public JFrame getFrame(){
	return this.frame;
    }
    
    public JTextArea getQuestion(){
	return this.qTextArea;
    }
    
    public JTextArea getAnswer(){
	return this.aTextArea;
    }
    
    public JMenu getMenu(){
	return this.fileMenu;
    }
    
    public JButton getNextButton(){
	return this.nextBtn;
    }
}
