package TCP;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import controllers.ConfigDefault;
import controllers.Encript;

public class ClienteRunnable implements Runnable{

	private Socket cliente;
	static String [] defaultEncryption = ConfigDefault.defaultEncrypt();
	public ClienteRunnable(Socket socket){
		this.cliente = socket;
	}

	public void run() {
		
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				Scanner s = null;
				try {	
					
					s = new Scanner(cliente.getInputStream());
					String response;
					
					while(s.hasNextLine()){
						response = s.nextLine();
	
						System.out.println(ConfigDefault.decryptMessage(response));									
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				s.close();
			}
		});
		
		t.start();	
	}

	public void enviarMensagem(String mensagem) throws IOException {
		PrintStream saida = new PrintStream(cliente.getOutputStream());
		saida.println(Encript.encriptarCifraCesar(3,mensagem));
	}
	
	public void encerrarConexao() throws IOException {
		PrintStream saida = new PrintStream(cliente.getOutputStream());	
		saida.println("fim");
		saida.close();
		cliente.close();
		
		System.out.println("Cliente finaliza conexão.");
	}

	 public static String [] msgSeparada(String msg) {
			
		String [] arrayString = msg.split(";");
		
		return arrayString;
	}
}
