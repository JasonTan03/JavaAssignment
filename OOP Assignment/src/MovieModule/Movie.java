/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MovieModule;

import BookingModule.Ticket;
import static TicketingSystem.TicketingSystem.moviesList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;



/**
 *
 * @author user
 */
public class Movie implements Cloneable{

    private String movieName;
    private String genre;
    private Date premiereDate;
    private int runTime;
    private static int movieNum = 0;
    private static final String[] genreList = {"Action", "Horror", "Romance", "Crime", "Comedy"};

    public Movie(){}
    public Movie(String movieName, String genre, Date premiereDate,int runTime) {
        this.movieName = movieName;
        this.genre = genre;
        this.runTime = runTime;
        this.premiereDate = premiereDate;
        movieNum++;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }  

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public Date getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(Date premiereDate) {
        this.premiereDate = premiereDate;
    }

    public static int getMovieNum() {
        return movieNum;
    }

    public static String[] getGenreList() {
        return genreList;
    }

     @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
    
    @Override
    public String toString() {
        return String.format("   %-32s %-11s %-18s %-4d", movieName, genre, premiereDate, runTime);
    }

    //function
    public static void readMovieList()
    {
        
        String line;
        try {
            
            BufferedReader br= new BufferedReader(new FileReader("MovieList.txt"));
            
            try 
            {
                while((line = br.readLine())!=null )
                {
                    
                    String[] read = line.split(",");
                
                    int day = Integer.parseInt(read[2]);
                    int month = Integer.parseInt(read[3]);
                    int year = Integer.parseInt(read[4]);
                    int runtime = Integer.parseInt(read[5]);
                    
                    moviesList.add(new Movie(read[0],read[1],new Date(day,month,year),runtime));
                    
                }
                br.close();
            } 
            catch(IOException ex) 
            {
                ex.printStackTrace();
            }
            } 
        catch (FileNotFoundException ex) 
          {
             ex.printStackTrace();
          }
            
    }
     public static String selectGenre()
    {
        int genreInt=0;
        String genre = "";
        System.out.println("\nGenreList:");
        for (int i = 0; i < genreList.length; i++) {
            System.out.println(i + 1 + "." + genreList[i]);
        }

        do {
             try {
                  System.out.print("Enter movie genre: ");
                  genreInt = new Scanner(System.in).nextInt();
            
            if(genreInt < 1 || genreInt > 5)
            {
                System.out.println("Invalid choices.Please reenter(1-5).");
            }
        } catch (Exception e) {
            System.out.println("Invalid choices(only digit).Please reenter(1-5).");
        }
             } while (genreInt < 1 || genreInt > 5);
            
             
            switch (genreInt) {
                case 1:
                    genre = genreList[0];
                    break;
                case 2:
                    genre = genreList[1];
                    break;
                case 3:
                    genre = genreList[2];
                    break;
                case 4:
                    genre = genreList[3];
                    break;
                case 5:
                    genre = genreList[4];
                    break;
                default:
                    break;
            }
        
        return genre;
    }

