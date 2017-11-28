//**
//author: ajrobinson0
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPserver {

	public static void main(String[] args) throws Exception {
		// create a string method the provides the filename the server is sending to the client
		String filename;
		System.out.println("Enter the name of the Files:");
		Scanner sc = new Scanner(System.in);
		filename = sc.nextLine();
		sc.close();
		
		//create a loop method thats executes the ServerSocket that response to the port number (1900)
		//print the dataprams of the input and output stream for the Serversocket's and socket's variables.
		while(true) {
			ServerSocket ss = new ServerSocket(1900);
			System.out.println("Please wait processing the request");
			Socket s = ss.accept();
			System.out.println("Connnecting with"+ s.getInetAddress().toString());
			DataInputStream dinput = new DataInputStream(s.getInputStream());
			DataOutputStream doutput = new DataOutputStream(s.getOutputStream());
			
			//create a loop method that returns the string of 'str' that reads the input for the UTF
			try {
				String str = "";
				str = dinput.readUTF();
				System.out.println("Sending Files...");
				
				if(!str.equals("stop")) {
					System.out.println("Sending the files:" +filename);
					doutput.writeUTF(filename);
					doutput.flush();
					
					File f = new File(filename);
					FileInputStream fin = new FileInputStream(f);
					
					//create variables to read the long the method of 'mb' variable
					long mb = (int) f.length();
					byte b[] = new byte [1024];
					int read;
					
					//write the UTF format for the output that returns the string and variable 
					doutput.writeUTF(Long.toString(mb));
					doutput.flush();
					System.out.println("Size:" +mb);
					System.out.println("Buffer size:" + ss.getReceiveBufferSize());
					
					// Use the loop method variable read the Dataoutput stream to write the data information in the file
					while((read = fin.read(b)) != -1) {
						doutput.write(b, 0, read);
						doutput.flush();
						
						
					}
					fin.close();
					System.out.println("ready..");
					doutput.flush();
					
				}
				doutput.writeUTF("STOP");
				System.out.println("Sending the files is now complete");
				doutput.flush();
				
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("There is an error");
			}
			dinput.close();
			s.close();
			ss.close();
		}
	}

}
