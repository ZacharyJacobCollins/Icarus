package Algorithm;

import rita.wordnet.RiWordnet;

public class WordNet {

	static RiWordnet wordnet = new RiWordnet(null);

	public static String pickReplacement(String word) {
		if (word != null) {

			String pos = wordnet.getBestPos(word);
			if (pos != null) {
				// First check and see if there are any antonyms
				// If so, pick a random one and return
				String[] antonyms = wordnet.getAllAntonyms(word, pos);
				if (antonyms != null) {
					String replacement = antonyms[(int) Math.random() * antonyms.length];
					System.out.println(word + " ==> " + replacement + "  (antonym " + pos + ")");
					return replacement;
				}
				// Then check and see if there are any hyponyms
				// If so, pick a random one and return
				String[] hyponyms = wordnet.getAllHyponyms(word, pos);
				if (hyponyms != null) {
					String replacement = hyponyms[(int) Math.random() * hyponyms.length];
					System.out.println(word + " ==> " + replacement + "  (hyponym " + pos + ")");
					return replacement;
				}
				// Same thing for synonyms
				String[] synonyms = wordnet.getAllSynonyms(word, pos);
				if (synonyms != null) {
					String replacement = synonyms[(int) Math.random() * synonyms.length];
					System.out.println(word + " ==> " + replacement + "  (synonym " + pos + ")");
					return replacement;
				}
			}
		}
		return word;
	}
}
