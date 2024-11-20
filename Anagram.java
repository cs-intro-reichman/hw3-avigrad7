/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		str1 = preProcess(str1);
		str2 = preProcess(str2);

		boolean found = false;
		for (int i = 0; i < str1.length(); i++) {
			//Need this because preProcess does not remove spaces for some reason
			if (str1.charAt(i) != ' ') {
				for (int u = 0; u < str2.length(); u++) {
					if (str2.charAt(u) == str1.charAt(i)) {
						found = true;
						str2 = str2.substring(0, u) + str2.substring(u+1);
						u = str2.length();
					}
				}	
				if (!found) {
					return false;
				}
				found = false;
			}
		}
		return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		String result = "";
		str = str.toLowerCase();
		for (int i = 0; i < str.length(); i++) {
			if(isValidLetter(str.charAt(i))) {
				result += str.charAt(i);
			}
		}
		return result;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		str = preProcess(str);
		String anagram = "";
		while (str.length() > 0) {
			int randomPosition = (int)(Math.random() * str.length());
			anagram += str.charAt(randomPosition);
			str = str.substring(0, randomPosition) + str.substring(randomPosition+1);
			
		}
		return anagram;
	}

	/**
	 * Checks if the given letter should be preserved
	 * @param letter The letter to check
	 * @return true if it should be preserved false if not
	 */
	public static boolean isValidLetter(char letter) {
		char[] validLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' '};
		for (char validLetter : validLetters) {
			if (letter == validLetter) {
				return true;
			}
		}
		return false;
	}
}
