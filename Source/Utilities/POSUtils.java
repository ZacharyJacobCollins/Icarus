package Utilities;

import java.util.Vector;

public class POSUtils {
	
	public static void ReCognizer(String[] taggedArray) {
		System.out.println("[LAUNCH] POSAnalysis.ReCognizer(String[] taggedArray)");
		Vector<String> POSVector = new Vector<String>();
		Vector<String> RootVector = new Vector<String>();
		Vector<String> POSVectorVector = new Vector<String>();
		Vector<String> RootVectorVector = new Vector<String>();

		for (int k = 0; k < taggedArray.length; k++) {
			if (taggedArray[k].contains(partsOfSpeech(taggedArray[k].toString()))) {
				Analysis(taggedArray[k]);
			} else {
				break;
			}
		}
		System.out.println("[END] POSAnalysis.ReCognizer(String[] taggedArray)");
	}

	public static String partsOfSpeech(String POS) {
		System.out.println("[LAUNCH] POSAnalysis.partsOfSpeech(String POS)");
		
		String[] POSArray = { "CC", "CD", "DT", "EX", "FW", "IN", "JJ", "JJR",
				"JJS", "LS", "MD", "NN", "NNP", "NNPS", "NNS", "PDT", "POS",
				"PRP", "PRP$", "RB", "RBR", "RBS", "RP", "SYM", "TO", "UH",
				"VB", "VBD", "VBG", "VBN", "VBP", "VBZ", "WDT", "WP", "WP$",
				"WRB" };
		boolean flag = true;

		while (flag = true) {
			System.out.println(POS);

			if (POS.contains("_")) {
				String[] temp = POS.split("_");
				POS = temp[1].toString();

				for (int i = 0; i < POSArray.length; i++) {
					if (POSArray[i].matches(POS)) {
						return POSArray[i];
					} else {
					}
				}flag = false; 
			}
			else break;
		}
		System.out.println("[END] POSAnalysis.partsOfSpeech(String POS)");
		return POS;
	}
	
	public static void Analysis(String POS) {
		System.out.println("[LAUNCH] POSAnalysis.Analysis(String POS)");
		String[] temp = POS.split("_");
		if(temp[1].matches("CC")){
			System.out.println("Coordinating conjuntion.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("CD")){
			System.out.println("Cardinal number.");
			System.out.println("The word is: " + temp[0]);
		}	
		if(temp[1].matches("DT")){
			System.out.println("Determiner.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("EX")){
			System.out.println("Existential /there/.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("FW")){
			System.out.println("Foreign word.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("IN")){
			System.out.println("Preoposition or subordaning conjunction.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("JJ")){
			System.out.println("Adjective.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("JJR")){
			System.out.println("Adjective, Comparitive.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("JJS")){
			System.out.println("Adjective, Superlative.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("LS")){
			System.out.println("List Item Marker.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("MD")){
			System.out.println("Modal.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("NN")){
			System.out.println("Noun.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("NNP")){
			System.out.println("Proper Noun, Singular.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("NNPS")){
			System.out.println("Proper Noun, Plural.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("NNS")){
			System.out.println("Noun, plural");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("PDT")){
			System.out.println("Predeterminer.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("POS")){
			System.out.println("Possessive Ending Noun.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("PRP")){
			System.out.println("Pesonal Pronoun.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("PRP$")){
			System.out.println("Possessive Pronoun.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("RB")){
			System.out.println("Adverb.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("RBR")){
			System.out.println("Adverb, Comparative.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("RBS")){
			System.out.println("Adverb, Superlative.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("RP")){
			System.out.println("Particle.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("SYM")){
			System.out.println("Symbol.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("TO")){
			System.out.println("/To/.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("UH")){
			System.out.println("Interjection.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("VB")){
			System.out.println("Verb, Base Form.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("VBD")){
			System.out.println("Verb, Past Tense.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("VBG")){
			System.out.println("Verb, Gerund or Present Participle.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("VBN")){
			System.out.println("Verb, past participle.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("VBP")){
			System.out.println("Verb, non-3rd person singular present.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("VBZ")){
			System.out.println("Verb, 3rd person, singular present.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("WDT")){
			System.out.println("Wh-Determiner.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("WP")){
			System.out.println("Wh-Pronoun.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("WP$")){
			System.out.println("Possessive wh-pronoun.");
			System.out.println("The word is: " + temp[0]);
		}
		if(temp[1].matches("WRB")){
			System.out.println("Wh-adverb.");
			System.out.println("The word is: " + temp[0]);
		}
		System.out.println("[END] POSAnalysis.Analysis(String POS)");
	}

}
