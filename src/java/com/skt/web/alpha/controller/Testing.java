package com.skt.web.alpha.controller;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class Testing {

public static void main(String[] args) {
	Sender sender = new Sender("AIzaSyDXvCl_735hYGGKhDNiYDmK9h-jzACRuaQ");
	Message msg = new Message.Builder().addData("message", "Hello Sumit")
			.build();
	
		Result result = null;
		try {
			result = sender.send(msg, "APA91bEaNIAbLyzibD0N1xJQh61wZOeNxbiOY9dWbIcDB6M5daZvsKotThE68HHWcyxDlqcf-L5w9dso0MznrxCPQm4zzgz8MbSuxNgT7ACzZoKKn3irkO7RKM2Dtya9HjY-t6rMDuqK", 1);
			System.out.println("Sent message to one device: " + result);
			System.out.println(result.getMessageId());
		} catch (IOException e) {
			e.printStackTrace();
			
		
		System.out.println("Sent message to one device: " + result);


}
		
}
}
