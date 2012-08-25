package com.wufulin.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyServer {

	private final static Logger logger = Logger.getLogger(MyServer.class
			.getName());

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(10000);

		while (true) {
			Socket socket = server.accept();
			socket.setSoTimeout(10*1000);
			invoke(socket);
		}
	}

	private static void invoke(final Socket socket) throws IOException {
		new Thread(new Runnable() {
			public void run() {
				ObjectInputStream is = null;
				ObjectOutputStream os = null;

				try {
					is = new ObjectInputStream(new BufferedInputStream(
							socket.getInputStream()));
					os = new ObjectOutputStream(new BufferedOutputStream(
							socket.getOutputStream()));

					Object obj = is.readObject();
					User user = (User) obj;
					System.out.println("user: " + user.getName() + "/"
							+ user.getPassword());

					user.setName(user.getName() + "_new");
					user.setPassword(user.getPassword() + "_new");

					os.writeObject(user);
					os.flush();
				} catch (IOException e) {
					logger.log(Level.SEVERE, null, e);
				} catch (ClassNotFoundException e) {
					logger.log(Level.SEVERE, null, e);
				} finally {
					try {
						is.close();
					} catch (IOException e) {
						logger.log(Level.SEVERE, null, e);
					}
					try {
						os.close();
					} catch (IOException e) {
						logger.log(Level.SEVERE, null, e);
					}
					try {
						socket.close();
					} catch (IOException e) {
						logger.log(Level.SEVERE, null, e);
					}
				}
			}
		}).start();
	}
}
