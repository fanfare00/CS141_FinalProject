package edu.cpp.cs.cs141.final_project.User_Interface;

import edu.cpp.cs.cs141.final_project.Application;

public interface IUserInterface
{
    public void createGrid(int rows, int cols);
    public void addApplication(Application app);
    public void addToGrid(int xIndex, int yIndex, char symbol);
    public void beginGame();
    public void promptCommand();
    public void drawGrid();
    //public void clearGrid();
}
