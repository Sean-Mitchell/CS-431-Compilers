package Project3;

import Project3.lexer.*;
import Project3.node.*;
import java.io.*;
import java.util.*;

public class Main{
	static Lexer theLexer;
	static StringBuilder ParseString;
	static Token currentToken;
	
    public static void main(String[] arguments){
        try{        	         	
            // Create a lexer instance.
        	theLexer = new Lexer(new PushbackReader
						(new InputStreamReader(System.in), 1024));
			
        	ParseString = new StringBuilder();
        	
        	System.out.println(RecursiveParse());
			
			// build tree of tokens
			// turn arraylist into stmts

			// interpret
			
        }
        catch(Exception e){ System.out.println(e.getMessage()); }
    }
    
    //Initializes the statement and uses a recursive end to create a list of statements
    private static String RecursiveParse() {
    	String returnString = "new Stmts(" + GetStatement();
    	
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
    		//Reads left paren
        	currentToken = GetNextToken();
        	returnString = "new AssignStmt(" + GetExpression() + ")";
    		//Reads right paren
        	currentToken = GetNextToken();
    		return returnString;
    	}
    	else if (currentToken.getClass().getSimpleName().equals("TPrint")){
    		//Reads left paren
        	currentToken = GetNextToken();
        	returnString = "new PrintStmt(" + GetExpression() + ")";
    		//Reads right paren
        	currentToken = GetNextToken();
    		return returnString;
    	}
    	return"";
    }
    
    //Gets the type of expression
    private static String GetExpression() {
    	currentToken = GetNextToken();
    	System.out.println(currentToken.getClass());
    	if (currentToken.getClass().getSimpleName().equals("TNumber")) {
    		return "new NumExp(" + currentToken.getText()  + ")";
    	}
    	else if (currentToken.getClass().getSimpleName().equals("TId")){
    		return "new IdExp(" + currentToken.getText() + ")";
    	}
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