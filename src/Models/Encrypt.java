package Models;

import java.io.Serializable;

import json.JSONObject;

public class Encrypt implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int type;
	private String key;
	
	public Encrypt(int type, String key){	
		this.setType(type);
		this.setKey(key);
	}
	
	public Encrypt(JSONObject j) {
		this.type = j.getJSONObject("Encrypt").getInt("type");
		this.key = j.getJSONObject("Encrypt").getString("key");
	}
	
	public json.JSONObject toJson() {
		json.JSONObject json = new json.JSONObject();
		json.put("type", this.type);
		json.put("key", this.key);
		return json;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
}
