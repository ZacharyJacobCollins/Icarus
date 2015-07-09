package Objects;

import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Algorithm.Converter;
import Algorithm.PartsOfSpeech;
import Models.Interrogative;
import Models.Model;


public class Sentence {

	public String sentence;
	public String transposition = "";
	public String transposedFocus = "";
	public ArrayList<Word> words = new ArrayList<Word>();
	public ArrayList<String> tokens = new ArrayList<String>();
	public ArrayList<String> organizations = new ArrayList<String>();
	public ArrayList<String> names = new ArrayList<String>();
	public String sentiment;
	public Vector<PartOfSpeech> pos = new Vector<PartOfSpeech>();
	public Vector<Dependency> syntax = new Vector<Dependency>();
	public Vector<PartOfSpeech> verbs = new Vector<PartOfSpeech>();
	public Vector<PartOfSpeech> nouns = new Vector<PartOfSpeech>();
	public Vector<PartOfSpeech> adjectives = new Vector<PartOfSpeech>();
	public Vector<PartOfSpeech> pronouns = new Vector<PartOfSpeech>();
	public String posPattern="";
	public String subject;
	public String verb;
	public String object;
	public String adj;
	public String type; //Declarative, Interrogative, Exclamatory, Imperative
	public Vector<Sentence> responses = new Vector<Sentence>();
	public String focus; //You, him, her, me, I, them, they, one, we, things, it, that. 
	public String[] elements;
	public String questionType;
	public Dependency negation;
	public Dependency possession;
	public Dependency nominalSubject;
	
	public Sentence(String response) {
		this.sentence = response;
		this.elements = response.split(" ");
	}
	
	public void createWords(){
		for(int i = 0;i<elements.length;i++){
			Word w = new Word(elements[i].toLowerCase());
			w.pos = pos.get(i);
			w.syntax = syntax.get(i); //DEBUG THIS. The roots have a tendency to come first. :/
			w.transposition = w.getTransposition(elements[i]);
			w.sentiment = w.getSentiment(); //Empty method
			//Empty Wordnet methods below. Use RiTaWN or JWNL for the lookups.
			w.hypernyms = w.getHypernyms();
			w.holonyms = w.getHolonyms();
			w.synonyms = w.getSynonyms();
			w.antonyms = w.getAntonyms();
			w.questionType = w.setQuestionType();
			words.add(w);
		}
	}
	
	public void generatePOSPattern(){
		for(PartOfSpeech p: pos){
			posPattern.concat(p.pos).concat(" ");
		}
		System.out.println("[INFO] The sentence's POS Pattern is: "+posPattern);
	}
	
	public void addResponse(String s) throws Throwable{
		Sentence r = new Sentence(s);
		PartsOfSpeech.tag(s, r);
		Converter.generatePOS(r);
		responses.add(r);
	}
	
	private void printData() {
		System.out.println("Dependencies :"+syntax.size());
		System.out.println("Verbs "+verbs.size());
		System.out.println("Nouns "+nouns.size());
		System.out.println("Tokens "+tokens.size());		
	}
	
	public void setFocus(){
		
		Pattern verbPattern = Pattern.compile("(prp)|(prp\\$)");
		for(PartOfSpeech pos:pos){
			//System.out.println("[Debug] Root: "+pos.root +" and Part: "+ pos.pos);
			Matcher pronounMatcher = verbPattern.matcher(pos.pos.toLowerCase());
			if(pronounMatcher.matches()){
				pronouns.add(pos);
				focus = pos.root;
				System.out.println("[ICARUS] Detected the sentence focus: "+focus);
				if(focus.matches("I") || focus.matches("me") || focus.matches("my")){
					transposedFocus = "YOU";
					System.out.println("[ICARUS] So this is all about "+transposedFocus+"?");
				}
				else if(focus.toLowerCase().matches("your") || focus.toLowerCase().matches("you")){
					transposedFocus = "ME";
					System.out.println("[ICARUS] So this is all about "+transposedFocus+"?");
					
				}
				else if(focus.toLowerCase().matches("him") || focus.toLowerCase().matches("his")){
					System.out.println("[ICARUS] So this is all about a GUY?");
				}
				else if(focus.toLowerCase().matches("her") || focus.toLowerCase().matches("hers")){
					System.out.println("[ICARUS] So this is all about a GIRL?");
				}
				else if(focus.toLowerCase().matches("this") || focus.toLowerCase().matches("that") || focus.toLowerCase().matches("it")){
					System.out.println("[ICARUS] So this is all about IT/THIS/THAT?");
				}
				else if(focus.toLowerCase().matches("them") || focus.toLowerCase().matches("they")){
					System.out.println("[ICARUS] So this is all about OTHERS?");
				}
				else if(focus.toLowerCase().matches("we") || focus.toLowerCase().matches("us")){
					System.out.println("[ICARUS] So this is all about the GROUP?");
				}
			}
		}
	}
	
	public void transpose(){
		for(int i = 0; i<elements.length;i++){
			if(elements[i].matches("I")){
				transposition = transposition.concat("you ");
			}
			else if(elements[i].toLowerCase().matches("my")){
				transposition = transposition.concat("your ");
			}
			else if(elements[i].toLowerCase().matches("your")){
				transposition = transposition.concat("my ");
			}
			else if(elements[i].toLowerCase().matches("you")){
				transposition = transposition.concat("I ");
			}
			else if(elements[i].toLowerCase().matches("was") && focus.matches("I")){
				transposition = transposition.concat("were ");
			}
			else if(elements[i].toLowerCase().matches("am")){
				transposition = transposition.concat("are ");
			}
			else if(elements[i].toLowerCase().matches("are") && !(isCopula("are"))){
				transposition = transposition.concat("am ");
			}
			else{
				transposition = transposition.concat(elements[i].toLowerCase()+" ");
			}
		}
		System.out.println("[ICARUS] So, "+transposition);
	}
	
	public boolean isCopula(String dep){
		for(Dependency d : syntax){
			if(d.dependent.matches(dep) && (d.type.matches("cop"))){
				return true;
			}
		}
		return false;
	}
	
	public void detectNegation(){
		for(Dependency d : syntax){
			if(d.type.matches("neg")){
				negation = d;
				System.out.println("[INFO] Negation detected: "+negation.dependent);
			}
		}
	}
	
	public void detectPossession(){
		for(Dependency d : syntax){
			if(d.type.matches("poss")){
				possession = d;
				System.out.println("[INFO] Possession detected: "+possession.governor);
				System.out.println("[ICARUS] So, the "+possession.governor+" belongs to "+transposedFocus+"?");
			}
		}		
	}
	
	public void detectSubject(){
		for(Dependency d : syntax){
			if(d.type.matches("nsubj")){
				nominalSubject = d;
				System.out.println("[INFO] Subject detected: "+nominalSubject.dependent);
			}
		}		
	}
	
	public int numericalSentiment(){
		int sentimentInt = 0;
		if(sentiment.matches("very positive")){
			sentimentInt += 2;
		}
		else if(sentiment.matches("positive")){
			sentimentInt += 1;
		}
		else if(sentiment.matches("neutral")){
			sentimentInt += 0;
		}
		else if(sentiment.matches("negative")){
			sentimentInt += -1;
		}
		else if(sentiment.matches("very negative")){
			sentimentInt += -2;
		}
		return sentimentInt;
	}
	
	
	public void printDependencies(){
		for(Dependency d1: syntax){
			System.out.println(d1.toString());
		}
	}
}


