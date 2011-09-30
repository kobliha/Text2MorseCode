package com.example.kobliha.text2morsecode;

public class MorseCharacterHumanReadable extends MorseCharacter {
	public MorseCharacterHumanReadable() {
		wordSeparator = "//";
		// Special Morse code separators
		characterSeparator = "/";

		// Special Morse code strings
		wait = ".-...";
		invitationToTransmit = "-.-";
		error = "........";
		endOfWork = "...-.-";
		understood = "...-.";
		startingSignal = "-.-.-";

		// letters
		characters.put('A', ".-");
		characters.put('B', "-...");
		characters.put('C', "-.-.");
		characters.put('D', "-..");
		characters.put('E', ".");
		characters.put('F', "..-.");
		characters.put('G', "--.");
		characters.put('H', "....");
		characters.put('I', "..");
		characters.put('J', ".---");
		characters.put('K', "-.-");
		characters.put('L', ".-..");
		characters.put('M', "--");
		characters.put('N', "-.");
		characters.put('O', "---");
		characters.put('P', ".--.");
		characters.put('Q', "--.-");
		characters.put('R', ".-.");
		characters.put('S', "...");
		characters.put('T', "-");
		characters.put('U', "..-");
		characters.put('V', "...-");
		characters.put('W', ".--");
		characters.put('X', "-..-");
		characters.put('Y', "-.--");
		characters.put('Z', "--..");

		// numbers
		characters.put('1', ".----");
		characters.put('2', "..---");
		characters.put('3', "...--");
		characters.put('4', "....-");
		characters.put('5', ".....");
		characters.put('6', "-....");
		characters.put('7', "--...");
		characters.put('8', "---..");
		characters.put('9', "----.");
		characters.put('0', "-----");

		// special characters
		characters.put('.', ".-.-.-");
		characters.put(',', "--..--");
		characters.put('?', "..--..");
		characters.put('\'', ".----.");
		characters.put('!', "-.-.--");
		characters.put('/', "-..-.");
		characters.put('(', "-.--.-");
		characters.put(')', "-.--.-");
		characters.put('&', ".-...");
		characters.put(':', "---...");
		characters.put(';', "-.-.-.");
		characters.put('=', "-...-");
		characters.put('+', "-...-");
		characters.put('-', "-....-");
		characters.put('_', "..--.-");
		characters.put('"', ".-..-.");
		characters.put('$', "...-.. -");
		characters.put('@', ".--.-.");

		// word separators
		characters.put(' ', wordSeparator);
		characters.put('\n', wordSeparator);
		characters.put('\t', wordSeparator);
		// CR is ignored
		characters.put('\r', "");
	}
}
