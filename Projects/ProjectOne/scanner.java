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
		String partialToken = fr.read();;
		
		while (partialToken.trim().length() == 0) {// Whitespace check
			partialToken = fr.read();
		}
		
		if (partialToken == null) { // EOF check
			return null;
		}
		
		Token[] possibleTokens = getPossibleTokens(partialToken);
		
		while (possibleTokens.size() > 0) {
			for (int i = 0; i < possibleTokens.size(); i++) {
				if (possibleTokens.get(i).value.equals(partialToken)) {
					return new Token(possibleTokens.get(i).type, possibleTokens.get(i).value, possibleTokens.get(i).token);
				}
			}
			
			String next = fr.read();
			
			if (next == null || next.trim().length() == 0 ) { // EOF and Whitespace check
				break;
			}
			
			partialToken = partialToken + next;
			possibleTokens = getPossibleTokens(partialToken);
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
		validTokens.add(new Token(0, "class", "<TClass>"));
		validTokens.add(new Token(1, "public", "<TPublic>"));
		validTokens.add(new Token(2, "static", "<TStatic>"));
		validTokens.add(new Token(3, "void", "<TVoid>"));
		validTokens.add(new Token(4, "main", "<TMain>"));
		validTokens.add(new Token(5, "String", "<TString>"));
		validTokens.add(new Token(6, "{", "<TLcurly>"));
		validTokens.add(new Token(7, "}", "<TRcurly>"));
		validTokens.add(new Token(8, "[", "<TLbracket>"));
		validTokens.add(new Token(9, "]", "<TRbracket>"));
		validTokens.add(new Token(10, "(", "<TRparen>"));
		validTokens.add(new Token(11, ")", "<TLparen>"));
		validTokens.add(new Token(12, "extends", "<TExtends>"));
		validTokens.add(new Token(14, ";", "<TSemicolon>"));
		validTokens.add(new Token(15, "return", "<TReturn>"));
		validTokens.add(new Token(16, "int", "<TInt>"));
		validTokens.add(new Token(17, "boolean", "<TBool>"));
		validTokens.add(new Token(18, "if", "<TIf>"));
		validTokens.add(new Token(19, "else", "<TElse>"));
		validTokens.add(new Token(20, "while", "<TWhile>"));
		validTokens.add(new Token(21, "System.out.println", "<TPrint>"));
		validTokens.add(new Token(22, "=", "<TEqual>"));
		validTokens.add(new Token(23, "&&", "<TAnd>"));
		validTokens.add(new Token(24, "<", "<TLt>"));
		validTokens.add(new Token(25, "+", "<TPlus>"));
		validTokens.add(new Token(26, "-", "<TMinus>"));
		validTokens.add(new Token(27, "*", "<TTimes>"));
		validTokens.add(new Token(28, ".", "<TPeriod>"));
		validTokens.add(new Token(29, "length", "<TLength>"));
		validTokens.add(new Token(30, ",", "<TComma>"));
		validTokens.add(new Token(31, "true", "<TTrue>"));
		validTokens.add(new Token(32, "false", "<TFalse>"));
		validTokens.add(new Token(33, "this", "<TThis>"));
		validTokens.add(new Token(34, "new", "<TNew>"));
		validTokens.add(new Token(35, "!", "<TExc>"));
		validTokens.add(new Token(36, "//", "<TComments>"));
		validTokens.add(new Token(37, "/*", "<TComments>"));
		validTokens.add(new Token(38, "*/", ""));
		validTokens.add(new Token(39, "<TInt><TPeriod><TInt>", "<TReal>"));
	}
}