    public static void addMovie() {

        // TODO code application logic here
        String genre;
        String movieName;
        Date date1 = new Date();
        TicketingSystem.ClearScreen.cls();
        do{
        System.out.print("Enter movie name(X to exit): ");
         movieName = new Scanner(System.in).nextLine();
         if(movieName=="")
         {
             System.out.println("Movie name cannot be empty.Please reenter");
             TicketingSystem.ClearScreen.wait(1);
         }
         if(movieName.toLowerCase().charAt(0)=='x' && movieName.length()==1)
         {
             TicketingSystem.ClearScreen.cls();
             return;
         }
        }while(movieName=="");

        genre = selectGenre();

    
        int day;
        int month;
        int year;
        do {
            day=0;
            month=0;
            year=0;
            System.out.println("\nEnter premiere date");
            
            do{
            try{
            System.out.print("Day: ");
            day= new Scanner(System.in).nextInt();
            }
            catch(Exception e)
            {
                System.out.println("Date can only be number.Please reenter.");
                TicketingSystem.ClearScreen.wait(1);
            }
            }while(day==0);
            
            do{
            try{
            System.out.print("Month: ");
            month = new Scanner(System.in).nextInt();
            }
             catch(Exception e)
             {
                 System.out.println("Date can only be number.Please reenter.");
                 TicketingSystem.ClearScreen.wait(1);
             }
            }while(month==0);
            
            
            do{
            try{
            System.out.print("Year: ");
            year = new Scanner(System.in).nextInt();
            }catch(Exception e){
                System.out.println("Date can only be number.Please reenter.");
                TicketingSystem.ClearScreen.wait(1);
            }
            }while(year==0);
            
            
            date1.setDay(day);
            date1.setMonth(month);
            date1.setYear(year);

        } while (date1.checkDate() == false);
        
        int runTime=0;
        do{
        try{
            System.out.print("\nEnter run time(minutes)\n:");
            runTime = new Scanner(System.in).nextInt();
            if(runTime<1)
        {
            System.out.println("Run Time cannot be less than or equal 0.Please reenter. ");
            TicketingSystem.ClearScreen.wait(1);
        }
        }catch(Exception e)
        {
            System.out.println("Run time can only be number.Please reenter.");
            TicketingSystem.ClearScreen.wait(1);
        }
        
        }while(runTime<1);

        moviesList.add(new Movie(movieName, genre,  date1,runTime));
        System.out.println("Movie added sucessfully!");
        movieByDate();
        TicketingSystem.ClearScreen.wait(2);
        TicketingSystem.ClearScreen.cls();
    }

    public static void displayMovie() {
          TicketingSystem.ClearScreen.cls();
        movieByDate();
        System.out.println();
        System.out.println("=============================================================================");
        System.out.printf("     %-32s %-11s %-18s %-4s \n", "Movie name", "Genre", "PremiereDate", "Run time");
        System.out.println("=============================================================================");
        for (int i = 0; i < moviesList.size(); i++) {
            System.out.print(i + 1 + ".");
            System.out.println(moviesList.get(i));
        }
         System.out.println("=============================================================================");
        
        TicketingSystem.ClearScreen.wait(3);
    }

    public static void deleteMovie() {
          TicketingSystem.ClearScreen.cls();
          int deleteIndex;
          
        if(movieNum==0)
        {
            System.out.println("Movie List is empty.");
        }
        else{
        
        Movie.displayMovie();
        System.out.printf("%-5s",((movieNum+1)+"."));
        System.out.println("Exit");
        do{
            deleteIndex=0;
        try{
            
        System.out.print("Please enter the number of wanted delete movie(1-"+(movieNum+1) +"):");
         deleteIndex = new Scanner(System.in).nextInt();
         if(deleteIndex<1 || deleteIndex>movieNum+1 )
        {
            System.out.print("\n\nInvalid choice. ");
              TicketingSystem.ClearScreen.wait(1);
        }
        }catch (Exception e){
            System.out.println("Only number can be entered.Please reenter.\n\n");
             TicketingSystem.ClearScreen.wait(1);
        }
        
        }while(deleteIndex==0 ||deleteIndex>movieNum+1 );
        deleteIndex--;
        char really;
        if(deleteIndex == movieNum)
        {
            TicketingSystem.ClearScreen.cls();
            return;
        }
        do{
            System.out.print("Do you really want to delete(y=yes,n=no)?");
             really = new Scanner(System.in).next().charAt(0);
             if(really!='y'&&really!='Y'&& really!='n'&&really!='N')
             {
                 System.out.println("Invalid choice.Please reenter");
                  TicketingSystem.ClearScreen.wait(1);
             }
        }while(really!='y'&&really!='Y'&& really!='n'&&really!='N');
        
        if(Character.toUpperCase(really)=='Y')     
        {
            moviesList.remove(deleteIndex);
            System.out.println("Deleted sucessful!");
            movieNum--;
            TicketingSystem.ClearScreen.cls();
        }
 
        }
    }

