package Starter;

import java.io.*;
import java.util.*;


public class Run {
	public static void main(String[] args) {
		System.out.println(args[0]);
		String fileName = args[0];
		Token[] tokens = TokenScanner.scanFile(args[0]);
				
		
		//Output tokens to file
		String fileToWriteTo = fileName.substring(0,fileName.indexOf(".")) + ".answer";
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