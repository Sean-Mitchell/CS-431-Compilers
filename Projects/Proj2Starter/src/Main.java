package Project2;

import Project2.lexer.*;
import Project2.node.*;
import java.io.*;

public class Main{

    public static void main(String[] arguments){
            try{        	         	
            	StringBuffer sb = new StringBuffer();
                // Create a lexer instance.
                Lexer l = new Lexer(new PushbackReader
							(new InputStreamReader(System.in), 1024));

                Token t = l.next();
                while (!t.getText().equals("")){
                			if (!t.getClass().getSimpleName().equals("TBlank")) {
                				sb.append("<"+t.getClass().getSimpleName()+">");
                			}

                        t = l.next();
                }

					Writer f = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(arguments[0]))));
            	f.write(sb.toString());
          		f.close();

            }
            catch(Exception e){ System.out.println(e.getMessage()); }
    }
}