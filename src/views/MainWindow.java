package views;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.Box.Filler;

import controllers.WordGenerator;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 693126606443270397L;
	
	private static final Rectangle PREFERRED_BOUNDS = new Rectangle(381, 215, 500, 162);
	private WordGenerator generator;
	
	private JLabel generationTimeLabel;
	private JTextArea generatedTextArea;
	private JSpinner phonemeSpinner;
	private JSpinner wordCountSpinner;
	
	public MainWindow(WordGenerator generator){
		this.generator = generator;
		this.setBounds(PREFERRED_BOUNDS);
		this.setResizable(false);
		this.setTitle("Paul Richter's Word Generator");
		
		JPanel mainPanel = createMainPanel();
		JPanel inputAreaPanel = createInputAreaPanel();
		JPanel infoAreaPanel = createInfoAreaPanel();
		JPanel controlPanel = createControlPanel();
		mainPanel.add(inputAreaPanel);
		mainPanel.add(infoAreaPanel);
		mainPanel.add(controlPanel);
		
		this.add(mainPanel);
		
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent arg0) {
				handleClosingOperations();
			}
		});
	}
	
	public void init(){
		this.setVisible(true);
		this.repaint();
	}
	
	private JPanel createMainPanel(){
		JPanel panel = new JPanel();
		return panel;
	}
	
	private JPanel createInputAreaPanel(){
		JLabel phonemeCountLabel = new JLabel("Number of Characters / Phonemes:");
		SpinnerNumberModel phonemeCountspinnerModel = new SpinnerNumberModel(5, 1, 10, 1);
		phonemeSpinner = new JSpinner(phonemeCountspinnerModel);
		
		Box.Filler filler = new Filler(new Dimension(0,0), new Dimension(20, 10), new Dimension(20, 10));
		
		JLabel wordCountLabel = new JLabel("Number of Words: ");
		SpinnerNumberModel wordCountSpinnerModel = new SpinnerNumberModel(1, 1, 5, 1);
		wordCountSpinner = new JSpinner(wordCountSpinnerModel);
		
		JPanel panel = new JPanel();
		panel.add(phonemeCountLabel);
		panel.add(phonemeSpinner);
		panel.add(filler);
		panel.add(wordCountLabel);
		panel.add(wordCountSpinner);
		return panel;
	}
	
	private JPanel createInfoAreaPanel(){
		generatedTextArea = new JTextArea();
		generatedTextArea.setRows(2);
		generatedTextArea.setColumns(25);
		generatedTextArea.setEditable(false);
		generationTimeLabel = new JLabel("Generation time will go here");
		JPanel panel = new JPanel();
		panel.add(generatedTextArea);
		panel.add(generationTimeLabel);
		return panel;
	}
	
	private JPanel createControlPanel(){
		JButton generateButton = new JButton("Generate!");
		generateButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String generatedWord = generator.generateWord((Integer)phonemeSpinner.getValue(), (Integer)wordCountSpinner.getValue());
				generatedTextArea.setText(generatedWord);
				Long generationTime = generator.generationTimeOfLastRun();
				generationTimeLabel.setText("Generation Time: " + generationTime + "ns");
			}
		});
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				handleClosingOperations();
			}
		});
		JPanel panel = new JPanel();
		panel.add(generateButton);
		panel.add(exitButton);
		return panel;
	}
	
	private void handleClosingOperations(){
		generator.handleClosingOperations();
		generationTimeLabel.setVisible(false);
		generationTimeLabel = null;
		generatedTextArea.setVisible(false);
		generatedTextArea = null;
		phonemeSpinner.setVisible(false);
		phonemeSpinner = null;
		this.setVisible(false);
		this.dispose();
		System.gc();
		System.exit(0);
	}
}