package cs131.pa2.filter.concurrent;

/**
 * The filter for printing in the console
 * @author cs131a
 *
 */
public class PrintFilter extends ConcurrentFilter {
	public PrintFilter() {
		super();
	}
	
	/**
	 *Overwrite the super process()
	 *Add the complete check to avoid the race condition
	 */
	public void process() {
		while(!isDone()) {
			try {
				String line = input.take();
				if(line.equals("COMPLETE")) {
					break;
				}
				processLine(line);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String processLine(String line) {
		System.out.println(line);
		return null;
	}
}
