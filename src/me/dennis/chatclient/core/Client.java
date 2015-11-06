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
import java.net.SocketException;

import org.omg.CORBA.portable.UnknownException;

public class Client implements Runnable {

	Socket connection;
	
	public static void main(String[] args) {
		new Client();
	}
	
	public Client() {
		Logger.info("Enter server IP: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String ip = "";
		try {
			ip = reader.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Logger.info("Connecting to server...");
			connection = new Socket(InetAddress.getByName(ip), 8231);
			Logger.info("Connection made!");
			new Thread(this).start();
			DataInputStream input = new DataInputStream(new BufferedInputStream(connection.getInputStream()));
			while (true) {
				Logger.info(input.readUTF());
			}
		}
		catch (UnknownException ex) {
			Logger.err("Unknown host!");
		}
		catch (ConnectException ex) {
			Logger.err("Could not connect to server!");
		}
		catch (SocketException ex) {
			Logger.err("Server disconnected!");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			Logger.info("Enter your username!");
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
