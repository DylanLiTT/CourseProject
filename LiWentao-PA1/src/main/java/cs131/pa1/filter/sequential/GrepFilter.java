package cs131.pa1.filter.sequential;

/**
 * An class that extends the Sequential Filter and implements the additional grep command filter functions.
 * It put lines with specified word from pipe input into output.
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 09/14/2020
 * COSI 131A PA1
 */
public class GrepFilter extends SequentialFilter {
	
	//the word that the word needs to te grep
	private String word;
	
	//the constructor of the grep filter
	public GrepFilter(String word) {
		this.word = word;
	}
	
	@Override
	protected String processLine(String line) {
		if(line.contains(word)) {
			return line;
		}
		return null;
	}
	
	
	/**
	 * @return - the word the filter needs to grep
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 *Return the initail subcommand.
	 */
	public String toString() {
		return "grep " + this.word;
	}

}
