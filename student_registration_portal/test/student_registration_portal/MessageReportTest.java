/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student_registration_portal;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Student
 */
public class MessageReportTest {
    
    @Test
    public void testLongestMessage(){
        List<Message> message = new ArrayList<>();
        
        message.add(new Message("+27718693002", "Hi Mike"));
        message.add(new Message("+27718693002", "Where are you? You are late! I have asked you to be o time."));
        message.add(new Message("+27718683002", "It is dinner time!"));

        String expected ="Where are you? I have asked you to be on time.";

        assertEquals(expected, MessageReport.longestMessage(message));        
    }
    
    @Test
    public void testLongestMessageEmpytList(){
        List<Message> messages = new ArrayList<>();
        
        assertEquals("No message found.", MessageReport.longestMessage(messages));
    }
    
    @Test
    public void testSearchByID(){
        List<Message> messages = new ArrayList<>();
        
        Message message = new Message("+27718693002", "Hi Mike");
        messages.add(message);
        
        String expected ="Recipient: +27718693002\nMessage: Hi Mike";
        
        assertEquals(expected, MessageReport.searchByID(messages,message.getMessageID()));
    }
    
    @Test
    public void testSearchByRecipient(){
        List<Message> messages = new ArrayList<>();
        
        messages.add(new Message("+27718693002", "Hi Mike"));
        messages.add(new Message("+27718693002", "It is dinner time!"));
        messages.add(new Message("+27718693002", "Hello John"));
        
        String expected = "Hi Mike\nIt is dinner time!\n";
        
        assertEquals(expected, MessageReport.searchByRecipient(messages,"+27718693002"));
    }
    
    @Test
    public void testSearchByRecipientNotFound(){
        List<Message> messages = new ArrayList<>();
        
        messages.add(new Message("+27718693002", "Hi Mike"));
        
        assertEquals("No message found.", MessageReport.searchByRecipient(messages, "+27999999999"));
    }
    
    @Test
    public void testDisplayReport(){
        List<Message> messages = new ArrayList<>();
        
        Message message = new Message("+27718693002", "Hi Mike");
        messages.add(message);
        
        String expected = "Message Hash:" + message.getMessageHash()+"\n" + "Recipient: +27718693002\n" + "Message: Hi Mike\n\n";
        
        assertEquals(expected, MessageReport.displayReport(messages));
    }
    
    @Test
    public void testDisplayReportEmptyList(){
        List<Message> messages = new ArrayList<>();
       
        assertEquals("No messages found.", MessageReport.displayReport(messages));
    }
}
