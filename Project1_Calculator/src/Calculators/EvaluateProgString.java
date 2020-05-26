package Calculators;

import java.util.LinkedList;
import java.util.Stack;

public class EvaluateProgString {
	static LinkedList<Token> InfixQueue = new LinkedList<Token>();
	static LinkedList<Token> PostfixQueue = new LinkedList<Token>();
	static char[] num= {'A','B','C','D','E','F',
			'0','1','2','3','4','5','6','7','8','9'};
	
	@SuppressWarnings("deprecation")
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
			case 'l':case 'h':case 'n':case 'o':case 'r':case 's':
			case 't':case 'v':case 'w':case 'x':case 'y':case 'z':
			{
				func=new String();
				func=func+Character.toString(token[i]);
				i++;
				while (i<expression.length() && isLetter(expression.charAt(i)))
					{func=func+Character.toString(expression.charAt(i));i++;}
				i--;
				switch(func) 
				{
				case"and":case"or":case"not":case"nand":case"nor":case"xor":
				case"lsh":case"rsh": case"mod":
					InfixQueue.addLast(new Token(func.toString(), Token.OpType.BINARY_RIGHT_ASSOC, 50));
        		break;
				}
			}break;
			case 'A' :case 'B' :case 'C' :case 'D' :case 'E' :case 'F':
        	case '0' :case '1' :case '2' :case '3' :case '4' :case 'u':
        	case '5' :case '6' :case '7' :case '8' :case '9' :case '.':
        		{
    				numstr=new String();
    				long nums = 0 ;
    				if(token[i]=='u') numstr=numstr+'-';
    				else
    				numstr=numstr+Character.toString(token[i]);
    				i++;
    				while (i<expression.length() && isNumeric(expression.charAt(i)))
    					{numstr=numstr+Character.toString(expression.charAt(i));i++;}
    				i--;
    				nums = Long.decode(numstr);
					InfixQueue.addLast(new Token(new Long(nums).longValue()));
				}break;
        	
        	case '+': case '-': 
        	{
        		
    			if (InfixQueue.isEmpty() || 
    					//InfixQueue.getLast().ttype==Token.TokenType.OPERATOR || 
    						InfixQueue.getLast().ttype==Token.TokenType.BRACKET_LEFT) 
    				{token[i]='u';i--;}
				//InfixQueue.addLast(new Token('u', Token.OpType.UNARY_PREFIX, 100));
				else 
					InfixQueue.addLast(new Token(String.valueOf(token[i]), Token.OpType.BINARY_LEFT_ASSOC, 50));
    		}break;
        	case'%':					
        		InfixQueue.addLast(new Token(String.valueOf(token[i])));break;
        	case '*': case '/':
        		InfixQueue.addLast(new Token(String.valueOf(token[i]), Token.OpType.BINARY_LEFT_ASSOC, 60));
        		break;
        	case '(' : InfixQueue.addLast(new Token(Token.TokenType.BRACKET_LEFT));break;
        	case ')' : InfixQueue.addLast(new Token(Token.TokenType.BRACKET_RIGHT));break;
        	default: {
				InfixQueue.clear();
				return("Unexpected character '"+token[i]+"'");
				}
			}
			
		}
		//return display(InfixQueue);
		return InfixQueue.toString();
	}

	private static boolean isNumeric( char ch) 
	{
		for(char s: num) 
		{
			if(s == ch) return true;
		}
		return false;
	}

	private static boolean isLetter(char ch) {
		if(Character.isLetter(ch)) return true;
		return false;
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
		//return display(PostfixQueue);
		return PostfixQueue.toString();
	}
	
	public static String Evaluate() 
	{
		if (PostfixQueue.size()==0) return "0";
		Token t;
		long a1;
		LinkedList<Token> postfixQueue = new LinkedList<Token>(PostfixQueue);
		Stack<Long> rpevalStack = new Stack<Long>();
		//rpevalStack.clear();
		while (!postfixQueue.isEmpty()) 
		{
			t=postfixQueue.removeFirst();
			switch(t.ttype) 
			{
			case NUMBER:rpevalStack.push(t.valprog);break;
			case OPERATOR:
				if(rpevalStack.isEmpty()) {postfixQueue.addLast(t);break;}
				a1 = rpevalStack.pop();
				switch(t.op) 
				{
					case "+": switch(t.otype) 
						{
						case BINARY_LEFT_ASSOC:
							if(rpevalStack.isEmpty())
								rpevalStack.push(a1); 
								else rpevalStack.push(rpevalStack.pop()+a1);break;
						//case UNARY_PREFIX:rpevalStack.push(+rpevalStack.pop());break;
						default:break;
					}break;
					case "-":
						switch(t.otype) 
						{
						case BINARY_LEFT_ASSOC:
							if(rpevalStack.isEmpty())rpevalStack.push(-a1); 
								else rpevalStack.push(rpevalStack.pop()-a1);break;
						//case UNARY_PREFIX:rpevalStack.push(-rpevalStack.pop());break;
						default:break;
						}break;
					case "*":rpevalStack.push(a1*rpevalStack.pop());break;
					case "/":rpevalStack.push(rpevalStack.pop()/a1);break;
					}break;
			case FUNC: 
				if(rpevalStack.isEmpty()) {postfixQueue.addLast(t);break;}
				a1 = rpevalStack.pop();
				switch(t.func) 
				{
					case "%":rpevalStack.push(a1/100);break;
				}
			default:break;
			}
		}
		return rpevalStack.pop().toString();
	}
	
	static String Eval(String str,int mode) 
	{//
		//return
				TokenizeInfix(str);
				//
				//return
				ConvertToPostfix();
	//
				String sout = null;
				String s = Evaluate();
				long out = Long.parseLong(s);
				switch(mode) 
				{
					case 2:sout = Long.toBinaryString(out);break;
					case 8:sout = Long.toOctalString(out);break;
					case 10:sout = Long.toString(out);break;
					case 16:sout = Long.toHexString(out);break;
				}
				return sout;
	}

	public static void main(String[] args) 
    { 
		
      // System.out.println(EvaluateString.Eval("10.5 + 2 ^ 6")); 
       // System.out.print(EvaluateString.Eval(" 10.5+----2^6"));
        //System.out.print(EvaluateString.Eval(" 10.5+-(-2)^6"));
        System.out.println(EvaluateProgString.Eval("5a+b",16)); 
        //System.out.println(EvaluateString.Eval("100.8^9 * ( 2 + 12 )")); 
       // System.out.println(EvaluateString.Eval("100 * ( 2 + 12 ) / 14")); 
    } 
}
