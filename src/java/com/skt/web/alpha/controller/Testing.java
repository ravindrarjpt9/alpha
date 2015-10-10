package com.skt.web.alpha.controller;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class Testing {

public static void main(String[] args) {
	Sender sender = new Sender("AIzaSyDXvCl_735hYGGKhDNiYDmK9h-jzACRuaQ");
	Message msg = new Message.Builder().addData("message", "DELETED:Hello Sumit")
			.build();
	
		Result result = null;
		try {
			result = sender.send(msg, "APA91bGVS8JtgiD5CpuzA2hd-NvovmV-l7urcuWomo1ba08EblkTt6ZF0EY3Tt7rE_QqJWZG82H5wY5Bo9EQAs_EIzA8_-Og3MaOQkz5HcmycPRa_TtjLAvkC6As3rGMrfUiI34c_rrD", 1);
			System.out.println("Sent message to one device: " + result);
			System.out.println(result.getMessageId());
		} catch (IOException e) {
			e.printStackTrace();
			
		
		System.out.println("Sent message to one device: " + result);


}
		
}
}
