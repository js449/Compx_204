

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws IOException {
		
		
		InetAddress IP = InetAddress.getByName(args[0]);
		int port = Integer.parseInt(args[1]);

		Socket s = new Socket(IP, port); // using socket to create the connection with server
				
		InputStreamReader in = new InputStreamReader(s.getInputStream()); //to read message from server
		BufferedReader bReader = new BufferedReader(in); //returns lines of text
		String line = bReader.readLine();
		System.out.println("Server : "  + line);
		
	}

}
