package primary;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.*;

@SuppressWarnings("serial")
public class chatBox extends JPanel implements ActionListener{
 
 private JTextField textField;
 private JTextArea textArea;
 private JScrollPane textAreaScroll;
 String text;
 JLabel label1;
 
 class JTextFieldLimit extends PlainDocument {
   private int limit;

   JTextFieldLimit(int limit) {
    super();
    this.limit = limit;
    }

   public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
     if (str == null) return;

     if ((getLength() + str.length()) <= limit) {
       super.insertString(offset, str, attr);
     }
   }
 }
 
 public chatBox(String s){
  super(s);
  this.setPreferredSize(new Dimension(500, 350));
  setGUI();
 }
 
 public static void createAndShowGUI(){
  chatBox console = new chatBox("Chat Box");
  console.pack();

  console.setDefaultCloseOperation(EXIT_ON_CLOSE);
  console.setVisible(true);
 }
 
 public void setGUI(){
  
  LayoutManager manager = new BorderLayout();
  this.setLayout(manager);
  
   final JButton button1 = new JButton("Talk");
   final JButton button2 = new JButton("YELL");
   label1 = new JLabel("max 10 chars");
   textField = new JTextField(15);
   this.add(label1);
   this.add(textField);
   textField.setDocument
   (new JTextFieldLimit(100));
   textField.addActionListener(this);
   
 

  textArea = new JTextArea();
  textArea.setLineWrap(true);
  textArea.setEditable(false);
  textAreaScroll = new JScrollPane(textArea);
//  this.add(button1,BorderLayout.EAST);
 
  this.add(textField, BorderLayout.SOUTH);
   this.add(textAreaScroll, BorderLayout.CENTER);
   JPanel blank = new JPanel();
   JPanel blank1 = new JPanel();
   JPanel blank2 = new JPanel();
   JPanel blank3 = new JPanel();
   JPanel panEast = new JPanel();
   panEast.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
   panEast.setLayout(new GridLayout(6,1));
//   panEast.setLayout(new FlowLayout());
   panEast.add(blank);
   blank.setBackground(Color.white);
   panEast.add(blank1);
   blank1.setBackground(Color.white);
   panEast.add(blank2);
   blank2.setBackground(Color.white);
   panEast.add(blank3);
   blank3.setBackground(Color.white);
   panEast.add(button1);
   panEast.add(button2);
   button2.setForeground(Color.red);
//  textField.setForeground(Color.red);
//  textArea.setForeground(Color.red);
   add( panEast, BorderLayout.EAST);
   textField.setDocument
   (new JTextFieldLimit(100));
   
   
   
   
   
   
  button1.addActionListener(new ActionListener() {
     
   public void actionPerformed(ActionEvent e) {
     
      if(textField.getText().length()>0){
	        text = textField.getText();
	        textArea.append("Talk: "+text + "\n");
	        textField.setText("");
	        }
	}
 });
 button2.addActionListener(new ActionListener() {
     
    public void actionPerformed(ActionEvent e) {
     
    
    if(textField.getText().length()<11&&textField.getText().length()>0){
    text = textField.getText();
    
    textArea.append("YELL: "+text + "\n");
    textField.setText("");
    
    
    
    }else if(textField.getText().length()<1){
    	textArea.append("");
    
    }else{
    	textArea.append("***TEXT OVER LIMIT***"+"\n");
    }
     }
 });
 }
 
 
 
 
 
 
 public void actionPerformed(ActionEvent evt){
  
   
	    if(textField.getText().length()>0){
	        text = textField.getText();
	        textArea.append("Talk: "+text + "\n");
	        textField.setText("");
	        
	        }
     }
  

 
 
 
 public String fetch(String s) throws Exception{
  URL iceworld = new URL(s);
  URLConnection con = iceworld.openConnection();
  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
  String ret = in.readLine();
  return ret;
 }
 
 
 
 
 
 public static void main(String [] args){
  //create a new thread which calls this class and run it.
  javax.swing.SwingUtilities.invokeLater(new Runnable(){
   public void run(){
    chatBox.createAndShowGUI();
   }
  });
 }
}
