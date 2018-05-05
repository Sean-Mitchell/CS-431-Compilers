package Project5;

import java.util.*;


public class Symbol
{
	public int intVal;
	public float floatVal;
	public String stringVal;
	
	private String type;
	private String register;
	
	
	/*
	 * 
	 * Start of Constructors
	 * 
	 */
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
	
	public Symbol(String outterVal, String outterType, String register) {
		this.stringVal = outterVal.trim();
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
	
	public String getRegister() {
		return register;
	}
}