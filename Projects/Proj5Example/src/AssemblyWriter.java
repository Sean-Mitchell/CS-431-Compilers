package Project5;

import Project5.analysis.*;
import Project5.node.*;
import java.util.*;


class AssemblyWriter extends DepthFirstAdapter
{	
	SymbolTable symbolTable;
	Hashtable<String, Integer> varAddressOffsets;
	//% by 7 to keep track of registers used
	int registerCounter;
	//% by 11 for float counters
	int floatRegCounter;
	
	//Assorted counters for labels
	int ifCount;
	int whileCount;
	int caseCount;
	
	//Used for MoreIds
	String variableType;
	
	//Stack that holds variables and whatnot including values?
	Stack<Symbol> varStack = new Stack<Symbol>();
	
	//String based stack that holds the current scope so that we know where to look in the symbol table
	Stack<String> currentScope = new Stack<String>();
	
	//StringBuilders for data and main method
	public StringBuilder mainAssembly;
	public StringBuilder dataAssembly;

 	public AssemblyWriter(SymbolTable symbolTable) {
		System.out.println("Creating assembly code");
		this.symbolTable = symbolTable;
		varAddressOffsets = new Hashtable<>();
		
		registerCounter = 0;
		floatRegCounter = 0;
		ifCount = 0;
		whileCount = 0;
		caseCount = 0;
		
		variableType = "";
		
		mainAssembly = new StringBuilder();
		dataAssembly = new StringBuilder();
		
	}
	
	public void caseAProg(AProg node) {
		//We should probably expand this and make it set up classes and methods and stuff eventually
		//Initial Add stuff
		mainAssembly.append("\t.text\n").append("\t.global main\n").append("main:\n");
		dataAssembly.append("\t.data\n");
        node.getClassmethodstmts().apply(this);
		System.out.println("main\n" + mainAssembly.toString() + "\n" + dataAssembly.toString() );
	}
	
	public void caseAClasStmtsClassmethodstmts(AClassStmtsClassmethodstmts node) {
		node.getClassmethodstmts().apply(this);
		node.getClassmethodstmt().apply(this);
	}
	
	public void caseAEpsilonClassmethodstmts(AEpsilonClassmethodstmts node) {
		
	}
	
	public void caseAClassDefClassmethodstmt(AClassDefClassmethodstmt node) {
		node.getMethodstmtseqs().apply(this);
	}
	
	public void caseAMethodDeclClassmethodstmt(AMethodDeclClassmethodstmt node) {
		
		/*Instead of doing this node stuff,
			Loop through all variables and add them to the stack
			I should say do this for local variables in a method not in the class		
		*/
		
		//Input method name into assembly code
		//Only if it's not the main method
		if (!node.getId().toString().trim().toUpperCase().equals("MAIN"))
			mainAssembly.append(node.getId().toString().trim() + ":");
		
		
		//Get total amount of vars != to strings
		//loop through them and assign them offsets from the $sp
		//Not worrying about other methods/ variables at the moment
		Method thisMethod = symbolTable.getMethod(node.getId().toString().trim());
		
		//gets all the keys (variable names) in method
		Set<String> variableNames = thisMethod.getLocalVariables().keySet();
		
		//push scope onto stack
		currentScope.push(node.getId().toString().trim());
		
		System.out.println("METHOD TEST");
		
		//variable to hold the amount that the stack pointer should be added
		int stackPointerAdded = 0;
		
		for (String key : variableNames) {
			//If the variable is not equal to string, add 4 (bytes) to stack
			//and assign variable $sp offset
			if (!thisMethod.getVar(key).getType().equals("STRING")) {
				thisMethod.getVar(key).setspOffset(stackPointerAdded);
				stackPointerAdded += 4;	
				
				System.out.println(thisMethod.getVar(key).getspOffset());
			} else {
				//Temporary, we still need to get the strings value
				dataAssembly.append(key + ":\t.asciiz\n");
			}
		}
		
		//Make pointer offset grow in the right direction
		stackPointerAdded *= -1;
		
		mainAssembly.append("\taddiu $sp, $sp," + stackPointerAdded + "\n");
			
		node.getVarlist().apply(this);
		node.getStmtseq().apply(this);

	}
	
