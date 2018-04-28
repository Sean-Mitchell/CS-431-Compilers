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
	
	String variableType;
	
	//StringBuilders for data and main method
	StringBuilder mainAssembly;
	StringBuilder dataAssembly;

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
		mainAssembly.append("\t.text\n").append("\t.globl main\n").append("main:\n");
		dataAssembly.append("\t.data\n");
        node.getClassmethodstmts().apply(this);
		System.out.println("main\n" + mainAssembly.toString() + "\n" + "data\n" + dataAssembly.toString() );
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
		
	}
	
	public void caseAExprFactor(AExprFactor node) {
		
	}
	
	public void caseAIntFactor(AIntFactor node) {
		
	}
	
	public void caseARealFactor(ARealFactor node) {
		
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
}