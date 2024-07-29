package personalprogram.model;

import java.util.UUID;

public class User {
	private UUID id;
	private String username;
	
	public User(UUID id, String username) {
		this.id = id;
		this.username = username;
		//System.out.println(id);
		
	}

	
	//We want to give a table for all user by each unique user
	public String getUsername() {
		return username;
	}
	public UUID getUUID() {
		//System.out.println("testing"+id);
		return id;
	}
}
