package Project5;

import Project5.lexer.*;
import Project5.node.*;
import Project5.parser.*;
import java.io.*;

public class Main{

   public static void main(String[] arguments){
      try{
            Lexer lexer = new Lexer(new PushbackReader
                  (new InputStreamReader(System.in), 1024));

            Parser parser = new Parser(lexer);
            
            Start ast = parser.parse();
            PrintTree printTree = new PrintTree();
            ast.apply(printTree);  //this is what gets the depth first search going
            
            
            if (!printTree.errorFound) {
            	//In here do the assembly traversal and write the string to a file
                //start the assembly bois off
                AssemblyWriter assembly = new AssemblyWriter(printTree.symbolTable);
                ast.apply(assembly);
            } else {
            	System.out.println("Parsing Issues.");
            }
      }
      catch(Exception e){ System.out.println("Error: " + e.getMessage()); }
   }
}