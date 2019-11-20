package com.totalcross.sample.pushnotification_firebase.model;

//Classe user genérica para postar e receber as informacoes em um BD
//Quando crar o bd, criar ele com 3 campos de string genericos chamados info1, info2 e info3
public class User {

	private String info1;
	private String info2;
	private String info3;

	public User(String username, String firstName, String password) {

		this.info1 = username;
		this.info2 = firstName;
		this.info3 = password;
	}

	public String getInfo1() {
		return info1;
	}

	public void setInfo1(String info1) {
		this.info1 = info1;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	public String getInfo3() {
		return info3;
	}

	public void setInfo3(String info3) {
		this.info3 = info3;
	}
}
