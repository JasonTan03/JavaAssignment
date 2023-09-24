/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package staff.module;

import java.util.Scanner;
import static TicketingSystem.TicketingSystem.jobList;
import static TicketingSystem.TicketingSystem.staffList;

/**
 *
 * @author Ai
 */

public class Staff extends Person{
    private String staffID;
    private JobTitle jobTitle;
    private String password;
    //private int loginAmount;
    private static int maxStaffID = 1000;
    private boolean isManager;
    private static int currentStaff = 0;
    
    //Constructor
    public Staff(String name, char gender, String joinDate, JobTitle jobTitle, String password, boolean isManager){
        super(name, gender, joinDate);
        if(isManager){
            staffID = "ST" + maxStaffID + "M";
        }else{
            staffID = "ST" + maxStaffID;
        }
        this.jobTitle=jobTitle;
        this.password=password;
        this.isManager = isManager;
        maxStaffID++;
    }
    
    //Getter/Accesor
    public String getStaffID(){
        return staffID;
    }
    
    public JobTitle getJobTitle(){
        return jobTitle;
    }
    
    public String getPassword(){
        return password;
    }
    
    public boolean getIsManager(){
        return isManager;
    }
    public int getCurrentStaff(){
        return currentStaff;
    }
    
    public static int getMaxStaffID(){
        return maxStaffID;
    }
    
