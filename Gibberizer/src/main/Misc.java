package main;

public class Misc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "Hello  this is a     string\rwith   spaces and whitespace.\nDoes this work?\tTabbed.";
		String[] words = s.split("\\s+");
		for(String str : words) System.out.println(str);
	}

}
