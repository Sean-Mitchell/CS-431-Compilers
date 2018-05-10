package Project5;

import java.util.*;


public class Symbol
{
	public int intVal;
	public float floatVal;
	public String stringVal = "";
	public boolean boolVal = false;
	
	private String type = "";
	private String register = "";
	private String id = "";
	private boolean valueSet;
	private boolean isGlobal;
	private boolean isMultOp;
	private boolean isMethodCall;
	private int spOffset;
	
	
	/*
	 * 
	 * Start of Constructors
	 * 
	 */
	
	public Symbol(String id, String outterType) {
		this.id = id.trim();
		this.type = outterType.trim();
	}
	
	public Symbol(String outterId, String outterType, String register, boolean valueSet) {
		this.id = outterId;
		this.type = outterType.trim();
		this.register = register.trim();
		this.valueSet = valueSet;
	}
	
	public Symbol(int outterVal, String outterType, String register, boolean valueSet) {
		this.intVal = outterVal;
		this.type = outterType.trim();
		this.register = register.trim();
		this.valueSet = valueSet;
	}

	public Symbol(float outterVal, String outterType, String register) {
		this.floatVal = outterVal;
		this.type = outterType.trim();
		this.register = register.trim();
		valueSet = false;
	}
	
	/*
	 * Constructors that include ID
	 */
	public Symbol(String id, int outterVal, String outterType, String register) {
		this.id = id.trim();
		this.intVal = outterVal;
		this.type = outterType.trim();
		this.register = register.trim();
		valueSet = true;
	}

	public Symbol(String id, float outterVal, String outterType, String register) {
		this.id = id.trim();
		this.floatVal = outterVal;
		this.type = outterType.trim();
		this.register = register.trim();
		valueSet = true;
	}
	
	//String with ID
	public Symbol(String id, String outterVal, String outterType, String register) {
		this.id = id.trim();
		this.stringVal = outterVal.trim();
		this.type = outterType.trim();
		this.register = register.trim();
		valueSet = true;
	}
	
	public Symbol(String id, String outterType, String register) {
		this.id = id.trim();
		this.type = outterType.trim();
		this.register = register.trim();
		valueSet = false;
	}

	/*
	 * 
	 * Setters
	 * 
	 */	
	
	public void setInt(int amount) {
		this.intVal = amount;
	}

	public void setIsMultOp(boolean multOp) {
		this.isMultOp = multOp;
	}
	
	public void setIsGlobal(boolean global) {
		this.isGlobal = global;
	}
	
	public void setIsMethodCall(boolean isMethodCall) {
		this.isMethodCall = isMethodCall;
	}
	
	public void setspOffset(int offset) {
		this.spOffset = offset;
	}
	
	public void setRegister(String register) {
		this.register = register.trim();
	}
	
	/*
	 * 
	 * Getters
	 * 
	 * 
	 */
	
	public String getValue() {
		if(type.equals("INT"))
			return intVal + "";
		if(type.equals("REAL"))
			return floatVal + "";
		if(type.equals("STRING"))
			return stringVal.trim() + "";
		return boolVal + "";
	}
	
	public int getspOffset() {
		return spOffset;
	}
	
	public boolean getValueSet() {
		return valueSet;
	}
	
	public boolean getIsMethodCall() {
		return isMethodCall;
	}
	
	public boolean getIsMultOp() {
		return isMultOp;
	}
	
	public String getStringVal() {
		return stringVal.trim();
	}
	public String getType() {
		return type.trim();
	}
	public String getId() {
		return id.trim();
	}
	
	public boolean getIsGlobal() {
		return isGlobal;
	}
	
	public String getRegister() {
		return register.trim();
	}
}