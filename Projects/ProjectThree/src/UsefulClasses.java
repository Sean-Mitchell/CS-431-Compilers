package Project3;

import java.util.*;
import Project3.lexer.*;
import Project3.node.*;

/*
	You will need to add many more classes to this file to get the interpreter 
	to work. The pattern shown below for the simple example should be enough 
	to show you what to do for the remaining classes.
*/

class IdHolder {
	static HashMap<String, Expression> idMap = new HashMap<>();
}

abstract class Stmts {}

class ContinuingStmts extends Stmts{
	public Stmt stm;
	public Stmts stms;
	public ContinuingStmts(Stmt stm, Stmts stms){
		this.stm = stm;
		this.stms = stms;
	}
}

class LastStmts extends Stmts{
	public Stmt stm;
	public LastStmts(Stmt stm){
		this.stm = stm;
	}
}

abstract class Stmt {}

//handles the Stmt --> echo ( ExpList ) production
class PrintStmt extends Stmt{
    public ExpList exps;
    public PrintStmt(ExpList e){
        exps = e;
    }
}

//handles the Stmt --> id <-- Expression production
class AssignStmt extends Stmt{
    public IdExp id;
    public Expression exp;
    public AssignStmt(IdExp i, Expression e){
        id = i;
        exp = e;
		
		IdHolder.idMap.put(id.id, exp);
    }
}

abstract class Expression {}

class NumExp extends Expression
{
    public int num;
    public NumExp(int n){
        num = n;
    }
}

class IdExp extends Expression
{
    public String id;
	
	public Expression getValue() {
		return IdHolder.idMap.get(id);
	}
	
    public IdExp(String id){
        this.id = id;
    }
}

class BinExp extends Expression
{
    public Expression exp1;
    public BinOp op;
    public Expression exp2;
    public BinExp(Expression exp1, BinOp op, Expression exp2){
        this.exp1 = exp1;
        this.op = op;
        this.exp2 = exp2;
    }
}

class UnaryExp extends Expression
{
    public Expression exp;
    public UnaryOp op;
    public UnaryExp( UnaryOp op, Expression exp){
        this.exp = exp;
        this.op = op;
    }
}

abstract class ExpList {}

class ContinuingExpList extends ExpList
{
	public Expression exp;
	public ExpList list;
	public ContinuingExpList(Expression exp, ExpList list){
		this.exp = exp;
		this.list = list;
	}
}

class LastExpList extends ExpList
{
    public Expression head;
    public LastExpList(Expression h){
        head = h;
    }
}

abstract class Operator
{
	public int opType;
}

class BinOp extends Operator
{
	public BinOp(int opType){
		this.opType = opType;
	}
}

class UnaryOp extends Operator
{
	public UnaryOp(int opType){
		this.opType = opType;
	}
}

class ProductionPart
{
	public ProductionPart[] contents;
	public String name;
	public boolean isTerminal;
	public Token terminal;
	
	public ProductionPart(ProductionPart[] contents, String name) {
		this.contents = contents;
		this.name = name;
		this.isTerminal = false;
		this.terminal = null;
	}
	
	public ProductionPart(String name, Token terminal) {
		this.contents = new ProductionPart[0];
		this.name = name;
		this.isTerminal = true;
		this.terminal = terminal;
	}
}

class ParseTreeNode
{
	public ProductionPart prod;
	public ArrayList<ParseTreeNode> children;
	
	public ParseTreeNode(ProductionPart prod, ArrayList<ParseTreeNode> children) {
		this.prod = prod;
		this.children = children;
	}
	
	public ParseTreeNode(ProductionPart prod) {
		this.prod = prod;
		this.children = new ArrayList<>();
	}
}