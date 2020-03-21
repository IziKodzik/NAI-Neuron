package main;

import javax.swing.*;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI
	extends JFrame {

	JTextField[] textFields;


	public GUI(int size,Neuron neuron){

		super("GUI");
		setLayout(new FlowLayout());
		this.getContentPane().setBackground(Color.GRAY);

		textFields = new JTextField[size];
		for(int op = 0 ; op < size ; ++op){

			textFields[op] = new JTextField(10);
			textFields[op].setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(textFields[op]);
		}

		JLabel labelResult = new JLabel("RESULT");
		labelResult.setBackground(Color.white);

		JButton buttonGuess = new JButton("GUESS");
		buttonGuess.setMargin(new Insets(5,10,5,10));
		buttonGuess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				java.util.List<Double> properties = new ArrayList<>();

				boolean	goForward = true;

				for (int op = 0; op < textFields.length; ++op) {

					try {
						properties.add(Double.valueOf(textFields[op].getText()));
						textFields[op].setBackground(new Color(20, 200, 60));
					} catch (NumberFormatException ex) {
						goForward=false;
						textFields[op].setBackground(new Color(222, 40, 40));
					}


				}

				if(goForward) {

					Flower flower = new Flower(new ArrayList<>(properties));
					properties.clear();

					flower.setNameCode(neuron.testObject(flower.getInputs()));
					flower.setName(Flower.nameByCode(flower.getNameCode()));
					labelResult.setText(flower.getName());

				}else{
					labelResult.setText("RESULT");
					System.out.println("Wrong input. Use . to separate digits.");
				}

				pack();

			}
		});

		getContentPane().add(buttonGuess);
		getContentPane().add(labelResult);



		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		pack();



	}

}
