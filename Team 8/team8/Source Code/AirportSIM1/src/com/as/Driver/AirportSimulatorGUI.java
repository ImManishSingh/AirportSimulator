package com.as.Driver;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JSeparator;

public class AirportSimulatorGUI implements ActionListener {

	private JFrame frame;
	private JTextField textField;															//Number of flight 1
	private JTextField textField_1;															//Boarding time for flight 1
	private JTextField textField_2;															// Runwaytime for flight 1
	private int NF1=0,NF2=0,NF3=0,BT1=0,BT2=0,BT3=0,RT1=0,RT2=0,RT3=0,PT=0;						
	private JTextField textField_3;															//Number of flight 2
	private JTextField textField_4;															// Boarding time for flight 2
	private JTextField textField_5;															// Runway time for flight 2
	private JTextField textField_6;															//Number of flight 3
	private JTextField textField_7;															//Boarding time for flight 3
	private JTextField textField_8;															//Runwaytime for flight 3
	private JTable table;
	private JLabel lblRunning;
	private JTextField textField_9;												           	//input for peak time duration in hrs 
	private JButton btnNewButton;
	public AirportSimulatorGUI() {
		initialize();
	}
	public void initialize() {
		frame = new JFrame();																//Initializing the main frame 
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);								// setting the color
		frame.setBounds(200, 200, 650, 600);												// setting the size of input gui
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter number of flights at peak time for type 1 : ");                 //Defining label for number of flight 1
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(24, 134, 363, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter boarding time (BT1) (mins) :");											//Defining label for boarding time of flight 1
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(24, 177, 229, 32);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblEnterRunwaytimert = new JLabel("Enter runwaytime (RT1) (mins) :");										//Defining label runway time of flight 1
		lblEnterRunwaytimert.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEnterRunwaytimert.setBounds(349, 181, 216, 24);
		frame.getContentPane().add(lblEnterRunwaytimert);
		
		btnNewButton = new JButton("Submit");																// Defining submit button
		btnNewButton.setBackground(UIManager.getColor("MenuItem.selectionBackground"));
		
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(252, 494, 113, 32);
		frame.getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(this);																		// adding functionality for button
		
		textField = new JTextField();																				// taking input for number of flight 1 from user
		textField.setBounds(373, 140, 49, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();																				// taking input for boarding time for flight 1
		textField_1.setColumns(10);
		textField_1.setBounds(272, 183, 49, 24);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();																				// taking input for runway time for flight 1
		textField_2.setColumns(10);
		textField_2.setBounds(575, 183, 49, 24);
		frame.getContentPane().add(textField_2);
		
		JLabel lblEnterNumberOf = new JLabel("Enter number of flights at peak time for type 2 : ");				// label for number of flights 2
		lblEnterNumberOf.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEnterNumberOf.setBounds(24, 258, 363, 32);
		frame.getContentPane().add(lblEnterNumberOf);
		
		textField_3 = new JTextField();                																// taking input for number of  flight 2	
		textField_3.setColumns(10);
		textField_3.setBounds(373, 264, 49, 24);
		frame.getContentPane().add(textField_3);
		
		JLabel lblEnterBoardingTime = new JLabel("Enter boarding time (BT2) (mins) :");									// label for boarding time  of flights 2
		lblEnterBoardingTime.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEnterBoardingTime.setBounds(24, 301, 229, 32);
		frame.getContentPane().add(lblEnterBoardingTime);
		
		textField_4 = new JTextField();               																// taking input for boarding time of  flight 2	
		textField_4.setColumns(10);
		textField_4.setBounds(272, 307, 49, 24);
		frame.getContentPane().add(textField_4);
		
		JLabel lblEnterRunwaytimert_1 = new JLabel("Enter runwaytime (RT2) (mins) :");                                   	// label for for runway time of flight 2	
		lblEnterRunwaytimert_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEnterRunwaytimert_1.setBounds(349, 305, 216, 24);
		frame.getContentPane().add(lblEnterRunwaytimert_1);
		
		textField_5 = new JTextField();																    			// taking input for runway time of  flight 2	
		textField_5.setColumns(10);
		textField_5.setBounds(575, 307, 49, 24);
		frame.getContentPane().add(textField_5);
		
		JLabel lblEnterNumberOf_1 = new JLabel("Enter number of flights at peak time for type 3: ");				// label for number of flights 3
		lblEnterNumberOf_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEnterNumberOf_1.setBounds(24, 373, 363, 32);
		frame.getContentPane().add(lblEnterNumberOf_1);
		
		textField_6 = new JTextField();					   																// taking input for number of  flight 3
		textField_6.setColumns(10);
		textField_6.setBounds(373, 379, 49, 24);
		frame.getContentPane().add(textField_6);
		
		JLabel lblEnterBoardingTime_1 = new JLabel("Enter boarding time (BT3) (mins) :");									// label for boarding time  of flights 3
		lblEnterBoardingTime_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEnterBoardingTime_1.setBounds(24, 416, 229, 32);
		frame.getContentPane().add(lblEnterBoardingTime_1);
		
		textField_7 = new JTextField();               																// taking input for boarding time of  flight 3
		textField_7.setColumns(10);
		textField_7.setBounds(272, 422, 49, 24);
		frame.getContentPane().add(textField_7);
		
		JLabel lblEnterRunwaytimert_2 = new JLabel("Enter runwaytime (RT3) (mins):");                                   	// label for for runway time of flight 3
		lblEnterRunwaytimert_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEnterRunwaytimert_2.setBounds(349, 420, 216, 24);
		frame.getContentPane().add(lblEnterRunwaytimert_2);
		
		textField_8 = new JTextField();															    		  		// taking input for runway time of  flight 3
		textField_8.setColumns(10);
		textField_8.setBounds(575, 422, 49, 24);
		frame.getContentPane().add(textField_8);
		
		table = new JTable();
		table.setBounds(206, 144, 1, 1);
		frame.getContentPane().add(table);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setBounds(0, 236, 634, 1);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();																//seperator for flight 1 input and flight 2 input 
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(0, 361, 634, 1);
		frame.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();															//seperator for flight 2 input and flight 3 input 
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.LIGHT_GRAY);
		separator_2.setBounds(0, 46, 634, -1);
		frame.getContentPane().add(separator_2);
		
		JLabel lblAirportSimulator = new JLabel("AIRPORT SIMULATOR");
		lblAirportSimulator.setBackground(UIManager.getColor("RadioButtonMenuItem.selectionBackground"));
		lblAirportSimulator.setForeground(Color.BLACK);
		lblAirportSimulator.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblAirportSimulator.setBounds(143, 11, 348, 24);
		frame.getContentPane().add(lblAirportSimulator);
		
		JLabel lblEnterTotalDuration = new JLabel("Enter total duration of peak time  (hrs):");
		lblEnterTotalDuration.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEnterTotalDuration.setBounds(26, 74, 271, 24);
		frame.getContentPane().add(lblEnterTotalDuration);
		
		textField_9 = new JTextField();
		textField_9.setBounds(307, 76, 49, 24);
		frame.getContentPane().add(textField_9);
		textField_9.setColumns(10);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		separator_3.setBackground(Color.LIGHT_GRAY);
		separator_3.setBounds(0, 122, 634, 1);
		frame.getContentPane().add(separator_3);
		
		lblRunning = new JLabel("Running...");
		lblRunning.setForeground(Color.BLUE);
		lblRunning.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblRunning.setBounds(272, 494, 113, 32);
		
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int NF1=0,NF2=0,NF3=0,BT1=0,BT2=0,BT3=0,RT1=0,RT2=0,RT3=0,PT;
		try {
				NF1=Integer.parseInt(textField.getText());										// converting text input from user into interger value
				BT1=Integer.parseInt(textField_1.getText());
				RT1=Integer.parseInt(textField_2.getText());
				NF2=Integer.parseInt(textField_3.getText());
				BT2=Integer.parseInt(textField_4.getText());
				RT2=Integer.parseInt(textField_5.getText());
				NF3=Integer.parseInt(textField_6.getText());
				BT3=Integer.parseInt(textField_7.getText());
				RT3=Integer.parseInt(textField_8.getText());
				PT=Integer.parseInt(textField_9.getText());
				if(NF1>0&&NF2>0&&NF3>0&&BT1>0&&BT2>0&&BT3>0&&RT1>0&&RT2>0&&RT3>0&&PT>0)
					;
				else
					throw new Exception("");			
			setValues(NF1,NF2,NF3,BT1,BT2,BT3,RT1,RT2,RT3,PT);
			synchronized(AirportSimulatorGUI.this)                                            // wait until input is not received 
			{
				this.notify();
			}
			
		}
		catch(Exception e1)
		{
				JOptionPane.showMessageDialog(null, "Enter Valid Positive Integer");      // if any of the entered number is not positive integer then error message is displayed
		}	
		frame.getContentPane().add(lblRunning);
		
		//btnNewButton.disable();
		frame.getContentPane().remove(btnNewButton);
		frame.repaint();
	}
	public void dispose() {																// for disposing input frame
		frame.dispose();
	}
	public void setValues(int NF1,int NF2,int NF3,int BT1,int BT2,int BT3,int RT1,int RT2,int RT3,int PT) {
		this.NF1=NF1;																//set value method 
		this.NF2=NF2;
		this.NF3=NF3;
		this.BT1=BT1;
		this.BT2=BT2;
		this.BT3=BT3;
		this.RT1=RT1;
		this.RT2=RT2;
		this.RT3=RT3;
		this.PT=PT*60;
	}
	public int getNF1()														// returning the values to driver(main) class
	{
		return NF1;
	}
	public int getNF2()
	{
		return NF2;
	}
	public int getNF3()
	{
		return NF3;
	}
	public int getBT1()
	{
		return BT1;
	}
	public int getBT2()
	{
		return BT2;
	}
	public int getBT3()
	{
		return BT3;
	}
	public int getRT1()
	{
		return RT1;
	}
	public int getRT2()
	{
		return RT2;
	}
	public int getRT3()
	{
		return RT3;
	}
	public int getPT()
	{
		return PT;
	}
}
