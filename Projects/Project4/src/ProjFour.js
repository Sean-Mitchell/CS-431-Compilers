Package ProjFour;

Helpers
    sp  = ' ';
    number = ['0'..'9'];
    letter = ['a'..'z'] | ['A'..'Z'];
    anychar = [35..255];
	

	add = '+';
	subtract = '-';
	times = '*';
	divide = '/';

	
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
	real = number+ '.' number+;
	stringtok = '"' (number | letter)* '"';
    whitespace = sp+;
	anychars = anychar+;
	begin = 'BEGIN';
	end = 'END';
	true = 'TRUE';
	false = 'FALSE';
	classs = 'CLASS';
	lcurly = '{';
	rcurly = '}';
	comma = ',';
	colon = ':';
	lbrack = '[';
	rbrack = ']';
	equal = '=';
	semicolon = ';';
	quote = '"';
	if = 'IF';
	then = 'THEN';
	else = 'ELSE';
	while = 'WHILE';
	for = 'FOR';
	plus = '+';
	minus = '-';
	put = 'PUT';
	get = 'GET';
	new = 'NEW';
	return = 'RETURN';
	dot = '.';
	switch = 'SWITCH';
	case = 'CASE';
	break = 'BREAK';
	default = 'DEFAULT';
	
	equivalent = '==';
	notequivalent = '!=';
	geq = '>=';
	leq = '<=';
	less = '<';
	greater = '>';

Ignored Tokens
  whitespace;

Productions
    prog = begin classmethodstmts end;
	
	classmethodstmts = {first} classmethodstmts classmethodstmt |
		{empty} ;
		
	classmethodstmt = {first} classs id lcurly methodstmtseqs rcurly |
		{second} type id lparen varlist rparen lcurly stmtseq rcurly |
		{third} [first]:id commaids colon type semicolon ;
		
	commaids = {first} commaid commaids |
		{empty} ;
		
	commaid = comma id;
		
	methodstmtseqs = {first} methodstmtseqs methodstmtseq |
		{empty} ;
		
	methodstmtseq = {first} type id lparen varlist rparen lcurly stmtseq rcurly |
		{second} [first]:id commaids colon type semicolon ;
		
	stmtseq = {first} stmt stmtseq |
		{empty} ;
		
	stmt = {third} [first]:id commaids colon type optbracknum semicolon |
		{fourth} if lparen boolean rparen then [first]:lcurly [second]:stmtseq [third]:rcurly optelse |
		{fifth} while lparen boolean rparen lcurly stmtseq rcurly |
		{sixth} for lparen opttype id [first]:colon [second]:equal [third]:expr [fourth]:semicolon boolean [fifth]:semicolon bigora rparen lcurly stmtseq rcurly |
		{seventh} id optbracknum colon equal get lparen rparen semicolon |
		{eigth} put lparen id optbracknum rparen semicolon |
		{ninth} [first]:id optbracknum bigorb |
		{tenth} id lparen varlisttwo rparen semicolon |
		{eleventh} [first]:id optbracknum dot [second]:id lparen varlisttwo rparen dotidthings semicolon |
		{twelfth} return expr semicolon |
		{thirteenth} switch [lparen1]:lparen expr [rparen1]:rparen lcurly case [lparen2]:lparen [first]:int [rparen2]:rparen [colon1]:colon [stmtseq1]:stmtseq breakornah cases default [colon2]:colon [stmtseq2]:stmtseq rcurly ;
	
	cases = {first} cases singcase |
		{empty} ;
		
	singcase = case lparen int rparen colon stmtseq breakornah ;
	
	breakornah = {first} break semicolon |
		{empty} ;
	
	dotidthings = {first} dotidthings dotidthing |
		{empty} ;
		
	dotidthing = dot id lparen varlisttwo rparen;
	
	bigora = {first} id [first]:plus [second]:plus |
		{second} id [first]:minus [second]:minus |
		{third} id colon equal expr ;
		
	bigorb = {first} [first]:plus [second]:plus semicolon |
		{second} [first]:minus [second]:minus semicolon |
		{third} colon equal new id lparen rparen semicolon |
		{fourth} bigorc ;
	
	bigorc = {first} colon equal new id lparen rparen semicolon |
		{second} colon equal bigord ;
	
	bigord = {first} expr semicolon |
		{second} [first]:quote anychars [second]:quote semicolon |
		{third} boolean semicolon ;
	
	opttype = {first} type |
		{empty} ;
		
	optbracknum = {first} lbrack int rbrack |
		{empty} ;
		
	optelse = {first} else lcurly stmtseq rcurly |
		{empty} ;
	
	varlist = varlistornah ;
	
	varlistornah = {first} id colon type optbracknum bigcommaids |
		{empty} ;
		
	bigcommaids = {first} bigcommaid bigcommaids |
		{empty} ;
		
	bigcommaid = comma id colon type optbracknum ;
	
	varlisttwo = varlisttwoornah ;
	
	varlisttwoornah = {first} expr commaexprs |
		{empty} ;
		
	commaexprs = {first} commaexpr commaexprs |
		{empty} ;
		
	commaexpr = comma expr;
	
	expr = {first} expr addop term |
		{second} term ;
		
	factor = {first} lparen expr rparen |
		{second} minus factor |
		{third} int |
		{fourth} real |
		{fifth} boolean |
		{sixth} [first]:id bigore ;
		
	bigore = {first} optbracknum optdotidthing |
		{second} lparen varlisttwo rparen ;
	
	optdotidthing = {first} dotidthing |
		{empty} ;
	
	string = stringtok;
	
	boolean = {first} true | {fourth}false |
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