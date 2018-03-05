package Starter;

import java.io.*;
import java.util.*;

//import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.HeaderTokenizer.Token;

public class Run {
	public static void main(String[] args) {
		System.out.println("Enter file name");
		Scanner in = new Scanner(System.in);
		String fileName = in.next();
		Token[] tokens = TokenScanner.scanFile(fileName);
				
		
		//Output tokens to file
		String fileToWriteTo = fileName.substring(0,fileName.indexOf(".")) + ".ans";
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileToWriteTo), "utf-8"));

			for (int i = 0; i < tokens.length; i++) {
				writer.write(tokens[i].token);
			}
		} catch (IOException ex) {
			// Report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {/* ignore */
			}
		}
	}
}