package Map.Model;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Parsing Json Objects
public class GeocodeResponse {

	// field names has to be the exact same as the properties in the json file, not
	// the Object name match
	private Infos info;
	private Options options;
	private Results[] results;
	
	//getters and setters
	public Infos getInfo() {
		return info;
	}
	public void setInfo(Infos info) {
		this.info = info;
	}
	public Options getOptions() {
		return options;
	}
	public void setOptions(Options options) {
		this.options = options;
	}
	public Results[] getResults() {
		return results;
	}
	public void setResults(Results[] results) {
		this.results = results;
	}
	

}

