package Map.Model;

//Level 1 Nested Class
public class Options {

	//attributes
	private int maxResults;
	private boolean ignoreLatLngInput;

	//getters and setters
	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
	public boolean isIgnoreLatLngInput() {
		return ignoreLatLngInput;
	}
	public void setIgnoreLatLngInput(boolean ignoreLatLngInput) {
		this.ignoreLatLngInput = ignoreLatLngInput;
	}

}
