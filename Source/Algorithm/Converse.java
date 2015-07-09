package Algorithm;

import java.util.*;

import Models.Exclamatory;
import Models.Interrogative;

import java.io.*;

import Objects.Conversation;
import Objects.Delta;
import Objects.Response;
import Objects.Sentence;
import Utilities.Dialogs;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.ArrayCoreMap;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.sentiment.*;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.*;

public class Converse {

	public static Conversation c = new Conversation();
	public static Scanner input = new Scanner(System.in);
	public static Properties props = new Properties();
	public static PrintWriter out;
	public static Annotation annotation;
	public static String response = "";

	public static void main(String[] args) throws Throwable {

		
		//[SETUP]
		System.out.println("[Icarus v1.42 Copyright (C) 2014 - 2015 Kisora Thomas]");
		props.setProperty("annotators" , "tokenize, ssplit, pos, lemma, parse, sentiment, ner");
		
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		Response previousResponse = null;
		
		while (!response.equals("BYE")) {
			
			
			//[QUESTION]
			System.out.println("[ICARUS] How are you doing today? ");
			System.out.print("[USER] ");
			response = input.nextLine();
			if(response.matches("BYE")) break;
			
			
			//[ANNOTATION]
			annotation = pipeline.process(response);
			out = new PrintWriter(System.out);
			List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
            pipeline.annotate(annotation);
            
            
			//[RESPONSE]
			//System.out.println("[INFO] Generating Response object...");
			Response currentResponse = new Response(response);
			
			for (CoreMap sentence : sentences) {
				
				
				//[SENTENCE]
				//System.out.println("[INFO] Generating Sentence object...");
				Sentence s = new Sentence(sentence.toString());
				
				
				//[SENTIMENT]
				//System.out.println("[INFO] Generating Sentiment...");
				String sentiment = sentence.get(SentimentCoreAnnotations.ClassName.class);
				s.sentiment = (sentiment.toLowerCase());
				System.out.println("[ICARUS] That sounded "+s.sentiment.toLowerCase()+".");
				
				
				//[PARTS OF SPEECH]
				PartsOfSpeech.tag(sentence.toString(),s);	
				
				
			    //[TOKENIZATION]
				for (CoreMap token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
					ArrayCoreMap aToken = (ArrayCoreMap) token;
					s.tokens.add(aToken.toShorterString());
				}
				Tokenize.Setup(s.tokens,s);
			
				
				//[DEPENDENCIES]
				SemanticGraph graph = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
				Syntax syn = new Syntax();
				syn.syntaxAnalysis(graph.toString("plain"),s);
				
				
				//[CONVERT]
				Converter.generatePOS(s);
				
				//[SENTENCE FUNCTIONS]
				s.setFocus();
				s.transpose();
				s.detectNegation();
				s.detectPossession();
				s.detectSubject();
				
				//[STATIC CLASSES]
				Interrogative.setQuestionType(s);
				Exclamatory.reply(s);
				Finale.choix(s);
				//Transformation.transformAll(s);
				currentResponse.sentences.add(s);
				c.setMood(s.sentiment);
			}
			
			
			//[CONVERSATION]
			if(!(previousResponse==null)){
				System.out.println("[INFO] Delta previous: "+previousResponse.sentences.get(0).sentence);
				System.out.println("[INFO] Delta current: "+currentResponse.sentences.get(0).sentence); 
				//Delta.correlateRoots(previousResponse.sentences.get(0),currentResponse.sentences.get(0));
				c.addDelta(new Delta(previousResponse,currentResponse));
			}
			c.responses.add(currentResponse);
			c.chatLength++;
			previousResponse = currentResponse;
		}
		
		//[GOODBYE]
		System.out.println("[ICARUS] "+Dialogs.betterGoodbye(c));
		System.out.println("[ICARUS] Until we speak again.");
	}
	
}
