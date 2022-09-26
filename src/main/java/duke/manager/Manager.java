package duke.manager;

import duke.Storage.Storage;
import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.DeleteCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.exception.NoSuchCommandException;
import duke.task.TaskList;
import java.util.Scanner;

public class Manager {

    private static Command commandCreator(String input) throws NoSuchCommandException {
        String keyword = Parser.getKeyword(input);
        Command newCommand;
        switch (keyword) {
        case "bye":
            newCommand = new ByeCommand();
            break;
        case "list":
            newCommand = new ListCommand();
            break;
        case "mark":
            newCommand = new MarkCommand();
            break;
        case "unmark":
            newCommand = new UnmarkCommand();
            break;
        case "delete":
            newCommand = new DeleteCommand();
            break;
        case "todo":
            newCommand = new TodoCommand();
            break;
        case "event":
            newCommand = new EventCommand();
            break;
        case "deadline":
            newCommand = new DeadlineCommand();
            break;
        default:
            throw new NoSuchCommandException();
        }
        return newCommand;
    }

    private static void readInput(TaskList taskList) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            // read in the next line of input
            try {
                String input = in.nextLine();
                // initialise Command
                UserInterface.printBorderLines();
                Command c = commandCreator(input);
                // parse
                Parser.parse(c, input);
                // execute
                if (c.isLegal()) {
                    TaskExecutor.execute(taskList, c);
                }
                // print goodbye if command is bye
                if (c.isBye()) {
                    UserInterface.printGoodbye();
                }
                Storage.saveManager(taskList);
                UserInterface.printBorderLines();
            } catch (NoSuchCommandException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                UserInterface.printBorderLines();
            }
        }
    }

    public static void run() {
        UserInterface.printHello();
        // load
        TaskList taskList = Storage.loadManager();
        // run
        readInput(taskList);
    }
}
