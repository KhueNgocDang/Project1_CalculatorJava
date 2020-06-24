package Calculators;

import java.util.LinkedList;
import java.util.Stack;

public class EvaluateString 
{
	static LinkedList<Token> InfixQueue = new LinkedList<Token>();
	static LinkedList<Token> PostfixQueue = new LinkedList<Token>();
	
	public static float factorial(float n) 
	{
		return(n==1||n==0) ? 1:n*factorial(n-1);
	}
	
	public static float percentage(float n) 
	{
		float hund = 10;
		return (float) (n/hund);
	}
	
	public static float combination(float n, float k) 
	{
		return(factorial(n)/factorial(n-k)*factorial(k));
	}
	public static float permutation(float n, float k) 
	{
		return(factorial(n)/factorial(n-k));
	}
	
	private static boolean isLetter(char ch) {
		if(Character.isLetter(ch)) return true;
		return false;
	}

	public static boolean isNumeric(char ch) 
	{
		if(Character.isDigit(ch)||ch =='.') return true;
		return false;
	}
	
	public static String TokenizeInfix(String expression) 
	{
		char[] token = expression.toCharArray();
		String numstr,func;
		InfixQueue.clear();
		for(int i = 0; i < token.length; i++)
		{	
			switch(token[i]) 
			{
			case ' ' : continue;
			case 'a':case 'b':case 'c':case 'd':case 'e':case 'f':
			case 'g':case 'h':case 'i':case 'k':case 'l':case 'm':
			case 'n':case 'o':case 'p':case 'q':case 'r':case 's':
			case 't':case 'v':case 'w':case 'x':case 'y':case 'π':
			case 'z':{
				func=new String();
				func=func+Character.toString(token[i]);
				i++;
				while (i<expression.length() && isLetter(expression.charAt(i)))
					{func=func+Character.toString(expression.charAt(i));i++;}
				i--;
				switch(func) 
					{
				case"cuber":case"sqrr":case"cube":case"sqr":case"log":case"ln":case"abs":
				case"fact":case"sin":case"cos":case"tan":case"cot":case"sec":case"csc":
				case"sinh":case"cosh":case"tanh":case"coth":case"sech":case"csch":
				case"asin":case"aos":case"atan":case"acot":case"asec":case"acsc":
				case"asinh":case"acosh":case"atanh":case"acoth":case"asech":case"acsch":
					InfixQueue.addLast(new Token(func.toString()));break;
				case"e":
					InfixQueue.addLast(new Token((float)(Math.exp(1))));break;
				case"π":        		
					InfixQueue.addLast(new Token((float)(Math.PI)));break;
				case"logb":case"comb":case"perm":
					InfixQueue.addLast(new Token(func.toString(), Token.OpType.BINARY_RIGHT_ASSOC, 100));
        		break;
					}
				}break;
        	case '0' :case '1' :case '2' :case '3' :case '4' :
        	case '5' :case '6' :case '7' :case '8' :case '9' :case '.':
        		{
    				numstr=new String();
    				numstr=numstr+Character.toString(token[i]);
    				i++;
    				while (i<expression.length() && isNumeric(expression.charAt(i)))
    					{numstr=numstr+Character.toString(expression.charAt(i));i++;}
    				i--;
    				InfixQueue.addLast(new Token(Float.valueOf(numstr)));
				}break;
        	case '+': case '-': 
        	{
        		
    			if (InfixQueue.isEmpty() || 
    					InfixQueue.getLast().ttype!=Token.TokenType.NUMBER || 
    						InfixQueue.getLast().ttype==Token.TokenType.BRACKET_LEFT) 
    				InfixQueue.addLast(new Token(String.valueOf(token[i]), Token.OpType.UNARY_PREFIX, 100));
				else 
					InfixQueue.addLast(new Token(String.valueOf(token[i]), Token.OpType.BINARY_LEFT_ASSOC, 40));
    		}break;
        	case'!':case'%':					
        		InfixQueue.addLast(new Token(String.valueOf(token[i]), Token.OpType.UNARY_POSTFIX, 60));
        	case '^':case '√':
        		InfixQueue.addLast(new Token(String.valueOf(token[i]), Token.OpType.BINARY_RIGHT_ASSOC, 100));
        		break;
        	case '*': case '/':
        		InfixQueue.addLast(new Token(String.valueOf(token[i]), Token.OpType.BINARY_LEFT_ASSOC, 50));
        		break;
        	case '(' : InfixQueue.addLast(new Token(Token.TokenType.BRACKET_LEFT));break;
        	case ')' : InfixQueue.addLast(new Token(Token.TokenType.BRACKET_RIGHT));break;
        	default: {
				InfixQueue.clear();
				return("Unexpected character '"+token[i]+"'");
				}
			}
		}
		return InfixQueue.toString();
	}
	
