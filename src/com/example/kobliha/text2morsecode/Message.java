package com.example.kobliha.text2morsecode;

import android.content.Context;
import android.widget.Toast;

public class Message {
	public static void showWarning (Context c, String text) {
	   	Toast.makeText(c, text, Toast.LENGTH_LONG).show();
	}
}
