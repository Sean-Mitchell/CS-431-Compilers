package Project3;

import Project3.lexer.*;
import Project3.node.*;
import java.io.*;

public class Main{

    public static void main(String[] arguments){
            try{        	         	
                // Create a lexer instance.
                Lexer l = new Lexer(new PushbackReader
							(new InputStreamReader(System.in), 1024));
							
				ArrayList<Stmt> statementList = new ArrayList<>();

                Token t = l.next();
                while (!t.getText().equals("")){
                			if (!t.getClass().getSimpleName().equals("TBlank")) {
                				// add token to array
								
                			}

                        t = l.next();
                }
				// build tree of tokens
				// turn arraylist into stmts

				// interpret
				
            }
            catch(Exception e){ System.out.println(e.getMessage()); }
    }
}