	public void caseAVarDeclClassmethodstmt(AVarDeclClassmethodstmt node) {
		
		
		if (node.getType().toString().trim().equals("STRING")) {
			dataAssembly.append(node.getId().toString().trim() + ":\t.asciiz\n");
			variableType = "STRING";
			
		} else if (node.getType().toString().trim().equals("INT")) {
			dataAssembly.append(node.getId().toString().trim() + ":\t .word\t 0\n");
			variableType = "INT";
			
		} else if (node.getType().toString().trim().equals("REAL")) {
			dataAssembly.append(node.getId().toString().trim() + ":\t .float\t 0.0\n");
			variableType = "REAL";
			
		} else { //Boolean Boi
			dataAssembly.append(node.getId().toString().trim() + ":\t .word\t 0\n");
			variableType = "BOOLEAN";
		}
		node.getMoreIds().apply(this);
	}
	
	public void caseAMethodStmtsMethodstmtseqs(AMethodStmtsMethodstmtseqs node) {
		node.getMethodstmtseqs().apply(this);
		node.getMethodstmtseq().apply(this);
	}
	
	public void caseAEpsilonMethodstmtseqs(AEpsilonMethodstmtseqs node) {
		
	}
	
	public void caseAMethodDeclMethodstmtseq(AMethodDeclMethodstmtseq node) {
		/*Instead of doing this node stuff,
		Loop through all variables and add them to the stack
		I should say do this for local variables in a method not in the class		
		*/
		
		//set $ra = $fp
		//set $fp = $sp
		//Get total amount of vars != to strings
		//loop through them and assign them offsets from the $sp
		//Not worrying about other methods/ variables at the moment
		Method thisMethod = symbolTable.getMethod(node.getId().toString().trim());
		
		//gets all the keys (variable names) in method
		Set<String> variableNames = thisMethod.getLocalVariables().keySet();
		
		//push scope onto stack
		currentScope.push(node.getId().toString().trim());
		
		System.out.println("METHOD TESTV2");
		
		//variable to hold the amount that the stack pointer should be added
		int stackPointerAdded = 0;
		
		for (String key : variableNames) {
			//If the variable is not equal to string, add 4 (bytes) to stack
			//and assign variable $sp offset
			if (!thisMethod.getVar(key).getType().equals("STRING")) {
				thisMethod.getVar(key).setspOffset(stackPointerAdded);
				stackPointerAdded += 4;	
				
				System.out.println(thisMethod.getVar(key).getspOffset());
			} else {
				//Temporary, we still need to get the strings value
				dataAssembly.append(key + ":\t.asciiz\n");
			}
		}
		
		//Must multiply the stackPointer by -1 so the stack grows correctly
		stackPointerAdded *= -1;
		
		mainAssembly.append("\taddiu $sp, $sp," + stackPointerAdded + "\n");
	
		node.getId().toString();
		node.getType().toString();
		node.getVarlist().apply(this);
		node.getStmtseq().apply(this);
	}
	
	public void caseAVarDeclMethodstmtseq(AVarDeclMethodstmtseq node) {
		node.getId().toString();
	}
	
	public void caseAAssignEqualsMethodstmtseq(AAssignEqualsMethodstmtseq node) {
		node.getId().toString();
		node.getExpr().apply(this);
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
		
		//Currently assume that this is a global scope 
		//TODO: update with scopeStack thingy
		Variable tempVariable = new Variable("wat","no");
		
		//SCOPE BOIS WEW LAD		
		//checks method scope
		if (symbolTable.containsMethod(currentScope.peek()))
		{
			//if the method exists in the global table then get it
			//if that method contains the var we're looking at then add it into the variable symbol table
			if (symbolTable.getMethod(currentScope.peek()).containsVar(node.getId().toString().trim()))
			{
				System.out.println(node.getId().toString());
				tempVariable = symbolTable.getMethod(currentScope.peek()).getVar(node.getId().toString().trim());
			}
			
			//check global
		} else {
			tempVariable = symbolTable.getVar(node.getId().toString());
		}
		

		System.out.println("Variable name and type");
		System.out.println(tempVariable.getName());
		System.out.println(tempVariable.getType());


		Symbol variableSymbol = new Symbol(tempVariable.getName(), tempVariable.getType(), getNextIntRegister());

		varStack.push(variableSymbol);

		if (varStack.peek().getType().equals("STRING"))
		System.out.println(varStack.peek().getStringVal());
		
		node.getExpr().apply(this);
		varStack.pop();

		
	}
	
	public void caseAAssignStringStmt(AAssignStringStmt node) {
		
	}
	
	public void caseAVarDeclStmt(AVarDeclStmt node) {
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

	}
	
	public void caseAGetStmt(AGetStmt node) {
		
	}
	
