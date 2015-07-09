package Objects;

import java.util.ArrayList;

public class Word {
	
	public String orginal;
	public String transposition;
	public Dependency syntax;
	public PartOfSpeech pos;
	public ArrayList<String> hypernyms = new ArrayList<String>();
	public ArrayList<String> holonyms = new ArrayList<String>();
	public ArrayList<String> synonyms = new ArrayList<String>();
	public ArrayList<String> antonyms = new ArrayList<String>();
	public String sentiment;
	public String questionType;
	
	
	public Word(String w){
		this.orginal = w;
	}


	public String getTransposition(String string) {
		// TODO Auto-generated method stub
		return null;
	}


	public String getSentiment() {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<String> getHypernyms() {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<String> getHolonyms() {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<String> getSynonyms() {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<String> getAntonyms() {
		// TODO Auto-generated method stub
		return null;
	}


	public String setQuestionType() {
		// TODO Auto-generated method stub
		return null;
		
	}

}
