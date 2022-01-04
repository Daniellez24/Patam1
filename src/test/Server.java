package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class Server {

	public interface ClientHandler{
		void handleClient(InputStream inFromClient, OutputStream outToClient);
		default void Bye(OutputStream socketOutputStream) throws IOException {
			socketOutputStream.write("bye".getBytes()); }
	}

	volatile boolean stop;

	public Server() {
		stop=false;
	}
	
	
	private void startServer(int port, ClientHandler ch) {
		try {
			ServerSocket server = new ServerSocket(port);
			server.setSoTimeout(1000);
			while(!stop){
				try (
					Socket aClient = server.accept();
					InputStream fromClient = aClient.getInputStream();
					OutputStream toClient = aClient.getOutputStream();
				) {
					ch.handleClient(fromClient, toClient);
					ch.Bye(toClient);
				}catch(SocketTimeoutException e){
					stop();
				}
			}
			server.close();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// runs the server in its own thread
	public void start(int port, ClientHandler ch) {

		new Thread(()->startServer(port,ch)).start();
	}
	
	public void stop() {

		stop=true;
	}
}