	public static String ConvertToPostfix() 
	{
		if (InfixQueue.size()==0) return "";
		LinkedList<Token> OperatorStack = new LinkedList<Token>();
		LinkedList<Token> infixQueue = new LinkedList<Token>(InfixQueue);
		Token t;
		PostfixQueue.clear();
		OperatorStack.clear();
		while (infixQueue.size()>0) 
		{
			t = infixQueue.removeFirst();
			switch(t.ttype)
			{
				case NUMBER:
					PostfixQueue.addLast(t);break;
				case OPERATOR:
					switch(t.otype) 
					{
						case UNARY_PREFIX: OperatorStack.addLast(t);break;
						case UNARY_POSTFIX: OperatorStack.addLast(t);break;
						case BINARY_LEFT_ASSOC:
							while (!OperatorStack.isEmpty() && OperatorStack.getLast().precidence > t.precidence) 
								PostfixQueue.addLast(OperatorStack.removeLast());
							OperatorStack.addLast(t);
							break;
						case BINARY_RIGHT_ASSOC:
							while (!OperatorStack.isEmpty() && OperatorStack.getLast().precidence > t.precidence) 
								PostfixQueue.addLast(OperatorStack.removeLast());
							OperatorStack.addLast(t);
							break;
					}break;
				case FUNC:
					PostfixQueue.addLast(t);break;
				case BRACKET_LEFT:OperatorStack.addLast(t);break;
				case BRACKET_RIGHT:
					while (OperatorStack.getLast().ttype != Token.TokenType.BRACKET_LEFT) 
						PostfixQueue.addLast(OperatorStack.removeLast());
					OperatorStack.removeLast();
						break;
				default:
					break;
			}
		}
		while (!OperatorStack.isEmpty()) 
		{
			if (OperatorStack.getLast().ttype!=Token.TokenType.OPERATOR) 
			{
				PostfixQueue.clear();
				return("Non-operator on shunting stack");
			}
			else PostfixQueue.addLast(OperatorStack.removeLast());
		}
		return PostfixQueue.toString();
	}
	
