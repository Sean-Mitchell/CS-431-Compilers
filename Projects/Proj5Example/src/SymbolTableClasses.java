package Project5;

import java.util.*;

public class Variable
{
	private String name;
	private String type;
	
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
}

public class Method
{
	private String name;
	private String returnType;
	private ArrayList<Variable> parameters;
	private Hashtable<String, Variable> localVariables;
	
	public Method(String name, String returnType) {
		this.name = name;
		this.returnType = returnType;
		parameters = new ArrayList<>();
		localVariables = new Hashtable<>();
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public void addParam(Variable v) {
		parameters.add(v);
	}
	
	public void addVar(Variable v) {
		localVariables.put(v.getName(), v);
	}
	
	public boolean containsParam(String id) {
		for(int i = 0; i < parameters.size(); i++) {
			if(parameters.get(i).getName().equals(id)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean containsVar(String id) {
		return localVariables.contains(id);
	}
	
	public ArrayList<Variable> getParams() {
		return parameters;
	}
	
	public Variable getParam(String id) {
		for(int i = 0; i < parameters.size(); i++) {
			if(parameters.get(i).getName().equals(id)) {
				return parameters.get(i);
			}
		}
		
		return null;
	}
	
	public Variable getVar(String id) {
		return localVariables.get(id);
	}
}

public class SymbolTable
{
	private Hashtable<String, Method> methods;
	private Hashtable<String, Variable> globalVariables;
	
	public SymbolTable() {
		methods = new Hashtable<>();
		globalVariables = new Hashtable<>();
	}
	
	public boolean addMethod(Method m) {
		if (!methods.contains(m.getName())) {
			methods.put(m.getName(), m);
			return true;
		}
		
		return false;
	}
	
	public boolean containsMethod(String id) {
		return methods.contains(id);
	}
	
	public Method getMethod(String id) {
		return methods.get(id);
	}
	
	public boolean addVar (Variable v) {
		if (!globalVariables.contains(v.getName())) {
			globalVariables.put(v.getName(), v);
			return true;
		}
		
		return false;
	}
	
	public boolean containsVar(String id) {
		return globalVariables.contains(id);
	}
	
	public Variable getVar(String id) {
		return globalVariables.get(id);
	}
}