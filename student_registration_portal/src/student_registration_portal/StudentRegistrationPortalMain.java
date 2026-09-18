/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package student_registration_portal;

import java.util.Scanner;

/**
 * @author 
 * 
 *Main 
 *Entry point of the application drives a simple menu that lets a person register a new user then log in, calling the validation, registration and login methods on the Login class.
 */
public class StudentRegistrationPortalMain {

    public static Scanner input = new Scanner(System.in);
        
    public static void main(String[] args) {
        
        String firstname;
        String lastname;
        String username;
        String password;
        String cellPhoneNumber;
    
        System.out.println("===== Welcome to the Student Registration Portal =======\n");
        
        // Ask and capture for student details 
        System.out.print("Enter your firstname: ");
        firstname = input.nextLine();
        
        System.out.print("Enter your surname: ");
        lastname = input.nextLine();
        
        System.out.println("Thank you : " +firstname+" "+lastname+".\n");
        
       // Ask student username and validate it in checkStudentUsername() method.
       while (true) {
           System.out.print("Please enter your preferred username: ");
           username = input.nextLine();
           
           if (checkStudentUsername(username)) {
               System.out.println("Username successfully captured!");
               break;
           } else {
               System.out.println("Invaild username. It must contain an unerscore (_) and be on more than 5 characters");
               System.out.println("");
           }
       }
       
       
        
        input.close();
    }
    
    public static boolean checkStudentUsername(String prefferredUsername) {
      return prefferredUsername.contains("_") && prefferredUsername.length() >= 4;
    }
    
}
