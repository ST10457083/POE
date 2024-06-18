package loginapp;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import static loginapp.Login.registerUser;

import static loginapp.Task.checkTaskDescription;
import static loginapp.Task.createTaskID;
import static loginapp.Task.printTaskDetails;
import static loginapp.Login.checkPasswordComplexity;
import static loginapp.Login.isValidUsername;
import static loginapp.Login.registerUser;
import static loginapp.Login.returnLoginStatus;
import static loginapp.Login.checkPasswordComplexity;
import static loginapp.Login.returnLoginStatus;
import static loginapp.Login.loginUser;

public class LoginApp {

    static ArrayList<String> developerNames = new ArrayList<>();
    static ArrayList<String> taskNames = new ArrayList<>();
    static ArrayList<String> taskIDs = new ArrayList<>();
    static ArrayList<Integer> taskDurations = new ArrayList<>();
    static ArrayList<String> taskStatuses = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
populateTestData();
        String defaultUsername = "kyl_1";
        String defaultPassword = "Ch&&sec@ke99!";
        registerUser(defaultUsername, defaultPassword, users);


        System.out.println("Enter Login username:");
        String usernameLogin = scanner.nextLine();

        System.out.println("Enter Login password:");
        String passwordLogin = scanner.nextLine();

        System.out.println(returnLoginStatus(usernameLogin, passwordLogin, users.get(0)));

