Package Project3;

Helpers
            digit = ['0'..'9'];
            lf = 10; 
            sp = ' '; 
			period = '.';
Tokens
			print = 'echo';
			semicolon = ';';
			lt = '<';
			plus = '+';
			minus = '-';
			times = '*';
			blank = ( sp | 13 | lf| 9)+;
			number = (digit)+ | ((digit)+ period (digit)*);
			id = (['a'..'z'] | ['A'..'Z'])+;
			echo = 'echo';
			divide = '/';
			mod = '%';
			lshift = '<<';
			rshift = '>>';
Ignored Tokens
			blank;
