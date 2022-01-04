package test;


import test.Commands.DefaultIO;
import test.Server.ClientHandler;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class AnomalyDetectionHandler implements ClientHandler{

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		SocketIO socket = new SocketIO(inFromClient, outToClient);
		CLI aClient = new CLI(socket);
		aClient.start();
	}


	public class SocketIO implements DefaultIO{

		Scanner in;
		PrintWriter out;

		public SocketIO(InputStream inFromClient, OutputStream outToClient) {
			this.in = new Scanner(inFromClient);
			this.out = new PrintWriter(outToClient);
		}

		@Override
		public String readText() {
			return in.nextLine();

		}

		@Override
		public void write(String text) {
			out.print(text);
			out.flush();
		}

		@Override
		public float readVal() {
			return in.nextFloat();
		}

		@Override
		public void write(float val) {
			out.print(val);
			out.flush();
		}

		public void close() {
			in.close();
			out.close();
		}
	}


}
