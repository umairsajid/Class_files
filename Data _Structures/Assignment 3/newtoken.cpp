#include <string>
#include <algorithm>
#include <vector>
#include <iterator>

#include <iostream>

using namespace std;

void Tokenize(const string& str,
                      vector<string>& tokens,
                      const string& delimiters = " ")
{
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



int main()
{
    vector<string> tokens;
	vector<string>::iterator iterator;
    string str("Split me up! Word1 Word2 Word3.");

    Tokenize(str, tokens);
	
    copy(tokens.begin(), tokens.end(), ostream_iterator<string>(cout, "\n "));
}
