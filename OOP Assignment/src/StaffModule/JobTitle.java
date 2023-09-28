/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StaffModule;

import static TicketingSystem.TicketingSystem.jobList;

/**
 *
 * @author Ai
 */
public class JobTitle {
    private String position;
    private double salary;
    private String shift;
    
    //Constructor
    public JobTitle (String position,double salary,String shift){
        this.position=position;
        this.salary=salary;
        this.shift=shift;
    }
    
    //Getter/Accesor
    public String getPosition(){
        return position;
    }
    public double getSalary(){
        return salary;
    }
    public String getShift(){
        return shift;
    }
    
    //Getter/Mutator
    public void setPosition(String position){
        this.position=position;
    }
    public void setSalary(double salary){
        this.salary=salary;
    }
    public void setShift(String shift){
        this.shift=shift;
    }

    
    public String toString(){
        return String.format("%-13s   %-8.2f     %-10s", position,salary,shift);
    }

    //method
    public static void displayJob(){
        for(int i=0; i<jobList.size();i++){
            System.out.println(jobList.get(i).toString());
        }
    }
}
