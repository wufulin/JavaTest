package com.wufulin.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class MyGZIPClient {

	private final static Logger logger = Logger.getLogger(MyGZIPClient.class
			.getName());

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			Socket socket = null;
			ObjectOutputStream os = null;
			ObjectInputStream is = null;
			GZIPInputStream gzipis = null;
			GZIPOutputStream gzipos = null;

			try {
				socket = new Socket("localhost", 10000);
				socket.setSoTimeout(10 * 1000);

				gzipos = new GZIPOutputStream(socket.getOutputStream());
				os = new ObjectOutputStream(gzipos);
				User user = new User("user_" + i, "password_" + i);
				os.writeObject(user);
				os.flush();
				gzipos.flush();

				gzipis = new GZIPInputStream(socket.getInputStream());
				is = new ObjectInputStream(gzipis);
				Object obj = is.readObject();
				if (obj != null) {
					user = (User) obj;
					System.out.println("user: " + user.getName() + "/"
							+ user.getPassword());
				}
			} catch (IOException ex) {
				logger.log(Level.SEVERE, null, ex);
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					logger.log(Level.SEVERE, null, e);
				} finally {
					try {
						os.close();
					} catch (IOException e) {
						logger.log(Level.SEVERE, null, e);
					} finally {
						try {
							socket.close();
						} catch (IOException e) {
							logger.log(Level.SEVERE, null, e);
						}
					}
				}
			}
		}
	}
}