	public static String Evaluate() 
	{
		if (PostfixQueue.size()==0) return "";
		Token t;
		float a1;
		LinkedList<Token> postfixQueue = new LinkedList<Token>(PostfixQueue);
		Stack<Float> rpevalStack = new Stack<Float>(); //Reverse Polish Evaluate Stack
		while (!postfixQueue.isEmpty()) 
		{
			t=postfixQueue.removeFirst();
			switch(t.ttype) 
			{
			case NUMBER:rpevalStack.push(t.val);break;
			case OPERATOR:
				a1 = rpevalStack.pop();
				switch(t.op) 
				{
					case "+": 
							if(t.otype==Token.OpType.UNARY_PREFIX)rpevalStack.push(a1); 
							if(t.otype==Token.OpType.BINARY_LEFT_ASSOC)rpevalStack.push(rpevalStack.pop()+a1);break;
					case "-":
							if(t.otype==Token.OpType.UNARY_PREFIX)rpevalStack.push(-a1); 
							if(t.otype==Token.OpType.BINARY_LEFT_ASSOC)rpevalStack.push(rpevalStack.pop()-a1);break;
					case "*":rpevalStack.push(a1*rpevalStack.pop());break;
					case "/":rpevalStack.push(rpevalStack.pop()/a1);break;
					case "^":rpevalStack.push((float) Math.pow(rpevalStack.pop(),a1));break;
					case "√":rpevalStack.push((float) ((Math.pow(a1,1/rpevalStack.pop()))));break;
					case "logb":rpevalStack.push((float) (Math.log(a1)/Math.log(rpevalStack.pop())));break;
					case "comb":rpevalStack.push(combination(a1,rpevalStack.pop()));break;
					case "perm":rpevalStack.push(permutation(a1,rpevalStack.pop()));break;
					case "%":rpevalStack.push(percentage(a1));break;
					case "!":rpevalStack.push(factorial(a1));break;
				}break;
			case FUNC: 
				a1 = rpevalStack.pop();
				switch(t.func) 
				{
					case "log":rpevalStack.push((float) (Math.log10(a1)));break;
					case "ln":rpevalStack.push((float)(Math.log(a1)));break;
					case "cuber":rpevalStack.push((float)(Math.cbrt(a1)));break;
					case "sqrr":rpevalStack.push((float)(Math.sqrt(a1)));break;
					case "cube":rpevalStack.push((float)(Math.pow(3,a1)));break;
					case "sqr":rpevalStack.push((float)(Math.pow(2,a1)));break;
					case "fact":rpevalStack.push((float)(factorial(a1)));break;
					case "sin":rpevalStack.push((float)(Math.sin(Math.toRadians(a1))));break;
					case "cos":rpevalStack.push((float)(Math.cos(Math.toRadians(a1))));break;
					case "tan":rpevalStack.push((float)(Math.tan(Math.toRadians(a1))));break;
					case "cot":rpevalStack.push((float)(1/Math.tan(Math.toRadians(a1))));break;
					case "csc":rpevalStack.push((float)(1/Math.sin(Math.toRadians(a1))));break;
					case "sec":rpevalStack.push((float)(1/Math.cos(Math.toRadians(a1))));break;
					case "sinh":rpevalStack.push((float)(Math.sinh(Math.toRadians(a1))));break;
					case "cosh":rpevalStack.push((float)(Math.cosh(Math.toRadians(a1))));break;
					case "tanh":rpevalStack.push((float)(Math.tanh(Math.toRadians(a1))));break;
					case "coth":rpevalStack.push((float)(1/Math.tanh(Math.toRadians(a1))));break;
					case "csch":rpevalStack.push((float)(1/Math.sinh(Math.toRadians(a1))));break;
					case "sech":rpevalStack.push((float)(1/Math.cosh(Math.toRadians(a1))));break;
					case "asin":rpevalStack.push((float)(Math.asin(Math.toRadians(a1))));break;
					case "acos":rpevalStack.push((float)(Math.acos(Math.toRadians(a1))));break;
					case "atan":rpevalStack.push((float)(Math.atan(Math.toRadians(a1))));break;
					case "acot":rpevalStack.push((float)(Math.atan(1/Math.toRadians(a1))));break;
					case "asinh":rpevalStack.push((float)(Math.log(a1+Math.sqrt(a1*a1+1))));break;
					case "acosh":rpevalStack.push((float)(Math.log(a1+Math.sqrt(a1*a1-1))));break;
					case "atanh":rpevalStack.push((float)((1/2)*Math.log((1+a1)/(1-a1))));break;
					case "acoth":rpevalStack.push((float)((1/2)*Math.log((a1+1)/(a1-1))));break;
					case "asech":rpevalStack.push((float)(Math.log((1+Math.sqrt(1-a1*a1)/a1))));break;
					case "acsch":rpevalStack.push((float)(Math.log(((1/a1)+Math.sqrt((1/a1*a1)+1)))));break;
				}
			default:break;
			}
		}
		return rpevalStack.pop().toString();
	}
	
	static String Eval(String str) 
	{
				TokenizeInfix(str);
				ConvertToPostfix();
				return Evaluate();
	}

}
