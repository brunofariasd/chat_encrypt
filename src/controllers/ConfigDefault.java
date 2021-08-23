package controllers;

import Models.Encrypt;
import controllers.Encript;

public class ConfigDefault {
	
	static public String addDefaultEncrypt(int type, String key) {
		
		Encrypt encrypter = new Encrypt(type, key);
		json.JSONObject jO = new json.JSONObject();

		Archiver.gravarArchiver("src/data/Encrypt.txt", jO.put("Encrypt", encrypter.toJson()));
		
		return "File created successfully";
	}
	
	public static String [] defaultEncrypt() {
		
		String [] response = {};
		String textoCompleto = Archiver.lerArchiver("src/data/Encrypt.txt");
		
		json.JSONObject jO = new json.JSONObject(textoCompleto);		
		Encrypt obj = (Encrypt) jO.get("Encrypt");
		
		response[0] = Integer.toString(obj.getType());
		response[1] = obj.getKey();
		
		return response;
	}
	
	public static String encryptMessage(String request) {
		
		String response;
		String textoCompleto = Archiver.lerArchiver("src/data/Encrypt.txt");
		
		json.JSONObject jO = new json.JSONObject(textoCompleto);		
		Encrypt obj = (Encrypt) jO.get("Encrypt");
		
		
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
		Encrypt obj = (Encrypt) jO.get("Encrypt");
		
		
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
