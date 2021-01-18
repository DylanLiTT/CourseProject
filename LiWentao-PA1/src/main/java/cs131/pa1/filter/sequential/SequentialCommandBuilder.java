package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cs131.pa1.filter.Filter;
import cs131.pa1.filter.Message;
/**
 * This class manages the parsing and execution of a command.
 * It splits the raw input into separated subcommands, 
 * creates subcommand filters, and links them into a list. 
 * @author cs131a
 *
 */
public class SequentialCommandBuilder {
	/**
	 * Creates and returns a list of filters from the specified command
	 * @param command the command to create filters from
	 * @return the list of SequentialFilter that represent the specified command
	 */
	public static List<SequentialFilter> createFiltersFromCommand(String command) {
		
		//create a list to contain all the filters
		List<SequentialFilter> filters = new ArrayList<SequentialFilter>();
		
		//split all filters and add the filters to the list.
		while(!command.equals("")) {
			filters.add(determineFinalFilter(command));
			command = adjustCommandToRemoveFinalFilter(command);		
		}
		
		//reverse the whole list since when we add we first add the command in the end
		for (int i = 0; i < filters.size() / 2; i++) { 
            SequentialFilter temp = filters.get(i); 
            filters.set(i, filters.get(filters.size() - i - 1)); 
            filters.set(filters.size() - i - 1, temp); 
        }
		
		filters.add(new printFilter());  //add a default print filter to the filter list
		boolean validCommand = linkFilters(filters);
		
		//the filters are linked successfully, return the filters list or return null
		if(validCommand) {
			return filters;
		} else {
			return null;
		}
	}
	
	/**
	 * Returns the filter that appears last in the specified command
	 * @param command the command to search from
	 * @return the SequentialFilter that appears last in the specified command
	 * @throws IOException 
	 */
	private static SequentialFilter determineFinalFilter(String command){
		
		//find the start index of the last filter
		int pipe = command.lastIndexOf("|");
		int txt = command.lastIndexOf(">");
		
		//construct the last command filter
		if((pipe == -1) && (txt == -1)) {
			return constructFilterFromSubCommand(command.trim());
		} else if(txt > pipe) {
			return constructFilterFromSubCommand(command.substring(txt).trim());
		} else {
			return constructFilterFromSubCommand(command.substring(pipe+1).trim());
		}
		
	}
	
	/**
	 * Returns a string that contains the specified command without the final filter
	 * @param command the command to parse and remove the final filter from 
	 * @return the adjusted command that does not contain the final filter
	 */
	private static String adjustCommandToRemoveFinalFilter(String command){
		
		//find the start index of the last filter
		int pipe = command.lastIndexOf("|");
		int txt = command.lastIndexOf(">");
		
		//construct the last command filter
		if((pipe == -1) && (txt == -1)) {
			return "";
		}  else if(txt > pipe) {
			return command.substring(0, txt);
		} else {
			return command.substring(0, pipe);
		}

	}
	
	/**
	 * Creates a single filter from the specified subCommand
	 * @param subCommand the command to create a filter from
	 * @return the SequentialFilter created from the given subCommand
	 * @throws IOException 
	 */
	private static SequentialFilter constructFilterFromSubCommand(String subCommand){
		
		if(subCommand.length() >= 2 && subCommand.substring(0,2).equals("> ")) {            //valid redirection filter
			return new TxtFilter(subCommand.substring(1).trim());
		} else if(subCommand.equals("ls")) {												//valid ls command
			return new LsFilter();	
		} else if(subCommand.equals("pwd")) {												//valid pwd command
			return new PwdFilter();
		} else if(subCommand.length() >= 3 && subCommand.substring(0,3).equals("cd ")){		//valid cd command
			String directory  = subCommand.substring(3).trim();
			if(directory.equals(".") || directory.equals("..")) {
				return new CdFilter(directory);
			}
			String newDirectory = SequentialREPL.currentWorkingDirectory + Filter.FILE_SEPARATOR + directory;
			File curDir = new File(newDirectory);
			if(curDir.isDirectory()) {
				return new CdFilter(newDirectory);
			} else {
				Message message = Message.DIRECTORY_NOT_FOUND;
				System.out.print(message.with_parameter(subCommand));
				return null;
			}	
		} else if(subCommand.length() >= 4 && subCommand.substring(0,4).equals("cat " )) {	//valid cat command
			String[] fileNames = subCommand.substring(4).trim().split(" ");
			for(String fileName: fileNames) {
				File temp = new File(SequentialREPL.currentWorkingDirectory + Filter.FILE_SEPARATOR + fileName);
				if(!temp.exists()) {
					Message message = Message.FILE_NOT_FOUND;
					System.out.print(message.with_parameter(subCommand));
					return null;
				}
			}
			return new CatFilter(fileNames);
		} else if(subCommand.length() >= 5 && subCommand.substring(0,5).equals("grep ")) {		//valid grep command
			String word = subCommand.substring(5).trim();
			return new GrepFilter(word);
		} else if(subCommand.length() >= 2 && subCommand.substring(0,2).equals("wc")) {			//valid wc command
			return new WcFilter();
		} else if(subCommand.equals("uniq")) {													//valid uniq command
			return new UniqFilter();
		} else if(subCommand.trim().equals("cat") || subCommand.trim().equals("grep") || subCommand.trim().equals("cd") || subCommand.trim().equals(">")) {    //missing argument
			Message message = Message.REQUIRES_PARAMETER;
			System.out.print(message.with_parameter(subCommand));
			return null;
		} else {
			Message message = Message.COMMAND_NOT_FOUND;										//unrecognizable
			System.out.print(message.with_parameter(subCommand));
			return null;
		}

	}
	
