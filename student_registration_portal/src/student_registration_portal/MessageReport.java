/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student_registration_portal;

import java.util.List;

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
    
}
