/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TicketingSystem;

import BookingModule.Ticket;
import StaffModule.Staff;
import MemberModule.Member;
import MemberModule.mRanks;
import MovieModule.Movie;
import java.io.FileNotFoundException;


import java.util.ArrayList;
import java.util.Scanner;
import StaffModule.JobTitle;
import StaffModule.Staff;

/**
 *
 * @author Ai
     */
public final class TicketingSystem{
    public static ArrayList<Ticket> TicketSales = new ArrayList<>();
    public static ArrayList<JobTitle> jobList = new ArrayList<>();
    public static ArrayList<Staff> staffList  = new ArrayList<>();
    public static ArrayList<Member> members = new ArrayList<>();
    public static ArrayList<mRanks> memranks = new ArrayList<>();
    public static ArrayList<Movie> moviesList = new ArrayList<>();
    
    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException{
        Member.readMemberModuleFile();
        Staff.readStaffModuleFile();
        Movie.readMovieList();
        
        while(true){
            ClearScreen.cls();
            int choice;
            //Staff Id and Password can be viewed through staffList.txt
            Logo();
            System.out.println("                               Please Login ");
            boolean login = Staff.login();
            ClearScreen.wait(2);
            if(login){
                do{
                    ClearScreen.cls();
                    Logo();
                    System.out.println("    TGB TICKETING SYSTEM");
                    System.out.println("============================");
                    System.out.println("1.    Staff   Module");
                    System.out.println("2.    Member  Module");
                    System.out.println("3.    Booking Module");
                    System.out.println("4.    Movie   Module");
                    System.out.println("5.       Log  out");
                    System.out.println("6.         Exit");
                    System.out.println("============================");
                    System.out.print("Enter your choice : ");
                choice = new Scanner(System.in).nextInt();

                switch(choice)
                {

                    case 1:ClearScreen.cls();StaffModule();Staff.writeStaffModuleFile();break;

                    case 2:MemberModule();break;

                    case 3:BookingModule();break;

                    case 4:MovieModule();break;

                    case 5:break;
                    
                    case 6:return;

                    default:System.out.println("Invalid choice.");
                }
                }while(choice !=5);

            }
        }
    }
    public static void StaffModule(){
        int cStaff = staffList.get(0).getCurrentStaff();
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("      WELCOME TO STAFF MODULE");
            System.out.println("===================================");
            System.out.println("     1) Display Staff");
            System.out.println("     2) Search  Staff");
            System.out.println("     3) Modify  Staff");
            System.out.println("     4) Add     Staff");
            System.out.println("     5) Delete  Staff");
            System.out.println("     6) Display Staff Password");
            System.out.println("     7) Exit");
            System.out.println("===================================");
            int valid = -1;
            char opt = 0;
            do{
                System.out.print("Please Select An Option : ");
                String check = input.next();
                if(check.length()>1){
                    System.out.println("\nInvalid Option Please Enter Again.\n");
                }else{
                    opt = (check.toUpperCase()).charAt(0);
                    valid = 1;
                }
            }while(valid == -1);
            switch(opt){
                case '1': ClearScreen.cls();Staff.displayStaff();
                    break;
                
                case '2': ClearScreen.cls();Staff.searchStaff();ClearScreen.cls();
                    break;
                
                case '3': ClearScreen.cls();Staff.modifyStaff();ClearScreen.cls();
                    break;
                
                case '4': ClearScreen.cls();Staff.addStaff();ClearScreen.cls();
                    break;
                
                case '5': 
                    if(staffList.get(cStaff).getIsManager() == true){
                        ClearScreen.cls();Staff.deleteStaff();
                    }
                    else
                        System.out.println("\nAccess Denied, User Need to Login As Manager!\n");
                    break;
                
                case '6': 
                    if(staffList.get(cStaff).getIsManager() == true){
                        ClearScreen.cls();Staff.displayPassword();
                    }
                    else
                        System.out.println("\nAccess Denied, User Need to Login As Manager!\n");
                    break;
                case '7': return;
            
                default: System.out.println("Invalid Option Please Enter Again.");    
            }
        }
    }
    public static void MemberModule() throws FileNotFoundException{
   
    Scanner sel = new Scanner(System.in);
    while(true){
        ClearScreen.cls();
        System.out.println("--------------------------");
        System.out.println("      <MEMBER MODULE>     ");
        System.out.println("--------------------------");
        System.out.println("    1. Display Member     ");
        System.out.println("    2.   Add   Member     ");
        System.out.println("    3. Modify  Member     ");
        System.out.println("    4. Search  Member     ");
        System.out.println("    5. Delete  Member     ");
        System.out.println("    6.      Exit          ");
        System.out.println("--------------------------");
        int valid = -1;
        char ch = 0;
        do{
            System.out.print("Enter A Selection :  ");
            String selM = sel.next();
        
            if(selM.length()> 1){
                System.out.println("Invalid Choice");
            }else{
                ch = (selM.toUpperCase().charAt(0));
                valid = 1;
            } 
        }while(valid == -1);
        switch(ch){
            case '1':Member.displayMember();
            break;
            
            case '2':Member.addMember();
            break;
            
            case '3':Member.modifyMember();
            break;
            
            case '4':Member.searchMember();
            break;
            
            case '5':Member.deleteMember();
            break;
            
            case '6':return;
            
            default:System.out.println("Invalid Choice");
        
            
        }
         
         Member.writeMember();
    }
    
  } 
    public static void MovieModule()
    {

        ClearScreen.cls();
        int moviechoice;
        do{
        System.out.println("--------------------------");
        System.out.println("      <MOVIE MODULE>     ");
        System.out.println("--------------------------");
        System.out.println("1.Add movie");
        System.out.println("2.Display movie");
        System.out.println("3.Search movie");
        System.out.println("4.Modify movie");
        System.out.println("5.Delete movie");
        System.out.println("6.Exit");
        System.out.println("--------------------------");
        
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
                System.out.println("\n\n\nThank you for using.Hope to see you again!");
                ClearScreen.wait(2);
                break;
            default:System.out.println("Invalid choice. Enter 1-6 only.");
        }
        }while(moviechoice!=6);
        
        Movie.writeMovieList();
        ClearScreen.cls();
    }
    
    public static void BookingModule() throws CloneNotSupportedException
    {
        ClearScreen.cls();
        System.out.println("--------------------------");
        System.out.println("      <Booking MODULE>     ");
        System.out.println("--------------------------");
        System.out.println("    1.Booking System");
        System.out.println("    2.Daily Sales Record");
        System.out.println("    3.Exit");
        System.out.println("--------------------------");
        
        System.out.print("\nEnter Number: ");
        int num = new Scanner(System.in).nextInt();
          
        switch (num) {
            case 1:
                    BookingModule.Ticket.bookingTicket();
                    break;
                    
            case 2:
                    BookingModule.Ticket.bookingRecord();
                    break;
                    
            case 3:   
                    return;
                
            default:
                
        }
            

    }
    public static void Logo(){
        System.out.println("============================================================================");
        System.out.println("      TTTTTTTTTTTTTT          GGGGGGGGGGGG       BBBBBBBBBBBBBBBBB");
        System.out.println("      TTTTTTTTTTTTTT         GGGGGGGGGGGGGG      BBBBBBBBBBBBBBBBB");
        System.out.println("      TTTTTTTTTTTTTT       GGG                   BBBBB        BBB");
        System.out.println("           TTTT           GGG                    BBBBBBBBBBBBBBBBB");
        System.out.println("           TTTT           GGG       GGGGGGG      BBBBBBBBBBBBBBBBB");
        System.out.println("           TTTT           GGG       GGGGGGG      BBBBB        BBB");
        System.out.println("           TTTT            GGG          GGG      BBBBBBBBBBBBBBBBB");
        System.out.println("           TTTT             GGGGGGGGGGGGGGG      BBBBBBBBBBBBBBBBB");
        System.out.println("============================================================================");
        System.out.println("                          WELCOME TO TGB CINEMA");
        System.out.println("============================================================================");
        
    }
    
   
}
