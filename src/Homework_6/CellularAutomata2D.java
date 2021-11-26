package Homework_6;
/*************************************************************************************
 * Assignment 5
 * Name: Aayush Kafle
 * E-mail: akafle1@unm.edu
 * Course: CS 152 - Section 006
 * Date Submitted: 11/22/2021
 *
 *The program computes game of life in display. We can see by running the program the a single member only exists if 2 or 3 of the neighbors are alive.
 * It also include a separate cellular automation in which a single cell is alive and any of the side members are alive then it becomes alive in next generation.
 * If its dead and if the diagonal neighbors are alive then it becomes alive in the next generation.
 *************************************************************************************/


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class CellularAutomata2D extends BasicPanel implements ActionListener
{
    int size;
    int[][] currentStates;
    int[][] nextStates;
    final int ALIVE = 1;
    final int DEAD = 0;
    final int CELLSIZE = 10;
    final Color ALIVE_COLOR = new Color(4, 15, 224);
    final Color DEAD_COLOR = Color.BLACK;
    final Color GRID_COLOR = new Color(50,50,50);
    JButton runButton;
    boolean running;

    CellularAutomata2D()
    {
        size = 75;
        setSize(size * CELLSIZE, size * CELLSIZE);
        runButton = new JButton("run");
        runButton.setPreferredSize(new Dimension(200, 30));
        this.add(runButton);
        runButton.addActionListener(this);
        running = false;

        currentStates = new int[size][size];
        nextStates = new int[size][size];
        for(int i=0;i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                currentStates[i][j] = DEAD;
                nextStates[i][j] = DEAD;
            }
        }
        // makes the heart shape in the screen as per the method below
        heartShape(2);
        // makes the cross shape in the screen as per the method below
        crossShape(3);
    }

    public static void main(String[] args) {
        CellularAutomata2D CA2D = new CellularAutomata2D();
        MyFrame frame = new MyFrame(CA2D);
        CA2D.animate(6);
    }

    @Override
    protected void paintComponent(Graphics g) {
        displayCurrentStates(g);
        if(running)
            iterate();
    }

    // Rule for Game of Life
    int rule(int row, int column){
        //count number of ALIVE neighbors
        int liveNeighbors = 0;
        for (int i=row-1; i<=row+1; i++)
        {
            for (int j=column-1; j<=column+1; j++)
            {
                if(!(i==row && j==column))
                {
                    if (currentStates[i][j] == ALIVE)
                        liveNeighbors++;
                }
            }
        }
        //count number of DEAD neighbors
        if (currentStates[row][column] == ALIVE && (liveNeighbors ==2 || liveNeighbors==3))
            return ALIVE;
        if (currentStates[row][column]==DEAD && liveNeighbors==3)
            return ALIVE;
        return DEAD;
    }

    // Rule for my Cellular Automata
    int myRule(int row, int column){
        //count number of ALIVE neighbors
        int liveNeighbors = 0;

        // count the sum of diagonals of a particular cell
        int diagonalSum = currentStates[row - 1][column - 1] +
                currentStates[row - 1][column + 1] +
                currentStates[row + 1][column - 1] +
                currentStates[row + 1][column + 1];

        for (int i=row-1; i<=row+1; i++)
        {
            for (int j=column-1; j<=column+1; j++)
            {
                if(!(i==row && j==column))
                {
                    if (currentStates[i][j] == ALIVE)
                        liveNeighbors++;
                }
            }
        }
        //count number of DEAD neighbors
        if (currentStates[row][column] == ALIVE && (liveNeighbors ==1))
            return ALIVE;

        // if at least one of the diagonal neighbor is alive
        if (currentStates[row][column]==DEAD && diagonalSum>=1)
            return ALIVE;

        // dead for any other combination
        return DEAD;
    }

    void iterate()
    {
        for (int i=1; i<size-1; i++)
        {
            for(int j=1; j<size-1; j++)
            {
                // this is where we change the rule whether to implement new CA or the game of life
                nextStates[i][j] = myRule(i,j);
            }
        }

        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                currentStates[i][j] = nextStates[i][j];

            }
        }
    }

    void displayCurrentStates(Graphics g)
    {
        for (int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                if (currentStates[i][j] == ALIVE)
                {
                    g.setColor(ALIVE_COLOR);
                }
                else
                {
                    g.setColor(DEAD_COLOR);
                }
                g.fillRect(j * CELLSIZE, i * CELLSIZE, CELLSIZE, CELLSIZE);
                g.setColor(GRID_COLOR);
                g.drawRect(j*CELLSIZE, i*CELLSIZE, CELLSIZE, CELLSIZE);
            }
        }
    }

    // for makinig the heartShape in the screen
    public void heartShape(int q)
    {

        currentStates[size/q][size/q] = ALIVE;
        currentStates[size/q-1][size/q-1] = ALIVE;
        currentStates[size/q-2][size/q-2] = ALIVE;
        currentStates[size/q-2][size/q-3] = ALIVE;
        currentStates[size/q-1][size/q-4] = ALIVE;
        currentStates[size/q][size/q-4] = ALIVE;
        currentStates[size/q+1][size/q-3] = ALIVE;
        currentStates[size/q+2][size/q-2] = ALIVE;
        currentStates[size/q+3][size/q-1] = ALIVE;
        currentStates[size/q+4][size/q] = ALIVE;
        currentStates[size/q+3][size/q+1] = ALIVE;
        currentStates[size/q+2][size/q+2] = ALIVE;
        currentStates[size/q+1][size/q+3] = ALIVE;
        currentStates[size/q][size/q+4] = ALIVE;
        currentStates[size/q-1][size/q+4] = ALIVE;
        currentStates[size/q-2][size/q+3] = ALIVE;
        currentStates[size/q-2][size/q+2] = ALIVE;
        currentStates[size/q-1][size/q+1] = ALIVE;
    }

    // makes the crossShape in the screen
    public void crossShape(int a)
    {
        currentStates[size/a][size/a] = ALIVE;
        currentStates[size/a-1][size/a-1] = ALIVE;
        currentStates[size/a+1][size/a+1] = ALIVE;
        currentStates[size/a+1][size/a-1] = ALIVE;
        currentStates[size/a-1][size/a+1] = ALIVE;
        currentStates[size/a-2][size/a-2] = ALIVE;
        currentStates[size/a+2][size/a+2] = ALIVE;
        currentStates[size/a+2][size/a-2] = ALIVE;
        currentStates[size/a-2][size/a+2] = ALIVE;
        currentStates[size/a-3][size/a-3] = ALIVE;
        currentStates[size/a+3][size/a+3] = ALIVE;
        currentStates[size/a+3][size/a-3] = ALIVE;
        currentStates[size/a-3][size/a+3] = ALIVE;
        currentStates[size/a-4][size/a-4] = ALIVE;
        currentStates[size/a+4][size/a+4] = ALIVE;
        currentStates[size/a+4][size/a-4] = ALIVE;
        currentStates[size/a-4][size/a+4] = ALIVE;
    }

    @Override
    public void actionPerformed(ActionEvent e){
            System.out.println("button pressed");
            if (running)
            {
                running = false;
                runButton.setText("run");
            }

            else
            {
                running = true;
                runButton.setText("stop");
            }
            //get focus back in main window, off to button
            requestFocus();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        for(int i=1; i<size-1; i++)
        {
            for(int j=1; j<size-1; j++)
            {
                //if at current cell, change state
                if ((x>j*CELLSIZE && x<(j+1)*CELLSIZE) &&
                        (y>i*CELLSIZE && y<(i+1)*CELLSIZE))
                {
                    currentStates[i][j] = 1-currentStates[i][j];
                }
            }
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                //if at current cell, change state
                if ((x>j*CELLSIZE && x<(j+1)*CELLSIZE) &&
                        (y>i*CELLSIZE && y<(i+1)*CELLSIZE))
                {
                    currentStates[i][j] = 1-currentStates[i][j];
                    currentStates[i][j] = ALIVE;
                }
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if(c == ' '){
            System.out.println("clearing the CA");
            for(int i=0; i<size; i++)
            {
                for (int j=0; j<size; j++)
                {
                    currentStates[i][j] = DEAD;
                }
            }
        }
    }
}
