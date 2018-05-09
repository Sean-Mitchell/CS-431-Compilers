package Project5;

import Project5.analysis.*;
import Project5.node.*;
import java.util.*;
import java.io.*;


class PrintTree extends DepthFirstAdapter
{	
	BufferedWriter w = Project5.Main.w;
	public SymbolTable symbolTable;
	Stack<Variable> varStack;
	Class currentClass;
	Method currentMethod;
	boolean inGlobalScope;
	boolean inClassScope;
	boolean inMethodScope;
	public boolean errorFound;
	public String exprType;

 	public PrintTree() {
		System.out.println("Start of the Printing Action");
		symbolTable = new SymbolTable();
		varStack = new Stack<>();
		currentClass = null;
		currentMethod = null;
		inGlobalScope = true;
		inClassScope = false;
		inMethodScope = false;
		errorFound = false;
		exprType = "";
	}
	
	public void caseAProg(AProg node) {
        node.getClassmethodstmts().apply(this);
	}
	
	public void caseAClasStmtsClassmethodstmts(AClassStmtsClassmethodstmts node) {
		node.getClassmethodstmts().apply(this);
		node.getClassmethodstmt().apply(this);
	}
	
	public void caseAEpsilonClassmethodstmts(AEpsilonClassmethodstmts node) {
		
	}
	
	public void caseAClassDefClassmethodstmt(AClassDefClassmethodstmt node) {
		if(symbolTable.containsClass(node.getId().toString())) {
			System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Class " + node.getId().toString() + " has already been declared.");
			try{
			w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Class " + node.getId().toString() + " has already been declared.");
			w.newLine();}catch(Exception e){}
			errorFound = true;
		}
		
		Class c = new Class(node.getId().toString());
		symbolTable.addClass(c);
		
		inClassScope = true;
		currentClass = c;
		
		node.getMethodstmtseqs().apply(this);
		
		inClassScope = false;
	}
	
