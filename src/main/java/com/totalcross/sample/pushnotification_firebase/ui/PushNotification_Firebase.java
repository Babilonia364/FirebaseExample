package com.totalcross.sample.pushnotification_firebase.ui;

import com.totalcross.sample.pushnotification_firebase.model.User;

import totalcross.firebase.FirebaseMessagingService;
import totalcross.firebase.RemoteMessage;
import totalcross.firebase.iid.FirebaseInstanceId;
import totalcross.firebase.iid.FirebaseInstanceIdService;
import totalcross.sys.Settings;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Container;

//URL do BD: push-notification-95de7.firebaseapp.com
//Lembrar de colocar a key no POM e no FirebaseExampleApp
//Esse codigo so implementa as funcoes do bd, para implementar as outras funcoes nativamente do firebase
//Sera necessario modificar o POM do projeto mas quem sabe fazer isso e o italo, perguntar para ele depois.

public class PushNotification_Firebase extends Container
{
	public static String firebaseToken;
	public static User loggedUser;
	
	public PushNotification_Firebase()
	{
		firebaseToken = FirebaseInstanceId.getInstance().getToken();
		System.out.println("FIREBASE TOKEN: " + firebaseToken);
	}
	
	static
	{
		Settings.applicationId = "TCFB";
	}
	
	protected FirebaseInstanceIdService initFirebaseInstanceIdService()
	{
		return new FirebaseInstanceIdService()
		{
			@Override
			public void onTokenRefresh()
			{
				firebaseToken = FirebaseInstanceId.getInstance().getToken();
				System.out.println("Token refresh");
			}
		};
	}
	
	protected FirebaseMessagingService initFirebaseMessagingService()
	{
		return new FirebaseMessagingService()
		{
			@Override
			public void onMessageReceived(RemoteMessage remoteMessage)
			{
				Vm.debug("I was called");
				System.out.println((String)remoteMessage.getData().get("hello"));
			}
		};
	}
	
	public void initUI()
	{
		Button onToken = new Button("onToken");
		
		add(onToken, CENTER, TOP, PREFERRED, PREFERRED);
		
		onToken.addPressListener((e) -> {
			System.out.println(initFirebaseInstanceIdService());
		});
	}
}