package Objects;
import java.util.Vector;


public class Delta {

	public Vector<Tuple> deltaDependency = new Vector<Tuple>();
	public Vector<Tuple> deltaRoot = new Vector<Tuple>();
	public Vector<String> newSyntaxType = new Vector<String>();
	public Vector<Dependency> newSyntax = new Vector<Dependency>();
	public boolean subjectConsistency=false;
	public double semanticDeviation; //use Wordnet to Compute semantic differences
	
	
	
	public Delta(Response r1, Response r2){
		
		Sentence s1 = r1.sentences.get(0);
		Sentence s2 = r2.sentences.get(0);
		
		for(Dependency d1: s1.syntax){
			for(Dependency d2: s2.syntax){
				if(d1.type.matches(d2.type)){
					this.deltaDependency.add(new Tuple(d1,d2));	
					if(d1.type.matches("nsubj")){
						subjectConsistency = true;
						System.out.println("[PARLANCE] I'm assuming  \'"+d2.dependent.toLowerCase()+"\' relates to \'"+d1.dependent.toLowerCase()+"\'?");
						this.deltaRoot.add(new Tuple(d1,d2));
					}
				}
			}	
		}
		
//		for(Dependency d1: s1.syntax){
//			if(!newSyntaxType.contains(d1.type)){
//				this.newSyntaxType.add(d1.type);
//				this.newSyntax.add(d1);
//			}
//		}
//		for(Dependency d2: s2.syntax){
//			if(!newSyntaxType.contains(d2.type)){
//				this.newSyntaxType.add(d2.type);
//				this.newSyntax.add(d2);
//				System.out.println("[INFO] New syntax type detected: "+d2.type);
//			}
//		}
		
	}
	
	public static void correlateRoots(Sentence s1, Sentence s2){
		Dependency root1;
		Dependency root2;
		
		for(Dependency d1 : s1.syntax){
			System.out.println(d1.toString());
			if(d1.type.matches("root")){
				root1 = d1;
			}
		}
		for(Dependency d2 : s2.syntax){
			System.out.println(d2.toString());
			if(d2.type.matches("root")){
				root2 = d2;
			}
		}
	}
	
	
	
}


