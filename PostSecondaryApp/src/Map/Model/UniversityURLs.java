package Map.Model;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URI;

//Classes to save all the links of university in alphabetic order
public class UniversityURLs {

	// Arrays of URL object
	public static final URI[] uniLinks = new URI[20];

	public static void setUpUniversityLink() {

		// surrounded with try and catch since the string might not be successfully
		// convert to URL Object
		try {
			uniLinks[0] = new URI("https://www.algomau.ca");
			uniLinks[1] = new URI("https://www.brocku.ca");
			uniLinks[2] = new URI("https://www.carleton.ca");
			uniLinks[3] = new URI("https://www.lakeheadu.ca");
			uniLinks[4] = new URI("https://www.laurentian.ca");
			uniLinks[5] = new URI("https://www.mcmaster.ca");
			uniLinks[6] = new URI("https://www.nipissingu.ca");
			uniLinks[7] = new URI("https://www.ontariotechu.ca");
			uniLinks[8] = new URI("https://www.queensu.ca");
			uniLinks[9] = new URI("https://www.rmc-cmr.ca");
			uniLinks[10] = new URI("https://www.torontomu.ca");
			uniLinks[11] = new URI("https://www.trentu.ca");
			uniLinks[12] = new URI("https://www.uoguelph.ca");
			uniLinks[13] = new URI("https://www.uottawa.ca");
			uniLinks[14] = new URI("https://www.utoronto.ca");
			uniLinks[15] = new URI("https://www.uwaterloo.ca");
			uniLinks[16] = new URI("https://www.uwindsor.ca");
			uniLinks[17] = new URI("https://www.uwo.ca");
			uniLinks[18] = new URI("https://www.wlu.ca");
			uniLinks[19] = new URI("https://www.yorku.ca");

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}