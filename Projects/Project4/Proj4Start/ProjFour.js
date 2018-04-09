Package ProjFour;

Helpers
    sp  = ' ';
    number = ['0'..'9'];
    letter = ['a'..'z'] | ['A'..'Z'];
    anyChar = [35..255];
	dot = '.';

	add = '+';
	subtract = '-';
	times = '*';
	divide = '/';

	quote = '"';
	lparen = '(';
	rparen = ')';	
	lbracket = '[';
	rbracket = ']';


    alphanumeric = letter | number;
    
Tokens
    id = letter (letter | number)*;
    digit = number+;
	real = number+ dot number+
	string = quote (number | letter)* quote;
    whitespace = sp+;
	anyChars = anyChar+;
	begin = 'BEGIN';
	end = 'END';
	true = 'TRUE';
	false = 'FALSE';
	
	equivalent = '==';
	notEquivalent = '!=';
	geq = '>=';
	leq = '<=';
	less = '<';
	greater = '>';

	optionInt = 

Ignored Tokens
  whitespace;

Productions
    prog = {first} id digit |
    	   {second} lotnumbers |
    	   {third} [eachsymbolisuniqueinaproduction]:id [secondid]:id [digitone]:digit [digittwo]:digit ;
    lotnumbers = digit morenumbers;
    morenumbers = {fourth} digit morenumbers |
    		  {emptyproduction} ;

	Factor = lparen Exp rparen |
				subtract Factor |
				Int | Real |
				Boolean | 
				
	

	String = string;
	
	Boolean = true | false |
				Expr Cond Expr | 
				Id;

	Cond = equivalent |
			notEquivalent |
			geq | leq |
			greater | less;

	AddOp = add | subtract;
	MultOp = times | divide;

	Type = Int | 
			Real |
			String |
			Boolean |
			Void | 
			Id;
			
	Id = id;
	Int = number;
	Real = real;
	AnyChars = anyChars;
	Letter = letter;
	Digit = number;
