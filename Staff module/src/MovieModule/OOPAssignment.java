/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package MovieModule;

/**
 *
 * @author user
 */


import java.util.Scanner;



public class OOPAssignment {
     
     
    
    public static void main(String[] args) {
        
       
        
        int moviechoice;
        do{
        System.out.println("1.Add movie");
        System.out.println("2.Display movie");
        System.out.println("3.Search movie");
        System.out.println("4.Modify movie");
        System.out.println("5.Delete movie");
        System.out.println("6.Exit");
        
        try{
            System.out.print("\nEnter your choice:");
            moviechoice = new Scanner(System.in).nextInt();
        }
        catch(Exception e)
        {
            System.out.println("Invalid choice.Please enter only 1-6");
            System.out.print("Enter your choice:");
            moviechoice = new Scanner(System.in).nextInt();
        }
        switch(moviechoice)
        {
            case 1:
                Movie.addMovie();
                break;
            case 2:
                Movie.displayMovie();
                break;  
            case 3:
                Movie.searchMovie();
                break;
            case 4:
                Movie.modifyMovie();
                break;
            case 5:
                Movie.deleteMovie();
                break;
            case 6:
                break;
            default:System.out.println("Invalid choice. Enter 1-6 only.");
        }
        }while(moviechoice!=6);
        
        Movie.writeMovieList();
    }
    

}
