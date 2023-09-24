/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.assignment;

/**
 *
 * @author user
 */
import java.time.Year;
public class Date {

    private int day;
    private int month;
    private int year;
    public Date()
    {
        
    }
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%-4d", day, month, year);
    }

    public boolean checkDate()
    {
       int yearNow = Year.now().getValue();

        if (year < yearNow) 
        {
            System.out.printf("Year enter cannot less then Year "+yearNow+".Please reenter.\n");
        } 
        else if (day <= 0 || day > 31) 
        {
            System.out.printf("Day should be between 1 to 31.Please reenter.\n");

            
            if (month < 1 || month > 12) 
            {
                System.out.printf("Month should be between 1 to 12.Please reenter.\n");
            }

        } 
        else if (month < 1 || month > 12) 
        {
            System.out.println("Month should be between 1 to 12.Please reenter.\n");

            if (day <= 0 || day > 31) 
            { 
                System.out.printf("Day should be between 1 to 31.Please reenter.\n");
            }
        } 
        else if (month == 2)
        {

            if ((year % 4 == 0) && day > 29) 
            {
                System.out.printf("Day for month %d in Year %d only have 29 days.Please reenter.\n", month, year);

            } 
            else if ((year % 4 != 0) && day > 28) 
            {
                System.out.printf("Day for month %d in Year %d only have 28 days.Please reenter.\n", month, year);

            } 
            else 
            {
                return true;
            }
        } 
        else if (month == 4 || month == 6 || month == 9 || month == 11) {

            if (day <= 0 || day > 30) 
            {
                System.out.printf("Day should be between 1 to 30.Please reenter.\n");
            } 
            else 
            {
                return true;
            }
        } 
        else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                || month == 10 || month == 12) {
            if (day <= 0 || day > 31) 
            {
                System.out.printf("Day should be between 1 to 31.Please reenter.\n");
            } 
            else 
            {
                return true;
            }
        } 
        else 
        {
            return true;
        }
        return false;

    }

}