	public void caseAMethodDeclClassmethodstmt(AMethodDeclClassmethodstmt node) {
		if (inClassScope) {
			if(currentClass.containsMethod(node.getId().toString())) {
				System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Method " + node.getId().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Method " + node.getId().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			
			Method m = new Method(node.getId().toString(), node.getType().toString());
			
			node.getVarlist().apply(this);
			
			while (!varStack.empty()) {
				m.addParam(varStack.pop());
			}
			
			currentMethod = m;
			inMethodScope = true;
			
			node.getStmtseq().apply(this);
			
			while (!varStack.empty()) {
				m.addVar(varStack.pop());
			}
			
			inMethodScope = false;
			
			currentClass.addMethod(m);
		} else {
			if(symbolTable.containsMethod(node.getId().toString())) {
				System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Method " + node.getId().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Method " + node.getId().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			
			Method m = new Method(node.getId().toString(), node.getType().toString());
			
			node.getVarlist().apply(this);
			
			while (!varStack.empty()) {
				m.addParam(varStack.pop());
			}
			
			currentMethod = m;
			inMethodScope = true;
			
			node.getStmtseq().apply(this);
			
			while (!varStack.empty()) {
				m.addVar(varStack.pop());
			}
			
			inMethodScope = false;
			
			symbolTable.addMethod(m);
		}
	}
	
	public void caseAVarDeclClassmethodstmt(AVarDeclClassmethodstmt node) {
		if (inMethodScope) {
			if (currentMethod.containsVar(node.getId().toString())) {
				System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			
			// Doesn't yet handle more_ids
			currentMethod.addVar(new Variable(node.getId().toString(), node.getType().toString()));
		} else if (inClassScope) {
			if (currentClass.containsVar(node.getId().toString())) {
				System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			
			// Doesn't yet handle more_ids
			currentClass.addVar(new Variable(node.getId().toString(), node.getType().toString()));
		} else {
			if (symbolTable.containsVar(node.getId().toString())) {
				System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			
			// Doesn't yet handle more_ids
			symbolTable.addVar(new Variable(node.getId().toString(), node.getType().toString()));
		}
		
	}
	
	public void caseAMethodStmtsMethodstmtseqs(AMethodStmtsMethodstmtseqs node) {
		node.getMethodstmtseqs().apply(this);
		node.getMethodstmtseq().apply(this);
	}
	
	public void caseAEpsilonMethodstmtseqs(AEpsilonMethodstmtseqs node) {
		
	}
	
	public void caseAMethodDeclMethodstmtseq(AMethodDeclMethodstmtseq node) {
		if (inClassScope) {
			if(currentClass.containsMethod(node.getId().toString())) {
				System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Method " + node.getId().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Method " + node.getId().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			
			Method m = new Method(node.getId().toString(), node.getType().toString());
			
			node.getVarlist().apply(this);
			
			while (!varStack.empty()) {
				m.addParam(varStack.pop());
			}
			
			currentMethod = m;
			inMethodScope = true;
			
			node.getStmtseq().apply(this);
			
			while (!varStack.empty()) {
				m.addVar(varStack.pop());
			}
			
			inMethodScope = false;
			
			currentClass.addMethod(m);
		} else {
			if(symbolTable.containsMethod(node.getId().toString())) {
				System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Method " + node.getId().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Method " + node.getId().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			
			Method m = new Method(node.getId().toString(), node.getType().toString());
			
			node.getVarlist().apply(this);
			
			while (!varStack.empty()) {
				m.addParam(varStack.pop());
			}
			
			currentMethod = m;
			inMethodScope = true;
			
			node.getStmtseq().apply(this);
			
			while (!varStack.empty()) {
				m.addVar(varStack.pop());
			}
			
			inMethodScope = false;
			
			symbolTable.addMethod(m);
		}
	}
	
	public void caseAVarDeclMethodstmtseq(AVarDeclMethodstmtseq node) {
		if (inMethodScope) {
			if (currentMethod.containsVar(node.getId().toString())) {
				System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			
			// Doesn't yet handle more_ids
			currentMethod.addVar(new Variable(node.getId().toString(), node.getType().toString()));
		} else if (inClassScope) {
			if (currentClass.containsVar(node.getId().toString())) {
				System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			
			// Doesn't yet handle more_ids
			currentClass.addVar(new Variable(node.getId().toString(), node.getType().toString()));
		} else {
			if (symbolTable.containsVar(node.getId().toString())) {
				System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			
			// Doesn't yet handle more_ids
			symbolTable.addVar(new Variable(node.getId().toString(), node.getType().toString()));
		}
	}
	
	public void caseAAssignEqualsMethodstmtseq(AAssignEqualsMethodstmtseq node) {
		boolean varFound = false;
		int location = -1;
		
		if (inMethodScope) {
			if (currentMethod.containsVar(node.getId().toString())) {
				varFound = true;
				location = 0;
			}
		}
		
		if (inClassScope && !varFound) {
			if (currentClass.containsVar(node.getId().toString())) {
				varFound = true;
				location = 1;
			}
		}
		
		if (!varFound) {
			if (!symbolTable.containsVar(node.getId().toString())) {
				System.out.println("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: variable " + node.getId().toString() + " has not been declared.");
				try{
				w.write("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: variable " + node.getId().toString() + " has not been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			} else {
				location = 2;
			}
		}
		
		node.getExpr().apply(this);
		
		if (!errorFound) {
			String type = "";
			switch (location) {
				case 0: type = currentMethod.getVar(node.getId().toString()).getType(); break;
				case 1: type = currentClass.getVar(node.getId().toString()).getType(); break;
				case 2: type = symbolTable.getVar(node.getId().toString()).getType(); break;
			}
			
			if (type.equals("REAL")) {
				if (!(exprType.equals("REAL") || exprType.equals("INT") || exprType.equals("BOOLEAN"))) {
					System.out.println("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: cannot assign " + exprType + " to variable of type " + type);
					try{
					w.write("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: cannot assign " + exprType + " to variable of type " + type);
					w.newLine();}catch(Exception e){}
					errorFound = true;
				}
			} else if (type.equals("INT")) {
				if (!(exprType.equals("INT") || exprType.equals("BOOLEAN"))) {
					System.out.println("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: cannot assign " + exprType + " to variable of type " + type);
					try{
					w.write("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: cannot assign " + exprType + " to variable of type " + type);
					w.newLine();}catch(Exception e){}
					errorFound = true;
				}
			} else {
				if (!exprType.equals(type)) {
					if (!(exprType.equals("INT") || exprType.equals("BOOLEAN"))) {
						System.out.println("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: cannot assign " + exprType + " to variable of type " + type);
						try{
						w.write("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: cannot assign " + exprType + " to variable of type " + type);
						w.newLine();}catch(Exception e){}
						errorFound = true;
					}
				}
			}
		}
	}
	
	public void caseAAssignStringMethodstmtseq(AAssignStringMethodstmtseq node) {
		
	}
	
	public void caseAPrintStmtMethodstmtseq(APrintStmtMethodstmtseq node) {
		
	}
	
	public void caseAAssignReadInMethodstmtseq(AAssignReadInMethodstmtseq node) {
		
	}
	
	public void caseAAssignIncMethodstmtseq(AAssignIncMethodstmtseq node) {
		
	}
	
	public void caseAAssignDecMethodstmtseq(AAssignDecMethodstmtseq node) {
		
	}
	
	public void caseADeclObjectMethodstmtseq(ADeclObjectMethodstmtseq node) {
		if (inMethodScope) {
			if (currentMethod.containsVar(node.getLeftSide().toString())) {
				System.out.println("Line " + node.getLeftSide().getLine() + " Pos " + node.getLeftSide().getPos() + " ERROR: Variable " + node.getLeftSide().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getLeftSide().getLine() + " Pos " + node.getLeftSide().getPos() + " ERROR: Variable " + node.getLeftSide().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			currentMethod.addVar(new Variable(node.getLeftSide().toString(), node.getRightSide().toString()));
		} else if (inClassScope) {
			if (currentClass.containsVar(node.getLeftSide().toString())) {
				System.out.println("Line " + node.getLeftSide().getLine() + " Pos " + node.getLeftSide().getPos() + " ERROR: Variable " + node.getLeftSide().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getLeftSide().getLine() + " Pos " + node.getLeftSide().getPos() + " ERROR: Variable " + node.getLeftSide().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			currentClass.addVar(new Variable(node.getLeftSide().toString(), node.getRightSide().toString()));
		} else {
			if (symbolTable.containsVar(node.getLeftSide().toString())) {
				System.out.println("Line " + node.getLeftSide().getLine() + " Pos " + node.getLeftSide().getPos() + " ERROR: Variable " + node.getLeftSide().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getLeftSide().getLine() + " Pos " + node.getLeftSide().getPos() + " ERROR: Variable " + node.getLeftSide().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			symbolTable.addVar(new Variable(node.getLeftSide().toString(), node.getRightSide().toString()));
		}
	}
	
	public void caseAAssignBooleanMethodstmtseq(AAssignBooleanMethodstmtseq node) {
		
	}
	
	public void caseAFirstStmtStmtseq(AFirstStmtStmtseq node) {
		node.getStmt().apply(this);
		node.getStmtseq().apply(this);
	}
	
	public void caseAEpsilonStmtseq(AEpsilonStmtseq node) {
		
	}
	
	public void caseAAssignExprStmt(AAssignExprStmt node) {
		boolean varFound = false;
		int location = -1;
		
		if (inMethodScope) {
			if (currentMethod.containsVar(node.getId().toString())) {
				varFound = true;
				location = 0;
			}
		}
		
		if (inClassScope && !varFound) {
			if (currentClass.containsVar(node.getId().toString())) {
				varFound = true;
				location = 1;
			}
		}
		
		if (!varFound) {
			if (!symbolTable.containsVar(node.getId().toString())) {
				System.out.println("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: variable " + node.getId().toString() + " has not been declared.");
				try{
				w.write("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: variable " + node.getId().toString() + " has not been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			} else {
				location = 2;
			}
		}
		
		node.getExpr().apply(this);
		
		if (!errorFound) {
			String type = "";
			switch (location) {
				case 0: type = currentMethod.getVar(node.getId().toString()).getType(); break;
				case 1: type = currentClass.getVar(node.getId().toString()).getType(); break;
				case 2: type = symbolTable.getVar(node.getId().toString()).getType(); break;
			}
			
			if (type.equals("REAL")) {
				if (!(exprType.equals("REAL") || exprType.equals("INT") || exprType.equals("BOOLEAN"))) {
					System.out.println("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: cannot assign " + exprType + " to variable of type " + type);
					try{
					w.write("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: cannot assign " + exprType + " to variable of type " + type);
					w.newLine();}catch(Exception e){}
					errorFound = true;
				}
			} else if (type.equals("INT")) {
				if (!(exprType.equals("INT") || exprType.equals("BOOLEAN"))) {
					System.out.println("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: cannot assign " + exprType + " to variable of type " + type);
					try{
					w.write("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: cannot assign " + exprType + " to variable of type " + type);
					w.newLine();}catch(Exception e){}
					errorFound = true;
				}
			} else {
				if (!exprType.equals(type)) {
					if (!(exprType.equals("INT") || exprType.equals("BOOLEAN"))) {
						System.out.println("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: cannot assign " + exprType + " to variable of type " + type);
						try{
						w.write("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: cannot assign " + exprType + " to variable of type " + type);
						w.newLine();}catch(Exception e){}
						errorFound = true;
					}
				}
			}
		}
	}
	
	public void caseAAssignStringStmt(AAssignStringStmt node) {
		
	}
	
	public void caseAVarDeclStmt(AVarDeclStmt node) {
		if (inMethodScope) {
			if (currentMethod.containsVar(node.getId().toString())) {
				System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			
			// Doesn't yet handle more_ids
			currentMethod.addVar(new Variable(node.getId().toString(), node.getType().toString()));
		} else if (inClassScope) {
			if (currentClass.containsVar(node.getId().toString())) {
				System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			
			// Doesn't yet handle more_ids
			currentClass.addVar(new Variable(node.getId().toString(), node.getType().toString()));
		} else {
			if (symbolTable.containsVar(node.getId().toString())) {
				System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			
			// Doesn't yet handle more_ids
			symbolTable.addVar(new Variable(node.getId().toString(), node.getType().toString()));
		}
	}
	
	public void caseAIfBlockStmt(AIfBlockStmt node) {
		node.getStmtseq().apply(this);
	}
	
	public void caseAIfElseBlockStmt(AIfElseBlockStmt node) {
		node.getIfBlockStmts().apply(this);
		node.getElseBlockStmts().apply(this);
	}
	
	public void caseAWhileStmt(AWhileStmt node) {
		node.getStmtseq().apply(this);
	}
	
	public void caseAForStmt(AForStmt node) {
		if(node.getForOptionalType().toString().length() == 0) {
			// Need to deal with another scope
			boolean varFound = false;
		
			if (inMethodScope) {
				if (currentMethod.containsVar(node.getId().toString())) {
					varFound = true;
				}
			}
			
			if (inClassScope && !varFound) {
				if (currentClass.containsVar(node.getId().toString())) {
					varFound = true;
				}
			}
			
			if (!varFound) {
				if (!symbolTable.containsVar(node.getId().toString())) {
					System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has not been declared.");
					try{
					w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has not been declared.");
					w.newLine();}catch(Exception e){}
					errorFound = true;
				}
			}
		} else {
			if (inMethodScope) {
				if (currentMethod.containsVar(node.getId().toString())) {
					System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
					try{
					w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
					w.newLine();}catch(Exception e){}
					errorFound = true;
				}
				
				// Doesn't yet handle more_ids
				currentMethod.addVar(new Variable(node.getId().toString(), node.getForOptionalType().toString()));
			} else if (inClassScope) {
				if (currentClass.containsVar(node.getId().toString())) {
					System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
					try{
					w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
					w.newLine();}catch(Exception e){}
					errorFound = true;
				}
				
				// Doesn't yet handle more_ids
				currentClass.addVar(new Variable(node.getId().toString(), node.getForOptionalType().toString()));
			} else {
				if (symbolTable.containsVar(node.getId().toString())) {
					System.out.println("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
					try{
					w.write("Line " + node.getId().getLine() + " Pos " + node.getId().getPos() + " ERROR: Variable " + node.getId().toString() + " has already been declared.");
					w.newLine();}catch(Exception e){}
					errorFound = true;
				}
				
				// Doesn't yet handle more_ids
				symbolTable.addVar(new Variable(node.getId().toString(), node.getForOptionalType().toString()));
			}
		}
		
		node.getStmtseq().apply(this);
	}
	
	public void caseAGetStmt(AGetStmt node) {
		
	}
	
	public void caseAPutStmt(APutStmt node) {
		
	}
	
	public void caseAIncrStmt(AIncrStmt node) {
		
	}
	
	public void caseADecrStmt(ADecrStmt node) {
		
	}
	
	public void caseADeclObjectStmt(ADeclObjectStmt node) {
		if (inMethodScope) {
			if (currentMethod.containsVar(node.getLeftSide().toString())) {
				System.out.println("Line " + node.getLeftSide().getLine() + " Pos " + node.getLeftSide().getPos() + " ERROR: Variable " + node.getLeftSide().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getLeftSide().getLine() + " Pos " + node.getLeftSide().getPos() + " ERROR: Variable " + node.getLeftSide().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			currentMethod.addVar(new Variable(node.getLeftSide().toString(), node.getRightSide().toString()));
		} else if (inClassScope) {
			if (currentClass.containsVar(node.getLeftSide().toString())) {
				System.out.println("Line " + node.getLeftSide().getLine() + " Pos " + node.getLeftSide().getPos() + " ERROR: Variable " + node.getLeftSide().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getLeftSide().getLine() + " Pos " + node.getLeftSide().getPos() + " ERROR: Variable " + node.getLeftSide().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			currentClass.addVar(new Variable(node.getLeftSide().toString(), node.getRightSide().toString()));
		} else {
			if (symbolTable.containsVar(node.getLeftSide().toString())) {
				System.out.println("Line " + node.getLeftSide().getLine() + " Pos " + node.getLeftSide().getPos() + " ERROR: Variable " + node.getLeftSide().toString() + " has already been declared.");
				try{
				w.write("Line " + node.getLeftSide().getLine() + " Pos " + node.getLeftSide().getPos() + " ERROR: Variable " + node.getLeftSide().toString() + " has already been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			}
			symbolTable.addVar(new Variable(node.getLeftSide().toString(), node.getRightSide().toString()));
		}
	}
	
	public void caseAMethodCallStmt(AMethodCallStmt node) {
		
	}
	
	public void caseAMethodCallInClassStmt(AMethodCallInClassStmt node) {
		
	}
	
	public void caseAReturnStmt(AReturnStmt node) {
		
	}
	
	public void caseAAssignBooleanStmt(AAssignBooleanStmt node) {
		
	}
	
	public void caseASwitchStmt(ASwitchStmt node) {
		node.getStmts().apply(this);
		node.getDefaultStmts().apply(this);
		node.getCaseHelper().apply(this);
	}
	
	public void caseAAnotherCaseCaseHelper(AAnotherCaseCaseHelper node) {
		node.getStmtseq().apply(this);
		node.getCaseHelper().apply(this);
	}
	
	public void caseAEpsilonCaseHelper(AEpsilonCaseHelper node) {
		
	}
	
	public void caseABreakBreakHelper(ABreakBreakHelper node) {
		
	}
	
	public void caseAEpsilonBreakHelper(AEpsilonBreakHelper node) {
		
	}
	
	public void caseAMethodCallMethodChainingOption(AMethodCallMethodChainingOption node) {
		
	}
	
	public void caseAEpsilonMethodChainingOption(AEpsilonMethodChainingOption node) {
		
	}
	
	public void caseAIncrForIncrStep(AIncrForIncrStep node) {
		
	}
	
	public void caseADecrForIncrStep(ADecrForIncrStep node) {
		
	}
	
	public void caseAAssignmentForIncrStep(AAssignmentForIncrStep node) {
		
	}
	
	public void caseAForOptionalType(AForOptionalType node) {
		
	}
	
	public void caseAEpsilonForOptionalType(AEpsilonForOptionalType node) {
		
	}
	
	public void caseAMoreIdsMoreIds(AMoreIdsMoreIds node) {
		
	}
	
	public void caseAEpsilonMoreIds(AEpsilonMoreIds node) {
		
	}
	
	public void caseAMoreIdsVarlist(AMoreIdsVarlist node) {
		varStack.push(new Variable(node.getId().toString(), node.getType().toString()));
		node.getMoreVarlist().apply(this);
	}
	
	public void caseAEpsilonVarlist(AEpsilonVarlist node) {
		
	}
	
	public void caseAArrayArrayOption(AArrayArrayOption node) {
		
	}
	
	public void caseAEpsilonArrayOption(AEpsilonArrayOption node) {
		
	}
	
	public void caseAMoreIdsMoreVarlist(AMoreIdsMoreVarlist node) {
		node.getVarlist().apply(this);
	}
	
	public void caseAEpsilonMoreVarlist(AEpsilonMoreVarlist node) {
		
	}
	
	public void caseAVarListVarListTwo(AVarListVarListTwo node) {
		
	}
	
	public void caseAEpsilonVarListTwo(AEpsilonVarListTwo node) {
		
	}
	
	public void caseAExprOrBool(AExprOrBool node) {
		
	}
	
	public void caseABoolExprOrBool(ABoolExprOrBool node) {
		
	}
	
	public void caseAMoreVarListTwo(AMoreVarListTwo node) {
		
	}
	
	public void caseAEpsilonMoreVarListTwo(AEpsilonMoreVarListTwo node) {
		
	}
	
	public void caseAAddExpr(AAddExpr node) {
		node.getExpr().apply(this);
		node.getTerm().apply(this);
	}
	
	public void caseATermExpr(ATermExpr node) {
		node.getTerm().apply(this);
	}
	
	public void caseAMultTerm(AMultTerm node) {
		node.getTerm().apply(this);
		node.getFactor().apply(this);
	}
	
	public void caseAFactorTerm(AFactorTerm node) {
		node.getFactor().apply(this);
	}
	
	public void caseAExprFactor(AExprFactor node) {
		node.getExpr().apply(this);
	}
	
	public void caseANegativeFactor(ANegativeFactor node) {
		node.getFactor().apply(this);
	}
	
	public void caseAIntFactor(AIntFactor node) {
		exprType = "INT";
	}
	
	public void caseARealFactor(ARealFactor node) {
		exprType = "REAL";
	}
	
	public void caseAArrayFactor(AArrayFactor node) {
		node.getArrayOrId().apply(this);
	}
	
	public void caseAIdvarlistFactor(AIdvarlistFactor node) {
		boolean varFound = false;
		int location = -1;
		
		if (inClassScope && !varFound) {
			if (currentClass.containsMethod(node.getId().toString())) {
				varFound = true;
				location = 1;
			}
		}
		
		if (!varFound) {
			if (!symbolTable.containsMethod(node.getId().toString())) {
				System.out.println("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: method " + node.getId().toString() + " has not been declared.");
				try{
				w.write("line " + node.getId().getLine() + " pos " + node.getId().getPos() + " error: method " + node.getId().toString() + " has not been declared.");
				w.newLine();}catch(Exception e){}
				errorFound = true;
			} else {
				location = 2;
			}
		}
		
		String type = "";
		
		switch (location) {
			case 1: type = currentClass.getMethod(node.getId().toString()).getType(); break;
			case 2: type = symbolTable.getMethod(node.getId().toString()).getType(); break;
		}
		
		exprType = type;
	}
	
	public void caseALastFactor(ALastFactor node) {
		
	}
	
	public void caseAArrayArrayOrId(AArrayArrayOrId node) {
		
	}
	
	public void caseAIdArrayOrId(AIdArrayOrId node) {
		
	}
	
	public void caseATrueBoolean(ATrueBoolean node) {
		
	}
	
	public void caseAFalseBoolean(AFalseBoolean node) {
		
	}
	
	public void caseAConditionalBoolean(AConditionalBoolean node) {
		
	}
	
	public void caseABooleanBoolid(ABooleanBoolid node) {
		
	}
	
	public void caseAIdBoolid(AIdBoolid node) {
		
	}
	
	public void caseAPlusAddop(APlusAddop node) {
		
	}
	
	public void caseAMinusAddop(AMinusAddop node) {
		
	}
	
	public void caseATypesType(ATypesType node) {
		
	}
	
	public void caseAIdType(AIdType node) {
		
	}
}