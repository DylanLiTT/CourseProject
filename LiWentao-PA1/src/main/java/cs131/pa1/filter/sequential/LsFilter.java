package cs131.pa1.filter.sequential;

import java.io.File;
/**
 * An class that extends the Sequential Filter and implements the additional ls command filter functions.
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 09/14/2020
 * COSI 131A PA1
 */
public class LsFilter extends SequentialFilter {
	
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 *This method is a overided ls command process. Which add all the directories and files under the 
	 *current directory to the output queue.
	 */
	@Override
	public void process() {
		File curDir = new File(SequentialREPL.currentWorkingDirectory);
		File[] filesList = curDir.listFiles();
        for(File f : filesList){
            if(f.isDirectory())
                this.output.add(f.getName());
            if(f.isFile()){
            	this.output.add(f.getName());
            }
        }
	}

}
