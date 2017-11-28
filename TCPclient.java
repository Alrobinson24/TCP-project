//**
//author: ajrobinson0
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.net.Socket;




public class TCPclient {

	public static void main(String[] args) throws Exception {
		// create a string method the provides the IPaddress and the for the UTF format
		String IPaddress = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the server IPaddress:");
		IPaddress = sc.nextLine();
		
		//create the socket for the port number which is 1900
		Socket s = new Socket(IPaddress,1900);
		DataInputStream dinput = new DataInputStream(s.getInputStream());
		DataOutputStream doutput = new DataOutputStream(s.getOutputStream());
		
		//The bufferedreader class explains the text from the input and output stream
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Send ready to begin..");
		String str = "", filename = "";
		
		// create a try and catch create while loop for the UTF
		try{
			while(!str.equals("begin"))
			str = br.readLine();
			doutput.writeUTF(str);
			doutput.flush();
			
			filename = dinput.readUTF();
			System.out.println("Recevied the files:"+filename);
			
			filename = "client" +filename;
			System.out.println("Saved files as:"+filename);
			// create a long method of 1024 to multiply the value of the file. If the file is greater than 1024 then an error will occur.
			long mb = Long.parseLong(dinput.readUTF());
			System.out.println("size of the files:"+(mb/(1024*1024))+ "mb");
			
			// create a byte method for the value of the megabytes size of 1024
			// create a do/try catch method to receive the files from the server
			byte b[] = new byte [1024];
			System.out.println("Ready to recevie..");
			FileOutputStream fos=new FileOutputStream(new File(filename), true);
			long bytesRead;
			
			do{
				bytesRead = dinput.read(b, 0, b.length);
				fos.write(b, 0, b.length);
				
			}while(!(bytesRead < 1024));
			System.out.println("sending files is finished:");
			fos.close();
			doutput.close();
			s.close();
			
			
		}catch(EOFException e) {
			
		}
		
	}

}
