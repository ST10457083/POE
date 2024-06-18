/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginapp;

/**
 *
 * @author Dell
 */
public class Task {
     public static int totalhours = 0;

    public static void resetTotalHours() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static String returnTotalHours() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     private String name,number,description,devDetails,taskID,status;
     private int hours; 

    public Task(String name, String number, String description, String devDetails, String taskID, String status, int hours) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.devDetails = devDetails;
        this.taskID = taskID;
        this.status = status;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public String getDevDetails() {
        return devDetails;
    }

    public String getTaskID() {
        return taskID;
    }

    public String getStatus() {
        return status;
    }

    public int getHours() {
        return hours;
    }
    
     
    public static boolean checkTaskDescription(String Description)
    {
      boolean isValid = true;
      if (Description.length() > 50){
      isValid = false;
      System.out.println("Please enter a desciption of less than 50 characters");
      }else {
            System.out.println("Task successfully captured");
      }
      
      return isValid;
    }
    public static String createTaskID(String taskName ,int value, String developerName){
       String TaskID = "";
        if (value == 0){
             TaskID =taskName.substring(0, 2).toUpperCase()+":0:"+ developerName.substring(developerName.length()-3);
   
        }else{
    TaskID =taskName.substring(1, 2).toUpperCase()+":" +String.valueOf(value++)+":"+ developerName.substring(developerName.length()-3);
        }
    return TaskID;
    } 
    public static String printTaskDetails(String taskName,String taskNumber,String taskDescription,String devDetails, String taskID,String taskStatus,int Duration){
    String Data = taskStatus+","+devDetails+","+ taskNumber+ "," + taskName+ ","+ taskDescription+","+taskID+","+String.valueOf(Duration); 
    return Data;
    }
    public static int returnTotalhours(){
    return totalhours;
    }
      
}