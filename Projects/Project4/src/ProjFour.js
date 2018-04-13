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

	begin = 'BEGIN';
	end = 'END';
	true = 'TRUE';
	false = 'FALSE';
	class = 'CLASS';
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
		
	classmethodstmt = {first} class id lcurly methodstmtseqs rcurly |
		{second} type id lparen varlist rparen lcurly stmtseq rcurly |
		
	methodstmtseqs = {first} methodstmtseqs methodstmtseq |
		{empty} ;
		
	methodstmtseq = {first} type id lparen varlist rparen lcurly stmtseq rcurly |
		
	stmtseq = {first} stmt stmtseq |
		{empty} ;
		
		{fifth} while lparen boolean rparen lcurly stmtseq rcurly |
		{tenth} id lparen varlisttwo rparen semicolon |
		{twelfth} return expr semicolon |
		
	
	
	expr = {first} expr addop term |
		{second} term ;
		
	factor = {first} lparen expr rparen |
		{second} minus factor |
		{third} int |
		{fourth} real |
		{fifth} boolean |
		
	string = stringtok;
	
	boolean = {first} true | {fourth}false |
		{second} [first]:expr cond [second]:expr | 

	cond = {first} equivalent |
		{second} notequivalent |
		{third} geq | {fourth} leq |

	addop = {first} add | {second} subtract;

	type = {first} int | 
		{second} real |
		{third} string |
		{fourth} boolean |
		{fifth} void | 
