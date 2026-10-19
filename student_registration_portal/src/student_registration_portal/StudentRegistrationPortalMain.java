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
         
         if (newUser.checkUserName()
                 && newUser.checkPasswordComplexity()&& newUser.checkCellPhoneNumber()) {
             registerUser = newUser;
         }
     }
      
     public static void loginUser(){
         if (registerUser == null){
             System.out.println("No registered user found. Please register first");
             return;
        }
         System.out.print("Enter username: ");
         String username = scanner.nextLine();
         
         System.out.print("Enter password: ");
         String password = scanner.nextLine();
         
         boolean loginSuccesful = registerUser.loginUser(username, password);
         System.out.println(registerUser.returnLoginStatus(username, password));
         if(loginSuccesful){
             quickChat();
         }
     }
     
   public static void quickChat(){
       System.out.println("Welcome to QuickChat.");
       System.out.println("How many messages would you like to enter?");
       int numberOfMessages;
       try{
           numberOfMessages = Integer.parseInt(scanner.nextLine().trim());
           if(numberOfMessages < 0){
               System.out.println("Please enter a valid number.");
               return;
           }
       }catch (NumberFormatException e){
           System.out.println("Please enetr a valid number.");
           return;
       }
       
     
       Message messageManager = new Message("","");
       boolean running = true;
       while (running){
           System.out.println("\nQuickChat Menu");
           System.out.println("1. Send Messages");
           System.out.println("2. Show recently sent messages");
           System.out.println("3. Quit");
           System.out.println("Choose an option: ");
           String choice = scanner.nextLine().trim();
           
           switch (choice){
               case "1":
                   for (int i = 0; i < numberOfMessages; i++){
                       System.out.print("Enter recipient cell number (include international code): ");
                       String recipient = scanner.nextLine().trim();
                       System.out.print("Enter your message (maximum 250 characters): ");
                       String messageText = scanner.nextLine();
                       Message newMessage = new Message(recipient, messageText);
                       
                       if(!newMessage.checkMessageID()){
                           System.out.println("Message ID is incorrectly formatted.");
                           i--;
                           continue;
                       }
                       if (!newMessage.checkRecipientCell()){
                           System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                           i--;
                           continue;
                       }
                       if (!newMessage.checkMessageLength().equals("Message ready to send.")){
                           System.out.println("Please enter a message of less than 250 characters");
                           i--;
                           continue;
                       }
                       
                       System.out.println(newMessage.checkMessageLength());
                       System.out.println(newMessage.SentMessage(scanner));
                       System.out.println("Message ID: " + newMessage.getMessageID());
                       System.out.println("Message Hash: " + newMessage.getMessageHash());
                       System.out.println("Recipient: " + newMessage.getRecipient());
                       System.out.println("Message: " + newMessage.getMessage());
                   }
                   System.out.println("Total messages sent:" + messageManager.returnTotalMessage());
                   break;
                case "2":
                   System.out.println("Coming Soon.");
                   break;
                case "3":
                    running = false;
                    System.out.println("Goodbye from QuickChat.");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");    
           }
       }
   }
}