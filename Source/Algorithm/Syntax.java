package Algorithm;
import java.util.*;

import Objects.Dependency;
import Objects.Sentence;

public class Syntax {

	public Vector<String> dependency = new Vector<String>();
	public Vector<String> depFromColl = new Vector<String>();
	public Vector<String> collapsed = new Vector<String>();
	
	public void syntaxAnalysis(String graph,Sentence s) {

		String[] graphA = graph.split("\n");
		
		for (int i = 0; i < graphA.length; i++) {
			dependency.add(graphA[i]);
		}

		for (int k = 0; k < dependency.size(); k++) {

			String[] temp = dependency.get(k).toString().split("\\(");
			String target = temp[0];

			if (target.contains("_")) {
				String[] temp2 = target.split("_");
				depFromColl.add(temp2[0]);
				collapsed.add(temp2[1]);
				target = temp2[0];
			}

			if (target.matches(Dependencies(target))) {
				Analysis(dependency.get(k).toString(), s);
			} else {
				break;
			}
		}
	}

	public String Dependencies(String depen) {
		String[] dependencyList = { "acomp", "advcl", "advmod", "agent",
				"amod", "appos", "aux", "auxpass", "cc", "ccomp", "conj",
				"cop", "csubj", "csubjpass", "dep", "det", "discourse", "dobj",
				"expl", "goeswith", "iobj", "mark", "mwe", "neg", "nn",
				"npadvmod", "nsubj", "nsubjpass", "num", "number", "parataxis",
				"pcomp", "pobj", "poss", "possessive", "preconj", "predet",
				"prep", "prepc", "prt", "punct", "quantmod", "referent",
				"root", "tmod", "vmod", "xcomp", "xsubj" };
		boolean flag = true;

		while (flag == true) {
			//System.out.println(depen);

			for (int i = 0; i < dependencyList.length; i++) {
				if (dependencyList[i].matches(depen)) {
					return dependencyList[i];
				} else {

				}
			}
			flag = false;
		}
		return depen;
	}

