Package ProjFour;

Helpers
    sp  = ' ';
    number = ['0'..'9'];
    letter = ['a'..'z'] | ['A'..'Z'];
    anychar = [35..255];
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
	real = number+ dot number+;
	string = quote (number | letter)* quote;
    whitespace = sp+;
	anychars = anychar+;
	begin = 'BEGIN';
	end = 'END';
	true = 'TRUE';
	false = 'FALSE';
	
	equivalent = '==';
	notequivalent = '!=';
	geq = '>=';
	leq = '<=';
	less = '<';
	greater = '>';

Ignored Tokens
  whitespace;

Productions
    prog = {first} id digit |
    	   {second} lotnumbers |
    	   {third} [eachsymbolisuniqueinaproduction]:id [secondid]:id [digitone]:digit [digittwo]:digit ;
    lotnumbers = digit morenumbers;
    morenumbers = {fourth} digit morenumbers |
    		  {emptyproduction} ;

	factor = {first} paren exp rparen |
				{second} subtract factor |
				{third} int | real |
				{fourth} boolean ;
				
	

	string = string;
	
	boolean = {first} true | false |
		{second} [first]:expr cond [second]:expr | 
			{third} id;

	cond = {first} equivalent |
		{second} notequivalent |
		{third} geq | {fourth} leq |
		{fifth} greater | {sixth} less;

	addop = {first} add | {second} subtract;
	multop = {first} times | {second} divide;

	type = {first} int | 
		{second} real |
		{third} string |
		{fourth} boolean |
		{fifth} void | 
		{sixth} id;
			
	id = id;
	int = number;
	real = real;
	anychars = anychars;
	letter = letter;
	digit = number;
