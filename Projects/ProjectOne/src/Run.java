package Starter;

import java.io.*;
import java.util.*;

public class Run {
	public static void main(String[] args) {
		System.out.println("Enter file name");
		Scanner in = new Scanner(System.in);
		String fileName = in.next();
		Token[] tokens = TokenScanner.scanFile(fileName);
	}
}