    //Setter/Mutator
    public void setPassword(String password){
        this.password=password;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    
    //To String
    @Override
    public String toString(){
        return String.format("%-10s       %-20s%-4c          %-11s      %-14s",staffID,getName(),getGender(),getJD(),jobTitle);
    }
    
    //Method
    public static boolean login(){
        Scanner input = new Scanner(System.in);
        int matchSearch, matchPassword;   
        while(true)
        {
            matchSearch = -1;
            System.out.print("\nEnter Staff Id : ");
            String checkId = input.next();
            
            for (int i=0; i<staffList.size(); i++) 
            {
                if (checkId.equals(staffList.get(i).getStaffID())) 
                {
                    currentStaff = i;
                    matchSearch = 1;
                }
            }
                if(matchSearch == -1)
                {
                    System.out.println("\nIncorrect Staff ID, Please enter again.");
                }
                else
                {
                    while(true)
                    {
                        matchPassword = -1;
                        System.out.print("Enter Password : ");
                        String checkPass =input.next();
                        for (int j=0; j < staffList.size(); j++)
                            if (checkPass.equals(staffList.get(j).getPassword()) && (checkId.equals(staffList.get(j).getStaffID()))) 
                            {
                                if(staffList.get(j).getIsManager()){
                                    matchPassword = 1;
                                    System.out.println("\nLogin Succesful as Manager\n");
                                    return true;
                                }else{
                                    matchPassword = 1;
                                    System.out.println("\nLogin Succesful\n");
                                    return true;
                                }
                            }
                        if(matchPassword == -1)
                        {
                            System.out.println("\nIncorrect password, Please enter again");
                        }
                    }
                }
            
        }
    
    }
    
    public static void displayStaff(){
        System.out.println("\n\n                                                 List of Staff");
        System.out.println("===================================================================================================================");
        System.out.println("Staff ID \t Staff Name \t   Gender \t   Join Date \t    Position \t    Salary(RM) \t      Shift");
        System.out.println("===================================================================================================================");
        for(int i=0; i<staffList.size();i++){
            System.out.println(staffList.get(i).toString());
        }
        System.out.println("===================================================================================================================\n\n");

    }
    
    public static void addStaff(){
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Staff Id : ST" + Staff.getMaxStaffID());
            System.out.print("Enter name (Enter X to exit) : ");
            String name=input.nextLine();
            
            if((name.toUpperCase()).equals("X"))
                return;
            
            char gender = 0;
            while(true){
                System.out.print("\nEnter gender : ");
                String tempGender = input.next().toUpperCase();
                if(tempGender.length()>1){
                    System.out.println("\nInvalid Gender Please Enter Again.\n");
                }else{
                    if(tempGender.equals("F")||tempGender.equals("M")){
                        gender = tempGender.charAt(0);
                        break;
                    }else{
                        System.out.println("\nInvalid Gender Please Enter Again.");
                    }
                }
            }
            
            System.out.print("\nEnter Join Date (Follow format \"DD/MM/YYY\") : ");
            String joinDate = input.next();
           
            System.out.println("\nList of Position :");
            for(int j=0; j < jobList.size(); j++)
            {
                System.out.println((j+1)+". " + jobList.get(j).toString());
            }
            int choice = 0;
            
            do{
                try{
                    System.out.print("Enter choice (1-"+jobList.size()+") : ");
                    String tempChoice = input.next();
                    if(tempChoice.length()>1){
                        System.out.println("\nInvalid Option Please Select Again!\n");
                    }else{
                        int tempChoiceint = Integer.parseInt(tempChoice);
                        if(tempChoiceint <= jobList.size() && tempChoiceint > 0){

                            choice = tempChoiceint;
                            break;
                        }else{
                            System.out.println("\nInvalid Option Please Select Again!");
                        }
                    }
                }catch(Exception e) {}
            }while(choice == 0);
            
            
            String password = "";
            
            while(true){
                System.out.print("\nEnter Password (Must contain at least 1 character, 1 number and length more than 4): ");
                String tempPass1 = input.next();
                if(tempPass1.length()<4 || tempPass1.length()>10){
                    System.out.println("\nPassword Length too short/long.");
                }
                else
                {
                    int countDigit = 0, countChar = 0;
                    for(int i=0;i<tempPass1.length();i++){
                        char checkPass = tempPass1.charAt(i);
                        if(Character.isDigit(checkPass))
                        {
                            countDigit++;
                        }
                        else if(Character.isLowerCase(checkPass))
                        {
                            countChar++;
                        }
                    }
                    if(countDigit>1 && countChar>1)
                    {
                        System.out.print("Comfirm Password : ");
                        String tempPass2 = input.next();
                        if(tempPass2.equals(tempPass1))
                        {
                            password = tempPass1;
                            break;
                        }
                        else
                        {
                            System.out.println("\nPlese enter correct Password!");
                        }
                    }
                }
            }
            
            if(choice == 4){
                staffList.add(new Staff(name, gender, joinDate,jobList.get(choice-1),password,true));
                System.out.println("New Staff Added!");
            }
            else
            {
                staffList.add(new Staff(name, gender, joinDate,jobList.get(choice-1),password, false));
                System.out.println("New Staff Added!");
            }
            
            do{
                System.out.print("\nContinue Adding New Staff? (Y/N) : ");
                String cont=input.next();

                if(cont.length()<2){
                    if((cont.toUpperCase()).equals("Y")){
                        break;
                    }
                    else if((cont.toUpperCase()).equals("N"))
                    {
                        System.out.println("");
                        return;
                    }
                }else{
                    System.out.println("\nInvalid Option Please Enter Again");
                }
            }while(true);
            input.nextLine();
        }
    }
    
