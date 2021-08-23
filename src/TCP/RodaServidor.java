package TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import controllers.ConfigDefault;

public class RodaServidor {

	private ServerSocket server;
	Scanner sc = new Scanner(System.in);

	public RodaServidor() throws IOException {

		server = new ServerSocket(12345);
		Thread acceptClients =new Thread(new Runnable() {
			
			@Override
			public void run() {

				while(true){
					System.out.println("\nAguardando conexão...");
					Socket con;
					try {
						con = server.accept();
						System.out.println("\nCliente conectado...");
						Thread t;
						t = new Thread(new Servidor(con));
						t.start();	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			              
				}
			}
		});
		acceptClients.start();
		
		System.out.println("#####################################Bem vindo ao chat do Brunao#####################################");
		System.out.println("Para continuar escolha o tipo de criptografia e chave a ser utilizada ");
		System.out.println("1 - Cifra de Cesar (Key numerica)");
		System.out.println("2 - Cifra de Vigere");
		System.out.println("3 - Cifra de Verman");
		System.out.print("\nEscolha sua opcao: ");
		int type = sc.nextInt();
		sc.nextLine();
		System.out.println("Digita a key da criptografia: ");
		String key = sc.nextLine();
		
		ConfigDefault.addDefaultEncrypt(type, key);
		
		System.out.println("\n## O chat será iniciado em Breve ##\n\n");
		
	}
	
	public static void main(String [] args) throws UnknownHostException, IOException {
		new RodaServidor();
	}
	
}

