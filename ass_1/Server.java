

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.io.*;

public class Server {

	public static void main(String[] args) throws Exception {
	
		ServerSocket ss = new ServerSocket(0); //server is waiting for the connection
		System.out.println(ss.getLocalPort());
		Socket s = ss.accept(); //Blocking
		System.out.println("Client Connected");

		PrintWriter writer = new PrintWriter(s.getOutputStream());
		writer.println("Hello : " + s.getInetAddress().getHostName() + " " + "your ip address is : " + s.getInetAddress().getHostAddress());
		writer.flush();
	
		}

	}


