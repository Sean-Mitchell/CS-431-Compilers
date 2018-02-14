package Starter;

public class Program3{
	//the syntax tree representation of:  echo(34)
	//You should create separate programs for each tree you create.
  	private static Stmts program = new ContinuingStmts(
		new AssignStmt(new IdExp("four"),
			new BinExp(	new NumExp(5), 
					new BinOp(0), 
					new NumExp(10))), 
		new ContinuingStmts(
				new AssignStmt(new IdExp("five"),
							new BinExp(
									new NumExp(10), 
							new BinOp(3), 
							new NumExp(3))), 
		new ContinuingStmts(
				new PrintStmt(
						new ContinuingExpList(new IdExp("four"), 
						new LastExpList(new IdExp("five")))
				)				
		,
		new ContinuingStmts(
				new AssignStmt(new IdExp("four"),
						new UnaryExp(new UnaryOp(1),new IdExp("four"))) 				
		,
		new ContinuingStmts(
				new PrintStmt(
						new LastExpList(
								new IdExp("four")))				
		,
		new ContinuingStmts(
				new AssignStmt(new IdExp("five"),
						new UnaryExp(new UnaryOp(1),
								new BinExp(new UnaryExp(
											new UnaryOp(0),										
											new IdExp("four")),								
										new BinOp(3), 
										new IdExp("four"))))			
		,
		new LastStmts(
				new PrintStmt(
						new ContinuingExpList(new IdExp("four"), 
						new LastExpList(new IdExp("five")))
				))))))));

	public static void main(String[] args) {
		//Create a new Interpreter Object
	    Interpreter interpreter = new Interpreter();
	    System.out.println("Evaluating...");
	    //Call the overloaded interpret method with the
	    //static program created above. Should print out 34.
	    interpreter.interpret(program);
	}
}
