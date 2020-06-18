  
package Calculators;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	EvaluateString  Evaluate= new EvaluateString();
	EvaluateProgString EvaluateProg = new EvaluateProgString();
	
	
	double result;
	int ShiftMode=1;
	int mark=0;
	int mode = 10;
	int TrigonometryMode = 0;
	int InvRegInd = 0;
	String operator ;
	
	private JPanel contentPane;
	private JTextField CalDisplayInput;
	private JTextField CalDisplayResult;
	private JTextField HEXtextField;
	private JTextField DECtextField;
	private JTextField OCTtextField;
	private JTextField BINtextField;
	//Basic Number Button
	private JButton btnZeroButton;
	private JButton btnButtonOne;
	private JButton btnButtonTwo;
	private JButton btnButtonThree;
	private JButton btnButtonFour;
	private JButton btnButtonFive;
	private JButton btnButtonSix;
	private JButton btnButtonSeven;
	private JButton btnButtonEight;
	private JButton btnButtonNine;
	private JButton btnPointButton;
	private JButton btnOpeningParenthesis;
	private JButton btnClosingParenthesis;
	//Basic Operator Button
	private JButton btnPlusButton;
	private JButton btnMinusButton;
	private JButton btnMultiplyButton;
	private JButton btnDivideButton;
	private JButton btnEqualButton;
	//Programmer Operator Button
	private JButton btnModButton;
	private JButton btnLeftShiftButton;
	private JButton btnRightShiftButton;
	private JButton btnANDButton;
	private JButton btnNOTButton;
	private JButton btnORButton;
	private JButton btnNANDButton;
	private JButton btnNORButton;
	private JButton btnXORButton;
	//Hex button
	private JButton btnHexAButton;
	private JButton btnHexBButton;
	private JButton btnHexCButton;
	private JButton btnHexDButton;
	private JButton btnHexEButton;
	private JButton btnHexFButton;
	//Clear and Clear Entry Button
	private JButton btnClearEntryButton;
	private JButton btnClearButton;
	private JButton btnDeleteButton;
	//Converter ComboBox
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Refresh content pane before moving to new calculator mode
	public void RefreshCal() 
	{
		contentPane.removeAll();
		operator = "/";
		mark=0;
		mode = 10;
	}
	public void NumberButtonPressed(JButton Button) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String iNum;
				if(mark==1) iNum = Button.getText();
				else iNum = CalDisplayInput.getText() + Button.getText();
				CalDisplayInput.setText(iNum);
				mark = 0;
			}
		});
	}
	
	public void NumbModeSelector(int numbmode) 
	{
		
		btnZeroButton.setEnabled(true);
		btnButtonOne.setEnabled(true);
		switch(numbmode)
		{
			case 1:	btnHexAButton.setEnabled(false);
					btnHexBButton.setEnabled(false);
					btnHexCButton.setEnabled(false);
					btnHexDButton.setEnabled(false);
					btnHexEButton.setEnabled(false);
					btnHexFButton.setEnabled(false);
					btnButtonNine.setEnabled(false);
					btnButtonEight.setEnabled(false);
					btnButtonSeven.setEnabled(false);
					btnButtonSix.setEnabled(false);
					btnButtonFive.setEnabled(false);
					btnButtonFour.setEnabled(false);
					btnButtonThree.setEnabled(false);
					btnButtonTwo.setEnabled(false);
					mode = 2 ;break;
					
			case 2:	btnHexAButton.setEnabled(false);
					btnHexBButton.setEnabled(false);
					btnHexCButton.setEnabled(false);
					btnHexDButton.setEnabled(false);
					btnHexEButton.setEnabled(false);
					btnHexFButton.setEnabled(false);
					btnButtonNine.setEnabled(false);
					btnButtonEight.setEnabled(false);
					btnButtonSeven.setEnabled(true);
					btnButtonSix.setEnabled(true);
					btnButtonFive.setEnabled(true);
					btnButtonFour.setEnabled(true);
					btnButtonThree.setEnabled(true);
					btnButtonTwo.setEnabled(true);
					mode = 8 ;break;
					
			case 3:	btnHexAButton.setEnabled(false);
					btnHexBButton.setEnabled(false);
					btnHexCButton.setEnabled(false);
					btnHexDButton.setEnabled(false);
					btnHexEButton.setEnabled(false);
					btnHexFButton.setEnabled(false);
					btnButtonNine.setEnabled(true);
					btnButtonEight.setEnabled(true);
					btnButtonSeven.setEnabled(true);
					btnButtonSix.setEnabled(true);
					btnButtonFive.setEnabled(true);
					btnButtonFour.setEnabled(true);
					btnButtonThree.setEnabled(true);
					btnButtonTwo.setEnabled(true);
					mode = 10 ;break;
					
			case 4: btnHexAButton.setEnabled(true);
					btnHexBButton.setEnabled(true);
					btnHexCButton.setEnabled(true);
					btnHexDButton.setEnabled(true);
					btnHexEButton.setEnabled(true);
					btnHexFButton.setEnabled(true);
					btnButtonNine.setEnabled(true);
					btnButtonEight.setEnabled(true);
					btnButtonSeven.setEnabled(true);
					btnButtonSix.setEnabled(true);
					btnButtonFive.setEnabled(true);
					btnButtonFour.setEnabled(true);
					btnButtonThree.setEnabled(true);
					btnButtonTwo.setEnabled(true);
					mode = 16 ;break;
		}
	}
	//Create Display text field to display result and formula
	public void CalDisplayEngage() 
	{
		CalDisplayInput = new JTextField();
		CalDisplayInput.setText("0");
		CalDisplayInput.setHorizontalAlignment(SwingConstants.RIGHT);
		CalDisplayInput.setBackground(UIManager.getColor("Button.background"));
		contentPane.add(CalDisplayInput);
		CalDisplayInput.setColumns(10);
		
		CalDisplayResult = new JTextField();
		CalDisplayResult.setText("0");
		CalDisplayResult.setHorizontalAlignment(SwingConstants.RIGHT);
		CalDisplayResult.setBackground(UIManager.getColor("Button.background"));
		contentPane.add(CalDisplayResult);
		CalDisplayResult.setColumns(10);
		
	}
	//Shunting_yard_algorithm

	//Create basic number button to be use and reuse in other modes of the calculator
	public void BasicNumberButtonInitiate() 
	{	
		btnZeroButton = new JButton("\u0030");
		NumberButtonPressed(btnZeroButton);
		contentPane.add(btnZeroButton);
		
		btnButtonOne = new JButton("\u0031");
		NumberButtonPressed(btnButtonOne);
		contentPane.add(btnButtonOne);
		
		btnButtonTwo = new JButton("\u0032");
		NumberButtonPressed(btnButtonTwo);
		contentPane.add(btnButtonTwo);
		
		btnButtonThree = new JButton("\u0033");
		NumberButtonPressed(btnButtonThree);
		contentPane.add(btnButtonThree);

		btnButtonFour = new JButton("\u0034");
		NumberButtonPressed(btnButtonFour);
		contentPane.add(btnButtonFour);
		
		btnButtonFive = new JButton("\u0035");
		NumberButtonPressed(btnButtonFive);
		contentPane.add(btnButtonFive);
		
		btnButtonSix = new JButton("\u0036");
		NumberButtonPressed(btnButtonSix);
		contentPane.add(btnButtonSix);
		
		btnButtonSeven = new JButton("\u0037");
		NumberButtonPressed(btnButtonSeven);
		contentPane.add(btnButtonSeven);
		
		btnButtonEight = new JButton("\u0038");
		NumberButtonPressed(btnButtonEight);
		contentPane.add(btnButtonEight);
		
		btnButtonNine = new JButton("\u0039");
		NumberButtonPressed(btnButtonNine);
		contentPane.add(btnButtonNine);
		
		btnOpeningParenthesis = new JButton("\u0028");
		NumberButtonPressed(btnOpeningParenthesis);
		contentPane.add(btnOpeningParenthesis);
		
		btnClosingParenthesis = new JButton("\u0029");
		NumberButtonPressed(btnClosingParenthesis);
		contentPane.add(btnClosingParenthesis);
		

	} 
	//Create clear, clear entry and delete button to be use and reuse in other mode 
	//of the calculator
	public void Clear_DeleteButton() 
	{
		btnClearEntryButton = new JButton("CE");
		btnClearEntryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalDisplayResult.setText("0");
			}
		});
		contentPane.add(btnClearEntryButton);
		
		btnClearButton = new JButton("C");
		btnClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalDisplayInput.setText("");
				CalDisplayResult.setText("0");
				result = 0;
			}
		});
		contentPane.add(btnClearButton);
		
		btnDeleteButton = new JButton("\u232b");
		btnDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bsp = null;
				if(CalDisplayResult.getText().length()>0) {
					StringBuilder strB = new StringBuilder(CalDisplayResult.getText());
					strB.deleteCharAt(CalDisplayResult.getText().length() - 1);
					bsp = strB.toString();
					CalDisplayResult.setText(bsp);
				}
			}
		});
		contentPane.add(btnDeleteButton);
	}
	
	public void Clear_DeleteButton_Prog() 
	{
		btnClearEntryButton = new JButton("CE");
		btnClearEntryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalDisplayResult.setText("0");
				HEXtextField.setText("0");
				DECtextField.setText("0");
				OCTtextField.setText("0");
				BINtextField.setText("0");
			}
		});
		contentPane.add(btnClearEntryButton);
		
		btnClearButton = new JButton("C");
		btnClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalDisplayInput.setText("");
				CalDisplayResult.setText("0");
				HEXtextField.setText("0");
				DECtextField.setText("0");
				OCTtextField.setText("0");
				BINtextField.setText("0");
				result = 0;
			}
		});
		contentPane.add(btnClearButton);
		
		btnDeleteButton = new JButton("\u232b");
		btnDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bsp = null;
				if(CalDisplayResult.getText().length()>0) {
					StringBuilder strB = new StringBuilder(CalDisplayResult.getText());
					strB.deleteCharAt(CalDisplayResult.getText().length() - 1);
					bsp = strB.toString();
					CalDisplayResult.setText(bsp);
				}
			}
		});
		contentPane.add(btnDeleteButton);
	}
	//Create basic operator button to be use and reuse in other modes of the calculator
	public void BasicOperatorButtonInitiate() 
	{
		btnPlusButton = new JButton("\u002B");
		OperatorButtonPressed(btnPlusButton,0);
		contentPane.add(btnPlusButton);
		
		btnMinusButton = new JButton("\u002D");
		OperatorButtonPressed(btnMinusButton,1);
		contentPane.add(btnMinusButton);
		
		btnMultiplyButton = new JButton("\u00d7");
		OperatorButtonPressed(btnMultiplyButton,2);
		contentPane.add(btnMultiplyButton);

		btnDivideButton = new JButton("\u00f7");
		OperatorButtonPressed(btnDivideButton,3);
		contentPane.add(btnDivideButton);
		
		btnEqualButton = new JButton("\u003D");
		EqualButtonPressed(btnEqualButton);
		contentPane.add(btnEqualButton);
	}
	
	public void programmerOperatorButtonInitiate() 
	{
		btnPlusButton = new JButton("\u002B");
		BitwiseOperatorButtonPressed(btnPlusButton,8);
		contentPane.add(btnPlusButton);
		
		btnMinusButton = new JButton("\u002D");
		BitwiseOperatorButtonPressed(btnMinusButton,9);
		contentPane.add(btnMinusButton);
		
		btnMultiplyButton = new JButton("\u00d7");
		BitwiseOperatorButtonPressed(btnMultiplyButton,10);
		contentPane.add(btnMultiplyButton);

		btnDivideButton = new JButton("\u00f7");
		OperatorButtonPressed(btnDivideButton,11);
		contentPane.add(btnDivideButton);
		
		btnEqualButton = new JButton("\u003D");
		EqualBitwiseButtonPressed(btnEqualButton);
		contentPane.add(btnEqualButton);
		
		btnModButton = new JButton("\u0025");
		BitwiseOperatorButtonPressed(btnModButton,12);
		contentPane.add(btnModButton);
		
		btnLeftShiftButton = new JButton("<<");
		BitwiseOperatorButtonPressed(btnLeftShiftButton,6);
		contentPane.add(btnLeftShiftButton);
		
		btnRightShiftButton = new JButton(">>");
		BitwiseOperatorButtonPressed(btnRightShiftButton,7);
		contentPane.add(btnRightShiftButton);
		
		btnANDButton = new JButton("AND");
		BitwiseOperatorButtonPressed(btnANDButton,1);
		contentPane.add(btnANDButton);;
		
		btnORButton = new JButton("OR");
		BitwiseOperatorButtonPressed(btnORButton,2);
		contentPane.add(btnORButton);

		btnNOTButton = new JButton("NOT");
		BitwiseOperatorButtonPressed(btnNOTButton,13);
		contentPane.add(btnNOTButton);
		
		btnNANDButton = new JButton("NAND");
		BitwiseOperatorButtonPressed(btnNANDButton,3);
		contentPane.add(btnNANDButton);
		
		btnNORButton = new JButton("NOR");
		BitwiseOperatorButtonPressed(btnNORButton,4);
		contentPane.add(btnNORButton);
		
		btnXORButton = new JButton("XOR");
		BitwiseOperatorButtonPressed(btnXORButton,5);
		contentPane.add(btnXORButton);
	
		btnHexAButton = new JButton("A");
		NumberButtonPressed(btnHexAButton);
		contentPane.add(btnHexAButton);

		btnHexBButton = new JButton("B");
		NumberButtonPressed(btnHexBButton);
		contentPane.add(btnHexBButton);
		
		btnHexDButton = new JButton("D");
		NumberButtonPressed(btnHexDButton);
		contentPane.add(btnHexDButton);
		
		btnHexCButton = new JButton("C");
		NumberButtonPressed(btnHexCButton);
		contentPane.add(btnHexCButton);
		
		btnHexEButton = new JButton("E");
		NumberButtonPressed(btnHexEButton);
		contentPane.add(btnHexEButton);
		
		btnHexFButton = new JButton("F");
		NumberButtonPressed(btnHexFButton);
		contentPane.add(btnHexFButton);
	}
	
	public void EulerNumber(JButton Button, int exptype) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalDisplayInput.setText(CalDisplayInput.getText() +"e");
			}
		});
	}
	
	public void GetPercentage(JButton Button) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalDisplayInput.setText(CalDisplayInput.getText() +"%");
			}
		});
	}
	
	public void GetReciprocal(JButton Button) 
	{
	}
	
	public void GetFactorial(JButton Button) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalDisplayInput.setText(CalDisplayInput.getText() +"!");
			}
		});
	}
	
	public void BitwiseOperatorButtonPressed(JButton Button, int OpType) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(OpType) 
				{
				case 1: operator ="and";break;
				case 2: operator = "or";break;
				case 3: operator ="nand";break;
				case 4: operator ="nor";break;
				case 5: operator ="xor";break;
				case 6: switch(ShiftMode) 
				{
					case 1: operator = "alsh";break;
					case 2: operator = "llsh";break;
					case 3: operator = "rol(";break;
					case 4: operator = "crol(";break;
					}break;
				case 7: switch(ShiftMode) 
				{
					case 1: operator = "arsh";break;
					case 2: operator = "lrsh";break;
					case 3: operator = "ror(";break;
					case 4: operator = "cror(";break;
				}break;
				case 8: operator ="+";break;
				case 9: operator = "-";break;
				case 10: operator ="*";break;
				case 11: operator ="/";break;
				case 12: operator ="%";break;
				case 13: operator ="not(";break;
				}
				CalDisplayInput.setText(CalDisplayInput.getText()+operator.toString());
			}
		});
	}
	
	public void EqualBitwiseButtonPressed(JButton Button) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = CalDisplayInput.getText();
				String answer = EvaluateProgString.Eval(input,mode,mode);
				CalDisplayResult.setText(answer);
				String BINanswer = EvaluateProgString.Eval(input,mode,2);
				BINtextField.setText(BINanswer);
				String DECanswer = EvaluateProgString.Eval(input,mode,10);
				DECtextField.setText(DECanswer);
				String OCTanswer = EvaluateProgString.Eval(input,mode,8);
				OCTtextField.setText(OCTanswer);
				String HEXanswer = EvaluateProgString.Eval(input,mode,16);
				HEXtextField.setText(HEXanswer);
				mark = 1 ;
			}
		});
	}
	
	public void OperatorButtonPressed(JButton Button,int OpType) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(OpType) 
				{
				case 0: operator = "+";break;
				case 1: operator = "-";break;
				case 2: operator = "*";break;
				case 3: operator = "/";break;
				case 4: operator = "^";break;
				case 5: operator = "yroot";break;
				case 6: operator = "base log";break;
				case 7: operator = "mod";break;
				case 8: operator = "comb";break;
				case 9: operator = "perm";break;
				case 10: operator = "abs(";break;
				}
				CalDisplayInput.setText(CalDisplayInput.getText()+" "+operator.toString()+" ");
				mark = 0;
			}
		});
	}
	
	public void TrigonometryButtonPressed(JButton Button,int OpType) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String trigonometry = null;
				switch(OpType) 
				{
				case 0: trigonometry = "sin";break;
				case 1: trigonometry = "cos";break;
				case 2: trigonometry = "tan";break;
				case 3: trigonometry = "cot";break;
				case 4: trigonometry = "sec";break;
				case 5: trigonometry = "csc";break;
				}
				if(TrigonometryMode==1) {trigonometry = trigonometry + "h";}
				if(InvRegInd == 1) {trigonometry =  "a" + trigonometry; }
				CalDisplayInput.setText(CalDisplayInput.getText() + trigonometry + "(");
			}
		});
	}
	
	public void powfunc(JButton Button, int functype) 
	{
		Button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			switch(functype) 
			{
			case 1: CalDisplayInput.setText(CalDisplayInput.getText() +"^2");
					break;
			case 2: CalDisplayInput.setText(CalDisplayInput.getText() +"^3");
					break;
			case 3: CalDisplayInput.setText(CalDisplayInput.getText() +"^(-1)");
					break;
			case 4: CalDisplayInput.setText(CalDisplayInput.getText() +"10^");
					break;
			case 5: CalDisplayInput.setText(CalDisplayInput.getText() +"2√");
					break;
			case 6: CalDisplayInput.setText(CalDisplayInput.getText() +"3√");
					break;
			case 7: CalDisplayInput.setText(CalDisplayInput.getText() +"log(");
					break;
			case 8: CalDisplayInput.setText(CalDisplayInput.getText() +"ln(");
					break;	
			}; 
		}
		});
	}
	
	public void EqualButtonPressed(JButton Button) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = CalDisplayInput.getText();
				String answer = EvaluateString.Eval(input);
				CalDisplayResult.setText(answer);
				mark = 1;
			}
		});
	}
	
	public void initiateStandardCal(JMenuItem StandardCal) 
	{
		StandardCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				setTitle("Standard Calculator - Project 1");
				setBounds(100, 100, 325, 465);
				
		//Refresh main variable 
				RefreshCal();
				
		//Set up Calculator display text field
				CalDisplayEngage();
				CalDisplayInput.setBounds(10, 11, 295, 37);
				CalDisplayResult.setBounds(10, 59, 295, 57);
				
		//Clear, CLear Entry  and Delete Button
				Clear_DeleteButton();
				btnClearEntryButton.setBounds(83, 127, 74, 45);
				btnClearButton.setBounds(156, 127, 74, 45);
				btnDeleteButton.setBounds(229, 127, 74, 45);
								
		//Operator Buttons		
				BasicOperatorButtonInitiate();				
				btnPlusButton.setBounds(229, 303, 74, 45);				
				btnMinusButton.setBounds(229, 259, 74, 45);				
				btnMultiplyButton.setBounds(229, 215, 74, 45);				
				btnDivideButton.setBounds(229, 171, 74, 45);				
				btnEqualButton.setBounds(229, 347, 74, 45);
					
		//Number Buttons
				BasicNumberButtonInitiate();				
				btnZeroButton.setBounds(83, 347, 74, 45);		
				btnButtonOne.setBounds(10, 303, 74, 45);				
				btnButtonTwo.setBounds(83, 303, 74, 45);				
				btnButtonThree.setBounds(156, 303, 74, 45);			
				btnButtonFour.setBounds(10, 259, 74, 45);				
				btnButtonFive.setBounds(83, 259, 74, 45);				
				btnButtonSix.setBounds(156, 259, 74, 45);				
				btnButtonSeven.setBounds(10, 215, 74, 45);				
				btnButtonEight.setBounds(83, 215, 74, 45);				
				btnButtonNine.setBounds(156, 215, 74, 45);				
				
		//Standard Calculator other button
				JButton btnPercentageButton = new JButton("%");
				GetPercentage(btnPercentageButton);
				btnPercentageButton.setBounds(10, 127, 74, 45);
				contentPane.add(btnPercentageButton);
				
				btnPointButton = new JButton(".");
				btnPointButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!CalDisplayInput.getText().contains(".")) 
						{
							CalDisplayInput.setText(CalDisplayInput.getText() + btnPointButton.getText());
						}
					}
				});
				btnPointButton.setBounds(156, 347, 74, 45);	
				contentPane.add(btnPointButton);
				
				JButton btnReciprocalButton = new JButton("1/x");
				GetReciprocal(btnReciprocalButton);
				btnReciprocalButton.setBounds(10, 171, 74, 45);
				contentPane.add(btnReciprocalButton);
				
				JButton btnNegateButton = new JButton("+/-");
				btnNegateButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(CalDisplayResult.getText()!=null) 
						{
							double ops = Double.parseDouble(String.valueOf(CalDisplayResult.getText()));
							ops = ops *(-1);
							CalDisplayResult.setText(String.valueOf(ops));
						}
					}
				});
				btnNegateButton.setBounds(10, 347, 74, 45);
				contentPane.add(btnNegateButton);
				
				JButton btnSquareButton = new JButton("<html>x<sup>2</sup></html>");
				powfunc(btnSquareButton, 1);
				btnSquareButton.setBounds(83, 171, 74, 45);
				contentPane.add(btnSquareButton);
				
				JButton btnSquareRootButton = new JButton("\u221A"+"x");
				powfunc(btnSquareRootButton, 5);
				btnSquareRootButton.setBounds(156, 171, 74, 45);
				contentPane.add(btnSquareRootButton);
			}
		});
	}
	
	public void initiateSciencetificCal(JMenuItem SciencetificCal) 
	{
		SciencetificCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setTitle("Sciencetific Calculator - Project 1");
				setBounds(100, 100, 513, 589);
			//Refresh main variable
				RefreshCal();
				
			//Set up Calculator display text field
				
				CalDisplayEngage();
				CalDisplayInput.setBounds(10, 11, 440, 37);
				CalDisplayResult.setBounds(10, 59, 440, 57);
							
			//Number Buttons
				BasicNumberButtonInitiate();
				btnZeroButton.setBounds(229, 410, 74, 32);
				btnButtonOne.setBounds(156, 379, 74, 32);
				btnButtonTwo.setBounds(229, 379, 74, 32);
				btnButtonThree.setBounds(302, 379, 74, 32);
				btnButtonFour.setBounds(156, 348, 74, 32);
				btnButtonFive.setBounds(229, 348, 74, 32);
				btnButtonSix.setBounds(302, 348, 74, 32);
				btnButtonSeven.setBounds(156, 317, 74, 32);
				btnButtonEight.setBounds(229, 317, 74, 32);
				btnButtonNine.setBounds(302, 317, 74, 32);
				btnOpeningParenthesis.setBounds(156, 286, 74, 32);
				btnClosingParenthesis.setBounds(229, 286, 74, 32);
					
			//Basic Operator Buttons
				BasicOperatorButtonInitiate();
				btnDivideButton.setBounds(375, 286, 74, 32);
				btnMultiplyButton.setBounds(375, 317, 74, 32);
				btnMinusButton.setBounds(375, 348, 74, 32);
				btnPlusButton.setBounds(375, 379, 74, 32);
				btnEqualButton.setBounds(375, 410, 74, 32);
							
			//Clear, CLear Entry  and Delete Button
				Clear_DeleteButton();
				btnClearButton.setBounds(302, 224, 74, 32);
				btnClearEntryButton.setBounds(229, 224, 74, 32);
				btnDeleteButton.setBounds(375, 224, 74, 32);
							
			//Scientific Calculator Buttons
				JButton btnButtonPermutation = new JButton("nPr");
				OperatorButtonPressed(btnButtonPermutation,9);
				btnButtonPermutation.setBounds(10, 224, 74, 32);		
				contentPane.add(btnButtonPermutation);
							
				btnPointButton = new JButton(".");
				btnPointButton.addActionListener(new ActionListener() {	
				public void actionPerformed(ActionEvent e) {
					if(!CalDisplayResult.getText().contains(".")) 
							{
								CalDisplayInput.setText(CalDisplayInput.getText() + btnPointButton.getText());
							}
						}
					});
				btnPointButton.setBounds(302, 410, 74, 32);	
				contentPane.add(btnPointButton);
							
				JButton btnPiButton = new JButton("π");
				btnPiButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
									CalDisplayResult.setText(String.valueOf(Math.PI));
								}
							});
				btnPiButton.setBounds(156, 410, 74, 32);
				contentPane.add(btnPiButton);
							
				JButton btnEulersNumberButton = new JButton("e");		
				EulerNumber(btnEulersNumberButton,1);
				btnEulersNumberButton.setBounds(302, 255, 74, 32);
				contentPane.add(btnEulersNumberButton);
							
				JButton btnDegreeButton = new JButton("°");	
				btnDegreeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						double a = Double.parseDouble(CalDisplayResult.getText());
						String b = String.format("%6.3e", a);
						CalDisplayResult.setText(b);
					}
				});
				btnDegreeButton.setBounds(156, 224, 74, 32);
				contentPane.add(btnDegreeButton);
							
				JButton btnAbsoluteButton = new JButton("|x|");
				OperatorButtonPressed(btnAbsoluteButton,10);
				btnAbsoluteButton.setBounds(229, 255, 74, 32);
				contentPane.add(btnAbsoluteButton);
							
				JButton btnFactorial = new JButton("n!");
				GetFactorial(btnFactorial);
				btnFactorial.setBounds(302, 286, 74, 32);
				contentPane.add(btnFactorial);
							
				JButton btnPercentageButton = new JButton("%");		
				GetPercentage(btnPercentageButton);
				btnPercentageButton.setBounds(156, 255, 74, 32);
				contentPane.add(btnPercentageButton);
							
				JButton btnSquareButton = new JButton("<html>x<sup>2</sup></html>");
				powfunc(btnSquareButton, 1);
				btnSquareButton.setBounds(10, 255, 74, 32);
				contentPane.add(btnSquareButton);
				
				JButton btnCubeButton = new JButton("<html>x<sup>3</sup></html>");
				powfunc(btnCubeButton, 2);
				btnCubeButton.setBounds(10, 286, 74, 32);
				contentPane.add(btnCubeButton);
							
				JButton btnXtothepowerofY = new JButton("<html>x<sup>y</sup></html>");
				OperatorButtonPressed(btnXtothepowerofY,4);
				btnXtothepowerofY.setBounds(10, 317, 74, 32);
				contentPane.add(btnXtothepowerofY);
							
				JButton btn10tothepowerofX = new JButton("<html>10<sup>x</sup></html>");
				powfunc(btn10tothepowerofX, 4);
				btn10tothepowerofX.setBounds(10, 348, 74, 32);
				contentPane.add(btn10tothepowerofX);
							
				JButton btnLogof10Button = new JButton("log");
				powfunc(btnLogof10Button, 7);
				btnLogof10Button.setBounds(10, 379, 74, 32);
				contentPane.add(btnLogof10Button);
							
				JButton btnNaturalLog = new JButton("ln");
				powfunc(btnNaturalLog, 8);
				btnNaturalLog.setBounds(10, 410, 74, 32);
				contentPane.add(btnNaturalLog);
							
				JButton btnModularButton = new JButton("mod");
				OperatorButtonPressed(btnModularButton,7);
				btnModularButton.setBounds(375, 255, 74, 32);
				contentPane.add(btnModularButton);
							
				JButton btnSquareRootButton = new JButton("\u221A"+"x");
				powfunc(btnSquareRootButton, 5);
				btnSquareRootButton.setBounds(83, 255, 74, 32);
				contentPane.add(btnSquareRootButton);
							
				JButton btnCubeRootButton = new JButton("\u221B"+"x");
				powfunc(btnCubeRootButton, 6);
				btnCubeRootButton.setBounds(83, 286, 74, 32);
				contentPane.add(btnCubeRootButton);
							
				JButton btn2SquareButton = new JButton("<html>2<sup>x</sup></html>");
				powfunc(btn2SquareButton, 3);
				btn2SquareButton.setBounds(83, 348, 74, 32);
				contentPane.add(btn2SquareButton);
							
				JButton btnYRootButton = new JButton("<html><sup>y</sup>\u221Ax</html>");
				OperatorButtonPressed(btnYRootButton,5);
				btnYRootButton.setBounds(83, 317, 74, 32);
				contentPane.add(btnYRootButton);
							
				JButton btnLogYofXButton = new JButton("<html>log<sub>y</sub>x</html>");
				OperatorButtonPressed(btnLogYofXButton,6);
				btnLogYofXButton.setBounds(83, 379, 74, 32);
				contentPane.add(btnLogYofXButton);
						
				JButton btnEulertothepowerofX = new JButton("<html>e<sup>x</sup></html>");
				EulerNumber(btnEulertothepowerofX,2);
				btnEulertothepowerofX.setBounds(83, 410, 74, 32);
				contentPane.add(btnEulertothepowerofX);
					
				JButton btnCombinationButton = new JButton("nCr");
				OperatorButtonPressed(btnCombinationButton,8);
				btnCombinationButton.setBounds(83, 224, 74, 32);
				contentPane.add(btnCombinationButton);
					
				JButton btnSinButton = new JButton("Sin");
				TrigonometryButtonPressed(btnSinButton,0);
				btnSinButton.setBounds(10, 193, 74, 32);
				contentPane.add(btnSinButton);
					
				JButton btnCosButton = new JButton("Cos");
				TrigonometryButtonPressed(btnCosButton,1);
				btnCosButton.setBounds(83, 193, 74, 32);
				contentPane.add(btnCosButton);
					
				JButton btnTanButton = new JButton("Tan");
				TrigonometryButtonPressed(btnTanButton,2);
				btnTanButton.setBounds(156, 193, 74, 32);
				contentPane.add(btnTanButton);
					
				JButton btnCotanButton = new JButton("Cotan");
				TrigonometryButtonPressed(btnCotanButton,3);
				btnCotanButton.setBounds(229, 193, 74, 32);
				contentPane.add(btnCotanButton);
				
				JButton btnSecButton = new JButton("Sec");
				TrigonometryButtonPressed(btnSecButton,4);
				btnSecButton.setBounds(302, 193, 74, 32);
				contentPane.add(btnSecButton);
					
				JButton btnCscButton = new JButton("Csc");
				TrigonometryButtonPressed(btnCscButton,5);
				btnCscButton.setBounds(375, 193, 74, 32);
				contentPane.add(btnCscButton);
				
				JRadioButton rdbtnHybperbolicRadioButton = new JRadioButton("Hyperbolic");
				rdbtnHybperbolicRadioButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent ae) 
					{
						TrigonometryMode = 1;
					}
				});
				rdbtnHybperbolicRadioButton.setBounds(93, 137, 80, 23);
				contentPane.add(rdbtnHybperbolicRadioButton);
				
				JRadioButton rdbtnCircleButton = new JRadioButton("Circle");
				rdbtnCircleButton.setSelected(true);
				if(rdbtnCircleButton.isSelected())TrigonometryMode = 0;
				rdbtnCircleButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent ae) 
					{
						TrigonometryMode = 0;
					}
				});
				rdbtnCircleButton.setBounds(93, 163, 80, 23);
				contentPane.add(rdbtnCircleButton);
				
				JRadioButton rdbtnRegButton = new JRadioButton("Regular");
				rdbtnRegButton.setSelected(true);
				if(rdbtnRegButton.isSelected())InvRegInd = 0;
				rdbtnRegButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent ae) 
					{
						InvRegInd = 0;
					}
				});
				rdbtnRegButton.setBounds(175, 137, 80, 23);
				contentPane.add(rdbtnRegButton);
				
				JRadioButton rdbtnInvButton = new JRadioButton("Inverse");
				rdbtnInvButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent ae) 
					{
						InvRegInd = 1;
					}
				});
				rdbtnInvButton.setBounds(175, 163, 80, 23);
				contentPane.add(rdbtnInvButton);
				
				JLabel lblTrigonometryLabel = new JLabel("Trigonometry");
				lblTrigonometryLabel.setBounds(20, 127, 80, 33);
				contentPane.add(lblTrigonometryLabel);
				
				ButtonGroup HypCir = new ButtonGroup();
				HypCir.add(rdbtnHybperbolicRadioButton);
				HypCir.add(rdbtnCircleButton);
				
				ButtonGroup InvReg = new ButtonGroup();
				InvReg.add(rdbtnRegButton);
				InvReg.add(rdbtnInvButton);
				
			}
		});
	}
		
	public void initiateProgrammerCal(JMenuItem ProgrammerCal) 
	{
		
		ProgrammerCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTitle("Programmer Calculator - Project 1");
				setBounds(100, 100, 513, 589);
		//Refresh main variable
				RefreshCal();
				
		//Set up Calculator display text field
				CalDisplayEngage();
				CalDisplayInput.setBounds(10, 11, 440, 37);
				CalDisplayResult.setBounds(10, 59, 440, 57);
		
		//Number Buttons
				BasicNumberButtonInitiate();
				btnZeroButton.setBounds(229, 486, 74, 32);
				btnButtonOne.setBounds(156, 455, 74, 32);
				btnButtonTwo.setBounds(229, 455, 74, 32);
				btnButtonThree.setBounds(302, 455, 74, 32);
				btnButtonFour.setBounds(156, 424, 74, 32);
				btnButtonFive.setBounds(229, 424, 74, 32);
				btnButtonSix.setBounds(302, 424, 74, 32);
				btnButtonSeven.setBounds(156, 393, 74, 32);
				btnButtonEight.setBounds(229, 393, 74, 32);
				btnButtonNine.setBounds(302, 393, 74, 32);
				btnOpeningParenthesis.setBounds(156, 362, 74, 32);
				btnClosingParenthesis.setBounds(229, 362, 74, 32);
				
		//Operator Buttons 
				programmerOperatorButtonInitiate();	
				btnDivideButton.setBounds(375, 362, 74, 32);
				btnMultiplyButton.setBounds(375, 393, 74, 32);
				btnMinusButton.setBounds(375, 424, 74, 32);
				btnPlusButton.setBounds(375, 455, 74, 32);
				btnEqualButton.setBounds(375, 486, 74, 32);
				btnANDButton.setBounds(10, 331, 74, 32);
				btnORButton.setBounds(10, 362, 74, 32);
				btnNOTButton.setBounds(10, 393, 74, 32);
				btnNANDButton.setBounds(10, 424, 74, 32);
				btnNORButton.setBounds(10, 455, 74, 32);
				btnXORButton.setBounds(10, 486, 74, 32);
				btnRightShiftButton.setBounds(229, 331, 74, 32);
				btnLeftShiftButton.setBounds(156, 331, 74, 32);
				btnModButton.setBounds(302, 486, 74, 32);
				btnHexAButton.setBounds(83, 331, 74, 32);
				btnHexBButton.setBounds(83, 362, 74, 32);
				btnHexDButton.setBounds(83, 424, 74, 32);
				btnHexCButton.setBounds(83, 393, 74, 32);
				btnHexEButton.setBounds(83, 455, 74, 32);
				btnHexFButton.setBounds(83, 486, 74, 32);
				
		//Clear, CLear Entry  and Delete Button
				Clear_DeleteButton_Prog();
				btnClearButton.setBounds(302, 331, 74, 32);
				btnClearEntryButton.setBounds(302, 362, 74, 32);
				btnDeleteButton.setBounds(375, 331, 74, 32);
				
				JButton btnNegateButton = new JButton("+/-");
				btnNegateButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(CalDisplayResult.getText()!=null) 
						{
							Long ops = Long.parseLong(String.valueOf(CalDisplayResult.getText()));
							ops = ops *(-1);
							CalDisplayResult.setText(String.valueOf(ops));
						}
					}
				});
				btnNegateButton.setBounds(156, 486, 74, 32);
				contentPane.add(btnNegateButton);
				
				HEXtextField = new JTextField();
				HEXtextField.setBounds(83, 172, 367, 20);
				contentPane.add(HEXtextField);
				HEXtextField.setColumns(10);
				
				DECtextField = new JTextField();
				DECtextField.setColumns(10);
				DECtextField.setBounds(83, 141, 367, 20);
				contentPane.add(DECtextField);
				
				OCTtextField = new JTextField();
				OCTtextField.setColumns(10);
				OCTtextField.setBounds(83, 202, 367, 20);
				contentPane.add(OCTtextField);
				
				BINtextField = new JTextField();
				BINtextField.setColumns(10);
				BINtextField.setBounds(83, 233, 367, 20);
				contentPane.add(BINtextField);
				
				JRadioButton ArithmeticShift = new JRadioButton("Arithmetic Shift");
				ArithmeticShift.setFont(new Font("Tahoma", Font.PLAIN, 10));
				ArithmeticShift.setHorizontalAlignment(SwingConstants.LEFT);
				ArithmeticShift.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent ae) 
					{
						ShiftMode = 1;
					}
				});
				ArithmeticShift.setSelected(true);
				if(ArithmeticShift.isSelected())ShiftMode=1;
				ArithmeticShift.setBounds(10, 271, 97, 23);
				contentPane.add(ArithmeticShift);
				
				JRadioButton LogicalShift = new JRadioButton("Logical Shift");
				LogicalShift.setFont(new Font("Tahoma", Font.PLAIN, 10));
				LogicalShift.setHorizontalAlignment(SwingConstants.LEFT);
				LogicalShift.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent ae) 
					{
						ShiftMode = 2;
					}
				});
				LogicalShift.setBounds(10, 301, 97, 23);
				contentPane.add(LogicalShift);
				
				JRadioButton RotateCirShift = new JRadioButton("Rotate Circular Shift");
				RotateCirShift.setFont(new Font("Tahoma", Font.PLAIN, 10));
				RotateCirShift.setHorizontalAlignment(SwingConstants.LEFT);
				RotateCirShift.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent ae) 
					{
						ShiftMode = 3;
					}
				});
				RotateCirShift.setBounds(109, 271, 123, 23);
				contentPane.add(RotateCirShift);
				
				JRadioButton RotateThrCarCirShift = new JRadioButton("Rotate Throught Carry Circular Shift");
				RotateThrCarCirShift.setFont(new Font("Tahoma", Font.PLAIN, 10));
				RotateThrCarCirShift.setHorizontalAlignment(SwingConstants.LEFT);
				RotateThrCarCirShift.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent ae) 
					{
						ShiftMode = 4;
					}
				});
				RotateThrCarCirShift.setBounds(109, 301, 194, 23);
				contentPane.add(RotateThrCarCirShift);
				
				JRadioButton OctButton = new JRadioButton("OCT");
				OctButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent ae) 
					{
						NumbModeSelector(2);
					}
				});
				OctButton.setBounds(10, 201, 53, 23);
				contentPane.add(OctButton);
				
				JRadioButton BinButton = new JRadioButton("BIN");
				BinButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent ae) 
					{
						NumbModeSelector(1);
					}
				});
				BinButton.setBounds(10, 232, 53, 23);
				contentPane.add(BinButton);
				
				JRadioButton HEXButton = new JRadioButton("HEX");
				HEXButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent ae) 
					{
						NumbModeSelector(4);
					}
				});
				HEXButton.setBounds(10, 171, 53, 23);
				contentPane.add(HEXButton);
				
				JRadioButton DECButton = new JRadioButton("DEC");
				DECButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent ae) 
					{
						NumbModeSelector(3);
					}
				});
				DECButton.setSelected(true);
				if(DECButton.isSelected())NumbModeSelector(3);
				DECButton.setBounds(10, 140, 53, 23);
				contentPane.add(DECButton);
				
				ButtonGroup NumberGroup = new ButtonGroup();
				NumberGroup.add(HEXButton);
				NumberGroup.add(DECButton);
				NumberGroup.add(OctButton);
				NumberGroup.add(BinButton);
				
				ButtonGroup ShiftGroup = new ButtonGroup();
				ShiftGroup.add(ArithmeticShift);
				ShiftGroup.add(LogicalShift);
				ShiftGroup.add(RotateCirShift);
				ShiftGroup.add(RotateThrCarCirShift);
			}
		});
	}
	
	public void initiateVolumeConvertCal(JMenuItem UnitConvertCal) 
	{
		UnitConvertCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTitle("Volume Converter Calculator - Project 1");
				setBounds(100, 100, 444, 378);
				
				String[] VolumeUnitType = 
					{"Teaspoons","Tablespoons","Fluid ounces","Cups","Pints","Quarts","Gallons","Cubic yards",
						"Cubic inches","Cubic feet","Milliliter","Liters","Cubic meters"};
		//Refresh main variable 
				RefreshCal();
				
		//Set up Calculator display text field
				CalDisplayEngage();
				CalDisplayInput.setBounds(10, 21, 147, 57);
				CalDisplayResult.setBounds(10, 185, 147, 57);
		//Set up combo box
				comboBox_1 = new JComboBox<String>(VolumeUnitType);
				comboBox_1.setSelectedItem("Teaspoons");
				comboBox_1.setBounds(50, 89, 107, 29);
				contentPane.add(comboBox_1);
				
				comboBox_2 = new JComboBox<String>(VolumeUnitType);
				comboBox_2.setSelectedItem("Teaspoons");
				comboBox_2.setBounds(50, 257, 107, 29);
				contentPane.add(comboBox_2);
		//Clear, CLear Entry  and Delete Button
				Clear_DeleteButton();
				btnClearEntryButton.setBounds(200, 21, 74, 45);
				btnClearButton.setBounds(273, 21, 74, 45);
				btnDeleteButton.setBounds(346, 21, 74, 45);
								
		//Number Buttons
				BasicNumberButtonInitiate();				
				btnZeroButton.setBounds(273, 197, 74, 45);		
				btnButtonOne.setBounds(200, 153, 74, 45);				
				btnButtonTwo.setBounds(273, 153, 74, 45);				
				btnButtonThree.setBounds(346, 153, 74, 45);			
				btnButtonFour.setBounds(200, 109, 74, 45);				
				btnButtonFive.setBounds(273, 109, 74, 45);				
				btnButtonSix.setBounds(346, 109, 74, 45);				
				btnButtonSeven.setBounds(200, 65, 74, 45);				
				btnButtonEight.setBounds(273, 65, 74, 45);				
				btnButtonNine.setBounds(346, 65, 74, 45);				

				btnPointButton = new JButton(".");
				btnPointButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!CalDisplayInput.getText().contains(".")) 
						{
							CalDisplayInput.setText(CalDisplayInput.getText() + btnPointButton.getText());
						}
					}
				});
				btnPointButton.setBounds(346, 197, 74, 45);	
				contentPane.add(btnPointButton);
				
				JButton btnConvButton = new JButton("=");
				btnConvButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!CalDisplayResult.getText().contains(".")) 
						{
							double res = 
									VolumeConverter.convert(Double.parseDouble(CalDisplayInput.getText()),
											comboBox_1.getSelectedItem().toString(),
											comboBox_2.getSelectedItem().toString()
									);
							CalDisplayResult.setText(String.valueOf(res));
							mark = 1;
						}
					}
				});
				btnConvButton.setBounds(200, 197, 74, 45);	
				contentPane.add(btnConvButton);
				
			}
		});
	}
	
	public void initiateLengthConvertCal(JMenuItem UnitConvertCal) 
	{
		UnitConvertCal.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void actionPerformed(ActionEvent arg0) {
				setTitle("Length Converter Calculator - Project 1");
				setBounds(100, 100, 444, 378);
				
				String[] LengthUnitType = 
					{"Nanometers","Microns","Millimeters","Centimeters","Meters","Kilometers"
							,"Inches","Feet","Yards","Miles","Nautical Miles"};
				
		//Refresh main variable 
				RefreshCal();
				
		//Set up Calculator display text field
				CalDisplayEngage();
				CalDisplayInput.setBounds(10, 21, 147, 57);
				CalDisplayResult.setBounds(10, 185, 147, 57);
		//Set up combo box
				comboBox_1 = new JComboBox(LengthUnitType);
				comboBox_1.setSelectedItem("Centimeters");
				comboBox_1.setBounds(50, 89, 107, 29);
				contentPane.add(comboBox_1);
				
				comboBox_2 = new JComboBox(LengthUnitType);
				comboBox_2.setSelectedItem("Centimeters");
				comboBox_2.setBounds(50, 257, 107, 29);
				contentPane.add(comboBox_2);
		//Clear, CLear Entry  and Delete Button
				Clear_DeleteButton();
				btnClearEntryButton.setBounds(200, 21, 74, 45);
				btnClearButton.setBounds(273, 21, 74, 45);
				btnDeleteButton.setBounds(346, 21, 74, 45);
								
		//Number Buttons
				BasicNumberButtonInitiate();				
				btnZeroButton.setBounds(273, 197, 74, 45);		
				btnButtonOne.setBounds(200, 153, 74, 45);				
				btnButtonTwo.setBounds(273, 153, 74, 45);				
				btnButtonThree.setBounds(346, 153, 74, 45);			
				btnButtonFour.setBounds(200, 109, 74, 45);				
				btnButtonFive.setBounds(273, 109, 74, 45);				
				btnButtonSix.setBounds(346, 109, 74, 45);				
				btnButtonSeven.setBounds(200, 65, 74, 45);				
				btnButtonEight.setBounds(273, 65, 74, 45);				
				btnButtonNine.setBounds(346, 65, 74, 45);				

				btnPointButton = new JButton(".");
				btnPointButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!CalDisplayInput.getText().contains(".")) 
						{
							CalDisplayInput.setText(CalDisplayInput.getText() + btnPointButton.getText());
						}
					}
				});
				btnPointButton.setBounds(346, 197, 74, 45);	
				contentPane.add(btnPointButton);
				
				JButton btnConvButton = new JButton("=");
				btnConvButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!CalDisplayResult.getText().contains(".")) 
						{
							double res = 
									LengthConverter.convert(Double.parseDouble(CalDisplayInput.getText()),
											comboBox_1.getSelectedItem().toString(),
											comboBox_2.getSelectedItem().toString()
									);
							CalDisplayResult.setText(String.valueOf(res));
							mark = 1;
						}
					}
				});
				btnConvButton.setBounds(200, 197, 74, 45);	
				contentPane.add(btnConvButton);
				
			}
		});
	}
	
	public void initiateMassConvertCal(JMenuItem UnitConvertCal) 
	{
		UnitConvertCal.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void actionPerformed(ActionEvent arg0) {
				setTitle("Mass and Weight Converter Calculator - Project 1");
				setBounds(100, 100, 444, 378);
				
				String[] MassUnitType = 
					{"Carats","Milligrams","Centigrams","Decigrams","Grams","Dekagrams"
							,"Hectograms","Kilograms","Metric tonnes","Ounces","Pounds"
							,"Stone","Short tons(US)","Long tons(UK)"};
		//Refresh main variable 
				RefreshCal();
				
		//Set up Calculator display text field
				CalDisplayEngage();
				CalDisplayInput.setBounds(10, 21, 147, 57);
				CalDisplayResult.setBounds(10, 185, 147, 57);
		//Set up combo box
				comboBox_1 = new JComboBox(MassUnitType);
				comboBox_1.setSelectedItem("Grams");
				comboBox_1.setBounds(50, 89, 107, 29);
				contentPane.add(comboBox_1);
				
				comboBox_2 = new JComboBox(MassUnitType);
				comboBox_2.setSelectedItem("Grams");
				comboBox_2.setBounds(50, 257, 107, 29);
				contentPane.add(comboBox_2);
		//Clear, CLear Entry  and Delete Button
				Clear_DeleteButton();
				btnClearEntryButton.setBounds(200, 21, 74, 45);
				btnClearButton.setBounds(273, 21, 74, 45);
				btnDeleteButton.setBounds(346, 21, 74, 45);
								
		//Number Buttons
				BasicNumberButtonInitiate();				
				btnZeroButton.setBounds(273, 197, 74, 45);		
				btnButtonOne.setBounds(200, 153, 74, 45);				
				btnButtonTwo.setBounds(273, 153, 74, 45);				
				btnButtonThree.setBounds(346, 153, 74, 45);			
				btnButtonFour.setBounds(200, 109, 74, 45);				
				btnButtonFive.setBounds(273, 109, 74, 45);				
				btnButtonSix.setBounds(346, 109, 74, 45);				
				btnButtonSeven.setBounds(200, 65, 74, 45);				
				btnButtonEight.setBounds(273, 65, 74, 45);				
				btnButtonNine.setBounds(346, 65, 74, 45);				

				btnPointButton = new JButton(".");
				btnPointButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!CalDisplayInput.getText().contains(".")) 
						{
							CalDisplayInput.setText(CalDisplayInput.getText() + btnPointButton.getText());
						}
					}
				});
				btnPointButton.setBounds(346, 197, 74, 45);	
				contentPane.add(btnPointButton);
				
				JButton btnConvButton = new JButton("=");
				btnConvButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!CalDisplayResult.getText().contains(".")) 
						{
							double res = 
									MassConverter.convert(Double.parseDouble(CalDisplayInput.getText()),
											comboBox_1.getSelectedItem().toString(),
											comboBox_2.getSelectedItem().toString()
									);
							CalDisplayResult.setText(String.valueOf(res));
							mark = 1;
						}
					}
				});
				btnConvButton.setBounds(200, 197, 74, 45);	
				contentPane.add(btnConvButton);
				
			}
		});
	}
	
	public void initiateTemperatureConvertCal(JMenuItem UnitConvertCal) 
	{
		UnitConvertCal.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void actionPerformed(ActionEvent arg0) {
				setTitle("Temperature Converter Calculator - Project 1");
				setBounds(100, 100, 444, 378);
				
				String[] Temperature =
					{"Celsius","Fahrenheit","Kelvin"};
		//Refresh main variable 
				RefreshCal();
				
		//Set up Calculator display text field
				CalDisplayEngage();
				CalDisplayInput.setBounds(10, 21, 147, 57);
				CalDisplayResult.setBounds(10, 185, 147, 57);
		//Set up combo box
				comboBox_1 = new JComboBox(Temperature);
				comboBox_1.setSelectedItem("Celsius");
				comboBox_1.setBounds(50, 89, 107, 29);
				contentPane.add(comboBox_1);
				
				comboBox_2 = new JComboBox(Temperature);
				comboBox_2.setSelectedItem("Celsius");
				comboBox_2.setBounds(50, 257, 107, 29);
				contentPane.add(comboBox_2);
		//Clear, CLear Entry  and Delete Button
				Clear_DeleteButton();
				btnClearEntryButton.setBounds(200, 21, 74, 45);
				btnClearButton.setBounds(273, 21, 74, 45);
				btnDeleteButton.setBounds(346, 21, 74, 45);
								
		//Number Buttons
				BasicNumberButtonInitiate();				
				btnZeroButton.setBounds(273, 197, 74, 45);		
				btnButtonOne.setBounds(200, 153, 74, 45);				
				btnButtonTwo.setBounds(273, 153, 74, 45);				
				btnButtonThree.setBounds(346, 153, 74, 45);			
				btnButtonFour.setBounds(200, 109, 74, 45);				
				btnButtonFive.setBounds(273, 109, 74, 45);				
				btnButtonSix.setBounds(346, 109, 74, 45);				
				btnButtonSeven.setBounds(200, 65, 74, 45);				
				btnButtonEight.setBounds(273, 65, 74, 45);				
				btnButtonNine.setBounds(346, 65, 74, 45);				

				btnPointButton = new JButton(".");
				btnPointButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!CalDisplayInput.getText().contains(".")) 
						{
							CalDisplayInput.setText(CalDisplayInput.getText() + btnPointButton.getText());
						}
					}
				});
				btnPointButton.setBounds(346, 197, 74, 45);	
				contentPane.add(btnPointButton);
				
				JButton btnConvButton = new JButton("=");
				btnConvButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!CalDisplayResult.getText().contains(".")) 
						{
							double res = 
									TemperatureConverter.convert(Double.parseDouble(CalDisplayInput.getText()),
											comboBox_1.getSelectedItem().toString(),
											comboBox_2.getSelectedItem().toString()
									);
							CalDisplayResult.setText(String.valueOf(res));
							mark = 1;
						}
					}
				});
				btnConvButton.setBounds(200, 197, 74, 45);	
				contentPane.add(btnConvButton);
				
			}
		});
	}
	
	public void initiateEnergyConvertCal(JMenuItem UnitConvertCal) 
	{
		UnitConvertCal.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void actionPerformed(ActionEvent arg0) {
				setTitle("Energy Converter Calculator - Project 1");
				setBounds(100, 100, 444, 378);
				
				String[] Energy = 
					{"Electron volts","Joules","Kilojoules","Thermal calories"
							,"Food calories","Foot-pounds","British thermal units"};
		//Refresh main variable 
				RefreshCal();
				
		//Set up Calculator display text field
				CalDisplayEngage();
				CalDisplayInput.setBounds(10, 21, 147, 57);
				CalDisplayResult.setBounds(10, 185, 147, 57);
		//Set up combo box
				comboBox_1 = new JComboBox(Energy);
				comboBox_1.setSelectedItem("Joules");
				comboBox_1.setBounds(50, 89, 107, 29);
				contentPane.add(comboBox_1);
				
				comboBox_2 = new JComboBox(Energy);
				comboBox_2.setSelectedItem("Joules");
				comboBox_2.setBounds(50, 257, 107, 29);
				contentPane.add(comboBox_2);
		//Clear, CLear Entry  and Delete Button
				Clear_DeleteButton();
				btnClearEntryButton.setBounds(200, 21, 74, 45);
				btnClearButton.setBounds(273, 21, 74, 45);
				btnDeleteButton.setBounds(346, 21, 74, 45);
								
		//Number Buttons
				BasicNumberButtonInitiate();				
				btnZeroButton.setBounds(273, 197, 74, 45);		
				btnButtonOne.setBounds(200, 153, 74, 45);				
				btnButtonTwo.setBounds(273, 153, 74, 45);				
				btnButtonThree.setBounds(346, 153, 74, 45);			
				btnButtonFour.setBounds(200, 109, 74, 45);				
				btnButtonFive.setBounds(273, 109, 74, 45);				
				btnButtonSix.setBounds(346, 109, 74, 45);				
				btnButtonSeven.setBounds(200, 65, 74, 45);				
				btnButtonEight.setBounds(273, 65, 74, 45);				
				btnButtonNine.setBounds(346, 65, 74, 45);				

				btnPointButton = new JButton(".");
				btnPointButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!CalDisplayInput.getText().contains(".")) 
						{
							CalDisplayInput.setText(CalDisplayInput.getText() + btnPointButton.getText());
						}
					}
				});
				btnPointButton.setBounds(346, 197, 74, 45);	
				contentPane.add(btnPointButton);
				
				JButton btnConvButton = new JButton("=");
				btnConvButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!CalDisplayResult.getText().contains(".")) 
						{
							double res = 
									EnergyConverter.convert(Double.parseDouble(CalDisplayInput.getText()),
											comboBox_1.getSelectedItem().toString(),
											comboBox_2.getSelectedItem().toString()
									);
							CalDisplayResult.setText(String.valueOf(res));
							mark = 1;
						}
					}
				});
				btnConvButton.setBounds(200, 197, 74, 45);	
				contentPane.add(btnConvButton);
				
			}
		});
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public MainWindow() {
		
		setBackground(Color.WHITE);
		setTitle("Calculator - Project 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 364);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFileMenu = new JMenu("File");
		menuBar.add(mnFileMenu);
		
		JMenuItem mntmStandardItem = new JMenuItem("Standard");
		initiateStandardCal(mntmStandardItem);
		mnFileMenu.add(mntmStandardItem);
		
		JMenuItem mntmScienctificItem = new JMenuItem("Scienctific");
		initiateSciencetificCal(mntmScienctificItem);
		mnFileMenu.add(mntmScienctificItem);
		
		JMenuItem mntmProgramerItem = new JMenuItem("Programmer");
		initiateProgrammerCal(mntmProgramerItem );
		mnFileMenu.add(mntmProgramerItem);
		
		JMenu mnConvertMenu = new JMenu("Converter");
		mnFileMenu.add(mnConvertMenu);
		
		JMenuItem mntmVolumeMenuItem = new JMenuItem("Volume");
		initiateVolumeConvertCal(mntmVolumeMenuItem);
		mnConvertMenu.add(mntmVolumeMenuItem);
		
		JMenuItem mntmLengthMenuItem = new JMenuItem("Length");
		initiateLengthConvertCal(mntmLengthMenuItem);
		mnConvertMenu.add(mntmLengthMenuItem);
		
		JMenuItem mntmWeightMenuItem = new JMenuItem("Weight and Mass");
		initiateMassConvertCal(mntmWeightMenuItem);
		mnConvertMenu.add(mntmWeightMenuItem);
		
		JMenuItem mntmTemperatureMenuItem = new JMenuItem("Temperature");
		initiateTemperatureConvertCal(mntmTemperatureMenuItem );
		mnConvertMenu.add(mntmTemperatureMenuItem);
		
		JMenuItem mntmEnergyMenuItem = new JMenuItem("Energy");
		initiateEnergyConvertCal(mntmEnergyMenuItem);
		mnConvertMenu.add(mntmEnergyMenuItem);

		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	}
}

