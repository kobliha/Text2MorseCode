package com.example.kobliha.text2morsecode;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSetting {
	public static final String PREFERENCES_ID = "Text2MorseCode";

	// Contains user settings
	private static SharedPreferences userSettings;
	// Editor for user settings
	private static SharedPreferences.Editor editor;

	/**
	 * Constructor. Reads user settings and initializes the user
	 * settings editor.
	 * 
	 * @param c Context
	 */
	public UserSetting (Context c) {
		userSettings = c.getSharedPreferences(PREFERENCES_ID, 0);
		editor = userSettings.edit();
	}

	/**
	 * Returns current settings (String) entry.
	 * 
	 * @param String key
	 * @return String value
	 */
	public String getString (String key) {
		return userSettings.getString(key, null);
	}

	/**
	 * Adjusts new settings (String) entry.
	 * 
	 * @param String key
	 * @param String value
	 */
	public void setString (String key, String value) {
		editor.putString(key, value);
	}
	
	/**
	 * Returns current settings (boolean) entry.
	 * 
	 * @param String key
	 * @return boolean value
	 */
	public boolean getBoolean (String key) {
		return userSettings.getBoolean(key, (Boolean) null);
	}

	/**
	 * Adjusts new settings (boolean) entry.
	 * 
	 * @param String key
	 * @param boolean value
	 */
	public void setBoolean (String key, boolean value) {
		editor.putBoolean(key, value);
	}

	/**
	 * Saves the current settings state.
	 */
	public void Save() {
		editor.commit();
	}
}
