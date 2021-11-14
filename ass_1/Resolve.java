

import java.net.*;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Resolve {

	public static void main(String[] args) {
	
		String hostName =""; //get a host name
		try {
			if(args.length != 0){
			for(int i = 0; i<args.length; i++){
				hostName = args[i];
				InetAddress IP =  InetAddress.getByName(hostName); //Resolve address with DNS name
			
				if(hostName.equals(IP.getHostAddress())) {
				System.err.println(hostName + " : Invalid name");
				}
				else {

					System.out.println(hostName + " : " + IP.getHostAddress()); //return ip address of the host
				}
			}
			}
			else{
				System.out.println("Usage: Resolve <name1> <name2> ... <nameN>");
			}
			
		} catch(UnknownHostException e) {
			System.err.println(hostName + " : unknown host");
		}
		}
		

	}