    public static void searchMovie() {
        
        int choice;
        TicketingSystem.ClearScreen.cls();
        do {
            TicketingSystem.ClearScreen.cls();
            choice=0;
            System.out.println("1.Search by Movie Name");
            System.out.println("2.Search by Movie Genre");
            System.out.println("3.Exit");
            do{
            try{
                System.out.print("Enter your choice(1-3):");
                choice = new Scanner(System.in).nextInt();
            }catch (Exception e)
            {
                System.out.println("Can only enter number. Please reenter.");
                 TicketingSystem.ClearScreen.wait(1);
                
            }
            }while(choice==0);
            

            if (choice == 1) {

                String sMovieName;
                TicketingSystem.ClearScreen.cls();
                do {
                    int count = 1;
                    
                    do{
                    System.out.print("Enter a movie name(X=exit):");
                    sMovieName = new Scanner(System.in).nextLine();
                    if (sMovieName == "") {
                        System.out.println("\nMovie name cannot be empty.Please reenter");
                         TicketingSystem.ClearScreen.wait(1);
                    }
                    }while(sMovieName == "");

                    for (int i = 0; i < moviesList.size(); i++) {
                        if((sMovieName.charAt(0)=='x'||sMovieName.charAt(0)=='X') && sMovieName.length()==1)
                        {
                            break;
                        }
                        //sMovieName.regionMatches(true,0,moviesList.get(i).getMovieName(),0,sMovieName.length())
                        if (moviesList.get(i).getMovieName().toLowerCase().contains(sMovieName.toLowerCase())) {
                            if(count==1)
                            {
                            System.out.println();
                             System.out.println("=============================================================================");
                            System.out.printf("   %-35s %-10s %-15s %-8s \n", "  Movie name", "Genre", "PremiereDate", "Run time");
                             System.out.println("=============================================================================");
                            }
                            System.out.println(count + "." + moviesList.get(i).toString());
                            count++;
                        }
                         
                    }
                     System.out.println("=============================================================================");
                    System.out.println();
                    if ((sMovieName.toLowerCase().charAt(0) != 'x' || sMovieName.length() != 1) && count == 1) {
                        System.out.println("\nMovie name entered not found,Please reenter.");
                         TicketingSystem.ClearScreen.wait(1);
                    }
                } while (sMovieName.toLowerCase().charAt(0) != 'x' || sMovieName.length() != 1);

            } else if (choice == 2) {
                String sMovieGenre;
                int count;
                char search;
                do {
                    TicketingSystem.ClearScreen.cls();
                    count=0;
                    sMovieGenre = selectGenre();
                     
                    for (int i = 0; i < moviesList.size(); i++) {
                        if (sMovieGenre.equalsIgnoreCase(moviesList.get(i).getGenre())) {
                            count++;
                        }

                    }
                    
                    if (count == 0) {
                        System.out.println("Movie genre entered not found.");
                    }
                    else
                    {
                        count=1;
                         System.out.println("=============================================================================");
                        System.out.printf("   %-35s %-10s %-15s %-8s \n", "  Movie name", "Genre", "PremiereDate", "Run time");
                         System.out.println("=============================================================================");
                        for (int i = 0; i < moviesList.size(); i++) {
                        if (sMovieGenre.equalsIgnoreCase(moviesList.get(i).getGenre())) {
                            System.out.println(count + "." + moviesList.get(i).toString());
                            count++;
                        }
                         
                        }
                        System.out.println("=============================================================================");
                    }
                    
                    
                    do{
                    System.out.print("\nContinue searching by MOVIE GENRE(y=yes,n=no)?");
                     search = new Scanner(System.in).next().charAt(0);
                     
                     if(search!='y'&&search!='Y'&& search!='n'&&search!='N')
                     {
                         System.out.println("Invalid choice.Please reenter");
                         TicketingSystem.ClearScreen.wait(1);
                     }
                    }while(search!='y'&&search!='Y'&& search!='n'&&search!='N');
                } while ((search =='y'||search =='Y'));

            } else if (choice == 3) {
                break;

            } else {

                System.out.println("Invalid choice,Please reenter again.");
                TicketingSystem.ClearScreen.wait(1);
            }
        } while (choice > 0 || choice < 3);
        

    }
    
