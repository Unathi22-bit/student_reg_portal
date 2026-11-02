/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student_registration_portal;

/**
 *
 * @author Student
 */
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Stores and validates messages sent through QuickChat.
 */

public class Message {
    
       private String messageID;
       private int numMessagesSent;
       private int messageNumber;
       private String recipient;
       private String message;
       private String messageHash;
       private static int totalMessagesSent = 0;
       private static final List<Message> sentMessages = new ArrayList<>();
       private static final List<Message> disregardedMessages = new ArrayList<>();
       private static final List<String> messageHashes = new ArrayList<>();
       private static final List<String> messageIDs = new ArrayList<>();
       
       public Message(String recipient, String message){
           this.messageID = String.format("%010d",new Random().nextInt(1_000_000_000));
           this.recipient = recipient;
           this.message = message;
           this.numMessagesSent = ++totalMessagesSent;
           this.messageHash = createMessageHash();
           messageIDs.add(messageID);
           messageHashes.add(messageHash);
       }
       
       public String getMessageID(){
           return messageID;
       }
       
       public int getNumMessagesSent(){
           return numMessagesSent;
       }
       
       public String getRecipient(){
           return recipient;
       }
       
       public String getMessage(){
           return message;
       }
      
       public String getMessageHash(){
           return messageHash;
       }
       
       public boolean checkMessageID(){
           return messageID != null && messageID.length()<=10;
       }
       
       public boolean checkRecipientCell(){
           return Login.checkCellPhoneNumber(recipient);
       }
       
       public String checkMessageLength(){
           if (message == null){
               return "Please enetr a message of less than 250 characters.";
           
           }
           if (message.length()> 250){
               return "Please enter a message of less than 250 characters.";
           }
           return "Message ready to send.";
       }
       
       public String createMessageHash(){
           String firstTwo = messageID.substring(0,2);
           String [] words = message.trim().split("\\s+");
           String firstWord = words.length == 0? "":words[0];
           String lastWord = words.length == 0?"": words[words.length - 1];
           return (firstTwo + ":" + numMessagesSent + ":" + firstWord + lastWord).toUpperCase();
       }
       
       public String SentMessage(Scanner scanner){
           System.out.println("Choose an option: 1) Send Message 2) Disregard Message 3) Store Message to send later");
           String choice = scanner.nextLine().trim();
           switch (choice){
               case "1":
                   sentMessages.add(this);
                   return "Message successfully sent";
               case "2":
                   disregardedMessages.add(this);
                   return "Press 0 to delete the message";
               case "3":
                   if (storeMessage()){
                       return "Message successfully stored";
                   }
                   return "Message could not be stored";
               default:
                   return "Invalid option. Message was not sent";
           }
       }
       
       public String printMessage(){
           StringBuilder result = new StringBuilder();
           for (Message sentMessage: sentMessages){
               result.append("Message ID: ").append(sentMessage.getMessageID()).append("\n")
                       .append("Message Hash: ").append(sentMessage.getMessageHash()).append("\n")
                       .append("Recipient: ").append(sentMessage.getRecipient()).append("\n")
                       .append("Message: ").append(sentMessage.getMessage()).append("\n");
           }
           return result.toString();
       }
       
       public int returnTotalMessage(){
           return sentMessages.size();
       }
       
       public boolean storeMessage(){
           try (FileWriter writer = new FileWriter("message.json",true)){
               writer.write("{\"messageID\":\"" + escapeJson(messageID) + "\"."
               + "\"messageHash\":\"" + escapeJson(messageHash)+ "\","
               +"\"recipient\":\"" + escapeJson (recipient)+ "\","
               + "\"message\":\"" + escapeJson (message)+ "\"}" + System.lineSeparator());
               return true;
           }catch (IOException e){
               System.out.println("Unable to store message:" + e.getMessage());
               return false;
           }
        }
       
    public static List<Message> getSentMessages(){ return new ArrayList<> (sentMessages);}
    public static List<Message> getDisregardedMessages(){return new ArrayList<>(disregardedMessages);}
    public static List<String> getMessageHashes(){ return new ArrayList<>(messageHashes);}
    public static List<String> getMessagesIDs() { return new ArrayList<>(messageIDs);}
    
   
    public static List<Message> readStoredMessage() throws IOException{
        List<Message> stored = new ArrayList<>();
        Path file = Path.of("message.json");
        if(!Files.exists(file))return stored;
        for (String line: Files.readAllLines(file, StandardCharsets.UTF_8)){
            String id = jsonValue(line,"messageID");
            String hash = jsonValue(line, "messageHash");
            String recipient = jsonValue(line, "recipient");
            String text = jsonValue(line,"message");
            if (id !=null && hash != null && recipient !=null && text !=null){
                Message item = new Message(recipient, text);
                messageIDs.remove(item.messageID);
                messageHashes.remove(item.messageHash);
                item.messageID = id;
                item.messageHash= hash;
                stored.add(item);
            }
        }
        return stored;
    }
    
    public static boolean deleteStoredMessage(String hash){
        Path file = Path.of("message.json");
        if (!Files.exists(file)){
            return false;
        }
        try{
            List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
            List<String> remaining = new ArrayList<>();
            boolean deleted = false;
            for (String line : lines){
                if (!deleted && hash.equals(jsonValue(line,"messageHash"))){
                    deleted = true;
                } else {
                    remaining.add(line);
                }
            }
            if (deleted)Files.write(file, remaining, StandardCharsets.UTF_8);
            return deleted;
        } catch (IOException e){
           return false;
        }
    }
    
    private static String jsonValue(String json, String key){
        String marker = "\"" + key + "\":\"";
        int start = json.indexOf(marker);
        if (start < 0) return null;
        start += marker.length();
        StringBuilder result = new StringBuilder();
        boolean escaped = false;
        for (int i = start; i < json.length(); i++){
            char c = json.charAt(i);
            if (escaped){
                if (c == 'n') result.append('\n');
                else if (c =='r') result.append('\r');
                else result.append(c);
                escaped = false;
            } else if (c == '\\')escaped = true;
            else if (c == '"')return result.toString();
            else result.append(c);
        }
        return null;
    }
    
    
        private String escapeJson(String value){
            return value.replace("\\","\\\\").replace("\"","\\\"")
                    .replace("\n","\\n").replace("\r","\\r");
        }
        
}

