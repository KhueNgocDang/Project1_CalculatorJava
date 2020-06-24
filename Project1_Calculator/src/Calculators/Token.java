package Calculators;

public class Token {
	String op;
	float val;
	int valprog;
	String func;
	int precidence;
	enum OpType {BINARY_LEFT_ASSOC,BINARY_RIGHT_ASSOC,UNARY_PREFIX,UNARY_POSTFIX};
	enum TokenType {NUMBER,OPERATOR,FUNC,BRACKET_LEFT,BRACKET_RIGHT};
	enum FuncType{PREFIX,POSTFIX};
	OpType otype;
	TokenType ttype;
	FuncType ftype;
	
	Token(String o, OpType t, int prec)
	{
		op = o;
		ttype = TokenType.OPERATOR;
		otype = t;
		precidence = prec;
	}
	
	Token(String f)
	{
		ttype = TokenType.FUNC;
		func = f;
	}
	
	Token(float f)
	{
		ttype = TokenType.NUMBER;
		val = f;
	}
	
	Token(int f)
	{
		ttype = TokenType.NUMBER;
		valprog = f;
	}
	
	Token(TokenType t)
	{
		if (t == TokenType.BRACKET_LEFT|| t == TokenType.BRACKET_RIGHT) ttype = t;
	}
	
	public String toString() 
	{
		switch(ttype) 
		{
		case OPERATOR: return op;
		case FUNC: return func;
		case NUMBER: 
			{
				if(Math.floor(val)==val) return Integer.toString(Math.round(val));
				else return String.valueOf(val);
			}
		case BRACKET_LEFT: return "(";
		case BRACKET_RIGHT: return ")";
		default: return "";
		}
	}
	
}
