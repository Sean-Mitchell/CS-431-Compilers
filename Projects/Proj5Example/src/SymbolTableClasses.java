package Project5;

import java.util.*;

class Variable
{
	private String name;
	private String type;
	private int spOffset;
	private boolean isGlobal;
	
	public Variable(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public int getspOffset() {
		return spOffset;
	}
	
	public void setIsGlobal(boolean isGlobal) {
		this.isGlobal = isGlobal;
	}
	
	public void setspOffset(int offset) {
		this.spOffset = offset;
	}
	
	public boolean isGlobal() {
		return isGlobal;
	}
}


/*
 * 
 * Method 
 * 
 */
class Method
{
	private String name;
	private String returnType;
	private ArrayList<Variable> parameters;
	private Hashtable<String, Variable> localVariables;
	
	public Method(String name, String returnType) {
		this.name = name;
		this.returnType = returnType;
		parameters = new ArrayList<Variable>();
		localVariables = new Hashtable<String, Variable>();
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return returnType;
	}
	
	public void addParam(Variable v) {
		parameters.add(v);
	}
	
	public void addVar(Variable v) {
		localVariables.put(v.getName().trim(), v);
	}
	
	public boolean containsParam(String id) {
		for(int i = 0; i < parameters.size(); i++) {
			if(parameters.get(i).getName().equals(id.trim())) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean containsVar(String id) {
		return localVariables.containsKey(id.trim());
	}
	
	public ArrayList<Variable> getParams() {
		return parameters;
	}
	
	public Variable getParam(String id) {
		for(int i = 0; i < parameters.size(); i++) {
			if(parameters.get(i).getName().equals(id.trim())) {
				return parameters.get(i);
			}
		}
		
		return null;
	}
	
	public Variable getVar(String id) {
		return localVariables.get(id.trim());
	}
	
	public Hashtable<String, Variable> getLocalVariables() {
		return localVariables;
	}
}


/*
 * 
 * Class 
 * 
 */
class Class
{
	private String name;
	private ArrayList<Variable> parameters;
	private Hashtable<String, Variable> localVariables;
	
	public Class(String name) {
		this.name = name;
		parameters = new ArrayList<Variable>();
		localVariables = new Hashtable<String, Variable>();
	}
	
	public String getName() {
		return name;
	}
	
	
	public void addParam(Variable v) {
		parameters.add(v);
	}
	
	public void addVar(Variable v) {
		localVariables.put(v.getName().trim(), v);
	}
	
	public boolean containsParam(String id) {
		for(int i = 0; i < parameters.size(); i++) {
			if(parameters.get(i).getName().equals(id.trim())) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean containsVar(String id) {
		return localVariables.containsKey(id.trim());
	}
	
	public ArrayList<Variable> getParams() {
		return parameters;
	}
	
	public Variable getParam(String id) {
		for(int i = 0; i < parameters.size(); i++) {
			if(parameters.get(i).getName().equals(id.trim())) {
				return parameters.get(i);
			}
		}
		
		return null;
	}
	
	public Variable getVar(String id) {
		return localVariables.get(id.trim());
	}
	
	public Hashtable<String, Variable> getLocalVariables() {
		return localVariables;
	}
}

/*
 * 
 * Symbol Table 
 * 
 */
class SymbolTable
{
	private Hashtable<String, Method> methods;
	private Hashtable<String, Variable> classes;
	private Hashtable<String, Variable> globalVariables;
	
	public SymbolTable() {
		methods = new Hashtable<String, Method>();
		globalVariables = new Hashtable<String, Variable>();
	}
	
	public boolean addMethod(Method m) {
		if (!methods.contains(m.getName().trim())) {
			methods.put(m.getName().trim(), m);
			return true;
		}
		
		return false;
	}
	
	public boolean containsMethod(String id) {
		return methods.containsKey(id.trim());
	}
	
	public Method getMethod(String id) {
		return methods.get(id.trim());
	}
	
	public boolean addVar (Variable v) {
		if (!globalVariables.contains(v.getName().trim())) {
			globalVariables.put(v.getName().trim(), v);
			return true;
		}
		
		return false;
	}
	
	public boolean containsVar(String id) {
		return globalVariables.containsKey(id.trim());
	}
	
	public Variable getVar(String id) {
		return globalVariables.get(id.trim());
	}
}