package Starter;

/*
	Add methods to handle the traversal of other nodes in 
	the syntax tree. Some methods will need to be updated. 
	For instance, the interpret method for a Stmt assumes 
	that all statements are print statements. This is 
	obviously not the case and needs to be handled.
*/

public class Interpreter{

	public int interpret(Stmts stms) {
		if (stms instanceof ContinuingStmts) {
			interpret(((ContinuingStmts)stms).stms);
			interpret(((ContinuingStmts)stms).stm);
		} else {
			interpret(((LastStmts)stms).stm);
		}
		
		return 0;
	}

	//currently assumes all Stmt are PrintStmt
	//probably needs to be updated
 	public int interpret(Stmt stm)  {
 		if (stm instanceof PrintStmt) {
 			return this.interpret((PrintStmt)stm);
 		}
    	
    	if (stm instanceof AssignStmt) {
    		return this.interpret((AssignStmt)stm);
    	}
    	
    	return 0;
 	}

	//each PrintStmt contains an ExpList
	//evaluate the ExpList
 	public int interpret(PrintStmt stm) {
 		ExpList exp = stm.exps;
 	   System.out.println(this.interpret(exp));
 	   return 0;
 	}
 	
 	public int interpret(AssignStmt stm) {
 		Expression exp = stm.exp;
 	   return this.interpret(exp);
 	}

 	public int interpret(Expression exp) {
    	if (exp instanceof NumExp)
      		return this.interpret((NumExp)exp);
      		
      if (exp instanceof IdExp)
      		return this.interpret((IdExp)exp);
      		
      if (exp instanceof BinExp)
      		return this.interpret((BinExp)exp);
      		
      if (exp instanceof UnaryExp)
      		return this.interpret((UnaryExp)exp);
      		
    	return 0;
 	}

 	public int interpret(NumExp exp) {
    	return exp.num;
 	}
 	
 	public int interpret(IdExp exp) {
		if (exp.getValue() instanceof NumExp) {
			return interpret((NumExp)exp.getValue());			
		} else if (exp.getValue() instanceof IdExp) {
			return interpret((IdExp)exp.getValue());		
		} else if (exp.getValue() instanceof BinExp) {
			return interpret((BinExp)exp.getValue());	
		} else {
			return interpret((UnaryExp)exp.getValue());
		}
 	}
 	
 	public int interpret(BinExp exp) {
    	switch(exp.op.opType){
    		case 0: return interpret(exp.exp1) + interpret(exp.exp2);
    		case 1: return interpret(exp.exp1) - interpret(exp.exp2);
    		case 2: return interpret(exp.exp1) * interpret(exp.exp2);
    		case 3: return interpret(exp.exp1) / interpret(exp.exp2);
    		case 4: return interpret(exp.exp1) % interpret(exp.exp2);
    		default: return 0;
    	}
 	}
 	
 	public int interpret(UnaryExp exp) {
 		int expVal = this.interpret(exp.exp);
    	switch(exp.op.opType){
    		case 0: return 1 << expVal;
    		case 1: return 1 >> expVal;
    		default: return 0;
    	}
 	}

 	public int interpret(ExpList list) {
		if (list instanceof ContinuingExpList) {
			return interpret(((ContinuingExpList)list).list) + ", " + interpret(((LastExpList)list).exp);
		} else {
			return interpret(((LastExpList)list).exp);
		}   		
 	}

	public int interpret(ContinuingExpList list) {
			return interpret((Expression)list.exp) + interpret((ExpList)list.list);	
	}

 	public int interpret(LastExpList list) {
		if (list.head instanceof NumExp) {
			return interpret((NumExp)list.head);			
		} else if (list.head instanceof IdExp) {
			return interpret((IdExp)list.head);		
		} else if (list.head instanceof BinExp) {
			return interpret((BinExp)list.head);	
		} else {
			return interpret((UnaryExp)list.head);
		}
  	}
}
