package main;

import java.util.HashMap;
import java.util.Random;

public class WordList {

	private HashMap<String, Word> wordList = new HashMap<String, Word>();
	private Random rand = new Random();
	
	public void updateFollowsAssoc(String[] words) {
		for(int i = 0; i < words.length; i++){
			String curString = words[i];
			//Check if it's in the wordList yet.
			Word curWord = wordList.get(curString);
			if(curWord == null){
				wordList.put(curString, new Word(curString));
				curWord = wordList.get(curString);
			}
			if(i+1 < words.length){
				//Get the next word and add it to the current word's frequency list.
				String nextWord = words[i+1];
				curWord.incrementFollows(nextWord);
			}
		}
	}
	
	public Word getWordFromString(String key){
		return wordList.get(key);
	}
	
	public Word randomWord(){
		Object[] wordCollection = wordList.values().toArray();
		return (Word) wordCollection[rand.nextInt(wordCollection.length)];
	}

}
