/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student_registration_portal;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class MessageReport {
    
    public static String longestMessage(List<Message>messages){
        String longest ="";
        for (Message item : messages){
            if (item.getMessage().length() > longest.length()){
                longest = item.getMessage();
            }
        }
        return longest.isEmpty()? "No messages found." : longest;
    }
    
    public static String searchByID(List<Message> messages, String id){
        
        for (Message item : messages){
            if (item.getMessageID().equals(id)){
                return "Recipient:" + item.getRecipient() + "\nMessage: " + item.getMessage();
            }
        }
        return "Message not found";
    }
    
    public static String searchByRecipient(List<Message> messages, String recipient){
        StringBuilder result = new StringBuilder();
        for (Message item : messages){
            if (item.getRecipient().equals(recipient)){
                result.append(item.getMessage()).append("\n");
            }
        }
        if(result.length() == 0) {
            return "No messages found.";
        }else {
            return result.toString();
        }
    }
    
    public static String displayReport(List<Message> message){
        StringBuilder report = new StringBuilder();
        for (Message item : message){
            report.append("Message Hash:").append(item.getMessageHash()).append("\n")
                    .append("Recipient: ").append(item.getRecipient()).append("\n")
                    .append("Message: ").append(item.getMessage()).append("\n\n");
        }
         if(report.length() == 0) {
            return "No messages found.";
        }else {
            return report.toString();
        }
    }
        
    public static void storeMessageMenu(Scanner scanner){
         boolean running = true;
         while(running){
             List<Message> stored = Message.readStoredMessages();
             System.out.println("\nStored Messages");
             System.out.println("1. Display sender and recipient");
             System.out.println("2. Display longest message");
             System.out.println("3. Search by message ID");
             System.out.println("4. Search by recipient");
             System.out.println("5. Delete by message hash");
             System.out.println("6. Search by message ID");
             System.out.println("7. Back");
             System.out.println("Choose an option: ");
             String choice = scanner.nextLine().trim();
             switch (choice) {
                 case "1":
                     if (stored.isEmpty()){
                         System.out.println("No messages found.");
                     }
                     for (Message item : stored) {
                         System.out.println("Sender: QuickChat user | Recipient: " + item.getRecipient());
                     }
                     break;
                 case "2":
                     System.out.println(longestMessage(stored));
                     break;
                 case "3":
                     System.out.print("Enter message ID: ");
                     System.out.println(searchByID(stored, scanner.nextLine().trim()));
                     break;  
                 case "4":
                     System.out.print("Enter recipient: ");
                     System.out.println(searchByRecipient(stored, scanner.nextLine().trim()));
                     break;
                 case "5":
                     System.out.print("Enter message hash: ");
                     System.out.println(Message.deleteStoredMessage(scanner.nextLine().trim())
                             ? "Message successfully deleted." : "Message not found.");
                     break;
                 case "6":
                     System.out.print(MessageReport.displayReport(stored));
                     break;
             }
         }
    }
}