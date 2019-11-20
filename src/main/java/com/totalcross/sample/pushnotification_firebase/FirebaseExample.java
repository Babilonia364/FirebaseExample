package com.totalcross.sample.pushnotification_firebase;

import com.totalcross.sample.pushnotification_firebase.ui.PushNotification_Firebase;

import totalcross.sys.Settings;
import totalcross.ui.MainWindow;

public class FirebaseExample extends MainWindow
{
	public FirebaseExample()
	{
		super("Pushnotification_firebase", NO_BORDER);
		setUIStyle(Settings.MATERIAL_UI);
	}

	static {
		Settings.applicationId = "TCIG";
		Settings.appVersion = "1.0.1";
	}
	
	public void initUI()
	{
		swap(new PushNotification_Firebase());
	}
}
