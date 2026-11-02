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
    
}
