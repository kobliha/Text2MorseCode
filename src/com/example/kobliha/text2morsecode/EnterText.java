package com.example.kobliha.text2morsecode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class EnterText extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
     * Reads and stores the currently selected output type from UI.
     */
    private void storeCurrentOutputType() {
    	UserSetting userSettings = new UserSetting(this.getBaseContext());

    	final RadioButton machineReadableOutput = (RadioButton) findViewById(R.id.output_type_MR);
    	String outputType = (machineReadableOutput.isChecked() ?
    		MorseCode.OUTPUT_TYPE_MACHINE_READABLE
    		:
    		MorseCode.OUTPUT_TYPE_HUMAN_READABLE);

    	userSettings.setString("outputType", outputType);
    	userSettings.Save();
    }
    
    /**
     * Adjusts EditText widgets according their state and translates
     * the entered text if appropriate.
     */
    public void adjustWidgets () {
    	EditText enteredTextWidget = (EditText)findViewById(R.id.text_entry);
    	String enteredText = enteredTextWidget.getText().toString();

    	EditText morseCodeWidget = (EditText)findViewById(R.id.morse_code);

    	storeCurrentOutputType();

    	// nothing entered
    	if (enteredText.length() == 0) {
    		morseCodeWidget.setEnabled(false);
    		morseCodeWidget.setText("");
    	} else {
    		morseCodeWidget.setEnabled(true);
    		MorseCode morseCode = new MorseCode(this.getBaseContext());
	        morseCodeWidget.setText(morseCode.textToMorse(enteredText));
    	}
	}
}