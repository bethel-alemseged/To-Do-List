
import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private int id;
    private String description;
    private boolean completed;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }

    public void displayTask() {
        String status = completed ? "Completed" : "Pending";

        System.out.println(
            id + ". " + description + " [" + status + "]"
        );
    }
}

public class ToDoListApp {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {

        int choice;

        System.out.println("================================");
        System.out.println("       JAVA TO-DO LIST");
        System.out.println("================================");

        do {
            displayMenu();

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addTask();
                        break;

                    case 2:
                        viewTasks();
                        break;

                    case 3:
                        completeTask();
                        break;

                    case 4:
                        deleteTask();
                        break;

                    case 5:
                        System.out.println("Thank you for using To-Do List!");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = 0;
            }

        } while (choice != 5);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n----------- MENU -----------");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Mark Task as Completed");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");
        System.out.println("----------------------------");
    }

    private static void addTask() {

        System.out.print("Enter task description: ");
        String description = scanner.nextLine().trim();

        if (description.isEmpty()) {
            System.out.println("Task description cannot be empty.");
            return;
        }

        Task task = new Task(nextId++, description);
        tasks.add(task);

        System.out.println("Task added successfully!");
    }

    private static void viewTasks() {

        if (tasks.isEmpty()) {
            System.out.println("Your to-do list is empty.");
            return;
        }

        System.out.println("\n----------- YOUR TASKS -----------");

        for (Task task : tasks) {
            task.displayTask();
        }

        System.out.println("----------------------------------");
    }

    private static void completeTask() {

        viewTasks();

        if (tasks.isEmpty()) {
            return;
        }

        System.out.print("Enter task ID to complete: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());

            for (Task task : tasks) {
                if (task.getId() == id) {

                    if (task.isCompleted()) {
                        System.out.println("Task is already completed.");
                    } else {
                        task.markCompleted();
                        System.out.println("Task completed successfully!");
                    }

                    return;
                }
            }

            System.out.println("Task not found.");

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task ID.");
        }
    }

    private static void deleteTask() {

        viewTasks();

        if (tasks.isEmpty()) {
            return;
        }

        System.out.print("Enter task ID to delete: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());

            for (Task task : tasks) {
                if (task.getId() == id) {
                    tasks.remove(task);
                    System.out.println("Task deleted successfully!");
                    return;
                }
            }

            System.out.println("Task not found.");

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task ID.");
        }
    }
}
