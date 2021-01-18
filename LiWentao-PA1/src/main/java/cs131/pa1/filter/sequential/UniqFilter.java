package cs131.pa1.filter.sequential;
/**
 * An class that extends the Sequential Filter and implements the additional unqi command filter functions.
 * It get rid of the repetitive lines of piped input.
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 09/14/2020
 * COSI 131A PA1
 */
public class UniqFilter extends SequentialFilter {

	@Override
	protected String processLine(String line) {
		
		if(!output.contains(line)) {
			return line;
		}
		return null;
	}
	

}
