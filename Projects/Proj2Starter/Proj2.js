Package Project2;

Helpers
            digit = ['0'..'9'];
            lf = 10; 
            sp = ' '; 
Tokens
			class = 'class';
			public = 'public';
			static = 'static';
			void = 'void';
			main = 'main';
			String = 'String';
			lcurly = '{';
			rcurly = '}';
			lbracket = '[';
			rbracket = ']';
			lparen = 'lparen';
			rparen = 'rparen';
			extends = 'extends';
			semicolon = ';';
			return = 'return';
			int = 'int';
			boolean = 'boolean';
            if = 'if';
			else = 'else';
			while = 'while';
			print = 'System.out.println';
			equal = '=';
			and = '&&';
			lt = '<';
			plus = '+';
			minus = '-';
			times = '*';
			period = '.';
			length = 'length';
			comma = ',';
			true = 'true';
			false = 'false';
			this = 'this';
			new = 'new';
			exc = '!';
			comment = '//' .* |
				'/*' ([^\n]?)* '*/';
			real = digit+ period digit*;
			id = ([a..z] | [A..Z]) | (digit | ([a..z] | [A..Z]) | '_');
Ignored Tokens