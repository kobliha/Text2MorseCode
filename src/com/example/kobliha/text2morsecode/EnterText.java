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
    		Message.showWarning(EnterText.this, "Enter string for translation first");
    		return;
    	}
    	
    	MorseCode morseCode = new MorseCode();
    	Toast.makeText(EnterText.this, morseCode.textToMorse(enteredText),
    		Toast.LENGTH_LONG).show();
    }
}