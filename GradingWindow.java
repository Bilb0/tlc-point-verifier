package tlc.buildpoints;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Main Application Window. Does not use a layout manager.
 * @author Bilbo
 */
public class GradingWindow extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	//Stores the last successful final results for use in the clip-board.
	String lastResults = null;
	
	//Text Areas
	JTextArea results = new JTextArea(18, 38);
	
	//Text Fields
	JTextField username = new JTextField(20);
	JTextField buildername = new JTextField(20);
	JTextField aethstetics = new JTextField(2);
	JTextField materials = new JTextField(2);
	JTextField location = new JTextField(2);
	JTextField creativity = new JTextField(2);
	JTextField design = new JTextField(2);
	JTextField landscaping = new JTextField(2);
	
	//Labels
	JLabel title = new JLabel("TLC Build Point Verifier");
	JLabel usertext = new JLabel("Enter your username:       ");
	JLabel buildertext = new JLabel("Enter the builder's name: ");
	JLabel aethsteticstext = new JLabel("Aethstetics (1-10):                                         ");
	JLabel materialstext = new JLabel("Materials (1-10):                                           ");
	JLabel locationtext = new JLabel("Location (1-10):                                             ");
	JLabel creativitytext = new JLabel("Creativity (1-10):                                           ");
	JLabel designtext = new JLabel("Design (1-10):                                                ");
	JLabel landscapingtext = new JLabel("Landscaping (1-10):                                  ");
	
	//Check Boxes
	JCheckBox landscapingcheck = new JCheckBox();
	
	//Buttons
	JButton aethsHelp = new JButton("?");
	JButton materHelp = new JButton("?");
	JButton locatHelp = new JButton("?");
	JButton creatHelp = new JButton("?");
	JButton desigHelp = new JButton("?");
	JButton landsHelp = new JButton("?");
	JButton copyToClip = new JButton("Copy");
	JButton help = new JButton("Help");
	
	public GradingWindow() {
			System.out.println("Main window intiating.");
			
		    //Window Attributes
			setTitle("TLC Build Point Automatic Grader");
			setSize(445, 710);
			setLocationRelativeTo(null);
			setResizable(false);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			//Panel
			System.out.println("Initiating JPanel");
			JPanel panel = new JPanel();
			getContentPane().add(panel);
			
			//The Fancies
			System.out.println("Drawing some fancies");
			
			//Fonts
			Font font = new Font("times new roman", Font.BOLD, 32);
			Font normal = new Font("times new roman", Font.PLAIN, 20);
			Font small = new Font("times new roman", Font.PLAIN, 10);
			
			//Title text
			title.setFont(font);
			panel.add(title);
			
			//First Separator
			JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
			sep.setPreferredSize(new Dimension(400,2));
			panel.add(sep);
			
			//Username
			usertext.setFont(normal);
			panel.add(usertext);
			panel.add(username);
			
			//Grader's username
			buildertext.setFont(normal);
			panel.add(buildertext);
			panel.add(buildername);
			
			//Point standard grading fields
			aethsteticstext.setFont(normal);
			aethsHelp.setBackground(Color.GRAY);
			panel.add(aethsteticstext);
			panel.add(aethsHelp);
			panel.add(aethstetics);
			
			materialstext.setFont(normal);
			materHelp.setBackground(Color.GRAY);
			panel.add(materialstext);
			panel.add(materHelp);
			panel.add(materials);

			locationtext.setFont(normal);
			locatHelp.setBackground(Color.GRAY);
			panel.add(locationtext);
			panel.add(locatHelp);
			panel.add(location);

			creativitytext.setFont(normal);
			creatHelp.setBackground(Color.GRAY);
			panel.add(creativitytext);
			panel.add(creatHelp);
			panel.add(creativity);

			designtext.setFont(normal);
			desigHelp.setBackground(Color.GRAY);
			panel.add(designtext);
			panel.add(desigHelp);
			panel.add(design);
			
			landscapingtext.setFont(normal);
			panel.add(landscapingtext);
			landsHelp.setBackground(Color.GRAY);
			landscapingcheck.setToolTipText("Uncheck if landscape is inapplicable");
			landscapingcheck.setSelected(true);
			panel.add(landscapingcheck);
			panel.add(landsHelp);
			panel.add(landscaping);
			
			//Final Separator
			JSeparator buttonsep = new JSeparator();
			buttonsep.setPreferredSize(new Dimension(400,2));
			panel.add(buttonsep);
			
			//Generate Button
			JButton playGame = new JButton("Generate Report");
			playGame.setBackground(Color.GREEN);
			playGame.setFont(font);
			panel.add(playGame);
			playGame.addActionListener(this);
			
			copyToClip.setEnabled(false);
			copyToClip.setFont(font);
			copyToClip.setBackground(Color.CYAN);
			panel.add(copyToClip);
			
			//Results text field
			results.setEditable(false);
			results.setEnabled(false);
			panel.add(results);
			
			help.setBackground(Color.LIGHT_GRAY);
			panel.add(help);
			
			//Footer
			JLabel footer = new JLabel("Created by BilboB123, 2014. Version " + Main.version);
			footer.setFont(small);
			footer.setForeground(Color.GRAY);
			panel.add(footer);
			
			//Button listeners
			aethsHelp.addActionListener(
			    new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					  JOptionPane.showMessageDialog(null, "Decoration and beauty of design: \n1-3: Little attention given to decoration\n4-6: Moderately but well-done decorations\n7-9: Very beautiful and well thought-out.\n10: Perfect all-around decorations and extremely beautiful.");
					}
			    }
			);
			
			materHelp.addActionListener(
				    new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						  JOptionPane.showMessageDialog(null, "Material choice and agreement:\n1-3: Very cheap or mismatching materials\n4-6: Good material choice, but lack of variation\n7-9: Good material choice with good variation\n10: Excellent material choice with various matching materials");
						}
				    }
				);
			
			locatHelp.addActionListener(
				    new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						  JOptionPane.showMessageDialog(null, "Location choice and adaption:\n1-3: Poorly chosen location, little or no attempt to blend build\n4-6: Decent choice of location and blends, but isn't particularly well-chosen\n7-9: Location fits the build wonderfully and fits seamlessly.\n10: Amazing location and build placement, such as a mansion in a mountain dome");
						}
				    }
				);
			
			desigHelp.addActionListener(
				    new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						  JOptionPane.showMessageDialog(null, "The architecture (interior and exterior) and size of a build:\n1-3: Poorly constructed shapes and architecture\n4-6: Well planned architecture, but lacking in size (Or 'wow' factor)\n7-9: Well planned architecture and size\n10: Extremely well made architechture that compliments the size of the build");
						}
				    }
				);
			creatHelp.addActionListener(
				    new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						  JOptionPane.showMessageDialog(null, "Uniqueness of the build:\n1-3: Very common build that's not very impessive\n4-6: Unique build but not particularly impressive\n7-9: Unique, impressive and well-executed build\n10: Mind-blowingly unique (Like Bioshock Stuff)");
						}
				    }
				);
			
			landsHelp.addActionListener(
				    new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						  JOptionPane.showMessageDialog(null, "Exterior Landscaping (Flowers, fountains, paths, etc.):\n1-3: Poorly landscaping or none at all\n4-6: Good landscaping, but not complete or impressive\n7-9: Good landscaping that is complete and impressive, a joy to walk upon\n10: Epic landscaping that reminds you of The Hanging Gardens or something");
						}
				    }
				);
			
			copyToClip.addActionListener(
				    new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							StringSelection stringSelection = new StringSelection (lastResults);
							Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
							clpbrd.setContents (stringSelection, null);
						}
				    }
				);
			
			help.addActionListener(
				    new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								openWebpage(new URL("http://thelegendcontinues.org/forum/m/10321957/viewthread/14021216-build-grading-system-general-building-tutorial"));
							} catch (MalformedURLException e1) {
								e1.printStackTrace();
							}
						}
				    }
				);
			
			//End
			System.out.println("Main window initiated.");
	}
	
	public boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}  
	
	public int getGrade(double score) {
		int grade = 0;
		
		if(score>=30 && score<=50) {
			grade = 1;
		} else if (score>50 && score<=70) {
			grade = 2;
		} else if (score>70 && score<=80) {
			grade = 3;
		} else if (score>80 && score<=90) {
			grade = 4;
		} else if (score>90) {
			grade = 5;
		}
		
		return grade;
	}
	
	public String getResults() {
		String finalResult = "Build created by [i]" + buildername.getText() + " [/i]and graded by [i]" + username.getText() + "[/i] \n\n\n [list]";
		boolean pass = true;
		double finalScore = 0;
		
		//Verifies that the username and builder name aren't empty or a space.
		if(username.getText()==null || username.getText()=="" || username.getText()==" ") {
			usertext.setForeground(Color.RED);
			pass = false;
		} else usertext.setForeground(Color.BLACK);
		
		if(buildername.getText()==null || buildername.getText()=="" || buildername.getText()==" ") {
			buildertext.setForeground(Color.RED);
			pass = false;
		} else buildertext.setForeground(Color.BLACK);
		
		//Checks the values of the text fields to see if they are numbers between 0 and 10, then adds the values to the
		//result if true.
		if(isNumeric(aethstetics.getText()) && Double.parseDouble(aethstetics.getText()) > 0 && Double.parseDouble(aethstetics.getText()) <= 10 ) {
			finalScore = Double.parseDouble(aethstetics.getText()) * 2;
			aethsteticstext.setForeground(Color.BLACK);
			finalResult = finalResult + "[*]Aethstetics grade: [b]" + aethstetics.getText() + "[/b]\n";
		} else { 
			pass = false;
			aethsteticstext.setForeground(Color.RED);
		}
		
		if(isNumeric(materials.getText()) && Double.parseDouble(materials.getText()) > 0 && Double.parseDouble(materials.getText()) <= 10) {
			finalScore += Double.parseDouble(materials.getText()) * 2;
			materialstext.setForeground(Color.BLACK);
			finalResult = finalResult + "[*]Materials grade: [b]" + materials.getText() + "[/b]\n";
		} else { 
			pass = false;
			materialstext.setForeground(Color.RED);
		}
		
		if(isNumeric(location.getText()) && Double.parseDouble(location.getText()) > 0 && Double.parseDouble(location.getText()) <= 10) {
			locationtext.setForeground(Color.BLACK);
			finalResult = finalResult + "[*]Location grade: [b]" + location.getText() + "[/b]\n";
			
			if(landscapingcheck.isSelected()) {
			    finalScore += Double.parseDouble(location.getText());
			} else finalScore += Double.parseDouble(location.getText()) * 2;
		} else { 
			pass = false;
			locationtext.setForeground(Color.RED);
		}
		
		if(isNumeric(creativity.getText()) && Double.parseDouble(creativity.getText()) > 0 && Double.parseDouble(creativity.getText()) <= 10) {
			creativitytext.setForeground(Color.BLACK);
			finalScore += Double.parseDouble(creativity.getText()) * 2;
			finalResult = finalResult + "[*]Creativity grade: [b]" + creativity.getText() + "[/b]\n";
		} else { 
			pass = false;
			creativitytext.setForeground(Color.RED);
		}
		
		if(isNumeric(design.getText()) && Double.parseDouble(design.getText()) > 0 && Double.parseDouble(design.getText()) <= 10) {
			designtext.setForeground(Color.BLACK);
			finalScore += Double.parseDouble(design.getText()) * 2;
			finalResult = finalResult + "[*]Design grade: [b]" + design.getText() + "[/b]\n";
		} else { 
			pass = false;
			designtext.setForeground(Color.RED);
		}
		
		if(isNumeric(landscaping.getText()) && landscapingcheck.isSelected() && Double.parseDouble(landscaping.getText()) > 0 && Double.parseDouble(landscaping.getText()) <= 10) {
			landscapingtext.setForeground(Color.BLACK);
			finalScore += Double.parseDouble(landscaping.getText());
			finalResult = finalResult + "[*]Landscaping grade: [b]" + landscaping.getText() + "[/b][/list]\n";
		} else {
			if(landscapingcheck.isSelected()) {
				landscapingtext.setForeground(Color.RED);
				pass = false;
				
				if(isNumeric(landscaping.getText()) && Double.parseDouble(landscaping.getText()) > 0 && Double.parseDouble(landscaping.getText()) <= 10) {
					landscapingtext.setForeground(Color.BLACK);
				}
			}
		}
		
		finalResult = finalResult + "\nFinal Grade: [color=#dd2423]" + finalScore +"/100[/color]\n" + "Points Assigned: [color=#dd2423]" + getGrade(finalScore) + "[/color]\n\n***ENTER BUILDER COMMENT HERE***";
		
		if(pass) {
			lastResults = finalResult;
			copyToClip.setEnabled(true);
			return finalResult;
		} else return "There is an error in the highlighted field(s). \nPlease fill out all fields correctly";
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		results.setEnabled(true);
		results.setText(getResults());
		
		System.out.println("Making a sexy...");
	}
	
	//Opens a specified webpage, accepts URI and URL
	public static void openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	public static void openWebpage(URL url) {
	    try {
	        openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	}
}
