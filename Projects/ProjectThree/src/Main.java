package Project3;

import Project3.lexer.*;
import Project3.node.*;
import java.io.*;
import java.util.*;

public class Main{
	static Lexer theLexer;
	static Token currentToken;
	
    public static void main(String[] arguments){
        try{        	         	
            // Create a lexer instance.
        	theLexer = new Lexer(new PushbackReader
						(new InputStreamReader(System.in), 1024));
			

        	StringBuilder ParseString = new StringBuilder();
        	//get string of objects
        	ParseString.append("package Project3;\n" + 
        	"\n" + 
        	"public class ProgExpr{\n" + 
        	"  	private static Stmts program = ");
        	
        	ParseString.append(RecursiveParse());
        	
        	ParseString.append(";\n" + 
        	"\n" + 
        	"	public static void main(String[] args) {\n" + 
        	"	    Interpreter interpreter = new Interpreter();\n" + 
        	"	    System.out.println(\"Evaluating...\");\n" + 
        	"	    interpreter.interpret(program);\n" + 
        	"	}\n" + 
        	"}\n" + 
        	"");
        	
        	System.out.println(ParseString.toString());
        	Writer f = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("src/ProgExpr.java"))));
        	f.write(ParseString.toString());
      		f.close();
			
        }
        catch(Exception e){ System.out.println("There was an error parsing your input"); }
    }
    
    //Initializes the statement and uses a recursive end to create a list of statements
    private static String RecursiveParse() {
    	String returnString = "new ContinuingStmts(" + GetStatement();
    	
    	//Keep going, there are other statements to parse
    	currentToken = GetNextToken();
    	if (currentToken.getClass().getSimpleName().equals("TSemicolon")) {
    		returnString += ", " + RecursiveParse();
    	}
    	
    	returnString += ")";
    	return returnString;
    }
    
    //Get's the type of statement
    private static String GetStatement() {
    	String returnString;
    	//Gets the first token
    	currentToken = GetNextToken();
    	if (currentToken.getClass().getSimpleName().equals("TId")) {
        	returnString = "new AssignStmt(" + GetExpression() + ")";
    		//Reads right paren
        	currentToken = GetNextToken();
    		return returnString;
    	}
    	else if (currentToken.getClass().getSimpleName().equals("TPrint")){
    		//Reads left paren
        	currentToken = GetNextToken();
        	returnString = "new PrintStmt(new ContinuingExpList(" + GetExpression() + "))";
    		//Reads right paren
        	currentToken = GetNextToken();
    		return returnString;
    	}
    	return"";
    }
    
    //Gets the type of expression
    private static String GetExpression() {
    	String returnString;
    	currentToken = GetNextToken();
    	//System.out.println(currentToken.getClass());
    	
    	if (currentToken.getClass().getSimpleName().equals("TNumber")) {
    		return "new NumExp(" + currentToken.getText()  + ")";
    	}
    	else if (currentToken.getClass().getSimpleName().equals("TId")){
        	returnString = "new IdExp(" + currentToken.getText() + ")";
    		return returnString;
    	}
    	
    	//Ran out of time to finish this guy
    	/*else if (currentToken.getClass().getSimpleName().equals("TBinOp")){
    		return "new BinOp(" + GetBinOp() + ")";
    	}
    	else if (currentToken.getClass().getSimpleName().equals("TUnaryOp")){
    		return "new UnaryOp(" + GetUnaryOp + ")";
    	}*/
    	return "";
    }
    
    //Get's next token
    private static Token GetNextToken() {
    	Token nextToken = null;
    	try{
    		nextToken = theLexer.next();
    		//Gets tokens until it's not blank
	    	while (nextToken.getClass().getSimpleName().equals("TBlank")) {
	    		nextToken = theLexer.next();
	    	}
    	} catch (Exception e) {}
    	return nextToken;
    }
}