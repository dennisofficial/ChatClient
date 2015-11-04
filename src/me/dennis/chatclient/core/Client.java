package me.dennis.chatclient.core;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements Runnable {

	Socket connection;
	
	public static void main(String[] args) {
		new Client();
	}
	
	public Client() {
		try {
			Logger.info("Connecting to server...");
			connection = new Socket(InetAddress.getLocalHost(), 8231);
			Logger.info("Connection made!");
			new Thread(this).start();
			DataInputStream input = new DataInputStream(new BufferedInputStream(connection.getInputStream()));
			while (true) {
				Logger.info(input.readUTF());
			}
		}
		catch (ConnectException ex) {
			Logger.err("Could not connect to server!");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				output.writeUTF(reader.readLine());
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}
