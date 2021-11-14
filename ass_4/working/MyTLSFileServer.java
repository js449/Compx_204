import javax.net.ssl.*;  //Classes for using SSL sockets
import java.security.KeyStore; //Classes for loading keys for authentication and encryption
import java.security.cert.X509Certificate; //Holds a Certificate in X.509 format
import javax.naming.ldap.*; //Classes for parsing X.509 certificate
import javax.net.*; //Classes for “Socket Factories”
import javax.net.ssl.SSLServerSocketFactory; //to create SSLServerSocket objects
import javax.net.ssl.SSLSocketFactory; //to create SSLSocket objects
import java.net.*;
import java.io.*;

public class MyTLSFileServer {
	public static void main(String args[]) {
	try {
		KeyStore ks = KeyStore.getInstance("JKS");
		//store the passphrase to unlock the JKS file.
		char[] passphrase = "jsingh449".toCharArray();
		//load the keystore file.
		ks.load(new FileInputStream("server.jks"), passphrase);
		//use the KeyManager Class to manage the key
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(ks, passphrase);
		
		//Get an SSL Context that speaks some version of TLS 
		SSLContext ctx = SSLContext.getInstance("TLS");
		//initialise the SSL context with the keys. 
		ctx.init(kmf.getKeyManagers(), null, null);


		ServerSocketFactory ssf = ctx.getServerSocketFactory();
		SSLServerSocket sserverSocket =(SSLServerSocket)ssf.createServerSocket(40202);
		String EnabledProtocols[] ={"TLSv1.2", "TLSv1.1"};
		sserverSocket.setEnabledProtocols(EnabledProtocols);

		SSLSocket sclient = (SSLSocket)sserverSocket.accept();//blocking

		System.out.println("Connected");
		BufferedReader reader = new BufferedReader(new InputStreamReader(sclient.getInputStream()));
		//BufferedOutputStream writer = new BufferedOutputStream(sclient.getOutputStream());		
	
		String line = reader.readLine();
		System.out.println(line);
		
	}
	catch(Exception ex) {
	    ex.printStackTrace();
	}
	}
	
}
