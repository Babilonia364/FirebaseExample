package com.totalcross.sample.pushnotification_firebase.control;

//Classe usada para implementar a comunicação com o firebase usando a api rest

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.totalcross.sample.pushnotification_firebase.model.User;
import com.totalcross.sample.pushnotification_firebase.util.ResponseDataURL;

import totalcross.io.ByteArrayStream;
import totalcross.json.JSONFactory;
import totalcross.json.JSONObject;
import totalcross.net.HttpStream;
import totalcross.net.URI;
import totalcross.net.ssl.SSLSocketFactory;

//Classe geral que eu tinha feito a muito tempo para o exemplo do instagram
//Na classe original contem inumeros metodos para as interacoes de api rest
//Nessa atual tem apegar um método para "registrar usuario" que eu tava usando para registrar genericamente o usuario
//em um banco de dados como se fosse qualquer outra informacao
public class CommunicationControl
{
	//url do projeto que testava no firebase
	private final String baseURL = "https://goclean-application.firebaseio.com/";

	//metodo usando o pst para registrar informacoes no banco de dados
	public String doRegister(User user) throws IOException {
		
		String responseMessage = "";

		String url = baseURL + "/users/" + user.getInfo1() + ".json";	//Essa linha talvez deva ajeitar a informação, tava comparando ontem com o original da goclean
		JSONObject obj = new JSONObject(user);

		//O restante é usar a api rest, apenas copiei o exemplo do docs
		HttpStream.Options options = new HttpStream.Options();

		try {
			
			options.httpType = HttpStream.PUT;
			options.data = obj.toString();
			options.postHeaders.put("Content-type", "application/json");

			if (url.startsWith("https:")) {
				options.socketFactory = new SSLSocketFactory();
			}

			HttpStream httpStreamData = new HttpStream(new URI(url), options);

			ByteArrayStream bas = new ByteArrayStream(4096);
			bas.readFully(httpStreamData, 10, 2048);
			String data = new String(bas.getBuffer(), 0, bas.available());

			Response<ResponseDataURL> response = new Response<>();
			response.responseCode = httpStreamData.responseCode;
			responseMessage += ((response.responseCode) + "\n");

			if (httpStreamData.responseCode == 200) {
				
				response.data = (ResponseDataURL) JSONFactory.parse(data, ResponseDataURL.class);

			} else {
				
				responseMessage += "Error: " + response.data.getError() + "\n";
				responseMessage += "AcessToken: " + response.data.getAcessToken();
			}

			bas.close();

		} catch (IOException e) {
			
			responseMessage = "erro";
			
		} catch (InstantiationException | InvocationTargetException | NoSuchMethodException
				| IllegalAccessException e) {
			e.printStackTrace();
		}

		return responseMessage;
	}

	public static class Response<T> {
		
		public T data;
		public int responseCode;

		@Override
		public String toString() {
			return "Response{" + "data=" + data + ", responseCode=" + responseCode + '}';
		}
	}
}
