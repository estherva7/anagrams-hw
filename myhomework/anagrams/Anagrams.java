package myhomework.anagrams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Anagrams {
	
	private static HashSet<String> dictionary = new HashSet<String>();
	private static HashSet<String> sortedSet = new HashSet<String>();

	/**
	 * @param args
	 * arg[0] is the complete path to dictionary file
	 * arg[1] to arg[N] are the input words
	 */
	public static void main(String[] args) {        
		File file = new File(args[0]); 
		try {
	        Scanner sc = new Scanner(file);
	        while (sc.hasNextLine()) {
	            String entry = sc.nextLine();
	            addWord(entry); // Adds word into dictionary 
	        }
	        sc.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		
		for(int i = 1; i < args.length; i++) {
			char[] charArray = args[i].toCharArray();
			java.util.Arrays.sort(charArray);
			String sorted = new String(charArray);
			if(sortedSet.contains(sorted)){ // Checks if there are any anagrams at all
				printAnagrams(args[i]);
			}
        }
	}
	
	/**
	 * Adds word into dictionary. The sorted string is also added to sortedSet
	 * @param word is the word to add in the dictionary
	 */
	public static void addWord(String word){
		if (word != null){
			dictionary.add(word);
			char[] charArray = word.toCharArray();
			java.util.Arrays.sort(charArray);
			sortedSet.add(new String(charArray));
		}
	}
	
	/**
	 * Prints anagrams for word
	 * @param s - the word string
	 */
	public static void printAnagrams(String s){
		if (s == null) return;
		HashSet<String> anagrams = getAllAnagrams(s);
		Iterator<String> iterator = anagrams.iterator();
		while (iterator.hasNext()){
			String a = iterator.next();
			if (dictionary.contains(a) && !a.equals(s)){ // Only prints if exists in dictionary
				System.out.println(a);
			}
		}
	}
	
	/**
	 * Gets all possible anagrams for a word
	 * @param s - the word string
	 */
	public static HashSet<String> getAllAnagrams(String s){
		if (s == null) return null;
		HashSet<String> anagrams = new HashSet<String>();
		if (s.length() == 1){
			anagrams.add(s);
			return anagrams;
		}
		char firstChar = s.charAt(0);
		String remaining = s.substring(1);
		HashSet<String> extendedSet = getAllAnagrams(remaining);
		for (String word: extendedSet){
			for (int i = 0; i <= word.length(); i++){
				anagrams.add(addCharAt(word, firstChar, i));
			}
		}
		return anagrams;
	}
	
	/**
	 * Returns a string with a character added at position pos
	 * @param s - the word string
	 * @param c - character to add
	 * @param pos - the index position where c will be added
	 */
	private static String addCharAt(String s, char c, int pos){
		return new String(s.substring(0,pos) + c + s.substring(pos));
	}

}
