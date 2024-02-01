package Map.Model;

//Level 1 Nested Class
public class Results {

	// Attributes
	private ProvidedLocation providedLocation;
	private Locations[] locations;

	// getters and setters
	public ProvidedLocation getProvidedLocation() {
		return providedLocation;
	}

	public void setProvidedLocation(ProvidedLocation providedLocation) {
		this.providedLocation = providedLocation;
	}

	public Locations[] getLocations() {
		return locations;
	}

	public void setLocations(Locations[] locations) {
		this.locations = locations;
	}

}
