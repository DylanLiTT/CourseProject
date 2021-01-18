package cs131.pa2.filter.concurrent;
/**
 * The filter for wc command
 * @author cs131a
 *
 */
public class WcFilter extends ConcurrentFilter {
	/**
	 * The count of lines found
	 */
	private int linecount;
	/**
	 * The count of words found
	 */
	private int wordcount;
	/**
	 * The count of characters found
	 */
	private int charcount;
	
	public WcFilter() {
		super();
	}
	
	/**
	 *For the process method, I overwrite the super process and combine the processline() and process().
	 *By this way, I can easily check the complete condition and make sure we would avoid the race condition.
	 */
	public void process() {
		while(!isDone()) {
			try {
				String line = input.take();
				if(line.equals("COMPLETE")) {
					break;
				}
				linecount++;
				String[] wct = line.split(" ");
				wordcount += wct.length;
				String[] cct = line.split("|");
				charcount += cct.length;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		output.add(linecount + " " + wordcount + " " + charcount);
		output.add("COMPLETE");
	}

	@Override
	protected String processLine(String line) {
		return null;
	}
	
}
