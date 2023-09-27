/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TicketingSystem;

import BookingModule.Ticket;
import MemberModule.Member;
import MemberModule.mRanks;
import MovieModule.Movie;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Scanner;
import staff.module.JobTitle;
import staff.module.Staff;

/**
 *
 * @author Ai
     */
public final class TicketingSystem{
    public static ArrayList<Ticket> TicketSales = new ArrayList<>();
    public static ArrayList<JobTitle> jobList = new ArrayList<JobTitle>();
    public static ArrayList<Staff> staffList  = new ArrayList<Staff>();
    public static ArrayList<Member> members = new ArrayList<Member>();
    public static ArrayList<mRanks> memranks = new ArrayList<mRanks>();
    public static ArrayList<Movie> moviesList = new ArrayList<>();
    
    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException{
        readMemberModuleFile();
        readStaffModuleFile();
        Movie.readMovieList();
        int choice;
        Logo();
        boolean login = Staff.login();
        if(login){
            
            
            
            do{
                System.out.println("1.Staff");
            System.out.println("2.Member");
            System.out.println("3.Booking");
            System.out.println("4.Movie");
            System.out.println("5.Exit");
            System.out.print("Enter your choice:");
            choice = new Scanner(System.in).nextInt();
            
            switch(choice)
            {
                
                case 1:StaffModule();break;
                
                case 2:MemberModule();break;
                
                case 3:BookingModule();break;
                
                case 4:MovieModule();break;
                
                case 5:break;
                
                default:System.out.println("Invalid choice.");
            }
            }while(choice !=5);
            
  
        }
    }
    public static void readMemberModuleFile() throws FileNotFoundException{
        String filepath1 = "Ranks.txt";
        String filepath2 = "Memberlist.txt";
      
        File file1 = new File(filepath1);
        Scanner sc1 = new Scanner(file1);
        String line = "";
        while(sc1.hasNextLine()){
            line = sc1.nextLine();
            String [] temp = line.split(",");
            memranks.add(new mRanks(temp[0],temp[1]));
        }

        File file2 = new File(filepath2);
        Scanner sc2 = new Scanner(file2);
        while(sc2.hasNextLine()){
            line = sc2.nextLine();
            String [] temp2 = new String[6];
            temp2 = line.split(",");

            members.add(new Member(temp2[0],temp2[1],temp2[2].charAt(0),new mRanks(temp2[3],temp2[4]),temp2[5]));

        }
    }
    public static void readStaffModuleFile()throws FileNotFoundException{
        File file1 = new File("Job.txt");
        Scanner scanFile1 = new Scanner(file1);
        String line = "";
        while(scanFile1.hasNextLine()){
            line = scanFile1.nextLine();
            String[] temp = line.split(",");
            jobList.add(new JobTitle(temp[0],Double.parseDouble(temp[1]), temp[2]));
        }
        
        File file2 = new File("StaffList.txt");
        Scanner scanFile2 = new Scanner(file2);
        while(scanFile2.hasNextLine()){
            line = scanFile2.nextLine();
            String[] temp2 = new String[6];
            temp2 = line.split(",");
            staffList.add(new Staff(temp2[0], temp2[1].charAt(0),temp2[2],jobList.get(Integer.parseInt(temp2[3])),temp2[4],Boolean.parseBoolean(temp2[5])));
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
                case '1': Staff.displayStaff();
                    break;
                
                case '2': Staff.searchStaff();
                    break;
                
                case '3': Staff.modifyStaff();
                    break;
                
                case '4': Staff.addStaff();
                    break;
                
                case '5': 
                    if(staffList.get(cStaff).getIsManager() == true)
                        Staff.deleteStaff();
                    else
                        System.out.println("\nAccess Denied, User Need to Login As Manager!\n");
                    break;
                
                case '6': 
                    if(staffList.get(cStaff).getIsManager() == true)
                        Staff.displayPassword();
                    else
                        System.out.println("\nAccess Denied, User Need to Login As Manager!\n");
                    break;
                case '7': return;
            
                default: System.out.println("Invalid Option Please Enter Again.");    
            }
        }
    }
    public static void MemberModule(){
    Scanner sel = new Scanner(System.in);
    while(true){
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
        System.out.println("    3.Sales Report");
        System.out.println("    4.Exit");
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
                
                break;
                
                
            case 4:    
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
        System.out.println("                               Please Login ");
    }
    
   
}
