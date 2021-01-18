package cs131.pa1.filter.sequential;
/**
 * An class that extends the Sequential Filter and implements the additional pwd filter functions.
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 09/14/2020
 * COSI 131A PA1
 */
public class PwdFilter extends SequentialFilter {

	@Override
	public void process() {
		this.output.add(SequentialREPL.currentWorkingDirectory);   //add the current working directory to the output queue of the pwd filter.
	}
	
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
