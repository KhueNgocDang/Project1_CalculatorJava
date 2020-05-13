package Calculators;


import java.util.Stack;



public class EvaluateString 
{
	public static double evaluate(String expression) 
	{
		char[] token = expression.toCharArray();
		Stack<Double> numbers = new Stack<Double>();
		Stack<Character> operators = new Stack<Character>();
		for(int i = 0; i < token.length; i++)
		{	
			switch(token[i]) 
			{
			case ' ' : continue;
        	case '0' :case '1' :case '2' :case '3' :case '4' :
        	case '5' :case '6' :case '7' :case '8' :case '9' :case '.':
        		{
        			StringBuffer numstr = new StringBuffer();
        			while (i<expression.length()&&isNumeric(token[i])) 
        				numstr.append(token[i++]);
        			numbers.push(Double.parseDouble(numstr.toString()));
				}break;
        	case '+': case '-': 
        		{
        			/*if(numbers.empty()||
        			//		//!isNumeric(expression.charAt(i-1))||
        							 expression.charAt(i-1) == '(')
        			//	numbers.push(Unary(token[i],numbers.pop()));
        			else */
        			//{
        				while (!operators.empty()&&Precedence(token[i],operators.peek()))
        					numbers.push(Operand(operators.pop(),numbers.pop(),numbers.pop()));
        				operators.push(token[i]);
        			//}
        		}break;
        	case '^':case '*': case '/':
        		{
				while (!operators.empty()&&Precedence(token[i],operators.peek()))
					numbers.push(Operand(operators.pop(),numbers.pop(),numbers.pop()));
				operators.push(token[i]);
        		}break;
        	case '(' : operators.push(token[i]);break;
        	case ')' : 
        		{
				while (operators.peek()!= '(')
					numbers.push(Operand(operators.pop(),numbers.pop(),numbers.pop()));
				operators.pop();
        		}break;
			}
		}
		while (!operators.empty())
			numbers.push(Operand(operators.pop(),numbers.pop(),numbers.pop()));
		return numbers.pop();	
	}
	
	public static boolean isNumeric(char ch) 
	{
		if(Character.isDigit(ch)||ch =='.') return true;
		return false;
	}
	
	public static boolean Precedence(char op1, char op2) 
	{
		if(op2=='('||op2 == ')') return false;
		if((op1=='*'||op1=='/')&&(op2=='+'||op2=='-'))return false;
		if((op1=='^'||op1=='l')&&(op2=='+'||op2=='-'||op2=='*'||op2=='/'))return false;
		else return true;
	}
	
	public static double Operand(char op, double b, double a) 
	{
		switch(op) 
		{
		case'+':return a+b;
		case'-':return a-b;
		case'*':return a*b;
		case'/':if(b==0) throw new UnsupportedOperationException("Syntax Error");
				return a/b;
		case'^':return Math.pow(a,b);
		}
		return 0;
	}
	
	public static double Unary(char op, double a) 
	{
		switch(op) 
		{
		case'+':return +a;
		case'-':return -a;
		}
		return 0;
	}
	
	public static void main(String[] args) 
    { 
        System.out.println(EvaluateString.evaluate("10.5 + 2 ^ 6")); 
        System.out.println(EvaluateString.evaluate("100 * 2 + 12")); 
        System.out.println(EvaluateString.evaluate("100.8^9 * ( 2 + 12 )")); 
        System.out.println(EvaluateString.evaluate("100 * ( 2 + 12 ) / 14")); 
    } 
}