    public static void modifyStaff(){
        Scanner input = new Scanner(System.in);
        
        while(true){
            displayStaff();
            int matchSearch = -1;
            int matchID = 0;
            System.out.print("Select staff to be modify : ");
            String findStaffId = input.next();
            for (int i=0; i<staffList.size(); i++) 
            {
                if (findStaffId.equals(staffList.get(i).getStaffID())) 
                {
                    matchSearch = 1;
                    matchID = i;
                }
                
            }
            if(matchSearch == -1)
            {
                    System.out.println("Staff ID Not Found, Please Enter again.");
            }
            else
            {
                while(true)
                {
                        System.out.println("\n===Modify Staff Detail===");
                        System.out.println("   1. Staff Name");
                        System.out.println("   2. Staff Gender");
                        System.out.println("   3. Staff Join Date");
                        System.out.println("   4. Staff Job");
                        System.out.println("   5. Staff Password");
                        System.out.println("   6. Exit");
                        System.out.println("===========================");
                        System.out.print("Select an option to modify : ");
                        String opt = input.next();
                        char option = opt.charAt(0);
                        input.nextLine();
                        switch(option)
                        {
                            case '1': 
                                    System.out.println("Current Name : " + staffList.get(matchID).getName());
                                    System.out.print("Enter New Staff Name (Enter \"X\" to Exit): ");
                                    String newName = input.nextLine();
                                    if((newName.toUpperCase()).equals("X"))
                                        break;
                                    staffList.get(matchID).setName(newName);
                                    displaySearch(matchID);
                                break;

                            case '2': 
                                    while(true){
                                        System.out.print("Enter New Staff Gender (Enter \"X\" to Exit): ");
                                        String tempGender = input.next().toUpperCase();
                                        if((tempGender.toUpperCase()).equals("X"))
                                            break;
                                        char newGender = 0 ;
                                        if(tempGender.length()>1){
                                            System.out.println("\nInvalid Gender Please Enter Again.\n");
                                        }else{
                                            if(tempGender.equals("F")||tempGender.equals("M")){
                                                newGender = tempGender.charAt(0);
                                                staffList.get(matchID).setGender(newGender);
                                                displaySearch(matchID);
                                                break;
                                            }else{
                                                System.out.println("\nInvalid Gender Please Enter Again.\n");
                                            }
                                        }
                                    }
                                break;

                            case '3': System.out.print("Enter New Staff Join Date : ");
                                    String joinDate = input.nextLine();
                                    staffList.get(matchID).setJD(joinDate);
                                    displaySearch(matchID);
                                break;

                            case '4': int check=0;
                                    while(check ==0){
                                        try{
                                            System.out.println("\n==== Modify Job Detail ====");
                                            System.out.println("      1. Job Title");
                                            System.out.println("      2. Job Salary");
                                            System.out.println("      3. Job Shift");
                                            System.out.println("      4. Exit");
                                            System.out.println("============================");
                                            System.out.print("Select an Option : ");
                                            String opt2 = input.next();
                                            char option2 = opt2.charAt(0);
                                            input.nextLine();
                                            switch(option2){
                                                case '1':   System.out.print("Enter New Job Position : ");
                                                            String newP = input.nextLine();
                                                            int updated = 0;
                                                            if((newP.toUpperCase()).equals("X"))
                                                                break;
                                                            for(int i=0; i < jobList.size(); i++){
                                                              if(newP.equals(jobList.get(i).getPosition()))
                                                              {
                                                                    staffList.get(matchID).getJobTitle().setPosition(newP);
                                                                    displaySearch(matchID);
                                                                    updated = 1;
                                                              }  
                                                            }
                                                            if(updated == 0){
                                                                System.out.println("\nPosition Not found");
                                                            }
                                                    break;

                                                case '2':   try{
                                                                System.out.print("Enter New Job Salary : ");
                                                                double newSalary = input.nextDouble();
                                                                staffList.get(matchID).getJobTitle().setSalary(newSalary);
                                                                displaySearch(matchID);
                                                            } catch(Exception e){}
                                                    break;

                                                case '3':   System.out.println("\n====== Job Shift ======");
                                                            for(int i=0; i<jobList.size();i++)
                                                            {
                                                                System.out.println((i+1)+". "+jobList.get(i).getShift());
                                                            }
                                                            System.out.println((jobList.size()+1)+". Exit");
                                                            System.out.println("=======================");
                                                            while(true){
                                                                System.out.print("Enter New Job Shift : ");
                                                            
                                                                try{
                                                                    String tempNewShift = input.next();
                                                                    if(tempNewShift.length()>1 || Character.isAlphabetic(tempNewShift.charAt(0))){
                                                                        System.out.println("\nInvalid Option Please Select Again!\n");
                                                                    }else{
                                                                        int newShift = Integer.parseInt(tempNewShift);
                                                                        if(newShift == (jobList.size()+1))
                                                                            break;
                                                                        if(newShift <= jobList.size() && newShift > 0){

                                                                            staffList.get(matchID).getJobTitle().setShift(jobList.get(newShift-1).getShift());
                                                                            displaySearch(matchID);
                                                                            break;
                                                                        }else{
                                                                            System.out.println("\nInvalid Option Please Select Again!");
                                                                        }
                                                                    }
                                                                }catch(Exception e) {}
                                                            }
                                                    break;

                                                case '4': check = 1; 
                                                    break;
                                            }
                                        } catch(Exception e){}
                                    }
                                break;

                            case '5': System.out.println("Enter Password to Proceed : ");
                                    String checkPassWord = input.next();
                                    if((checkPassWord.toUpperCase()).equals("X"))
                                        break;
                                    if(checkPassWord.equals(staffList.get(matchID).getPassword()))
                                    {
                                        System.out.print("Enter New Staff Password : ");
                                        String tempPass1 = input.next();
                                        String newPassword;
                                        if((tempPass1.toUpperCase()).equals("X"))
                                            break;
                                        if(tempPass1.length()<4 || tempPass1.length()>10){
                                            System.out.println("\nPassword Length too short/long.");
                                        }
                                        else
                                        {
                                            int countDigit = 0, countChar = 0;
                                            for(int i=0;i<tempPass1.length();i++){
                                                char checkPass = tempPass1.charAt(i);
                                                if(Character.isDigit(checkPass))
                                                {
                                                    countDigit++;
                                                }
                                                else if(Character.isLowerCase(checkPass))
                                                {
                                                    countChar++;
                                                }
                                            }
                                            if(countDigit>1 && countChar>1)
                                            {
                                                System.out.print("Comfirm Password : ");
                                                String tempPass2 = input.next();
                                                if(tempPass2.equals(tempPass1))
                                                {
                                                    newPassword = tempPass1;
                                                    staffList.get(matchID).setPassword(newPassword);
                                                    break;
                                                }
                                                else
                                                {
                                                    System.out.println("\nPlese enter correct Password!");
                                                }
                                            }
                                        }  
                                    }
                                    else
                                    {
                                        System.out.println("\nIncorrect Staff Password.\n");
                                    }
                                break;

                            case '6': return;

                            default: System.out.println("\nInvalid Option, Please Enter Again. \n");
                        }
                }   
            }
        }
    }
    
