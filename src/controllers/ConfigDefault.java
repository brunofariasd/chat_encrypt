package controllers;

import Models.Encrypt;

public class ConfigDefault {
	
	private static String [] responses;

	static public String addDefaultEncrypt(int type, String key) {
		
		Encrypt encrypter = new Encrypt(type, key);
		json.JSONObject jO = new json.JSONObject();

		Archiver.gravarArchiver("src/data/Encrypt.txt", jO.put("Encrypt", encrypter.toJson()));
		
		return "File created successfully";
	}
	
	public static String [] defaultEncrypt() {
		
		responses = new String [2];
		String textoCompleto = Archiver.lerArchiver("src/data/Encrypt.txt");
			
		Encrypt obj = new Encrypt(new json.JSONObject(textoCompleto));
				
		responses[0] = Integer.toString(obj.getType());
		responses[1] = obj.getKey();
		
		return responses;
	}
	
	public static String encryptMessage(String request) {
		
		String response;
		String textoCompleto = Archiver.lerArchiver("src/data/Encrypt.txt");
		
		json.JSONObject jO = new json.JSONObject(textoCompleto);		
		Encrypt obj = new Encrypt(jO);
		
		
		switch (obj.getType()){
	        case 1:
	        	response = Encript.encriptarCifraCesar(Integer.parseInt(obj.getKey()), request);
	            break;
	        case 2:
	        	response = Encript.codificarCifraVigenere(obj.getKey(), request);
	            break;
	        case 3:
	        	response = Encript.encriptarCifraCesar(Integer.parseInt(obj.getKey()), request);
	        default:
	        	response = "Invalid default settings";
	        	break;

		}
		
		return response;
	}
	
	public static String decryptMessage(String request) {
		
		String response;
		String textoCompleto = Archiver.lerArchiver("src/data/Encrypt.txt");
		
		json.JSONObject jO = new json.JSONObject(textoCompleto);		
		Encrypt obj = new Encrypt(jO);
		
		
		switch (obj.getType()){
	        case 1:
	        	response = Encript.decriptarCifraCesar(Integer.parseInt(obj.getKey()), request);
	            break;
	        case 2:
	        	response = Encript.decodificarCifraVigenere(obj.getKey(), request);
	            break;
	        case 3:
	        	response = Encript.decriptarCifraCesar(Integer.parseInt(obj.getKey()), request);
	        default:
	        	response = "Invalid default settings";
	        	break;

		}
		
		return response;
	}

}
