package cs131.pa2.filter.concurrent;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import cs131.pa2.filter.Message;

/**
 * The main implementation of the REPL loop (read-eval-print loop).
 * It reads commands from the user, parses them, executes them and displays the result.
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 10/14/2020
 * COSI 131A PA2
 */
public class ConcurrentREPL {
	/**
	 * the path of the current working directory
	 */
	static String currentWorkingDirectory;
	/**
	 * The main method that will execute the REPL loop
	 * @param args not used
	 */
	public static void main(String[] args){
		currentWorkingDirectory = System.getProperty("user.dir");
		Scanner s = new Scanner(System.in);
		System.out.print(Message.WELCOME);
		String command;
		
		//store the background threads of a command
		LinkedList<Thread> bgThreads = new LinkedList<Thread>();
		
		while(true) {
			//obtaining the command from the user
			System.out.print(Message.NEWCOMMAND);
			command = s.nextLine();
			if(command.equals("exit")) {
				break;
			} else if(command.trim().equals("repl_jobs")) {           //when the command is "repl_jobs"
				int count = 1;
				for(Thread t: bgThreads) {
					if(t.isAlive()) {
						System.out.println("\t" + count + ". " + t.getName() + " &");
					}
					count++;
				}
			} else if(command.startsWith("kill")) {					//deal with the kill commands
				
				String[] splitter = command.trim().split(" ");
				if (splitter.length == 1){								//if the kill command has no parameter
					System.out.printf(Message.REQUIRES_PARAMETER.toString(), command);
				} else if (splitter.length > 2){						//if the parameter is invalid
					System.out.printf(Message.INVALID_PARAMETER.toString(), command);
				} else {
					int killNum = 0;
					try {
						killNum = Integer.parseInt(splitter[1]);		
						if(bgThreads.get(killNum-1).isAlive()) {
							bgThreads.get(killNum-1).interrupt();			//kill the thread of the bg command
						}
					
					} catch (NumberFormatException e){
						System.out.printf(Message.INVALID_PARAMETER.toString(), command);		// if the parameter is not a int
					}
				}
				
			} else if(!command.trim().equals("")) {     			  //noraml commands execution
				
				//booelan for whether the command contains the "&"
				boolean bgCommand = false;
				
				//check the last char of the command
				if(command.substring(command.length()-2).equals(" &")) {
					bgCommand = true;	
					int ampersandIndex = command.lastIndexOf("&");
					command = command.substring(0, ampersandIndex).trim();
				}
				
				//building the filters list from the command
				//start all the filter thread
				ConcurrentFilter filterlist = ConcurrentCommandBuilder.createFiltersFromCommand(command);
				Thread last = Thread.currentThread();
				while(filterlist != null) {
					Thread newThread = new Thread(filterlist, command);
					newThread.start();
					if(filterlist.getNext() == null) {
						last = newThread;
					}
					filterlist = (ConcurrentFilter) filterlist.getNext();
				}
				
				
				// if the command is a background command, then we don't need to wait for the command to finsih
				if(bgCommand) {
					bgThreads.add(last);
				} else {
					try{
						if(!last.equals(Thread.currentThread())) {
							last.join();
						}
					} catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			} 
		}
		s.close();
		System.out.print(Message.GOODBYE);
	}

}
