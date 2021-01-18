package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import cs131.pa1.filter.Filter;

/**
 * An class that extends the Sequential Filter and implements the additional text filter functions.
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 09/14/2020
 * COSI 131A PA1
 */
public class TxtFilter extends SequentialFilter{
	
	private String fileName; //name of the new txt file
	private File newTxt;
	private PrintStream writer;
	/**
	 * @param fileName-the name of the txt that the filter will build
	 * This is the constructor of the txtFilter
	 * @throws IOException 
	 */
	public TxtFilter(String fileName){
		this.fileName = SequentialREPL.currentWorkingDirectory + Filter.FILE_SEPARATOR + fileName;
		this.newTxt = new File(this.fileName);
		try {
			this.writer = new PrintStream(newTxt);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected String processLine(String line) {
		writer.println(line);
		return null;
	}
	
	/**
	 *return the intial command.
	 */
	public String toString() {
		return "> " + this.fileName.substring(this.fileName.lastIndexOf(Filter.FILE_SEPARATOR)+1);
	}

}
