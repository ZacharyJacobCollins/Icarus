package Algorithm;
import java.io.IOException;
import java.util.Vector;

import Objects.PartOfSpeech;
import Objects.Sentence;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class PartsOfSpeech {

	public static Vector<String> POSVector = new Vector<String>();
	public static Vector<String> RootVector = new Vector<String>();
	static boolean firstTime = true;
	static MaxentTagger tagger=null;
	
	public static void tag(String sentence, Sentence s) throws IOException,ClassNotFoundException {
		//System.out.println("[LAUNCH] POSTagger.tag()");		

		if (firstTime) {
			tagger = new MaxentTagger("taggers\\english-left3words-distsim.tagger");
			firstTime = false;
		}
		
		String tagged = tagger.tagString(sentence);
		
		String taggedArray[] = tagged.split(" ");
		String temp[];
		
		for (int k = 0; k < taggedArray.length; k++) {
			temp = taggedArray[k].toString().split("_");
			//POSAnalysis.Analysis(taggedArray[k].toString());
			RootVector.add(temp[0].toString());
			POSVector.add(temp[1].toString());
			//System.out.println("[INFO] POS is: "+ temp[1].toString());
			PartOfSpeech pos = new PartOfSpeech(temp[0],temp[1]);
			s.pos.add(pos);
		}
		//System.out.println("[END] POSTagger.tag()");	
	}
	
}
