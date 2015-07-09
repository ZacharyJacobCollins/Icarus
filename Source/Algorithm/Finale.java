package Algorithm;

import Brain.Self;
import Objects.Dependency;
import Objects.Sentence;

public class Finale {

	public static String computerReply = "Ask me a question about me, and I may have an answer for you.";
	
	public static void choix(Sentence s){
		
		if(s.type.matches("interrogative")){
			if(s.focus.matches("your")){
				if(s.questionType.matches("what")){
					for(Dependency syn : s.syntax){
						if(syn.dependent.matches("your")){
							if(syn.type.matches("poss")){
								if(syn.governor.matches("name")){
									computerReply = "I go by the name "+Self.name+".";
								}
								else if(syn.governor.matches("age")){
									computerReply = "My age is "+Self.age+".";
								}
								else if(syn.governor.matches("interests")){
									computerReply = "My interests are "+Self.interests+".";
								}
								else if(syn.governor.matches("dream")){
									computerReply = "My dream is "+Self.dream+".";
								}
							}
						}
					}				
				}
			}
		}
		System.out.println("[ICARUS] "+computerReply);
	}
	
}
