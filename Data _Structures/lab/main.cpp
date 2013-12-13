/*
** main.cpp is the driver program for maze.h
**
** Authored by: Sterling Kohel and Jason Blackwell
**
** main.cpp tests all required functions of maze.h
**
** The first maze object "maze" is read in using the
** textfile without coordinates and uses a queue to find
** oliver's path.
**
** The second maze object "maze1" is read in using a
** textfile filled with coordinates for the maze and uses
** a stack to find oliver's path.
**
** The thrid maze object "maze2" is read in using a
** textfile filled with coordinates for the maze and uses
** a stack to find oliver's path.
**
** The fourth maze object "maze3" is read in using a
** textfile filled with coordinates for the maze and uses
** a queue to find oliver's path.
**
*/

#include <fstream>
#include <iostream>
#include "maze.h"

using namespace std;

int main()
{
    mazeType maze;
    cout<<"Reading Maze From Textfile..."<<endl<<endl;
    maze.readmaze();
    cout<<"Printing Maze..."<<endl<<endl;
    maze.printmaze();
    cout<<endl;
    cout<<"Finding Path Using Queue..."<<endl<<endl;
    maze.findpathQueue();
    cout<<"Printing Maze With Path..."<<endl<<endl;
    maze.printmaze();
    cout<<endl;
    cout<<"Printing Oliver's Path Coordinates..."<<endl<<endl;
    maze.printpathcoord();
    
    mazeType maze1;
    cout<<endl<<"Reading Maze From Textfile..."<<endl<<endl;
    maze1.readmaze();
    cout<<"Printing Maze..."<<endl<<endl;
    maze1.printmaze();
    cout<<endl;
    cout<<"Finding Path Using Stack..."<<endl;
    cout<<endl;
    maze1.findpathStack(); 
    cout<<"Printing Maze With Path..."<<endl<<endl;
    maze1.printmaze();
    cout<<endl;
    cout<<"Printing Oliver's Path Coordinates..."<<endl<<endl;
    maze1.printpathcoord();

    mazeType maze2;
    cout<<endl<<"Reading Maze From Textfile Using Coordinates..."<<endl<<endl;
    maze2.readcoord();
    cout<<"Printing Maze..."<<endl<<endl;
    maze2.printmaze();
    cout<<endl;
    cout<<"Finding Path Using Stack..."<<endl;
    cout<<endl;
    maze2.findpathStack(); 
    cout<<"Printing Maze With Path..."<<endl<<endl;
    maze2.printmaze();
    cout<<endl;
    cout<<"Printing Oliver's Path Coordinates..."<<endl<<endl;
    maze2.printpathcoord();

    mazeType maze3;
    cout<<endl<<"Reading Maze From Textfile Using Coordinates..."<<endl<<endl;
    maze3.readcoord();
    cout<<"Printing Maze..."<<endl<<endl;
    maze3.printmaze();
    cout<<endl<<"Finding Path Using Queue..."<<endl<<endl;
    maze3.findpathQueue();
    cout<<"Printing Maze With Path..."<<endl<<endl;
    maze3.printmaze();
    cout<<endl;
    cout<<"Printing Oliver's Path Coordinates..."<<endl<<endl;
    maze3.printpathcoord();
    
};