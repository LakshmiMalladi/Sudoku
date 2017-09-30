/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author KriLak
 */
public class Sudoku {

    /**
     * @param args the command line arguments
     */
    public static int[][] grid;
    
    public boolean solveSudoku()
    {
        int row,col;
        int[] blankCell = findBlankLocation();
        row = blankCell[0];
        col = blankCell[1];
        if(row==-1)
            return true;
        
        for(int i=1;i<=9;i++)
        {
            if(isSafe(row,col,i))
            {
                grid[row][col] = i;
                if(solveSudoku())
                    return true;
                grid[row][col]=0;
            }
        }
        return false;
    }
    
    public boolean isSafe(int r, int c, int i)
    {
        if(!inRow(r,i)&&!inCol(c,i)&&!inRegion(r,c,i))
        {
            return true;
        }
        return false;
    }
    
    public boolean inRow(int r, int i)
    {
        for(int col=0;col<9;col++)
        {
            if(grid[r][col]==i)
                return true;
        }
        return false;
    }
    public boolean inCol(int c, int i)
    {
       for(int row=0;row<9;row++)
        {
            if(grid[row][c]==i)
                return true;
        }
        return false;
    }
    public boolean inRegion(int r, int c, int i)
    {
       int x = r%3;
       int y = c%3;
       
       for(int row = r-x;row<=(r-x+2);row++)
       {
           for(int col=c-y;col<=(c-y+2);col++)
           {
               if(grid[row][col]==i)
                   return true;
           }
       }
       return false;
    }
    
    public int[] findBlankLocation()
    {
        int[] cell = new int[2];
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(grid[i][j]==0)
                {
                    cell[0]=i;
                    cell[1]=j;
                    return cell;
                }
            }
        }
        cell[0]=-1;
        cell[1]=-1;
        return cell;
    }
    
    public void displayGrid()
    {
        for(int row=0;row<9;row++)
        {
            if(row%3==0)
            {
                System.out.println("-----------------------");
            }
            for(int col=0;col<9;col++)
            {
                if(col%3==0)
                {
                    System.out.print("| ");
                }
                System.out.print(grid[row][col]+" ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }
    
   
    public static void main(String[] args) {
        // TODO code application logic here
        
        grid = new int[][]
        {
            {5,3,0,0,7,0,0,0,0},
            {6,0,0,1,9,5,0,0,0},
            {0,9,8,0,0,0,0,6,0},
            {8,0,0,0,6,0,0,0,3},
            {4,0,0,8,0,3,0,0,1},
            {7,0,0,0,2,0,0,0,6},
            {0,6,0,0,0,0,2,8,0},
            {0,0,0,4,1,9,0,0,5},
            {0,0,0,0,8,0,0,7,9}
        };
        
        Sudoku s = new Sudoku();
        //s.displayInitialGrid();
        s.displayGrid();
        if(s.solveSudoku())
        {
            s.displayGrid();
        }
        else
        {
            System.out.println("No Solution");
        }
    }
    
}
