package com.example.kobliha.text2morsecode;

import java.util.Hashtable;
import java.text.Normalizer;

import android.content.Context;

public class MorseCode {

	private static Hashtable<Character, String> morseCharacters =
		new Hashtable<Character, String>();

	// Special Morse code separators
	public static final String WORD_SEPARATOR_MR = "       ";  // 7 spaces 
	public static final String CHARACTER_SEPARATOR_MR = "   "; // 3 spaces
	public static final String WORD_SEPARATOR_HR = "//";
	public static final String CHARACTER_SEPARATOR_HR = "/";

	private static String wordSeparator = "";
	private static String characterSeparator = "";
	
	// Special Morse code strings
	public static final String MORSE_WAIT = ". - . . .";
	public static final String MORSE_INVITATION_TO_TRANSMIT = "- . -";
	public static final String MORSE_ERROR = ". . . . . . . .";
	public static final String MORSE_END_OF_WORK = ". . . - . -";
	public static final String MORSE_UNDERSTOOD = ". . . - .";
	public static final String MORSE_STARTING_SIGNAL = "- . - . -";

	private static boolean minimizeOutput = false;

	public static final String OUTPUT_TYPE_HUMAN_READABLE = "HR";
	public static final String OUTPUT_TYPE_MACHINE_READABLE = "MR";
	public static final String OUTPUT_TYPE_DEFAULT = OUTPUT_TYPE_HUMAN_READABLE;
	
	/**
	 * Constructor
	 */
	public MorseCode(Context c) {
		UserSetting userSettings = new UserSetting(c);
		String outputType = userSettings.getString("outputType");
		if (outputType == null) outputType = OUTPUT_TYPE_DEFAULT;

		if (outputType == OUTPUT_TYPE_HUMAN_READABLE) {
			wordSeparator = WORD_SEPARATOR_HR;
			characterSeparator = CHARACTER_SEPARATOR_HR;
			minimizeOutput = true;
		} else if (outputType == OUTPUT_TYPE_MACHINE_READABLE) {
			wordSeparator = WORD_SEPARATOR_MR;
			characterSeparator = CHARACTER_SEPARATOR_MR;
			minimizeOutput = false;
		} else {
			// FIXME: throw an exception
			return;
		}

		// letters
		morseCharacters.put('A', ". -");
		morseCharacters.put('B', "- . . .");
		morseCharacters.put('C', "- . - .");
		morseCharacters.put('D', "- . .");
		morseCharacters.put('E', ".");
		morseCharacters.put('F', ". . - .");
		morseCharacters.put('G', "- - .");
		morseCharacters.put('H', ". . . .");
		morseCharacters.put('I', ". .");
		morseCharacters.put('J', ". - - -");
		morseCharacters.put('K', "- . -");
		morseCharacters.put('L', ". - . .");
		morseCharacters.put('M', "- -");
		morseCharacters.put('N', "- .");
		morseCharacters.put('O', "- - -");
		morseCharacters.put('P', ". - - .");
		morseCharacters.put('Q', "- - . -");
		morseCharacters.put('R', ". - .");
		morseCharacters.put('S', ". . .");
		morseCharacters.put('T', "-");
		morseCharacters.put('U', ". . -");
		morseCharacters.put('V', ". . . -");
		morseCharacters.put('W', ". - -");
		morseCharacters.put('X', "- . . -");
		morseCharacters.put('Y', "- . - -");
		morseCharacters.put('Z', "- - . .");

		// numbers
		morseCharacters.put('1', ". - - - -");
		morseCharacters.put('2', ". . - - -");
		morseCharacters.put('3', ". . . - -");
		morseCharacters.put('4', ". . . . -");
		morseCharacters.put('5', ". . . . .");
		morseCharacters.put('6', "- . . . .");
		morseCharacters.put('7', "- - . . .");
		morseCharacters.put('8', "- - - . .");
		morseCharacters.put('9', "- - - - .");
		morseCharacters.put('0', "- - - - -");

		// special characters
		morseCharacters.put('.', ". - . - . -");
		morseCharacters.put(',', "- - . . - -");
		morseCharacters.put('?', ". . - - . .");
		morseCharacters.put('\'', ". - - - - .");
		morseCharacters.put('!', "- . - . - -");
		morseCharacters.put('/', "- . . - .");
		morseCharacters.put('(', "- . - - . -");
		morseCharacters.put(')', "- . - - . -");
		morseCharacters.put('&', ". - . . .");
		morseCharacters.put(':', "- - - . . .");
		morseCharacters.put(';', "- . - . - .");
		morseCharacters.put('=', "- . . . -");
		morseCharacters.put('+', "- . . . -");
		morseCharacters.put('-', "- . . . . -");
		morseCharacters.put('_', ". . - - . -");
		morseCharacters.put('"', ". - . . - .");
		morseCharacters.put('$', ". . . - . . -");
		morseCharacters.put('@', ". - - . - .");

		// word separators
		morseCharacters.put(' ', wordSeparator);
		morseCharacters.put('\n', wordSeparator);
		morseCharacters.put('\t', wordSeparator);
		// CR is ignored
		morseCharacters.put('\r', "");
	}
	
	/**
	 * Translates given text into Morse code. Returned string consists of
	 * dots (.) for short and (-) dashes for long units. Character space ( )
	 * represents either character or word separators.
	 * 
	 * @param String text to translate
	 * @return String translated text
	 */
    public String textToMorse (String translateThis) {
    	String[] words =
			// translates all non-ASCII characters to their ASCII representation
    		Normalizer.normalize(translateThis, Normalizer.Form.NFD)
    		.replaceAll("[^\\p{ASCII}]", "")
    		// all Morse code letters are upper-case
    		.toUpperCase()
    		.split(" ");

    	String morseText = "";

		// translating word by word
		for (int wordIndex=0; wordIndex < words.length; wordIndex++) {
			morseText += (morseText.length() == 0 ? "" : wordSeparator)
				+ wordToMorse(words[wordIndex]);
		}

		// FIXME: spaces are already contained in the morseCharacters Hashtable
		if (minimizeOutput)
			morseText = morseText.replaceAll(" ", "");
		
		return morseText;
    }

    /**
     * Translates given word into its Morse code representation.
     * 
     * @param String word to translate
     * @return String Morse code representation
     */
    private String wordToMorse (String wrd) {
    	String morseWord = "";

    	char[] stringArray = wrd.toCharArray();
		for (int index=0; index < stringArray.length; index++) {
			morseWord += (morseWord.length() == 0 ? "" : characterSeparator)
				+ charToMorse(stringArray[index]);
		}

		return morseWord;
    }
    
    /**
     * Translates a single character into its Morse code representation.
     * If a given character is unknown, Morse code for error is returned.
     * 
     * @param Character character to translate
     * @return String Morse code representation
     */
    private String charToMorse (Character chr) {
    	// unknown character
    	if (! morseCharacters.containsKey(chr)) {
    		return MORSE_ERROR;
    	}

		return morseCharacters.get(chr);
    }
}
