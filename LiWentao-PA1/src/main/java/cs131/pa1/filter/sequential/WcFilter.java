package cs131.pa1.filter.sequential;
/**
 * An class that extends the Sequential Filter and implements the additional wc command filter functions.
 * It reports the line, word, char nums of piped input
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 09/14/2020
 * COSI 131A PA1
 */
public class WcFilter extends SequentialFilter {
	
	//first spot track the line, second for the word, third for the char.
	private int[] counter = {0,0,0};
	
	
	
	@Override
	public void process() {
		
		super.process();
		if(isDone()) {
			output.add(String.format("%d %d %d", counter[0], counter[1], counter[2]));
		}
	}
	
	@Override
	protected String processLine(String line) {
		
		counter[0]++;     //increase the line num.
		
		String[] words = line.split(" ");  //increase the word num.
		counter[1] += words.length;
		
		counter[2] += line.length();
		
		return null;
	}
	

}