        if (loginUser(usernameLogin, passwordLogin, users.get(0))) {
            System.out.println("Welcome to EasyKanban");
            displayMenu(scanner, tasks);
        } else {
            System.out.println("Invalid login credentials");
        }
    }

 public static void populateTestData() {
    developerNames.add("Mike Smith");
    taskNames.add("Create Login");
    taskIDs.add("CL:0:ith");
    taskDurations.add(5);
    taskStatuses.add("To Do");

    developerNames.add("Edward Harrison");
    taskNames.add("Create Add Features");
    taskIDs.add("CA:1:son");
    taskDurations.add(8);
    taskStatuses.add("Doing");

    developerNames.add("Samantha Paulson");
    taskNames.add("Create Reports");
    taskIDs.add("CR:2:lso");
    taskDurations.add(2);
    taskStatuses.add("Done");

    developerNames.add("Glenda Oberholzer");
    taskNames.add("Add Arrays");
    taskIDs.add("AA:3:lze");
    taskDurations.add(11);
    taskStatuses.add("To Do");
}


    private static void displayMenu(Scanner scanner, ArrayList<Task> tasks) {
        int option;
        do {
            System.out.println("Menu Options:");
            System.out.println("1) Add Task");
            System.out.println("2) Show Report");
            System.out.println("3) Quit");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    addTasks(scanner, tasks);
                    break;
                case 2:
                    showReportMenu(scanner, tasks);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 3);
    }

    private static void addTasks(Scanner scanner, ArrayList<Task> tasks) {
        System.out.println("How many tasks do you want to enter?");
        int numTasks = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numTasks; i++) {
            System.out.println("Enter Task Name:");
            String name = scanner.nextLine();

            System.out.println("Enter Task Description:");
            String description = scanner.nextLine();
            while (!checkTaskDescription(description)) {
                System.out.println("Invalid description. Please enter a valid Task Description:");
                description = scanner.nextLine();
            }

            System.out.println("Enter Developer Details:");
            String developerDetails = scanner.nextLine();

            System.out.println("Enter Task Duration in hours:");
            int duration = Integer.parseInt(scanner.nextLine());

            System.out.println("Choose Status:");
            System.out.println("1) To Do");
            System.out.println("2) Done");
            System.out.println("3) Doing");
                int choice = Integer.parseInt(scanner.nextLine());
                String status = ""; 

                if (choice == 1) {
                    status = "To Do";
                } else if (choice == 2) {
                    status = "Done";
                } else if (choice == 3) {
                    status = "Doing";
                } else {
                    status = "Unknown";
                }
            String taskId = createTaskID(name, tasks.size(), developerDetails);
            JOptionPane.showMessageDialog(null, printTaskDetails(name, String.valueOf(tasks.size()), description, developerDetails, taskId, status, duration));
            Task task = new Task(name, String.valueOf(tasks.size()), description, developerDetails, taskId, status, duration);
            tasks.add(task);

            developerNames.add(developerDetails);
            taskNames.add(name);
            taskIDs.add(taskId);
            taskDurations.add(duration);
            taskStatuses.add(status);

            Task.totalhours += duration;
        }

        System.out.println("The total number of hours: " + Task.returnTotalHours());
    }

    private static void showReportMenu(Scanner scanner, ArrayList<Task> tasks) {
        int option;
        do {
            System.out.println("Report Menu:");
            System.out.println("1) Display tasks with status 'done'");
            System.out.println("2) Display task with longest duration");
            System.out.println("3) Search for a task by name");
            System.out.println("4) Search tasks by developer");
            System.out.println("5) Delete a task by name");
            System.out.println("6) Display full report");
            System.out.println("7) Back to main menu");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    displayTasksWithStatusDone();
                    break;
                case 2:
                    displayTaskWithLongestDuration();
                    break;
                case 3:
                    searchTaskByName(scanner);
                    break;
                case 4:
                    searchTasksByDeveloper(scanner);
                    break;
                case 5:
                    deleteTaskByName(scanner);
                    break;
                case 6:
                    System.out.println(displayFullReport());
                    break;
                case 7:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 7);
    }

    private static void displayTasksWithStatusDone() {
        System.out.println("Tasks with status 'done':");
        for (int i = 0; i < taskStatuses.size(); i++) {
            if ("Done".equalsIgnoreCase(taskStatuses.get(i))) {
                System.out.println("Developer: " + developerNames.get(i) + ", Task Name: " + taskNames.get(i) + ", Duration: " + taskDurations.get(i));
            }
        }
    }

    private static void displayTaskWithLongestDuration() {
        int maxDuration = 0;
        int index = -1;
        for (int i = 0; i < taskDurations.size(); i++) {
            if (taskDurations.get(i) > maxDuration) {
                maxDuration = taskDurations.get(i);
                index = i;
            }
        }
        if (index != -1) {
            System.out.println("Task with longest duration:");
            System.out.println("Developer: " + developerNames.get(index) + ", Duration: " + taskDurations.get(index));
        }
    }

    private static void searchTaskByName(Scanner scanner) {
        System.out.println("Enter task name to search:");
        String taskName = scanner.nextLine();
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskName)) {
                System.out.println("Task Found - Task Name: " + taskNames.get(i) + ", Developer: " + developerNames.get(i) + ", Status: " + taskStatuses.get(i));
                return;
            }
        }
        System.out.println("Task not found.");
    }

    private static void searchTasksByDeveloper(Scanner scanner) {
        System.out.println("Enter developer name to search tasks:");
        String developerName = scanner.nextLine();
        System.out.println("Tasks assigned to " + developerName + ":");
        for (int i = 0; i < developerNames.size(); i++) {
            if (developerNames.get(i).equalsIgnoreCase(developerName)) {
                System.out.println("Task Name: " + taskNames.get(i) + ", Status: " + taskStatuses.get(i));
            }
        }
    }

    private static void deleteTaskByName(Scanner scanner) {
        System.out.println("Enter task name to delete:");
        String taskName = scanner.nextLine();
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskName)) {
                developerNames.remove(i);
                taskNames.remove(i);
                taskIDs.remove(i);
                taskDurations.remove(i);
                taskStatuses.remove(i);
                System.out.println("Task " + taskName + " deleted.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    static String displayFullReport() {
        StringBuilder report = new StringBuilder("Task Report:\n");
        for (int i = 0; i < taskNames.size(); i++) {
            report.append("Task Name: ").append(taskNames.get(i))
                    .append(", Developer: ").append(developerNames.get(i))
                    .append(", Task ID: ").append(taskIDs.get(i))
                    .append(", Duration: ").append(taskDurations.get(i))
                    .append(", Status: ").append(taskStatuses.get(i))
                    .append("\n");
        }
        return report.toString();
    }

 
}
