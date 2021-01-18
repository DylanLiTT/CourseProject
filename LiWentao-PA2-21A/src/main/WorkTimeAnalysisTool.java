package main;

/**
* This class provides the “command line” functionality that parses user inputs and performs operations on the data provided in the CSV file 
* by wrapping the data structures I create.
* Known Bugs: None
*
* @author Wentao Li
* wentaoli@brandeis.edu
* 4/11/2020
* COSI 21A PA2
*/
public class WorkTimeAnalysisTool {
	
	private WorkEntrySearchNode root;
	private WorkEntry[] entries;
	private String previousSearch;
	
	
	
	/**
	 * @param entries - the data set
	 * This constructs the tool given an array of WorkEntry objects. It creates
	 * different nodes and add all the workEntries into specific nodes accordingly.
	 * O(nlogn)
	 */
	public WorkTimeAnalysisTool(WorkEntry[] entries) {
		this.root = null;
		this.entries = entries;
		this.previousSearch = "";
		if(entries.length > 0) {
			root = new WorkEntrySearchNode(entries[0].getActivity());
			root.add(entries[0]);
		}
		for(int i = 1; i < entries.length; i++){
			
			WorkEntrySearchNode temp = new WorkEntrySearchNode(entries[i].getActivity());
			root = root.insert(temp);			
			root.add(entries[i]);
			
		}
		
	}
	
	
	/**
	 * @param cmd - the command of the user
	 * @return - certain response from the program
	 * This will parse a given command. 
	 * If the command is a “list” or “search” command, 
	 * then it should return the result of the list or search. 
	 * If the command  is “del”, then this should return null.
	 * O(logn)
	 */
	public String parse(String cmd) {
		if(cmd.equals("list r")) {
			
			return root.getByRecent();	
			
			
		} else if(cmd.equals("list l")) {
			
			return root.toString();
			
		} else if(cmd.length() >= 3 && cmd.substring(0, 3).equals("del")) {
			
			if(previousSearch == "") {
				
				throw new IllegalStateException("[del] command must be preceded by a [search] command");
				
			} else {
				
				int start = previousSearch.indexOf('"');
				String activity = previousSearch.substring(start+1, previousSearch.length()-1);
				root = root.search(new WorkEntrySearchNode(activity));
				if(root.compareTo(new WorkEntrySearchNode(activity)) ==  0) {
					
					root = root.del(Integer.parseInt(cmd.substring(cmd.indexOf(" ")+1)));
					
				}
				previousSearch = cmd;
				return "";
				
			}
			
		} else if(cmd.substring(0, 6).equals("search")){
			
			previousSearch = cmd;
			int start = cmd.indexOf('"');
			String activity = cmd.substring(start+1, cmd.length()-1);
			root = root.search(new WorkEntrySearchNode(activity));
			if(root.compareTo(new WorkEntrySearchNode(activity)) ==  0) {
				
				return root.getEntryData();
				
			}
			return "";
			
		} else if(cmd == "q") {
			
			return cmd;
			
		} else {
			
			return "the command is invalid.";
			
		}
	}
	
	
	
}
