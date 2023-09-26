/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BookingModule;

import static BookingModule.Ticket.Seat;
import java.util.Scanner;
/**
 *
 * @author teren
 */
public class HallSeat 
{
    private char mvRow;
    private int mvColumn;

    public HallSeat() 
    {
        
    }
    
    public HallSeat( char mvRow, int mvColumn)
    {
        this.mvRow = mvRow;
        this.mvColumn = mvColumn;
    }
    
    public void enterRow()
    {
        char rowToUpper;
        do 
        {            
        
        System.out.print("Enter Row For Seat(A - J): ");
        char mvRow = new Scanner(System.in).next().charAt(0);
        rowToUpper = Character.toUpperCase(mvRow);

        if(checkRowSeat(rowToUpper)== false)
        {
            System.out.println("Wrong Input, Try Again !!!");
        }
        
        } while (checkRowSeat(rowToUpper)== false);
        
        int mvColumn;
        do 
        {            
        System.out.print("Enter Column For Seat(1 - 14): ");
         mvColumn = new Scanner(System.in).nextInt();
        if(checkColumnSeat(mvColumn)==false)
        {
            System.out.println("Wrong Input, Try Again !!!");
        }
        
        } while (checkColumnSeat(mvColumn)== false);
        
        Seat.add(new HallSeat(rowToUpper,mvColumn));
        
    }

    
    public boolean checkRowSeat(char rowToUpper)
    {
        if(rowToUpper == 'A'|| rowToUpper == 'B' || rowToUpper == 'C' || rowToUpper == 'D' 
           || rowToUpper == 'E'|| rowToUpper == 'F' || rowToUpper == 'G' || rowToUpper == 'H' || rowToUpper == 'I' || rowToUpper == 'J')
        {
            return true;
        }
        else 
        {
            
            return false;
        }
    }
    
    public boolean  checkColumnSeat(int mvColumn)
    {
        if(mvColumn >= 1 || mvColumn <= 14)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public char getMvRow() 
    {
        return mvRow;
    }

    public void setMvRow(char mvRow)
    {
        this.mvRow = mvRow;
    }

    public int getMvColumn()
    {
        return mvColumn;
    }

    public void setMvColumn(int mvColumn) 
    {
        this.mvColumn = mvColumn;
    }
    
    @Override
    public String toString()
    {
        return String.format(" %c%d", mvRow, mvColumn);
    }
    
}
