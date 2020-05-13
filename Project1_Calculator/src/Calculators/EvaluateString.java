package Calculators;

import java.util.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;



public class EvaluateString 
{
	static LinkedList<Token> InfixQueue = new LinkedList<Token>();
	LinkedList<Token> PostfixQueue = new LinkedList<Token>();
	
	public static String evaluate(String expression) 
	{
		int i = 0; 
		char cur;
		String numstr;
		InfixQueue.clear();
		while (i <expression.length()) 
		{
			cur = expression.charAt(i);
			switch(cur) 
			{
			case ' ' : continue;
        	case '0' :case '1' :case '2' :case '3' :case '4' :
        	case '5' :case '6' :case '7' :case '8' :case '9' :case '.':
        		{
        			numstr = new String();
        			numstr = numstr + Character.toString(cur);
        			i++;
        			while (i<expression.length() && isNumeric(expression.charAt(i))) 
        			{
        				numstr = numstr + Character.toString(expression.charAt(i));
        				i++;
        			}
        			i--;
				
				try 
					{
					InfixQueue.addLast(new Token(new Float(numstr).floatValue()));
					}
				catch (NumberFormatException e) 
					{
					InfixQueue.clear();
					return("Invalid number '"+numstr+"'");
					}
				}
        	case '+': InfixQueue.addLast(new Token(cur, Token.OpType.BINARY_LEFT_ASSOC,50));
        	case '-': 
        		{
        			if(InfixQueue.size()==0||
        					(InfixQueue.getLast().ttype!=Token.TokenType.NUMBER
        							&&InfixQueue.getLast().ttype!=Token.TokenType.BRACKET_RIGHT))
        				InfixQueue.addLast(new Token(cur, Token.OpType.UNARY_PREFIX, 100));
        			else InfixQueue.addLast(new Token(cur, Token.OpType.BINARY_LEFT_ASSOC, 50));
        		}
        	case '*': case '/':
        		InfixQueue.addLast(new Token(cur, Token.OpType.BINARY_LEFT_ASSOC,60));
        	case '^': InfixQueue.addLast(new Token(cur, Token.OpType.BINARY_RIGHT_ASSOC,60));
        	case '(' : InfixQueue.addLast(new Token(Token.TokenType.BRACKET_LEFT));
        	case ')' : InfixQueue.addLast(new Token(Token.TokenType.BRACKET_RIGHT));
        	default:
        		{
        			InfixQueue.clear();
        			return("Unexpected character'"+cur+"'");
        		}
			}
		}
		return display(InfixQueue);
	}
	
	public static boolean isNumeric(char ch) 
	{
		if(Character.isDigit(ch)||ch =='.') return true;
		return false;
	}
	
	public static String display(LinkedList<?> l) 
	{
		String s = new String("<html>");
		for(ListIterator<?> it = l.listIterator(0); it.hasNext();) 
		{
			Object t = it.next();
			String tts = t.toString();
			switch(tts) 
			{
			case "*":s+="&times;";
			case "/":s+="&divides;";
			case "-":s+="&minus;";
			default: s+=tts;
			}
			s+="&nbsp;";
		}
		return s+"</html>";
	}
	
	public static String toPostfix(StatusTableModel stm) 
	{
		
	}
	
	public static void main(String[] args) 
    { 
        System.out.println(EvaluateString.evaluate("10.5 +- 2 ^ 6")); 
        System.out.println(EvaluateString.evaluate("100 * 2 + 12")); 
        System.out.println(EvaluateString.evaluate("100.8^9 * ( 2 + 12 )")); 
        System.out.println(EvaluateString.evaluate("100 * ( 2 + 12 ) / 14")); 
    } 
}
