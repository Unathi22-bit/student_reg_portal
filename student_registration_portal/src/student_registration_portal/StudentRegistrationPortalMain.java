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

    public static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
    
        System.out.println("=====Registration & Login App=======");
        System.out.println("1. Regiter a new user");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println("Choose an option:  ");
            
        scanner.close();
    }
    
}
