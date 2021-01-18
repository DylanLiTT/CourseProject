/**
 * This class provides the functionality of a simplified Bayesian email spam filter. 
 * A SpamFilter can be trained based on a set of Emails marked spam or not. 
 * Known Bugs: None
 * @author Wentao Li
 * wentaoli@brandeis.edu
 * <May 8th, 2020>
 * COSI 21A PA3
*/
package main;

public class SpamFilter {
	
	private int threshold;						//strictness of the filter
	private PriorityQueue receivedEmails;		//received emails stores here
	private HashTable spamT;					//track spam email words
	private HashTable nonspamT;					//track safe email words
	private String[] spamWords;					//marked words for a spam email
	private int spamWordsCount;					//marked words count for a spam email
	private String[] nonSpamWords;				//marked words for a safe email
	private int nonSpamWordsCount;				//marked words count for a safe email
	private int trainedSpam;					//analyze finished spam emails
	private int trainedSafe;					//analyze finished safe emails
	
	
	/**
	 * @param threshold - ceiling of spam score of a Email
	 * This is the constructor for the spam filter.
	 */
	public SpamFilter(int threshold) {
		
		this.threshold = threshold;
		this.receivedEmails = new PriorityQueue();
		this.spamT = new HashTable();
		this.nonspamT = new HashTable();
		this.spamWords = new String[10];
		this.spamWordsCount = 0;
		this.nonSpamWords = new String[10];
		this.nonSpamWordsCount = 0;
		this.trainedSpam = 0;
		this.trainedSpam = 0;
		
	}
	
	/**
	 * @param threshold - new threshold value
	 * This method updates this SpamFilter to have a new threshold value.
	 * O(1)
	 */
	public void setThreshold(int threshold) {
		this.threshold  = threshold;
	}
	
	/**
	 * @param word - a String word
	 * @return - the spamcity of the word
	 * O(1)
	 */
	public double getSpamicity(String word) {
		
		double wordInSpam = 0.0;
		if(spamT.get(word) != null) {
			wordInSpam = (int) spamT.get(word) / (double) trainedSpam;
		}
		double wordInSafe = 0.0;
		if(nonspamT.get(word) != null) {
			wordInSafe = (int) nonspamT.get(word) / (double) trainedSafe;
		}
		return wordInSpam / (wordInSpam + wordInSafe);
	}
	
	/**
	 * @param words - the String array of an Email content
	 * @return - the spamScore of the email
	 * O(n)
	 */
	public int calculateSpamScore(String[] words) {
		
		int spamScore = 0;
		
		for(String word: words) {
			
			double spamicity = this.getSpamicity(word);
			if(spamicity >= 0.9) {
				spamScore += 4;
			} else if(spamicity >= 0.75) {
				spamScore += 2;
			} else if (spamicity >= 0.5) {
				spamScore += 1;
			}
			
		}
		
		return spamScore;
	}
	
	/**
	 * @param emails - newly arrived Emails
	 * This method should store the Emails in emails in this SpamFilter. 
	 * O(n^2)
	 */
	public void receive(Email[] emails) {
		
		for(int i = 0; i < emails.length; i++) {
			
			String[] words = emails[i].getWords();
			int spamScore = this.calculateSpamScore(words);
			emails[i].setSpamScore(spamScore);
			this.receivedEmails.insert(emails[i]);
			
		}
		
	}
	
	/**
	 * @return - the ID of emails that are filtered out by the threshold
	 * This should remove all Emails that are stored in this SpamFilter 
	 * that have spam scores greater than or equal to this SpamFilter’s threshold value.
	 * O(n) 
	 */
	public String filter() {
		
		String removed = "";
		while(receivedEmails.getMaximumSpamScore() >= this.threshold) {
			Email e = receivedEmails.extractMaximum();
			removed += e.getID() + "\n";
		}
		return removed;
		
	}
	
	/**
	 * @param emails - emails that are labeled spam or not
	 * @param isSpam - the spam boolean of these emails
	 * This method passes this SpamFilter a set of Emails that have been labelled as spam or not. 
	 * And provide spam analyze for later spam decider.
	 * O(n^3)
	 */
	public void train(Email[] emails, boolean[] isSpam) {
		
		for(int i = 0; i < emails.length; i++) {
			if(isSpam[i]) {
				
				String[] words = emails[i].getWords();
				
				for(String word: words) {
					
					if(!this.contain(spamWords, spamWordsCount, word)) {
						
						if(spamT.get(word) == null) {
							spamT.put(word, 1);
						} else {
							spamT.put(word, ((int) spamT.get(word))+1);
						}
						addSpamWord(word);
						
					}

				}
				
				trainedSpam++;
				spamWords = new String[10];
				spamWordsCount = 0;
				
			} else {
				
				String[] words = emails[i].getWords();
				
				for(String word: words) {
					
					if(!this.contain(nonSpamWords, nonSpamWordsCount, word)) {
						
						if(nonspamT.get(word) == null) {
							nonspamT.put(word, 1);
						} else {
							nonspamT.put(word, ((int) nonspamT.get(word))+1);
						}
						addNonSpamWord(word);
						
					}
				}
				
				trainedSafe++;
				nonSpamWords = new String[10];
				nonSpamWordsCount = 0;
				
			}
		}
		
		
		String[] keys = receivedEmails.getIDs();
		for(int i = 0; i < keys.length; i++) {
			
			String[] words = receivedEmails.getWords(keys[i]);
			int newScore = this.calculateSpamScore(words);
			receivedEmails.updateSpamScore(keys[i], newScore);
			
		}
		
	}
	
	/**
	 * @param words - a String array
	 * @param word - a String
	 * @return - whether the array contains the String
	 * O(n)
	 */
	public boolean contain(String[] words, int wordCount,String word) {
		
		for(int i = 0; i < wordCount; i++) {
			if(words[i].equals(word)) {
				return true;
			}
		}
		return false;
			
	}
	
	
	/**
	 * @param word - newly analyzed word in a email
	 * This method mark a word as analyzed in a safe email.
	 * O(n)
	 */
	public void addNonSpamWord(String word) {

		if(nonSpamWordsCount == nonSpamWords.length) {
			
			String[] temp = new String[nonSpamWordsCount*2];
			for(int i = 0; i < nonSpamWordsCount; i++) {
				temp[i] = nonSpamWords[i];
			}
			nonSpamWords = temp;
			
		}
		
		nonSpamWords[spamWordsCount] = word;
		
	}
	
	/**
	 * @param word - newly analyzed word in a email
	 * This method mark a word as analyzed in a spam email.
	 * O(n)
	 */
	public void addSpamWord(String word) {

		if(spamWordsCount == spamWords.length) {
			
			String[] temp = new String[spamWordsCount*2];
			for(int i = 0; i < spamWordsCount; i++) {
				temp[i] = spamWords[i];
			}
			spamWords = temp;
			
		}
		
		spamWords[spamWordsCount] = word;
		
	}

	/**
	 * @return - ranked emails with their spam Score
	 * O(n)
	 */
	public String getEmailRanking() {
		
		String[] rankSpam = receivedEmails.rankEmails();
		String rank = "";
		for(int i = 0; i < rankSpam.length; i++) {
			rank += rankSpam[i] + "\n";	
		}
		return rank;
		
	}
}
