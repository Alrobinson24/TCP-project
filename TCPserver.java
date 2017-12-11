import java.io.*;
import java.net.*;

public class TCPserver {

	public static void main(String[] args) throws IOException {
//Use the serverSocket to implement the connection between the client and put the file in the socket class
		ServerSocket ss = new ServerSocket(1900);
		Socket s = ss.accept();
//Define the file as File.txt in the variable to be called transferFile and use the array method to find the data
		System.out.println("Waiting for connection:" + socket);
		File f = new File("File.txt");
		byte [] bytearray  = new byte [(int)transferFile.length()];
//The fin will be use to read the transferFile and the data would read the bytearray from the bin thats read the file 
		FileInputStream fin = new FileInputStream(f);
		BufferedInputStream bin = new BufferedInputStream(fin);
		bin.read(bytearray,0,bytearray.length);
// Define the outputstream to communicate with the client
		OutputStream os = socket.getOutputStream();
	    System.out.println("Sending the Files...");
	    os.write(bytearray,0,bytearray.length);
	    os.flush();
		socket.close();
		System.out.println("Transfering the file is complete");
		
					
	}

}
