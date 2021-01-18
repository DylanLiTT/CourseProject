package cs131.pa1.filter.sequential;

import cs131.pa1.filter.Filter;

/**
 * An class that extends the Sequential Filter and implements the additional cd command functions.
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 09/214/2020
 * COSI 131A PA1
 */
public class CdFilter extends SequentialFilter {
	
	//new directory of the cd command
	private String newDirectory;
	
	//the constructor of the cd command filter
	public CdFilter(String newDirectory) {
		this.newDirectory = newDirectory;
	}
	
	@Override
	public void process() {
		
		if(newDirectory.equals("..")) {
			SequentialREPL.currentWorkingDirectory = SequentialREPL.currentWorkingDirectory.substring(0, SequentialREPL.currentWorkingDirectory.lastIndexOf(Filter.FILE_SEPARATOR));
		} else if(newDirectory.equals(".")){
			return;
		} else {
			SequentialREPL.currentWorkingDirectory = this.newDirectory;
		}
		
	}
	
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		return "cd " + this.newDirectory;
	}

	/**
	 * @return - the new directory name.
	 */
	public String getDic() {
		if(newDirectory.equals(".") || newDirectory.equals("..")) {
			return newDirectory;
		} else {
			return this.newDirectory.substring(this.newDirectory.lastIndexOf(Filter.FILE_SEPARATOR)+1);
		}
	}
}
