package Map.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Level 2 Nested Class
public class ProvidedLocation {
	
	//Attributes I
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocations(String location) {
		this.location = location;
	}
	
	public String toString() {
		return location;
	}

}