	public void Analysis(String dependency, Sentence s) {

		//System.out.println("[LAUNCH] Syntax.Analysis()");
		
		String cleanDependency = "";
		String collapsed = "";
		String[] temp = dependency.split("\\(");
		String extractedDependency = temp[0];
		String[] temp2 = temp[1].split(",");
		String[] temp3 = temp2[0].split("-");
		String governor = temp3[0];
		String[] temp4 = temp2[1].split("-");
		String dependent = temp4[0];
		
		Dependency d = new Dependency(extractedDependency.trim(),governor.trim(),dependent.trim());
		
		if (extractedDependency.contains("_")) {
			String[] actualDependency = extractedDependency.split("_");
			cleanDependency = actualDependency[0];
			collapsed = actualDependency[1];
			System.out.println("[INFO] I found a collapsed dependency.");
			d = new Dependency(collapsed.trim(),governor.trim(),dependent.trim());
		}
		
		s.syntax.add(d);

		if (extractedDependency.matches("root")) {
			//System.out.println("root");
		}
		if (extractedDependency.matches("aux")) {
			//System.out.println("auxillary.");	
		}
		if (extractedDependency.matches("auxpass")) {
			//System.out.println("passive auxilary.");			
		}
		if (extractedDependency.matches("cop")) {
			//System.out.println("copula");			
		}
		if (extractedDependency.matches("arg")) {
			//System.out.println("argument.");			
		}
		if (extractedDependency.matches("agent")) {
			//System.out.println("agent.");			
		}
		if (extractedDependency.matches("comp")) {
			//System.out.println("compliment.");			
		}
		if (extractedDependency.matches("acomp")) {
			//System.out.println("adjectival complement");			
		}
		if (extractedDependency.matches("ccomp")) {
			//System.out.println("clausal complement");			
		}
		if (extractedDependency.matches("xcomp")) {
			//System.out.println("clausal complement with external subject.");			
		}
		if (extractedDependency.matches("obj")) {
			//System.out.println("object.");			
		}
		if (extractedDependency.matches("dobj")) {
			//System.out.println("direct object");			
		}
		if (extractedDependency.matches("iobj")) {
			//System.out.println("Indirect Object");			
		}
		if (extractedDependency.matches("pobj")) {
			//System.out.println("object of preposition");			
		}
		if (extractedDependency.matches("subj")) {
			//System.out.println("Subject");			
		}
		if (extractedDependency.matches("nsubj")) {
			//System.out.println("nominal subject.");
		}
		if (extractedDependency.matches("nsubjpass")) {
			//System.out.println("passive nominal subject");
		}
		if (extractedDependency.matches("csubj")) {
			//System.out.println("clausal subject");
		}
		if (extractedDependency.matches("csubjpass")) {
			//System.out.println("passivel clausal subject");	
		}
		if (extractedDependency.matches("cc")) {
			//System.out.println("Coordination.");		
		}
		if (extractedDependency.matches("conj")) {
			//System.out.println("conjunct");
		}
		if (extractedDependency.matches("expl")) {
			//System.out.println("expletive");
		}
		if (extractedDependency.matches("mod")) {
			//System.out.println("modifier.");	
		}
		if (extractedDependency.matches("amod")) {
			//System.out.println("adjectival modifier.");
			
		}
		if (extractedDependency.matches("appos")) {
			//System.out.println("appositional modifier");
			
		}
		if (extractedDependency.matches("adcvl")) {
			//System.out.println("adverbial clause modifier.");
			
		}
		if (extractedDependency.matches("det")) {
			//System.out.println("determiner");
			
		}
		if (extractedDependency.matches("predet")) {
			//System.out.println("Predeterminer");
			
		}
		if (extractedDependency.matches("preconj")) {
			//System.out.println("Preconjunct");
			
		}
		if (extractedDependency.matches("vmod")) {
			//System.out.println("reduced non-finite verbal modifier");
			
		}
		if (extractedDependency.matches("mwe")) {
			//System.out.println("Multiword expression modifier");
			
		}
		if (extractedDependency.matches("mark")) {
			//System.out.println("marker");
			
		}
		if (extractedDependency.matches("advmod")) {
			//System.out.println("adverbial modifier");
			
		}
		if (extractedDependency.matches("rcmod")) {
			//System.out.println("Relative clause modifier");
			
		}
		if (extractedDependency.matches("quantmod")) {
			//System.out.println("quantifier modifier");
			
		}
		if (extractedDependency.matches("nn")) {
			//System.out.println("noun compound modifier");
			
		}
		if (extractedDependency.matches("npadvmod")) {
			//System.out.println("noun phrase adverbial modiﬁer ");
			
		}
		if (extractedDependency.matches("tmod")) {
			//System.out.println("temporal modifier");
			
		}
		if (extractedDependency.matches("num")) {
			//System.out.println("numeric modifier");
			
		}
		if (extractedDependency.matches("number")) {
			//System.out.println("element of compound number");
			
		}
		if (extractedDependency.matches("prep")) {
			//System.out.println("prepositional modifier");
			
		}
		if (extractedDependency.matches("pos")) {
			//System.out.println("possession modifier");
			
		}
		if (extractedDependency.matches("prt")) {
			//System.out.println("phrasal verb participle");
			
		}
		if (extractedDependency.matches("parataxis")) {
			//System.out.println("parataxis");
			
		}
		if (extractedDependency.matches("punct")) {
			//System.out.println("punctuation");
			
		}
		if (extractedDependency.matches("ref")) {
			//System.out.println("rerferent");
			
		}
		if (extractedDependency.matches("sdep")) {
			//System.out.println("demantic dependent");
			
		}
		if (extractedDependency.matches("xsubj")) {
			//System.out.println("controlling subject");
			
		}
		//System.out.println("[END] Syntax.Analysis()");
	}
}
