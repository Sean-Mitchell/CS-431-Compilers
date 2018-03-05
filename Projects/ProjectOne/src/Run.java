package Starter;

import java.io.*;
import java.util.*;


public class Run {
	public static void main(String[] args) {
		
			Writer writer = null;
		for (int i = 0; i < args.length; i++) {
			
			System.out.println(args[i]);
			String fileName = args[i];
			Token[] tokens = TokenScanner.scanFile(fileName);
					
			
			//Output tokens to file
			String fileToWriteTo = fileName.substring(0,fileName.indexOf(".")) + ".answer";

			try {
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(fileToWriteTo), "utf-8"));

				for (int j = 0; j < tokens.length; j++) {
					writer.write(tokens[j].token);
					if ( i != 0) {
						
				System.out.println(tokens[j].token);
					}
				}
			} catch (IOException ex) {
				// Report
				System.out.println(ex.toString());
			} finally {
				try {
					writer.close();
				} catch (Exception ex) {
				System.out.println(ex.toString());}
			}
		}
	}
}