package TCP;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import controllers.ConfigDefault;

public class Servidor implements Runnable{
	
	public Socket client;
	static String [] defaultEncryption = ConfigDefault.defaultEncrypt();
	public static ArrayList<Socket> clientsConecteds = new ArrayList<Socket>();
		
	public Servidor(Socket cliente) throws IOException{
		this.client = cliente;	
		Servidor.clientsConecteds.add(cliente);
	}
	
	public void run(){
		try {
			
			Scanner s = null;
			s = new Scanner(this.client.getInputStream());
			String rcv;
			String response;
			
			while(s.hasNextLine()){
				rcv = s.nextLine();
				response = ConfigDefault.decryptMessage(rcv);
				System.out.println("Texto encriptado >> "+ rcv);
				
				enviarMensagemBroadcast(response);
				
				System.out.println("Texto decriptado >> "+ response);
				
				//RN
			}
			//Closed scanner e socket
			s.close();
			this.client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	 public void enviarMensagem(String mensagem) throws IOException{
			PrintStream saida = new PrintStream(client.getOutputStream());
			saida.println(ConfigDefault.encryptMessage(mensagem));
	 }
 
	 public static void enviarMensagemBroadcast(String mensagem) throws IOException {
		for (int counter = 0; counter < clientsConecteds.size(); counter++) {
			try{
				PrintStream saida = new PrintStream(clientsConecteds.get(counter).getOutputStream());
				saida.println(ConfigDefault.encryptMessage(mensagem));
			} catch(Exception ex){
			    /*client.remove*/
				clientsConecteds.remove(counter);
				System.out.println(ex.getMessage());
			}
		}
	 } 
}