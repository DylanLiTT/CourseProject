package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cs131.pa1.filter.Filter;
/**
 * An class that extends the Sequential Filter and implements the additional cat command filter functions.
 * Cat commands display the content of basic txt files, and it supports to read multiple files at the same time.
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 09/14/2020
 * COSI 131A PA1
 */
public class CatFilter extends SequentialFilter {
	
	//the name of the txt file to be cat
	private String[] fileName;
	
	//the constructor of the catFilter with the file name to be cat
	public CatFilter(String[] fileName) {
		this.fileName = fileName;
	}
	
	/**
	 *Cat commands display the content of basic txt files, and it supports to read multiple files at the same time.
	 */
	@Override
	public void process() {
		for(String fileName: fileName) {
			File temp = new File(SequentialREPL.currentWorkingDirectory + Filter.FILE_SEPARATOR + fileName);
			try {
				Scanner fileReader = new Scanner(temp);
				while(fileReader.hasNextLine()) {
					this.output.add(fileReader.nextLine());
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 *Return the initial subCommand.
	 */
	public String toString() {
		
		String result = "cat ";
		for(String file:fileName) {
			result += file + " ";
		}
		return result.trim();
	}

}
