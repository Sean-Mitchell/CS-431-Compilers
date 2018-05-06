package Project5;

import java.util.*;


public class Symbol
{
	public int intVal;
	public float floatVal;
	public String stringVal;
	
	private String type;
	private String register;
	public String id;
	
	
	/*
	 * 
	 * Start of Constructors
	 * 
	 */
	
	public Symbol(String id, String outterType) {
		this.id = id.trim();
		this.type = outterType.trim();
	}
	
	public Symbol(int outterVal, String outterType, String register) {
		this.intVal = outterVal;
		this.type = outterType.trim();
		this.register = register.trim();
	}

	public Symbol(float outterVal, String outterType, String register) {
		this.floatVal = outterVal;
		this.type = outterType.trim();
		this.register = register.trim();
	}
	
	/*
	 * Constructors that include ID
	 */
	public Symbol(String id, int outterVal, String outterType, String register) {
		this.id = id.trim();
		this.intVal = outterVal;
		this.type = outterType.trim();
		this.register = register.trim();
	}

	public Symbol(String id, float outterVal, String outterType, String register) {
		this.id = id.trim();
		this.floatVal = outterVal;
		this.type = outterType.trim();
		this.register = register.trim();
	}
	
	//String with ID
	public Symbol(String id, String outterVal, String outterType, String register) {
		this.id = id.trim();
		this.stringVal = outterVal.trim();
		this.type = outterType.trim();
		this.register = register.trim();
	}
	
	public Symbol(String id, String outterType, String register) {
		this.id = id.trim();
		this.type = outterType.trim();
		this.register = register.trim();
	}

	/*
	 * 
	 * Setters
	 * 
	 */	
	
	public void setInt(int amount) {
		this.intVal = amount;
	}
	
	public void setRegister(String register) {
		this.register = register;
	}
	
	/*
	 * 
	 * Getters
	 * 
	 * 
	 */
	public String getStringVal() {
		return stringVal;
	}
	public String getType() {
		return type;
	}
	public String getId() {
		return id;
	}
	
	public String getRegister() {
		return register;
	}
}