/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginapp;

import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class Login {
    
     private static boolean checkUserName(String username) {
         
       boolean validate = false;
         if(username != null && username.contains("_")  && username.length() <= 5){
         validate = true;
         }
        return  validate;
    }
      public static boolean checkPasswordComplexity(String password) {
          
        boolean hasUpperCase = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;
        boolean condition = false;
        if (password == null || password.length() < 8) {
            condition = false;
            return condition;
        }


        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }
if (hasUpperCase && hasNumber && hasSpecialChar){
condition = true;
}
        return condition;
    }


    public static void registerUser(String username, String password, ArrayList<User> user) {
      
         if(checkUserName(username))
        {
         if (checkPasswordComplexity(password)){
             User Newuser;
             Newuser = new User(username,password);
          user.add(Newuser);
                  System.out.println("the user has been registered successfully");
        
        }else{
         
            System.out.println("he password does not meet the complexity requirements");}
        }else{
           System.out.println("The username is incorrectly formatted");
         }
       
       
    }


    
 public static boolean isValidUsername(String username) {
     
        return username != null && username.contains("_") && username.length() <= 5;
    }
    
public static boolean loginUser(String Username, String password,User users){
    boolean valid = false;
    
         
    if(users.getUserName().equals(Username) && users.getPassword().equals(password)){
    valid= true;
    }
    
    return valid;
}

 public static String returnLoginStatus(String Username, String Password,User user){
  String valid = "";
   if ( loginUser(Username,Password ,user)) {
       valid = "A successful login";
   }else{
       valid = "A failed login";   
   }
  
  return valid;
}
 

    
    
}
