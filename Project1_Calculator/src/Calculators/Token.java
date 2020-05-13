package Calculators;

public class Token {
	char op;
	float val;
	int precidence;
	enum OpType {UNARY_PREFIX,UNARY_POSTFIX,BINARY_LEFT_ASSOC,BINARY_RIGHT_ASSOC};
	enum TokenType {NUMBER,OPERATOR,BRACKET_LEFT,BRACKET_RIGHT};
	OpType otype;
	TokenType ttype;
	static final String IDENTIFIER = "MyClass";
	
	Token(char o, OpType t, int prec)
	{
		op = o;
		ttype = TokenType.OPERATOR;
		otype = t;
		precidence = prec;
	}
	
	Token(float f)
	{
		ttype = TokenType.NUMBER;
		val = f;
	}
	
	Token(TokenType t)
	{
		if (t == TokenType.BRACKET_LEFT|| t == TokenType.BRACKET_RIGHT) ttype = t;
	}
	
	public String toString() 
	{
		switch(ttype) 
		{
		case OPERATOR: return String.valueOf(op);
		case NUMBER: 
			{
				if(Math.floor(val)==val) return new Integer((int)val).toString();
				else return String.valueOf(val);
			}
		case BRACKET_LEFT: return "(";
		case BRACKET_RIGHT: return ")";
		default: return "";
		}
	}
	
}
