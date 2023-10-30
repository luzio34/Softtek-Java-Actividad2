import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TodoList {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Agregar tarea");
            System.out.println("2. Mostrar tareas");
            System.out.println("3. Marcar tarea como completada");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int option = getUserChoice(scanner);

            switch (option) {
                case 1:
                    System.out.print("Ingresa la tarea: ");
                    String taskDescription = scanner.nextLine();
                    Task task = new Task(taskDescription);
                    tasks.add(task);
                    System.out.println("Tarea agregada.");
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("Tareas pendientes:");
                    listTasks(tasks);
                    System.out.println("");
                    break;
                case 3:
                    System.out.print("Ingrese el número de la tarea completada: ");
                    int completedTask = getUserChoice(scanner);
                    markTaskAsCompleted(tasks, completedTask);
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("¡Adiós!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    System.out.println("");
            }
        }
    }

    public static void listTasks(ArrayList<Task> tasks) {
        int count = 1;
        for (Task task : tasks) {
            System.out.println(count + ". " + task.getDescription() + (task.isCompleted() ? " (Completada)" : ""));
            count++;
        }
    }

    public static void markTaskAsCompleted(ArrayList<Task> tasks, int taskIndex) {
        if (taskIndex >= 1 && taskIndex <= tasks.size()) {
            Task task = tasks.get(taskIndex - 1);
            if (!task.isCompleted()) {
                task.markAsCompleted();
                System.out.println("Tarea marcada como completada.");
            } else {
                System.out.println("La tarea ya está completada.");
            }
        } else {
            System.out.println("Número de tarea inválido.");
        }
    }

    public static int getUserChoice(Scanner scanner) {
        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Introduce un número.");
                scanner.next(); // Limpiar la entrada incorrecta
            }
        }

        return choice;
    }
}