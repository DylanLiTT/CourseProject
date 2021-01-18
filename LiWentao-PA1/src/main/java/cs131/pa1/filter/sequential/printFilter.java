package cs131.pa1.filter.sequential;
/**
 * An class that extends the Sequential Filter and implements the additional print filter functions.
 * This class must take input and it will never add anything to the output queue.
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 09/14/2020
 * COSI 131A PA1
 */
public class printFilter extends SequentialFilter {

	/**
	 *This method is overriding the sequentialFilter's method.
	 *It prints every line of the input and display on the console.
	 *Retun nothing.
	 */
	@Override
	protected String processLine(String line) {
		System.out.println(line);
		return null;
	}
	
}
