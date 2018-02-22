package Starter;

import java.io.*;
import java.util.*;

public class Scanner {
	private static ArrayList<Token> validTokens = new ArrayList<>();
	
	public Token[] scanFile(String fileName) {
		initializeValidTokens();
		
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
	
	private Token getNextToken(FileReader fr) {
		String partialToken = "";
		
		partialToken = partialToken + fr.next();
		Token[] possibleTokens = getPossibleTokens(partialToken);
		
		while (possibleTokens.size() > 0) {
			for (int i = 0; i < possibleTokens.size(); i++) {
				if (possibleTokens.get(i).value.equals(partialToken)) {
					//needs a lot more than just this like adding variables and shit, this is just to get the ball rollin
					return new Token(possibleTokens.get(i).value, possibleTokens.get(i).type);
				}
			}
		}
	}
	
	private Token[] getPossibleTokens(String partialToken) {
		ArrayList<Token> possibleTokens = new ArrayList<>();
		
		for (int i = 0; i < validTokens.size(); i++) {
			if (validTokens.get(i).substring(0, partialToken.length()).equals(partialToken)) {
				possibleTokens.add(validTokens.get(i));
			}
		}
		
		return possibleTokens.toArray();
	}
	
	private void initializeValidTokens() {
		validTokens.add(new Token(0, "class"));
		validTokens.add(new Token(1, "public"));
		validTokens.add(new Token(2, "static"));
		validTokens.add(new Token(3, "void"));
		validTokens.add(new Token(4, "main"));
		validTokens.add(new Token(5, "String"));
		validTokens.add(new Token(6, "{"));
		validTokens.add(new Token(7, "}"));
		validTokens.add(new Token(8, "["));
		validTokens.add(new Token(9, "]"));
		validTokens.add(new Token(10, "("));
		validTokens.add(new Token(11, ")"));
		validTokens.add(new Token(12, "extends"));
		validTokens.add(new Token(14, ";"));
		validTokens.add(new Token(15, "return"));
		validTokens.add(new Token(16, "int"));
		validTokens.add(new Token(17, "boolean"));
		validTokens.add(new Token(18, "if"));
		validTokens.add(new Token(19, "else"));
		validTokens.add(new Token(20, "while"));
		validTokens.add(new Token(21, "System.out.println"));
		validTokens.add(new Token(22, "="));
		validTokens.add(new Token(23, "&&"));
		validTokens.add(new Token(24, "<"));
		validTokens.add(new Token(25, "+"));
		validTokens.add(new Token(26, "-"));
		validTokens.add(new Token(27, "*"));
		validTokens.add(new Token(28, "."));
		validTokens.add(new Token(29, "length"));
		validTokens.add(new Token(30, ","));
		validTokens.add(new Token(31, "true"));
		validTokens.add(new Token(32, "false"));
		validTokens.add(new Token(33, "this"));
		validTokens.add(new Token(34, "new"));
		validTokens.add(new Token(35, "!"));
		validTokens.add(new Token(36, "//"));
		validTokens.add(new Token(37, "/*"));
		validTokens.add(new Token(38, "*/"));
	}
}