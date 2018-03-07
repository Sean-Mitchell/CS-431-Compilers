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
    
    private static String RecursiveParse() {
    	ParseString.append("new Stmts(" + GetStatement());
    	
    	//Keep going, there are other statements to parse
    	if (currentToken.getClass().getSimpleName().equals("TSemicolon")) {
    		ParseString.append(", " + RecursiveParse());
    	}
    	
    	return ParseString.append(")").toString();
    }
    
    private static String GetStatement() {
    	//Gets the first token
    	currentToken = GetNextToken();
    	if (currentToken.getClass().getSimpleName().equals("TId")) {
    		//Reads left paren
        	currentToken = GetNextToken();
    		return "new AssignStmt(" + GetExpression() + ")";
    	}
    	else if (currentToken.getClass().getSimpleName().equals("TPrint")){
    		//Reads left paren
        	currentToken = GetNextToken();
    		return "new PrintStmt(" + GetExpression() + ")";
    	}
    	return"";
    }
    
    private static String GetExpression() {
    	currentToken = GetNextToken();
    	System.out.println(currentToken.getClass());
    	if (currentToken.getClass().getSimpleName().equals("TNumber")) {
    		return "new NumExp(" + currentToken.getText()  + ")";
    	}
    	else if (currentToken.getClass().getSimpleName().equals("TPrint")){
    		return "new PrintStmt(" + GetExpression() + ")";
    	}
    	return "";
    }
    
    private static Token GetNextToken() {
    	Token nextToken = null;
    	try{
    		nextToken = theLexer.next();
	    	while (nextToken.getClass().getSimpleName().equals("TBlank")) {
	    		nextToken = theLexer.next();
	    	}
    	} catch (Exception e) {}
    	return nextToken;
    }
}