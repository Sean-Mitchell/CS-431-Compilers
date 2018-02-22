package Starter;

import java.io.*;
import java.util.*;

public class Scanner {
	public Token[] scanFile(String fileName) {
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		
		ArrayList<Token> tokenList = new ArrayList<>();
		Token next = getNextToken(fr);
		
		while (next != null) {
			tokenList.add(next);
			next = getNextToken(fr);
		}
		
		return tokenList;
	}
	
	public Token getNextToken(FileReader fr) {
		//TODO
	}
}