package Models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Objects.PartOfSpeech;
import Objects.Sentence;

public class Interrogative extends Model{
	
	//How to handle questions directed at the computer
	
	/* [BASES]
	 * Who ---> Do I look like Facebook to you?
	 * What --> Ask an encyclopedia.
	 * When --> Time is really just an illusion. 
	 * Where --> There's this really cool site called GOOGLE MAPS.
	 * Why --> Hold on, I'm putting on my philosopher's hat for this. 
	 * How --> It is done with <ADV>.
	 * Which --> Pick a number one through ten. There's your answer. 
	 * 
	 * [MODIFIERS]
	 * Who: was/is that/this, were you <VBP>, am I, are you, are they? 
	 * What: was/is that/this, were you <VBP>, am I, are you, are they? 
	 * When:  was/is that/this, were you <VBP>, am I, are you, are they? 
	 * Where: was/is that/this, were you <VBP>, am I, are you, are they? 
	 * Why: was/is that/this, were you <VBP>, am I, are you, are they? 
	 * How: is that <VBD/VBN/NN>
	 * Which: was/is that/this, were you <VBP>, am I, are you, are they? 
	 * 
	 * [ALTERNATES]
	 * Are <PN> <VB>
	 * Do <PN> <VB>
	 * Does <PN> <VB>
	 * Have <PN> <VB>
	 * Would <PN>  <VB>
	 * May <PN> <VB>
	 * Can <PN> <VB>
	 * 
	 * */
	
	public static void setQuestionType(Sentence s){                 
            s.questionType = "";                                                                                                  
			if(s.elements[0].toLowerCase().matches("who")){s.questionType = ("who");}
			else if(s.elements[0].toLowerCase().matches("what")){s.questionType = ("what");} 
			else if(s.elements[0].toLowerCase().matches("when")){s.questionType = ("when");} 
			else if(s.elements[0].toLowerCase().matches("where")){s.questionType = ("where");} 
			else if(s.elements[0].toLowerCase().matches("why")){s.questionType = ("why");} 
			else if(s.elements[0].toLowerCase().matches("how")){s.questionType = ("how");} 
			else if(s.elements[0].toLowerCase().matches("which")){s.questionType = ("which");}
			
			if(!(s.questionType=="")){
				System.out.println("[INFO] Question Type: " +s.questionType);
			}
	}
	
	
	

}
