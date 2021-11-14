import javax.net.ssl.*;  //Classes for using SSL sockets
import java.security.KeyStore; //Classes for loading keys for authentication and encryption
import java.security.cert.X509Certificate; //Holds a Certificate in X.509 format
import javax.naming.ldap.*; //Classes for parsing X.509 certificate
import javax.net.*; //Classes for “Socket Factories”
import javax.net.ssl.SSLServerSocketFactory; //to create SSLServerSocket objects
import javax.net.ssl.SSLSocketFactory; //to create SSLSocket objects
import javax.naming.ldap.*;
import java.net.SocketTimeoutException;
import java.io.*;
import java.net.*;

class MyTLSFileClient {
	public static void main(String args[]) {
		try {
			InputStream in = null;
			OutputStream out = null;
			
	
			String hostName = (args[0]);
			int port = Integer.parseInt(args[1]);
			String fileName = (args[2]);

			SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
			SSLSocket ssocket =(SSLSocket)factory.createSocket(hostName, port);
			System.out.println("1");	

			SSLParameters params = new SSLParameters();
			params.setEndpointIdentificationAlgorithm("HTTPS");
			ssocket.setSSLParameters(params);
			System.out.println("2");


			ssocket.startHandshake();
			System.out.println("3");

			SSLSession sesh = ssocket.getSession();
			X509Certificate cert = (X509Certificate)sesh.getPeerCertificates()[0];
			displayCert(cert);
			System.out.println("4");

			in = ssocket.getInputStream();
			out = ssocket.getOutputStream();
			System.out.println("5");

			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ssocket.getOutputStream()));
			writer.write(fileName + "\n");
			writer.flush();
			System.out.println("6");

			
			BufferedReader reader = new BufferedReader(new InputStreamReader(ssocket.getInputStream())); 
			BufferedOutputStream Out1 = new BufferedOutputStream(new FileOutputStream("_" + fileName));
			System.out.println("7");

			byte[] bytearray = new byte[1024];
			BufferedInputStream buffIn = new BufferedInputStream(ssocket.getInputStream());
			System.out.println("8");

			int readfile = buffIn.read(bytearray);
			while (readfile != -1){
				Out1.write(bytearray, 0,readfile);
				readfile = buffIn.read(bytearray);
				System.out.println("9");
			}
			Out1.flush();
			buffIn.close();
		

		}
		catch(Exception e) {
		    System.err.println("Exception: " + e);
		}
	}
	


	public static void displayCert(X509Certificate cert){
		try{
			String name = cert.getSubjectX500Principal().getName();
			LdapName ln = new LdapName(name);
			for(Rdn rdn : ln.getRdns())
			System.out.println(rdn.getValue().toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
    	}	
}
