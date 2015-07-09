package Models;

import Objects.Sentence;

public class Exclamatory {

	public static void reply(Sentence s){
		if(s.type.matches("exclamatory")){
			if(s.numericalSentiment()>0){
				System.out.println("[ICARUS] Yay! I am really excited for you!");
			}
			else if(s.numericalSentiment()<0){
				System.out.println("[ICARUS] Do not yell at me like that!");
			}			
		}
	}
}
