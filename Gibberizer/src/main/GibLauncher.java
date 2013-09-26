package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GibLauncher {

	private final static String FILE_FOLDER = "src/textFiles/";
	private final static int NEWLINE_BUFFER = 20; 
	private final static String SEED_WORD = "I";
	
	public void launch(String[] args){
		if(args.length == 0){
			System.out.println("Usage: 100 partyUSA.txt (runs Gibberizer with 100 words with partyUSA.txt)");
			return;
		}
		int outputLength = Integer.parseInt(args[0]);
		String textString = "";
		for(int i = 1; i < args.length; i++){
			textString += fileToString(args[i])+"\n";
		}
		//System.out.println(textString);
		String[] words = textString.split("\\s+");
		WordList wordList = new WordList();
		wordList.updateFollowsAssoc(words);
		gibberize(wordList, SEED_WORD,outputLength);
	}
	
	private String fileToString(String fileName) {
		String textString = "";
		FileReader fileReader = null; 
		try {
			fileReader = new FileReader(FILE_FOLDER+fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader buffReader = new BufferedReader(fileReader);
		try {
			String curLine = buffReader.readLine();
			while(curLine != null){
				textString += curLine + "\n";
				curLine = buffReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return textString;
	}

	private void gibberize(WordList wordList, String seedWord, int outputLength) {
		Word curWord = wordList.getWordFromString(seedWord);
		if(curWord == null) curWord = wordList.randomWord();
		String output = curWord.toString();
		for(int i = 0; i < outputLength; i++){
			String nextWord;
			if(curWord.getNumberOfFollowingWords() > 0) nextWord = curWord.getWeightedRandomNextWord();
			else nextWord = wordList.randomWord().toString();
			String sep = "";
			if((i+1) % NEWLINE_BUFFER == 0) sep = "\n"; 
			output += " " + nextWord + sep;
			curWord = wordList.getWordFromString(nextWord);
		}
		System.out.println(output);
		
	}

	public static void main(String[] args) {
		GibLauncher launcher = new GibLauncher();
		launcher.launch(args);
	}

}
