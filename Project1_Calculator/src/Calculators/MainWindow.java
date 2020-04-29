  
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

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.SystemColor;
import javax.swing.event.ChangeListener;
import javax.swing.text.AbstractDocument.Content;
import javax.swing.event.ChangeEvent;
import javax.swing.JTextPane;

public class MainWindow extends JFrame {
	double firstnum = 0;
	double secondnum = 0;
	double result;
	int mark=0;
	int mark2=0;
	int mark3 = 0;
	String operator;
	
	private Object math;
	private JPanel contentPane;
	private JTextField CalDisplayInput;
	private JTextField CalDisplayResult;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	public void GenOps() 
	{
		firstnum = 0;
		secondnum = 0;
		mark=0;
		mark2=0;
		mark3 = 0;
	}
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
				case 0: operator = Button.getText();break;
				case 1: operator = "^";break;
				case 2: operator = "yroot";break;
				case 3: operator = "base log";break;
				}
				String answer;
				if(mark == 0) 
				{
					String iNum = CalDisplayResult.getText()+operator;
					if(CalDisplayResult == null) firstnum = 0; else
					firstnum = Double.parseDouble(CalDisplayResult.getText());
					if(CalDisplayInput.getText()==null) 
						{
						CalDisplayInput.setText(iNum);
						}
					else CalDisplayInput.setText(CalDisplayInput.getText()+iNum);
				
				switch(operator) 
				{
				case "mod":
					result = firstnum % secondnum;
					break;
				case "+":
					result = firstnum + secondnum;
					break;
				case "-":
					result = firstnum - secondnum;
					break;
				case "*":
					result = firstnum * secondnum;
					break;
				case "/":
					result = firstnum / secondnum;
					break;
				case "^":
					result = Math.pow(firstnum,secondnum);
					break;
				case "yroot":
					result = Math.pow(firstnum, 1/secondnum);
					break;
				case "base log":
					result = Math.log(firstnum)/Math.log(secondnum);
					break;
				}
				answer = String.format("%.2f",result);
				if(mark2 == 1) CalDisplayResult.setText(answer); 
				secondnum = Double.parseDouble(CalDisplayResult.getText());
				mark = 1;
				mark2 = 1;
			}}
		});
	}
	
	public void powfunc(JButton Button, int functype) 
	{
	}
	
	public void EqualButtonPressed(JButton Button) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String answer;
				mark = 1;
				secondnum = Double.parseDouble(CalDisplayResult.getText());
				switch(operator) 
				{
				case "mod":
					result = firstnum % secondnum;
					break;
				case "+":
					result = firstnum + secondnum;
					break;
				case "-":
					result = firstnum - secondnum;
					break;
				case "*":
					result = firstnum * secondnum;
					break;
				case "/":
					result = firstnum / secondnum;
					break;
				case "^":
					result = Math.pow(firstnum,secondnum);
					break;
				case "yroot":
					result = Math.pow(firstnum , 1/secondnum);
					break;
				case "base log":
					result = Math.log(firstnum)/Math.log(secondnum);
					break;
				}
				answer = String.format("%.2f",result);
				CalDisplayInput.setText(firstnum + operator + secondnum);
				CalDisplayResult.setText(answer);
			}
		});
	}
	
	public void initiateStandardCal(JMenuItem StandardCal) 
	{
		StandardCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				
				CalDisplayEngage();
				GenOps();
				setTitle("Standard Calculator - Project 1");
				setBounds(100, 100, 325, 465);
				
				CalDisplayInput.setBounds(10, 11, 295, 37);
				CalDisplayInput.setText(null);
				CalDisplayResult.setBounds(10, 59, 295, 57);
				CalDisplayResult.setText("0");
				
				JButton btnClearEntryButton = new JButton("CE");
				btnClearEntryButton.setBounds(83, 127, 74, 45);
				contentPane.add(btnClearEntryButton);
				
				JButton btnClearButton = new JButton("C");
				btnClearButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CalDisplayInput.setText("");
						CalDisplayResult.setText("");
						firstnum = 0;
						secondnum = 0;
						result = 0;
					}
				});
				btnClearButton.setBounds(156, 127, 74, 45);
				contentPane.add(btnClearButton);
				
				JButton btnDeleteButton = new JButton("\u232b");
				btnDeleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String bsp = null;
						if(CalDisplayInput.getText().length()>0) {
							StringBuilder strB = new StringBuilder(CalDisplayInput.getText());
							strB.deleteCharAt(CalDisplayInput.getText().length() - 1);
							bsp = strB.toString();
							CalDisplayInput.setText(bsp);
						}
					}
				});
				btnDeleteButton.setBounds(229, 127, 74, 45);
				contentPane.add(btnDeleteButton);
				
				JButton btnPercentageButton = new JButton("%");
				GetPercentage(btnPercentageButton);
				btnPercentageButton.setBounds(10, 127, 74, 45);
				contentPane.add(btnPercentageButton);
				
				JButton btnReciprocalButton = new JButton("1/x");
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

				JButton btnPlusButton = new JButton("+");
				OperatorButtonPressed(btnPlusButton,0);
				btnPlusButton.setBounds(229, 303, 74, 45);
				contentPane.add(btnPlusButton);
				
				JButton btnMinusButton = new JButton("-");
				OperatorButtonPressed(btnMinusButton,0);
				btnMinusButton.setBounds(229, 259, 74, 45);
				contentPane.add(btnMinusButton);
				
				JButton btnMultiplyButton = new JButton("×");
				OperatorButtonPressed(btnMultiplyButton,0);
				btnMultiplyButton.setBounds(229, 215, 74, 45);
				contentPane.add(btnMultiplyButton);

				JButton btnDivideButton = new JButton("÷");
				OperatorButtonPressed(btnDivideButton,0);
				btnDivideButton.setBounds(229, 171, 74, 45);
				contentPane.add(btnDivideButton);
				
				JButton btnEqualButton = new JButton("=");
				EqualButtonPressed(btnEqualButton);
				btnEqualButton.setBounds(229, 347, 74, 45);
				contentPane.add(btnEqualButton);
					
		//Number Button 	
				JButton btnZeroButton = new JButton("0");
				NumberButtonPressed(btnZeroButton);
				btnZeroButton.setBounds(83, 347, 74, 45);
				contentPane.add(btnZeroButton);
				
				JButton btnButtonOne = new JButton("1");
				NumberButtonPressed(btnButtonOne);
				btnButtonOne.setBounds(10, 303, 74, 45);
				contentPane.add(btnButtonOne);
				
				JButton btnButtonTwo = new JButton("2");
				NumberButtonPressed(btnButtonTwo);
				btnButtonTwo.setBounds(83, 303, 74, 45);
				contentPane.add(btnButtonTwo);
				
				JButton btnButtonThree = new JButton("3");
				NumberButtonPressed(btnButtonThree);
				btnButtonThree.setBounds(156, 303, 74, 45);
				contentPane.add(btnButtonThree);

				JButton btnButtonFour = new JButton("4");
				NumberButtonPressed(btnButtonFour);
				btnButtonFour.setBounds(10, 259, 74, 45);
				contentPane.add(btnButtonFour);
				
				JButton btnButtonFive = new JButton("5");
				NumberButtonPressed(btnButtonFive);
				btnButtonFive.setBounds(83, 259, 74, 45);
				contentPane.add(btnButtonFive);
				
				JButton btnButtonSix = new JButton("6");
				NumberButtonPressed(btnButtonSix);
				btnButtonSix.setBounds(156, 259, 74, 45);
				contentPane.add(btnButtonSix);
				
				JButton btnButtonSeven = new JButton("7");
				NumberButtonPressed(btnButtonSeven);
				btnButtonSeven.setBounds(10, 215, 74, 45);
				contentPane.add(btnButtonSeven);
				
				JButton btnButtonEight = new JButton("8");
				NumberButtonPressed(btnButtonEight);
				btnButtonEight.setBounds(83, 215, 74, 45);
				contentPane.add(btnButtonEight);
				
				JButton btnButtonNine = new JButton("9");
				NumberButtonPressed(btnButtonNine);
				btnButtonNine.setBounds(156, 215, 74, 45);
				contentPane.add(btnButtonNine);
				
				JButton btnPointButton = new JButton(".");
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
			}
		});
	}
	public void initiateSciencetificCal(JMenuItem SciencetificCal) 
	{
		SciencetificCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				//aaa
				CalDisplayEngage();
				GenOps();
				setTitle("Scienctific Calculator - Project 1");
				setBounds(100, 100, 480, 475);
				
				CalDisplayInput.setBounds(10, 11, 440, 37);
				CalDisplayInput.setText(null);
				CalDisplayResult.setBounds(10, 59, 440, 57);
				CalDisplayResult.setText("0");
				
				JButton btnZeroButton = new JButton("0");
				NumberButtonPressed(btnZeroButton);
				btnZeroButton.setBounds(229, 363, 74, 32);
				contentPane.add(btnZeroButton);
				
				JButton btnButtonOne = new JButton("1");
				NumberButtonPressed(btnButtonOne);
				btnButtonOne.setBounds(156, 332, 74, 32);
				contentPane.add(btnButtonOne);
				
				JButton btnButtonTwo = new JButton("2");
				NumberButtonPressed(btnButtonTwo);
				btnButtonTwo.setBounds(229, 332, 74, 32);
				contentPane.add(btnButtonTwo);
				
				JButton btnButtonThree = new JButton("3");
				NumberButtonPressed(btnButtonThree);
				btnButtonThree.setBounds(302, 332, 74, 32);
				contentPane.add(btnButtonThree);

				JButton btnButtonFour = new JButton("4");
				NumberButtonPressed(btnButtonFour);
				btnButtonFour.setBounds(156, 301, 74, 32);
				contentPane.add(btnButtonFour);
				
				JButton btnButtonFive = new JButton("5");
				NumberButtonPressed(btnButtonFive);
				btnButtonFive.setBounds(229, 301, 74, 32);
				contentPane.add(btnButtonFive);
				
				JButton btnButtonSix = new JButton("6");
				NumberButtonPressed(btnButtonSix);
				btnButtonSix.setBounds(302, 301, 74, 32);
				contentPane.add(btnButtonSix);
				
				JButton btnButtonSeven = new JButton("7");
				NumberButtonPressed(btnButtonSeven);
				btnButtonSeven.setBounds(156, 270, 74, 32);
				contentPane.add(btnButtonSeven);
				
				JButton btnButtonEight = new JButton("8");
				NumberButtonPressed(btnButtonEight);
				btnButtonEight.setBounds(229, 270, 74, 32);
				contentPane.add(btnButtonEight);
				
				JButton btnButtonNine = new JButton("9");
				NumberButtonPressed(btnButtonNine);
				btnButtonNine.setBounds(302, 270, 74, 32);
				contentPane.add(btnButtonNine);
				
				JButton btnPointButton = new JButton(".");
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
				
				JButton btnClearButton = new JButton("C");
				btnClearButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								CalDisplayInput.setText("");
								CalDisplayResult.setText("0");
								firstnum = 0;
								secondnum = 0;
								result = 0;
							}
						});
				btnClearButton.setBounds(302, 177, 74, 32);
				contentPane.add(btnClearButton);
				
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
				OperatorButtonPressed(btnXtothepowerofY,1);
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
				
				JButton btnDeleteButton = new JButton("\u232b");
				btnDeleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String bsp = null;
						if(CalDisplayInput.getText().length()>0) {
							StringBuilder strB = new StringBuilder(CalDisplayInput.getText());
							strB.deleteCharAt(CalDisplayInput.getText().length() - 1);
							bsp = strB.toString();
							CalDisplayInput.setText(bsp);
						}
					}
				});
				btnDeleteButton.setBounds(376, 177, 74, 32);
				contentPane.add(btnDeleteButton);
				
				JButton btnModularButton = new JButton("mod");
				OperatorButtonPressed(btnModularButton,0);
				btnModularButton.setBounds(376, 208, 74, 32);
				contentPane.add(btnModularButton);
				
				JButton btnDivideButton = new JButton("÷");
				OperatorButtonPressed(btnDivideButton,0);
				btnDivideButton.setBounds(376, 239, 74, 32);
				contentPane.add(btnDivideButton);
				
				JButton btnMultiplyButton = new JButton("×");
				OperatorButtonPressed(btnMultiplyButton,0);
				btnMultiplyButton.setBounds(376, 270, 74, 32);
				contentPane.add(btnMultiplyButton);
				
				JButton btnMinusButton = new JButton("-");
				OperatorButtonPressed(btnMinusButton,0);
				btnMinusButton.setBounds(376, 301, 74, 32);
				contentPane.add(btnMinusButton);
				
				JButton btnPlusButton = new JButton("+");
				OperatorButtonPressed(btnPlusButton,0);
				btnPlusButton.setBounds(376, 332, 74, 32);
				contentPane.add(btnPlusButton);
				
				JButton btnEqualButton = new JButton("=");
				EqualButtonPressed(btnEqualButton);
				btnEqualButton.setBounds(376, 363, 74, 32);
				contentPane.add(btnEqualButton);
				
				JButton btnClearEntryButton = new JButton("CE");
				btnClearEntryButton.setBounds(229, 177, 74, 32);
				contentPane.add(btnClearEntryButton);
				
				JButton btnSquareRootButton = new JButton("\u221A"+"x");
				powfunc(btnSquareRootButton, 5);
				btnSquareRootButton.setBounds(83, 208, 74, 32);
				contentPane.add(btnSquareRootButton);
				
				JButton btnCubeRootButton = new JButton("\u221B"+"x");
				powfunc(btnSquareRootButton, 6);
				btnCubeRootButton.setBounds(83, 239, 74, 32);
				contentPane.add(btnCubeRootButton);
				
				JButton btn2SquareButton = new JButton("<html>2<sup>x</sup></html>");
				powfunc(btn2SquareButton, 3);
				btn2SquareButton.setBounds(83, 301, 74, 32);
				contentPane.add(btn2SquareButton);
				
				JButton btnYRootButton = new JButton("<html><sup>y</sup>\u221Ax</html>");
				OperatorButtonPressed(btnYRootButton,2);
				btnYRootButton.setBounds(83, 270, 74, 32);
				contentPane.add(btnYRootButton);
				
				JButton btnLogYofXButton = new JButton("<html>log<sub>y</sub>x</html>");
				OperatorButtonPressed(btnLogYofXButton,3);
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
				
				contentPane.removeAll();
				
				CalDisplayEngage();
				GenOps();
				setTitle("Programmer Calculator - Project 1");
				setBounds(100, 100, 513, 589);
				
				CalDisplayInput.setBounds(10, 11, 440, 37);
				CalDisplayInput.setText(null);
				CalDisplayResult.setBounds(10, 59, 440, 57);
				CalDisplayResult.setText("0");
				
				JButton btnZeroButton = new JButton("0");
				NumberButtonPressed(btnZeroButton);
				btnZeroButton.setBounds(229, 456, 74, 32);
				contentPane.add(btnZeroButton);
				
				JButton btnButtonOne = new JButton("1");
				NumberButtonPressed(btnButtonOne);
				btnButtonOne.setBounds(156, 425, 74, 32);
				contentPane.add(btnButtonOne);
				
				JButton btnButtonTwo = new JButton("2");
				NumberButtonPressed(btnButtonTwo);
				btnButtonTwo.setBounds(229, 425, 74, 32);
				contentPane.add(btnButtonTwo);
				
				JButton btnButtonThree = new JButton("3");
				NumberButtonPressed(btnButtonThree);
				btnButtonThree.setBounds(302, 425, 74, 32);
				contentPane.add(btnButtonThree);

				JButton btnButtonFour = new JButton("4");
				NumberButtonPressed(btnButtonFour);
				btnButtonFour.setBounds(156, 394, 74, 32);
				contentPane.add(btnButtonFour);
				
				JButton btnButtonFive = new JButton("5");
				NumberButtonPressed(btnButtonFive);
				btnButtonFive.setBounds(229, 394, 74, 32);
				contentPane.add(btnButtonFive);
				
				JButton btnButtonSix = new JButton("6");
				NumberButtonPressed(btnButtonSix);
				btnButtonSix.setBounds(302, 394, 74, 32);
				contentPane.add(btnButtonSix);
				
				JButton btnButtonSeven = new JButton("7");
				NumberButtonPressed(btnButtonSeven);
				btnButtonSeven.setBounds(156, 363, 74, 32);
				contentPane.add(btnButtonSeven);
				
				JButton btnButtonEight = new JButton("8");
				NumberButtonPressed(btnButtonEight);
				btnButtonEight.setBounds(229, 363, 74, 32);
				contentPane.add(btnButtonEight);
				
				JButton btnButtonNine = new JButton("9");
				NumberButtonPressed(btnButtonNine);
				btnButtonNine.setBounds(302, 363, 74, 32);
				contentPane.add(btnButtonNine);
				
				JButton btnPointButton = new JButton(".");
				btnPointButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!CalDisplayResult.getText().contains(".")) 
						{
							CalDisplayResult.setText(CalDisplayResult.getText() + btnPointButton.getText());
						}
					}
				});
				btnPointButton.setBounds(302, 456, 74, 32);
				contentPane.add(btnPointButton);
				
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
				btnNegateButton.setBounds(156, 456, 74, 32);
				contentPane.add(btnNegateButton);
				
				JButton btnHexAButton = new JButton("A");
				btnHexAButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CalDisplayResult.setText(String.valueOf(Math.PI));
					}
				});
				btnHexAButton.setBounds(83, 301, 74, 32);
				contentPane.add(btnHexAButton);
				
				JButton btnLeftShiftButton = new JButton("<<");
				EulerNumber(btnLeftShiftButton,1);
				btnLeftShiftButton.setBounds(156, 301, 74, 32);
				contentPane.add(btnLeftShiftButton);
				
				JButton btnClear_ClearEntryButton = new JButton();
				if(CalDisplayResult.getText()=="0") btnClear_ClearEntryButton.setText("C");
				else btnClear_ClearEntryButton.setText("CE");
				btnClear_ClearEntryButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								CalDisplayInput.setText("");
								CalDisplayResult.setText("0");
								firstnum = 0;
								secondnum = 0;
								result = 0;
							}
						});
				btnClear_ClearEntryButton.setBounds(302, 301, 74, 32);
				contentPane.add(btnClear_ClearEntryButton);
				
				JButton btnOpeningParenthesis = new JButton("(");
				btnOpeningParenthesis.setBounds(156, 332, 74, 32);
				contentPane.add(btnOpeningParenthesis);
				
				JButton btnClosingParenthesis = new JButton(")");
				btnClosingParenthesis.setBounds(229, 332, 74, 32);
				contentPane.add(btnClosingParenthesis);
				
				JButton btnPercentageButton = new JButton("%");
				GetFactorial(btnPercentageButton);
				btnPercentageButton.setBounds(302, 332, 74, 32);
				contentPane.add(btnPercentageButton);
				
				JButton btnANDButton = new JButton("AND");
				GetPercentage(btnANDButton);
				btnANDButton.setBounds(10, 301, 74, 32);
				contentPane.add(btnANDButton);;
				
				JButton btnORButton = new JButton("OR");
				powfunc(btnORButton, 2);
				btnORButton.setBounds(10, 332, 74, 32);
				contentPane.add(btnORButton);
				
				JButton btnNOTButton = new JButton("NOT");
				OperatorButtonPressed(btnNOTButton,1);
				btnNOTButton.setBounds(10, 363, 74, 32);
				contentPane.add(btnNOTButton);
				
				JButton btnNANDButton = new JButton("NAND");
				powfunc(btnNANDButton, 4);
				btnNANDButton.setBounds(10, 394, 74, 32);
				contentPane.add(btnNANDButton);
				
				JButton btnNORButton = new JButton("NOR");
				powfunc(btnNORButton, 7);
				btnNORButton.setBounds(10, 425, 74, 32);
				contentPane.add(btnNORButton);
				
				JButton btnXORButton = new JButton("XOR");
				powfunc(btnXORButton, 8);
				btnXORButton.setBounds(10, 456, 74, 32);
				contentPane.add(btnXORButton);
				
				JButton btnDeleteButton = new JButton("\u232b");
				btnDeleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String bsp = null;
						if(CalDisplayInput.getText().length()>0) {
							StringBuilder strB = new StringBuilder(CalDisplayInput.getText());
							strB.deleteCharAt(CalDisplayInput.getText().length() - 1);
							bsp = strB.toString();
							CalDisplayInput.setText(bsp);
						}
					}
				});
				btnDeleteButton.setBounds(376, 301, 74, 32);
				contentPane.add(btnDeleteButton);
				
				JButton btnDivideButton = new JButton("÷");
				OperatorButtonPressed(btnDivideButton,0);
				btnDivideButton.setBounds(376, 332, 74, 32);
				contentPane.add(btnDivideButton);
				
				JButton btnMultiplyButton = new JButton("×");
				OperatorButtonPressed(btnMultiplyButton,0);
				btnMultiplyButton.setBounds(376, 363, 74, 32);
				contentPane.add(btnMultiplyButton);
				
				JButton btnMinusButton = new JButton("-");
				OperatorButtonPressed(btnMinusButton,0);
				btnMinusButton.setBounds(376, 394, 74, 32);
				contentPane.add(btnMinusButton);
				
				JButton btnPlusButton = new JButton("+");
				OperatorButtonPressed(btnPlusButton,0);
				btnPlusButton.setBounds(376, 425, 74, 32);
				contentPane.add(btnPlusButton);
				
				JButton btnEqualButton = new JButton("=");
				EqualButtonPressed(btnEqualButton);
				btnEqualButton.setBounds(376, 456, 74, 32);
				contentPane.add(btnEqualButton);
				
				JButton btnRightShiftButton = new JButton(">>");
				btnRightShiftButton.setBounds(229, 301, 74, 32);
				contentPane.add(btnRightShiftButton);
				
				JButton btnHexBButton = new JButton("B");
				btnHexBButton.setBounds(83, 332, 74, 32);
				contentPane.add(btnHexBButton);
				
				JButton btnHexDButton = new JButton("D");
				powfunc(btnHexDButton, 3);
				btnHexDButton.setBounds(83, 394, 74, 32);
				contentPane.add(btnHexDButton);
				
				JButton btnHexCButton = new JButton("C");
				OperatorButtonPressed(btnHexCButton,2);
				btnHexCButton.setBounds(83, 363, 74, 32);
				contentPane.add(btnHexCButton);
				
				JButton btnHexEButton = new JButton("E");
				OperatorButtonPressed(btnHexEButton,3);
				btnHexEButton.setBounds(83, 425, 74, 32);
				contentPane.add(btnHexEButton);
				
				JButton btnHexFButton = new JButton("F");
				EulerNumber(btnHexFButton,2);
				btnHexFButton.setBounds(83, 456, 74, 32);
				contentPane.add(btnHexFButton);
				
				textField = new JTextField();
				textField.setBounds(10, 141, 440, 20);
				contentPane.add(textField);
				textField.setColumns(10);
				
				textField_1 = new JTextField();
				textField_1.setColumns(10);
				textField_1.setBounds(10, 172, 440, 20);
				contentPane.add(textField_1);
				
				textField_2 = new JTextField();
				textField_2.setColumns(10);
				textField_2.setBounds(10, 202, 440, 20);
				contentPane.add(textField_2);
				
				textField_3 = new JTextField();
				textField_3.setColumns(10);
				textField_3.setBounds(10, 233, 440, 20);
				contentPane.add(textField_3);
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
