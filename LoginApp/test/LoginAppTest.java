import java.util.ArrayList;
import loginapp.LoginApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
public class LoginAppTest {

    static ArrayList<String> developerNames = new ArrayList<>();
    static ArrayList<String> taskNames = new ArrayList<>();
    static ArrayList<String> taskIDs = new ArrayList<>();
    static ArrayList<Integer> taskDurations = new ArrayList<>();
    static ArrayList<String> taskStatuses = new ArrayList<>();
    public void setUp() {
       
        developerNames.clear();
        taskNames.clear();
        taskIDs.clear();
        taskDurations.clear();
        taskStatuses.clear();

   
        LoginApp.populateTestData();
    }


    public void testDeveloperArrayPopulation() {
      
        assertEquals("Mike Smith", developerNames.get(0));
        assertEquals("Edward Harrison", developerNames.get(1));
        assertEquals("Samantha Paulson", developerNames.get(2));
        assertEquals("Glenda Oberholzer", developerNames.get(3));
    }

   
    public void testLongestDurationTask() {
       
        int maxDuration = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < taskDurations.size(); i++) {
            if (taskDurations.get(i) > maxDuration) {
                maxDuration = taskDurations.get(i);
                index = i;
            }
        }
        assertEquals("Glenda Oberholzer", developerNames.get(index));
        assertEquals(11, taskDurations.get(index));
    }


    public void testSearchTaskByName() {
        // Test searching for a task by name
        String taskName = "Create Login";
        String expectedDeveloper = "Mike Smith";
        String expectedStatus = "To Do";
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equals(taskName)) {
                assertEquals(expectedDeveloper, developerNames.get(i));
                assertEquals(expectedStatus, taskStatuses.get(i));
                return;
            }
        }
        fail("Task not found: " + taskName);
    }

   
    public void testSearchTasksByDeveloper() {
        // Test searching for tasks by developer
        String developerName = "Samantha Paulson";
        String expectedTaskName = "Create Reports";
        for (int i = 0; i < developerNames.size(); i++) {
            if (developerNames.get(i).equals(developerName)) {
                assertEquals(expectedTaskName, taskNames.get(i));
            }
        }
    }

  
    public void testDeleteTaskByName() {
        // Test deleting a task by name
        String taskNameToDelete = "Create Reports";
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equals(taskNameToDelete)) {
                developerNames.remove(i);
                taskNames.remove(i);
                taskIDs.remove(i);
                taskDurations.remove(i);
                taskStatuses.remove(i);
                break;
            }
        }
        assertFalse(taskNames.contains(taskNameToDelete));
    }


   
}