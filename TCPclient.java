import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPclient {

	public static void main(String[] args) throws IOException {
// Define the variables bytesread which contain the current status of the bytes from the inputstream and the currentTot which reads the number of the btyes
	    int filesize=1022386; 
	    int bytesRead;
	    int currentTot = 0; 
//Define the socket that connects to the IPaddress to the port number which is 1900 
	    Socket socket = new Socket("127.0.0.1",1900);
	    byte [] bytearray  = new byte [filesize];
	    InputStream ie = socket.getInputStream(); 
//This method fos will locate the file to copied from the server and bos prints the output file at btye array 
	    FileOutputStream fos = new FileOutputStream("copy.doc"); 
	    BufferedOutputStream bos = new BufferedOutputStream(fos); 
	    bytesRead = ie.read(bytearray,0,bytearray.length); 
	    currentTot = bytesRead;
//Implement the do/while loop to print the input stream and determine ifthe bytesRead are greater than or equal to zero then it will update the current total of the bytes.
// If the ByteRead is -1 then no data is left on the input stream and the loop exits	    
	    do{
	    	bytesRead = ie.read(bytearray, currentTot, (bytearray.length-currentTot));
	    	if(bytesRead >= 0) currentTot += bytesRead;
	    }while(bytesRead > -1);
	    
	    bos.write(bytearray, 0 , currentTot);
	    bos.flush();
	    bos.close();
	    socket.close();

	  
		

	}

}
