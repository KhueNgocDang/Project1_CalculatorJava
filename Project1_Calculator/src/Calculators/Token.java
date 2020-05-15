package Calculators;

public class Token {
	String op;
	float val;
	int precidence;
	enum OpType {UNARY_PREFIX,UNARY_POSTFIX,BINARY_LEFT_ASSOC,BINARY_RIGHT_ASSOC};
	enum TokenType {NUMBER,OPERATOR,FUNC,BRACKET_LEFT,BRACKET_RIGHT};
	OpType otype;
	TokenType ttype;
	
	Token(String o, OpType t, int prec)
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