    public static void modifyMovie()
    {
        TicketingSystem.ClearScreen.cls();
        int editchoice;
        int index;
         
        displayMovie();
        System.out.printf("%-5s",(movieNum+1+"."));
        System.out.println("Exit");
       
        do{
        index = 0;
        System.out.print("\nWhich movie number to be edited(1-"+(movieNum+1)+"):");
        try {
             index = new Scanner(System.in).nextInt();
             if(index <1||index>movieNum+1)
             {
                 System.out.println("Invalid choice.Please reenter");
                 TicketingSystem.ClearScreen.wait(1);
             }
        } catch (Exception e) {
            System.out.println("Only can enter number.Please try again.");
            TicketingSystem.ClearScreen.wait(1);
        }
        }while(index <1||index>movieNum+1);


        index--;
        
        if(index == movieNum)
        {
            TicketingSystem.ClearScreen.cls();
            return;
        }
        do{
            TicketingSystem.ClearScreen.cls();
            System.out.println("\nMovie Details"              +"                                                   Edit List");
            System.out.printf("%-64s","===========================");
            System.out.println("=========");
            System.out.printf("%-64s","Movie Name:"+moviesList.get(index).getMovieName());
            System.out.println("1.Movie name");
            System.out.printf("%-64s","Movie Genre:"+moviesList.get(index).getGenre());
            System.out.println("2.Movie Genre");
            System.out.printf("%-64s","Movie Premiere Date:"+moviesList.get(index).getPremiereDate());
            System.out.println("3.Movie premiere date");
            System.out.printf("%-64s","Movie RunTime:"+moviesList.get(index).getRunTime());
            System.out.println("4.Movie run time");
            System.out.printf("%-64s","============================");
            System.out.println("5.Exit");
        
        do{
            editchoice=0;
              
        try{
        System.out.printf("%87s","Enter your choice(1-5):");
        editchoice = new Scanner(System.in).nextInt();
        if(editchoice<1||editchoice>5)
        {
            System.out.println("Invalid choice.Please reenter\n");
            TicketingSystem.ClearScreen.wait(1);
        }
        
        }catch(Exception e)
        {
            System.out.println("Invalid choice.Please reenter.");
            TicketingSystem.ClearScreen.wait(1);
        }
        }while(editchoice<1||editchoice>5);
        
          
            
            switch (editchoice) 
            {
                case 1:
                    String editname="";
                    do{
                    System.out.print("Enter new movie name:");
                    editname = new Scanner(System.in).nextLine();
                    if(editname=="")
                    {
                        System.out.println("Name cannot be empty.Please reenter.\n");
                        TicketingSystem.ClearScreen.wait(1);
                    }
                    }while(editname=="");
                    moviesList.get(index).setMovieName(editname);
                    break;
                    
                case 2:
                    moviesList.get(index).setGenre(selectGenre());
                    break;
                    
                case 3:
                    Date date1 = new Date();
                    int day;
                    int month;
                    int year;
                     do {
                         day=0;
                         month=0;
                         year=0;
                         System.out.println("Enter new premiere date");
                         
                          do{
                             try {
                                 System.out.print("Day: ");
                                 day = new Scanner(System.in).nextInt();
                             } catch (Exception e) {
                                 System.out.println("Date can only be number.Please reenter.");
                                 TicketingSystem.ClearScreen.wait(1);
                             }
                         } while (day == 0);

                         do {
                             try {
                                 System.out.print("Month: ");
                                 month = new Scanner(System.in).nextInt();
                             } catch (Exception e) {
                                 System.out.println("Date can only be number.Please reenter.");
                                 TicketingSystem.ClearScreen.wait(1);
                             }
                         } while (month == 0);

                         do {
                             try {
                                 System.out.print("Year: ");
                                 year = new Scanner(System.in).nextInt();
                             } catch (Exception e) {
                                 System.out.println("Date can only be number.Please reenter.");
                                 TicketingSystem.ClearScreen.wait(1);
                             }
                         } while (year == 0);
                         date1.setDay(day);
                         date1.setMonth(month);
                         date1.setYear(year);

                        } while (date1.checkDate() == false);
                     moviesList.get(index).setPremiereDate(date1);
                     break;
                case 4:
                    int runTime = 0;
                    do {
                        try {
                            System.out.println("Enter run time(minutes)");
                            runTime = new Scanner(System.in).nextInt();
                            if (runTime < 1)
                            {
                                System.out.println("Run Time cannot be less than or equal 0.Please reenter. ");
                                TicketingSystem.ClearScreen.wait(1);
                            }
                        } catch (Exception e) {
                            System.out.println("Run time can only be number.Please reenter.");
                            TicketingSystem.ClearScreen.wait(1);
                        }

                    } while (runTime < 1);
                    moviesList.get(index).setRunTime(runTime);
                    break;
                    
                case 5:
                    break;
                    
                default:System.out.println("Invalid choice.Select only 1-5.");
                TicketingSystem.ClearScreen.wait(1);
            }
            
           
        }while(editchoice!=5);
        TicketingSystem.ClearScreen.cls();
    }
    
