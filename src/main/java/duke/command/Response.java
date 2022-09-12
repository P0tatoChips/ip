package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Response {

    public static void printLines() {
        System.out.println("___________________________________________________________________________");
    }

    public static void printGreetings(String greeting) {
        if (greeting.equals("hello")) {
            // hello greeting
            printLines();
            System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
            printLines();
        }   else {
            // bye greeting
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
    public static void markResponse(Task[] tasks, String description) {

        int taskPosition;
        try {
            taskPosition = ExceptionHandler.handleNotInteger(description);
            ExceptionHandler.handleOutOfBounds(tasks, taskPosition, "mark");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't think " + description + " is a number. :-(");
        } catch (NullPointerException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but " + description + " is beyond my scope.  :-(");
        } catch (ArrayIndexOutOfBoundsException e) {
            if (Task.getTaskNumber() == 0) {
                description = "any";
            }
            System.out.println("☹ OOPS!!! I'm sorry, but you don't have " + description + " tasks. :-(");
        }
    }

    public static void unmarkResponse(Task[] tasks, String description) {

        int taskPosition;
        try {
            taskPosition = ExceptionHandler.handleNotInteger(description);
            ExceptionHandler.handleOutOfBounds(tasks, taskPosition, "unmark");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't think " + description + " is a number. :-(");
        } catch (NullPointerException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but " + description + " is beyond my scope.  :-(");
        } catch (ArrayIndexOutOfBoundsException e) {
            if (Task.getTaskNumber() == 0) {
                description = "any";
            }
            System.out.println("☹ OOPS!!! I'm sorry, but you don't have " + description + " tasks. :-(");
        }
    }

    /*public static void deleteResponse(Task[] tasks, String description) {

        int taskPosition;
        try {
            taskPosition = ExceptionHandler.handleNotInteger(description);
            ExceptionHandler.handleOutOfBounds(tasks, taskPosition, "delete");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't think " + description + " is a number. :-(");
        } catch (NullPointerException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but you don't have " + description + " number of tasks. :-(");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but you don't have " + description + " number of tasks. :-(");
        }
    }*/

    public static void listResponse(Task[] tasks, int count) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }

    public static void toDoResponse(Task[] tasks, String description, int taskNum) {

        tasks[taskNum] = new Todo(description);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + tasks[taskNum] + System.lineSeparator() + "Now you have " + (taskNum + 1)
                + " tasks in the list.");
    }

    public static void deadlineResponse(Task[] tasks, String description, String time, int taskNum) {

        tasks[taskNum] = new Deadline(description, time);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + tasks[taskNum] + System.lineSeparator() + "Now you have "
                + (taskNum + 1)  + " tasks in the list.");
    }

    public static void eventResponse(Task[] tasks, String description, String time, int taskNum) {
        if (description.equals("")) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            return;
        }

        tasks[taskNum] = new Event(description, time);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                +  tasks[taskNum] + System.lineSeparator() + "Now you have "
                + (taskNum + 1) + " tasks in the list.");
    }
}
