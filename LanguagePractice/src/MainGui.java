import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainGui extends JPanel implements ActionListener {
	public static JTextArea pArea;
	public static JScrollPane scrollPane;
	private static int started = 0;
	private static String ftrans;
	private static LinkedList<String> nouns = new LinkedList<String>();
	private static LinkedList<String> verbs = new LinkedList<String>();
	private static LinkedList<String> adverbs = new LinkedList<String>();
	private static LinkedList<String> adjectives = new LinkedList<String>();
	private static LinkedList<String> grammarStructures = new LinkedList<String>();
	private static LinkedList<String> others = new LinkedList<String>();
	private static String finish;
	private int rNounLength = 1;
	private int rVerbLength = 1;
	private int rAdverbLength = 1;
	private int rAdjectiveLength = 1;
	private int rGrammarStructureLength = 1;
	private int rOtherLength = 1;
	
	public MainGui(String thePath){//The Gui for the main stuff (after the user has selected a dictionary file)
		pArea = new JTextArea("                                      SENTENCE PROMPT                                      \n \n \n \n \n \n \n \n");
		scrollPane = new JScrollPane(pArea); 
		pArea.setEditable(false);
		add(scrollPane);
		JButton b1 = new JButton("Get a new sentence prompt!");
		b1.setMnemonic(KeyEvent.VK_E);
		b1.setActionCommand("prompt");
		b1.addActionListener(this);	
		add(b1);
		JButton b2 = new JButton("Translate/Re-translate words!");
		b2.setMnemonic(KeyEvent.VK_E);
		b2.setActionCommand("trans");
		b2.addActionListener(this);	
		add(b2);
		FileParser.parse(thePath);
		nouns=FileParser.getNouns();//the entire file is read into these as soon as this panel is loaded into the frame
		verbs=FileParser.getVerbs();
		adverbs=FileParser.getAdverbs();
		adjectives=FileParser.getAdjectives();
		grammarStructures=FileParser.getGrammarStructures();
		others=FileParser.getOthers();
		setVisible(true);
	}
	
	public String[] randoms(LinkedList<String> words, int length){//getting random word;translation pairs. User specifies length (how many random words of this category to get)
		String[] rWords = new String[length];
		int[] ints = new Random().ints(0,words.size()).distinct().limit(length).toArray(); //ensure that no random word is repeated
		StringBuilder strBuilder = new StringBuilder(); //for the words
		StringBuilder strBuilder2 = new StringBuilder(); //for the translations
		for(int i=0; i<length; i++){
			rWords[i]=words.get(ints[i]);
			strBuilder.append(rWords[i].substring(0, rWords[i].indexOf(';'))+",\n");
			strBuilder2.append(rWords[i].substring(rWords[i].indexOf(';')+1,rWords[i].length())+",\n");
		}
		return new String[] {strBuilder.toString(), strBuilder2.toString()};
	}
	
	public void actionPerformed(ActionEvent e){
		if("prompt".equals(e.getActionCommand())){ //get a sentence prompt - requires random word;translation pairs	
			String[] f=randoms(nouns,rNounLength); //get the randoms
			finish=f[0]; //words
			ftrans=f[1]; //translations
			f=randoms(verbs,rVerbLength);
			finish+=f[0];
			ftrans+=f[1];
			f=randoms(adverbs,rAdverbLength);
			finish+=f[0];
			ftrans+=f[1];
			f=randoms(adjectives,rAdjectiveLength);
			finish+=f[0];
			ftrans+=f[1];
			f=randoms(grammarStructures,rGrammarStructureLength);
			finish+=f[0];
			ftrans+=f[1];
			f=randoms(others,rOtherLength);
			finish+=f[0];
			ftrans+=f[1];
			finish=finish.substring(0,finish.length()-2);
			pArea.setText("Create a new sentence using the following words:\n"+finish);
			started=1; //keep track of the text being viewed - the opening text is 0, the words is 1, the translations is 2
		}
		else if("trans".equals(e.getActionCommand())){ //switch from words to translations or vice versa
			if(started==1){
				pArea.setText("Create a new sentence using the following words:\n"+ftrans);
				started=2;
			}
			else if(started==2){
				pArea.setText("Create a new sentence using the following words:\n"+finish);
				started=1;
			}
		}
	}
	
	public void setNounLength(int n){
		rNounLength=n;
	}
	
	public void setVerbLength(int n){
		rVerbLength=n;
	}
	
	public void setAdverbLength(int n){
		rAdverbLength=n;
	}
	
	public void setAdjectiveLength(int n){
		rAdjectiveLength=n;
	}
	
	public void setGrammarStructureLength(int n){
		rGrammarStructureLength=n;
	}
	
	public void setOtherLength(int n){
		rOtherLength=n;
	}

}
