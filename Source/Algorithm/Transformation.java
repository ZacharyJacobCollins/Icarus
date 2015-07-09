package Algorithm;
import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Objects.Sentence;
import simplenlg.aggregation.ClauseCoordinationRule;
import simplenlg.features.Feature;
import simplenlg.features.Form;
import simplenlg.features.Gender;
import simplenlg.features.InterrogativeType;
import simplenlg.features.LexicalFeature;
import simplenlg.features.NumberAgreement;
import simplenlg.features.Person;
import simplenlg.features.Tense;
import simplenlg.framework.CoordinatedPhraseElement;
import simplenlg.framework.DocumentElement;
import simplenlg.framework.InflectedWordElement;
import simplenlg.framework.LexicalCategory;
import simplenlg.framework.NLGElement;
import simplenlg.framework.PhraseElement;
import simplenlg.framework.WordElement;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;

public class Transformation {

	public static void transformAll(Sentence s) throws Throwable{
		
		System.out.println("[LAUNCH] Transformation.transformAll()");
		
		getPastTense(s);
		getPresentTense(s);
		getFutureTense(s);
		getQuestions(s);
		getProgress(s);
		getAdjectiveOrdering(s);
		getAggregateAuxilary(s);
		getAppositive(s);
		getCompliments(s);
		getConjunction(s);
		getConjuntionType(s);
		getCuePhrase(s);
		//getElided(s);
		getForm(s);
		getIsComparative(s);
		getIsSuperlative(s);
		getModal(s);
		//getSupressedComplementiser(s);
		getSupressGenitiveGerund(s);
		getRaiseSpecifier(s);
		getPronominal(s);
		getProgress(s);
		getPossessive(s);
		get1stPerson(s);
		get2ndPerson(s);
		get3rdPerson(s);
		getPerfect(s);
		getPassive(s);
		getParticle(s);
		getNumberBoth(s);
		getNumberPlural(s);
		getNumberSingular(s);
		getNegated(s);
		getInterrogativeBareInfinitive(s);
		getInterrogativeGerund(s);
		getInterrogativeImperative(s);
		getInterrogativeInfinitive(s);
		getInterrogativeNormal(s);
		getInterrogativePastParticiple(s);
		getInterrogativePresentParticiple(s);
		
		System.out.println("[INFO] Response size = "+s.responses.size());
		for(int i=0;i<s.responses.size();i++){
			addFrontModifier(s.responses.get(i));
			addCompliment(s.responses.get(i));
			addPostModifier(s.responses.get(i));
			addPreModifier(s.responses.get(i));
		}
		
		for(Sentence computerReply : s.responses){
				//System.out.println("[PARLANCE] "+computerReply.sentence);
		}
		
		System.out.println("[END] Transformation.transformAll()");
	}

	public static void addFrontModifier(Sentence s) throws Throwable{
		
		Lexicon lexicon = Lexicon.getDefaultLexicon();
	    NLGFactory nlgFactory = new NLGFactory(lexicon);
	    Realiser realiser = new Realiser(lexicon);
	    
	    SPhraseSpec p = nlgFactory.createClause(s.subject,s.verb,s.object);
	    p.addFrontModifier(WordNet.pickReplacement(s.adj));  
	    s.addResponse(realiser.realiseSentence(p));
	    
	}
	
	public static void addCompliment(Sentence s) throws Throwable{
		
		Lexicon lexicon = Lexicon.getDefaultLexicon();
	    NLGFactory nlgFactory = new NLGFactory(lexicon);
	    Realiser realiser = new Realiser(lexicon);
	    
		
	    SPhraseSpec p = nlgFactory.createClause(s.subject,s.verb,s.object);
	    p.addComplement(WordNet.pickReplacement(s.adj));  
	    s.addResponse(realiser.realiseSentence(p));
	    
	}
	
