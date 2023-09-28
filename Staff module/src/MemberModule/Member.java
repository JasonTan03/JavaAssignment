/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



package MemberModule;
import TicketingSystem.ClearScreen;
import java.util.Scanner;

import static TicketingSystem.TicketingSystem.members;
import static TicketingSystem.TicketingSystem.memranks; 
import staff.module.Person;


/**
 *
 * @author User
 */
public class Member extends Person {
    private String memberID;
    private mRanks mranks;
    private String status;
    private static int maxMemberID = 1000;

    //constructor

    public Member(String memberID, String name, char gender, mRanks mranks, String joinDate) {
        super(name, gender, joinDate);
        this.memberID = memberID;
        this.mranks = mranks;
        //this.status = status;
        maxMemberID++;
    }

     
    

    //getter
    public String getMemberID() {
        return memberID;
    }

    public mRanks getMranks() {
        return mranks;
    }


    public String getStatus() {
        return status;
    }

    /*public Date getDate() {
        return date;
    }*/

    public static int getMaxmemberID() {
        return maxMemberID;
    }

    //setter

    public void setMranks(mRanks mranks) {
        this.mranks = mranks;
    }
    
    //to string
    @Override
    public String toString() {
        return String.format(" %-15s %-17s %-12c %-15s  %s",memberID,getName(),getGender(),mranks,getJD());
    }
    
public static void wait(int sec){
	 try {
		 Thread.currentThread().sleep(sec * 1000);
	 } catch (InterruptedException e){
		 e.printStackTrace();
         }
}

public static void displayMember(){
    ClearScreen.cls();
    System.out.println("\n\n                                       List of Member");
    System.out.println("-----------------------------------------------------------------------------------------------------------");
    System.out.println(" Member ID\t MemberName\t Gender\t       MemberRank\t Status\t      Join Date");
    System.out.println("-----------------------------------------------------------------------------------------------------------");
    for (int i=0;i<members.size();i++){
        System.out.println(members.get(i).toString());
    }
    System.out.println("-----------------------------------------------------------------------------------------------------------");
    wait(10);
    ClearScreen.cls();
}

public static void addMember(){
    ClearScreen.cls();
    Scanner addM = new Scanner(System.in);
    
    while(true){
        System.out.println("Member Id : MB" + Member.getMaxmemberID());
        System.out.print("Enter Name : ");
        String name = addM.nextLine();
        
        if(addM.equals("X") || (addM.equals("x"))){
            System.out.println("\nReturning...\n");
            return;
        }
        
        char gender = 0;
        
        while(true){
            System.out.print("\nEnter gender : ");
            String Mgender = addM.next().toUpperCase();
            if(Mgender.equals("F") || Mgender.equals("M")){
                gender = Mgender.charAt(0);
                break;
            }else{
                System.out.println("\nInvalid Gender Try Again");
            }
        }
        
         System.out.println("\nList of Member Rank : ");
         for(int i = 0; i < memranks.size(); i++)
         {
             System.out.println((i+1)+"." + memranks.get(i).toString());
         }
         int choice = 0;
         
         do{
             try{
                 System.out.print("Enter your choice (1-" + memranks.size() +") : ");
                 String temp1 = addM.next();
                 int temp1int = Integer.parseInt(temp1);
                 if(temp1int <= memranks.size() && temp1int > 0){
                     choice = temp1int;
                     break;
                 }else{
                     System.out.println("\nInvalid Choice");
                 }
             }catch(Exception e){}
         }while(choice == 0);
        
         System.out.print("\nEnter Join Date (DD/MM/YYYY) : ");
         String joinDate = addM.next();
                  
         
        members.add(new Member(  "MB" + Member.getMaxmemberID(), name, gender, memranks.get(choice -1 ) , joinDate));

         
         do{
             System.out.print("\nContinue?(Y/N) : ");
             String cont = addM.next();
             
            if(cont.length() < 2){
                if(cont.equals("Y") || cont.equals("y")){
                    break;
                }else if(cont.equals("N") || cont.equals("n")){
                return;
                }
            }else{
                System.out.println("\nInvalid Choice");
            }
         }while(true);
         addM.nextLine();
    }
}
    public static void modifyMember(){
        ClearScreen.cls();
        Scanner modM = new Scanner(System.in);
        
        while(true){
            int match = -1;
            int matchID = 0;
            System.out.println("\n\n                                       List of Member");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.println(" Member ID\t MemberName\t Gender\t       MemberRank\t Status\t      Join Date");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            for (int i=0;i<members.size();i++){
            System.out.println(members.get(i).toString());
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            
            System.out.print("                                       Select memberID to modify :");
            String MMemberId = modM.next();
            for(int i = 0; i < members.size();i++){
                if(MMemberId.equals(members.get(i).getMemberID())){
                    match = 1;
                    matchID = i;
                }
            }
            if(match == -1){
                System.out.println("                                        Invalid MemberID");
                ClearScreen.cls();
            }
            else
            {
                 
                while(true){
                    System.out.println("\n<Modify Member Details>");
                    System.out.println("    1. Member Name       ");
                    System.out.println("    2. Member Gender     ");
                    System.out.println("    3. Member Ranks      ");
                    System.out.println("    4. Member Join Date  ");
                    System.out.println("    5. Exit              ");
                    System.out.println("------------------------ ");
                    System.out.print("Select which to modify : ");
                    int choice = 0;
                    choice = modM.nextInt();
                    modM.nextLine();
                    switch(choice){
                        case 1:
                            System.out.print("Enter New Member Name(X to exit) : ");
                            String modName = modM.nextLine();
                            if(modName.equals("X") || modName.equals("x"))
                                break;
                            members.get(matchID).setName(modName);
                            break;
                            
                        case 2:
                            System.out.print("Enter New Member Gender(X to exit) : ");
                            String tempgender = modM.next().toUpperCase();
                            if(tempgender.equals("X") || tempgender.equals("x"))
                                break;
                            char modGender = 0;
                            if(tempgender.length() > 1){
                                System.out.println("\nInvalid Gender.\n");
                            }else{
                                if(tempgender.equals("F") ||tempgender.equals("M")){
                                   modGender= tempgender.charAt(0);
                                   members.get(matchID).setGender(modGender);
                                   break;
                            }else{
                                    System.out.println("\nInvalid Gender.\n");
                                 }
                           
                            } 
                            break;

                        case 3:
                            for(int i = 0; i < memranks.size(); i++)
                                {
                                  System.out.println((i+1)+"." + memranks.get(i).toString());
                                }
                                   int choice3= 0;
                              do{
                                    try{
                                        
                                        System.out.print("Enter your new choice (1-" + memranks.size() +") : ");
                                        String temp1 = modM.next();
                                        int modranks = Integer.parseInt(temp1);
                                        if(modranks <= memranks.size() && modranks > 0){
                                            choice3 = modranks;
                                            members.get(matchID).getMranks().setRanks(memranks.get(choice3 -1).getRanks());
                                            members.get(matchID).getMranks().setStatus(memranks.get(choice3 -1).getStatus());

                                            break;
                                        }else{
                                            System.out.println("\nInvalid Choice");
                                        }
                                    }catch(Exception e){}
                                }while(choice3 == 0);
                                break;
                            
                        case 4:
                                System.out.print("Enter New Member Join Date (X to exit): ");
                                String joinDate = modM.nextLine();
                                members.get(matchID).setJD(joinDate);
                                break;
                                
                        case 5:
                                return;
                                
                            default:
                                 System.out.println("\nInvalid Choice\n");
                                
                        }
                }
            }   
        }
    } 
    
   public static void searchMember(){
       ClearScreen.cls();
       Scanner srcM = new Scanner(System.in);
       int ch,MSearch;
       
       do{
           System.out.println("\n<Search Member Details>  \n");
           System.out.println("-----------------------------");
           System.out.println("   1.Member ID       ");
           System.out.println("   2.Member Name     ");
           System.out.println("   3.Member Gender   ");
           System.out.println("   4.Member Ranks    ");
           System.out.println("   5.Member Join Date");
           System.out.println("   6.Exit            ");
           System.out.println("-----------------------------");
           System.out.print("Select which to search : ");
           ch = srcM.nextInt();
           switch(ch){
               case 1:
                   do{
                       MSearch = -1;
                       try{
                           System.out.print("Enter MemberID (X to exit) :");
                           String sMemId = srcM.next();
                           if(sMemId.equals("X") || sMemId.equals("x"))
                               break;
                           
                           for(int i=0;i<members.size();i++){
                               if(sMemId.equals(members.get(i).getMemberID())){
                                   displayS(i);
                                   MSearch = 1;
                               }
                           }
                           
                       }catch(Exception e){}
                       validation(MSearch);
                   }while(MSearch == -1);
                   break;
                   
               case 2:
                   do{
                       MSearch = -1;
                       System.out.print("Enter Member Name (X to exit) : ");
                       String sMemName = srcM.next();
                       if(sMemName.equals("X") || sMemName.equals("x"))
                           break;
                       
                       for(int i=0;i<members.size();i++){
                           if(sMemName.equals(members.get(i).getName())){
                                   displayS(i);
                                   MSearch = 1;
                           }
                       }validation(MSearch);  
                   }while(MSearch == -1);
                   break;
                   
               case 3:
                   do{
                       MSearch = -1;
                       System.out.print("Enter Member Gender (X to exit) : ");
                       char sMemGen = srcM.next().charAt(0);
                       if(Character.toUpperCase(sMemGen) == 'X')
                           break;
                       
                       for(int i=0;i<members.size();i++){
                           if(Character.toUpperCase(sMemGen) == members.get(i).getGender()){
                                   displayS(i);
                                   MSearch = 1;
                           }
                       }validation(MSearch);  
                    }while(MSearch == -1);
                    break;
                    
               case 4:
                   for(int i = 0; i < memranks.size(); i++)
                                {
                                  System.out.println((i+1)+"." + memranks.get(i).toString());
                                  
                                }
                                  System.out.println("4.Exit");
                                  
                    do{
                       MSearch = -1;
                       System.out.print("Select Search Member Ranks");
                       String sMemRanks = srcM.next();
                       int srcranks = Integer.parseInt(sMemRanks);
                       
                       switch(srcranks){
                           case 1:
                           for(int i=0;i<members.size();i++){
                                if(members.get(i).getMranks().getRanks().equals("Normal")){
                                   
                                   displayS(i);
                                   MSearch = 1;
                                   break;
                                }
                            }
                           case 2:
                           for(int i=0;i<members.size();i++){
                                if(members.get(i).getMranks().getRanks().equals("Silver")){
                                   
                                   displayS(i);
                                   MSearch = 1;
                                   break;
                                }
                            }
                           
                           case 3:
                               for(int i=0;i<members.size();i++){
                                if(members.get(i).getMranks().getRanks().equals("Gold")){
                                   
                                   displayS(i);
                                   MSearch = 1;
                                   break;
                                }
                            }
                               
                           case 4:
                               return;
                               
                           default:
                               System.out.println("Invalid Choice");
                               
                               
                               
                       }
                    }while(MSearch == -1);
                    break;
      
               
               case 5:
                   do{
                      MSearch = -1;
                      System.out.print("Enter Member Join Date(X to exit) : ");
                      String sMemJD = srcM.next();
                       if(sMemJD.equals("X") || sMemJD.equals("x"))
                            break;
                   
                      for(int i=0;i<members.size();i++){
                            if(sMemJD.equals(members.get(i).getJD())){
                                   displayS(i);
                                   MSearch = 1;
                           }
                           
                        }validation(MSearch);  
                    }while(MSearch == -1);
                     break;
                     
               case 6:
                   return;
                    
               default:
                   System.out.println("Invalid Choice!");
                     
           }
        }while(ch != 8);
    }
   
   public static void deleteMember(){
       ClearScreen.cls();
       Scanner delM = new Scanner(System.in);
       int MSearch,delete = 0, i =0;
       do{
           MSearch = -1;
           try{
            System.out.println("\n\n                                       List of Member");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.println(" Member ID\t MemberName\t Gender\t       MemberRank\t Status\t      Join Date");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            for( i=0;i<members.size();i++){
            System.out.println(members.get(i).toString());
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            
               System.out.print("<Delete Member>");
               System.out.print("\nEnter MemberId to delete(X to exit):");
               String delMemID = delM.next();
               if(delMemID.equals("X") || delMemID.equals("x"))
                   return;
               for(i = 0;i < members.size();i++){
                   if(delMemID.equals(members.get(i).getMemberID())){
                       delete = i;
                       MSearch = 1;
                   }
               }displayS(i); 
           }catch(Exception e) {}
           validation(MSearch);
       }while(MSearch == -1);
       displayS(MSearch);
       System.out.print("Are You Sure?(Y/N) : ");
       do{
           char confirm = delM.next().charAt(0);
           if(confirm == 'N' || confirm == 'n'){
               System.out.println("Cancelled");
               break;
           }else if(confirm == 'Y'  || confirm == 'y'){
               members.remove(delete);
               System.out.println("Member deleted");
           }else{
               System.out.println("Invalid Choice");
           }
        }while(MSearch == -1); 
   }
   
   public static void validation(int MSearch){
       if(MSearch == -1){
           System.out.println("\nNo Result Found!\n");
       }
   }
   
   public static void displayS(int MSearch){
       System.out.println("\n          Infomation Found!");
       System.out.println("\n        Searched Member Details");
       System.out.println("-------------------------------------------");
       System.out.println(  "Member ID         :" + members.get(MSearch).getMemberID());
       System.out.println(  "Member Name       :" + members.get(MSearch).getName());
       System.out.println(  "Member Gender     :" + members.get(MSearch).getGender());
       System.out.println(  "Member Ranks      :" + members.get(MSearch).getMranks().getRanks());
       System.out.println(  "Member Status     :" + members.get(MSearch).getMranks().getStatus());
       System.out.println(  "Member Join Date  :" + members.get(MSearch).getJD());
       System.out.println("-------------------------------------------");
   }    


}