	public void caseAPutStmt(APutStmt node) {
		node.getId().apply(this);		
		
		//System.out.println(varStack.peek().getRegister());
		//System.out.println(varStack.peek().getType());
		
		Symbol print = varStack.pop();
		if (print.getType().equals("STRING")) {
			mainAssembly.append("\tli  $v0, 4\n");
			mainAssembly.append("\tla  $a0, " + print.stringVal + "\n");
			mainAssembly.append("syscall\n");
			
		} else if (print.getType().equals("INT")) {
			mainAssembly.append("\tli  $v0, 1\n");
			mainAssembly.append("\tla  $a0, (" + print.getRegister() + ")\n");
			mainAssembly.append("syscall\n");
			
		} else if (print.getType().equals("REAL")) {
			mainAssembly.append("\tli  $v0, 2\n");
			mainAssembly.append("\tla  $a0, (" + print.getRegister() + ")\n");
			mainAssembly.append("syscall\n");
			
		} else {
			
		}
	}
	
	public void caseAIncrStmt(AIncrStmt node) {
		
	}
	
	public void caseADecrStmt(ADecrStmt node) {
		
	}
	
	public void caseADeclObjectStmt(ADeclObjectStmt node) {

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
	
	//uses global variable type to decide what type of id it is
	public void caseAMoreIdsMoreIds(AMoreIdsMoreIds node) {
		
		/*
		 * 
		 * 
		 * TODO: THIS IS WRONG
		 * I am making ints, reals, and bools global when they may not be
		 * add scope variable so that these variables are added ONLY when they are
		 * class level variables.
		 * 
		 * 
		 */
		
		if (variableType.equals("STRING")) {
			dataAssembly.append(node.getId().toString().trim() + ":\t.asciiz\n");
			
		} else if (variableType.equals("INT")) {
			dataAssembly.append(node.getId().toString().trim() + ":\t .word\t 0\n");
			
		} else if (variableType.equals("REAL")) {
			dataAssembly.append(node.getId().toString().trim() + ":\t .float\t 0.0\n");
			
		} else { //Boolean Boi
			dataAssembly.append(node.getId().toString().trim() + ":\t .word\t 0\n");
		}
		node.getMoreIds().apply(this);
	}
	
	//Resets what type of variable is being created
	public void caseAEpsilonMoreIds(AEpsilonMoreIds node) {
		variableType = "";
	}
	
	public void caseAMoreIdsVarlist(AMoreIdsVarlist node) {

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
		
	}
	
	public void caseATermExpr(ATermExpr node) {
		node.getTerm().apply(this);
		
	}
	
	public void caseAMultTerm(AMultTerm node) {
		//Start the var declarations wew lad
		//this probably isn't right but it's a start
		/*
		if(node.getTerm().getType().toString().equals("INT")) {
			
		} else if(node.getTerm().getType().toString().equals("REAL")) {
		
		} else if(node.getTerm().getType().toString().equals("STRING")) {
			
		} else if(node.getTerm().getType().toString().equals("BOOLEAN")) {
			
		}
		*/
	}
	
	public void caseAFactorTerm(AFactorTerm node) {
		node.getFactor().apply(this);
		
	}
	
	public void caseAExprFactor(AExprFactor node) {
		
	}
	
	//li cause it's an int
	public void caseAIntFactor(AIntFactor node) {
		System.out.println("did we get here?");
		
		mainAssembly.append("\tli\t" + varStack.peek().getRegister() + "\t" + node.getInt().toString().trim() + "\n");	
		varStack.peek().setInt(Integer.parseInt(node.getInt().toString().trim()));
	}
	
	//li.s because it's a floating point
	public void caseARealFactor(ARealFactor node) {
		//I think this should be pushed on the stack, uncertain atm
		
		mainAssembly.append("\t\tli.s\t" + varStack.peek().getRegister() + "\t" + node.getReal().toString().trim() + "\n");		
	}
	
	public void caseAArrayFactor(AArrayFactor node) {
		
	}
	
	public void caseAIdvarlistFactor(AIdvarlistFactor node) {
		
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
		//Start the var declarations wew lad
		//
		/*
		if(node.getType().toString().equals("INT")) {
			
		} else if(node.getType().toString().equals("REAL")) {
		
		} else if(node.getType().toString().equals("STRING")) {
			
		} else if(node.getType().toString().equals("BOOLEAN")) {
			
		}
		*/
		
	}
	
	public void caseATypesType(ATypesType node) {
		
	}
	
	public void caseAIdType(AIdType node) {
		
	}
	
	/*
	 * These are the non-generated methods, will hopefully move into its own class
	 * */
	
	//Used to cycle through normal registers
	private String getNextIntRegister() {
		int registerNumber = registerCounter % 7;
		return "$t" + registerNumber;
	}

	//Used to cycle through float registers
	private String getNextFloatRegister() {
		int registerNumber = floatRegCounter % 11;
		return "$f" + registerNumber;
	}
}