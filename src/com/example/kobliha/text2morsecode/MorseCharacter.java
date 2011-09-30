package com.example.kobliha.text2morsecode;

import java.util.Hashtable;

public class MorseCharacter {

	// Must be initialized as empty, ancestors add entries there
	protected Hashtable<Character, String> characters
		= new Hashtable<Character, String>();
	
	// Special Morse code separators
	protected String wordSeparator;
	protected String characterSeparator;

	// Special Morse code strings
	protected String wait;
	protected String invitationToTransmit;
	protected String error;
	protected String endOfWork;
	protected String understood;
	protected String startingSignal;

	public MorseCharacter() {
	}

	public String getWordSeparator() {
		return wordSeparator;
	}

	public String getCharacterSeparator() {
		return characterSeparator;
	}

	public String getError() {
		return error;
	}
}
