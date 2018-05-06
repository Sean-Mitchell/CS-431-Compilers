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
            	
            	//Apply the traversal
                ast.apply(assembly);
                
                //Combine the assembly and data parts of the mips program and output them to
                //the file that was passed in (normally the prog name defined in build.xml)
                outputToFile(arguments[0], assembly.mainAssembly.toString() + assembly.dataAssembly.toString());
            } else {
            	System.out.println("Parsing Errors.");
            }
      }
      catch(Exception e){ System.out.println("Error: " + e.getMessage()); }
   }
   
   // Method to output assembly code to a file
   // At the moment will only write the output to one file
   // This is dependent on what is passed into the args in the ant build file
   private static void outputToFile(String outputFileName, String outputString) {
      try {
         Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output/" + outputFileName), "utf-8"));
		 writer.write(outputString);
	     writer.close();
	  } catch (IOException e) {
		  e.printStackTrace();
	  }
   }
}