  
package Calculators;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.*;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.*;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;
import java.awt.SystemColor;

import javax.swing.text.AbstractDocument.Content;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class MainWindow extends JFrame {
	double firstnum = 0;
	double secondnum = 0;
	double result;
	int mark=0;
	int mark2=0;
	int mark3 = 0;
	String operator = "=";
	
	private Object math;
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
	//Basic Operator Button
	private JButton btnPlusButton;
	private JButton btnMinusButton;
	private JButton btnMultiplyButton;
	private JButton btnDivideButton;
	private JButton btnEqualButton;
	//Clear and Clear Entry Button
	private JButton btnClearEntryButton;
	private JButton btnClearButton;
	private JButton btnDeleteButton;
	//Stack
	static Stack<Double> numbers = new Stack<Double>();
	
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
		operator = "=";
		firstnum = 0;
		secondnum = 0;
		mark=0;
		mark2=0;
		mark3 = 0;
	}
	
	//Create Display text field to display result and formula
	public void CalDisplayEngage() 
	{
		CalDisplayInput = new JTextField();
		CalDisplayInput.setText(null);
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
	
	//Create basic number button to be use and reuse in other modes of the calculator
	public void BasicNumberButtonInitiate() 
	{
		btnZeroButton = new JButton("0");
		NumberButtonPressed(btnZeroButton);
		contentPane.add(btnZeroButton);
		
		btnButtonOne = new JButton("1");
		NumberButtonPressed(btnButtonOne);
		contentPane.add(btnButtonOne);
		
		btnButtonTwo = new JButton("2");
		NumberButtonPressed(btnButtonTwo);
		contentPane.add(btnButtonTwo);
		
		btnButtonThree = new JButton("3");
		NumberButtonPressed(btnButtonThree);
		contentPane.add(btnButtonThree);

		btnButtonFour = new JButton("4");
		NumberButtonPressed(btnButtonFour);
		contentPane.add(btnButtonFour);
		
		btnButtonFive = new JButton("5");
		NumberButtonPressed(btnButtonFive);
		contentPane.add(btnButtonFive);
		
		btnButtonSix = new JButton("6");
		NumberButtonPressed(btnButtonSix);
		contentPane.add(btnButtonSix);
		
		btnButtonSeven = new JButton("7");
		NumberButtonPressed(btnButtonSeven);
		contentPane.add(btnButtonSeven);
		
		btnButtonEight = new JButton("8");
		NumberButtonPressed(btnButtonEight);
		contentPane.add(btnButtonEight);
		
		btnButtonNine = new JButton("9");
		NumberButtonPressed(btnButtonNine);
		contentPane.add(btnButtonNine);

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
				numbers.clear();
				firstnum = 0;
				secondnum = 0;
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
		btnPlusButton = new JButton("+");
		OperatorButtonPressed(btnPlusButton,0);
		contentPane.add(btnPlusButton);
		
		btnMinusButton = new JButton("-");
		OperatorButtonPressed(btnMinusButton,1);
		contentPane.add(btnMinusButton);
		
		btnMultiplyButton = new JButton("×");
		OperatorButtonPressed(btnMultiplyButton,2);
		contentPane.add(btnMultiplyButton);

		btnDivideButton = new JButton("÷");
		OperatorButtonPressed(btnDivideButton,3);
		contentPane.add(btnDivideButton);
		
		btnEqualButton = new JButton("=");
		EqualButtonPressed(btnEqualButton);
		contentPane.add(btnEqualButton);
	}
	public void NumberButtonPressed(JButton Button) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String iNum;
				if(mark==1) iNum = Button.getText();
				else iNum = CalDisplayResult.getText() + Button.getText();
				CalDisplayResult.setText(iNum);
				mark = 0;
			}
		});
	}
	
	public void EulerNumber(JButton Button, int exptype) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double exp = 0 ;
				
				switch(exptype) 
				{
					case 1: exp = 1; break;
					case 2: exp = Double.parseDouble(CalDisplayResult.getText()); 
					CalDisplayInput.setText("e^("+CalDisplayResult.getText()+")");
					break;
				}
				CalDisplayResult.setText(String.valueOf(Math.exp(exp)));
			}
		});
	}
	
	public void GetPercentage(JButton Button) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double ops = Double.parseDouble(String.valueOf(CalDisplayResult.getText()));
				ops = ops/100;
				CalDisplayResult.setText(String.valueOf(ops));
			}
		});
	}
	
	public void GetReciprocal(JButton Button) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double ops = Double.parseDouble(String.valueOf(CalDisplayResult.getText()));
				ops =1/ops;
				CalDisplayResult.setText(String.valueOf(ops));
			}
		});
	}
	
	public void GetFactorial(JButton Button) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double ops = Double.parseDouble(String.valueOf(CalDisplayResult.getText()));
				double res = 1,i;
				for(i=2;i<=ops;i++) res *= 1;
				CalDisplayInput.setText("fact(" + CalDisplayResult.getText() + ")");
				CalDisplayResult.setText(String.valueOf(res));
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
				}
				String answer;
				if(mark == 0) 
				{
					String iNum = CalDisplayResult.getText()+operator;
					double pushnum = Double.parseDouble(CalDisplayResult.getText());
					numbers.push(pushnum);
					if(CalDisplayInput.getText()==null) 
						{
						CalDisplayInput.setText(iNum);
						}
					else CalDisplayInput.setText(CalDisplayInput.getText()+iNum);
				}
				if(numbers.size()==2)switch(operator) 
				{
				case "mod":
					firstnum = numbers.pop();
					secondnum = numbers.pop();
					numbers.push(firstnum % secondnum);
					break;
				case "+":
					firstnum = numbers.pop();
					secondnum = numbers.pop();
					numbers.push(firstnum + secondnum);
					break;
				case "-":
					firstnum = numbers.pop();
					secondnum = numbers.pop();
					numbers.push(firstnum - secondnum);
					break;
				case "*":
					firstnum = numbers.pop();
					secondnum = numbers.pop();
					numbers.push(firstnum * secondnum);
					break;
				case "/":
					firstnum = numbers.pop();
					secondnum = numbers.pop();
					numbers.push(firstnum / secondnum);
					break;
				case "^":
					firstnum = numbers.pop();
					secondnum = numbers.pop();
					numbers.push(Math.pow(firstnum,secondnum));
					break;
				case "yroot":
					firstnum = numbers.pop();
					secondnum = numbers.pop();
					numbers.push(Math.pow(firstnum, 1/secondnum));
					break;
				case "base log":
					firstnum = numbers.pop();
					secondnum = numbers.pop();
					numbers.push(Math.log(firstnum)/Math.log(secondnum));
					break;
				}
				result = numbers.lastElement();
				answer = String.format("%.2f",result);
				if(mark2 == 1) CalDisplayResult.setText(answer);
				mark = 1;
				mark2 = 1;
			}
		});
	}
	
	public void powfunc(JButton Button, int functype) 
	{
		Button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			switch(functype) 
			{
			case 1: result = Math.pow(Double.parseDouble(CalDisplayResult.getText()),2);
					break;
			case 2: result = Math.pow(Double.parseDouble(CalDisplayResult.getText()),3);
					break;
			case 3: result = Math.pow(2,Double.parseDouble(CalDisplayResult.getText()));
					break;
			case 4: result = Math.pow(10,Double.parseDouble(CalDisplayResult.getText()));
					break;
			case 5: result = Math.pow(Double.parseDouble(CalDisplayResult.getText()),1/2);
					break;
			case 6: result = Math.pow(Double.parseDouble(CalDisplayResult.getText()),1/3);
					break;
			case 7: result = Math.log10(Double.parseDouble(CalDisplayResult.getText()));
					break;
			case 8: result = Math.log(Double.parseDouble(CalDisplayResult.getText()));
					break;	
			}
			CalDisplayResult.setText(String.format("%.2f",result)); 
		}
		});
	}
	
	public void EqualButtonPressed(JButton Button) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String answer;
				if(mark == 0) 
				{
					String iNum = CalDisplayResult.getText()+operator;
					double pushnum = Double.parseDouble(CalDisplayResult.getText());
					numbers.push(pushnum);
					if(CalDisplayInput.getText()==null) 
						{
						CalDisplayInput.setText(iNum);
						}
					else CalDisplayInput.setText(CalDisplayInput.getText()+iNum);
				}
				mark = 1;
				if(numbers.size()==2)switch(operator) 
				{
				case "mod":
					firstnum = numbers.pop();
					secondnum = numbers.pop();
					numbers.push(firstnum % secondnum);
					break;
				case "+":
					firstnum = numbers.pop();
					secondnum = numbers.pop();
					numbers.push(firstnum + secondnum);
					break;
				case "-":
					firstnum = numbers.pop();
					secondnum = numbers.pop();
					numbers.push(firstnum - secondnum);
					break;
				case "*":
					firstnum = numbers.pop();
					secondnum = numbers.pop();
					numbers.push(firstnum * secondnum);
					break;
				case "/":
					firstnum = numbers.pop();
					secondnum = numbers.pop();
					numbers.push(firstnum / secondnum);
					break;
				case "^":
					firstnum = numbers.pop();
					secondnum = numbers.pop();
					numbers.push(Math.pow(firstnum,secondnum));
					break;
				case "yroot":
					firstnum = numbers.pop();
					secondnum = numbers.pop();
					numbers.push(Math.pow(firstnum, 1/secondnum));
					break;
				case "base log":
					firstnum = numbers.pop();
					secondnum = numbers.pop();
					numbers.push(Math.log(firstnum)/Math.log(secondnum));
					break;
				}
				result = numbers.lastElement();
				answer = String.format("%.2f",result);
				if(mark2 == 1) CalDisplayResult.setText(answer);
				CalDisplayInput.setText(firstnum + operator + secondnum);
				CalDisplayResult.setText(answer);
				operator = "=";
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
						if(!CalDisplayResult.getText().contains(".")) 
						{
							CalDisplayResult.setText(CalDisplayResult.getText() + btnPointButton.getText());
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
				btnSquareRootButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						double ops = Double.parseDouble(String.valueOf(CalDisplayResult.getText()));
						ops = Math.sqrt(ops);
						CalDisplayInput.setText("sqrt(" + CalDisplayResult.getText() + ")");
						CalDisplayResult.setText(String.valueOf(ops));
					}
				});
				btnSquareRootButton.setBounds(156, 171, 74, 45);
				contentPane.add(btnSquareRootButton);
			}
		});
	}
	
	public void initiateSciencetificCal(JMenuItem SciencetificCal) 
	{
		SciencetificCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setTitle("Scienctific Calculator - Project 1");
				setBounds(100, 100, 480, 475);
				
		//Refresh main variable
				RefreshCal();
				
		//Set up Calculator display text field		
				CalDisplayEngage();
				CalDisplayInput.setBounds(10, 11, 440, 37);
				CalDisplayResult.setBounds(10, 59, 440, 57);
				
		//Number Buttons
				BasicNumberButtonInitiate();
				btnZeroButton.setBounds(229, 363, 74, 32);
				btnButtonOne.setBounds(156, 332, 74, 32);
				btnButtonTwo.setBounds(229, 332, 74, 32);
				btnButtonThree.setBounds(302, 332, 74, 32);
				btnButtonFour.setBounds(156, 301, 74, 32);
				btnButtonFive.setBounds(229, 301, 74, 32);
				btnButtonSix.setBounds(302, 301, 74, 32);
				btnButtonSeven.setBounds(156, 270, 74, 32);
				btnButtonEight.setBounds(229, 270, 74, 32);
				btnButtonNine.setBounds(302, 270, 74, 32);
		
		//Basic Operator Buttons
				BasicOperatorButtonInitiate();
				btnDivideButton.setBounds(376, 239, 74, 32);
				btnMultiplyButton.setBounds(376, 270, 74, 32);
				btnMinusButton.setBounds(376, 301, 74, 32);
				btnPlusButton.setBounds(376, 332, 74, 32);
				btnEqualButton.setBounds(376, 363, 74, 32);
				
		//Clear, CLear Entry  and Delete Button
				Clear_DeleteButton();
				btnClearButton.setBounds(302, 177, 74, 32);
				btnClearEntryButton.setBounds(229, 177, 74, 32);
				btnDeleteButton.setBounds(376, 177, 74, 32);
				
		//Scientific Calculator Buttons
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
				btnNegateButton.setBounds(156, 363, 74, 32);
				contentPane.add(btnNegateButton);
				
				btnPointButton = new JButton(".");
				btnPointButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!CalDisplayResult.getText().contains(".")) 
						{
							CalDisplayResult.setText(CalDisplayResult.getText() + btnPointButton.getText());
						}
					}
				});
				btnPointButton.setBounds(302, 363, 74, 32);	
				contentPane.add(btnPointButton);
				
				JButton btnPiButton = new JButton("π");
				btnPiButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CalDisplayResult.setText(String.valueOf(Math.PI));
					}
				});
				btnPiButton.setBounds(83, 177, 74, 32);
				contentPane.add(btnPiButton);
				
				JButton btnEulersNumberButton = new JButton("e");
				EulerNumber(btnEulersNumberButton,1);
				btnEulersNumberButton.setBounds(156, 177, 74, 32);
				contentPane.add(btnEulersNumberButton);
				
				JButton btnExponentialButton = new JButton("exp");
				btnExponentialButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						double a = Double.parseDouble(CalDisplayResult.getText());
						String b = String.format("%6.3e", a);
						CalDisplayResult.setText(b);
					}
				});
				btnExponentialButton.setBounds(302, 208, 74, 32);
				contentPane.add(btnExponentialButton);
				
				JButton btnAbsoluteButton = new JButton("|x|");
				btnAbsoluteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						double a = Double.parseDouble(CalDisplayResult.getText());
						CalDisplayResult.setText(String.valueOf(Math.abs(a)));
					}
				});
				btnAbsoluteButton.setBounds(229, 208, 74, 32);
				contentPane.add(btnAbsoluteButton);
				
				JButton btnOpeningParenthesis = new JButton("(");
				btnOpeningParenthesis.setBounds(156, 239, 74, 32);
				contentPane.add(btnOpeningParenthesis);
				
				JButton btnClosingParenthesis = new JButton(")");
				btnClosingParenthesis.setBounds(229, 239, 74, 32);
				contentPane.add(btnClosingParenthesis);
				
				JButton btnFactorial = new JButton("n!");
				GetFactorial(btnFactorial);
				btnFactorial.setBounds(302, 239, 74, 32);
				contentPane.add(btnFactorial);
				
				JButton btnReciprocalButton = new JButton("1⁄x");
				GetReciprocal(btnReciprocalButton);
				btnReciprocalButton.setBounds(156, 208, 74, 32);
				contentPane.add(btnReciprocalButton);
				
				JButton btnPercentageButton = new JButton("%");
				GetPercentage(btnPercentageButton);
				btnPercentageButton.setBounds(10, 177, 74, 32);
				contentPane.add(btnPercentageButton);
				
				JButton btnSquareButton = new JButton("<html>x<sup>2</sup></html>");
				powfunc(btnSquareButton, 1);
				btnSquareButton.setBounds(10, 208, 74, 32);;
				contentPane.add(btnSquareButton);
				
				JButton btnCubeButton = new JButton("<html>x<sup>3</sup></html>");
				powfunc(btnCubeButton, 2);
				btnCubeButton.setBounds(10, 239, 74, 32);
				contentPane.add(btnCubeButton);
				
				JButton btnXtothepowerofY = new JButton("<html>x<sup>y</sup></html>");
				OperatorButtonPressed(btnXtothepowerofY,4);
				btnXtothepowerofY.setBounds(10, 270, 74, 32);
				contentPane.add(btnXtothepowerofY);
				
				JButton btn10tothepowerofX = new JButton("<html>10<sup>x</sup></html>");
				powfunc(btn10tothepowerofX, 4);
				btn10tothepowerofX.setBounds(10, 301, 74, 32);
				contentPane.add(btn10tothepowerofX);
				
				JButton btnLogof10Button = new JButton("log");
				powfunc(btnLogof10Button, 7);
				btnLogof10Button.setBounds(10, 332, 74, 32);
				contentPane.add(btnLogof10Button);
				
				JButton btnNaturalLog = new JButton("ln");
				powfunc(btnNaturalLog, 8);
				btnNaturalLog.setBounds(10, 363, 74, 32);
				contentPane.add(btnNaturalLog);
				
				JButton btnModularButton = new JButton("mod");
				OperatorButtonPressed(btnModularButton,7);
				btnModularButton.setBounds(376, 208, 74, 32);
				contentPane.add(btnModularButton);
				
				JButton btnSquareRootButton = new JButton("\u221A"+"x");
				powfunc(btnSquareRootButton, 5);
				btnSquareRootButton.setBounds(83, 208, 74, 32);
				contentPane.add(btnSquareRootButton);
				
				JButton btnCubeRootButton = new JButton("\u221B"+"x");
				powfunc(btnCubeRootButton, 6);
				btnCubeRootButton.setBounds(83, 239, 74, 32);
				contentPane.add(btnCubeRootButton);
				
				JButton btn2SquareButton = new JButton("<html>2<sup>x</sup></html>");
				powfunc(btn2SquareButton, 3);
				btn2SquareButton.setBounds(83, 301, 74, 32);
				contentPane.add(btn2SquareButton);
				
				JButton btnYRootButton = new JButton("<html><sup>y</sup>\u221Ax</html>");
				OperatorButtonPressed(btnYRootButton,5);
				btnYRootButton.setBounds(83, 270, 74, 32);
				contentPane.add(btnYRootButton);
				
				JButton btnLogYofXButton = new JButton("<html>log<sub>y</sub>x</html>");
				OperatorButtonPressed(btnLogYofXButton,6);
				btnLogYofXButton.setBounds(83, 332, 74, 32);
				contentPane.add(btnLogYofXButton);
				
				JButton btnEulertothepowerofX = new JButton("<html>e<sup>x</sup></html>");
				EulerNumber(btnEulertothepowerofX,2);
				btnEulertothepowerofX.setBounds(83, 363, 74, 32);
				contentPane.add(btnEulertothepowerofX);

				JMenu mnNewMenu = new JMenu("New menu");
				mnNewMenu.setBounds(10, 137, 113, 27);
				contentPane.add(mnNewMenu);
				
				JMenu mnNewMenu_1 = new JMenu("New menu");
				mnNewMenu_1.setBounds(134, 137, 113, 27);
				contentPane.add(mnNewMenu_1);
			}
		});}
		
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
				
				
		//Operator Buttons 
				BasicOperatorButtonInitiate();	
				btnDivideButton.setBounds(375, 362, 74, 32);
				btnMultiplyButton.setBounds(375, 393, 74, 32);
				btnMinusButton.setBounds(375, 424, 74, 32);
				btnPlusButton.setBounds(375, 455, 74, 32);
				btnEqualButton.setBounds(375, 486, 74, 32);
				
		//Clear, CLear Entry  and Delete Button
				Clear_DeleteButton();
				btnClearButton.setBounds(302, 331, 74, 32);
				btnClearEntryButton.setBounds(302, 362, 74, 32);
				btnDeleteButton.setBounds(375, 331, 74, 32);
				
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
				btnNegateButton.setBounds(156, 486, 74, 32);
				contentPane.add(btnNegateButton);
				
				JButton btnHexAButton = new JButton("A");
				btnHexAButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CalDisplayResult.setText(String.valueOf(Math.PI));
					}
				});
				btnHexAButton.setBounds(83, 331, 74, 32);
				contentPane.add(btnHexAButton);
				
				JButton btnLeftShiftButton = new JButton("<<");
				EulerNumber(btnLeftShiftButton,1);
				btnLeftShiftButton.setBounds(156, 331, 74, 32);
				contentPane.add(btnLeftShiftButton);	
				
				JButton btnOpeningParenthesis = new JButton("(");
				btnOpeningParenthesis.setBounds(156, 362, 74, 32);
				contentPane.add(btnOpeningParenthesis);
				
				JButton btnClosingParenthesis = new JButton(")");
				btnClosingParenthesis.setBounds(229, 362, 74, 32);
				contentPane.add(btnClosingParenthesis);
				
				JButton btnPercentageButton = new JButton("%");
				GetFactorial(btnPercentageButton);
				
				btnPercentageButton.setBounds(302, 486, 74, 32);
				contentPane.add(btnPercentageButton);
				
				JButton btnANDButton = new JButton("AND");
				GetPercentage(btnANDButton);
				btnANDButton.setBounds(10, 331, 74, 32);
				contentPane.add(btnANDButton);;
				
				JButton btnORButton = new JButton("OR");
				powfunc(btnORButton, 2);
				btnORButton.setBounds(10, 362, 74, 32);
				contentPane.add(btnORButton);
				
				JButton btnNOTButton = new JButton("NOT");
				OperatorButtonPressed(btnNOTButton,1);
				btnNOTButton.setBounds(10, 393, 74, 32);
				contentPane.add(btnNOTButton);
				
				JButton btnNANDButton = new JButton("NAND");
				powfunc(btnNANDButton, 4);
				btnNANDButton.setBounds(10, 424, 74, 32);
				contentPane.add(btnNANDButton);
				
				JButton btnNORButton = new JButton("NOR");
				powfunc(btnNORButton, 7);
				btnNORButton.setBounds(10, 455, 74, 32);
				contentPane.add(btnNORButton);
				
				JButton btnXORButton = new JButton("XOR");
				powfunc(btnXORButton, 8);
				btnXORButton.setBounds(10, 486, 74, 32);
				contentPane.add(btnXORButton);
				
				JButton btnRightShiftButton = new JButton(">>");
				btnRightShiftButton.setBounds(229, 331, 74, 32);
				contentPane.add(btnRightShiftButton);
				
				JButton btnHexBButton = new JButton("B");
				btnHexBButton.setBounds(83, 362, 74, 32);
				contentPane.add(btnHexBButton);
				
				JButton btnHexDButton = new JButton("D");
				powfunc(btnHexDButton, 3);
				btnHexDButton.setBounds(83, 424, 74, 32);
				contentPane.add(btnHexDButton);
				
				JButton btnHexCButton = new JButton("C");
				OperatorButtonPressed(btnHexCButton,2);
				btnHexCButton.setBounds(83, 393, 74, 32);
				contentPane.add(btnHexCButton);
				
				JButton btnHexEButton = new JButton("E");
				OperatorButtonPressed(btnHexEButton,3);
				btnHexEButton.setBounds(83, 455, 74, 32);
				contentPane.add(btnHexEButton);
				
				JButton btnHexFButton = new JButton("F");
				EulerNumber(btnHexFButton,2);
				btnHexFButton.setBounds(83, 486, 74, 32);
				contentPane.add(btnHexFButton);
				
				HEXtextField = new JTextField();
				DocumentListener HEXdl = new DocumentListener() 
				{
					@Override
					public void removeUpdate(DocumentEvent e) 
					{
						
					}
					@Override
					public void insertUpdate(DocumentEvent e) 
					{
						textChanged();
					}
					@Override
					public void changedUpdate(DocumentEvent e) 
					{}
					
					private void textChanged() 
					{
						Long Hex = Long.parseLong(CalDisplayResult.getText());
						String Hexstring = Long.toHexString(Hex);
						HEXtextField.setText(String.format("%2s",Hexstring ));
					}
				};
				Long Hex = Long.parseLong(CalDisplayResult.getText());
				String Hexstring = Long.toHexString(Hex);
				HEXtextField.setText(String.format("%2s",Hexstring ));
				CalDisplayResult.getDocument().addDocumentListener(HEXdl);
				HEXtextField.setBounds(83, 172, 367, 20);
				contentPane.add(HEXtextField);
				HEXtextField.setColumns(10);
				
				DECtextField = new JTextField();
				DocumentListener DECdl = new DocumentListener() 
				{
					@Override
					public void removeUpdate(DocumentEvent e) 
					{
						
					}
					@Override
					public void insertUpdate(DocumentEvent e) 
					{
						textChanged();
					}
					@Override
					public void changedUpdate(DocumentEvent e) 
					{}
					
					private void textChanged() 
					{
						String Decstring = CalDisplayResult.getText();
						DECtextField.setText(String.format("%2s",Decstring));
					}
				};
				String Decstring = CalDisplayResult.getText();
				DECtextField.setText(String.format("%2s",Decstring));
				CalDisplayResult.getDocument().addDocumentListener(DECdl);
				DECtextField.setColumns(10);
				DECtextField.setBounds(83, 141, 367, 20);
				contentPane.add(DECtextField);
				
				OCTtextField = new JTextField();
				DocumentListener OCTdl = new DocumentListener() 
				{
					@Override
					public void removeUpdate(DocumentEvent e) 
					{
						
					}
					@Override
					public void insertUpdate(DocumentEvent e) 
					{
						textChanged();
					}
					@Override
					public void changedUpdate(DocumentEvent e) 
					{}
					
					private void textChanged() 
					{
						Long Oct = Long.parseLong(CalDisplayResult.getText());
						String Octstring = Long.toOctalString(Oct);
						OCTtextField.setText(String.format("%2s",Octstring ));
					}
				};
				Long Oct = Long.parseLong(CalDisplayResult.getText());
				String Octstring = Double.toHexString(Oct);
				OCTtextField.setText(String.format("%2s",Octstring ));
				CalDisplayResult.getDocument().addDocumentListener(OCTdl);
				OCTtextField.setColumns(10);
				OCTtextField.setBounds(83, 202, 367, 20);
				contentPane.add(OCTtextField);
				
				BINtextField = new JTextField();
				DocumentListener BINdl = new DocumentListener() 
				{
					@Override
					public void removeUpdate(DocumentEvent e) 
					{
						
					}
					@Override
					public void insertUpdate(DocumentEvent e) 
					{
						textChanged();
					}
					@Override
					public void changedUpdate(DocumentEvent e) 
					{}
					
					private void textChanged() 
					{
						Long Bin = Long.parseLong(CalDisplayResult.getText());
						String Binstring = Long.toBinaryString(Bin);
						BINtextField.setText(String.format("%2s",Binstring ));
					}
				};
				Long Bin = Long.parseLong(CalDisplayResult.getText());
				String Binstring = Long.toBinaryString(Bin);
				BINtextField.setText(String.format("%2s",Binstring ));
				CalDisplayResult.getDocument().addDocumentListener(BINdl);
				BINtextField.setColumns(10);
				BINtextField.setBounds(83, 233, 367, 20);
				contentPane.add(BINtextField);
				
				JRadioButton ArithmeticShift = new JRadioButton("Arithmetic Shift");
				ArithmeticShift.setFont(new Font("Tahoma", Font.PLAIN, 10));
				ArithmeticShift.setHorizontalAlignment(SwingConstants.LEFT);
				ArithmeticShift.setBounds(10, 271, 97, 23);
				contentPane.add(ArithmeticShift);
				
				JRadioButton LogicalShift = new JRadioButton("Logical Shift");
				LogicalShift.setFont(new Font("Tahoma", Font.PLAIN, 10));
				LogicalShift.setHorizontalAlignment(SwingConstants.LEFT);
				LogicalShift.setBounds(10, 301, 97, 23);
				contentPane.add(LogicalShift);
				
				JRadioButton RotateCirShift = new JRadioButton("Rotate Circular Shift");
				RotateCirShift.setFont(new Font("Tahoma", Font.PLAIN, 10));
				RotateCirShift.setHorizontalAlignment(SwingConstants.LEFT);
				RotateCirShift.setBounds(109, 271, 123, 23);
				contentPane.add(RotateCirShift);
				
				JRadioButton RotateThrCarCirShift = new JRadioButton("Rotate Throught Carry Circular Shift");
				RotateThrCarCirShift.setFont(new Font("Tahoma", Font.PLAIN, 10));
				RotateThrCarCirShift.setHorizontalAlignment(SwingConstants.LEFT);
				RotateThrCarCirShift.setBounds(109, 301, 194, 23);
				contentPane.add(RotateThrCarCirShift);
				
				JRadioButton OctButton = new JRadioButton("OCT");
				OctButton.setBounds(10, 201, 53, 23);
				contentPane.add(OctButton);
				
				JRadioButton BinButton = new JRadioButton("BIN");
				BinButton.setBounds(10, 232, 53, 23);
				contentPane.add(BinButton);
				
				JRadioButton HEXButton = new JRadioButton("HEX");
				HEXButton.setBounds(10, 171, 53, 23);
				contentPane.add(HEXButton);
				
				JRadioButton DECButton = new JRadioButton("DEC");
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
	/**
	 * Create the frame.
	 */
	public MainWindow() {
		
		setBackground(Color.WHITE);
		setTitle("Calculator - Project 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 589);
		
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
		
		JMenuItem mntmUnitConvertItem = new JMenuItem("Unit convert");
		mntmUnitConvertItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Unit convert Calculator - Project 1");
				setBounds(100, 100, 884, 367);
				CalDisplayInput.setBounds(10, 11, 494, 37);
			}
		});
		mnFileMenu.add(mntmUnitConvertItem);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CalDisplayEngage();
		
		}
	}

