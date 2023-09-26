/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package BookingModule;
import static TicketingSystem.TicketingSystem.members;
import static TicketingSystem.TicketingSystem.TicketSales;
import MovieModule.Movie;
import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar; 
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import static TicketingSystem.TicketingSystem.moviesList;
//valid cdc number = {12345674, 24681355, 0304261012}

public class Ticket 
{
    
    private Movie movie;
    private ArrayList<HallSeat> hallSeat;
    private double totalTicketPrice;
    private String date;
    private String customerPaymentType;
    private static double actionPrice = 15.00;
    private static double horrorPrice = 15.00;
    private static double romancePrice = 18.00;
    private static double crimePrice = 20.00;
    private static double comedyPrice = 20.00;

    
    public static ArrayList<HallSeat> Seat = new ArrayList<>();
    
    public Ticket(Movie movie, ArrayList<HallSeat> hallSeat, double totalTicketPrice, String customerPaymentType) 
    {
        this.movie = movie;
        this.hallSeat = hallSeat;
        this.totalTicketPrice = totalTicketPrice;
        this.date = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss").format(Calendar.getInstance().getTime());
        this.customerPaymentType = customerPaymentType;
    }
    
    public static void bookingTicket() throws CloneNotSupportedException
    {
        clearScreen();
        Movie mvSelected;
        Scanner bkt = new Scanner(System.in); 
        HallSeat temp = new HallSeat();
        char cfm;
        double totalPriceAfterDiscount;
        String paymentType;
        
        Seat.clear();
        Movie.displayMovie();
        System.out.print("Select Movie:");
        int mvSelection = bkt.nextInt();
        mvSelection--;
        mvSelected = (Movie)moviesList.get(mvSelection).clone();
        
        System.out.print("Enter The Number Of Seat: ");
        int numberOfSeat = bkt.nextInt();
        clearScreen();
           
           for(int i = 0; i < numberOfSeat; i++)
           {
               clearScreen();
               readSeatFile();
               System.out.println("No." + (i+1) + " Seat Position: ");
               
               temp.enterRow();
               //Seat.add(temp);
               
               
           }
           clearScreen();
           System.out.println("======================================");
           System.out.println("MOVIE NAME: " + mvSelected.getMovieName());
           System.out.println("TICKET NO.: " + numberOfSeat);
           System.out.println("SEAT POSITION:");
           for(int y = 0; y <Seat.size(); y++)
           {
                         System.out.println("No."+(y+1) + Seat.get(y).toString()); 
           }
           System.out.println("======================================");
            do
            {
                System.out.print("Order Confirm ? (Y/N): ");
                char confirm = bkt.next().charAt(0);
                cfm = Character.toUpperCase(confirm);
                clearScreen();
                if(cfm == 'Y')
                {
                   totalPriceAfterDiscount = checkMember(numberOfSeat, mvSelected);
                   clearScreen();
                   System.out.println("Ticket(s) Price is(are): "+ "RM " +totalPriceAfterDiscount);
                   paymentType = customerPayType(); 
                   if(paymentType.equals("CREDIT CARD"))
                   {
                       int repeat;
                       do {                            
                       repeat = 0;
                       clearScreen();
                       System.out.print("Enter Credit Card Number: ");
                       int cardNo = new Scanner(System.in).nextInt();
                       
                       int sum1 = 0;
                       int sum2 = 0;
                       int temp1 = cardNo;
                       for(int i = 0; i < 8; i+=2)
                       {
                           sum1 += temp1%10;
                           temp1 /= 100;
                       }
                       
                       int temp2 =cardNo/10;
                       for(int j = 0; j < 8; j += 2)
                       {
                           int lastDigit = temp2%10;
                           int x = lastDigit * 2;
                           int a = x%10;
                           int b = x/10;
                           
                           sum2 += a+b;
                           temp2/=100;
                       }
                       
                       int result = sum1 + sum2;
                       
                       if(result%10 == 0)
                       {
                           System.out.println("The Credit Card Is Valid.");
                           wait(2);
                           repeat += 1;
                       }
                       else
                       {
                           System.out.println("The Creadit Card Is Not Valid!!!");   
                           wait(2);
                       }
                       } while (repeat == 0);
                       
                   }
                   
                   TicketSales.add(new Ticket(mvSelected,Seat,totalPriceAfterDiscount, paymentType));
                   clearScreen();
                       System.out.println("   _                              _\n" +
                                         " ('v')                          ('v')\n" +
                                         "//-=-\\\\ !!!Enjoy Your Movie!!! //-=-\\\\\n" +
                                         "(\\_=_/)                        (\\_=_/)\n" +
                                         " ^^ ^^                          ^^ ^^");   
                       wait(5);
                       clearScreen();
                }
             else if(cfm == 'N')
                {
                  break;
                }
             else
                {
                  System.out.println("Wrong Input !!!");  
                }
        
            }while(cfm != 'Y' && cfm != 'N'); 
    }
    