    public static void writeMovieList()
    {
        movieByDate();
        try {
            PrintWriter pw = new PrintWriter("MovieList.txt");
            
            for(int i=0;i<moviesList.size();i++)
            {
                pw.println(moviesList.get(i).getMovieName()+","+moviesList.get(i).getGenre()+","+moviesList.get(i).getPremiereDate().getDay()
                + "," + moviesList.get(i).getPremiereDate().getMonth()+","+moviesList.get(i).getPremiereDate().getYear()+","+moviesList.get(i).getRunTime()) ;
            }
            pw.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void movieByDate()
    {
        Movie temp =  new Movie(); 
        
        //sort by year//
        for(int i=0;i<moviesList.size();i++)
        {
            for(int j=i+1;j<moviesList.size();j++)
            {
                if(moviesList.get(i).getPremiereDate().getYear()>moviesList.get(j).getPremiereDate().getYear())
                {
                    temp =moviesList.get(i);
                    moviesList.set(i, moviesList.get(j));
                    moviesList.set(j, temp);
                }
            }
        }
        //sort by month
         for(int i=0;i<moviesList.size();i++)
        {
            for(int j=i+1;j<moviesList.size();j++)
            {
                if(moviesList.get(i).getPremiereDate().getMonth()>moviesList.get(j).getPremiereDate().getMonth()&&(moviesList.get(i).getPremiereDate().getYear()==moviesList.get(j).getPremiereDate().getYear()))
                {
                    temp =moviesList.get(i);
                    moviesList.set(i, moviesList.get(j));
                    moviesList.set(j, temp);
                }
            }
        }
         //sort by date
         for(int i=0;i<moviesList.size();i++)
        {
            for(int j=i+1;j<moviesList.size();j++)
            {
                if(moviesList.get(i).getPremiereDate().getDay()>moviesList.get(j).getPremiereDate().getDay()&&(moviesList.get(i).getPremiereDate().getMonth()==moviesList.get(j).getPremiereDate().getMonth())&&(moviesList.get(i).getPremiereDate().getYear()==moviesList.get(j).getPremiereDate().getYear()))
                {
                    temp =moviesList.get(i);
                    moviesList.set(i, moviesList.get(j));
                    moviesList.set(j, temp);
                }
            }
        }
         
         //sort same date but accorting to runtime length
         
         for(int i=0;i<moviesList.size();i++)
        {
            for(int j=i+1;j<moviesList.size();j++)
            {
                if(moviesList.get(i).getRunTime()>moviesList.get(j).getRunTime()&&moviesList.get(i).getPremiereDate().getDay()==moviesList.get(j).getPremiereDate().getDay()&&(moviesList.get(i).getPremiereDate().getMonth()==moviesList.get(j).getPremiereDate().getMonth())&&(moviesList.get(i).getPremiereDate().getYear()==moviesList.get(j).getPremiereDate().getYear()))
                {
                    temp =moviesList.get(i);
                    moviesList.set(i, moviesList.get(j));
                    moviesList.set(j, temp);
                }
            }
        }

    }
   
}
