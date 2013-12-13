using namespace std;

static string variable[] ={"A", "B", "C", "D", "E", "F", "G", "H", "I"};
static string value []= {"1.0", "2.0", "3.0", "4.0", "5.0", "6.0", "7.0", "8.0" , "9.0"};
class infix
{
	public:
		infix(string expression);
		// Class constructor.
		void tokenize(const string& str, vector<string>& tokens);
		// Function: Breaks a string that contains an equation in to individual strings
		// The equation variable has a space delimter between each symbol
    	// Post: the vector tokens contains the equation string as individual tokens
		bool isOperand(string s);
		// Function: Determines in the given string is an operand 
    	// Post: function value (string is not a symbol)
		void split();
		// Function: takes the vector tokens and splits the contents 
		// Pre: The equation is tokenized in the tokens vector
    	// Post: symbols vector contains all the symbol tokens and postfix contains all the operands
		void display();
		// Function: Displays the content of postfix vector
    	// Post: All elements are displayed on the screen
		int priority(string &s);
		// Function: Determines the order of the mathematic symbols
    	// Pre:  given a mathematic symbol as a string 
    	// Post: determines the presendence of the symbol
		void insertOperandValue();
		// Function: switches a variable with its numberic value
    	// Post: all variables are switched with its numberic value
		void shift(string &symbolToShift, int &counter);
		// Function: Moves a symbol from the vector symbols to the vector postfix
    	// Post: The symbol is moved from symbols to postfix
		double calculate();
		// Function: Calculates the value of an expression
    	// Pre: The postfix vector contains the string in the correct postfix order
    	// Post: the double value is printed to the screen
	private:
		string equation;
		vector<string> tokens, symbols, postfix;
		vector<string>::iterator iterator;
};

infix::infix(string expression)
{
	equation = expression;
	tokenize(expression, tokens);
}

void infix::tokenize(const string& str, vector<string>& tokens)
{
	string delimiters = " ";
    // Skip delimiters at beginning.
    string::size_type lastPos = str.find_first_not_of(delimiters, 0);
    // Find first "non-delimiter".
    string::size_type pos     = str.find_first_of(delimiters, lastPos);

    while (string::npos != pos || string::npos != lastPos)
    {
        // Found a token, add it to the vector.
        tokens.push_back(str.substr(lastPos, pos - lastPos));
        // Skip delimiters.  Note the "not_of"
        lastPos = str.find_first_not_of(delimiters, pos);
        // Find next "non-delimiter"
        pos = str.find_first_of(delimiters, lastPos);
    }
}

void infix::display()
{
	for(iterator = postfix.begin(); iterator != postfix.end(); iterator++)
	{
		string s = *iterator;
		cout << *iterator;
	}
	cout << endl;
}	

bool infix::isOperand(string s)
{
	return (s != "+" && s != "-" && s != "*" && s != "/" && s != "(" && s != ")");
}

int infix::priority(string &a)
{
        int presedence;
        
		if (a == "(")
        	presedence = 3;
        else if (a == "*" || a == "/")
        	presedence = 2;
    	else  if (a == "+" || a == "-")
       	 	presedence = 1;
        else if (a == ")")
        	presedence = 0;
    return presedence;
}

void infix::split()
{
	string last;
	vector<string>::iterator reverseIt;
	int symbolCount = 0;
	
	for(iterator = tokens.begin(); iterator != tokens.end(); iterator++)
	{
		string s = *iterator;
				
		if (symbolCount != 0)
			last = symbols.back();
		if (isOperand(s))
			postfix.push_back(s);	
		else if (priority(s) > priority(last) || symbolCount == 0  || last == "(")
		{
			symbols.push_back(s);
			symbolCount++;
		}
		else
		{
			while (priority(s) <= priority(last) && symbolCount != 0 && last != "(")
			{
				shift(last, symbolCount);
				if (symbolCount != 0)
					last = symbols.back();
				if (symbolCount == 0 || priority(s) > priority(last))
				{
					symbols.push_back(s);
					symbolCount++;
					break;
				}				
				
				if (last =="(" && s == ")")
				{
					symbols.pop_back();
					symbolCount--;
					break;
				}
				else if (last =="(")
				{
					symbols.push_back(s);
					symbolCount++;
				}
			}
		}
	}
	
	for(iterator = symbols.end(); iterator != symbols.begin(); iterator--)
	{
		last = symbols.back();
		symbols.pop_back();
		symbolCount--;
		if (last == "(")
			{
				break;
			}
		postfix.push_back(last);
	}
	
	for(iterator = postfix.begin(); iterator != postfix.end(); iterator++)
	{
		string s = *iterator;
		cout <<s<<" ";
	}
	cout<<endl;
}

void infix::shift(string &symbolToShift, int &counter)
{
	symbols.pop_back();
	postfix.push_back(symbolToShift);
	counter--;
}

void infix::insertOperandValue()
{
	int i, j;
	for(i = 0; i < postfix.size(); i++)
	{
		string operand = postfix.at(i);
		
		if (isOperand(operand))
		{
			for(j = 0; j < 9; j++)
			{
				if (variable[j] == operand)
				{
					operand = value[j];
					postfix.insert(postfix.begin()+i+1, operand);
					postfix.erase(postfix.begin()+i);
					break;
				}
			}
		}
	}
	cout<<endl;
}

double infix::calculate()
{
	int i;
    string operand1, operand2, total;
	double term1, term2, result;
	stringstream ss;
	
	for(i = 0; i < postfix.size(); i++)
    {
		if (!isOperand(postfix.at(i)))
        {
			operand2 = postfix.at(i-1);
			operand1 = postfix.at(i-2);
            term2 = atof(operand2.c_str());
			term1 = atof(operand1.c_str());
        
        	if (postfix.at(i) == "+")
				result = term1 + term2;
			else if (postfix.at(i) == "-")
				result = term1 - term2;
			else if (postfix.at(i) == "*")
				result = term1 * term2;
			else if (postfix.at(i) == "/")
				result = term1 / term2;
		
			ss << result;
			total = ss.str();
			postfix.insert(postfix.begin()+i+1, total);
			postfix.erase(postfix.begin()+i);
			postfix.erase(postfix.begin()+i-1);	
			postfix.erase(postfix.begin()+i-2);	
			ss.str("");
			i = 0;
		}	
    }
	cout<<"The answer is "<<result<<endl;
}   
	
