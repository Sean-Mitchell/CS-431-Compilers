package Project5;

import java.util.*;

class ExtendedNode
{
	// type constants
	public static final int CLASS = 0;
	public static final int VAR = 1;
	public static final int METHOD = 2;
	
	// return type constants
	public static final int INT = 0;
	public static final int REAL = 1;
	public static final int STRING = 2;
	public static final int BOOLEAN = 3;
	public static final int VOID = 4;
	
	public int type;
	public int returnType;
	Hashtable<Object, Object> innerScope;
	public Object value;
	
	public ExtendedNode(int type, int returnType) {
		this.type = type;
		this.returnType = returnType;
		this.node = node;
		this.innerScope = null;
	}
	
	public ExtendedNode(int type, int returnType, Hashtable<Object, Object> innerScope) {
		this.type = type;
		this.returnType = returnType;
		this.node = node;
		this.innerScope = innerScope;
	}

}