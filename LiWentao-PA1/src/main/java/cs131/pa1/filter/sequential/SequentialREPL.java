package cs131.pa1.filter.sequential;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import cs131.pa1.filter.Message;

/**
 * The main implementation of the REPL loop (read-eval-print loop).
 * It reads commands from the user, parses them, executes them and displays the result.
 * @author cs131a
 *
 */
public class SequentialREPL {
	/**
	 * the path of the current working directory
	 */
	static String currentWorkingDirectory;
	
	/**
	 * The main method that will execute the REPL loop
	 * @param args not used
	 * @throws IOException 
	 */
	public static void main(String[] args){
		
		currentWorkingDirectory = System.getProperty("user.dir");
		
		//Beginning of the REPL
		System.out.print(Message.WELCOME);
		System.out.print(Message.NEWCOMMAND);
		
		//create a scanner to read the user's commands
		Scanner console = new Scanner(System.in);
		String command = console.nextLine();
		
		//Do not exit the REPL until the user enter exit;
		while(!command.equals("exit")) {
			
			List<SequentialFilter> filters = SequentialCommandBuilder.createFiltersFromCommand(command);
			if(filters != null) {
				processAllFilters(filters);
			}
			System.out.print(Message.NEWCOMMAND);
			command = console.nextLine();
			
		}
		System.out.print(Message.GOODBYE);
		
	}

	/**
	 * @param filters - the filters created and connected by a valid command.
	 */
	private static void processAllFilters(List<SequentialFilter> filters) {
			
		for(SequentialFilter filter: filters) {
			filter.process();
		}
	}

}
