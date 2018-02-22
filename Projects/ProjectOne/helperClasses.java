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
	
	public Token(int type) {
		this.type = type;
		
		switch (type) {
			case 0:
				value = "class";
				token = "<TClass>";
			case 1:
				value = "public";
				token = "<TPublic>";
			case 2:
				value = "static";
				token = "<TStatic>";
			case 3:
				value = "void";
				token = "<TVoid>";
			case 4:
				value = "main";
				token = "<TMain>";
			case 5:
				value = "String";
				token = "<TString>";
			case 0:
				value = "class";
				token = "<TClass>";
		}
	}
}