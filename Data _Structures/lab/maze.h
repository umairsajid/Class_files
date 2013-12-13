/*
** Header File maze.h
**
** Authored by: Sterling Kohel and Jason Blackwell
** 
** maze.h holds all of the methods that find oliver's
** path as well as the methods to read the maze from
** the appropriate text files and print coordinates of
** olivers path.
**
** maze.h is tested by running main.cpp
**/

#include <fstream>
#include <sstream>
#include <iostream>
#include <stdlib.h>
#include <stack>
#include <vector>
#include <queue>

using namespace std;

struct locationType
{
	char mazeElement;
	locationType *north, *south , *east , *west, *prevLocation ;
};

class mazeType
{
public:
	// Default constructor
    mazeType();

    // Function: Returns a pointer to Oliver's location
	// Pre: The maze array is filled
    // Post: The pointer to Oliver is layed out
    locationType *findOliver();

    // Function: Reads a coordinate scheme version of the maze
	// Pre: A file called mazecoord.txt is in the dir
    // Post: The maze array is filled with locationType nodes of the maze
    void readcoord();

	// Function: Reads an ASCII version of the maze into array
	// Pre: A file called maze.txt is in the dir
    // Post: The maze array is filled with locationType nodes of the maze
	void readmaze();

    // Function: Prints the contents of the maze array
	// Pre: The maze array is filled
    // Post: The maze array is printed to the screen 
    void printmaze();    

    // Function: Prints the contents of the maze array in coordinate scheme
	// Pre: The maze array is filled
    // Post: The maze array is printed to the screen
    void printpathcoord();

    // Function: Finds a path from Oliver to the cheese using stack
	// Pre: The maze array is filled
    // Post: The path from Oliver to the cheese is layed out
    void findpathStack();

    // Function: Finds a path from Oliver to the cheese using queue
	// Pre: The maze array is filled
    // Post: The path from Oliver to the cheese is layed out
    void findpathQueue();

private:
	//Declarations
    int mazeSize;
    locationType *oliverLocPtr; 
    stack<locationType, vector<locationType*> > path;     
    vector<vector<locationType*> > maze;
    queue<locationType*> queuePath;
};

mazeType::mazeType()
{
	oliverLocPtr = NULL;
}

locationType* mazeType::findOliver()
{
	return oliverLocPtr;
}

void mazeType::readcoord()
{
	ifstream read;
	string filename = "mazecoord.txt";
	char ch;
	string textSize, line;
	locationType *newLocation;
	int x, y;

	read.open(filename.c_str());
	getline(read, textSize);
	mazeSize = atoi(textSize.c_str());
	
	maze.resize(mazeSize);
	for(int i = 0; i < mazeSize; i++)
	{
		maze[i].resize(mazeSize);
	}

	for(y = 0; y < mazeSize; y++)
	{
		for(x = 0; x < mazeSize; x++)
		{
			newLocation = new locationType;
        	newLocation -> mazeElement = ' ';
        	maze[y][x] = newLocation;
        	maze[y][x] -> prevLocation = NULL;
		}
	}
	while (!read.eof())
    {
    	read >> ch;
    	read >> y;
    	read >> x;
    	
        newLocation = new locationType;
        newLocation -> mazeElement = ch;
        maze[y][x] = newLocation;
		if(ch == 'o')
			oliverLocPtr = newLocation;
    }

    for(y = 0; y < mazeSize; y++)
	{	
		for(x = 0; x< mazeSize; x++)
		{
			if (y > 0 && y < mazeSize-1)
			{
				maze[y][x] -> north = maze[y-1][x];
				maze[y][x] -> south = maze[y+1][x];
			}
			else if (y == 0)
				maze[y][x] -> north = NULL;
			else 
				maze[y][x] -> south = NULL;

			if (x > 0 && x < mazeSize-1)
			{
				maze[y][x] -> west = maze[y][x-1];
				maze[y][x] -> east = maze[y][x+1];
			}
			else if (x == 0)
				maze[y][x] -> west = NULL;
			else 
				maze[y][x] -> east = NULL;			
		}
    }
}

