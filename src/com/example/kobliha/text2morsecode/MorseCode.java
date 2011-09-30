package com.example.kobliha.text2morsecode;

import java.text.Normalizer;

import android.content.Context;

public class MorseCode {
	public static final String OUTPUT_TYPE_HUMAN_READABLE = "HR";
	public static final String OUTPUT_TYPE_MACHINE_READABLE = "MR";
	public static final String OUTPUT_TYPE_DEFAULT = OUTPUT_TYPE_HUMAN_READABLE;

	private MorseCharacter morseCharacters = new MorseCharacter();
	
	private String outputType = OUTPUT_TYPE_DEFAULT;
	
	/**
	 * Constructor
	 */
	public MorseCode(Context c) {
		UserSetting userSettings = new UserSetting(c);
		String storedOutputType = userSettings.getString("outputType");
		// might not be stored yet
		if (storedOutputType != null)
			outputType = storedOutputType;

		if (outputType.equals(OUTPUT_TYPE_HUMAN_READABLE)) {
			morseCharacters = new MorseCharacterHumanReadable();
		} else if (outputType.equals(OUTPUT_TYPE_MACHINE_READABLE)) {
			morseCharacters = new MorseCharacterMachineReadable();
		} else {
			// FIXME: localization
			Message.showWarning(c, "Selected output type '" + outputType + "' is unknown.");
			// FIXME: throw an exception
			return;
		}
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
			morseText += (morseText.length() == 0 ? "" : morseCharacters.getWordSeparator())
				+ wordToMorse(words[wordIndex]);
		}
		
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
			morseWord += (morseWord.length() == 0 ? "" : morseCharacters.getCharacterSeparator())
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
    	if (! morseCharacters.characters.containsKey(chr)) {
    		return morseCharacters.getError();
    	}

		return morseCharacters.characters.get(chr);
    }
}
