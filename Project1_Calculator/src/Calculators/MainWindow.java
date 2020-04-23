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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.SystemColor;

public class MainWindow extends JFrame {
	double firstnum = 0;
	double secondnum = 0;
	double result;
	int mark=0;
	int mark2=0;
	String operator;
	
	private Object math;
	private JPanel contentPane;
	private JTextField CalDisplayInput;
	private JTextField CalDisplayResult;

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
	
	public void OperatorButtonPressed(JButton Button) 
	{
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operator = Button.getText();
				String answer;
				if(mark == 0) 
				{
					String iNum = CalDisplayResult.getText()+Button.getText();
					firstnum = Double.parseDouble(CalDisplayResult.getText());
					if(CalDisplayInput.getText()==null) 
						{
						CalDisplayInput.setText(iNum);
						}
					else CalDisplayInput.setText(CalDisplayInput.getText()+iNum);
				}
				switch(operator) 
				{
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
				}
				answer = String.format("%.2f",result);
				CalDisplayResult.setText(answer);
				secondnum = Double.parseDouble(CalDisplayResult.getText());
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
				
		CalDisplayInput = new JTextField();
				CalDisplayInput.setText("");
				CalDisplayInput.setFont(new Font("Arial", Font.BOLD, 14));
				CalDisplayInput.setHorizontalAlignment(SwingConstants.RIGHT);
				CalDisplayInput.setBackground(UIManager.getColor("Button.background"));
				CalDisplayInput.setBounds(10, 11, 295, 37);
				contentPane.add(CalDisplayInput);
				CalDisplayInput.setColumns(10);
				
				CalDisplayResult = new JTextField();
				CalDisplayResult.setText("");
				CalDisplayResult.setHorizontalAlignment(SwingConstants.RIGHT);
				CalDisplayResult.setFont(new Font("Arial", Font.BOLD, 24));
				CalDisplayResult.setColumns(10);
				CalDisplayResult.setBackground(SystemColor.menu);
				CalDisplayResult.setBounds(10, 59, 295, 57);
				contentPane.add(CalDisplayResult);		
				CalDisplayInput = new JTextField();
				CalDisplayInput.setText("");
				CalDisplayInput.setFont(new Font("Arial", Font.BOLD, 14));
				CalDisplayInput.setHorizontalAlignment(SwingConstants.RIGHT);
				CalDisplayInput.setBackground(UIManager.getColor("Button.background"));
				CalDisplayInput.setBounds(10, 11, 295, 37);
				contentPane.add(CalDisplayInput);
				CalDisplayInput.setColumns(10);
				
				CalDisplayResult = new JTextField();
				CalDisplayResult.setText("");
				CalDisplayResult.setHorizontalAlignment(SwingConstants.RIGHT);
				CalDisplayResult.setFont(new Font("Arial", Font.BOLD, 24));
				CalDisplayResult.setColumns(10);
				CalDisplayResult.setBackground(SystemColor.menu);
				CalDisplayResult.setBounds(10, 59, 295, 57);
				contentPane.add(CalDisplayResult);
				
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
				
				JButton btnDeleteButton = new JButton("Delete");
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
				btnPercentageButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						double ops = Double.parseDouble(String.valueOf(CalDisplayResult.getText()));
						ops = ops/100;
						CalDisplayResult.setText(String.valueOf(ops));
					}
				});
				btnPercentageButton.setBounds(10, 127, 74, 45);
				contentPane.add(btnPercentageButton);
				
				JButton btnReciprocalButton = new JButton("1/x");
				btnReciprocalButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						double ops = Double.parseDouble(String.valueOf(CalDisplayResult.getText()));
						ops = 1/ops;
						CalDisplayInput.setText("1/(" + CalDisplayResult.getText() + ")");
						CalDisplayResult.setText(String.valueOf(ops));
					}
				});
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
				
				JButton btnSquareButton = new JButton("x^2");
				btnSquareButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						double ops = Double.parseDouble(String.valueOf(CalDisplayResult.getText()));
						ops *= ops;
						CalDisplayInput.setText("sqr(" + CalDisplayResult.getText() + ")");
						CalDisplayResult.setText(String.valueOf(ops));
					}
				});
				btnSquareButton.setBounds(83, 171, 74, 45);
				contentPane.add(btnSquareButton);
				
				JButton btnSquareRootButton = new JButton("s/r x");
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
				OperatorButtonPressed(btnPlusButton);
				btnPlusButton.setBounds(229, 303, 74, 45);
				contentPane.add(btnPlusButton);
				
				JButton btnMinusButton = new JButton("-");
				OperatorButtonPressed(btnMinusButton);
				btnMinusButton.setBounds(229, 259, 74, 45);
				contentPane.add(btnMinusButton);
				
				JButton btnMultiplyButton = new JButton("*");
				OperatorButtonPressed(btnMultiplyButton);
				btnMultiplyButton.setBounds(229, 215, 74, 45);
				contentPane.add(btnMultiplyButton);

				JButton btnDivideButton = new JButton("/");
				OperatorButtonPressed(btnDivideButton);
				btnDivideButton.setBounds(229, 171, 74, 45);
				contentPane.add(btnDivideButton);
				
				JButton btnEqualButton = new JButton("=");
				btnEqualButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String answer;
						mark = 1;
						secondnum = Double.parseDouble(CalDisplayResult.getText());
						switch(operator) 
						{
						case "+":
							result = firstnum + secondnum;
							answer = String.format("%.2f",result);
							CalDisplayInput.setText(firstnum + "+" + secondnum);
							CalDisplayResult.setText(answer);
							break;
						case "-":
							result = firstnum - secondnum;
							answer = String.format("%.2f",result);
							CalDisplayInput.setText(firstnum + "-" + secondnum);
							CalDisplayResult.setText(answer);
							break;
						case "*":
							result = firstnum * secondnum;
							answer = String.format("%.2f",result);
							CalDisplayInput.setText(firstnum + "*" + secondnum);
							CalDisplayResult.setText(answer);
							break;
						case "/":
							result = firstnum / secondnum;
							answer = String.format("%.2f",result);
							CalDisplayInput.setText(firstnum + "/" + secondnum);
							CalDisplayResult.setText(answer);
							break;
						}
					}
				});
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
		
	}
	/**
	 * Create the frame.
	 */
	public MainWindow() {
		
		setBackground(Color.WHITE);
		setTitle("Calculator - Project 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 465);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFileMenu = new JMenu("File");
		menuBar.add(mnFileMenu);
		
		JMenuItem mntmStandardItem = new JMenuItem("Standard");
		initiateStandardCal(mntmStandardItem);
		mnFileMenu.add(mntmStandardItem);
		
		JMenuItem mntmScienctificItem = new JMenuItem("Scienctific");
		mntmScienctificItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("Scienctific Calculator - Project 1");
				setBounds(100, 100, 608, 405);
				CalDisplayInput.setBounds(10, 11, 494, 37);
			}
		});
		mnFileMenu.add(mntmScienctificItem);
		
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
		
		CalDisplayInput = new JTextField();
		CalDisplayInput.setText("");
		CalDisplayInput.setFont(new Font("Arial", Font.BOLD, 14));
		CalDisplayInput.setHorizontalAlignment(SwingConstants.RIGHT);
		CalDisplayInput.setBackground(UIManager.getColor("Button.background"));
		CalDisplayInput.setBounds(10, 11, 295, 37);
		contentPane.add(CalDisplayInput);
		CalDisplayInput.setColumns(10);
		
		CalDisplayResult = new JTextField();
		CalDisplayResult.setText("");
		CalDisplayResult.setHorizontalAlignment(SwingConstants.RIGHT);
		CalDisplayResult.setFont(new Font("Arial", Font.BOLD, 24));
		CalDisplayResult.setColumns(10);
		CalDisplayResult.setBackground(SystemColor.menu);
		CalDisplayResult.setBounds(10, 59, 295, 57);
		contentPane.add(CalDisplayResult);
		
		JButton btnZeroButton = new JButton("0");
		NumberButtonPressed(btnZeroButton);
		btnZeroButton.setBounds(154, 363, 74, 32);
		contentPane.add(btnZeroButton);
		
		JButton btnButtonOne = new JButton("1");
		NumberButtonPressed(btnButtonOne);
		btnButtonOne.setBounds(81, 332, 74, 32);
		contentPane.add(btnButtonOne);
		
		JButton btnButtonTwo = new JButton("2");
		NumberButtonPressed(btnButtonTwo);
		btnButtonTwo.setBounds(154, 332, 74, 32);
		contentPane.add(btnButtonTwo);
		
		JButton btnButtonThree = new JButton("3");
		NumberButtonPressed(btnButtonThree);
		btnButtonThree.setBounds(227, 332, 74, 32);
		contentPane.add(btnButtonThree);

		JButton btnButtonFour = new JButton("4");
		NumberButtonPressed(btnButtonFour);
		btnButtonFour.setBounds(81, 301, 74, 32);
		contentPane.add(btnButtonFour);
		
		JButton btnButtonFive = new JButton("5");
		NumberButtonPressed(btnButtonFive);
		btnButtonFive.setBounds(154, 301, 74, 32);
		contentPane.add(btnButtonFive);
		
		JButton btnButtonSix = new JButton("6");
		NumberButtonPressed(btnButtonSix);
		btnButtonSix.setBounds(227, 301, 74, 32);
		contentPane.add(btnButtonSix);
		
		JButton btnButtonSeven = new JButton("7");
		NumberButtonPressed(btnButtonSeven);
		btnButtonSeven.setBounds(81, 269, 74, 32);
		contentPane.add(btnButtonSeven);
		
		JButton btnButtonEight = new JButton("8");
		NumberButtonPressed(btnButtonEight);
		btnButtonEight.setBounds(154, 269, 74, 32);
		contentPane.add(btnButtonEight);
		
		JButton btnButtonNine = new JButton("9");
		NumberButtonPressed(btnButtonNine);
		btnButtonNine.setBounds(227, 269, 74, 32);
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
		btnPointButton.setBounds(227, 363, 74, 32);
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
		btnNegateButton.setBounds(81, 363, 74, 32);
		contentPane.add(btnNegateButton);
		
	}
}

