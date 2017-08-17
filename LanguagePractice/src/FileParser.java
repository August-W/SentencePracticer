import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class FileParser {
	private static String[] type = {"NOUNS", "VERBS", "ADVERBS", "ADJECTIVES", "GRAMMARSTRUCTURES", "OTHERS"}; //For checking the categories in the Dictionary file
	private static LinkedList<String> nouns = new LinkedList<String>(); //These hold all the word;translation pairs in each category
	private static LinkedList<String> verbs = new LinkedList<String>();
	private static LinkedList<String> adverbs = new LinkedList<String>();
	private static LinkedList<String> adjectives = new LinkedList<String>();
	private static LinkedList<String> grammarStructures = new LinkedList<String>();
	private static LinkedList<String> others = new LinkedList<String>();
	private static String line;
	
	public static void parse(String fname){  //Fill the above LinkedLists with word;translation pairs
		try {
			File file = new File(fname); //the user-defined Dictionary file
			FileInputStream fis = new FileInputStream(file);
			BufferedReader rb = new BufferedReader(new InputStreamReader(fis,StandardCharsets.UTF_8)); //must be utf-8 encoded for languages
			rb.mark(1);
			if (rb.read() != 0xFEFF)
				rb.reset(); //Get rid of the leading "?" from the utf-8 encoding, if there is one
			createList(rb); //call the below function
		} catch (Exception e) {
			System.out.println("Error here: "+e);
		}
	}

	public static LinkedList<String> createList(BufferedReader theBr) throws Exception{ //This is where most of the work is done, recursively
		LinkedList<String> words = new LinkedList<String>();
		while((line=theBr.readLine())!=null){
			if(line.charAt(0)=='#'){//this is where each category's word;translation list ends
				return words; //this is the base case per-category 
			}
			else if(line.charAt(0)=='*'){//category title
		    	   for(int i=0; i<6; i++){
		    		   if(line.substring(1, line.length()).equals(type[i])){
		    			   switch(i){//recursively call this function for each category
			    		   	case 0:  nouns=createList(theBr);
			    		   	case 1:  verbs=createList(theBr);
			    		   	case 2:  adverbs=createList(theBr);
			    		   	case 3:  adjectives=createList(theBr);
			    		   	case 4:  grammarStructures=createList(theBr);
			    		   	case 5:  others=createList(theBr);
			    		   }
			    	   }
		    	   }
			}
			else{//regular word;translation pairs end up here
				words.add(line);
			}
		}
		return others;//finally, exit the function
	}
	
	public static LinkedList<String> getNouns(){
		return nouns;
	}
	
	public static LinkedList<String> getVerbs(){
		return verbs;
	}
	
	public static LinkedList<String> getAdverbs(){
		return adverbs;
	}
	
	public static LinkedList<String> getAdjectives(){
		return adjectives;
	}
	
	public static LinkedList<String> getGrammarStructures(){
		return grammarStructures;
	}
	
	public static LinkedList<String> getOthers(){
		return others;
	}	
}
