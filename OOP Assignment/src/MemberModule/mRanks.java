/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MemberModule;

import static TicketingSystem.TicketingSystem.memranks;

/**
 *
 * @author User
 */
public class mRanks {
    private String ranks;
    private String status;
    
    //constructor
    public mRanks(String ranks, String status) {
        this.ranks = ranks;
        this.status = status;
    }
    
    //getter
    public String getRanks() {
        return ranks;
    }

    public String getStatus() {
        return status;
    }
    
    //setter

    public void setRanks(String ranks) {
        this.ranks = ranks;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   @Override
    public String toString() {
        return String.format("%-15s  %-7s",ranks,status);
    }
    
    public static void displayRanks(){
        for(int i=0;i<memranks.size();i++){
            System.out.println(memranks.get(i).toString());
        }
    }







    
    
    
}



