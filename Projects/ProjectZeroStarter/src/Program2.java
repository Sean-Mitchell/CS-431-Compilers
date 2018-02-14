package Starter;

public class Program2{
	//the syntax tree representation of:  echo(34)
	//You should create separate programs for each tree you create.
  	private static Stmts program = new ContinuingStmts(
			new PrintStmt(
					new ContinuingExpList(new IdExp("two"), 
					new LastExpList(new IdExp("three")))
			)	
		, 
		new ContinuingStmts(
			new AssignStmt(new IdExp("three"), 
					new BinExp(new NumExp(6), 
							new BinOp(0), 
							new BinExp(new IdExp("two"),
									new BinOp(4), 
									new NumExp(4)))),
			new LastStmts(new AssignStmt(new IdExp("two"),
					new BinExp(new NumExp(5), 
							new BinOp(2), 
							new BinExp(new NumExp(20),
									new BinOp(1), 
									new NumExp(10))))			
			)
		)
	);

	public static void main(String[] args) {
		//Create a new Interpreter Object
	    Interpreter interpreter = new Interpreter();
	    System.out.println("Evaluating...");
	    //Call the overloaded interpret method with the
	    //static program created above. Should print out 34.
	    interpreter.interpret(program);
	}
}
