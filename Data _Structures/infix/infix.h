using namespace std;

class infix
{
	public:
		infix(string expression);
		void tokenize(const string& str, vector<string>& tokens);
		bool isDouble(string s);
		void split();
		void display();
		int priority(string s);
		void shift(string &symbolToShift, int &counter);
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
	for(iterator = tokens.begin(); iterator != tokens.end(); iterator++)
	{
		string s = *iterator;
		cout << *iterator<<endl;
	}
}

bool infix::isDouble(string s)
{
	return isdigit(s.at(0));
}

int infix::priority(string a)
{
        int presedence;
        if (a == ")")
                presedence = 3;
        if (a == "*" || a == "/")
        presedence = 2;
    else  if (a == "+" || a == "-")
        presedence = 1;
        else if (a == "(")
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
		if (isDouble(s))
		{
			postfix.push_back(s);			
		}
		else if (priority(s) > priority(last) || symbolCount == 0)
		{
			symbols.push_back(s);
			symbolCount++;
		}
		else
		{
			while (priority(s) <= priority(last))
			{
				if (priority(s) == 3 and priority(last) == 0)
					break;
				shift(last, symbolCount);
				last = symbols.back();
			}
		}
	}
	
	for(iterator = symbols.end(); iterator != symbols.begin(); iterator--)
	{
		last = symbols.back();
		symbols.pop_back();
		symbolCount--;
		cout<<"count is "<<symbolCount<<endl;
		if (last == "(")
			{
				break;
			}
		postfix.push_back(last);
	}
	
	for(iterator = postfix.begin(); iterator != postfix.end(); iterator++)
	{
		string s = *iterator;
		cout <<"postfix -> "<<s<<endl;
	}
}

 
void infix::shift(string &symbolToShift, int &counter)
{
	symbols.pop_back();
	counter--;
	postfix.push_back(symbolToShift);
}
	
