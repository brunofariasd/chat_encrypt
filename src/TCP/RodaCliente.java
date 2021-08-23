package TCP;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import controllers.ConfigDefault;

public class RodaCliente {
	
	String msg;
	int defaultEncryption;
	String defaultKey;
	Scanner sc = new Scanner(System.in);
	 
	ClienteRunnable c;
	public RodaCliente() throws UnknownHostException, IOException {
		Thread escutarServidor =new Thread(new Runnable() {
			@Override
			public void run() {
				Socket socket;
				try {
					socket = new Socket("127.0.0.1", 12345);
					c = new ClienteRunnable(socket);
					Thread t = new Thread(c);
					t.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
		escutarServidor.start();
		
		System.out.println("Chat Iniciado...");
		while(true) {
			
			System.out.print(">> ");
			msg = sc.nextLine();
			c.enviarMensagem(ConfigDefault.encryptMessage(msg));
		}
		/*
		 * HERE
		 */
	}
	
	public static void main(String args[]) throws UnknownHostException, IOException {
		new RodaCliente(); 
	}
		
}