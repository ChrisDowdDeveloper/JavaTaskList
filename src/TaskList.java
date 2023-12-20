import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private String title;
    private String description;
    private boolean isComplete;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.isComplete = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public void markComplete() {
        isComplete = true;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nDescription: " + description + "\nIs Complete: " + isComplete; 
    }
}

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(String title, String description) {
        Task task = new Task(title, description);
        tasks.add(task);
        System.out.println("Task added successfully");
    }

    public void viewTasks() {
        if(tasks.isEmpty()) {
            System.out.println("No tasks to display");
        } else {
            System.out.println("To-Do List:");
            for(int i = 0; i < tasks.size(); i++) {
                System.out.println("\nTask " + (i + 1) + "\n" + tasks.get(i));
            }
        }
    }

    public void completeTask(String taskName) {
        boolean found = false;
        for(int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if(task.getTitle().equalsIgnoreCase(taskName)) {
                task.markComplete();
                found = true;
                System.out.println("Task has been marked as completed");
                break;
            }
        }

        if(!found) {
            System.out.println("Task not found. Check the task title.");
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("\nTo-Do List Menu");
            System.out.println("A. Add Task");
            System.out.println("B. View Tasks");
            System.out.println("C. Complete a Task");
            System.out.println("D. Exit");

            String choice = scanner.nextLine().toLowerCase();
            
            if(choice.equals("a") || choice.equals("add task")) {
                
                System.out.println("Enter task title: ");
                String title = scanner.nextLine();
                System.out.println("Enter task description");
                String description = scanner.nextLine();
                taskList.addTask(title, description);

            } else if(choice.equals("b") || choice.equals("add task")) {

                taskList.viewTasks();

            } else if(choice.equals("c") || choice.equals("complete task") || choice.equals("complete a task")) {
                
                System.out.println("Please enter the task name of the task you would like to complete.");
                String taskName = scanner.nextLine().toLowerCase();
                taskList.completeTask(taskName);

            } else if(choice.equals("d") || choice.equals("exit")) {
                
                System.out.println("Would you like to save your task list?");
                System.out.println("A. Yes");
                System.out.println("B. No");
                String answer = scanner.nextLine().toLowerCase();
                if(answer.equals("yes") || answer.equals("a")) {
                    System.out.println("Please enter what you would like it to be called.(Without the .txt extension)");
                    String fileName =  scanner.nextLine().trim().concat(".txt");
                    try {
                        File toDoList = new File(fileName);
                        PrintWriter writer = new PrintWriter(toDoList);

                        for(Task task : taskList.getTasks()) {
                            writer.println("Title: " + task.getTitle());
                            writer.println("Description: " + task.getDescription());
                            writer.println("Is Complete: " + task.getIsComplete());
                            writer.println();
                        }
                        writer.close();
                        System.out.println("File " + fileName + " created and task list saved");
                        System.exit(0);
                    } catch(IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                } else if(answer.equals("no") || answer.equals("b")) {
                    scanner.close();
                    System.exit(0);
                } else {
                    System.out.println("Invalid choice. Enter the number or the choice.");
                }

            } else {
                
                System.out.println("Invalid choice. Enter the number or the choice.");

            }
        }
    }
}