void mazeType::readmaze()
{
	ifstream read;
	string filename = "maze.txt";
	char ch;
	string textSize;
	locationType *newLocation;
	int x, y;

	read.open(filename.c_str());
	getline(read, textSize);
	mazeSize = atoi(textSize.c_str());
	
	maze.resize(mazeSize);
	for(int i = 0; i < mazeSize; i++)
	{
		maze[i].resize(mazeSize);
	}

	for(y = 0; y < mazeSize; y++)
	{	
		for(x = 0; x < mazeSize; x++)
		{
			read.get(ch);
			if(ch == '\n')
				read.get(ch);
			newLocation = new locationType;
			newLocation -> mazeElement = ch;
			maze[y][x] = newLocation;
			maze[y][x] -> prevLocation = NULL;

			if(ch == 'o')
				oliverLocPtr = newLocation;
		}
	}
    
	read.close();
	
	for(y = 0; y < mazeSize; y++)
	{	
		for(x = 0; x< mazeSize; x++)
		{
			if (y > 0 && y < mazeSize-1)
			{
				maze[y][x] -> north = maze[y-1][x];
				maze[y][x] -> south = maze[y+1][x];
			}
			else if (y == 0)
				maze[y][x] -> north = NULL;
			else 
				maze[y][x] -> south = NULL;

			if (x > 0 && x < mazeSize-1)
			{
				maze[y][x] -> west = maze[y][x-1];
				maze[y][x] -> east = maze[y][x+1];
			}
			else if (x == 0)
				maze[y][x] -> west = NULL;
			else 
				maze[y][x] -> east = NULL;			
		}
    }	
}

void mazeType::printmaze()
{
	for(int i = 0; i < mazeSize; i++)
	{
		for(int j = 0; j < mazeSize; j++)
		{
			cout<<maze[i][j] -> mazeElement;
		}
		cout<<endl;
	}
}

void mazeType::printpathcoord()
{
	int x, y;

	for(y = 0; y < mazeSize; y++)
	{
		for(x = 0; x < mazeSize; x++)
		{
			if(maze[y][x] -> mazeElement == '*' || maze[y][x]->mazeElement =='o' 
			   || maze[y][x] -> mazeElement == 'c')
				cout << maze[y][x] -> mazeElement<< " "<< y 
					 << " " << x << endl;
		}
	}
}

void mazeType::findpathStack()
{
	locationType *last = new locationType, *current;
	bool pushed = false;
	
	current = oliverLocPtr;
	path.push(current);
	
	while(path.top() -> mazeElement != 'c' )
	{
		current = path.top();
		if(current -> prevLocation == NULL)
			current -> prevLocation = last;
		if(current -> north -> mazeElement == ' '  
			&& current -> north -> prevLocation == NULL && pushed == false)
		{
			path.push(current -> north);
			pushed = true;
		}
		else if (current -> north -> mazeElement == 'c')
		{
			current -> prevLocation = last;
			break;
		}
		if(current -> east -> mazeElement == ' '  
			&& current -> east -> prevLocation == NULL && pushed == false)
		{
			path.push(current -> east);
			pushed = true;
		}
		else if (current -> east -> mazeElement == 'c')
		{
			current -> prevLocation = last;
			break;
		}
		if(current -> south -> mazeElement == ' ' 
			&& current -> south -> prevLocation == NULL && pushed == false)	
		{
			path.push(current -> south);
			pushed = true;
		}
		else if (current -> south -> mazeElement == 'c')
		{
			current -> prevLocation = last;
			break;
		}
		if(current -> west -> mazeElement == ' ' 
			&& current -> west -> prevLocation == NULL && pushed == false)	
		{
			path.push(current -> west);
			pushed = true;
		}
		else if (current -> west -> mazeElement == 'c')
		{
			current -> prevLocation = last;
			break;
		}
		if (pushed == false)
		{
			path.pop();
		}
		last = current;
		pushed = false;
	}

	while(current->mazeElement != 'o')
	{
		current -> mazeElement = '*';
		current = current -> prevLocation;
	}
}

void mazeType::findpathQueue()
{ 
	locationType *last = new locationType, *current;
	bool pushed = false;
	
	current = oliverLocPtr;
	queuePath.push(current);
	
	while(queuePath.front() -> mazeElement != 'c' )
	{
		current = queuePath.front();	
		queuePath.pop();

		if(current -> north -> mazeElement == ' ' && current -> north -> prevLocation == NULL)
		{
			queuePath.push(current -> north);
			current -> north -> prevLocation = current;
		}
		else if (current -> north -> mazeElement == 'c')
		{
			break;
		}
		if(current -> east -> mazeElement == ' ' && current -> east -> prevLocation == NULL)
		{
			queuePath.push(current -> east);
			current -> east-> prevLocation = current;
		}
		else if (current -> east -> mazeElement == 'c')
		{
			break;
		}
		if(current -> south -> mazeElement == ' ' && current -> south -> prevLocation == NULL)	
		{
			queuePath.push(current -> south);
			current -> south -> prevLocation = current;
		}
		else if (current -> south -> mazeElement == 'c')
		{	
			break;
		}
		if(current -> west -> mazeElement == ' ' && current -> west -> prevLocation == NULL)	
		{
			queuePath.push(current -> west);
			current -> west -> prevLocation = current;
		}
		else if (current -> west -> mazeElement == 'c')
		{
			break;
		}
		
		last = current;
		pushed = false;
	}

	while(current->mazeElement != 'o')
	{
		current -> mazeElement = '*';
		current = current -> prevLocation;
	}
}