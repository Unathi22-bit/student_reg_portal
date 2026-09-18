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
    
    public String firstname;
    public String lastname;
    public String username;
    public String password;
    public String cellPhoneNumber;
    
    
    public static void main(String[] args) {
    
        System.out.println("===== Welcome to the Student Registration Portal =======");
        
        input.close();
    }
    
}
