package Map.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

//Level 1 Nested Class
public class Infos {

	// Attributes
	// important attributes to determine if the address is valid or not
	private long statuscode;
	private CopyRight copyright;
	private List<String> messages; // since we don't know the size of the array

	// getters and setters
	public long getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(long statuscode) {
		this.statuscode = statuscode;
	}

	public CopyRight getCopyright() {
		return copyright;
	}

	public void setCopyright(CopyRight copyright) {
		this.copyright = copyright;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}