	/**
	 * links the given filters with the order they appear in the list
	 * @param filters the given filters to link
	 * @return true if the link was successful, false if there were errors encountered.
	 * Any error should be displayed by using the Message enum.
	 */
	private static boolean linkFilters(List<SequentialFilter> filters){
		//check whether all filters are valid or command is valid
		for(SequentialFilter filter: filters) {
			if(filter == null) {
				return false;
			}
		}
		
		
		//Check whether the first filter needs needs a input or not.
		if(filters.get(0) instanceof GrepFilter || filters.get(0) instanceof WcFilter || filters.get(0) instanceof UniqFilter || filters.get(0) instanceof TxtFilter) {
			Message message  = Message.REQUIRES_INPUT;
			if(filters.get(0) instanceof GrepFilter) {
				System.out.print(message.with_parameter("grep " + (((GrepFilter) filters.get(0)).getWord())));
				return false;
			} else if(filters.get(0) instanceof WcFilter){
				System.out.print(message.with_parameter("wc"));
				return false;
			} else if(filters.get(0) instanceof UniqFilter){
				System.out.print(message.with_parameter("uniq"));
				return false;
			} else {
				System.out.print(message.with_parameter(((TxtFilter) filters.get(0)).toString()));
				return false;
			}
		}
		
		
		//Cd command cannot have output, so no continue command.
		if(filters.get(0) instanceof CdFilter) {    	
			if(filters.size() > 2) {
				Message message = Message.CANNOT_HAVE_OUTPUT;
				System.out.print(message.with_parameter("cd " + ((CdFilter) filters.get(0)).getDic()));
				return false;
			}
		}
		
		//start to connect all the filters after validating the command
		filters.get(0).setNextFilter(filters.get(1));
		
		//no filter should be follow the txtfilter
		for(int i = 1; i < filters.size()-2; i++) {
			if(filters.get(i) instanceof TxtFilter) {
				Message message = Message.CANNOT_HAVE_OUTPUT;
				System.out.print(message.with_parameter(((TxtFilter) filters.get(i)).toString()));
				return false;
			}
		}
		
		//Connect all the commands within the middle
		for(int i = 1; i < filters.size()-1; i++) {
			
			//commands that cannot be in the middle
			if(filters.get(i) instanceof CatFilter) {
				Message message = Message.CANNOT_HAVE_INPUT;
				System.out.print(message.with_parameter(((CatFilter) filters.get(i)).toString()));
				return false;
			} else if(filters.get(i) instanceof LsFilter) {
				Message message = Message.CANNOT_HAVE_INPUT;
				System.out.print(message.with_parameter("ls"));
				return false;
			} else if(filters.get(i) instanceof PwdFilter) {
				Message message = Message.CANNOT_HAVE_INPUT;
				System.out.print(message.with_parameter("pwd"));
				return false;
			} else if(filters.get(i) instanceof CdFilter) {
				Message message = Message.CANNOT_HAVE_INPUT;
				System.out.print(message.with_parameter("cd " + ((CdFilter) filters.get(i)).getDic()));
				return false;
			}
			
			//commands that must follow a filter with output
			if(filters.get(i) instanceof GrepFilter) {
				if(filters.get(i-1) instanceof CdFilter || filters.get(i-1) instanceof TxtFilter || filters.get(i-1) instanceof PwdFilter){
					Message message = Message.REQUIRES_INPUT;
					System.out.print(message.with_parameter("grep " + (((GrepFilter) filters.get(i)).getWord())));
				}
			}else if(filters.get(i) instanceof UniqFilter) {
				if(filters.get(i-1) instanceof CdFilter || filters.get(i-1) instanceof TxtFilter || filters.get(i-1) instanceof PwdFilter){
					Message message = Message.REQUIRES_INPUT;
					System.out.print(message.with_parameter("uniq"));
				}
			}else if(filters.get(i) instanceof WcFilter) {
				if(filters.get(i-1) instanceof CdFilter || filters.get(i-1) instanceof TxtFilter || filters.get(i-1) instanceof PwdFilter){
					Message message = Message.REQUIRES_INPUT;
					System.out.print(message.with_parameter("wc"));
				}
			} else if(filters.get(i) instanceof TxtFilter) {
				if(filters.get(i-1) instanceof CdFilter || filters.get(i-1) instanceof TxtFilter || filters.get(i-1) instanceof PwdFilter){
					Message message = Message.REQUIRES_INPUT;
					System.out.print(message.with_parameter(((TxtFilter) filters.get(i)).toString()));
				}
			}
			filters.get(i).setPrevFilter(filters.get(i-1));
			filters.get(i).setNextFilter(filters.get(i+1));
		}
		filters.get(filters.size()-1).setPrevFilter(filters.get(filters.size()-2));
			
		return true;
	}
}