    public static void searchStaff(){
        Scanner input = new Scanner(System.in);
        int opt, matchSearch;
        do{
            System.out.println("\n  Search Staff by : ");
            System.out.println("=======================");
            System.out.println("   1. Staff ID");
            System.out.println("   2. Staff Name");
            System.out.println("   3. Staff Gender");
            System.out.println("   4. Staff Join Date");
            System.out.println("   5. Staff Position");
            System.out.println("   6. Staff Salary");
            System.out.println("   7. Staff Shift");
            System.out.println("   8. Exit");
            System.out.println("=======================");
            System.out.print("Please Enter An Option : ");
            opt = input.nextInt();
            switch(opt){
                case 1:
                    do{
                        matchSearch = -1;
                        try{
                            System.out.print("Enter Staff Id (Enter X to exit) : ");
                            String findStaffId = input.next();
                            if(findStaffId.equals("X"))
                            {
                                break;
                            }
                        
                            for (int i=0; i<staffList.size(); i++) 
                            {
                                if (findStaffId.equals(staffList.get(i).getStaffID())) 
                                {
                                    displaySearch(i);
                                    matchSearch = 1;
                                }
                            }
                        
                        }catch(Exception e) {}
                        validation(matchSearch);
                    }while(matchSearch == -1);
                break;
                
                case 2:
                    do{
                        matchSearch = -1;
                        System.out.print("Enter Staff Name (Enter X to exit) : ");
                        String findName = input.next();
                        if(findName.toUpperCase().equals("X")){
                            break;
                        }             
                        for (int i=0; i<staffList.size(); i++) 
                        {
                            if (findName.equals(staffList.get(i).getName())) 
                            {
                                displaySearch(i);
                                matchSearch = 1;
                            }
                        }
                        validation(matchSearch);
                    }while(matchSearch == -1);
                    break;
                case 3:
                    do{
                        matchSearch = -1;
                        System.out.print("Enter Staff Gender (Enter X to exit) : ");
                        char findStaffGender = input.next().charAt(0);
                        if(Character.toUpperCase(findStaffGender) == 'X'){
                            break;
                        }             
                        for (int i=0; i<staffList.size(); i++) 
                        {
                            if (Character.toUpperCase(findStaffGender) == staffList.get(i).getGender()) 
                            {
                                displaySearch(i);
                                matchSearch = 1;
                            }
                        }
                        validation(matchSearch);
                    }while(matchSearch == -1);
                    break;
                case 4:
                    do{
                        matchSearch = -1;
                        System.out.print("Enter Staff Join Date (Enter X to exit) : ");
                        String findStaffId = input.next().toUpperCase();
                        if(findStaffId.equals("X")){
                            break;
                        }             

                        for (int i=0; i<staffList.size(); i++) 
                        {
                            if (findStaffId.equals(staffList.get(i).getStaffID())) 
                            {
                                displaySearch(i);
                                matchSearch = 1;
                            }
                        }
                        validation(matchSearch);
                    }while(matchSearch == -1);
                    break;
                case 5:
                    do{
                        matchSearch = -1;
                        System.out.print("Enter Staff Position (Enter X to exit) : ");
                        String FindStaffPosition = input.next();
                        if(FindStaffPosition.toUpperCase().equals("X")){
                            break;
                        }             
                        for (int i=0; i<staffList.size(); i++) 
                        {
                            if (FindStaffPosition.equals(staffList.get(i).getJobTitle().getPosition())) 
                            {
                                displaySearch(i);
                                matchSearch = 1;
                            }
                        }
                        validation(matchSearch);
                    }while(matchSearch == -1);
                    break;
                case 6:
                    do{
                        matchSearch = -1;
                        try{
                            System.out.print("Enter Staff Salary (Enter X to exit) : ");
                            String tempFindStaffSalary = input.next().toUpperCase();
                            if(tempFindStaffSalary.equals("X")){
                                break;
                            } 
                            int findStaffSalary = Integer.parseInt(tempFindStaffSalary);
                            System.out.println("\n 1. Find Staff More or Equal to Salary Rm " + findStaffSalary);
                            System.out.println(" 2. Find Staff Less or Equal to Salary Rm " + findStaffSalary);
                            try{
                                System.out.print("Please Enter an Option : ");
                            
                                String tempOpt = input.next().toUpperCase();
                                if(tempOpt.equals("X")){
                                    break;
                                }
                                int opt1 = Integer.parseInt(tempOpt);


                                switch(opt1){
                                    case 1:
                                    for (int i=0; i<staffList.size(); i++) 
                                    {
                                        if (findStaffSalary <= staffList.get(i).getJobTitle().getSalary()) 
                                        {
                                            displaySearch(i);
                                            matchSearch = 1;
                                        }
                                    }
                                    break;
                                    case 2:
                                        for (int i=0; i<staffList.size(); i++) 
                                    {
                                        if (findStaffSalary >= staffList.get(i).getJobTitle().getSalary()) 
                                        {
                                            displaySearch(i);
                                            matchSearch = 1;
                                        }
                                    }
                                    break;
                                }
                            }catch(Exception e) {}
                        }catch(Exception e) {}
                        validation(matchSearch);
                    }while(matchSearch == -1);
                    break;
                case 7:
                    do{
                        matchSearch = -1;
                        System.out.println("\n====== Job Shift ======");
                        for(int i=0; i<jobList.size();i++)
                        {
                            System.out.println((i+1)+". "+jobList.get(i).getShift());
                        }
                        System.out.println("=======================");
                        System.out.print("Enter Staff Shift (Enter X to exit) : ");
                        String findStaffShift = input.next();
                        if(findStaffShift.toUpperCase().equals("X")){
                            break;
                        }
                        try{
                            if(findStaffShift.length()>1 || Character.isAlphabetic(findStaffShift.charAt(0))){
                                System.out.println("\nInvalid Option Please Select Again!\n");
                            }else{
                                int findShift = Integer.parseInt(findStaffShift);
                                String shift = jobList.get(findShift).getShift();
                                for (int i=0; i<staffList.size(); i++) 
                                {
                                    if (shift.equals(staffList.get(i).getJobTitle().getShift())) 
                                    {
                                        displaySearch(i);
                                        matchSearch = 1;
                                    }
                                }
                            }
                        }catch(Exception e) {}
                        validation(matchSearch);
                    }while(matchSearch == -1);
                    break;
                case 8: return;
                default: System.out.println("\nInvalid Option, Please Enter Again. \n");
            }
        }while(opt != 8);
               
    }
    
