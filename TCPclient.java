import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.net.Socket;




public class TCPclient {

	public static void main(String[] args) throws Exception {
		
		String IPaddress = " ";
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the server IPaddress:");
		IPaddress = sc.nextLine();
		
		Socket s = new Socket(IPaddress,1900);
		DataInputStream dinput = new DataInputStream(s.getInputStream());
		DataOutputStream doutput = new DataOutputStream(s.getOutputStream());
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Send ready to begin:");
		String str = " ", filename = " ";
		
		try{
			while(!str.equals("start"))
			str = br.readLine();
			doutput.writeUTF(str);
			doutput.flush();
			
			filename = dinput.readUTF();
			System.out.println("Recevied the files:"+filename);
			
			filename = "client" +filename;
			System.out.println("Saved files as:"+filename);
			
			long ma = Long.parseLong(dinput.readUTF());
			System.out.println("size of the files:"+(ma/(1024*1024))+ "mb");
			
			byte b[] = new byte [1024];
			System.out.println("Ready to recevie the files:");
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
