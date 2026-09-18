/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package student_registration_portal;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author 
 * 
 *Main 
 *Entry point of the application drives a simple menu that lets a person register a new user then log in, calling the validation, registration and login methods on the Login class.
 */
public class StudentRegistrationPortalMain {

    
    public static Scanner scanner = new Scanner(System.in);
    public static Login registerUser = null;
    
     public static void main(String[] args){
         boolean running = true;
         
         while (running){
             System.out.println("===== Welcome to the Student Portal =======\n");
            System.out.println("1. Regiter a new student");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("Choose an option:  ");
            
            String choice = scanner.nextLine().trim();
            
             switch (choice) {
                 case "1" -> registerNewUser();
                 case "2" -> loginUser();
                 case "3" -> {
                     running = false;
                     System.out.println("Goodbye!");
                 }
                 default -> System.out.println("Invaild option, please try again.");
                     
             }
         }
         
         scanner.close();
     }
     
     //Captures user details from the console and registers them using the Login class.
     public static void registerNewUser(){
         System.out.print("Enter first name : ");
         String firstName = scanner.nextLine();
         
         System.out.print("Enter last name : ");
         String lastName = scanner.nextLine();
         
         System.out.print("Enter prefferred  username(must contain an underscore, max 5 characters) : ");
         String username = scanner.nextLine();
         
         System.out.print("Enter password(min 8 chars, a captial letter, a number, a special character) : ");
         String password = scanner.nextLine();
         
         System.out.print("Enter South African cell phone number(format +27XXXXXXX): ");
         String cellPhoneNumber = scanner.nextLine();
         
         Login newUser = new Login(firstName, lastName, username,password, cellPhoneNumber);
         String result = newUser.registerUser();
         System.out.println(result);
         
         //Only keep the user "on file" if registration fully succeeded
         if (newUser.checkUserName()
                 && newUser.checkPasswordComplexity()&& newUser.checkCellPhoneNumber()) {
             registerUser = newUser;
         }
     }
      
    // Captures login credentials from the console and validates them against the last successfully registered user.
     public static void loginUser(){
         if (registerUser == null){
             System.out.println("No registered user found. Please register first");
             return;
        }
         System.out.print("Enter username: ");
         String username = scanner.nextLine();
         
         System.out.print("Enter password: ");
         String password = scanner.nextLine();
         
         System.out.println(registerUser.returnLoginStatus(username, password));
     }
}
