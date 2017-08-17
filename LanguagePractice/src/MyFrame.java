import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyFrame extends JFrame implements ActionListener{
	private static JButton furst;
	private MainGui mainGui;
	
	public static void main(String[] args){
		MyFrame myFrame=new MyFrame(); //Begin the application
	}
	
	public MyFrame(){
		try {//Set to OS's look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			System.out.println("L&F Error: "+e);
		}
		setTitle("Language Learning");
		ImageIcon icon = createImageIcon("datboi.png");
		setIconImage(icon.getImage());
		JLabel label1 = new JLabel("Sentence Practicer", JLabel.CENTER);
		label1.setFont(new Font("Serif", Font.PLAIN, 27));
		add(label1, BorderLayout.PAGE_START);
		furst = new JButton("Select your \"Dictionary\" file");
		furst.setMnemonic(KeyEvent.VK_E);
		furst.setFont(new Font("Serif", Font.PLAIN, 18));
		furst.setActionCommand("getfile");
		furst.addActionListener(this);
		add(furst, BorderLayout.CENTER);
		String original="";
		byte[] data;
		try {
			data = Files.readAllBytes(Paths.get("about.txt"));//read and display the about.txt, which is UTF-8 encoded
			original=new String(data, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("Error: "+e);
		}
		JTextArea textArea = new JTextArea(original);
		textArea.setFont(new Font("GNU UNifont", Font.PLAIN, 14));
		textArea.setMargin(new Insets(20,30,0,30));
		textArea.setBackground(new Color(0, 0, 0, 1));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		add(textArea, BorderLayout.PAGE_END);
		setMinimumSize(new Dimension(800, 800));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e){
		if("getfile".equals(e.getActionCommand())){//Open FileChooser to get the Dictionary file
			String dictionaryPath;
			JFileChooser fc = new JFileChooser();
			FileFilter filter = new FileNameExtensionFilter("txt file","txt");
			fc.setFileFilter(filter);
			fc.setDialogTitle("First, select \"Dictionary\" text file");		
	        int returnVal = fc.showOpenDialog(this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            dictionaryPath=file.getAbsolutePath();
	            mainGui = new MainGui(dictionaryPath); //File is selected, so prepare the next panel and read the file
	            add(mainGui, BorderLayout.CENTER);
				furst.setVisible(false);
				remove(furst);
				JMenuBar menuBar = new JMenuBar(); //The menu allows users to select the amount of each word category they want in their prompt
				JButton sb = new JButton("Nouns");
				sb.setMnemonic(KeyEvent.VK_E);
				sb.setActionCommand("nsettings");
				sb.addActionListener(this);
				menuBar.add(sb);
				JButton sb2 = new JButton("Verbs");
				sb2.setMnemonic(KeyEvent.VK_E);
				sb2.setActionCommand("vsettings");
				sb2.addActionListener(this);
				menuBar.add(sb2);
				JButton sb3 = new JButton("Adverbs");
				sb3.setMnemonic(KeyEvent.VK_E);
				sb3.setActionCommand("asettings");
				sb3.addActionListener(this);
				menuBar.add(sb3);
				JButton sb4 = new JButton("Ajdectives");
				sb4.setMnemonic(KeyEvent.VK_E);
				sb4.setActionCommand("ajsettings");
				sb4.addActionListener(this);
				menuBar.add(sb4);
				JButton sb5 = new JButton("Others");
				sb5.setMnemonic(KeyEvent.VK_E);
				sb5.setActionCommand("osettings");
				sb5.addActionListener(this);
				menuBar.add(sb5);
				setJMenuBar(menuBar);
	        }		
		}
		else if("nsettings".equals(e.getActionCommand())){
			Object[] possibilities = {"1", "2", "3", "4", "5"};//User can have 1-5 nouns in the sentence prompt
			JFrame frame = new JFrame();
			int s = Integer.parseInt((String) JOptionPane.showInputDialog(frame,"Choose how many nouns/sentence you want (Default is 1):","Customized Dialog",JOptionPane.PLAIN_MESSAGE,new ImageIcon(),possibilities,"1"));
			if (s>0) {
			    mainGui.setNounLength(s);
			}
			return;
		}
		else if("vsettings".equals(e.getActionCommand())){
			Object[] possibilities = {"1", "2", "3", "4", "5"};
			JFrame frame = new JFrame();
			int s = Integer.parseInt((String) JOptionPane.showInputDialog(frame,"Choose how many verbs/sentence you want (Default is 1):","Customized Dialog",JOptionPane.PLAIN_MESSAGE,new ImageIcon(),possibilities,"1"));
			if (s>0) {
			    mainGui.setVerbLength(s);
			}
			return;
		}
		else if("asettings".equals(e.getActionCommand())){
			Object[] possibilities = {"1", "2", "3", "4", "5"};
			JFrame frame = new JFrame();
			int s = Integer.parseInt((String) JOptionPane.showInputDialog(frame,"Choose how many adverbs/sentence you want (Default is 1):","Customized Dialog",JOptionPane.PLAIN_MESSAGE,new ImageIcon(),possibilities,"1"));
			if (s>0) {
			    mainGui.setAdverbLength(s);
			}
			return;
		}
		else if("ajsettings".equals(e.getActionCommand())){
			Object[] possibilities = {"1", "2", "3", "4", "5"};
			JFrame frame = new JFrame();
			int s = Integer.parseInt((String) JOptionPane.showInputDialog(frame,"Choose how many adjectives/sentence you want (Default is 1):","Customized Dialog",JOptionPane.PLAIN_MESSAGE,new ImageIcon(),possibilities,"1"));
			if (s>0) {
			    mainGui.setAdjectiveLength(s);
			}
			return;
		}
		else if("osettings".equals(e.getActionCommand())){
			Object[] possibilities = {"1", "2", "3", "4", "5"};
			JFrame frame = new JFrame();
			int s = Integer.parseInt((String) JOptionPane.showInputDialog(frame,"Choose how many others/sentence you want (Default is 1):","Customized Dialog",JOptionPane.PLAIN_MESSAGE,new ImageIcon(),possibilities,"1"));
			if (s>0) {
			    mainGui.setOtherLength(s);
			}
			return;
		}
	}
	
	//Copied code for an icon for dat boi
	protected ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.out.println("Couldn't find file: " + path);
			return null;
		}
	}
}
