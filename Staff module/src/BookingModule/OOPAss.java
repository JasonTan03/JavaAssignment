/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package sales.module;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author User
 */
public final class OOPAss {
       static ArrayList<Member> members = new ArrayList<Member>();
       static ArrayList<mRanks> memranks = new ArrayList<mRanks>();
       
    public  void clearJavaConsoleScreen() {
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

    public String alignCenter(String text, int totalWidth){
        // Calculate the number of spaces needed on each side to center-align the text
        int padding = (totalWidth - text.length()) / 2; 
        int odd = (totalWidth - text.length()) % 2; // Check odd
        
        // Create a format string with padding on both sides
        String format = "%" + padding + "s%s%" + padding + "s"; 
        if(odd != 0)
            format +=  " ";
            
        // Use String.format to center-align the text
        return String.format(format, "", text, "");
    }


    
    public static void main(String[] args) throws FileNotFoundException{
      String filepath1 = "C:/Users/User/Documents/NetBeansProjects/OOPAss/Ranks.txt";
      String filepath2 = "C:/Users/User/Documents/NetBeansProjects/OOPAss/Memberlist.txt";
      
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
     
      sc1.close();
      sc2.close();
      
      MemberModule();

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








}