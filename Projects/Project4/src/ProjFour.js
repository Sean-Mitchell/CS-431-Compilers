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
	lparenh = '(';
	rparenh = ')';	
	lbracket = '[';
	rbracket = ']';


    alphanumeric = letter | number;
    
Tokens
	lparen = lparenh;
	rparen = rparenh;
    idtok = letter (letter | number)*;
    digittok = number+;
	digsing = number;
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
    prog = {first} id digittok |
    	   {second} lotnumbers |
    	   {third} [eachsymbolisuniqueinaproduction]:id [secondid]:id [digitone]:digittok [digittwo]:digittok ;
    lotnumbers = digittok morenumbers;
    morenumbers = {fourth} digittok morenumbers |
    		  {emptyproduction} ;

	factor = {first} lparen exp rparen |
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
			
	id = idtok;
	int = digittok;
	real = real;
	anychars = anychars;
	letter = letter;
	digit = digsing;