    public static void deleteStaff(){
        Scanner input = new Scanner(System.in);
        int matchSearch, delete = 0, i = 0;
        do{
            matchSearch = -1;
            try{
                System.out.print("Enter Staff Id to Delete (Enter X to exit) : ");
                String findStaffId = input.next();
                if(findStaffId.equals("X") || findStaffId.equals("x"))
                {
                    return;
                }
                for (i=0; i<staffList.size(); i++) 
                {
                    if (findStaffId.equals(staffList.get(i).getStaffID())) 
                    {
                        delete = i;
                        matchSearch = 1;
                    }
                }
                displaySearch(i);
            }catch(Exception e) {}
            validation(matchSearch);
        }while(matchSearch == -1);
        displaySearch(matchSearch);
        System.out.print("Are You Sure to Delete This Staff? (Y/N) : ");
        do{
            char comfrim = input.next().charAt(0);
            if(Character.toUpperCase(comfrim) == 'N'){
                break;
            }else if(Character.toUpperCase(comfrim) == 'Y'){
                staffList.remove(delete);
                System.out.println("Staff has been Deleted.");
                System.out.println("Updated Staff List");
                Staff.displayStaff();
            }else{
                System.out.println("\nInvalid Option Please Enter Again! ");
            }             
        }while(matchSearch == -1);
        
    }
    
