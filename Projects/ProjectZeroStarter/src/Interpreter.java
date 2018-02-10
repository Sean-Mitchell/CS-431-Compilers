package Starter;

/*
	Add methods to handle the traversal of other nodes in 
	the syntax tree. Some methods will need to be updated. 
	For instance, the interpret method for a Stmt assumes 
	that all statements are print statements. This is 
	obviously not the case and needs to be handled.
*/

public class Interpreter{

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
    	return exp.id;
    	//TODO
 	}
 	
 	public int interpret(BinExp exp) {
    	switch(exp.op.opType){
    		case 0: return exp.exp1 + exp.exp2;
    		case 1: return exp.exp1 - exp.exp2;
    		case 2: return exp.exp1 * exp.exp2;
    		case 3: return exp.exp1 / exp.exp2;
    		case 4: return exp.exp1 % exp.exp2;
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
    	return this.interpret((LastExpList)list);
 	}

	public int interpret(ContinuingExpList list) {
		return 0;
	}

 	public int interpret(LastExpList list) {
    	return this.interpret(list.head);
  	}
}