    public static double checkMember(int numberOfSeat, Movie mvSelected)
    {
        int i;
        int y;
        double totalP = 0.0;
        
        do {
            i = 0;
            System.out.print("Member or Non-member(Y / N): ");
            char confirmYN = new Scanner(System.in).next().charAt(0);
            char confirmToUpper = Character.toUpperCase(confirmYN);

            if(confirmToUpper == 'Y')
            {
                y = 0;
                System.out.print("Enter Member ID: ");
                String inputMemberId = new Scanner(System.in).next();

                for(int m = 0; m < members.size(); m++)
                {
                    if(inputMemberId.equalsIgnoreCase(members.get(m).getMemberID()))
                    {    
                        String memberLvString = members.get(m).getMranks().getRanks();
                        switch (memberLvString.toLowerCase()) {
                            case "normal":
                                totalP = payment(numberOfSeat, mvSelected.getGenre());
                                return totalP * 0.95;
                            case "silver":
                                totalP = payment(numberOfSeat, mvSelected.getGenre());
                                return totalP * 0.9;
                            case "gold":
                                totalP = payment(numberOfSeat, mvSelected.getGenre());
                                return totalP * 0.85;
                        }
                    }

                    if(y == 0)
                    {
                        System.out.println("Member ID not Valid!!!");
                        i += 1;
                    }
                }
            }
            else if(confirmToUpper == 'N')
            {
                return totalP = payment(numberOfSeat, mvSelected.getGenre());
            }
            else
            {
                System.out.print("Wrong Input!!!");
                i += 1;
            }

        } while (i == 0);
        return 0;
    }
    
    public static double payment(int numberOfSeat, String mvSelected)
    {
        
        if(mvSelected.equalsIgnoreCase("action") )
        {
            return numberOfSeat * actionPrice;
        }
        else if(mvSelected.equalsIgnoreCase("horror"))
        {
            return numberOfSeat * horrorPrice;        
        }
        else if(mvSelected.equalsIgnoreCase("romance"))
        {
            return numberOfSeat * romancePrice;        
        }
        else if(mvSelected.equalsIgnoreCase("crime"))
        {
            return numberOfSeat * crimePrice;        
        }
        else
        {
            return numberOfSeat * comedyPrice;        
        }
        
    }
    
    public static String customerPayType()
    {
        String customerPayType = null;
        int cusPayType;
        do {            
        System.out.println("[Payment Type]\n"+
                           "==============\n"+
                           "1. Cash\n"+
                           "2. Credit Card\n"+
                           "==============");
        
        System.out.print("Enter Payment Type: ");
        cusPayType = new Scanner(System.in).nextInt();
        
        if(cusPayType == 1)
        {
            customerPayType = "CASH";
        }
        else if(cusPayType == 2)
        {
            customerPayType = "CREDIT CARD";
        }
        
        } while (cusPayType != 1 && cusPayType != 2);
        
        return customerPayType;
    }
    
    
    
    public static double getActionPrice() 
    {
        return actionPrice;
    }

    public static void setActionPrice(double actionPrice) 
    {
        Ticket.actionPrice = actionPrice;
    }

    public static double getHorrorPrice() 
    {
        return horrorPrice;
    }

    public static void setHorrorPrice(double horrorPrice) 
    {
        Ticket.horrorPrice = horrorPrice;
    }

    public static double getRomancePrice() 
    {
        return romancePrice;
    }

    public static void setRomancePrice(double romancePrice) 
    {
        Ticket.romancePrice = romancePrice;
    }

    public static double getCrimePrice() 
    {
        return crimePrice;
    }

    public static void setCrimePrice(double crimePrice) 
    {
        Ticket.crimePrice = crimePrice;
    }

    public static double getComedyPrice() 
    {
        return comedyPrice;
    }

    public static void setComedyPrice(int comedyPrice) 
    {
        Ticket.comedyPrice = comedyPrice;
    }
    
    private static void readSeatFile()
    {
      try 
       {
            File seatFile = new File("seat.txt");
            Scanner seatReader = new Scanner(seatFile);
              while (seatReader.hasNextLine()) 
               {
                 String data = seatReader.nextLine();
                 System.out.println(data);
                 
                }
            seatReader.close();
        }    
        catch (FileNotFoundException e) 
       {
            System.out.println("Seat File can not be read!!!");
             e.printStackTrace();
        }
    }
     
   
    
    public  static void clearScreen() 
    {
        try{
            Robot rob = new Robot();
            try {
            rob.keyPress(KeyEvent.VK_CONTROL); // press "CTRL"
            rob.keyPress(KeyEvent.VK_L); // press "L"
            rob.keyRelease(KeyEvent.VK_L); // unpress "L"
            rob.keyRelease(KeyEvent.VK_CONTROL); // unpress "CTRL"
            Thread.sleep(1000); // add delay in milisecond, if not there will automatically stop after clear
            } catch (InterruptedException e) { e.printStackTrace(); }
        } catch(AWTException e) { e.printStackTrace(); }  
    }
    
    public static void wait(int sec) 
    {
	 try {
		 Thread.currentThread().sleep(sec * 1000);
	 } catch (InterruptedException e) {
		 e.printStackTrace();
	 }
    
    }
}

