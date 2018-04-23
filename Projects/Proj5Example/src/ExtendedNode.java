package Project5;

import java.util.*;

class ExtendedNode
{
	public static final int CLASS = 0;
	public static final int VAR = 1;
	
	public int type;
	public Object node;
	// Don't know if this is necessary but just in case
	Hashtable<Object, Object> innerScope;
	
	public ExtendedNode(int type, Object node) {
		this.type = type;
		this.node = node;
		this.innerScope = null;
	}
	
	public ExtendedNode(int type, Object node, Hashtable<Object, Object> innerScope) {
		this.type = type;
		this.node = node;
		this.innerScope = innerScope;
	}
}