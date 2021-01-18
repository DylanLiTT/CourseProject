package cs131.pa2.filter.concurrent;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import cs131.pa2.filter.Filter;

/**
 * An abstract class that extends the Filter and implements the basic functionality of all filters. Each filter should
 * extend this class and implement functionality that is specific for that filter.
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * 10/14/2020
 * COSI 131A PA2
 *
 */
public abstract class ConcurrentFilter extends Filter implements Runnable{
	/**
	 * The input queue for this filter
	 */
	protected LinkedBlockingQueue<String> input;
	/**
	 * The output queue for this filter
	 */
	protected LinkedBlockingQueue<String> output;
	
	
	/**
	 *The abstract method of the thread and we simply process the filter
	 */
	public void run() {
		process();
	}
	
	@Override
	public void setPrevFilter(Filter prevFilter) {
		prevFilter.setNextFilter(this);
	}
	
	@Override
	public void setNextFilter(Filter nextFilter) {
		if (nextFilter instanceof ConcurrentFilter){
			ConcurrentFilter concurrentNext = (ConcurrentFilter) nextFilter;
			this.next = concurrentNext;
			concurrentNext.prev = this;
			if (this.output == null){
				this.output = new LinkedBlockingQueue<String>();   //change the linked list to LBQ to satisfy the concurrency
			}
			concurrentNext.input = this.output;
		} else {
			throw new RuntimeException("Should not attempt to link dissimilar filter types.");
		}
	}
	/**
	 * Gets the next filter
	 * @return the next filter
	 */
	public Filter getNext() {
		return next;
	}
	
	/**
	 * processes the input queue and writes the result to the output queue
	 * Add the complete statment to represent the death of the filter
	 */
	public void process(){
		
		while(!isDone()) {
			try {
				String line = input.take();
				if(line.equals("COMPLETE")) {
					break;
				}
				String processedLine = processLine(line);
				if(processedLine != null) {
					output.add(processedLine);
				}
			} catch (InterruptedException e) {
				output.add("COMPLETE");
			}
		}
		
		this.output.add("COMPLETE");
	}
	
	
	/**
	 *The isDone method is used to verify whether a filter has end its thread. For thread or filter that is ended,
	 *the only element input in the input will be the "COMPLETE", which means that it has finishes all its input from the previous filter. 
	 */
	@Override
	public boolean isDone() {
		if(input.peek() != null && input.peek().equals("COMPLETE")) {
			return true;
		} else {
			return false;
		}
	}
	
	protected abstract String processLine(String line);
	
}
