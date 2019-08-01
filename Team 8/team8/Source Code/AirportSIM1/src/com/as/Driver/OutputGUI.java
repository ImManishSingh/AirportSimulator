package com.as.Driver;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class OutputGUI {

	private JFrame frame;
	private int No_Gates=0,No_Emergency=0,No_Runways=0;											// initialing value which are to be displayed

	

	/**
	 * Create the application.
	 */
	public OutputGUI(int No_Gates,int No_Emergency,int No_Runways) {
		this.No_Gates=No_Gates;																//set value method 
		this.No_Emergency=No_Emergency;
		this.No_Runways=No_Runways;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//int no_gates=0;
		frame = new JFrame();																			//defining size of output frame 
		frame.setBounds(100, 100, 550, 145);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblOptimalNumberOf = new JLabel("Optimal number of gates airport should have :");  // label  for optimal number of gates to be dispalyed 
		lblOptimalNumberOf.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblOptimalNumberOf.setBounds(10, 11, 361, 22);
		frame.getContentPane().add(lblOptimalNumberOf);
		
		JLabel lblNumberOfEmergency = new JLabel("Number of emergency landing airport can handle :"); // label  for optimal number of emergency landing to be dispalyed
		lblNumberOfEmergency.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNumberOfEmergency.setBounds(10, 57, 414, 22);
		frame.getContentPane().add(lblNumberOfEmergency);
		
		JLabel gates = new JLabel("");																// output number of gates to be displayed
		gates.setFont(new Font("Times New Roman", Font.BOLD, 18));
		gates.setBounds(402, 11, 88, 28);
		frame.getContentPane().add(gates);
		gates.setText(this.No_Gates+"");
		
		JLabel emergency = new JLabel("");															// output number of emergency landing to be displayed 
		emergency.setFont(new Font("Times New Roman", Font.BOLD, 18));
		emergency.setBounds(412, 54, 112, 28);
		frame.getContentPane().add(emergency);
		emergency.setText(this.No_Emergency+"");
		
		
		frame.setVisible(true);
	}

}
