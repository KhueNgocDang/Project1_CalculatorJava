package Calculators;


import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;



public class EvaluateString 
{
	static LinkedList<Token> InfixQueue = new LinkedList<Token>();
	static LinkedList<Token> PostfixQueue = new LinkedList<Token>();
	
	static String display(LinkedList<?> l)
	{
		String s = new String("<html>");
		for (ListIterator<?> it = l.listIterator(0); it.hasNext(); ) 
		{
			Object t = it.next();
			String tts=t.toString();
			if (tts.equals("*")) s+="&times;";
			else if (tts.equals("/")) s+="&divide;";
			else if (tts.equals("-")) s+="&minus;";
			else s+=tts;
			s+="&nbsp;";
		}
		return s+"</html>";
	}
	
	public static String TokenizeInfix(String expression) 
	{
		char[] token = expression.toCharArray();
		String numstr;
		InfixQueue.clear();
		for(int i = 0; i < token.length; i++)
		{	
			switch(token[i]) 
			{
			case ' ' : continue;
        	case '0' :case '1' :case '2' :case '3' :case '4' :case 'u':
        	case '5' :case '6' :case '7' :case '8' :case '9' :case '.':
        		{
    				numstr=new String();
    				if(token[i]=='u') numstr=numstr+'-';
    				else
    				numstr=numstr+Character.toString(token[i]);
    				i++;
    				while (i<expression.length() && isNumeric(expression.charAt(i)))
    					{numstr=numstr+Character.toString(expression.charAt(i));i++;}
    				i--;
    				InfixQueue.addLast(new Token(new Float(numstr).floatValue()));
				}break;
        	
        	case '+': case '-': 
        	{
    			if (InfixQueue.isEmpty() || 
    					//InfixQueue.getLast().ttype==Token.TokenType.OPERATOR || 
    						InfixQueue.getLast().ttype==Token.TokenType.BRACKET_LEFT) 
    				{token[i]='u';i--;}
				//InfixQueue.addLast(new Token('u', Token.OpType.UNARY_PREFIX, 100));
				else 
					InfixQueue.addLast(new Token(token[i], Token.OpType.BINARY_LEFT_ASSOC, 50));
    		}break;
        	case '^':
        		InfixQueue.addLast(new Token(token[i], Token.OpType.BINARY_RIGHT_ASSOC, 100));
        		break;
        	case '*': case '/':
        		InfixQueue.addLast(new Token(token[i], Token.OpType.BINARY_LEFT_ASSOC, 60));
        		break;
        	case '(' : InfixQueue.addLast(new Token(Token.TokenType.BRACKET_LEFT));break;
        	case ')' : InfixQueue.addLast(new Token(Token.TokenType.BRACKET_RIGHT));break;
        	default: {
				InfixQueue.clear();
				return("Unexpected character '"+token[i]+"'");
				}
			}
		}
		return display(InfixQueue);
	}
	
	public static boolean isNumeric(char ch) 
	{
		if(Character.isDigit(ch)||ch =='.'||ch =='u') return true;
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
						case UNARY_POSTFIX:OperatorStack.addLast(t);break;
						case UNARY_PREFIX:PostfixQueue.addLast(t);break;
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
		return display(PostfixQueue);
	}
	
	public static String Evaluate() 
	{
		if (PostfixQueue.size()==0) return "";
		Token t;
		float a1;
		LinkedList<Token> postfixQueue = new LinkedList<Token>(PostfixQueue);
		Stack<Float> rpevalStack = new Stack<Float>();
		//rpevalStack.clear();
		while (!postfixQueue.isEmpty()) 
		{
			t=postfixQueue.removeFirst();
			switch(t.ttype) 
			{
			case NUMBER:rpevalStack.push(t.val);break;
			case OPERATOR:
				if(rpevalStack.isEmpty()) {postfixQueue.addLast(t);break;}
				a1 = rpevalStack.pop();
				switch(t.op) 
				{
					case '+': switch(t.otype) 
						{
						case BINARY_LEFT_ASSOC:
							if(rpevalStack.isEmpty())
								rpevalStack.push(a1); 
								else rpevalStack.push(rpevalStack.pop()+a1);break;
						//case UNARY_PREFIX:rpevalStack.push(+rpevalStack.pop());break;
						default:break;
					}break;
					case '-':
						switch(t.otype) 
						{
						case BINARY_LEFT_ASSOC:
							if(rpevalStack.isEmpty())rpevalStack.push(-a1); 
								else rpevalStack.push(rpevalStack.pop()-a1);break;
						//case UNARY_PREFIX:rpevalStack.push(-rpevalStack.pop());break;
						default:break;
						}break;
					case '*':rpevalStack.push(a1*rpevalStack.pop());break;
					case '/':rpevalStack.push(rpevalStack.pop()/a1);break;
					case '^':rpevalStack.push(new Float(Math.pow(rpevalStack.pop(),a1)));break;
				}break;
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

	public static void main(String[] args) 
    { 
		
      // System.out.println(EvaluateString.Eval("10.5 + 2 ^ 6")); 
       // System.out.print(EvaluateString.Eval(" 10.5+----2^6"));
        System.out.print(EvaluateString.Eval(" 10.5+-(-2)^6"));
       // System.out.println(EvaluateString.Eval("100 * 2 + 12")); 
        //System.out.println(EvaluateString.Eval("100.8^9 * ( 2 + 12 )")); 
       // System.out.println(EvaluateString.Eval("100 * ( 2 + 12 ) / 14")); 
    } 
}
