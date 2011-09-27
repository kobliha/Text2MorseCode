package com.example.kobliha.text2morsecode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EnterText extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        adjustWidgets();
    }

    /**
     * Reads and processes the entered text
     */
    public void runText2MorseCode (View v) {
    	EditText enteredTextWidget = (EditText)findViewById(R.id.text_entry);
    	String enteredText = enteredTextWidget.getText().toString();

    	// empty string entered
    	if (enteredText.length() == 0) {
    		// FIXME: localization
    		Message.showWarning(EnterText.this, "Enter text for translation first");
    		return;
    	}

    	adjustWidgets();
    }

    /**
     * Clears the UI
     */
    public void runClearWidgets (View v) {
    	EditText enteredTextWidget = (EditText)findViewById(R.id.text_entry);
    	enteredTextWidget.setText("");

    	adjustWidgets();
    }

    /**
     * Adjusts EditText widgets according their state and translates
     * the entered text if appropriate.
     */
    public void adjustWidgets () {
    	EditText enteredTextWidget = (EditText)findViewById(R.id.text_entry);
    	String enteredText = enteredTextWidget.getText().toString();

    	EditText morseCodeWidget = (EditText)findViewById(R.id.morse_code);

    	// nothing entered
    	if (enteredText.length() == 0) {
    		morseCodeWidget.setEnabled(false);
    		morseCodeWidget.setText("");
    	} else {
    		morseCodeWidget.setEnabled(true);
        	MorseCode morseCode = new MorseCode();
        	morseCodeWidget.setText(morseCode.textToMorse(enteredText));
    	}
    }
}