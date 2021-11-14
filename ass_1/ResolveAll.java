

import java.net.*;
import java.util.Scanner;

public class ResolveAll {

	public static void main(String[] args) {
		
		String hostName =""; //Get a string host name
		try {
			if(args.length != 0){
			for(int i = 0; i<args.length; i++){
				hostName = args[i];
				InetAddress[] IP =  InetAddress.getAllByName(hostName); //resolve all Ip addresses with dns name
				
				for (int j = 0; j < IP.length; j++) 
					 {			
						 System.out.println(hostName + " : " + IP[j].getHostAddress()); 
					 }
			}	
			}else{
				System.out.println("Usage: Resolve <name1> <name2> ... <nameN>");
			}
		}
			catch(UnknownHostException e) {
				System.err.println(hostName + " : unknown host");
			}
		
		
	
}
}
	


