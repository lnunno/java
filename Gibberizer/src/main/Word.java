package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class Word {
	
	
	private String wordAsString = "";
	private HashMap<String, Integer> followingFrequency = new HashMap<String, Integer>(); 
	private int numberFollowingWords = 0;
	
	private ArrayList<String> followingWords = new ArrayList<String>();
	private Random rand = new Random();
	
	public Word(String wordAsString){
		this.wordAsString = wordAsString;
	}
	
	public void incrementFollows(String nextWord){
		++numberFollowingWords;
		followingWords.add(nextWord);
		Integer frequency = followingFrequency.get(nextWord);
		if(frequency == null) followingFrequency.put(nextWord, 1);
		else followingFrequency.put(nextWord, frequency+1);
	}
	
	public int getNumberTimesFollowsWord(String nextWord){
		Integer i = followingFrequency.get(nextWord);
		if(i == null) return 0;
		else return i;
	}
	
	public String followingAssocAsString(){
		String output = "Following frequencies:\n";
		for(Entry<String,Integer> entry : followingFrequency.entrySet()){
			output += entry.getKey() + ": " + entry.getValue() + '\n';
		}
		return output;
	}
	
	public String getWeightedRandomNextWord(){
		return followingWords.get(rand.nextInt(followingWords.size()));
	}

	@Override
	public String toString() {
		return wordAsString;
	}
	
	public int getNumberOfFollowingWords(){
		return numberFollowingWords;
	}
}
