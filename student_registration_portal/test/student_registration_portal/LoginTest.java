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

public class LoginTest {
    
    // ----------------- Password tests -----------------

    @Test
    public void testPassword_MeetsComplexityRequirements() {
        // Test Data: "Ch&&sec@ke99!"
        // The system returns: "Password successfully captured."
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkPasswordComplexity());
        assertTrue(user.registerUser().contains("Password successfully captured."));
    }

    @Test
    public void testPassword_DoesNotMeetComplexityRequirements() {
        // Test Data: "password"
        // The system returns: "Password is not correctly formatted; please ensure that the
        // password contains at least eight characters, a capital letter, a number, and a
        // special character."
        Login user = new Login("Jane", "Smith", "toolongname", "password", "08966553");
        assertFalse(user.checkPasswordComplexity());
        assertTrue(user.registerUser().contains(
                "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character."));
    }

    // ----------------- Cell phone tests -----------------

    @Test
    public void testCellPhoneNumber_CorrectlyFormatted() {
        // Test Data: +27838968976
        // The system returns: "Cell number successfully captured." (per POE table:
        // "Cell phone number successfully added.")
        assertTrue(Login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneNumber_IncorrectlyFormatted() {
        // Test Data: 08966553
        // The system returns: "Cell number is incorrectly formatted or does not contain
        // an international code; please correct the number and try again." (per POE table:
        // "Cell phone number incorrectly formatted or does not contain international code.")
        assertFalse(Login.checkCellPhoneNumber("08966553"));
    }

    // ----------------- Registration tests -----------------

    @Test
    public void testRegisterUser_AllValid_Success() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String result = user.registerUser();
        assertTrue(result.contains("User registered successfully!"));
    }

    @Test
    public void testRegisterUser_AllInvalid_Failure() {
        Login user = new Login("Jane", "Smith", "toolongname", "password", "08966553");
        String result = user.registerUser();
        assertFalse(result.contains("User registered successfully!"));
    }

    // ----------------- Login tests -----------------

    @Test
    public void testLogin_Successful() {
        // Test (assertTrue/False): Login Successful -> The system returns: True
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLogin_Failed() {
        // Test (assertTrue/False): Login Failed -> The system returns: False
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(user.loginUser("kyl_1", "wrongpassword"));
    }


    @Test
    public void testReturnLoginStatus_Welcome() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String status = user.returnLoginStatus("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome John, Doe it is great to see you again.", status);
    }

    @Test
    public void testReturnLoginStatus_Incorrect() {
        Login user = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String status = user.returnLoginStatus("kyl_1", "wrongpassword");
        assertEquals("Username or password incorrect, please try again.", status);
    }
    
}
