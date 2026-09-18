/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student_registration_portal;

/**
 *
 * @author Student
 */
public class Login {
    //Declare users information
    public String firstname;
    public String lastname;
    public String username;
    public String password;
    public String cellPhoneNumber;
    
    
    //Create a variable that will store whether the login was a success
    
    public Login(String firstName, String lastName,String username,String password,String cellPhoneNumber){
        this.firstname= firstName;
        this.lastname= lastName;
        this.username= username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        
    }
    
    //------------------Getters---------------------------------
    public String getFirstName() {
        return firstname;
    }
    public String getLastName(){
        return lastname;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword (){
        return password;
    }
    public String getCellPhoneNumber(){
        return cellPhoneNumber;
    }
    
   //-------------------Validation methods------------------------
    //User contains an underscore and its no more than five characters long.
    public boolean checkUserName(){
        return username !=null 
                && username.contains("_")
                && username.length()<=5;
        
    }
    
    //Password must be at least 8 characters,
    //contain a capital letter, number and special character
    public boolean checkPasswordComplexity() {
      return checkPasswordComplexity(password);
        }
    public static boolean checkPasswordComplexity(String password){
        if (password == null || password.length() < 8){
            return false;
        }
        
           boolean hasCapital = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");
        return hasCapital && hasNumber && hasSpecial;
    }
    // Cell phone number contains the international country code (+27) followed by number, which must be more than ten characters long e.g.+274567897
      
      public boolean checkCellPhoneNumber(){
        return checkCellPhoneNumber(cellPhoneNumber);
    }
     public static boolean checkCellPhoneNumber(String cellPhoneNumber){
         if (cellPhoneNumber == null)return false;
         return cellPhoneNumber.matches("^\\+[0-9]{2}[0-9]{9,}$");
     }
     //----------------------Registraction------------------------------
     // Returns the necessary messageing, indicating whether the username is incorrectly formatted, The password does not meet complexity requirements, or both conditions have been met and user has been registered succeddfully.
     public String registerUser(){
         StringBuilder result = new StringBuilder();
         boolean username0k = checkUserName();
         boolean password0k = checkPasswordComplexity();
         boolean cell0k = checkCellPhoneNumber();
         
         if (!username0k){
             result.append("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.\n");
         }
         if (!password0k){
             result.append("Password is nor correctly formatted; please ensure that contains at least eight characters, a capital letter and special character.\n");
         }
         if (!cell0k){
             result.append("Cell phone number incorrectly formatted or does not contin international code.\n");
         }
         if (username0k && password0k && cell0k){
             result.append("Username successfully captured.\n");
             result.append("Password successfully captured.\n");
             result.append("Cell Phone number successfully added.\n");
             result.append("User registered successfully!");
         }
         
         return result.toString();
         
    }
     
     public String returnLoginStatus(String enteredUsername, String enteredPassword){
        if (loginUser(enteredUsername,enteredPassword)){
            return "Welcome  " + getFirstName() +" "+ getLastName() + " , it is great to see you again.";
        } else {
            return"Username or password incorrect, please try again";
        }
     }
     //------------------------Login------------------------------------
     //Verifies that login details entered match the login details stored when the user registered.
     public boolean loginUser(String enteredUsername, String enteredPassword){
         return this.username.equals(enteredUsername)&& this.password.equals(enteredPassword);     }
     }
    
