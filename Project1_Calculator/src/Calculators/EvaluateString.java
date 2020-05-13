package Calculators;


import java.util.Stack;

public class EvaluateString 
{
	public static int evaluate(String expression) 
	{
		char[] token = expression.toCharArray();
		// Stack for numbers
        Stack<Integer> numbers = new Stack<Integer>(); 
        // Stack for Operators
        Stack<Character> operators = new Stack<Character>(); 
        for (int i = 0; i < token.length; i++) 
        {
        	switch(token[i]) 
        	{
        	case ' ' : continue;
        	case '0' :case '1' :case '2' :case '3' :case '4' :
        	case '5' :case '6' :case '7' :case '8' :case '9' :
        		 StringBuffer sbuf = new StringBuffer(); 
                 while (i < token.length && token[i] >= '0' && token[i] <= '9') 
                     sbuf.append(token[i++]); 
                 numbers.push(Integer.parseInt(sbuf.toString())); break;
        	case '(' : operators.push(token[i]);break;
        	case ')' : 
        		while (operators.peek() != '(') numbers.push(applyOp(operators.pop(), numbers.pop(), numbers.pop())); 
        		operators.pop(); break;
             
        	case '+': case '-': case '*': case '/': 
        	{ 
				while (!operators.empty() && hasPrecedence(token[i], operators.peek())) 
					numbers.push(applyOp(operators.pop(), numbers.pop(), numbers.pop())); 
				operators.push(token[i]); 
			}
        	}
        }
        while (!operators.empty()) 
        	numbers.push(applyOp(operators.pop(), numbers.pop(), numbers.pop()));  
        return numbers.pop();
	}
	
	public static boolean hasPrecedence(char op1, char op2) 
    { 
        if (op2 == '(' || op2 == ')') 
            return false; 
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) 
            return false; 
        else
            return true; 
    } 
	
	public static int applyOp(char op, int b, int a) 
    { 
        switch (op) 
        { 
        case '+': 
            return a + b; 
        case '-': 
            return a - b; 
        case '*': 
            return a * b; 
        case '/': 
            if (b == 0) 
                throw new
                UnsupportedOperationException("Cannot divide by zero"); 
            else return a / b; 
        } 
        return 0; 
    }
	public static void main(String[] args) 
    { 
        System.out.println(EvaluateString.evaluate("10 + 2 * 6")); 
        System.out.println(EvaluateString.evaluate("100 * 2 + 12")); 
        System.out.println(EvaluateString.evaluate("100 * ( 2 + 12 )")); 
        System.out.println(EvaluateString.evaluate("100 * ( 2 + 12 ) / 14")); 
    } 
}
