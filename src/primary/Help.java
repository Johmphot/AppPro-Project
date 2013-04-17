import java.awt.*;
import java.io.IOException;
import java.net.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.text.html.*;


public class Help extends JDialog implements HyperlinkListener {
JTabbedPane tabbedPane;

JComponent ICEWorldPeekTab;
JComponent ICEPortTab;
JComponent GlossaryTab;

JPanel ICEWorldPeekContentPane;
JEditorPane ICEWorldPeekContent;

JPanel ICEPortContentPane;
JEditorPane ICEPortContent;

JPanel GlossaryContentPane;
JEditorPane GlossaryContent;

	public Help(){
		this.setSize(800, 1000);
		setLocationRelativeTo(null);
		
		setupTabbedPane();
		setResizable(false);
		setVisible(true);
		
	}
	
	private void setupTabbedPane(){
		tabbedPane = new JTabbedPane();
		
		ICEWorldPeekTab = setupICEWorldPeekTab();
		tabbedPane.addTab("ICE World Peek",null,ICEWorldPeekTab,"ICE World Peek Manual");
		
		ICEPortTab = setupICEPortTab();
		tabbedPane.addTab("ICE Port", null, ICEPortTab,"ICE Port Manual");
		
		GlossaryTab = setupGlossaryTab();
		tabbedPane.addTab("Glossary", null, GlossaryTab,"Specific word using in help");
		
		add(tabbedPane);
		pack();
	}

	

	private JComponent setupGlossaryTab() {
		GlossaryContentPane = new JPanel();
		GlossaryContentPane.setLayout(new BorderLayout());
		try {
			GlossaryContent = new JEditorPane("https://dl.dropboxusercontent.com/u/37431797/help/glossary.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GlossaryContent.setEditable(false);
		GlossaryContent.addHyperlinkListener(this);
		GlossaryContentPane.add(GlossaryContent,BorderLayout.CENTER);
		return GlossaryContentPane;
	}

	private JComponent setupICEPortTab() {
		ICEPortContentPane = new JPanel();
		ICEPortContentPane.setLayout(new BorderLayout());
		
		try {
			ICEPortContent = new JEditorPane("https://dl.dropboxusercontent.com/u/37431797/help/ICEPort/index.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ICEPortContent.setEditable(false);
		ICEPortContent.addHyperlinkListener(this);
		ICEPortContentPane.add(ICEPortContent,BorderLayout.CENTER);
		return ICEPortContentPane;
	}

	private JComponent setupICEWorldPeekTab() {
		ICEWorldPeekContentPane = new JPanel();
		ICEWorldPeekContentPane.setLayout(new BorderLayout());

				try {
				
				ICEWorldPeekContent = new JEditorPane("https://dl.dropboxusercontent.com/u/37431797/help/ICEWorldPeek/index.html");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		ICEWorldPeekContent.addHyperlinkListener(this);
		ICEWorldPeekContent.setEditable(false);
		ICEWorldPeekContentPane.add(ICEWorldPeekContent,BorderLayout.CENTER);
		return ICEWorldPeekContentPane;
	}
	
	 public void hyperlinkUpdate(HyperlinkEvent event) {
		    
		 if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
		   //   try {
		    	  if (event instanceof HTMLFrameHyperlinkEvent) {
	                     HTMLFrameHyperlinkEvent  evt = (HTMLFrameHyperlinkEvent)event;
	                     HTMLDocument doc = (HTMLDocument) ((JTextComponent) event.getSource()).getDocument();
	                     doc.processHTMLFrameHyperlinkEvent(evt);}
		   	  else{
		    		  
		    		  if(Desktop.isDesktopSupported()) {
		    			    try {
								Desktop.getDesktop().browse(event.getURL().toURI());
							} catch (IOException e) {
								e.printStackTrace();
							} catch (URISyntaxException e) {
								e.printStackTrace();
							}
		    			}
		   	  }  // this above part is to bring hyperlink to be opened in default browser
		    	  // but there is a bug from htmlframehyperlinkevent since every action in frame are count as htmlframehyperlinkevent
		    	  // and this cannot bring url inside html frame to use default browser
		    	  
		    	 // ignore this if html are used in frame 
		    	/*  else{
		    	 if (event.getSource().equals(ICEWorldPeekContent))
		        ICEWorldPeekContent.setPage(event.getURL());
		    	 else if (event.getSource().equals(ICEPortContent))
		    	ICEPortContent.setPage(event.getURL());
		    	 else if (event.getSource().equals(GlossaryContent))
		        GlossaryContent.setPage(event.getURL());
		    	 else {
		    		 
		    		 }}
		    	 
		      } catch(IOException ioe) {
		      }*/
		      }
		    }
		  }




