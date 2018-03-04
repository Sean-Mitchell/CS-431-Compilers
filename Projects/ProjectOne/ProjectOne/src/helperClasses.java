package Starter;

class Token {
	int type;
	String value;
	String token;
	public Token(int type, String value, String token) {
		this.type = type;
		this.value = value;
		this.token = token;
	}

	public String toString() {
		return this.type + " " + this.value + " " + this.token;
	}
}
