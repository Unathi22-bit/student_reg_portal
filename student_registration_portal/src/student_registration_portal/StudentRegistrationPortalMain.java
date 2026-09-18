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

    public static Scanner input = new Scanner(System.in);
        
    public static void main(String[] args) {
        
        String firstname;
        String lastname;
        String username;
        String password;
        String cellphoneNumber;
    
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
               System.out.println("Username successfully captured!\n");
               break;
           } else {
               System.out.println("Invaild username. It must contain an unerscore (_) and be on more than 5 characters");
               System.out.println("");
           }
       }
       
       // ask Student to capture cellphone number and validate it : checkCellPhoneNumber().
        while (true){
          System.out.print("Enter your cell phone number. (EG: +2781234567) : ");
          cellphoneNumber = input.nextLine();
          
          if (checkcellPhoneNumber(cellphoneNumber)){
              System.out.println("Cell phone number successfully captured.\n");
              break;
          } else {
              System.out.println("Cell phone number is incorrectly captured.");
              System.out.println("Please enter a valid South African number with international code(+27).\n");
          }
        }
        
        // capture and validate student password
        while (true){
          System.out.print("Enter your prefferred password: ");
          password = input.nextLine();
          
          if (checkPasswordComplexity(password)){
              System.out.println("Password successfully captured.\n");
              break;
          } else {
              System.out.println("Captured password length must be 8 characters long and  contain a Capital Letter, A Number and a Special Character.");
          }
        }
                
       
        System.out.println("\nRegistration successful! Would you like to : \n");
        
        // allow user to either login or exit application after successful registration.
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.println("Choose an option:  ");
        
           String studentChoice = input.nextLine().trim();
            
             switch (studentChoice) {
                 case "1" -> studentLogin();
                 case "2" -> {
                                System.out.println("Goodbye!");
                             }
                 default -> System.out.println("Invaild option, please try again.");
             }
        
        

        input.close();
    }
    
    public static boolean checkStudentUsername(String prefferredUsername) {
      return prefferredUsername.contains("_") && prefferredUsername.length() >= 4;
    }
    
    public static boolean checkcellPhoneNumber(String cellPhoneNumber) {

    String regex ="\\+27[0-9]{9}$";

    return cellPhoneNumber != null && Pattern.matches(regex, cellPhoneNumber);
    }
    
    public static boolean checkPasswordComplexity(String password){
        if (password == null || password.length() < 8){
            return false;
        }
        
        boolean hasCapital = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");
        
        return hasCapital && hasNumber && hasSpecial;
    }
    
          
    // Captures login credentials from the console and validates them against the last successfully registered user.
     public static void studentLogin(){
//         if (registerUser == null){
//             System.out.println("No registered user found. Please register first");
//             return;
//        }
         System.out.print("Enter username: ");
         String username = input.nextLine();
         
         System.out.print("Enter password: ");
         String password = input.nextLine();
         
         //call login service
     }
         
    
}
