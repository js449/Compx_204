

import java.net.*;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Reverse {

	public static void main(String[] args) {
		String hostAdd =""; //get an ip address
		try {
			if(args.length != 0){
				for(int i = 0; i<args.length; i++){
					hostAdd = args[i];
					InetAddress IP =  InetAddress.getByName(hostAdd); 
					if(hostAdd.equals(IP.getHostName())) {
						System.err.println(hostAdd + " : no name");
					}
					else {
						System.out.println(hostAdd + " : " + IP.getHostName()); //return Host address
					}
				}
			}
				
			else{
				System.out.println("Usage: Resolve <name1> <name2> ... <nameN>");
			}
				
		} catch(UnknownHostException e) {
			System.err.println(hostAdd + " : unknown host");
		}
		}
	}


