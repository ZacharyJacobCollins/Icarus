package Algorithm;
import java.util.ArrayList;
import java.util.Vector;

import Objects.Sentence;

public class Tokenize {

	public static ArrayList<String> orgVector = new ArrayList<String>();
	public static ArrayList<String> personVector = new ArrayList<String>();
	public static ArrayList tokenElementsV = new ArrayList();
	public static Vector sentenceItems = new Vector();
	public static Vector tempVector = new Vector();
	public static String[] tokenElements;
	public static String orgName = "";
	public static String people = "";
	
	public static void Setup(ArrayList<String> tokens, Sentence s) {
		
		//System.out.println("[LAUNCH] Tokenize.Setup()");
		
		for (int i = 0; i < tokens.size(); i++) {

			if (tokens.get(i).contains("[") && tokens.get(i).contains("]")) {
				tokens.get(i).replace("[", "");
				tokens.get(i).replace("]", "");
			}

			tokenElements = tokens.get(i).split(" ");			
			tokenElementsV = new ArrayList();
			for (int k = 0; k < tokenElements.length; k++) {
				tokenElementsV.add(tokenElements[k]);
			}
			NER(tokenElementsV);
		}
		s.tokens = tokenElementsV;
		setType(s);
		//System.out.println("[END] Tokenize.Setup()");
		organizationRemarks(s);
		personRemarks(s);
	}

	public static void NER(ArrayList<String> tokenElementsV2) {
		//System.out.println("[LAUNCH] Tokenize.NER()");
		
		for (int k = 0; k < tokenElementsV2.size(); k++) {
			
			if (tokenElementsV2.get(k).contains("NamedEntityTag=PERSON")) {
				// Search for the name of the person now
				String[] temp = tokenElementsV2.get(0).split("=");
				//For fun, you can do a quick web-search of the name 
				personVector.add(temp[1]);
			}
			
			if (tokenElementsV2.get(k).contains("NamedEntityTag=ORGANIZATION")) {
				System.out.println("[ICARUS] I found an organization!");
				// Search for the name of the organization
				String[] temp = tokenElementsV2.get(0).split("=");
				orgVector.add(temp[1]);
			}
		}
		//System.out.println("[END] Tokenize.NER()");
	}

	public static void organizationRemarks(Sentence s) {
		if (!orgVector.isEmpty()) {
			for (int j = 0; j < orgVector.size(); j++) {
				orgName = orgName.concat((String) orgVector.get(j).toString()).concat(" ");
			}
			System.out.println("[ICARUS] So, I bet that organization is called: "+ orgName);
			System.out.println(Questioner.Organizations(orgName));
			s.organizations.add(orgName);
		}
	}

	public static void personRemarks(Sentence s) {
		if (!personVector.isEmpty()) {
			if (personVector.size() == 1) {
				for (int j = 0; j < personVector.size(); j++) {
					people = people.concat((String) personVector.get(j).toString());
				}
				System.out.println("[ICARUS] The person I found was: " + people + ".");
				System.out.println(Questioner.People(people));
			} else if (personVector.size() > 1) {
				for (int j = 0; j < personVector.size() - 1; j++) {
					people = people.concat((String) personVector.get(j).toString()).concat(", ");
					s.names.add(people);
				}
				people = people.concat("and ").concat((String) personVector.get(personVector.size()).toString());
				System.out.println("[ICARUS] The people I found were: " + people + ".");
				System.out.println(Questioner.People(people));
			}
		}
	}
	
	public static void setType(Sentence s){
		
		if(isImperative(s)){
			s.type = "imperative";
		}
		else if(isExclamatory(s)){
			s.type = "exclamatory";
		}
		else if(isInterrogative(s)){
			s.type = "interrogative";
		}
		else{
			s.type = "declarative";
		}
		System.out.println("[DEBUG] This sentence is type: "+s.type+".");
	}

	private static boolean isInterrogative(Sentence s) {
		//If the final punctuation is a question mark
		//System.out.println(s.pos.get(s.pos.size()-1).root);
		if(s.pos.get(s.pos.size()-1).root.matches("\\?")) return true;
		else return false;
	}

	private static boolean isImperative(Sentence s) {
		//If the first word is a Verb base form or VB Present tense
		if(s.pos.get(0).pos.matches("VB") || s.pos.get(0).pos.matches("VBP") ) return true;
		else return false;
	}

	private static boolean isExclamatory(Sentence s) {
		// If the final punctuation is an exclamation mark
		//System.out.println(s.pos.get(s.pos.size()-1).root);
		if(s.pos.get(s.pos.size()-1).root.matches("\\!")) return true;
		else return false;
	}
}
