import javax.net.ssl.*;  //Classes for using SSL sockets
import java.security.KeyStore; //Classes for loading keys for authentication and encryption
import java.security.cert.X509Certificate; //Holds a Certificate in X.509 format
import javax.naming.ldap.*; //Classes for parsing X.509 certificate
import javax.net.*; //Classes for “Socket Factories”
import javax.net.ssl.SSLServerSocketFactory; //to create SSLServerSocket objects
import javax.net.ssl.SSLSocketFactory; //to create SSLSocket objects
import java.net.*;
import java.io.*;

class MyTLSFileServer {
	public static void main(String args[]) {
	try {
			KeyStore ks = KeyStore.getInstance("JKS");
			char[] passphrase = "jsingh449".toCharArray();
			ks.load(new FileInputStream("server.jks"), passphrase);



			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(ks, passphrase);
			System.out.println("1");
			
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(kmf.getKeyManagers(), null, null);
			System.out.println("2");


			int port = 40202;
			ServerSocketFactory ssf = ctx.getServerSocketFactory();
			SSLServerSocket sserverSocket =(SSLServerSocket)ssf.createServerSocket(port);
			String EnabledProtocols[] ={"TLSv1.2", "TLSv1.1"};
			sserverSocket.setEnabledProtocols(EnabledProtocols);
			System.out.println("3");

			SSLSocket sclient = (SSLSocket)sserverSocket.accept();//blocking
			System.out.println("4");

			BufferedReader reader = new BufferedReader(new InputStreamReader(sclient.getInputStream()));
			String line = reader.readLine();
			System.out.println(line);
			System.out.println("5");
			
			
			byte[] bytearray = new byte[1024];
			FileInputStream file = new FileInputStream(line);	
			BufferedOutputStream In = new BufferedOutputStream(sclient.getOutputStream());
			System.out.println("6");

			int readfile = file.read(bytearray);//read the file
			while (readfile != -1){
				In.write(bytearray, 0,readfile);//Accept the output bytes
				readfile = file.read(bytearray);
				System.out.println("7");
			}
			In.flush();//Send the output to the sink
			In.close();//Close the input file
		}
		catch(Exception ex) {
		    ex.printStackTrace();
		}
	}
	
}
