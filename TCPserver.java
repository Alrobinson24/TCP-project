import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPserver {

	public static void main(String[] args) throws Exception {
		
		String filename;
		System.out.println("Enter the name of the Files:");
		Scanner sc = new Scanner(System.in);
		filename = sc.nextLine();
		sc.close();
		
		while(true) {
			ServerSocket ss = new ServerSocket(1900);
			System.out.println("Please wait processing the request:");
			Socket s = ss.accept();
			System.out.println("Connnect with"+ s.getInetAddress().toString());
			DataInputStream dinput = new DataInputStream(s.getInputStream());
			DataOutputStream doutput = new DataOutputStream(s.getOutputStream());
			
			try {
				String str = " ";
				str = dinput.readUTF();
				System.out.println("Files are sent:");
				
				if(!str.equals("stop")) {
					System.out.println("Sending the files:" +filename);
					doutput.writeUTF(filename);
					doutput.flush();
					
					File f = new File(filename);
					FileInputStream fin = new FileInputStream(f);
					
					long ma = (int) f.length();
					byte b[] = new byte [1024];
					int read;
					
					doutput.writeUTF(Long.toString(ma));
					doutput.flush();
					System.out.println("Size:" +ma);
					System.out.println("Buffer size:" + ss.getReceiveBufferSize());
					

					while((read = fin.read(b)) != -1) {
						doutput.write(b, 0, read);
						doutput.flush();
						
						
					}
					fin.close();
					System.out.println("ready?:");
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
