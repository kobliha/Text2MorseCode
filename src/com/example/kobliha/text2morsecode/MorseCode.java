package com.example.kobliha.text2morsecode;

import java.util.Hashtable;
import java.text.Normalizer;

public class MorseCode {

	private static Hashtable<Character, String> morseCharacters = new Hashtable<Character, String>();

	// Special Morse code separators
	public static final String WORD_SEPARATOR				= "       ";
	public static final String CHARACTER_SEPARATOR			= "   ";

	// Special Morse code strings
	public static final String MORSE_WAIT					= ". - . . .";
	public static final String MORSE_INVITATION_TO_TRANSMIT	= "- . -";
	public static final String MORSE_ERROR					= ". . . . . . . .";
	public static final String MORSE_END_OF_WORK			= ". . . - . -";
	public static final String MORSE_UNDERSTOOD				= ". . . - .";
	public static final String MORSE_STARTING_SIGNAL		= "- . - . -";

	/**
	 * Constructor
	 */
	public MorseCode() {
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
		morseCharacters.put(':', "- - - . . .");
		morseCharacters.put('?', ". . - - . .");
		morseCharacters.put('\'', ". - - - - .");
		morseCharacters.put('-', "- . . . . -");
		morseCharacters.put('/', "- . . - .");
		morseCharacters.put('(', "- . - - . -");
		morseCharacters.put(')', "- . - - . -");
		morseCharacters.put('"', ". - . . - .");
		morseCharacters.put('@', ". - - . - .");
		morseCharacters.put('=', "- . . . -");

		// word separators
		morseCharacters.put(' ', WORD_SEPARATOR);
		morseCharacters.put('\n', WORD_SEPARATOR);
		morseCharacters.put('\t', WORD_SEPARATOR);
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
		char[] stringArray =
			// translates all non-ASCII characters to their ASCII representation
			Normalizer.normalize(translateThis, Normalizer.Form.NFD)
			.replaceAll("[^\\p{ASCII}]", "")
        	// all Morse code letters are upper-case
        	.toUpperCase()
        	// translating char by char
        	.toCharArray();

		String morseText = "";
		Character charToMorse;

		for (int index=0; index < stringArray.length; index++) {
			charToMorse = stringArray[index];
			morseText += (morseText.length() == 0 ? "" : CHARACTER_SEPARATOR)
				+ charToMorse(charToMorse);
		}

		return morseText;
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
