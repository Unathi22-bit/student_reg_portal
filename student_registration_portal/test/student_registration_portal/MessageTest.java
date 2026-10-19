/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student_registration_portal;

/**
 *
 * @author Student
 */
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;
import student_registration_portal.Message;
        
public class MessageTest {
    
    @Test
    public void testMessageID_IsTenChatactersOrLess(){
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight");
        assertTrue(message.checkMessageID());
        assertEquals(10, message.getMessageID().length());
    }
    
    @Test
    public void testRecipientCell_CorrectlyFormattted(){
        Message message = new Message("+27718693002", "Hello");
        assertTrue(message.checkRecipientCell());
    }
    
    @Test
    public void testRecipientCell_IncorrctlyFormatted(){
        Message message = new Message("+27718693002", "Hello");
        assertFalse(message.checkRecipientCell());
    }
    
    @Test
    public void testMessageLength_WithinLimit(){
        Message message = new Message("+27718693002","Hi Mike");
        assertEquals("Message ready to send", message.checkMessageLength());
    }
    
    @Test
    public void testMessageLegth_ExceedsLimit(){
        Message message = new Message("+27718693002", "a".repeat(251));
        assertEquals("Please enter a message of less than 250 characters", message.checkMessageLength());
    }
    
    @Test
    public void testMessageHash_IsUppercaseAndConatinsExpectedParts(){
        Message message = new Message("+27718693002", "Hi Tonight");
        assertTrue(message.getMessageHash().equals(message.getMessageHash().toUpperCase()));
        assertTrue(message.getMessageHash().matches("[0-9]{2}:[0-9]+:.*"));
    }
    
    @Test
    public void testSentMessage_Discard(){
        Message message = new Message("+27718693002", "Hi Keegan");
        assertEquals("Press 0 to delete the message", message.SentMessage(new Scanner("2\n")));
    }
}
