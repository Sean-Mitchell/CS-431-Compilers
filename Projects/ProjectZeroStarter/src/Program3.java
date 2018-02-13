package Starter;

public class Program{
	//the syntax tree representation of:  echo(34)
	//You should create separate programs for each tree you create.
  	private static Stmts program = new ContinuingStmts(
		new AssignStmt(new IdExp("four"),
			new BinExp(new UnaryExp
						(new UnaryOp("--"),
						new NumExp(5)), 
					new BinOp("+"), 
					new NumExp(10)), 
		new ContinuingStmts(
				new AssignStmt(new IdExp("five"),
					new BinExp(new UnaryExp
							(new UnaryOp("--"),
									new NumExp(10)), 
							new BinOp("/"), 
							new NumExp(3))), 
		new ContinuingStmts(
				new PrintStmt(
						new ContinuingExpList(new IdExp("four"), 
						new LastExpList(new IdExp("five")))
				)				
		,
		new ContinuingStmts(
				new AssignStmt(new IdExp("four"),
						new UnaryExp(new UnaryOp(">>"), 
								new UnaryExp(new UnaryOp("--"), new IdExp("four")))), 				
		,
		new ContinuingStmts(
				new PrintStmt(
						new LastExpList(
								new IdExp("four")))				
		,
		new ContinuingStmts(
				new AssignStmt(new IdExp("five"),
						new BinExp(new UnaryExp(
								new UnaryOp("<<"),
										new UnaryExp(new UnaryOp("--",
												new IdExp("four")))),
								
								new BinOp("/"), 
								new UnaryExp(UnaryOp(">>"), new IdExp("four"))))				
		,
		new LastStmts(
				new PrintStmt(
						new ContinuingExpList(new IdExp("four"), 
						new LastExpList(new IdExp("five")))
				)))))))
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
