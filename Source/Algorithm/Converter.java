package Algorithm;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Objects.PartOfSpeech;
import Objects.Sentence;


public class Converter {
	
	public static void generatePOS(Sentence s){
		//System.out.println("[LAUNCH] Converter.generatePOS()");
		
		Pattern verbPattern = Pattern.compile("(vb)|(vbd)|(vbg)|(vbn)|(vbp)|(vbz)");
		Pattern nounPattern = Pattern.compile("(prp)|(nn)|(nns)|(nnp)|(nnps)");
		Pattern adjPattern = Pattern.compile("(jj)|(jjr)|(jj)");
		boolean firstNoun=true;
		boolean secondNoun=true;
		boolean firstVerb=true;
		boolean firstAdj = true;
		
		for(PartOfSpeech pos:s.pos){
			Matcher verbMatcher = verbPattern.matcher(pos.pos.toLowerCase());
			if(verbMatcher.matches()){
				s.verbs.add(pos);
				if(firstVerb){
					//System.out.println("[INFO] Assigning the verb: "+pos.root);
					s.verb = pos.root;
					firstVerb = false;
				}
			}
		}
		
		for(PartOfSpeech pos:s.pos){
			Matcher nounMatcher = nounPattern.matcher(pos.pos.toLowerCase());
			if(nounMatcher.matches()){
				s.nouns.add(pos);
				if(firstNoun){
					//System.out.println("[INFO] Assigning the noun: "+pos.root);
					s.subject = pos.root;
					firstNoun = false;
				}
				else if(secondNoun){
					//System.out.println("[INFO] Assigning the object: "+pos.root);
					s.object = pos.root;
					secondNoun = false;
				}
			}
		}
		
		for(PartOfSpeech pos:s.pos){
			Matcher adjMatcher = adjPattern.matcher(pos.pos.toLowerCase());
			if(adjMatcher.matches()){
				s.adjectives.add(pos);
				if(firstAdj){
					//System.out.println("[INFO] Assigning the adjective: "+pos.root);
					s.adj = pos.root;
					firstAdj = false;
				}
			}
		}
		//System.out.println("[END] Converter.generatePOS()");
	}
}