    public static void displayPassword(){
        System.out.println("\n\n      Staff Password");
        System.out.println("==================================================");
        System.out.println("Staff ID \t Staff Name \t    Password");
        System.out.println("==================================================");
        for(int i=0; i<staffList.size();i++){
            System.out.println(staffList.get(i).getStaffID() +"\t\t "+ staffList.get(i).getName() +"\t    "+ staffList.get(i).getPassword());
        }
        System.out.println("==================================================\n\n");
    }
    
    public static void validation(int matchSearch){
        if(matchSearch == -1){
            System.out.println("\nNo matching result found, please enter again.\n");
        }
    }
    
    public static void displaySearch(int matchSearch){
        System.out.println("\n               Staff Detail");
        System.out.println("===========================================");
        System.out.println("Staff ID        : " + staffList.get(matchSearch).getStaffID());
        System.out.println("Staff Name      : " + staffList.get(matchSearch).getName());
        System.out.println("Staff Gender    : " + staffList.get(matchSearch).getGender());
        System.out.println("Staff Join Date : " + staffList.get(matchSearch).getJD());
        System.out.println("Staff Position  : " + staffList.get(matchSearch).getJobTitle().getPosition());
        System.out.println("Staff Salary    : " + staffList.get(matchSearch).getJobTitle().getSalary());
        System.out.println("Staff Name      : " + staffList.get(matchSearch).getJobTitle().getShift());
        System.out.println("===========================================");
    }
}
