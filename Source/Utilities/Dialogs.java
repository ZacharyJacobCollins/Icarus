package Utilities;

import Objects.Conversation;

public class Dialogs {
	
	
	
	public static String betterGoodbye(Conversation c){
		String bye = "";
		
		if(c.chatLength==0){
			bye = "Dude. You're leaving before we even started! Bye then!";
		}
		else if(c.chatLength==1){
			bye = "Whoa, you're leaving now? We just started.";
		}
		else if(c.chatLength==2){
			bye = "Yeah, I've got things to do too. Later.";
		}
		else if(c.chatLength==3){
			bye = "Okay bye! See ya later!";
		}
		else if(c.chatLength==4){
			bye = "Just when I was starting to know you. Haha! Bye!";
		}
		else if(c.chatLength==5){
			bye = "I hope to hear from you again soon, fella!";
		}
		else if(c.chatLength==6){
			bye = "I'll be thinking about what you said.";
		}
		else if(c.chatLength<6){
			bye = "That was a good chat. See you later!";
		}

		return bye;
	}
	
	public void conversationDriver(){
		//List of 'things to say next', after one iteration of sentence exchange has completed. 
		//"Okay, go on.", "Tell me more.", "Continue, please.", "This is *interesting!", "What happenened* next?"
		//"What *did/will you* do?", "I want to know more.", "How is that working out for you?", "Hmm..."
	}
	
	
	
	

}
