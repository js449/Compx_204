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

public class MyTLSFileClient {
	public static void main(String args[]) {
	try {
	
	String hostName = args[0];
	int port = Integer.parseInt(args[1]);
	String fileName = (args[2]);

	SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
	SSLSocket ssocket =(SSLSocket)factory.createSocket(hostName, port);
	System.out.println("HI jay");	

	SSLParameters params = new SSLParameters();
	params.setEndpointIdentificationAlgorithm("HTTPS");
	ssocket.setSSLParameters(params);

	//get the X509Certificate for this session 
	System.out.println("Hi man");
	ssocket.startHandshake();

	System.out.println("Hi man1");
	SSLSession sesh = ssocket.getSession();
	System.out.println("Hi man2");
	X509Certificate cert = (X509Certificate)sesh.getPeerCertificates()[0];
	System.out.println("Hello"); 
	displayCert(cert);

	//Get the input stream and output stream
          //  InputStream in = ssocket.getInputStream();
          //  OutputStream out = ssocket.getOutputStream();

	
	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ssocket.getOutputStream()));
	writer.write(fileName + "\n");
	writer.flush();

	//connecting to the socket's input stream
	//BufferedReader reader = new BufferedReader(new InputStreamReader(ssocket.getInputStream())); 
}
	catch(Exception e) {
	    System.err.println("Exception: " + e);
	}
	}
	
	 static void displayCert(X509Certificate cert)
	{
		System.out.println("HI");
	try{
	 //Distinguished Name: CN=shangrila.cms.waikato.ac.nz,OU=,O=University of Waikato,L=Hamilton,ST=Waikato,C=NZ
	String name = cert.getSubjectX500Principal().getName();
	LdapName ln = new LdapName(name);
	for(Rdn rdn : ln.getRdns())
	System.out.println(rdn.getValue().toString());
	}
	catch(Exception e) {
		    System.err.println("Exception: " + e);
		}
}	
}