	public static void addPostModifier(Sentence s) throws Throwable{
		
		Lexicon lexicon = Lexicon.getDefaultLexicon();
	    NLGFactory nlgFactory = new NLGFactory(lexicon);
	    Realiser realiser = new Realiser(lexicon);
	    
		
	    SPhraseSpec p = nlgFactory.createClause(s.subject,s.verb,s.object);
	    p.addPostModifier(WordNet.pickReplacement(s.adj));
	    s.addResponse(realiser.realiseSentence(p));
	    
	}
	public static void addPreModifier(Sentence s) throws Throwable{
		
		Lexicon lexicon = Lexicon.getDefaultLexicon();
	    NLGFactory nlgFactory = new NLGFactory(lexicon);
	    Realiser realiser = new Realiser(lexicon);
	    
		
	    SPhraseSpec p = nlgFactory.createClause(s.subject,s.verb,s.object);
	    p.addPreModifier(WordNet.pickReplacement(s.adj));
	    s.addResponse(realiser.realiseSentence(p));
	    
	}
	
	
	public static void getPastTense(Sentence s) throws Throwable {

		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.TENSE, Tense.PAST);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getPresentTense(Sentence s) throws Throwable {
		
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.TENSE, Tense.PRESENT);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getFutureTense(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.TENSE, Tense.FUTURE);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getQuestions(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory phraseFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.WHAT_OBJECT);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.YES_NO);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.WHERE);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.WHY);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.HOW);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.HOW_MANY);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE,
				InterrogativeType.HOW_PREDICATE);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE,
				InterrogativeType.HOW_PREDICATE);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.WHAT_SUBJECT);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE,
				InterrogativeType.WHO_INDIRECT_OBJECT);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.WHO_OBJECT);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.WHO_SUBJECT);
		s.addResponse(realiser.realiseSentence(p));

	}

	public static void getProgress(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory phraseFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.PROGRESSIVE, true);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getAdjectiveOrdering(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory phraseFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.ADJECTIVE_ORDERING, true);
		s.addResponse(realiser.realiseSentence(p));

	}

	public static void getAggregateAuxilary(Sentence s) throws Throwable {

		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory phraseFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.AGGREGATE_AUXILIARY, true);
		s.addResponse(realiser.realiseSentence(p));

	}

	public static void getAppositive(Sentence s) throws Throwable {
		
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory phraseFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.APPOSITIVE, true);
		s.addResponse(realiser.realiseSentence(p));

	}

	public static void getCompliments(Sentence s) throws Throwable {

		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory phraseFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.COMPLEMENTISER, true);
		s.addResponse(realiser.realiseSentence(p));

	}

	public static void getConjunction(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory phraseFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.CONJUNCTION, true);
		s.addResponse(realiser.realiseSentence(p));

	}

	public static void getConjuntionType(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory phraseFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.CONJUNCTION_TYPE, true);
		s.addResponse(realiser.realiseSentence(p));

	}

	public static void getCuePhrase(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory phraseFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.CUE_PHRASE, true);
		s.addResponse(realiser.realiseSentence(p));

	}

	public static void getElided(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory phraseFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.ELIDED, true);
		s.addResponse(realiser.realiseSentence(p));

	}

	public static void getForm(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory phraseFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.FORM, Form.BARE_INFINITIVE);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.FORM, Form.GERUND);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.FORM, Form.IMPERATIVE);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.FORM, Form.INFINITIVE);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.FORM, Form.NORMAL);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.FORM, Form.PAST_PARTICIPLE);
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.FORM, Form.PRESENT_PARTICIPLE);
		s.addResponse(realiser.realiseSentence(p));

	}

	public static void getIsComparative(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory phraseFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.IS_COMPARATIVE, true);
		s.addResponse(realiser.realiseSentence(p));

	}

	public static void getIsSuperlative(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory phraseFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.IS_SUPERLATIVE, true);
		s.addResponse(realiser.realiseSentence(p));

	}

	public static void getModal(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory phraseFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.MODAL, "can");
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.MODAL, "may");
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.MODAL, "must");
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.MODAL, "ought");
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.MODAL, "shall");
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.MODAL, "should");
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.MODAL, "will");
		s.addResponse(realiser.realiseSentence(p));

		p = phraseFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.MODAL, "would");
		s.addResponse(realiser.realiseSentence(p));

	}

	public static void getSupressedComplementiser(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause();
		p.setFeature(Feature.SUPRESSED_COMPLEMENTISER, true);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getSupressGenitiveGerund(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.SUPPRESS_GENITIVE_IN_GERUND, true);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getRaiseSpecifier(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.RAISE_SPECIFIER, true);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getPronominal(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.PRONOMINAL, true);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getPossessive(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.POSSESSIVE, true);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void get1stPerson(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.PERSON, Person.FIRST);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void get2ndPerson(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.PERSON, Person.SECOND);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void get3rdPerson(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.PERSON, Person.THIRD);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getPerfect(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.PERFECT, true);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getPassive(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.PASSIVE, true);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getParticle(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.PARTICLE, true);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getNumberBoth(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.NUMBER, NumberAgreement.BOTH);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getNumberPlural(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getNumberSingular(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.NUMBER, NumberAgreement.SINGULAR);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getNegated(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.NEGATED, true);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getInterrogativeBareInfinitive(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE, Form.BARE_INFINITIVE);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getInterrogativeGerund(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE, Form.GERUND);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getInterrogativeImperative(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE, Form.IMPERATIVE);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getInterrogativeInfinitive(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE, Form.INFINITIVE);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getInterrogativeNormal(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE, Form.NORMAL);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getInterrogativePastParticiple(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE, Form.PAST_PARTICIPLE);
		s.addResponse(realiser.realiseSentence(p));
	}

	public static void getInterrogativePresentParticiple(Sentence s) throws Throwable {
		 
		 
		 
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause(s.subject, s.verb, s.object);
		p.setFeature(Feature.INTERROGATIVE_TYPE, Form.PRESENT_PARTICIPLE);
		s.addResponse(realiser.realiseSentence(p));
	}

}
