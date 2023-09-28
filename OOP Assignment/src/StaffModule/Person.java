/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StaffModule;

/**
 *
 * @author Ai
 */
public abstract class Person {
    private String name;
    private char gender;
    private String joinDate;
    
    //Contructor  
    public Person(String name, char gender,String joinDate){
        this.name=name;
        this.gender=gender;
        this.joinDate=joinDate;
    }
    
    //Setter/Mutator
    public void setName(String name){
        this.name=name;
    }
    public void setGender(char gender){
        this.gender=gender;
    }
    public void setJD(String joinDate){
        this.joinDate=joinDate;
    }
    
    //Getter/Accesor
    public String getName(){
        return  name;
    }
    public char getGender(){
        return gender;
    }
    public String getJD(){
        return joinDate;
    }
    
    //To String
    public abstract String toString();
}
