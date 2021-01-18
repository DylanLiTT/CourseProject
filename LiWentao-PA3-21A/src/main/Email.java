/**
 * This class represents an email. 
 * An Email has a unique identifier, an array of the words in the Email, and the Email’s “spam score”. 
 * Known Bugs: None
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * <May 8th, 2020>
 * COSI 21A PA3
*/
package main;

public class Email {
	
	private String id;
	private String contents;
	private int spamScore;
	
	/**
	 * @param id - unique identifier of an email
	 * @param contents - the String content of the email
	 * This is a constructor of the email object.
	 */
	public Email(String id, String contents) {
		
		this.id = id;
		this.contents = contents;
		this.spamScore = -1;
		
		
	}
	
	/**
	 * @return - the unique identifier of the email
	 * O(1)
	 */
	public String getID() {
		return this.id;
	}
	
	/**
	 * @return - returns the words contained in this Email. 
	 * You may assume that the words of an Email can be retrieved 
	 * from the contents passed into the constructor as Strings that are separated by a space “ ”
	 * O(1)
	 */
	public String[] getWords() {
		String[] words = contents.split(" ");
		return words;
	}
	
	/**
	 * @return - the spam score of this Email.
	 * O(1)
	 */
	public int getSpamScore() {
		return this.spamScore;
	}
	
	/**
	 * @param score - new score of the Email.
	 * This method sets the spam score of this Email
	 * O(1)
	 */
	public void setSpamScore(int score) {
		this.spamScore = score;
	}
	
	/**
	 *This method returns the String representation of the Email.
	 */
	public String toString() {
		
		return this.id + " -- " + this.spamScore;
		
	}
}
